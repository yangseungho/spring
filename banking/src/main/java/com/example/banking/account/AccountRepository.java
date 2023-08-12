package com.example.banking.account;

import com.example.banking.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByAccountNumber(String accountNumber);
    Account findByUser(Users user);
    Account findByBankName(String bankName);
    Account findByBankNameAndAccountNumber(String bankName, String accountNumber);
    List<Account> findByBankNameLike(String bankName);
}