package at.microlab.calendar_app.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Event {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    private LocalDate date;

    private String description;

    @Enumerated(EnumType.STRING)
    private TimeOfReminder timeOfReminder;

    public Event(LocalDate date, String description, TimeOfReminder timeOfReminder) {
        this.date = date;
        this.description = description;
        this.timeOfReminder = timeOfReminder;
    }

    public Event(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    public Event() {
    }

    public Long getEventId() { return eventId; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TimeOfReminder getTimeOfReminder() { return timeOfReminder; }

    public void setTimeOfReminder(TimeOfReminder timeOfReminder) { this.timeOfReminder = timeOfReminder; }
}
