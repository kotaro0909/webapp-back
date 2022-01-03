package com.example.backed.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderMain {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderMainId;

    @Column(length = 20)
    private String orderCode;

    @Column()
    private Long loginUserId;

    @Column(length = 20)
    private String itemCode1;

    @Column(length = 255)
    private String itemName1;

    @Column()
    private Long orderNumber1;

    @Column(length = 20)
    private String itemCode2;

    @Column(length = 255)
    private String itemName2;

    @Column()
    private Long orderNumber2;
}
