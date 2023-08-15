package com.example.banking.member;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, nullable = false, unique = true)
    private String mid;

    @Column(length = 30, nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(length = 100)
    private String email;

    @Column(name = "create_date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createDate;
}
