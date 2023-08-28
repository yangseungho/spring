package com.example.banking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class MainController {
    @RequestMapping("/")
    public String root(Authentication authentication){
        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication != null) {
            String currentUserName = authentication.getName();
            log.info("login 정보 : " + currentUserName);
            return "main";
        }

        return "redirect:member/login";
    }

    @RequestMapping("/index")
    public String index(){
        log.trace("trace message");
        log.debug("debug message");
        log.info("info message");
        log.warn("warn message");
        log.error("error message");
        return "index.html";
    }

    @RequestMapping("/main")
    public String main(){
        log.info("main page on");
        return "main";
    }
}