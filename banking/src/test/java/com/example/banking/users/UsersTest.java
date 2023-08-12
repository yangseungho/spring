package com.example.banking.users;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class UsersTest {
    @Autowired
    private UsersRepository usersRepository;

    @Test
    void testJpaInsert() {
        Users u1 = new Users();
        u1.setId("seungho_yang");
        u1.setPassword("1111");
        u1.setName("승호");
        u1.setCreateDate(LocalDateTime.now());

        this.usersRepository.save(u1);
    }
}