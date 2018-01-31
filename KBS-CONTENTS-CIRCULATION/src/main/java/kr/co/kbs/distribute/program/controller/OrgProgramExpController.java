package kr.co.kbs.distribute.program.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.program.service.OrgProgramExpService;
import kr.co.kbs.distribute.program.vo.OrgProgramExpVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Controller
public class OrgProgramExpController implements MDLController{
	
	@Autowired
	OrgProgramExpService service;
	
	@RequestMapping(value="/program/getOrgProgramExpList", method=RequestMethod.GET)
	public String getOrgProgramExpList(ProgramParamVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<OrgProgramExpVo> list = service.getOrgProgramExpList(param);
		
		model.addAttribute("orgProgramExpList", list);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/program/deleteOrgProgramExp", method=RequestMethod.POST)
	public String deleteOrgProgramExp(ProgramParamVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		try{
			service.deleteOrgProgramExp(param);
		}catch(Exception e) {
			
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="/program/saveOrgProgramExp", method=RequestMethod.POST)
	public String saveOrgProgramExp(OrgProgramExpVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		try{
			service.saveOrgProgramExp(param);
		}catch(Exception e) {
			
		}
		
		return "jsonView";
	}
}
