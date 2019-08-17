package com.projektpo.wiorektrepka.budget.security.oauth2.user;

import com.projektpo.wiorektrepka.budget.security.oauth2.AuthProvider;
import com.projektpo.wiorektrepka.budget.exceptions.OAuth2AuthenticationProcessingException;

import java.util.Map;

public class OAuth2UserInfoFactory {

	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if(registrationId.equalsIgnoreCase(AuthProvider.google.toString())) {
			return new GoogleOAuth2UserInfo(attributes);
		} else {
			throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
		}
	}
}