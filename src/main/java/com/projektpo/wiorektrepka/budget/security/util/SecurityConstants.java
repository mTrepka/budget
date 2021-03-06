package com.projektpo.wiorektrepka.budget.security.util;

public class SecurityConstants {
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 36_000_000; // 1 hour
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String HOME_URL = "http://localhost:4200/social-login";
}
