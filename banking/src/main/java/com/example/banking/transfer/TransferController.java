package com.example.banking.transfer;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/transfer")
public class TransferController {
    @RequestMapping(value="/transfer", method = RequestMethod.GET)
    public String transfer(){
        return "이체";
    }
}
