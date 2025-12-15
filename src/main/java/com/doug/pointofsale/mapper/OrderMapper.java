package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.Order;
import com.doug.pointofsale.payload.dto.OrderDto;

import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setBranchId(order.getBranch().getId());
        orderDto.setCustomerId(order.getCustomer().getId());
        orderDto.setPaymentType(order.getPaymentType());
        orderDto.setCreatedAt(order.getCreatedAt());
        orderDto.setItems(order.getItems()
                .stream()
                .map(OrderItemMapper::toDTO)
                .collect(Collectors.toList())

        );
        return orderDto;
    }
}
