package com.example.banking.account;

import com.example.banking.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AccountService {
    private AccountRepository accountRepository;

    public void create(Member member, Map<String, String> requestData) {
        System.out.println("Account Service create");
        Account account = new Account();
        account.setAccountNumber(requestData.get("accountNumber"));
        account.setPassword(requestData.get("password"));
        account.setBankName(requestData.get("bankName"));
        account.setMember(member);
        account.setCreateDate(LocalDateTime.now());
        account.setAccountAlias(requestData.get("accountAlias"));

        this.accountRepository.save(account);
    }
}
