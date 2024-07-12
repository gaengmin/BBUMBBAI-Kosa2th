package org.kosa.project.service.Enum;

public enum MeetingStatus {
    CONTINUE("모집 중"),
    FINISH("마감 완료"),
    TIME_END("일정 종료");
    private final String statusType;

    MeetingStatus(String statusType) {
        this.statusType = statusType;
    }

    public String getStatusType() {
        return statusType;
    }

}
