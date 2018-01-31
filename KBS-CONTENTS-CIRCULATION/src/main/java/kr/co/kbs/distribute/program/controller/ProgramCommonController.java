package kr.co.kbs.distribute.program.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.program.service.ProgramCommonService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Controller
public class ProgramCommonController implements MDLController{
	
	@Autowired
	ProgramCommonService service;
	
	@RequestMapping(value="/program/common/getProgramVodcntList", method=RequestMethod.GET) 
	public String getProgramVodcntList(ProgramParamVo param, Model model) throws Exception {
		
		model.addAttribute("vodcnt", service.getProgramVodcntList(param));
		
		return "jsonView";
	}
		
	
}
