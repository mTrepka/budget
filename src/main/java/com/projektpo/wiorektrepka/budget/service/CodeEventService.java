package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.CodeEvent;

public interface CodeEventService {

	void saveCode(CodeEvent codeEvent);

	boolean validCode(CodeEvent codeEvent);

	void removeCode(CodeEvent codeEvent);
}
