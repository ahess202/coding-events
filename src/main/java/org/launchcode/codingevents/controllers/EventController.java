package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.launchcode.codingevents.models.Event;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    // findAll, save, findById

    private static List<Event> events = new ArrayList<>();

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }
        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }
        return "redirect:";
    }

//    @GetMapping("edit/{eventId}")
//    public String displayEditForm(Model model, @PathVariable int eventId) {
//        Event event = eventRepository.findById(eventId);
//        model.addAttribute("title", "Edit Event " + event.getName() + " (id=" + event.getId() + ")");
//        model.addAttribute("event", event);
//        return "events/edit";
//    }
//    @PostMapping("edit")
//    public String processEditForm(int eventId, String name, String description, String venue, int numAttendees, Date eventDate, String contactEmail, boolean mustRegister) {
//        Event event = eventRepository.findById(eventId);
//        event.setName(name);
//        event.setDescription(description);
//        event.setVenue(venue);
//        event.setNumAttendees(numAttendees);
//        event.setEventDate(eventDate);
//        event.setContactEmail(contactEmail);
//        event.setMustRegister(mustRegister);
//        return "redirect:/events";
//    }

}
