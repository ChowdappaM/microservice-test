package com.miroservice.order.service;

import com.miroservice.order.dao.OrderRepository;
import com.miroservice.order.dto.InventoryResponse;
import com.miroservice.order.dto.OrderLineItemsDto;
import com.miroservice.order.dto.OrderRequest;
import com.miroservice.order.model.Order;
import com.miroservice.order.model.OrderLineItem;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {


    private OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {

        List<String> skuCodes = orderRequest.getOrderLineItems().stream().map(OrderLineItemsDto::getSkuCode).toList();
        // check the product is available, if available place order.
        InventoryResponse[] inventoryResponses = webClientBuilder.build().get().
                uri("http://inventory-service/api/v1/inventory/code", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        assert inventoryResponses != null;
        boolean found = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if(!found){
          throw  new RuntimeException("Product not found");
        }
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


    private WebClient.Builder webClientBuilder;


    @Autowired
    public void setWebClientBuilder(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
