package kr.co.kbs.distribute.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.login.service.LoginService;
import kr.co.kbs.distribute.member.vo.MemberVo;

@Controller
public class LoginController implements MDLController {

	@Autowired
	LoginService service;
	
	@RequestMapping(value= "/login/loginCheck" ,method=RequestMethod.POST)
	public String loginCheck(HttpServletRequest request, HttpServletResponse res, Model model, MemberVo param) throws Exception{
		
		log.debug(" {} {} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		int result  = service.loginCheck(request, res, param, model);
		
		model.addAttribute("loginAccount", result);
		
		return "jsonView";
		
	}
	
	@RequestMapping(value= "/adminLogin" ,method=RequestMethod.GET)
	public String userMainPage(HttpServletRequest request ,Model model) throws Exception{
		log.debug("관리자 로그인 페이지 ");
	    return "/index";
	}
	
	
	@RequestMapping(value= "/logout" ,method=RequestMethod.GET)
	public String logout(HttpServletRequest request ,Model model) throws Exception{
		
		HttpSession session = request.getSession();
		
		if(session!=null) {
			log.debug("loginType:"+session.getAttribute("loginType"));
			if(session.getAttribute("loginType").equals("01")) {
				  session.invalidate();
				  return "/index";
			}else {
				session.invalidate();
				  return "/index_user";
			}
		}else {
			return "/index_user";
		}
	}
	
	@RequestMapping(value= "/" ,method=RequestMethod.GET)
	public String mainPage(HttpServletRequest request ,Model model) throws Exception{
		HttpSession session = request.getSession();
		
		if(session!=null) {
			String loginType = (String) ObjectUtils.defaultIfNull(session.getAttribute("loginType"),"00");
			session.invalidate();
			if(loginType==null||"".equals(loginType)) loginType = "01";
			
			log.debug("loginType111:"+loginType);
			if(loginType.equals("01")) {
				  return "/index";
			}else {
				  return "/index_user";
			}
		}else {
			return "/index_user";
		}
	}
}
