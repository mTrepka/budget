package com.projektpo.wiorektrepka.budget.mail.template;

import lombok.Data;
import lombok.Generated;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class MailTemplate {
	@Id
	@Generated
	private long id;
	private String name;
	private String template;
	private String subject;

	public static MailBuilder builder() {
		return new MailBuilder();
	}
}