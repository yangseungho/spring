package com.example.banking.account;

import com.example.banking.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(String accountNumber);
    Account findByMember(Member member);
    Account findByBankName(String bankName);
    Account findByBankNameAndAccountNumber(String bankName, String accountNumber);
    List<Account> findByBankNameLike(String bankName);
}