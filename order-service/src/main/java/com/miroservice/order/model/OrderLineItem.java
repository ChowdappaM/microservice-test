package com.miroservice.order.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name= "order_line_item_t")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderNumber;
    @Column
    private int quantity;

    private Double unitPrice;

    private String skuCode;
}
