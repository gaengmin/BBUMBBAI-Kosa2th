package org.kosa.project.service.Enum;

public enum UserType {
    LEADER("모임장"),
    FOLLOWER("모임원"),
    NOTFOLLOWER("미참여");
    private final String userTypeName;

    UserType(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getUserTypeName() {
        return userTypeName;
    }
}
