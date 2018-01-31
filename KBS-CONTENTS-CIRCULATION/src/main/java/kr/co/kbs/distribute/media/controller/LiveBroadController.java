package kr.co.kbs.distribute.media.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.code.service.CodeService;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.media.service.LiveBroadService;
import kr.co.kbs.distribute.media.vo.MediaParamVo;

@Controller
public class LiveBroadController implements MDLController {
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	LiveBroadService service;
	
	@RequestMapping(value="/media/managerLiveBroad", method=RequestMethod.GET)
	public String managerLiveBroadPage(HttpServletRequest request ,Model model) throws Exception {
		
		CodeVo channelVo = new CodeVo();
		channelVo.setCodeType("18");
		
		model.addAttribute("channleList", codeService.getCodeList(channelVo));
		
		return "/media/managerLiveBroadPage";
	}
	
	@RequestMapping(value="/media/getLiveBroadTopList", method=RequestMethod.GET)
	public String getLiveBroadTopList(MediaParamVo param ,Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		CodeVo channelVo = new CodeVo();
		channelVo.setCodeType("18");
		
		List<CodeVo> channelList = codeService.getCodeList(channelVo);
		
		service.getLiveBroadTopList(param, channelList, model);
		
		
		return "jsonView";
	}
	
	
	@RequestMapping(value="/media/getLiveBroadTotalTopList", method=RequestMethod.GET)
	public String getLiveBroadTotalTopList(MediaParamVo param ,Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		CodeVo channelVo = new CodeVo();
		channelVo.setCodeType("18");
		
		List<CodeVo> channelList = codeService.getCodeList(channelVo);
		
		service.getLiveBroadTotalTopList(param, channelList, model);
		
		
		return "jsonView";
	}
	
	
}
