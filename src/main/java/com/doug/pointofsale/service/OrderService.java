package com.doug.pointofsale.service;

import com.doug.pointofsale.domain.OrderStatus;
import com.doug.pointofsale.domain.PaymentType;
import com.doug.pointofsale.payload.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto) throws Exception;
    OrderDto getOrderById(Long id) throws Exception;
    List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus) throws Exception;
    List<OrderDto> getOrderByCashier(Long cashierId);
    void deleteOrder(Long id);
    List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception;
    List<OrderDto> getTodayOrdersByCustomerId(Long customerId) throws Exception;
    List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) throws Exception;


}
