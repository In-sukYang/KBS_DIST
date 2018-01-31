package kr.co.kbs.distribute.interce.interceptor;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.kbs.distribute.menu.vo.LoginMenuVo;

/**
 *	
 * @Author : yhkim
 * @Date   : 2017. 5. 22.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
		HttpSession session = request.getSession(false);
		
		if(session == null){
			RequestDispatcher dispatcher =request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
			return false;
		}
		
		String userId = (String)session.getAttribute("loginId");
		if(StringUtils.isEmpty(userId)){
			RequestDispatcher dispatcher =request.getRequestDispatcher("/");
			dispatcher.forward(request, response);
			return false;
		}
		
		String uri = request.getRequestURI();
		List<LoginMenuVo> menuList = (List<LoginMenuVo>)session.getAttribute("menuList");
		boolean menuCheck = false;
		if(uri.contains("Page") || uri.contains("get") || uri.contains("save") || uri.contains("delete") || uri.contains("Layer") || uri.contains("download") ) {
			menuCheck = true;
		}else {
			String parentMenu = "";
			for(int k=0; k < menuList.size(); k++) {
				
				if(menuList.get(k).getSubCnt() != 0){
					parentMenu = menuList.get(k).getPageNm();	
				}
				
				if(uri.equals(menuList.get(k).getPageUrl())){
					menuCheck = true;
					session.setAttribute("parentMenu", parentMenu);
					session.setAttribute("acivePage", menuList.get(k).getPageUrl());
					break;
				}
			}
		}
		if(!menuCheck) {
			RequestDispatcher dispatcher =request.getRequestDispatcher("/error/menuCheck");
			dispatcher.forward(request, response);
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}

}
