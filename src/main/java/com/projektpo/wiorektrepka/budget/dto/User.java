package com.projektpo.wiorektrepka.budget.dto;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String name;
    private String surname;
    private String username;
    private String email;
    public static User from(com.projektpo.wiorektrepka.budget.domain.User user) {
        User result = new User();
        result.name = user.getUName();
        result.userId = user.getUserId();
        result.surname = user.getSurname();
        result.email = user.getEmail();
        result.username = user.getUsername();
        return result;
    }
}
