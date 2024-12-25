package com.software.eventmanagement.entities;

import jakarta.persistence.*;

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
    private long id;
    private String OrganizerId;
    private String location;
    private String services; // separate multiple services by comma
    private String phoneNumber;
    private String description;
    private String mentorName; // faculty member
    private Type type;

    public Event(long id, String organizerId, String location, String services, String phoneNumber, String description, String mentorName, Type type) {
        this.id = id;
        OrganizerId = organizerId;
        this.location = location;
        this.services = services;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.mentorName = mentorName;
        this.type = type;
    }

    public Event(long id, Event event) {
        this.id = id;
        this.location = event.getLocation();
        this.services = event.getServices();
        this.phoneNumber = event.getPhoneNumber();
        this.description = event.getDescription();
        this.mentorName = event.getMentorName();
        this.type = event.getType();
    }

    public Event(String organizerId, String location, String services, String phoneNumber, String description, String mentorName, Type type) {
        OrganizerId = organizerId;

    }

    public Event() {
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
}
