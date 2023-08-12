package com.example.banking.account;

import jakarta.persistence.*;

import lombok.Data;
import com.example.banking.users.Users;

import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Users user;

    @Column(length = 30, nullable = false)
    private String accountNumber;

    @Column(length = 20)
    private String bankName;

    @Column(name = "create_date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createDate;

    @Column(length = 40)
    @ColumnDefault("0")
    private Integer amount;
}
