package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.repository.CategoryRepository;
import com.projektpo.wiorektrepka.budget.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("moneyService")
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
            if (m.getOwner().getUName().equals(userService.getCurrentUserNick()))
                return m;
        }
        return null;
    }

    @Override
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
            eventRepository.save(m);
        }
    }

    @Override
    public void deleteEvent(Integer id) {
        Optional<Event> o = eventRepository.findById(id);
        if (checkUserEvent(o, id)) {
            Event m = o.get();
            eventRepository.delete(m);
        }
    }

    public boolean checkUserEvent(Optional<Event> o, Integer id) {
        return id != null && o.isPresent();
    }
}
