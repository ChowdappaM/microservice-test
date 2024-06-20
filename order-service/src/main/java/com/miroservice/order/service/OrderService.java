package com.miroservice.order.service;

import com.miroservice.order.dao.OrderRepository;
import com.miroservice.order.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {


    private OrderRepository orderRepository;

    public void placeOrder(Order order) {
        orderRepository.save(order);
    }

    public Order getOrderDetails(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
