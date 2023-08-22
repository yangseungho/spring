package com.example.banking.account;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreateForm {
    @Size(min = 9, max = 20)
    @NotEmpty(message = "계좌번호는 필수항목입니다.")
    private String accountNumber;

    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    @NotEmpty(message = "비밀번호 검증은 필수항목입니다.")
    private String password2;

    @NotEmpty(message = "은행은 필수항목입니다.")
    private String bankName;

    private String accountAlias;
}
