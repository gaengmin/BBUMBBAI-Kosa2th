package org.kosa.project.repository;

import lombok.RequiredArgsConstructor;
import org.kosa.project.controller.Page;
import org.kosa.project.controller.Pageable;
import org.kosa.project.repository.mapper.MeetingMapper;

import org.kosa.project.service.dto.meeting.MeetingDetailDto;
import org.kosa.project.service.dto.meeting.MeetingRegisterDto;
import org.kosa.project.service.dto.meeting.MeetingSummaryDto;
import org.kosa.project.service.dto.search.SearchConditionDto;
import org.kosa.project.service.dto.user.UserMeetingCheckDto;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MeetingRepository {
    private final MeetingMapper meetingMapper;

    public void save(MeetingRegisterDto meetingDto) {
        meetingMapper.save(meetingDto);
    }

    public long selectLastInsertId(long userId) {
        return meetingMapper.selectLastInsertId(userId);
    }

    public Page<MeetingSummaryDto> meetingList(SearchConditionDto searchConditionDto, Pageable pageable) {
        return meetingMapper.meetingList(searchConditionDto, pageable);
    }
    /*모임참여관련 */

    public void userMeetingSave(UserMeetingCheckDto userMeetingDto) {
        meetingMapper.userMeetingSave(userMeetingDto);
    }

    public String getUserMeetingCheck(long userId, long meetingId) {

        return meetingMapper.getUserMeetingCheck(userId, meetingId);
    }

    public MeetingDetailDto meetingDetails(long meetingId) {
        return meetingMapper.meetingDetails(meetingId);
    }

    /*모임참석 눌렀을시 현재원 수 업데이트*/
    public void meetingUpdateCountAndStatus(long meetingId) {

        meetingMapper.meetingUpdateCountAndStatus(meetingId);
    }

    /*관리자가 Wait를 허용했을 떄*/
    public void userMeetingUpdate(UserMeetingCheckDto userMeetingCheckDto){

        meetingMapper.userMeetingUpdate(userMeetingCheckDto);
    }

    /*모임나가기*/
    public void exitMeeting(UserMeetingCheckDto userMeetingDto) {
        meetingMapper.exitMeeting(userMeetingDto);
    }
}
