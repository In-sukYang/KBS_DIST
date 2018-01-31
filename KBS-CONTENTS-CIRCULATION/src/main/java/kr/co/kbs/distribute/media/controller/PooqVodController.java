package kr.co.kbs.distribute.media.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.code.service.CodeService;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.common.service.MinMaxDayService;
import kr.co.kbs.distribute.company.service.CompanyService;
import kr.co.kbs.distribute.media.service.PooqVodService;
import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.program.service.UsageStatProgramService;

@Controller
public class PooqVodController implements MDLController {
	
	@Autowired
	PooqVodService service;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	UsageStatProgramService usageStatProgramService;
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	MinMaxDayService minMaxDayService;
	
	@RequestMapping(value="/media/mangerPooqVod", method=RequestMethod.GET)
	public String mangerPooqVodList(HttpServletRequest request ,Model model) throws Exception {
		
		CodeVo channelVo = new CodeVo();
		
		channelVo.setCodeType("18");
		
		
		model.addAttribute("channleList", codeService.getCodeList(channelVo));
		model.addAttribute("dayInfo", minMaxDayService.getUsageStatProgramMinMaxDay("01"));
		return "/media/pooqVodMangerPage";
	}
	
	@RequestMapping(value="/media/getPooqVodList", method=RequestMethod.GET)
	public String getTvVodList(MediaParamVo param ,Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		model.addAttribute("pooqList", service.getPooqVodList(param));
		
		return "jsonView";
	}
	
}
