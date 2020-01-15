package com.ehsun.eventsavy.repos;

import org.springframework.data.repository.CrudRepository;

import com.ehsun.eventsavy.entities.Event;

public interface EventRepository extends CrudRepository<Event, Long> {

}
