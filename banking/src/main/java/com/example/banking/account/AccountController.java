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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/create/{id}")
    public String createByMember(Model model,
                                 @PathVariable("id") Long id) {
        model.addAttribute("accountCreateForm", new AccountCreateForm());
        model.addAttribute("mid", id);
        return "account/create";
    }

    @PostMapping("/create/{mid}")
    public String create(Model model,
                         @PathVariable("mid") Long id,
                         @Valid AccountCreateForm accountCreateForm,
                         BindingResult bindingResult) {
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

        Member member = this.memberService.getMember(id);
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

        model.addAttribute("mid", id);
        return "account/create_complete";
    }

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        System.out.println("hi");
        String url = "create";
        return "This is test page";
    }

    @GetMapping("list/{mid}")
    public String list(Model model,
                       @PathVariable("mid") Long id) {
        List<Account> accountList = this.accountService.getList();
        model.addAttribute("accountList", accountList);
        return "account/list";
    }
}
