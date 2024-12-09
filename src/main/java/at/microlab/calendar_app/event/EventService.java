package at.microlab.calendar_app.event;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> findEvent(Long id) {
        return eventRepository.findById(id);
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    public Optional<Event> getEvent(Long id) {
        return eventRepository.findById(id);
    }
}