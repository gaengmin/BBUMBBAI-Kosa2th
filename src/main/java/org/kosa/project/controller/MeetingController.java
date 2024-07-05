package org.kosa.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.kosa.project.service.Enum.Category;
import org.kosa.project.service.FileUploadService;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.MeetingRegisterDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@Controller
@RequestMapping("/meeting")
@RequiredArgsConstructor
// /meeting/list
// /member/list
public class MeetingController {
    private final MeetingService meetingService;

    private final FileUploadService fileUploadService;
    private final static String imageRootDir = "meeting";

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 6;
        int startRow = (page - 1) *6 +1;
        int endRow = page* pageSize;

        List<MeetingDetailDto> list = meetingService.meetingList(startRow, endRow);
        int totalMeetings = meetingService.getTotalMeetingCount();
        int totalPages = (int)Math.ceil((double) totalMeetings/ pageSize);

        for (MeetingDetailDto meetingDetailDto : list) {
            System.out.println(meetingDetailDto);
        }
        model.addAttribute("list", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "meeting/list";
    }

   /*
    @GetMapping("/list")
    public String list(Model model) {
        List<MeetingDetailDto> list = meetingService.meetingList();
        for (MeetingDetailDto meetingDetailDto : list) {
            System.out.println(meetingDetailDto);
        }
        model.addAttribute("list", list);
        return "meeting/list";
    }*/

    @GetMapping("/detailMeeting")
    public String detailMeeting(@RequestParam long meetingId, Model model){
        //나중에 예외처리 할 것이 뭐냐면? param값이 없는 값이 없다고 표시
        MeetingDetailDto meetingDetailDto = meetingService.detailMeeting(meetingId);
        System.out.println(meetingDetailDto.getCategory().getDisplayName());
        System.out.println(meetingDetailDto.toString());
        model.addAttribute(meetingDetailDto);

        return  "meeting/detailMeeting";
    }


    @GetMapping("/insertMeeting")
    public String insertMeeting(Model model) {
        model.addAttribute("meetingRegisterDto", new MeetingRegisterDto());
        model.addAttribute("categories", Category.values()); //Enum 카테고리 데이터 넘기기
        return "meeting/insertMeeting";
    }

    @PostMapping("/insertMeeting")
    public String insertMeetingData(@ModelAttribute MeetingRegisterDto meetingRegisterDto) {
        System.out.println(meetingRegisterDto.toString());

        // category 값이 제대로 설정되었는지 확인
        if (meetingRegisterDto.getCategory() == null) {
            log.error("Category is null");
            return "redirect:/meeting/insertMeeting";
        }

        MultipartFile image = meetingRegisterDto.getImage();
        String contentType = image.getContentType();
        // 파일에 대한 유효성 검증.
        if (contentType == null || !contentType.startsWith("image/")) {
            log.error("이미지 파일이 아님" + contentType);
            return "redirect:uploadStatus";
        }
        /**
         *
         */
        String fileUploadUrl = fileUploadService.saveFile(image, imageRootDir);
        meetingService.save(meetingRegisterDto, fileUploadUrl);
        return "redirect:/meeting/list";


    }
}