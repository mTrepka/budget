package com.projektpo.wiorektrepka.budget.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int categoryId;
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Event> eventList;
}
