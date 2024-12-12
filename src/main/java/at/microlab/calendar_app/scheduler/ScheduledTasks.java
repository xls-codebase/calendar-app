package at.microlab.calendar_app.scheduler;

import at.microlab.calendar_app.email.EmailService;
import at.microlab.calendar_app.event.Event;
import at.microlab.calendar_app.event.ReminderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Component
public class ScheduledTasks {

    private final ReminderService reminderService;
    private final EmailService emailService;

    @Value("${recipient}")
    private String recipient;

    public ScheduledTasks(ReminderService reminderService, EmailService emailService) {
        this.reminderService = reminderService;
        this.emailService = emailService;
    }

    @Scheduled(cron="${cronExpression}")
    @Async
    public void task() {
        List<Event> eventList = reminderService.upcomingEventsWithReminder();
        Queue<Event> eventsForEmailing = new LinkedList<>();
        LocalDate today = LocalDate.now();

        for (Event event : eventList) {
            if (today.isEqual(reminderService.calculateReminderDate(event))) {
                eventsForEmailing.add(event);
            }
        }

        while (!eventsForEmailing.isEmpty()) {
            Event event = eventsForEmailing.poll();
            String eventDescription = event.getDescription().trim();
            String subject = "Erinnerung: " + event.getDate() + " - "
                    + eventDescription.substring(0, Math.min(eventDescription.length(), 30) );
            String textBlock = """
                            Sie haben eine gespeicherte Erinnerung:
                            
                            Datum: %s
                            
                            Beschreibung: %s
                            """;
            String body = textBlock.formatted(event.getDate(), eventDescription);
            emailService.sendMail(recipient, subject, body);
        }
    }


}
