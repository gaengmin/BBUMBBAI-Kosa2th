package org.kosa.project.service.Enum;

public enum Category {
    ENTIRE("전체"),
    BOB_FRIEND("밥친구"),
    ALCOHOL_FRIEND("술친구"),
    DESSERT("디저트");

    private final String displayName;

    Category(String displayName) {

        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Category[] value() {
        return Category.values();
    }
}
