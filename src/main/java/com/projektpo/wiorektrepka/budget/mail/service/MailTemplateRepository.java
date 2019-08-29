package com.projektpo.wiorektrepka.budget.mail.service;

import com.projektpo.wiorektrepka.budget.mail.template.MailTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailTemplateRepository extends JpaRepository<MailTemplate, Long> {
	MailTemplate getByName(String name);
}
