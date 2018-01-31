package kr.co.kbs.distribute.code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.code.service.CodeService;
import kr.co.kbs.distribute.code.vo.CodeParamVo;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.controller.MDLController;

@Controller
public class CodeController implements MDLController{

	@Autowired
	CodeService service;
	
	@RequestMapping(value="/code/mangerCodePage", method=RequestMethod.GET)
	public String managerCodePage(HttpServletRequest request, HttpServletResponse response, Model model){
		
		return "/code/mangerCodePage";
	}
	
	@RequestMapping(value="/code/getCodeList", method=RequestMethod.GET)
	public String getCodeList(CodeParamVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<CodeVo> list = service.getCodeList(param);
		param.makePaging(service.getTotalRecords(param));
		
		model.addAttribute("subCodeList", list);
		model.addAttribute("param", param);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/code/saveCode", method=RequestMethod.POST)
	public String saveCode(CodeVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		String resultCode = "00";
		try{
			service.saveCode(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/code/getCodeList2", method=RequestMethod.GET)
	public String getCodeList2(CodeVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<CodeVo> list = service.getCodeList(param);
		
		model.addAttribute("subCodeList", list);
		model.addAttribute("param", param);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/code/deleteCode", method=RequestMethod.POST)
	public String deleteCode(CodeVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		service.deleteCode(param);
		
		return "jsonView";
	}
}
