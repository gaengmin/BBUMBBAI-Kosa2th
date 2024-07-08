package org.kosa.project.controller;

import lombok.RequiredArgsConstructor;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MeetingService meetingService;

    @GetMapping("/")
    public String home(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        int pageSize = 10;

        return getMeetingList(page, model, pageSize, meetingService);
    }
}
