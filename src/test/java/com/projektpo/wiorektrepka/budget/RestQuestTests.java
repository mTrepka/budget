package com.projektpo.wiorektrepka.budget;

import com.projektpo.wiorektrepka.budget.configuration.Rest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RestQuestTests {
    @Autowired
    private Rest rest;

    @Test
    public void isUserWithEmailRegister(){
        rest.restorePassword("example5@email.pl");
    }

    @Test
    public void isUserWithUsernameRegister(){

    }

    @Test
    public void restorePassword(){

    }
    @Test
    public void changePassword(){

    }
}
