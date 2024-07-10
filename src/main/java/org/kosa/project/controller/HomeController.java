package org.kosa.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.kosa.project.security.CustomUserDetails;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.SearchCondition;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequiredArgsConstructor
public class HomeController {
    // /members/1 => Path variable
    // /members?id=1 => Query String
    // 홈을 호출했을 때 meeting/list 1 page
    @GetMapping("/")
    public String home(RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("page", 1);
        return "redirect:/meeting/list";
    }

    @GetMapping("/search")
    public String search(){
        return "123";
    }



}
