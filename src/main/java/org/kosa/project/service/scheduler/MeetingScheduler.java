package org.kosa.project.service.scheduler;

import org.kosa.project.service.MeetingService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MeetingScheduler {


    private final MeetingService meetingService;

    public MeetingScheduler(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    //@Scheduled(cron = "0 0 * * * *") //매시간마다 실행
    //@Scheduled(cron = "0 * * * * *") // 매 분마다 실행
    @Scheduled(cron = "0 */5 * * * *") // 매 5분마다 실행
    public void updateMeetingEndTime() {
        System.out.println("실행");
        meetingService.updateMeetingEndTime();
    }
}
