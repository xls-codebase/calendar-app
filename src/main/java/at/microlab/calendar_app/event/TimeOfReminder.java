package at.microlab.calendar_app.event;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TimeOfReminder {
    ONE_DAY("1 Tag"),
    TWO_DAYS("2 Tage"),
    FOUR_DAYS("4 Tage"),
    ONE_WEEK("1 Woche"),
    TWO_WEEKS("2 Wochen");

    private final String displayText;

    TimeOfReminder(String displayText) {
        this.displayText = displayText;
    }

    @JsonValue
    public String getDisplayText() {
        return displayText;
    }
}
