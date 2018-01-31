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
import kr.co.kbs.distribute.company.vo.CompanyParamVo;
import kr.co.kbs.distribute.media.service.TvVodService;
import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.program.service.UsageStatProgramService;

@Controller
public class TvVodController implements MDLController {
	
	@Autowired
	TvVodService service;
	
	@Autowired
	CompanyService companyService;
	
	@Autowired
	UsageStatProgramService usageStatProgramService;
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	MinMaxDayService minMaxDayService;
	
	@RequestMapping(value="/media/managerTvVod", method=RequestMethod.GET)
	public String mangerTvVodList(HttpServletRequest request ,Model model) throws Exception {
		
		CodeVo channelVo = new CodeVo();
		CodeVo distributionVo = new CodeVo();
		CompanyParamVo platformParamVo = new CompanyParamVo();
		
		channelVo.setCodeType("18");
		distributionVo.setCodeType("19");
		platformParamVo.setCType("P");
		
		model.addAttribute("channleList", codeService.getCodeList(channelVo));
		model.addAttribute("distributionList", codeService.getCodeList(distributionVo));
		model.addAttribute("platformList", companyService.getCompanyListNoPage(platformParamVo));
		model.addAttribute("dayInfo", minMaxDayService.getUsageStatProgramMinMaxDay("02"));
		
		
		return "/media/tvVodMangerPage";
	}
	
	@RequestMapping(value="/media/getTvVodList", method=RequestMethod.GET)
	public String getTvVodList(MediaParamVo param ,Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		model.addAttribute("tvList", service.getTvVodList(param));
		
		return "jsonView";
	}
	
}
