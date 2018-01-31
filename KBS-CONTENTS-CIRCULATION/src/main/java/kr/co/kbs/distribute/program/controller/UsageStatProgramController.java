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
import kr.co.kbs.distribute.program.service.UsageStatProgramService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.UsageStatProgramVo;

@Controller
public class UsageStatProgramController implements MDLController{
	
	@Autowired
	UsageStatProgramService service;
	
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value="/program/managerUsageStatProgram")
	public String managerClipPage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		List<String> yearList = service.getDateYear();
		model.addAttribute("yearList", yearList);
		
		CodeVo vo = new CodeVo();
		vo.setCodeType("11");
		
		model.addAttribute("channelList", codeService.getCodeList(vo));
		
		vo = new CodeVo();
		vo.setCodeType("08");
		model.addAttribute("proTypeList", codeService.getCodeList(vo));
		
		
		return "/program/managerUsageStatProgramPage";
	}
	
	@RequestMapping(value="/program/getUsageStatProgramList", method=RequestMethod.GET)
	public String getUsageStatProgramList(ProgramParamVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<UsageStatProgramVo> list= service.getUsageStatProgramList(param);
		param.makePaging(service.getTotalRecords(param));
		
		model.addAttribute("programList", list);
		model.addAttribute("param", param);
	
		return "jsonView";
	}
	
	@RequestMapping(value="/program/getUsageStatProgram", method=RequestMethod.POST)
	public String saveUsageStatProgram(UsageStatProgramVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		String resultCode = "00";
		try{
			service.saveUsageStatProgram(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
}
