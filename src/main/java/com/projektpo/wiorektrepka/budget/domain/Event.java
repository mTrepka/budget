package com.projektpo.wiorektrepka.budget.domain;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Event {
    @GeneratedValue
    @Id
    private int moneyId;
    private String mName;
    private String type;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    private LocalDate eventDate;
    @CreationTimestamp
    private LocalDate creationDate;
    private int value;
    @ManyToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    private User owner;
}
