package com.projektpo.wiorektrepka.budget;

import com.projektpo.wiorektrepka.budget.configuration.Rest;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestAdminTests {
    @Autowired
    private Rest rest;
}
