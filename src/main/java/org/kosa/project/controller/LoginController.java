package org.kosa.project.controller;

import lombok.RequiredArgsConstructor;
import org.kosa.project.service.UserService;
import org.kosa.project.service.dto.LoginForm;
import org.kosa.project.service.dto.UserDetailDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
