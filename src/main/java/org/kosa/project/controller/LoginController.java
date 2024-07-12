package org.kosa.project.controller;

import lombok.RequiredArgsConstructor;
import org.kosa.project.service.UserService;
import org.kosa.project.service.dto.user.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginform", new LoginForm());
        return "loginForm";
    }
}
