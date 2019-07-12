package com.controller;

import com.model.dto.UserDto;
import com.model.entity.*;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthorizationController {

    private UserService userService;
    private MessageSource messageSource;

    @Autowired
    public AuthorizationController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    
    public void getMainPage(){

    }

    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    @GetMapping("/register")
    public String getRegister(){
        return "register";
    }

    @PostMapping("/register")
    public void postRegister(@RequestBody UserDto dto){

        System.out.println(dto);

        User user = userService.dtoToUser(dto);

        System.out.println(user);

        userService.createUser(user);
    }

}
