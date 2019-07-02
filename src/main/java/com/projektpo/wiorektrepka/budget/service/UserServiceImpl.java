package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.FormUser;
import com.projektpo.wiorektrepka.budget.domain.User;
import com.projektpo.wiorektrepka.budget.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;

    @Override
    public User getCurrentUser() {
        return findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public String getCurrentUserNick() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public User findUserByUsername(String name) {
        return userRepository.findUserByUsername(name);
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
    public void createUser(User u) {
        u.setPassword(bCryptPasswordEncoder.encode(u.getPassword()));
        userRepository.save(u);
    }

    @Override
    public boolean updateCurrentUser(FormUser user) {
        User u = getCurrentUser();
	    if (bCryptPasswordEncoder.matches(user.getPassword(), u.getPassword())) {
            if (!user.getSurname().isEmpty())
                u.setSurname(user.getSurname());
            if (!user.getUName().isEmpty())
                u.setUName(user.getUName());
            if (!user.getUsername().isEmpty())
                u.setUsername(user.getUsername());
            if (!user.getPass1().isEmpty() && user.getPass1().equals(user.getPass2()))
	            u.setPassword(bCryptPasswordEncoder.encode(user.getPass1()));
        }
        userRepository.save(u);
        return false;
    }

    @Override
    public boolean isUserWithThisEmail(String email) {
        return userRepository.findUserByEmail(email) != null;
    }

    @Override
    public boolean isUserWithThisUsername(String username) {
        return userRepository.findUserByUsername(username) != null;
    }

    @Override
    public void registerUser(FormUser user) {
        if (!isUserWithThisEmail(user.getEmail()) && !isUserWithThisUsername(user.getUsername()) && user.getPass1().equals(user.getPass2())) {
            User u = getUserFromFormUser(user);
            u.getRoles().add(roleService.getUserRole());
            userRepository.save(u);
        }
    }

    private User getUserFromFormUser(FormUser user) {
        User u = new User();
        u.setPassword(bCryptPasswordEncoder.encode(user.getPass1()));
        u.setUsername(user.getUsername());
        u.setSurname(user.getSurname());
        u.setUName(user.getUName());
        u.setEmail(user.getEmail());
        u.setRoles(new HashSet<>());
        return u;
    }
}
