package com.projektpo.wiorektrepka.budget.mail.service;

import com.projektpo.wiorektrepka.budget.mail.template.MailTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("EmailTemplate")
@RequiredArgsConstructor
public class MailTemplateServiceImpl implements MailTemplateService {
	private final MailTemplateRepository emailTemplateRepository;

	@Override
	public MailTemplate getFooter() {
		return emailTemplateRepository.getByName("FOOTER");
	}

	@Override
	public MailTemplate getTemplateByName(String name) {
		return emailTemplateRepository.getByName(name);
	}

	@Override
	public MailTemplate save(MailTemplate e) {
		return emailTemplateRepository.save(e);
	}
}
