package com.projektpo.wiorektrepka.budget.service;


import com.projektpo.wiorektrepka.budget.domain.AuthorizationLog;
import com.projektpo.wiorektrepka.budget.repository.AuthorizationLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service("logService")
@AllArgsConstructor
public class LogServiceImpl implements LogService {
	private final AuthorizationLogRepository authorizationLogRepository;

	@Override
	public void logFailureUserAuthentication(String remoteAddr, Object principal) {
		AuthorizationLog log = new AuthorizationLog();
		log.setType("fail");
		log.setUser((String) principal);
		log.setIp(remoteAddr);
		authorizationLogRepository.save(log);
	}

	@Override
	public void logSuccessUserAuthentication(String remoteAddr, Object principal) {
		AuthorizationLog log = new AuthorizationLog();
		log.setType("success");
		log.setUser(((User) principal).getUsername());
		log.setIp(remoteAddr);
		authorizationLogRepository.save(log);
	}
}
