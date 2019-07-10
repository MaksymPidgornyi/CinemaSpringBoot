package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/cinema")
public class AppController {
    
    public void getMainPage(){

    }

    @GetMapping("/login")
    public String getLogin(){
        return "login.ftl";
    }
}
