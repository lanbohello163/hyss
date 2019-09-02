package com.zxtech.platform.auth;

import java.util.Objects;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class CookieUtils {

	public final static String TOKEN_KEY = "access_token";
	public final static String AUTH_ERR_USER_PREFIX = "auth_err_user__";

	/**
	 * get cookie
	 * 
	 * @param httpServletRequest
	 * @param cookieName
	 * @return
	 */
	public static String getCookie(HttpServletRequest httpServletRequest, String cookieName) {
		Objects.requireNonNull(cookieName);
		String token = null;
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return token;
	}

	/**
	 * set cookie
	 * 
	 * @param httpServletResponse
	 * @param cookieName
	 * @param cookieValue
	 */
	public static void setCookie(HttpServletResponse httpServletResponse, String cookieName, String cookieValue,
			Integer expiry) {
		Objects.requireNonNull(cookieName);
		Objects.requireNonNull(cookieValue);
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(expiry == null ? -1 : expiry);
		cookie.setPath("/");
		// cookie.setSecure(true); // https only
		httpServletResponse.addCookie(cookie);
	}

	/**
	 * remove cookie
	 * 
	 * @param httpServletResponse
	 * @param cookieName
	 */
	public static void removeCookie(HttpServletResponse httpServletResponse, String cookieName) {
		Objects.requireNonNull(cookieName);
		Cookie cookie = new Cookie(cookieName, StringUtils.EMPTY);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		httpServletResponse.addCookie(cookie);
	}
}
