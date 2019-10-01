package com.projektpo.wiorektrepka.budget.util;

import com.projektpo.wiorektrepka.budget.domain.Category;
import com.projektpo.wiorektrepka.budget.domain.Event;
import com.projektpo.wiorektrepka.budget.domain.User;

import java.sql.Date;

public class EventBuilder {
	private Event e;

	EventBuilder() {
		e = new Event();
	}

	public static EventBuilder builder() {
		return new EventBuilder();
	}

	public EventBuilder setMoneyId(int moneyId) {
		e.setMoneyId(moneyId);
		return this;
	}

	public EventBuilder setEvName(String evName) {
		e.setEvName(evName);
		return this;
	}

	public EventBuilder setType(String type) {
		e.setType(type);
		return this;
	}

	public EventBuilder setCategory(Category category) {
		e.setCategory(category);
		return this;
	}

	public EventBuilder setEventDate(Date eventDate) {
		e.setEventDate(eventDate);
		return this;
	}

	public EventBuilder setCreationDate(Date creationDate) {
		e.setCreationDate(creationDate);
		return this;
	}

	public EventBuilder setValue(Integer value) {
		e.setValue(value);
		return this;
	}

	public EventBuilder setOwner(User owner) {
		e.setOwner(owner);
		return this;
	}

	public Event getEvent() {
		return e;
	}


}
