package com.software.eventmanagement.event;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

enum Type {
    TRAINING,
    COMPETITION,
    WORKSHOP,
    LECTURE,
    INTIATIVE,
    EXHIBITION
}

@Table(name = "events")
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // automatic, not input by registration
    private String OrganizerId;
    private String location;
    private String services; // separate multiple services by comma
    private String phoneNumber;
    private String description;
    private String mentorName; // supervisor (faculty member)
    private Type type;
    private Date startTime;
    private Date endTime;
    private short seats; // maximum number of seats
    private short participants; // number of current participants, not input by registration
    private short rating; // not input by registration
    @ElementCollection
    private List<String> feedback; // not input by registration

    public Event(long id, String organizerId, String location, String services, String phoneNumber, String description, String mentorName, Type type, Date startTime, Date endTime, short seats, short participants) {
        this.id = id;
        this.OrganizerId = organizerId;
        this.location = location;
        this.services = services;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.mentorName = mentorName;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.seats = seats;
        this.participants = participants;
        this.rating = 0;
        this.feedback = new ArrayList<String>();
    }

    public Event(long id, Event event) {
        this.id = id;
        this.location = event.getLocation();
        this.services = event.getServices();
        this.phoneNumber = event.getPhoneNumber();
        this.description = event.getDescription();
        this.mentorName = event.getMentorName();
        this.type = event.getType();
        this.startTime = event.getStartTime();
        this.seats = event.getSeats();
        this.participants = event.getParticipants();
        this.OrganizerId =event.getOrganizerId();
        this.rating = event.getRating();
        this.feedback= event.getFeedback();
        this.endTime = event.getEndTime();
    }



    public Event() {
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public short getRating() {
        return rating;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public List<String> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<String> feedback) {
        this.feedback = feedback;
    }

    public short getParticipants() {
        return participants;
    }

    public void setParticipants(short participants) {
        this.participants = participants;
    }

    public short getSeats() {
        return seats;
    }

    public void setSeats(short seats) {
        this.seats = seats;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrganizerId() {
        return OrganizerId;
    }

    public void setOrganizerId(String organizerId) {
        OrganizerId = organizerId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date date) {
        this.startTime = date;
    }
}
