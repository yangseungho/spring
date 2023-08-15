package com.example.banking.account;

import com.example.banking.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> getList(){
        return this.accountRepository.findAll();
    }

    public void create(Member member, Account account) {
        account.setMember(member);
        account.setAmount(0);
        account.setCreateDate(LocalDateTime.now());

        this.accountRepository.save(account);
    }
}
