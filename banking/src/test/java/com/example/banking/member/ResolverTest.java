package com.example.banking.member;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.DefaultMessageCodesResolver;

@SpringBootTest
public class ResolverTest {
    @Test
    void messageCodesResolverObject() {
        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "member");
        for (String msg : messageCodes) {
            System.out.println(msg);
        }
    }

    @Test
    void messageCodesResolverField() {
        DefaultMessageCodesResolver codesResolver = new DefaultMessageCodesResolver();
        String[] messageCodes = codesResolver.resolveMessageCodes("required", "member", "id", String.class);
        for (String msg : messageCodes) {
            System.out.println(msg);
        }
    }
}
