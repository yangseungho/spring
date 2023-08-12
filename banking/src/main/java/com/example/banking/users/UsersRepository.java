package com.example.banking.users;

import com.example.banking.users.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findById(String id);
}
