package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.repository.CategoryRepository;
import com.projektpo.wiorektrepka.budget.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
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
        if (o.isPresent()) {
            try {
                Event m = o.get();
                String uName = userService.getCurrentUser().getUName();
                String eventUName = m.getOwner().getUName();
                System.out.println(eventUName);
                if (uName.equals(eventUName))
                    return m;
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public void addNewEvent(Event event) {
        event.setOwner(userService.getCurrentUser());
        eventRepository.saveAndFlush(event);
    }

    @Override
    public List<Event> getEventsBetweenDateCurrentUser(String startDate, String endDate) {
        return eventRepository.findAllByEventDateBetweenAndOwner(Date.valueOf(startDate), Date.valueOf(endDate), userService.getCurrentUser());
    }

    @Override
    public void deleteEvent(Integer id) {
        Optional<Event> o = eventRepository.findById(id);
        if (o.isPresent() && userService.getCurrentUser().getUserId() == (o.get().getOwner().getUserId())) {
            eventRepository.deleteById(id);
        }
    }

    @Override
    public void updateEvent(Event event) {
        Event e = eventRepository.findById(event.getMoneyId()).get();
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
        return eventRepository.countEventsByEventDateBetweenAndOwner(Date.valueOf(startDate), Date.valueOf(endDate), userService.getCurrentUser());
    }


    public boolean checkUserEvent(Integer id) {
        return id != null && userService.getCurrentUser().getUserId() == id;
    }
}
