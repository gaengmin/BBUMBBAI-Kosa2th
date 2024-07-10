package org.kosa.project.controller;

import lombok.extern.log4j.Log4j2;
import org.kosa.project.config.annotation.MeetingFileServiceQualifier;
import org.kosa.project.security.CustomUserDetails;
import org.kosa.project.service.Enum.Category;
import org.kosa.project.service.Enum.UserMeetingType;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.MeetingDetailDto;
import org.kosa.project.service.dto.UserMeetingCheckDto;
import org.kosa.project.service.dto.UserMeetingDto;
import org.kosa.project.service.exception.meeting.MeetingUserNotLoginException;
import org.kosa.project.service.fileupload.FileUploadService;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1") int page, Model model) {
        int pageSize = 10;

        return getMeetingList(page, model, meetingService);
    }

    static String getMeetingList(int page, Model model, MeetingService meetingService) {
        List<MeetingDetailDto> list = meetingService.meetingList(null, PAGE_SIZE);
        int totalMeetings = list.size();
        int totalPages = (int) Math.ceil((double) totalMeetings / PAGE_SIZE);

        model.addAttribute("list", list);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        return "meeting/list";
    }


    @GetMapping("/detailMeeting")
    public String detailMeeting(@RequestParam Long meetingId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        //나중에 예외처리 할 것이 뭐냐면? param값이 없는 값이 없다고 표시
        //        model.addAttribute("userTypes", userTypes);*/
        MeetingDetailDto meetingDetailDto = meetingService.meetingDetails(meetingId);


        // 현재 로그인 한 유저의 현재 미팅에 대한 참여 정보를 확인하고 싶다.
        UserMeetingType userMeetingType = getCurrentLoginUserMeetingType(userDetails, meetingDetailDto.getUserMeetingDto());
        System.out.println(userMeetingType);
        model.addAttribute("meetingDetailDto", meetingDetailDto);
        model.addAttribute("userTypes", userMeetingType);
        return "meeting/detailMeeting";
    }

    private UserMeetingType getCurrentLoginUserMeetingType(CustomUserDetails userDetails, List<UserMeetingDto> userMeetings) {

        if (userDetails == null) {
            return UserMeetingType.NOT_LOGIN;
        }

        for (int i = 0; i < userMeetings.size(); i++) {
            long loginUserId = Long.parseLong(userDetails.getUserId());
            UserMeetingDto userMeetingDto = userMeetings.get(i);
            if (userMeetingDto.getUserId() == loginUserId) {
                return userMeetingDto.getUserType();
            }
        }

        return UserMeetingType.NOT_FOLLOWER;
    }


    @GetMapping("/insertMeeting")
    public String insertMeeting(Model model) {
        model.addAttribute("meetingRegisterRequest", new MeetingRegisterRequest(1L, Category.DESSERT, null, null, 0, null, null, UserMeetingType.LEADER));
        model.addAttribute("categories", Category.values()); //Enum 카테고리 데이터 넘기기
        return "meeting/insertMeeting";
    }

    @PostMapping("/insertMeeting")
    public String insertMeetingData(@ModelAttribute MeetingRegisterRequest request, @AuthenticationPrincipal CustomUserDetails user) {
        long userId = Long.parseLong(user.getUserId());
        System.out.println(userId);
        String response = request.validate();
        String fileUploadUrl = fileUploadService.saveFile(request.image());
        meetingService.save(convertToMeetingRegisterDto(request, fileUploadUrl, userId));
        return response;
    }

    @PostMapping("/detailMeeting")
    public String userTypeMappingAction(
            @RequestParam("meetingId") long meetingId,
            @RequestParam("action") UserMeetingType userType,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            RedirectAttributes redirectAttributes) {

        // 비로그인이면
        if (userDetails == null) {
            throw new MeetingUserNotLoginException("Not Logined");
        }

        UserMeetingCheckDto userMeetingCheckDto = new UserMeetingCheckDto();
        userMeetingCheckDto.setMeetingId(meetingId);
        userMeetingCheckDto.setUserId(Long.parseLong(userDetails.getUserId()));
        userMeetingCheckDto.setUserType(userType);

        System.out.println(userMeetingCheckDto.toString());
        userType.handleAction(meetingService, userMeetingCheckDto);
        redirectAttributes.addAttribute("meetingId", meetingId);
        return "redirect:/meeting/detailMeeting";  // GET 요청으로 리다이렉트
    }

}

