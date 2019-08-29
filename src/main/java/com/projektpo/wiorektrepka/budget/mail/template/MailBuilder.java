package com.projektpo.wiorektrepka.budget.mail.template;

import com.projektpo.wiorektrepka.budget.domain.User;
import org.springframework.mail.SimpleMailMessage;

public class MailBuilder {
	private MailTemplate mailTemplate;
	private User user;
	private String code;
	private String url;
	private String message = "";

	public MailBuilder setTemplate(MailTemplate template) {
		this.mailTemplate = mailTemplate;
		return this;
	}

	public MailBuilder setUser(User user) {
		this.user = user;
		return this;
	}

	public MailBuilder setUrl(String url) {
		this.url = url;
		return this;
	}

	public MailBuilder build() {
		String temp = mailTemplate.getTemplate();
		message = temp.replace("<username>", user.getUsername())
				.replace("<email>", user.getEmail())
				.replace("<url>", url);
		return this;
	}

	public MailBuilder addTemplate(String temp, String target) {
		message.replace(temp, target);
		return this;
	}

	public SimpleMailMessage get() {
		if (message.equals("")) build();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setText(this.message);
		message.setTo(user.getEmail());
		message.setSubject(mailTemplate.getSubject());
		return message;
	}
}
