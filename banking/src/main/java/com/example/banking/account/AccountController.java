package com.example.banking.account;

import com.example.banking.member.Member;
import com.example.banking.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final MemberService memberService;
    @GetMapping("/create/{id}")
    public String createByMember(Model model,
                                 @PathVariable("id") Long id) {
        model.addAttribute("mid", id);
        return "account/create";
    }

    @PostMapping("/create/{mid}")
    public String create(Model model,
                         @PathVariable("mid") Long id,
                         @RequestBody Map<String, String> requestData) {
        System.out.println("create call");
        Member member = this.memberService.getMember(id);
        this.accountService.create(member, requestData);
        return "main";
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
