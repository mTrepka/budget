package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.User;

public interface UserService {
    User getCurrentUser();

    String getCurrentUserNick();

    User findUserByNick(String username);

    User getCurrentUserFormatted();

    void editName( String name);

    void editPassword(String password);

    void editSurname(String surname);
}
