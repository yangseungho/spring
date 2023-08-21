package com.example.banking.member;

import com.example.banking.account.Account;
import com.example.banking.account.AccountValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
            for(FieldError error : bindingResult.getFieldErrors()){
                System.out.println("FieldError (" + error.getField() + "/" + error.getCode() + ")" + "  : " + error.getDefaultMessage());
//                model.addAttribute("error", error);
            }
            System.out.println("===================");
            bindingResult.addError(new ObjectError("member", "테스트 입니다."));
            log.info("errors = {}", bindingResult);
            return "member/create";
        }

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
