package at.microlab.calendar_app.event;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReminderServiceTest {
    EventService eventService = Mockito.mock(EventService.class);
    ReminderService reminderService = new ReminderService(eventService);

    @Test
    void calculateReminderDateReturnsReminderDate() {
        Event event = Mockito.mock(Event.class);
        Mockito.when(event.getTimeOfReminder()).thenReturn(TimeOfReminder.ONE_DAY);
        Mockito.when(event.getDate()).thenReturn(LocalDate.now().plusDays(1));
        LocalDate reminderDate = reminderService.calculateReminderDate(event);
        assertTrue(reminderDate.isEqual(LocalDate.now()));
    }
}