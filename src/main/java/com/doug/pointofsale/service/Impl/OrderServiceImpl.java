package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.domain.OrderStatus;
import com.doug.pointofsale.domain.PaymentType;
import com.doug.pointofsale.mapper.OrderMapper;
import com.doug.pointofsale.models.*;
import com.doug.pointofsale.payload.dto.OrderDto;
import com.doug.pointofsale.repository.OrderRepository;
import com.doug.pointofsale.repository.ProductRepository;
import com.doug.pointofsale.service.OrderService;
import com.doug.pointofsale.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserService userService, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception {
        User cashier = userService.getCurrentUser();
        Branch branch = cashier.getBranch();
        if (branch == null) {
            throw new Exception("cashier branch is not found");
        }
        Order order = new Order();
        order.setBranch(branch);
        order.setCashier(cashier);
        order.setCustomer(orderDto.getCustomer());
        order.setPaymentType(orderDto.getPaymentType());

        List<OrderItem> orderItems = orderDto.getItems()
                .stream()
                .map(
                        itemDto ->{
                            Product product = productRepository.findById(itemDto.getProductId())
                                    .orElseThrow(()-> new EntityNotFoundException("product not found"));

                             OrderItem  orderItem = new OrderItem();
                             orderItem.setProduct(product);
                             orderItem.setQuantity(itemDto.getQuantity());
                             orderItem.setPrice(product.getSellingPrice() * itemDto.getQuantity());
                             orderItem.setOrder(order);
                             return orderItem;
                        }
                ).toList();
        double total = orderItems.stream().mapToDouble(
                OrderItem::getPrice
        ).sum();
        order.setTotalAmount(total);
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);


        return OrderMapper.toDTO(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long id) throws Exception {
        return orderRepository.findById(id)
                .map(OrderMapper::toDTO)
                .orElseThrow(
                ()-> new Exception("order not found with id"+ id)
        );
    }

    @Override
    public List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus) throws Exception {
        return orderRepository.findByBranchId(branchId)
                .stream()
                .filter(order -> customerId == null || (order.getCustomer() != null && order.getCustomer().getId().equals(customerId)))
                .filter(order -> cashierId == null || order.getCashier() != null && order.getCashier().getId().equals(cashierId))
                .filter(order ->  paymentType == null || order.getPaymentType() == paymentType )
                .map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {
        return orderRepository.findByCashierId(cashierId)
                .stream()
                .map(OrderMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) {

    }

    @Override
    public List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception {
        return List.of();
    }

    @Override
    public List<OrderDto> getTodayOrdersByCustomerId(Long customerId) throws Exception {
        return List.of();
    }

    @Override
    public List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) throws Exception {
        return List.of();
    }
}
