package com.example.banking.account;

import com.example.banking.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Account> getList(){
        return this.accountRepository.findAll();
    }

    public void create(Member member, AccountCreateForm accountCreateForm) {
        Account account = new Account();

        account.setMember(member);
        account.setAccountNumber(accountCreateForm.getAccountNumber());
        account.setPassword(passwordEncoder.encode(member.getPassword()));
        account.setBankName(accountCreateForm.getBankName());
        account.setAccountAlias(accountCreateForm.getAccountAlias());
        account.setAmount(0);
        account.setCreateDate(LocalDateTime.now());

        this.accountRepository.save(account);
    }
}
