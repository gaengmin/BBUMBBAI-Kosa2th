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

        List<MeetingDetailDto> list = meetingService.meetingList(null, page);
        int totalMeetings = meetingService.getTotalMeetingCount();
        int totalPages = (int)Math.ceil((double) totalMeetings/ pageSize);

        for (MeetingDetailDto meetingDetailDto : list) {
            System.out.println(meetingDetailDto);
        }
        // page 공통적으로 필요,
        model.addAttribute("list", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "meeting/list";
    }
}
