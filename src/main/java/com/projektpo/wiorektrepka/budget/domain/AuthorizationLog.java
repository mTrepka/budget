package com.projektpo.wiorektrepka.budget.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.sql.Timestamp;

@Entity
@Data
public class AuthorizationLog {
	@Id
	@GeneratedValue
	private long id;
	@NotEmpty
	private String user;
	@NotEmpty
	private String ip;
	@NotEmpty
	private boolean success;
	@NotNull
	private Type type;
	@PastOrPresent
	@CreationTimestamp
	private Timestamp date;
}
