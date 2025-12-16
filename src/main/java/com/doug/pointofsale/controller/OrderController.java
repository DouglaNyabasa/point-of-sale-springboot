package com.doug.pointofsale.controller;

import com.doug.pointofsale.domain.OrderStatus;
import com.doug.pointofsale.domain.PaymentType;
import com.doug.pointofsale.payload.dto.OrderDto;
import com.doug.pointofsale.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto order) throws Exception {
        return  ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) throws Exception {
        return  ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<OrderDto>> getOrderByBranch(@PathVariable Long branchId , @RequestParam(required = false) Long customerId, @RequestParam(required = false) Long cashierId, @RequestParam(required = false)PaymentType paymentType, @RequestParam(required = false)OrderStatus orderStatus) throws Exception {
        return  ResponseEntity.ok(orderService.getOrdersByBranch(branchId,customerId,cashierId,paymentType,orderStatus));
    }




}
