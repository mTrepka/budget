package com.projektpo.wiorektrepka.budget.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Event {
    @GeneratedValue
    @Id
    private int moneyId;
    private String mName;
    private String type;
    @ManyToOne(targetEntity = Category.class)
    private Category category;
    @ManyToOne(targetEntity = User.class)
    private User owner;
}
