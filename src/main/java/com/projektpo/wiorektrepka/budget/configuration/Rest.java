package com.projektpo.wiorektrepka.budget.configuration;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projektpo.wiorektrepka.budget.domain.*;
import com.projektpo.wiorektrepka.budget.security.util.SecurityConstants;
import com.projektpo.wiorektrepka.budget.service.CategoryService;
import com.projektpo.wiorektrepka.budget.service.EventService;
import com.projektpo.wiorektrepka.budget.service.LogService;
import com.projektpo.wiorektrepka.budget.service.UserService;
import com.projektpo.wiorektrepka.budget.util.form.NewPasswordPojo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.HashMap;
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

    @PostMapping("/event/edit/")
    public void editEvent(@RequestBody Event event) {
        eventService.updateEvent(event);
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
    public List<AuthorizationLog> getAuthLog(){
        return logService.getCurrentUserAuthLog();
    }

    @GetMapping("/user/info-token")
    public Map<String,String> getUsernameAndToken() {
        Map<String,String> map = new HashMap<>();
        String username = userService.getCurrentUserNick();
        String token = JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
        map.put("username",username);
        map.put("token",token);
        return map;
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