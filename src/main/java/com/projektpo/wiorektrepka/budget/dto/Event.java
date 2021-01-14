package com.projektpo.wiorektrepka.budget.dto;

import com.projektpo.wiorektrepka.budget.domain.Category;
import com.projektpo.wiorektrepka.budget.domain.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
public class Event {
    private int id;
    private String name;
    private String type;
    private String category;
    private Date eventDate;
    private Date creationDate;
    private int value;
    public static Event from(com.projektpo.wiorektrepka.budget.domain.Event event) {
        Event result = new Event();
        result.id = event.getId();
        result.name = event.getEvName();
        result.type = event.getType();
        result.category = event.getCategory().getName();
        result.eventDate = event.getEventDate();
        result.creationDate = event.getCreationDate();
        result.value = event.getValue();
        return result;
    }

}
