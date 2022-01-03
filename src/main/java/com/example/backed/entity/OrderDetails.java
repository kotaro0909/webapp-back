package com.example.backed.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private Long orderDetailId;

    @Column()
    private Long itemId;

    @Column(length = 20)
    private String itemCode;

    @Column(length = 255)
    private String itemName;

    @Column(precision = 12, scale = 2)
    private BigDecimal salesPrice;

    @Column()
    private Long orderNumber;
}
