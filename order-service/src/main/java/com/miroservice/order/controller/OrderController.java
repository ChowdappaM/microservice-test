package com.miroservice.order.controller;

import com.miroservice.order.dto.OrderRequest;
import com.miroservice.order.model.Order;
import com.miroservice.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private OrderService orderService;
    @GetMapping("{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
        return new ResponseEntity<>(orderService.getOrderDetails(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderRequest orderRequest) throws Exception {
        orderService.placeOrder(orderRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
