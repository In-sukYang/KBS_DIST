package kr.co.kbs.distribute.program.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.code.service.CodeService;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.program.service.OrgProgramService;
import kr.co.kbs.distribute.program.vo.OrgProgramVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Controller
public class OrgProgramController implements MDLController{
	
	@Autowired
	OrgProgramService service;
	
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value="/program/managerOrgProgram")
	public String managerOrgProgramPage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		
		CodeVo vo = new CodeVo();
		vo.setCodeType("07");
		
		model.addAttribute("broadTypeList", codeService.getCodeList(vo));
		
		vo = new CodeVo();
		vo.setCodeType("18");
		
		model.addAttribute("channelList", codeService.getCodeList(vo));
		
		return "/program/managerOrgProgramPage";
	}
	
	
	@RequestMapping(value="/program/layer/orgProgramLayer")
	public String orgProgramLayer(HttpServletRequest request, HttpServletResponse response,ProgramParamVo param, Model model) throws Exception {
		
		CodeVo vo = new CodeVo();
		vo.setCodeType("07");
		
		model.addAttribute("broadTypeList", codeService.getCodeList(vo));
		model.addAttribute("param", param);
		
		return "/program/layer/orgProgramLayer";
	}
	
	@RequestMapping(value="/program/getOrgProgramList", method=RequestMethod.GET)
	public String getOrgProgramList(ProgramParamVo param, Model model) throws Exception {
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		log.debug(param.getPagingYn());
		
		List<OrgProgramVo> list = service.getOrgProgramList(param);
		
		param.makePaging(service.getTotalRecords(param));
		
		model.addAttribute("orgProgramList", list);
		model.addAttribute("param", param);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/program/saveOrgProgram", method=RequestMethod.POST)
	public String saveOrgProgram(OrgProgramVo param, Model model) throws Exception {
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		String resultCode = "00";
		
		try{
			service.saveOrgProgram(param);
		}catch(Exception e) {
			resultCode = "12";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
}
