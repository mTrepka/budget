package com.projektpo.wiorektrepka.budget.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@Data
public class AuthorizationLog {
	@Id
	@GeneratedValue
	private long id;
	private String user;
	private String ip;
	private String type;
	@CreationTimestamp
	private Timestamp timestamp;
}
