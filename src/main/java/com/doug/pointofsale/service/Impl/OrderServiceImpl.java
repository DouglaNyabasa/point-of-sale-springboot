package com.doug.pointofsale.service.Impl;

import com.doug.pointofsale.domain.OrderStatus;
import com.doug.pointofsale.domain.PaymentType;
import com.doug.pointofsale.payload.dto.OrderDto;
import com.doug.pointofsale.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception {
        return null;
    }

    @Override
    public OrderDto getOrderById(Long id) throws Exception {
        return null;
    }

    @Override
    public List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus) throws Exception {
        return List.of();
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {
        return List.of();
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
