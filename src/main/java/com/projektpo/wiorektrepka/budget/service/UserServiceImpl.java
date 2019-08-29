package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.AppEvent;
import com.projektpo.wiorektrepka.budget.domain.CodeEvent;
import com.projektpo.wiorektrepka.budget.domain.FormUser;
import com.projektpo.wiorektrepka.budget.domain.User;
import com.projektpo.wiorektrepka.budget.mail.service.MailService;
import com.projektpo.wiorektrepka.budget.repository.UserRepository;
import com.projektpo.wiorektrepka.budget.security.oauth2.user.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleService roleService;
    private final MailService mailService;
    private final CodeEventService codeEventService;

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
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
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
            userRepository.saveAndFlush(u);
            mailService.sendWelcomeEmail(u);
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

    @Override
    public User registerUser(OAuth2UserInfo oAuth2UserInfo) {
        User user = new User();
        user.setUName(oAuth2UserInfo.getName());
        user.setUsername(oAuth2UserInfo.getEmail());
        user.setSurname(" ");
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setEventList(new ArrayList<>());
        user.setPassword(bCryptPasswordEncoder.encode(RandomString.make(10)));
        user.setRoles(new HashSet<>());
        userRepository.saveAndFlush(user);
        mailService.sendWelcomeEmail(user);
        return user;
    }

    @Override
    public boolean changePassword(CodeEvent ce, String newPassword) {
        if (codeEventService.validCode(ce)) {
            User u = userRepository.getOne((int) ce.getUserId());
            u.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userRepository.save(u);
            return true;
        }
        return false;
    }

    @Override
    public boolean restorePassword(String email) {

        User u = userRepository.findUserByEmail(email);
        if (u != null) {
            CodeEvent ce = CodeEvent.builder()
                    .setUserId(u.getUserId())
                    .setAppEvent(AppEvent.forgottenPassword)
                    .get();
            codeEventService.saveCode(ce);
            mailService.sendForgotPasswordByEmail(u, ce.getCode());
        }
        return false;
    }

}
