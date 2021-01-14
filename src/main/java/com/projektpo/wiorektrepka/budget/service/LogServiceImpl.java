package com.projektpo.wiorektrepka.budget.service;


import com.projektpo.wiorektrepka.budget.domain.AuthorizationLog;
import com.projektpo.wiorektrepka.budget.domain.Type;
import com.projektpo.wiorektrepka.budget.repository.AuthorizationLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("logService")
@AllArgsConstructor
public class LogServiceImpl implements LogService {
	private final AuthorizationLogRepository authorizationLogRepository;
	private final UserService userService;

	@Override
	public void logFailureUserAuthentication(String remoteAddr, Object principal) {
		AuthorizationLog log = generateLog(remoteAddr, principal);
		log.setSuccess(false);
		authorizationLogRepository.save(log);
	}

	@Override
	public void logSuccessUserAuthentication(String remoteAddr, Object principal) {
		AuthorizationLog log = generateLog(remoteAddr, principal);
		log.setSuccess(true);
		authorizationLogRepository.save(log);
	}

	private AuthorizationLog generateLog(String remoteAddr, Object principal) {
		AuthorizationLog log = new AuthorizationLog();
		try {
			log.setUser(((User) principal).getUsername());
			log.setType(Type.login);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.setIp(remoteAddr);
		return log;
	}

	@Override
	public List<AuthorizationLog> getCurrentUserAuthLog() {
		return authorizationLogRepository.findAllByUser(userService.getCurrentUserNick());
	}
}
