package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.repository.CategoryRepository;
import com.projektpo.wiorektrepka.budget.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

<<<<<<< HEAD
import java.util.Optional;

@Service("moneyService")
=======
import java.util.List;
import java.util.Optional;

@Service("eventService")
>>>>>>> origin/master
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
<<<<<<< HEAD
            if (m.getOwner().getUName().equals(userService.getCurrentUserNick()))
=======
            if (m.getOwner().getUName().equals(userService.getCurrentUser().getUName()))
>>>>>>> origin/master
                return m;
        }
        return null;
    }

    @Override
<<<<<<< HEAD
    public void addNewEvent(String name, String type, Integer categoryId) {
        Event m = generateEvent(name, type, categoryId);
        m.setOwner(userService.getCurrentUser());
        eventRepository.save(m);
    }

    @Override
    public Event generateEvent(String name, String type, Integer categoryId) {
        Event m = new Event();
        m.setMName(name);
        m.setType(type);
        m.setCategory(categoryRepository.getOne(categoryId));
=======
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
>>>>>>> origin/master
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
<<<<<<< HEAD
            eventRepository.save(m);
=======
            eventRepository.saveAndFlush(m);
>>>>>>> origin/master
        }
    }

    @Override
    public void deleteEvent(Integer id) {
        Optional<Event> o = eventRepository.findById(id);
        if (checkUserEvent(o, id)) {
<<<<<<< HEAD
            Event m = o.get();
            eventRepository.delete(m);
=======
            eventRepository.deleteById(id);
>>>>>>> origin/master
        }
    }

    public boolean checkUserEvent(Optional<Event> o, Integer id) {
        return id != null && o.isPresent();
    }
}
