package com.fse.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/user-registration-boot"})
    public String homePage() {
        return "/home";
    }
}


