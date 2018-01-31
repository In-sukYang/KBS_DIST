package kr.co.kbs.distribute.program.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.program.service.ProgramExpService;
import kr.co.kbs.distribute.program.vo.ProgramExpVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Controller
public class ProgramExpController implements MDLController{

	@Autowired
	ProgramExpService service;
	
	@RequestMapping(value="/program/getProgramExpList", method=RequestMethod.GET)
	public String getProgramExpList(ProgramParamVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<ProgramExpVo> list = service.getProgramExpList(param);
		
		model.addAttribute("programExpList", list);
		return "jsonView";
	}
	
	@RequestMapping(value="/program/deleteProgramExp", method=RequestMethod.POST)
	public String deleteProgramExp(ProgramParamVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		try{
			service.deleteProgramExp(param);
		}catch(Exception e) {
			
		}
		
		return "jsonView";
	}
	
	@RequestMapping(value="/program/saveProgramExp", method=RequestMethod.POST)
	public String saveProgramExp(ProgramExpVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		try{
			service.saveProgramExp(param);
		}catch(Exception e) {
			
		}
		
		return "jsonView";
	}
	
}
