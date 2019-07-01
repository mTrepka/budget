package com.projektpo.wiorektrepka.budget.security;


import com.projektpo.wiorektrepka.budget.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
	private final HttpServletRequest request;
	private final LogService logService;

	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		logService.logFailureUserAuthentication(request.getRemoteAddr(), event.getAuthentication().getPrincipal());
	}
}
