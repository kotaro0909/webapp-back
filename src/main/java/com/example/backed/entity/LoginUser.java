package com.example.backed.entity;

import lombok.Data;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;

@Entity
@Data
public class LoginUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column()
    private Long loginUserId;

    @Column(length = 20)
    private String loginId;

    @Column(length = 255)
    private String loginPassword;

    @Column(length = 255)
    private String userName;
}
