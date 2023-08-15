package com.example.banking.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MemberTest {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testJpaInsert() {
        Member u1 = new Member();
        u1.setMid("seungho_yang");
        u1.setPassword("1111");
        u1.setName("승호");
        u1.setCreateDate(LocalDateTime.now());
        this.memberRepository.save(u1);

        Member u2 = new Member();
        u2.setMid("park");
        u2.setPassword("1234");
        u2.setName("대원");
        u2.setCreateDate(LocalDateTime.now());

        this.memberRepository.save(u2);
    }
}