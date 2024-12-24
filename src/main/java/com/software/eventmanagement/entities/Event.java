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
    private String services; // seperate multiple services by comma
    private String phoneNumber;
    private String description;
    private String tutorName; // faculty member
    private Type type;

}
