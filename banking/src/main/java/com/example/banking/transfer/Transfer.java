package com.example.banking.transfer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;


@Entity
@Data
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String fromAcc;

    @Column(length = 30)
    private String toAcc;

    @Column(length = 40)
    private Integer amount;

    @Column(length = 100)
    private String memo;

    @Column(length = 10)
    private Integer type;

    @Column(name = "create_date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createDate;
}