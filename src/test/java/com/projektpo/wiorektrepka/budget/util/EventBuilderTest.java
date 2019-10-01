package com.projektpo.wiorektrepka.budget.util;


import com.projektpo.wiorektrepka.budget.domain.Category;
import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.domain.User;
import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventBuilderTest {

	@Test
	public void eventBuilderTest() {
		assertNotNull(EventBuilder.builder());
		assertNotNull(EventBuilder.builder().getEvent());
		Integer value = new Random().nextInt();
		int moneyId = new Random().nextInt();
		String evName = "evName";
		String type = "type";
		Category category = new Category();
		String catName = "category";
		category.setName(catName);
		Date creationDate = Date.valueOf(LocalDate.now().minusDays(5));
		Date eventDate = Date.valueOf(LocalDate.now().minusDays(10));
		User owner = new User();
		int userId = new Random().nextInt();
		owner.setUserId(userId);
		Event e = EventBuilder.builder()
				.setValue(value).setEvName(evName).setCategory(category).setType(type)
				.setCreationDate(creationDate).setEventDate(eventDate)
				.setOwner(owner).setMoneyId(moneyId).getEvent();
		assertNotNull(e);
		assertEquals(e.getValue(), value);
		assertEquals(e.getEvName(), evName);
		assertEquals(e.getCategory().getName(), catName);
		assertEquals(e.getType(), type);
		assertEquals(e.getCreationDate(), creationDate);
		assertEquals(e.getEventDate(), eventDate);
		assertEquals(e.getOwner().getUserId(), userId);
		assertEquals(e.getMoneyId(), moneyId);
	}
}
