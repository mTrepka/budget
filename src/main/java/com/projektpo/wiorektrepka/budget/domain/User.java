package com.projektpo.wiorektrepka.budget.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class User {
    @GeneratedValue
    @Id
    private int userId;
    private String uName;
    private String surname;
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToMany
    List<Money> moneyList;
}
