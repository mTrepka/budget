package com.projektpo.wiorektrepka.budget.mail.service;

import com.projektpo.wiorektrepka.budget.UrlUtil;
import com.projektpo.wiorektrepka.budget.domain.User;
import com.projektpo.wiorektrepka.budget.mail.template.MailTemplate;
import com.projektpo.wiorektrepka.budget.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {
	private final MailTemplateService mailTemplateService;
	private final UserRepository userRepository;
	private final JavaMailSender mailSender;

	public void sendRegisterCode(String code, String email) {
		SimpleMailMessage message = MailTemplate.builder()
				.setTemplate(mailTemplateService.getTemplateByName("registerCode"))
				.setUser(userRepository.findUserByEmail(email))
				.setUrl(UrlUtil.APP_URL + UrlUtil.REGISTER_CODE)
				.build()
				.addTemplate("<code>", code)
				.get();
		mailSender.send(message);
	}

	@Override
	public void sendForgotPasswordByEmail(User u, String code) {
		SimpleMailMessage message = MailTemplate.builder()
				.setTemplate(mailTemplateService.getTemplateByName("forgotPassword"))
				.setUser(u)
				.setUrl(UrlUtil.APP_URL + UrlUtil.FORGOTTEN_PASSWORD + code)
				.get();
		mailSender.send(message);
	}

	@Override
	public void sendWelcomeEmail(User u) {
		SimpleMailMessage message = MailTemplate.builder()
				.setTemplate(mailTemplateService.getTemplateByName("welcome"))
				.setUser(u)
				.setUrl(UrlUtil.APP_URL + UrlUtil.WELCOME_URL)
				.get();
		mailSender.send(message);
	}
}
