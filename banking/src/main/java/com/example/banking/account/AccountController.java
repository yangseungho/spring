package com.example.banking.account;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/account")
public class AccountController {
    @GetMapping("/create")
    public String create(){
        String url = "create";
        return url;
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        System.out.println("hi");
        String url = "create";
        return "This is test page";
    }

    @GetMapping("/account/list")
    @ResponseBody
    public String list(){
        return "list";
    }
}
