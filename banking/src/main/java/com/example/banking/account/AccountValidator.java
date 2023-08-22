package com.example.banking.account;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AccountCreateForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        AccountCreateForm account = (AccountCreateForm)target;

        if(!StringUtils.hasText(account.getAccountNumber())){
            errors.rejectValue("accountNumber", "required", "계좌번호를 입력하세요");
        }

        if(!StringUtils.hasText(account.getPassword1()) || !StringUtils.hasText(account.getPassword2())){
            errors.rejectValue("password", "required","계좌 비밃번호를 입력해주세요");
        }

        if(!StringUtils.hasText(account.getBankName())){
            errors.rejectValue("bankName", "required","은행을 입력해주세요");
        }

//        if(account.getMember() == null){
//            errors.rejectValue("mid", "계좌주 정보가 필요합니다.");
//        }

    }
}
