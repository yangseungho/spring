package com.example.banking.member;

import com.example.banking.account.Account;
import com.example.banking.account.AccountValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final MemberValidator memberValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(memberValidator);
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("member", new Member());
        String url = "/member/create";
        return url;
    }

    @PostMapping("/create")
    public String create(Model model,
                         @Validated @ModelAttribute Member memberModel,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "member/create";
        }

        try {
            this.memberService.create(memberModel);
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "member/create";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "member/create";
        }

        return "member/create_complete";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Member> memberList = this.memberService.getList();
        model.addAttribute("memberList", memberList);
        return "member/list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/detail")
    public String detail(Model model, Principal principal) {
        Member member = this.memberService.getMember(principal.getName());
        model.addAttribute("member", member);
        return "member/detail";
    }
}
