package com.example.banking.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {
    @GetMapping("/account/create")
    public String create(){
        System.out.println("hi");
        String url = "create.html";
        return url;
    }
}
