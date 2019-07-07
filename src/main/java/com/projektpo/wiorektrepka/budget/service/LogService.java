package com.projektpo.wiorektrepka.budget.service;

import com.projektpo.wiorektrepka.budget.domain.AuthorizationLog;

import java.util.List;

public interface LogService {
	void logFailureUserAuthentication(String remoteAddr, Object principal);

	void logSuccessUserAuthentication(String remoteAddr, Object principal);

	List<AuthorizationLog> getCurrentUserAuthLog();
}
