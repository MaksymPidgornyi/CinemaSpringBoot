package com.controller;

import com.controller.utils.ControllerUtils;
import com.model.entity.*;
import com.model.entity.enums.Role;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class AuthorizationController {

    private UserService userService;
    private MessageSource messageSource;

    @Autowired
    public AuthorizationController(UserService userService, MessageSource messageSource) {
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String getRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(@Valid User user, BindingResult bindingResult, Model model) {

        System.out.println(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> fieldErrorMap = ControllerUtils.getErrorsMap(bindingResult);

            model.mergeAttributes(fieldErrorMap);
        }
        else if (user.getPassword().equals(user.getConfirmation())) {

            user.setRole(Role.USER);
            user.setPassword(userService.encodePassword(user.getPassword()));
            System.out.println(user.getPassword().length());
            user.setActivity(true);
            userService.createUser(user);

            return "redirect:/login";
        }

        return "register";
    }

}
