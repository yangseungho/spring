package com.example.banking.member;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;

@Component
public class MemberValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member)target;

        if(!StringUtils.hasText(member.getMid())){
//            errors.rejectValue("id", "required", "ID를 입력하세요");
            errors.rejectValue("id", "required");
        }

        if(!StringUtils.hasText(member.getPassword())){
//            errors.rejectValue("password", "required", "비밃번호를 입력해주세요");
            errors.rejectValue("password", "required");
        }

        if(!StringUtils.hasText(member.getName())){
            // 이렇게 입력해도 resolver에서 매핑된다.
            errors.rejectValue("name", "required", "회원이름을 입력해주세요");
//            errors.rejectValue("name", "required");
        }

        if(!StringUtils.hasText(member.getEmail())){
            errors.rejectValue("email", "required", "이메일을 입력해주세요");
//            errors.rejectValue("email", "required");
        }
    }
}
