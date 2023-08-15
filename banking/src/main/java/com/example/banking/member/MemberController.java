package com.example.banking.member;

import com.example.banking.account.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("member", new Member());
        String url = "/member/create";
        return url;
    }

    @PostMapping("/create")
    public String create(Model model,
                         @ModelAttribute Member memberModel){
        this.memberService.create(memberModel);
        return "member/create_complete";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Member> memberList = this.memberService.getList();
        model.addAttribute("memberList", memberList);
        return "member/list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        Member member = this.memberService.getMember(id);
        model.addAttribute("member", member);
        return "member/detail";
    }
}
