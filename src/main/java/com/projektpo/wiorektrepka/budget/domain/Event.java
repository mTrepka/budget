package com.projektpo.wiorektrepka.budget.domain;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
public class Event {
    @GeneratedValue
    @Id
    private int moneyId;
    private String evName;
    private String type;
    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;
    private Date eventDate;
    @CreationTimestamp
    private Date creationDate;
    private int value;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    private User owner;
}
