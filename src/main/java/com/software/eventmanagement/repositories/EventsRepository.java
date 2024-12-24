package com.software.eventmanagement.repositories;

import com.software.eventmanagement.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventsRepository extends JpaRepository<Event, Long> {
}
