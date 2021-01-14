package com.projektpo.wiorektrepka.budget.configuration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.projektpo.wiorektrepka.budget.domain.*;
import com.projektpo.wiorektrepka.budget.dto.FormUser;
import com.projektpo.wiorektrepka.budget.service.CategoryService;
import com.projektpo.wiorektrepka.budget.service.EventService;
import com.projektpo.wiorektrepka.budget.service.LogService;
import com.projektpo.wiorektrepka.budget.service.UserService;
import com.projektpo.wiorektrepka.budget.util.form.NewPasswordPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.projektpo.wiorektrepka.budget.dto.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class Rest {
    private final UserService userService;
    private final EventService eventService;
    private final CategoryService categoryService;
    private final LogService logService;


    @GetMapping("/event/")
    public List<com.projektpo.wiorektrepka.budget.dto.Event> getUserEvent(String category, String startDate, String endDate) {
        List<Event> events;
        if (startDate != null && endDate != null && !startDate.equals(endDate))
            events = eventService.getEventsBetweenDateCurrentUser(startDate, endDate);
        else if (startDate == null && endDate != null) {
            events = eventService.getEventsEndDateCurrentUser(endDate);
        } else if (startDate != null && endDate == null) {
            events = eventService.getEventsStartDateCurrentUser(startDate);
        } else {
            events = userService.getCurrentUser().getEventList();
        }

        if (category == null) {
            return events.stream().map(com.projektpo.wiorektrepka.budget.dto.Event::from).collect(Collectors.toList());
        }
        return events.stream().filter(e -> e.getCategory().getName().equals(category)).map(com.projektpo.wiorektrepka.budget.dto.Event::from).collect(Collectors.toList());
    }

    @GetMapping("/event/count/")
    public Integer countEventsByDate(@NotNull String startDate,@NotNull String endDate) {
        return eventService.countEventsBetweenDateCurrentUser(startDate, endDate);
    }

    @GetMapping("/event/{id}")
    public Event getUserEventById(@PathVariable("id") Integer id) {
        return eventService.getUserEventById(id);
    }

    @PostMapping("/event/")
    public void addNewEvent(@RequestBody com.projektpo.wiorektrepka.budget.dto.Event event) {
        eventService.addNewEvent(event);
    }

    @PostMapping("/event/edit/")
    public void editEvent(@RequestBody Event event) {
        eventService.updateEvent(event);
    }

    @PostMapping("/event/delete/{id}")
    public void deleteEvent(@PathVariable("id") Integer id) {
        eventService.deleteEvent(id);
    }

    @GetMapping("/category")
    public List<Category> getAllCategory() {
        return categoryService.getAll();
    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable Integer id) {
        return categoryService.getById(id);
    }


    @GetMapping("/user")
    public User getCurrentUser() {
        return User.from(userService.getCurrentUserFormatted());
    }

    @PostMapping("/user/edit/")
    public boolean editUser(@RequestBody FormUser user) {
        return userService.updateCurrentUser(user);
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody FormUser user) {
        userService.registerUser(user);
    }

    @GetMapping("/email/")
    public boolean isUserWithEmailRegister(String email) {
        return userService.isUserWithThisEmail(email);
    }

    @GetMapping("/username/")
    public boolean isUserWithUsernameRegister(String username) {
        return userService.isUserWithThisUsername(username);
    }

    @GetMapping("/user/security/log/")
    public List<AuthorizationLog> getAuthLog() {
        return logService.getCurrentUserAuthLog();
    }

    @PostMapping("/reset-password")
    public boolean restorePassword(@RequestBody @Email String email) {
        return userService.restorePassword(email);
    }

    @PostMapping("/change-forgotten-password")
    public boolean changePassword(@RequestBody Map<String, Map<String, String>> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        NewPasswordPojo newPassword = objectMapper.convertValue(map.get("newPassword"), NewPasswordPojo.class);
        CodeEvent ce = objectMapper.convertValue(map.get("code"), CodeEvent.class);
        if (newPassword.getNewPassword().equals(newPassword.getRepeatPassword()))
            return userService.changePassword(ce, newPassword.getNewPassword());
        return true;
    }


}