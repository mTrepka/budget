package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.FormUser;
import com.projektpo.wiorektrepka.budget.domain.User;

public interface UserService {
    User getCurrentUser();

    String getCurrentUserNick();

	User findUserByUsername(String name);

    User getCurrentUserFormatted();

	void createUser(User u);

	boolean updateCurrentUser(FormUser user);

	boolean isUserWithThisUsername(String username);

	void registerUser(FormUser user);

	boolean isUserWithThisEmail(String email);
}
