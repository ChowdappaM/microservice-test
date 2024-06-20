package com.miroservice.order.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int quantity;
}
