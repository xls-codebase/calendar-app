package at.microlab.calendar_app.event;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReminderService {

    public LocalDate calculateReminderDate(Event event, TimeOfReminder timeOfReminder) {
        LocalDate reminderDate;
        switch (timeOfReminder) {
            case ONE_DAY -> reminderDate = event.getDate().minusDays(1);
            case TWO_DAYS -> reminderDate = event.getDate().minusDays(2);
            case FOUR_DAYS -> reminderDate = event.getDate().minusDays(4);
            case ONE_WEEK -> reminderDate = event.getDate().minusWeeks(1);
            case TWO_WEEKS -> reminderDate = event.getDate().minusWeeks(2);
            default -> throw new IllegalStateException("Unexpected value: " + timeOfReminder);
        }
        
        if (reminderDate.isBefore(LocalDate.now().plusDays(1))) {
            throw new RuntimeException("Date of Reminder must be in the Future.");
        }
        return reminderDate;
    }
}
