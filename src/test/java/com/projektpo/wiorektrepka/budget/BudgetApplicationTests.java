package com.projektpo.wiorektrepka.budget;

import com.projektpo.wiorektrepka.budget.configuration.Rest;
import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.repository.EventRepository;
import com.projektpo.wiorektrepka.budget.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BudgetApplicationTests {
	@Autowired
	private Rest rest;
	@Autowired
    private EventRepository eventRepository;
	@Autowired
    private UserService userService;

	@Test
	public void contextLoads() {
    }
    @Test
    @Transactional
    public void moneyRestTest(){
        assertTrue(eventRepository.findAll().isEmpty());
        //rest.addNewEvent("one","two",1);
        Event e = eventRepository.getOne(1);
        assertEquals(e.getEvName(),"one");
        assertEquals(e.getType(),"two");
        assertEquals(e.getCategory().getCategoryId(),1);

        rest.editEvent(1,"two","one",2);
        e = eventRepository.getOne(1);
        assertEquals(e.getEvName(),"two");
        assertEquals(e.getType(),"one");
        assertEquals(e.getCategory().getCategoryId(),2);

        Event b = rest.getUserEventById(1);
        assertEquals(b.getEvName(),"two");
        assertEquals(b.getType(),"one");
        assertEquals(b.getCategory().getCategoryId(),2);
//        assertTrue(rest.getUserEvent("kategoria1").isEmpty());
//        assertFalse(rest.getUserEvent("kategoria2").isEmpty());
//        assertTrue(rest.getUserEvent("kategoria1").isEmpty());
//        assertFalse(rest.getUserEvent(null).isEmpty());
        rest.deleteEvent(1);
        assertNull(rest.getUserEventById(1));
    }

}
