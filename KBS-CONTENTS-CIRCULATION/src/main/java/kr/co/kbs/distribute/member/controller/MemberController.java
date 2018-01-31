package kr.co.kbs.distribute.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.company.service.CompanyService;
import kr.co.kbs.distribute.member.service.MemberService;
import kr.co.kbs.distribute.member.vo.MemberParamVo;
import kr.co.kbs.distribute.member.vo.MemberVo;

@Controller
public class MemberController implements MDLController{
	
	@Autowired
	MemberService service;
	
	@Autowired
	CompanyService	companyService;
	
	@RequestMapping(value="/member/managerMember", method=RequestMethod.GET)
	public String managerMemberPage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		model.addAttribute("companyList",companyService.getCompanyListNoPage(null));
		return "/member/managerMemberPage";
	}
	
	@RequestMapping(value="/member/getMemberList", method=RequestMethod.GET)
	public String getMemberList(MemberParamVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<MemberVo> list = service.getMemberList(param);
		
		param.makePaging(service.getTotalRecords(param));
		
		model.addAttribute("memberList", list);
		model.addAttribute("param", param);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/member/saveMember", method=RequestMethod.POST)
	public String saveMember(MemberVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		service.saveMember(param);
		
		return "jsonView";
	}
}
