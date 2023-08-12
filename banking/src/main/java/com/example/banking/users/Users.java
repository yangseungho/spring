package com.example.banking.users;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
public class Users {
    @Id
    @Column(length = 30)
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(length = 30, nullable = false)
    private String name;

    @Column(name = "create_date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createDate;
}
