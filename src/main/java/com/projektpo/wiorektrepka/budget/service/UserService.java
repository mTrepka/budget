package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.CodeEvent;
import com.projektpo.wiorektrepka.budget.dto.FormUser;
import com.projektpo.wiorektrepka.budget.domain.User;

public interface UserService {
    User getCurrentUser();

    String getCurrentUserNick();

	User findUserByUsername(String name);

	User findUserByEmail(String email);

	User getCurrentUserFormatted();

	void createUser(User u);

	boolean updateCurrentUser(FormUser user);

	boolean isUserWithThisUsername(String username);

	void registerUser(FormUser user);

	boolean isUserWithThisEmail(String email);

	boolean restorePassword(String email);

	boolean changePassword(CodeEvent ce, String newPassword);
}
