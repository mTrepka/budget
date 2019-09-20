package com.projektpo.wiorektrepka.budget.domain;


import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private int userId;
    @NotEmpty
    private String uName;
    @NotEmpty
    private String surname;
    @UniqueElements
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    @UniqueElements
    private String email;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    @OneToMany(mappedBy = "owner")
    List<Event> eventList;
}
