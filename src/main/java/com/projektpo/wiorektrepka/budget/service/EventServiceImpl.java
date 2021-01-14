package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.repository.CategoryRepository;
import com.projektpo.wiorektrepka.budget.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service("eventService")
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final UserService userService;
    private final CategoryRepository categoryRepository;

    @Override
    public Event getUserEventById(Integer id) {
        Optional<Event> o = eventRepository.findById(id);
        if (checkUserEvent(o, id)) {
            Event m = o.get();
            if (m.getOwner().getUName().equals(userService.getCurrentUser().getUName()))
                return m;
        }
        return null;
    }

    @Override
    public void addNewEvent(com.projektpo.wiorektrepka.budget.dto.Event event) {
        Event m = generateEventFromBody(event);
        m.setOwner(userService.getCurrentUser());
        eventRepository.saveAndFlush(m);
    }

    @Override
    public List<Event> getEventsBetweenDateCurrentUser(String startDate, String endDate) {
        return eventRepository.findAllByEventDateIsBetweenAndOwner(Date.valueOf(startDate),Date.valueOf(LocalDate.parse(endDate).plusDays(1)),userService.getCurrentUser());
    }

    @Override
    public List<Event> getEventsEndDateCurrentUser(String endDate) {
        return eventRepository.findAllByEventDateIsLessThanEqualAndOwner(Date.valueOf(LocalDate.parse(endDate).plusDays(1)),userService.getCurrentUser());

    }

    @Override
    public List<Event> getEventsStartDateCurrentUser(String startDate) {
        return eventRepository.findAllByEventDateIsGreaterThanAndOwner(Date.valueOf(startDate),userService.getCurrentUser());
    }

    private Event generateEventFromBody(com.projektpo.wiorektrepka.budget.dto.Event event) {
        Event m = new Event();
        m.setEvName(event.getName());
        m.setType(event.getType());
        m.setValue(event.getValue());
        m.setCategory(categoryRepository.findByName(event.getCategory()));
        m.setEventDate(event.getEventDate());
        return m;
    }

    @Override
    public void deleteEvent(Integer id) {
        Optional<Event> o = eventRepository.findById(id);
        if (checkUserEvent(o, id)) {
            eventRepository.deleteById(id);
        }
    }

    @Override
    public void updateEvent(Event event) {
        Event e = eventRepository.findById(event.getId()).get();
        if (canEventEdit(e)) {
            e.setValue(event.getValue());
            e.setCategory(categoryRepository.getOne(event.getCategory().getCategoryId()));
            e.setEvName(event.getEvName());
            e.setEventDate(event.getEventDate());
            e.setType(event.getType());
            eventRepository.save(e);
        }

    }

    private boolean canEventEdit(Event event) {
        return event.getOwner().getUsername().equals(userService.getCurrentUserNick());
    }

    @Override
    public Integer countEventsBetweenDateCurrentUser(String startDate, String endDate) {
        return eventRepository.countEventsByEventDateBetweenAndOwner(Date.valueOf(startDate),Date.valueOf(LocalDate.parse(endDate).plusDays(1)),userService.getCurrentUser());
    }


    public boolean checkUserEvent(Optional<Event> o, Integer id) {
        return id != null && o.isPresent();
    }
}
