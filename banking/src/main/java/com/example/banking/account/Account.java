package com.example.banking.account;

import jakarta.persistence.*;

import lombok.Data;
import com.example.banking.member.Member;

import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Member member;

    @Column(length = 20, nullable = false, unique = true)
    private String accountNumber;

    @Column(length = 60, nullable = false)
    private String password;

    @Column(length = 20)
    private String bankName;

    @Column(name = "create_date", columnDefinition= "TIMESTAMP WITH TIME ZONE")
    private LocalDateTime createDate;

    @Column(length = 40)
    @ColumnDefault("0")
    private Integer amount;

    @Column(length = 20)
    private String accountAlias;
}
