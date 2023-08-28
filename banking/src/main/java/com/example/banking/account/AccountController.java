package com.example.banking.account;

import com.example.banking.member.Member;
import com.example.banking.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;
    private final MemberService memberService;
    private final AccountValidator accountValidator;

    @InitBinder
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(accountValidator);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String createByMember(Model model) {
        model.addAttribute("accountCreateForm", new AccountCreateForm());
        return "account/create";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String create(Model model,
                         @Valid AccountCreateForm accountCreateForm,
                         BindingResult bindingResult,
                         Principal principal) {
        if (!accountCreateForm.getPassword1().equals(accountCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
        }

        if (bindingResult.hasErrors()) {
            for(FieldError error : bindingResult.getFieldErrors()){
                System.out.println("FieldError (" + error.getField() + "/" + error.getCode() + ")" + "  : " + error.getDefaultMessage());
            }
            System.out.println("===================");
            log.info("errors = {}", bindingResult);
            return "account/create";
        }

        Member member = this.memberService.getMember(principal.getName());
        try {
            this.accountService.create(member, accountCreateForm);
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("failed", "계좌가 중복되어 있습니다.");
            return "account/create";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("failed", e.getMessage());
            return "account/create";
        }

        model.addAttribute("mid", member.getMid());
        return "account/create_complete";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        String url = "create";
        return "This is test page";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("list")
    public String list(Model model,
                       Principal principal) {
        Member _member = this.memberService.getMember(principal.getName());
        List<Account> accountList = this.accountService.getList(_member.getId());
        model.addAttribute("accountList", accountList);
        return "account/list";
    }
}
