package kr.co.kbs.distribute.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;


public class CookieUtil {
	public static Cookie getCookie(HttpServletRequest request, String name){
		Cookie returnCookie = null;
		String cookies = request.getHeader("cookie");

		if(StringUtils.isEmpty(cookies)){
			cookies ="";
		}
		
		if(!cookies.equals("")){
			if(cookies.indexOf(";") != -1){
				String[] _cookies = cookies.split(";");
				for(int i=0;i<_cookies.length;i++){
					if(_cookies[i].indexOf("=") != -1){
						String[] _cookieName = _cookies[i].split("=");	//smartUXManager
						if(name.equals(_cookieName[0].trim())){
							String _cookieVal =  _cookies[i].substring(_cookieName[0].length()+1,_cookies[i].length());
							_cookieVal = _cookieVal.replaceAll("\"", "");
							returnCookie = new Cookie(name, _cookieVal);
							break;
						}
					}
				}
			}
		}
		return returnCookie;
	}

	public static void setSessionCookie(HttpServletResponse response, String cookieName,
			String domain, String cookieValue, int maxAge) {
		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setDomain(domain);
		maxAge = maxAge > 0 ? maxAge : 0;
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static void clearSessionCookie(HttpServletResponse response, String cookieName,
			String domain) {
		Cookie cookie = new Cookie(cookieName, "");
		cookie.setMaxAge(0);
		cookie.setDomain(domain);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	public static void refreshSessionCookie(HttpServletRequest request, HttpServletResponse response,
			String cookieName, String domain, int maxAge) {
		Cookie cookie = getSessionCookie(request, cookieName);
		if (cookie != null) {
			cookie.setMaxAge(maxAge);
			cookie.setDomain(domain);
			cookie.setPath("/");
			response.addCookie(cookie);
		}
	}

	public static String getSessionCookieValue(HttpServletRequest request, String cookieName) {
		Cookie cookie = getSessionCookie(request, cookieName);
		if (cookie != null) {
			return cookie.getValue();
		}
		return null;
	}

	private static Cookie getSessionCookie(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (cookieName.equals(cookies[i].getName())) {
					return cookies[i];
				}
			}
		}
		return null;
	}
}

