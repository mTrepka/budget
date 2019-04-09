package com.projektpo.wiorektrepka.budget.configuration;

import com.projektpo.wiorektrepka.budget.domain.User;
import com.projektpo.wiorektrepka.budget.domain.Role;
import com.projektpo.wiorektrepka.budget.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Transactional()
    public UserDetails loadUserByUsername(String nick)
            throws UsernameNotFoundException {
        User user = userService.findUserByNick(nick);
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                true, true, true, true, getGrantedAuthorities(user));
    }


    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();

        for(Role userProfile : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getRole()));
        }
        return authorities;
    }
}