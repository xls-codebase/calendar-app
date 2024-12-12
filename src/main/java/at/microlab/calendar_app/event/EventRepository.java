package at.microlab.calendar_app.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    public List<Event> findEventsByTimeOfReminderNotNullAndDateGreaterThan(LocalDate date);
}
