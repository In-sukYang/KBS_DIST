package kr.co.kbs.distribute.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.member.vo.MemberVo;

public interface LoginService extends CommonService{
	public int loginCheck(HttpServletRequest request, HttpServletResponse res, MemberVo param, Model model) throws Exception;
}
