package org.kosa.project.controller;

import lombok.extern.log4j.Log4j2;
import org.kosa.project.config.annotation.MeetingFileServiceQualifier;
import org.kosa.project.security.CustomUserDetails;
import org.kosa.project.service.Enum.Category;
import org.kosa.project.service.Enum.MeetingStatus;
import org.kosa.project.service.Enum.UserMeetingStrategy;
import org.kosa.project.service.MeetingService;
import org.kosa.project.service.dto.meeting.MeetingDetailDto;
import org.kosa.project.service.dto.search.Page;
import org.kosa.project.service.dto.search.SearchConditionDto;
import org.kosa.project.service.dto.meeting.MeetingSummaryDto;
import org.kosa.project.service.dto.user.UserMeetingDto;
import org.kosa.project.service.fileupload.FileUploadService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.kosa.project.controller.DTOMapper.convertToMeetingRegisterDto;

@Log4j2
@Controller
@RequestMapping("/meeting")
public class MeetingController {

    private final MeetingService meetingService;
    private final FileUploadService fileUploadService;

    private static final Integer PAGE_PER_SIZE = 6;
    public MeetingController(MeetingService meetingService, @MeetingFileServiceQualifier FileUploadService fileUploadService) {
        this.meetingService = meetingService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping("/list")
    public String list(@ModelAttribute SearchConditionDto condition,
                       @RequestParam(defaultValue = "1") Integer page,
                       Model model) {
        return getMeetingList(condition, page, model);
    }

    private String getMeetingList(SearchConditionDto condition, Integer page, Model model) {

        Page<MeetingSummaryDto> detailList = meetingService.meetingList(condition, page, PAGE_PER_SIZE);
        model.addAttribute("detailList", detailList);
        model.addAttribute("condition", condition);
        model.addAttribute("statuses", MeetingStatus.values());
        return "meeting/list";
    }


    // /meeting/comment
    // /meeting/{meetingId}/comments post
    // -> /meeting/{meetingId}/detail
    // -> /meeting/detailsMeeting?meetingId=?/comment
    @GetMapping("/detailMeeting")
    public String detailMeeting(@RequestParam Long meetingId, Model model, @AuthenticationPrincipal CustomUserDetails userDetails) {
        //나중에 예외처리 할 것이 뭐냐면? param값이 없는 값이 없다고 표시
        //        model.addAttribute("userTypes", userTypes);*/
        MeetingDetailDto meetingDetailDto = meetingService.meetingDetails(meetingId);
        SearchConditionDto condition = new SearchConditionDto();
        // 현재 로그인 한 유저의 현재 미팅에 대한 참여 정보를 확인하고 싶다.

        if (userDetails != null) {
            model.addAttribute("userIdentify", userDetails.getUserId());
        }

        UserMeetingStrategy userMeetingStrategy = getCurrentLoginUserMeetingType(userDetails, meetingDetailDto.getUserMeetingDto());
        model.addAttribute("meetingDetailDto", meetingDetailDto);
        model.addAttribute("userType", userMeetingStrategy);
        model.addAttribute("condition", condition);

        return "meeting/detailMeeting";
    }

    private UserMeetingStrategy getCurrentLoginUserMeetingType(CustomUserDetails userDetails, List<UserMeetingDto> userMeetings) {
        if (userDetails == null) {
            return UserMeetingStrategy.NOT_LOGIN;
        }
        for (UserMeetingDto userMeeting : userMeetings) {
            long loginUserId = userDetails.getUserId();
            if (userMeeting.getUserId() == loginUserId) {
                return userMeeting.getUserType();
            }
        }
        return UserMeetingStrategy.NOT_FOLLOWER;
    }


    @GetMapping("/insertMeeting")
    public String insertMeeting(Model model) {
        model.addAttribute("meetingRegisterRequest", new MeetingRegisterRequest(1L, Category.BOB_FRIEND, null, null, 0, null, null, null, null));
        model.addAttribute("categories", Category.values()); //Enum 카테고리 데이터 넘기기
        return "meeting/insertMeeting";
    }

    @PostMapping("/insertMeeting")
    public String insertMeetingData(@ModelAttribute @Validated MeetingRegisterRequest request, BindingResult bindingResult,
                                    Model model,
                                    @AuthenticationPrincipal CustomUserDetails user) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", Category.values());
            model.addAttribute("meetingRegisterRequest", request);
            return "meeting/insertMeeting";
        }
        long userId = user.getUserId();
        String response = request.validate();
        String fileUploadUrl = fileUploadService.saveFile(request.image());
        meetingService.save(convertToMeetingRegisterDto(request, fileUploadUrl, userId));
        return response;
    }
}

