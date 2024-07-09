package org.kosa.project.controller;

import lombok.extern.log4j.Log4j2;
import org.kosa.project.config.annotation.MeetingFileServiceQualifier;
import org.kosa.project.security.CustomUserDetails;
import org.kosa.project.service.Enum.Category;
import org.kosa.project.service.dto.SearchCondition;
import org.kosa.project.service.fileupload.FileUploadService;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.kosa.project.controller.DTOMapper.convertToMeetingRegisterDto;

@Log4j2
@Controller
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;
    private final FileUploadService fileUploadService;

    private static Integer PAGE_SIZE = 6;

    public MeetingController(MeetingService meetingService, @MeetingFileServiceQualifier FileUploadService fileUploadService) {
        this.meetingService = meetingService;
        this.fileUploadService = fileUploadService;
    }

    // requestParameter 정보 -> 객체를 매핑
    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10;

        return getMeetingList(page, model, meetingService);
    }

    static String getMeetingList(int page, Model model, MeetingService meetingService) {
        List<MeetingDetailDto> list = meetingService.meetingList(page, PAGE_SIZE);
        int totalMeetings = meetingService.getTotalMeetingCount();
        int totalPages = (int) Math.ceil((double) totalMeetings / PAGE_SIZE);

        for (MeetingDetailDto meetingDetailDto : list) {
            System.out.println(meetingDetailDto);
        }
        model.addAttribute("list", list);

        model.addAttribute("totalPages", totalPages);
        return "meeting/list";
    }


    @GetMapping("/detailMeeting")
    public String detailMeeting(@RequestParam long meetingId, Model model) {
        //나중에 예외처리 할 것이 뭐냐면? param값이 없는 값이 없다고 표시
        MeetingDetailDto meetingDetailDto = meetingService.detailMeeting(meetingId);
        System.out.println(meetingDetailDto.getCategory().getDisplayName());
        System.out.println(meetingDetailDto);
        model.addAttribute(meetingDetailDto);

        return "meeting/detailMeeting";
    }


    @GetMapping("/insertMeeting")
    public String insertMeeting(Model model) {
        model.addAttribute("meetingRegisterRequest", new MeetingRegisterRequest(1L, Category.DESSERT, null, null, 0, null, null));
        model.addAttribute("categories", Category.values()); //Enum 카테고리 데이터 넘기기
        return "meeting/insertMeeting";
    }

    @PostMapping("/insertMeeting")
    public String insertMeetingData(@ModelAttribute MeetingRegisterRequest request, @AuthenticationPrincipal CustomUserDetails user) {
        System.out.println(user.getUserId());
        long userId = Long.parseLong(user.getUserId());
        String response = request.validate();
        String fileUploadUrl = fileUploadService.saveFile(request.image());
        meetingService.save(convertToMeetingRegisterDto(request, fileUploadUrl, userId));
        return response;
    }

}

