package com.projektpo.wiorektrepka.budget;

import com.projektpo.wiorektrepka.budget.domain.FormUser;
import com.projektpo.wiorektrepka.budget.domain.Role;
import com.projektpo.wiorektrepka.budget.domain.User;
import com.projektpo.wiorektrepka.budget.repository.UserRepository;
import com.projektpo.wiorektrepka.budget.service.RoleService;
import com.projektpo.wiorektrepka.budget.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BudgetApplicationTests {
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	@Test
	public void roleServiceTest() {
		Role result = roleService.getUserRole();
		assertEquals(result.getRole(), "user");
	}

	@Test
	@Transactional
	public void userServiceTest() {
		findUserByUsername();
		createUserTest();
		isUserWithThisEmail();
		isUserWithThisUsername();
	}

	@Test
	@Transactional
	@WithMockUser(username = "user", authorities = {"ADMIN", "USER"})
	public void userServiceTestWithMockUser() {
		initUserServiceTestWithMockUser();
		getCurrentUser();
		getCurrentUserFormatted();
		getCurrentUserNick();
		updateCurrentUser();
	}


	public void createUserTest() {
		User u = new User();
		u.setPassword("pass");
		u.setUsername("username");
		u.setSurname("surname");
		u.setEmail("mail@m.com");
		u.setUName("uname");
		userService.createUser(u);
		User byEmail = userRepository.findUserByEmail("mail@m.com");
		User byUsername = userRepository.findUserByUsername("username");
		assertEquals(byEmail.getUserId(), byUsername.getUserId());
		assertEquals(byEmail.getUsername(), "username");
		assertNotEquals(byEmail.getPassword(), "pass");
		assertEquals(byEmail.getUName(), "uname");
		assertEquals(byEmail.getSurname(), "surname");
		assertEquals(byEmail.getEmail(), "mail@m.com");
	}

	public void findUserByUsername() {
		User u1 = new User();
		u1.setUsername("username2");
		u1.setPassword("pass");
		u1.setSurname("surname");
		u1.setEmail("mail1@m.com");
		u1.setUName("uname");
		User u2 = new User();
		u2.setUsername("username3");
		u2.setPassword("pass");
		u2.setSurname("surname");
		u2.setEmail("mail2@m.com");
		u2.setUName("uname");
		userRepository.saveAll(Arrays.asList(u1, u2));
		User f = userService.findUserByUsername("username2");
		assertEquals(f.getUsername(), "username2");
	}

	public void isUserWithThisEmail() {
		assertFalse(userService.isUserWithThisEmail("mail11@m.com"));
		User u1 = new User();
		u1.setUsername("username4");
		u1.setPassword("pass");
		u1.setSurname("surname");
		u1.setEmail("mail11@m.com");
		u1.setUName("uname");
		userRepository.saveAndFlush(u1);
		assertTrue(userService.isUserWithThisEmail("mail11@m.com"));
	}

	public void isUserWithThisUsername() {
		assertFalse(userService.isUserWithThisUsername("username5"));
		User u1 = new User();
		u1.setUsername("username5");
		u1.setPassword("pass");
		u1.setSurname("surname");
		u1.setEmail("mail12@m.com");
		u1.setUName("uname");
		userRepository.saveAndFlush(u1);
		assertTrue(userService.isUserWithThisUsername("username5"));
	}

	public void initUserServiceTestWithMockUser() {
		User u = new User();
		u.setPassword("pass");
		u.setUsername("user");
		u.setSurname("surname");
		u.setEmail("email@mock.com");
		u.setUName("uname");
		userRepository.save(u);
	}

	public void getCurrentUser() {
		User u = userService.getCurrentUser();
		assertEquals(u.getUsername(), "user");
		assertEquals(u.getEmail(), "email@mock.com");
	}

	public void getCurrentUserNick() {
		assertEquals(userService.getCurrentUserNick(), "user");
	}

	public void getCurrentUserFormatted() {
		User u = userService.getCurrentUserFormatted();
		assertNull(u.getPassword());
		assertNull(u.getRoles());
		assertNull(u.getEventList());
	}

	public void updateCurrentUser() {
		FormUser form = new FormUser();
		form.setUName("newName");
		form.setPassword("pass");
		form.setUsername("newUsername");
		form.setPass1("pass1");
		form.setPass2("pass2");
		form.setEmail("newEmail@mail.com");
	}


}