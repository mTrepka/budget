package com.projektpo.wiorektrepka.budget;

import com.projektpo.wiorektrepka.budget.configuration.Rest;
import com.projektpo.wiorektrepka.budget.service.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BudgetApplicationTests {
	@Autowired
	private Rest rest;
	@Autowired
	private EventService moneyService;

	@Test
	public void contextLoads() {
	}

}
