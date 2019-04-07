package com.projektpo.wiorektrepka.budget.configuration;


import com.projektpo.wiorektrepka.budget.domain.Category;
import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.service.CategoryService;
import com.projektpo.wiorektrepka.budget.service.EventService;
import com.projektpo.wiorektrepka.budget.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class Rest {
    private final UserService userService;
    private final EventService eventService;
    private final CategoryService categoryService;


    @GetMapping("/event/")
    public List<Event> getUserEvent(String category, String startDate, String endDate){
        List<Event> events;
        if(startDate!=null&&endDate!=null&&!startDate.equals(endDate))
            events = eventService.getEventsBetweenDateCurrentUser(startDate,endDate);
        else
            events = userService.getCurrentUser().getEventList();
        events.forEach(e -> {e.getCategory().setEventList(null);e.setOwner(null);});
        if(category==null)
        return events;
        return events.stream().filter(e -> e.getCategory().getName().equals(category)).collect(Collectors.toList());
    }

    @GetMapping("/event/{id}")
    public Event getUserEventById(@PathVariable("id") Integer id){
        return eventService.getUserEventById(id);
    }

    @PostMapping("/event/")
    public void addNewEvent(@RequestBody Event event){
        eventService.addNewEvent(event);
    }

    @PostMapping("/event/edit/{id}")
    public void editEvent(@PathVariable("id") Integer id, String name,String type,Integer categoryId){
        eventService.editEvent(id,name,type,categoryId);
    }
    @PostMapping("/event/delete/{id}")
    public void deleteEvent(@PathVariable("id") Integer id){
        eventService.deleteEvent(id);
    }

    @GetMapping("/category")
    public List<Category> getAllCategory(){
        return categoryService.getAll();
    }
}
