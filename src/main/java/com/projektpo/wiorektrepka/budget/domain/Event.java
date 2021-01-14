package com.projektpo.wiorektrepka.budget.domain;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Entity
@Data
public class Event {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
	@NotEmpty
	private String evName;
	@NotEmpty
	private String type;
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
	@NotNull
	private Date eventDate;
    @CreationTimestamp
    private Date creationDate;
	@NotNull
	private int value;
	@NotNull
    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    private User owner;
}
