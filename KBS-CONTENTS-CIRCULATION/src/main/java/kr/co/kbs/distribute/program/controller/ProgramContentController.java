
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
import kr.co.kbs.distribute.program.service.ProgramContentService;
import kr.co.kbs.distribute.program.vo.ProgramContentVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Controller
public class ProgramContentController implements MDLController{
	
	@Autowired
	ProgramContentService service;
	
	@RequestMapping(value="/program/managerProgramContent")
	public String managerProgramContentPage(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/program/managerProgramContentPage";
	}
	
	@RequestMapping(value="/program/getProgramContentList", method=RequestMethod.GET)
	public String getProgramList(ProgramParamVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<ProgramContentVo> list = service.getProgramContentList(param);
		param.makePaging(service.getTotalRecords(param));
		
		model.addAttribute("programList", list);
		model.addAttribute("param", param);
		return "jsonView";
	}
	
	@RequestMapping(value="/program/layer/contentLayer")
	public String contentLayer(HttpServletRequest request, HttpServletResponse response,ProgramParamVo param,  Model model){

		model.addAttribute("param", param);
		
		return "/program/layer/contentLayer";
	}
	
	
	@RequestMapping(value="/program/saveProgramContent", method=RequestMethod.POST)
	public String saveProgramContent(ProgramContentVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		String resultCode = "00";
		try{
			service.saveProgramContent(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
	
	
}
