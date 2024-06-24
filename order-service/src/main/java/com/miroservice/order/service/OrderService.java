package com.miroservice.order.service;

import com.miroservice.order.dao.OrderRepository;
import com.miroservice.order.dto.OrderLineItemsDto;
import com.miroservice.order.dto.OrderRequest;
import com.miroservice.order.model.Order;
import com.miroservice.order.model.OrderLineItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {


    private OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItems().stream().map(this::mapToDto).toList();
        order.setOrderLineItems(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItem mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItem orderLineItem = new OrderLineItem();
        orderLineItem.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItem.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItem.setUnitPrice(orderLineItemsDto.getUnitPrice());
        return orderLineItem;
    }

    public Order getOrderDetails(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
