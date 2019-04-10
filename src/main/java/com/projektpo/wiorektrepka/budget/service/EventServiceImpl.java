package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.repository.CategoryRepository;
import com.projektpo.wiorektrepka.budget.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void addNewEvent(Event event) {
        Event m = generateEventFromBody(event);
        m.setOwner(userService.getCurrentUser());
        eventRepository.saveAndFlush(m);
    }

    @Override
    public List<Event> getEventsBetweenDateCurrentUser(String startDate, String endDate) {
        eventRepository.findEventsByOwnerWhereEventDateBetween(userService.getCurrentUser(),startDate,endDate);
        return null;
    }
    private Event generateEventFromBody(Event event) {
        Event m = new Event();
        m.setMName(event.getMName());
        m.setType(event.getType());
        m.setValue(event.getValue());
        m.setCategory(categoryRepository.getOne(event.getCategory().getCategoryId()));
        m.setEventDate(event.getEventDate());
        return m;
    }

    @Override
    public void editEvent(Integer id, String name, String type, Integer categoryId) {
        Optional<Event> o = eventRepository.findById(id);
        if (checkUserEvent(o, id)) {
            Event m = o.get();
            m.setMName(name);
            m.setType(type);
            m.setCategory(categoryRepository.getOne(categoryId));
            eventRepository.saveAndFlush(m);
        }
    }

    @Override
    public void deleteEvent(Integer id) {
        Optional<Event> o = eventRepository.findById(id);
        if (checkUserEvent(o, id)) {
            eventRepository.deleteById(id);
        }
    }

    public boolean checkUserEvent(Optional<Event> o, Integer id) {
        return id != null && o.isPresent();
    }
}
