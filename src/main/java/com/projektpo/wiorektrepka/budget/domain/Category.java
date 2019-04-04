package com.projektpo.wiorektrepka.budget.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Category {
    @GeneratedValue
    @Id
    private int categoryId;
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Event> eventList;
}
