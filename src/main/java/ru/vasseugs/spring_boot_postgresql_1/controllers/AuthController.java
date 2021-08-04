package ru.vasseugs.spring_boot_postgresql_1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/guitars")
public class AuthController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
