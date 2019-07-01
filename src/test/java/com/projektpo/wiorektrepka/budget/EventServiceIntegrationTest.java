package com.projektpo.wiorektrepka.budget;


import com.projektpo.wiorektrepka.budget.repository.CategoryRepository;
import com.projektpo.wiorektrepka.budget.repository.EventRepository;
import com.projektpo.wiorektrepka.budget.service.EventService;
import com.projektpo.wiorektrepka.budget.service.EventServiceImpl;
import com.projektpo.wiorektrepka.budget.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
public class EventServiceIntegrationTest {

	@Autowired
	private EventService eventService;
	@MockBean
	private EventRepository eventRepository;

	@Test
	public void test() {

	}

	@TestConfiguration
	static class EventServiceImplTestContextConfiguration {
		@MockBean
		EventRepository eventRepository;
		@MockBean
		UserService userService;
		@MockBean
		CategoryRepository categoryRepository;

		@Bean
		public EventService eventService() {
			return new EventServiceImpl(eventRepository, userService, categoryRepository);
		}
	}
}
