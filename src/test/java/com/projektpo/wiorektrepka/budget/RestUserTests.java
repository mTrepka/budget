package com.projektpo.wiorektrepka.budget;

import com.projektpo.wiorektrepka.budget.configuration.Rest;
import com.projektpo.wiorektrepka.budget.domain.Category;
import com.projektpo.wiorektrepka.budget.domain.User;
import com.projektpo.wiorektrepka.budget.dto.Event;
import com.projektpo.wiorektrepka.budget.dto.FormUser;
import com.projektpo.wiorektrepka.budget.repository.CategoryRepository;
import com.projektpo.wiorektrepka.budget.repository.EventRepository;
import com.projektpo.wiorektrepka.budget.repository.UserRepository;
import com.projektpo.wiorektrepka.budget.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestUserTests {
    @Autowired
    private Rest rest;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @WithMockUser(username = "UserName", authorities = {"USER"})
    @Test
    public void getCategories() {
        List<Category> list = rest.getAllCategory();
        assertEquals(list.toString(), categoryRepository.findAll().toString());
    }

    @WithMockUser(username = "UserName", authorities = {"USER"})
    @Test
    public void getCategoryById() {
        List<Category> list = rest.getAllCategory();
        list.forEach(e -> assertEquals(rest.getCategoryById(e.getCategoryId()).getName(), e.getName()));
    }

    @WithMockUser(username = "UserName", authorities = {"USER"})
    @Test
    public void getCurrentUser() {
        User u = userRepository.findUserByUsername("UserName");
        com.projektpo.wiorektrepka.budget.dto.User result = rest.getCurrentUser();
        assertEquals(u.getUsername(), result.getUsername());
        assertEquals(u.getUName(), result.getName());
        assertEquals(u.getEmail(), result.getEmail());
        assertEquals(u.getSurname(), result.getSurname());
        assertEquals(u.getUserId(), result.getUserId());
    }

    @WithMockUser(username = "UserName", authorities = {"USER"})
    @Test
    @Transactional
    public void getEvents() {
        User u = userRepository.findUserByUsername("UserName");
        List<com.projektpo.wiorektrepka.budget.domain.Event> events = u.getEventList();
        List<Event> result = rest.getUserEvent(null, null, null);
        assertEquals(u.getEventList().size(), result.size());
        for (Event event : result) {
            com.projektpo.wiorektrepka.budget.domain.Event tested = events.stream().filter(e -> e.getId() == event.getId()).findFirst().get();
            assertEquals(event.getCategory(), tested.getCategory().getName());
            assertEquals(event.getId(), tested.getId());
            assertEquals(event.getCreationDate(), tested.getCreationDate());
            assertEquals(event.getName(), tested.getEvName());
            assertEquals(event.getType(), tested.getType());
            assertEquals(event.getValue(), tested.getValue());
            assertEquals(event.getEventDate(), tested.getEventDate());
        }

        result = rest.getUserEvent("first", null, null);
        assertEquals(result.size(), 2);
        result = rest.getUserEvent("first", null, null);
        assertEquals(result.size(), 2);
        result = rest.getUserEvent(null, "2020-01-01", "2020-02-01");
        assertEquals(result.size(), 11);
        result = rest.getUserEvent(null, "2020-01-13", "2020-01-16");
        assertEquals(result.size(), 4);
        result = rest.getUserEvent("fourth", "2020-01-13", "2020-01-16");
        assertEquals(result.size(), 2);
        result = rest.getUserEvent(null, null, "2020-01-15");
        assertEquals(result.size(), 4);
        result = rest.getUserEvent(null, "2020-01-15", null);
        assertEquals(result.size(), 8);
    }

    @Test
    @WithMockUser(username = "UserName", authorities = {"USER"})
    public void countEventsByDate() {
        assertEquals(11, rest.countEventsByDate("2020-01-01", "2020-02-01").intValue());
        assertEquals(4, rest.countEventsByDate("2020-01-13", "2020-01-16").intValue());
    }

    @Test
    @WithMockUser(username = "UserName2", authorities = {"USER"})
    @Transactional
    public void addNewEvent() {
        Event event = new Event();
        event.setName("NowyTest");
        event.setEventDate(Date.valueOf("2020-02-03"));
        event.setType("inc");
        event.setCategory("first");
        rest.addNewEvent(event);
        User u = userService.getCurrentUser();
        com.projektpo.wiorektrepka.budget.domain.Event e = u.getEventList().stream().filter(b -> b.getEvName().equals("NowyTest") && b.getType().equals("inc")).findFirst().get();
        assertEquals("inc", e.getType());
        assertEquals(Date.valueOf("2020-02-03").toString(), e.getEventDate().toString());
        assertEquals("first", e.getCategory().getName());
        assertEquals("NowyTest", e.getEvName());

    }

    @Test
    @WithMockUser(username = "UserName2", authorities = {"USER"})
    @Transactional
    public void deleteEvent() {
        int id = 23;
        com.projektpo.wiorektrepka.budget.domain.Event event = eventRepository.getOne(id);
        assertEquals("UserName2", event.getOwner().getUsername());
        rest.deleteEvent(id);
        Optional test = eventRepository.findById(id);
        assertFalse(test.isPresent());
    }

    @Test
    @WithMockUser(username = "UserName2", authorities = {"USER"})
    @Transactional
    public void editEvent() {
        com.projektpo.wiorektrepka.budget.domain.Event e = eventRepository.getOne(24);
        assertEquals("Nazwa", e.getEvName());
        assertEquals("inc", e.getType());
        com.projektpo.wiorektrepka.budget.domain.Event template = new com.projektpo.wiorektrepka.budget.domain.Event();
        template.setId(24);
        template.setCategory(e.getCategory());
        String newName = "sdafgffasdfg";
        String newType = "rev";
        template.setEvName(newName);
        template.setType(newType);
        rest.editEvent(template);
        assertEquals(newName, e.getEvName());
        assertEquals(newType, e.getType());
        assertEquals("first", e.getCategory().getName());
        template.setCategory(categoryRepository.findByName("fourth"));
        rest.editEvent(template);
        assertEquals("fourth", e.getCategory().getName());
    }

    @Test
    @WithMockUser(username = "UserName3", authorities = {"USER"})
    @Transactional
    public void editUser() {
        FormUser change = new FormUser();
        User u = userService.getCurrentUser();
        String pass = u.getPassword();
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        userRepository.save(u);
        String oldPass = u.getPassword();
        String newPass = "sasa";
        change.setPass1("sasa");
        rest.editUser(change);
        assertEquals(oldPass, u.getPassword());
        change.setPass2("sasa");
        rest.editUser(change);
        assertEquals(oldPass, u.getPassword());
        change.setPassword(pass);
        rest.editUser(change);
        assertNotEquals(oldPass, u.getPassword());
        change.setPass1(null);
        change.setPass2(null);
        change.setPassword(newPass);
        String newUserName = "Stefan";
        assertNotEquals(u.getUName(),newUserName);
        change.setUName(newUserName);
        rest.editUser(change);
        assertEquals(u.getUName(),newUserName);
    }


}
