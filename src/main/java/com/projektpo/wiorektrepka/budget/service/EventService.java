package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Event;

import java.util.List;

public interface EventService {
    Event getUserEventById(Integer id);

    void deleteEvent(Integer id);

    List<Event> getEventsBetweenDateCurrentUser(String startDate, String endDate);
    Integer countEventsBetweenDateCurrentUser(String startDate, String endDate);

    void addNewEvent(Event event);

    void updateEvent(Event event);
}
