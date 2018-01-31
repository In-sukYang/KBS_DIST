package kr.co.kbs.distribute.program.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.program.service.ClipService;
import kr.co.kbs.distribute.program.vo.ClipVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Controller
public class ClipController implements MDLController{
	
	@Autowired
	ClipService service;
	
	@RequestMapping(value="/program/managerClip")
	public String managerClipPage(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/program/managerClipPage";
	}
	
	@RequestMapping(value="/program/getClipList", method=RequestMethod.GET)
	public String getClipPage(ProgramParamVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<ClipVo> list = service.getClipList(param);
		param.makePaging(service.getTotalRecords(param));
		
		log.debug("{}" ,param.getRecordsPerPage());
		
		model.addAttribute("programList", list);
		model.addAttribute("param", param);
		return "jsonView";
	}
	
	@RequestMapping(value="/program/saveClip", method=RequestMethod.POST)
	public String saveClip(ClipVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		String resultCode = "00";
		try{
			service.saveClip(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
	
}
