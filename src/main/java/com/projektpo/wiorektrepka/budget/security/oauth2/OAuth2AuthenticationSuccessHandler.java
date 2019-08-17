package com.projektpo.wiorektrepka.budget.security.oauth2;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.projektpo.wiorektrepka.budget.domain.User;
import com.projektpo.wiorektrepka.budget.repository.UserRepository;
import com.projektpo.wiorektrepka.budget.security.oauth2.user.UserPrincipal;
import com.projektpo.wiorektrepka.budget.security.util.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@RequiredArgsConstructor
@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private String homeUrl = "http://localhost:4200/social-login";
	private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
	private final UserRepository userRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String uri = getUri(authentication);
		clearAuthenticationAttributes(request, response);
		getRedirectStrategy().sendRedirect(request, response, uri);
	}

	protected String getUri(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		User u = userRepository.findUserByEmail(userPrincipal.getUsername());

		String token = JWT.create()
				.withSubject(u.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
		return UriComponentsBuilder.fromUriString(homeUrl)
				.queryParam("token", token)
				.build().toUriString();
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
		super.clearAuthenticationAttributes(request);
		httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
	}

}