package at.microlab.calendar_app.event;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReminderServiceTest {

    ReminderService reminderService = new ReminderService();

    @Test
    void calculateReminderDateReturnsReminderDate() {
        Event event = new Event(LocalDate.now().plusDays(2), "Birthday Party");
        LocalDate reminderDate = reminderService.calculateReminderDate(event, TimeOfReminder.ONE_DAY);
        assertTrue(reminderDate.minusDays(1).isEqual(LocalDate.now()));
    }

    @Test
    void calculateReminderDateInThePastThrowsRuntimeException() {
        Event event = new Event(LocalDate.now().plusDays(1), "Birthday Party");
        Exception exception = assertThrows(RuntimeException.class, () -> reminderService.calculateReminderDate(event, TimeOfReminder.ONE_DAY));
        assertTrue(exception.getMessage().contains("Date of Reminder must be in the Future."));
    }
}