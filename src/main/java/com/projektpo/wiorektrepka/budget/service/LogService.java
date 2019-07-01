package com.projektpo.wiorektrepka.budget.service;

public interface LogService {
	void logFailureUserAuthentication(String remoteAddr, Object principal);

	void logSuccessUserAuthentication(String remoteAddr, Object principal);
}
