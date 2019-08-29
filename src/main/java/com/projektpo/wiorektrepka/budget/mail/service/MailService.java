package com.projektpo.wiorektrepka.budget.mail.service;


import com.projektpo.wiorektrepka.budget.domain.User;

public interface MailService {
	void sendWelcomeEmail(User u);

	void sendForgotPasswordByEmail(User email, String code);
}
