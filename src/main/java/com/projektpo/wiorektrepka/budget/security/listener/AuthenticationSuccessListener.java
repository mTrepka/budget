package com.projektpo.wiorektrepka.budget.security.listener;

import com.projektpo.wiorektrepka.budget.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
	private final HttpServletRequest request;
	private final LogService logService;


	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		logService.logSuccessUserAuthentication(request.getRemoteAddr(), event.getAuthentication().getPrincipal());

	}
}
