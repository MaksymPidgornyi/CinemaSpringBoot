package com.controller;

import com.model.entity.Actor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @PostMapping("/add-actor")
    public void postActor(Actor actor){

    }
}
