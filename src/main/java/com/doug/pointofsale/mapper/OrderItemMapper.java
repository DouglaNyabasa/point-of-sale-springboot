package com.doug.pointofsale.mapper;

import com.doug.pointofsale.models.OrderItem;
import com.doug.pointofsale.payload.dto.OrderItemDto;

public class OrderItemMapper {

    public  static OrderItemDto toDTO(OrderItem item){

        if(item == null){
            return null;
        }

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(item.getId());
        orderItemDto.setProductId(item.getProduct().getId());
        orderItemDto.setQuantity(item.getQuantity());
        orderItemDto.setPrice(item.getPrice());
        orderItemDto.setProduct(ProductMapper.toDTO(item.getProduct()));
        return orderItemDto;
    }
}
