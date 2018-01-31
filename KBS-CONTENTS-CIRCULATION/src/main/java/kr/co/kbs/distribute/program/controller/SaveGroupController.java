package kr.co.kbs.distribute.program.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.kbs.distribute.code.service.CodeService;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.common.service.SaveGroupService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Controller
public class SaveGroupController implements MDLController{
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	SaveGroupService service;
	
	@RequestMapping(value="/program/managerSaveGroup")
	public String managerSaveGroup(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
		CodeVo vo = new CodeVo();
		vo.setCodeType("06");
		
		model.addAttribute("distTypeList", codeService.getCodeList(vo));
		
		return "/program/managerSaveGroupPage";
	}
	
	@RequestMapping(value="/program/getSaveGroupList")
	public String getSaveGroupList(ProgramParamVo param, Model model) throws Exception{
		
		model.addAttribute("list", service.getSaveGroup(param));
		param.makePaging(service.getTotalRecords(param));
		model.addAttribute("param", param);
		return "jsonView";
	}
	
}
