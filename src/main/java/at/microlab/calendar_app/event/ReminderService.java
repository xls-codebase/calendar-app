package at.microlab.calendar_app.event;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReminderService {

    private final EventService eventService;

    public ReminderService(EventService eventService) {
        this.eventService = eventService;
    }

    public List<Event> upcomingEventsWithReminder() {
        LocalDate today = LocalDate.now();
        return eventService.getUpcomingEventsWithReminder(today);
    }

    public LocalDate calculateReminderDate(Event event) {
        LocalDate reminderDate;
        switch (event.getTimeOfReminder()) {
            case ONE_DAY -> reminderDate = event.getDate().minusDays(1);
            case TWO_DAYS -> reminderDate = event.getDate().minusDays(2);
            case FOUR_DAYS -> reminderDate = event.getDate().minusDays(4);
            case ONE_WEEK -> reminderDate = event.getDate().minusWeeks(1);
            case TWO_WEEKS -> reminderDate = event.getDate().minusWeeks(2);
            default -> throw new IllegalStateException("Unexpected value: " + event.getTimeOfReminder());
        }
        return reminderDate;
    }
}
