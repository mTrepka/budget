package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.CodeEvent;
import com.projektpo.wiorektrepka.budget.repository.CodeEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("codeEventService")
@RequiredArgsConstructor
public class CodeEventServiceImpl implements CodeEventService {
	private final CodeEventRepository codeEventRepository;

	@Override
	public boolean validCode(CodeEvent codeEvent) {
		CodeEvent ce = codeEventRepository.findById(codeEvent.getCodeEventId()).get();
		return codeEvent.getUserId() == ce.getUserId();
	}

	@Override
	public void removeCode(CodeEvent codeEvent) {
		codeEventRepository.save(codeEvent);
	}

	@Override
	public void saveCode(CodeEvent codeEvent) {
		codeEventRepository.save(codeEvent);
	}
}
