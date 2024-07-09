package org.kosa.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.kosa.project.service.UserService;
import org.kosa.project.service.dto.LoginForm;
import org.kosa.project.service.dto.UserDetailDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("loginform", new LoginForm());
        return "loginForm";
    }

    @GetMapping("/logoutpage")
    public String logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String linkUrl = request.getHeader("Referer");
        System.out.println("LoginController.logout");
        System.out.println(linkUrl);
        if (linkUrl != null) {
            return "redirect:" + linkUrl;
        } else{
            return "redirect:/";
        }
    }

}
