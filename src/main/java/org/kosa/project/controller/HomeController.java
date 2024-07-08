package org.kosa.project.controller;

import lombok.RequiredArgsConstructor;
import org.kosa.project.security.CustomUserDetails;
import org.kosa.project.service.MeetingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.kosa.project.controller.MeetingController.getMeetingList;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MeetingService meetingService;

    @GetMapping("/")
    public String home(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        return getMeetingList(page, model, meetingService);
    }
}
