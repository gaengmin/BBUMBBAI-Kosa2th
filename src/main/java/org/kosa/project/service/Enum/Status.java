package org.kosa.project.service.Enum;

public enum Status {
    RECRUTING("모집 중"),
    FINISH("마감 완료");

    private final String displayName;


    Status(String displayName) {
        this.displayName = displayName;
    }
}
