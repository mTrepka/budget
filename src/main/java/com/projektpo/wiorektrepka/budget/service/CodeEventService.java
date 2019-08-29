package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.CodeEvent;

public interface CodeEventService {
	boolean validCode(CodeEvent codeEvent);

	void saveCode(CodeEvent codeEvent);

	void removeCode(CodeEvent codeEvent);
}
