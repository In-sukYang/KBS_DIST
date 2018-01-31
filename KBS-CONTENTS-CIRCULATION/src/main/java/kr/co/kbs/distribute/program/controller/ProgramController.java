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
import kr.co.kbs.distribute.program.service.ProgramService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.ProgramVo;

@Controller
public class ProgramController implements MDLController{
	
	@Autowired
	ProgramService service;

	@Autowired
	CodeService codeService;
	
	@RequestMapping(value="/program/managerProgram")
	public String managerProgramPage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
		CodeVo vo = new CodeVo();
		vo.setCodeType("12");
		
		model.addAttribute("broadList", codeService.getCodeList(vo));
		
		vo = new CodeVo();
		vo.setCodeType("11");
		
		model.addAttribute("channelList", codeService.getCodeList(vo));
		
		vo = new CodeVo();
		vo.setCodeType("07");
		
		model.addAttribute("broadTypeList", codeService.getCodeList(vo));
		
		return "/program/managerProgramPage";
	}
	
	@RequestMapping(value="/program/layer/parentProgramLayer")
	public String parentProgramLayer(HttpServletRequest request, HttpServletResponse response, ProgramParamVo param, Model model) throws Exception{
		
		CodeVo vo = new CodeVo();
		vo.setCodeType("12");
		
		model.addAttribute("broadList", codeService.getCodeList(vo));
		
		vo = new CodeVo();
		vo.setCodeType("11");
		
		model.addAttribute("channelList", codeService.getCodeList(vo));
		
		vo = new CodeVo();
		vo.setCodeType("07");
		
		model.addAttribute("broadTypeList", codeService.getCodeList(vo));
		model.addAttribute("param", param);
		
		return "/program/layer/parentProgramLayer";
	}
	
	@RequestMapping(value="/program/getProgramList", method=RequestMethod.GET)
	public String getProgramList(ProgramParamVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<ProgramVo> list = service.getProgramList(param);
		param.makePaging(service.getTotalRecords(param));
		
		log.debug("{}" ,param.getRecordsPerPage());
		
		model.addAttribute("programList", list);
		model.addAttribute("param", param);
		return "jsonView";
	}
	
	
	@RequestMapping(value="/program/getProgramListLayer", method=RequestMethod.GET)
	public String getProgramListLayer(ProgramParamVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<ProgramVo> list = service.getProgramListLayer(param);
		
		model.addAttribute("programList", list);
		model.addAttribute("param", param);
		return "jsonView";
	}
	
	
	@RequestMapping(value="/program/saveProgram", method=RequestMethod.POST)
	public String saveProgram(ProgramVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		String resultCode = "00";
		try{
			service.saveProgram(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
}
