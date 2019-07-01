package com.projektpo.wiorektrepka.budget.configuration;


import com.projektpo.wiorektrepka.budget.domain.Category;
import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.domain.FormUser;
import com.projektpo.wiorektrepka.budget.domain.User;
import com.projektpo.wiorektrepka.budget.service.CategoryService;
import com.projektpo.wiorektrepka.budget.service.EventService;
import com.projektpo.wiorektrepka.budget.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

        if(category==null)
        {
            events.forEach(e -> {e.getCategory().setEventList(null);e.setOwner(null);});
            return events;
        }
        events.forEach(e -> e.setOwner(null));
        return events.stream().filter(e -> e.getCategory().getName().equals(category)).collect(Collectors.toList());
    }

    @GetMapping("/event/count/")
    public Integer countEventsByDate(String startDate, String endDate){
        return eventService.countEventsBetweenDateCurrentUser(startDate,endDate);
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

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable Integer id){
        return categoryService.getById(id);
    }

    @PostMapping("/category/delete/{id}")
    public void deleteCategory(@PathVariable("id") Integer id){
        categoryService.deleteCategory(id);
    }

    @PostMapping("/category/edit/{id}")
    public void editCategory(@PathVariable("id") Integer id,@RequestBody Category cat){
        categoryService.editCategory(id, cat);
    }

    @PostMapping("/category/add")
    public void addCategory(@RequestBody Category category){
        categoryService.addNewCategory(category);
    }

    @GetMapping("/user")
    public User getCurrentUser(){
        return userService.getCurrentUserFormatted();
    }

    @PostMapping("/user/edit/")
    public boolean editUser(@RequestBody FormUser user) {
	    return userService.updateCurrentUser(user);
    }

}