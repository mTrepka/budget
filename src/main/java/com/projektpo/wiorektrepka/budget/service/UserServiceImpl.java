package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.User;
import com.projektpo.wiorektrepka.budget.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        //return findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return findUserByUsername("user");
    }

    @Override
    public String getCurrentUserNick() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private User findUserByUsername(String name) {
        return userRepository.findUserByUsername(name);
    }

    @Override
    public User findUserByNick(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User getCurrentUserFormatted() {
        User u = getCurrentUser();
        u.setPassword(null);
        u.setEventList(null);
        u.setRoles(null);
        return u;
    }

    @Override
    public void editName(String newName) {
        getCurrentUser().setUName(newName);
    }


    @Override
    public void editPassword(String newPassword) {
        getCurrentUser().setPassword(newPassword);
    }

    @Override
    public void editSurname(String surname) {
        getCurrentUser().setSurname(surname);
    }


}
