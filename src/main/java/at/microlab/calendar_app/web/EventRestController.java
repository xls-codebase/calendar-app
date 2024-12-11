package at.microlab.calendar_app.web;

import at.microlab.calendar_app.event.Event;
import at.microlab.calendar_app.event.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/events")
public class EventRestController {

    private final EventService eventService;

    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public List<Event> eventList() {
        return eventService.getEvents();
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Object> event(@PathVariable Long eventId) {
        Optional<Event> event = eventService.getEvent(eventId);
        return event.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(() -> ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Event: " + eventId + " not found"));
    }

    @PostMapping
    public ResponseEntity<Void> createEvent(@RequestBody Event newEvent) {
        Event event = eventService.save(newEvent);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{resourceId}")
                .buildAndExpand(event.getEventId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{eventId}")
    private ResponseEntity<Void> putCashCard(@PathVariable Long eventId, @RequestBody Event eventUpdate) {
        Optional<Event> optionalEvent = eventService.findEvent(eventId);

        if (optionalEvent.isPresent()) {
            Event updatedEvent = optionalEvent.get();
            updatedEvent.setDate(eventUpdate.getDate());

            updatedEvent.setDescription(eventUpdate.getDescription());
            updatedEvent.setTimeOfReminder(eventUpdate.getTimeOfReminder());
            eventService.save(updatedEvent);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEvent(@PathVariable("eventId") long eventId) {
        eventService.deleteEvent(eventId);
    }
}
