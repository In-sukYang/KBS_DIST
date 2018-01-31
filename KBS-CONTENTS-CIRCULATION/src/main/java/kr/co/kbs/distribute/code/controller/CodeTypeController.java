package kr.co.kbs.distribute.code.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.code.service.CodeTypeService;
import kr.co.kbs.distribute.code.vo.CodeParamVo;
import kr.co.kbs.distribute.code.vo.CodeTypeVo;
import kr.co.kbs.distribute.common.controller.MDLController;

@Controller
public class CodeTypeController implements MDLController {
	
	@Autowired
	CodeTypeService service;
	
	@RequestMapping(value="/code/managerCodeType", method=RequestMethod.GET)
	public String managerCodeTypePage(HttpServletRequest request, HttpServletResponse response, Model model){
		
		return "/code/managerCodeTypePage";
	}
	
	@RequestMapping(value="/code/getCodeTypeList", method=RequestMethod.GET)
	public String getCodeTypeList(CodeParamVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<CodeTypeVo> list = service.getCodeTypeList(param);
		
		//param.makePaging(service.getTotalRecords(param));
		
		model.addAttribute("codeList", list);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/code/saveCodeType", method=RequestMethod.POST)
	public String saveCode(CodeTypeVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		String resultCode = "00";
		try{
			service.saveCodeType(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/code/deleteCodeType", method=RequestMethod.POST)
	public String deleteCode(CodeTypeVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		String resultCode = "00";
		try{
			service.deleteCodeType(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}

}
