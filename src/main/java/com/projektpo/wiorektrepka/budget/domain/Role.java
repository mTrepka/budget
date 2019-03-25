package com.projektpo.wiorektrepka.budget.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Role {
    @Id
    @GeneratedValue
    @Column(name="role_id")
    private int id;
    @Column(name="role")
    private String role;
}
