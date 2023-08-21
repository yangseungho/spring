package com.example.banking.account;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Account.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account account = (Account)target;

        if(!StringUtils.hasText(account.getAccountNumber())){
            errors.rejectValue("account_number", "계좌번호를 입력하세요");
        }

        if(!StringUtils.hasText(account.getPassword())){
            errors.rejectValue("password", "계좌 비밃번호를 입력해주세요");
        }

        if(!StringUtils.hasText(account.getBankName())){
            errors.rejectValue("bank_name", "은행을 입력해주세요");
        }

//        if(account.getMember() == null){
//            errors.rejectValue("mid", "계좌주 정보가 필요합니다.");
//        }

    }
}
