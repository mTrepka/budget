package com.projektpo.wiorektrepka.budget.configuration;


import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.service.EventService;
import com.projektpo.wiorektrepka.budget.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class Rest {
    private final UserService userService;
    private final EventService moneyService;


    @GetMapping("/event/")
    public List<Event> getUserMoney(String category){
        List<Event> events = userService.getCurrentUser().getEventList();
        if(category==null)
        return events;
        return events.stream().filter(e -> e.getCategory().getName().equals(category)).collect(Collectors.toList());
    }

    @GetMapping("/event/{id}")
    public Event getUserMoneyById(@PathVariable("id") Integer id){
        return moneyService.getUserEventById(id);
    }

    @PostMapping("/event/")
    public void addNewMoney(String name,String type,Integer categoryId){
        moneyService.addNewEvent(name,type,categoryId);
    }
    @PostMapping("/event/edit/{id}")
    public void editMoney(@PathVariable("id") Integer id, String name,String type,Integer categoryId){
        moneyService.editEvent(id,name,type,categoryId);
    }
    @PostMapping("/event/delete/{id}")
    public void deleteMoney(@PathVariable("id") Integer id){
        moneyService.deleteEvent(id);
    }
}
