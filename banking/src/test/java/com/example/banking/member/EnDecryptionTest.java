package com.example.banking.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EnDecryptionTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    static int testInt = 100;

    @Test
    void testBCryptionTest() {
        // 단방향 해쉬라 비번을 알수가 없음(복호화 불능)
        String testString = "100";
        System.out.println("ori data : " + testString);
        String encode = this.passwordEncoder.encode(testString);
        System.out.println("encode data : " + encode);
        assertTrue(this.passwordEncoder.matches(testString, encode));

    }

}