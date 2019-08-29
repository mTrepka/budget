package com.projektpo.wiorektrepka.budget.util;

import com.projektpo.wiorektrepka.budget.domain.AppEvent;
import com.projektpo.wiorektrepka.budget.domain.CodeEvent;

import java.util.UUID;


public class CodeEventBuilder {
	private CodeEvent codeEvent;

	public CodeEventBuilder() {
		this.codeEvent = new CodeEvent();
	}

	public CodeEventBuilder setUserId(long userId) {
		codeEvent.setUserId(userId);
		return this;
	}

	public CodeEventBuilder setAppEvent(AppEvent appEvent) {
		codeEvent.setEvent(appEvent);
		return this;
	}

	public CodeEvent get() {
		codeEvent.setCode(UUID.randomUUID().toString().replace("-", "1"));
		return codeEvent;
	}
}
