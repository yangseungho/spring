package com.example.banking.account;

import com.example.banking.member.Member;
import com.example.banking.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
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
        model.addAttribute("account", new Account());
        model.addAttribute("mid", id);
        return "account/create";
    }

    @PostMapping("/create/{mid}")
    public String create(Model model,
                         @PathVariable("mid") Long id,
                         @Validated @ModelAttribute Account accountModel,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("account", new Account());
            model.addAttribute("mid", id);
            return "account/create";
        }

        Member member = this.memberService.getMember(id);
        this.accountService.create(member, accountModel);
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
