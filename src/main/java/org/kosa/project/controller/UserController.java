package org.kosa.project.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/")
    public String home() {
        System.out.println("홈 호출");
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "loginForm";
    }
}
