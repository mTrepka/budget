package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Event;

public interface EventService {
    Event getUserEventById(Integer id);

    void addNewEvent(String name, String type, Integer categoryId);

    Event generateEvent(String name, String type, Integer categoryId);

    void editEvent(Integer id,String name, String type, Integer categoryId);

    void deleteEvent(Integer id);
}
