package at.microlab.calendar_app.web;

import at.microlab.calendar_app.event.Event;
import at.microlab.calendar_app.event.EventService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/")
    public String eventList(Model model) {
        List<Event> events = eventService.getEvents();
        model.addAttribute("events", events);
        return "index";
    }
}
