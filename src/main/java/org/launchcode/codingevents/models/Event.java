package org.launchcode.codingevents.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.Objects;

public class Event {

    private int id;
    private static int nextId = 1;

    @NotBlank(message = "Name is required!")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 5 characters!")
    private String name;

    @Size(max = 500, message = "Description too long! Maximum 500 characters.")
    private String description;

    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid email. Try again.")
    private String contactEmail;

    @NotBlank(message = "Venue required")
    @Size(max = 30)
    private String venue;

    @AssertTrue(message = "User must register for event.")
    private boolean mustRegister;

    @NotNull(message = "Number of attendees required!")
    @Min(1)
    @Digits(integer = 100000, fraction = 0, message = "Must enter a number!")
    private int numAttendees;

    @NotNull(message = "Date required!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future
    private Date eventDate;


    public Event(String name, String description, String contactEmail, String venue, boolean mustRegister, int numAttendees, Date eventDate) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.venue = venue;
        this.mustRegister = mustRegister;
        this.numAttendees = numAttendees;
        this.eventDate = eventDate;
    }

    public Event() {
        this.id = this.nextId;
        nextId++;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public boolean isMustRegister() {
        return mustRegister;
    }

    public void setMustRegister(boolean mustRegister) {
        this.mustRegister = mustRegister;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public int getId() {
        return id;
    }

    public int getNumAttendees() {
        return numAttendees;
    }

    public void setNumAttendees(int numAttendees) {
        this.numAttendees = numAttendees;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
