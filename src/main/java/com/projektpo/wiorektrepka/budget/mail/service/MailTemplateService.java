package com.projektpo.wiorektrepka.budget.mail.service;

import com.projektpo.wiorektrepka.budget.mail.template.MailTemplate;

public interface MailTemplateService {
	MailTemplate getFooter();

	MailTemplate getTemplateByName(String name);

	MailTemplate save(MailTemplate e);
}
