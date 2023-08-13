package com.example.banking.users;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private final UsersRepository usersRepository;

    @GetMapping("/create")
    public String create(){
        String url = "/users/template";
        return url;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Users> usersList = this.usersRepository.findAll();
        model.addAttribute("usersList", usersList);
        return "users/list";
    }
}
