package kr.co.kbs.distribute.program.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.channel.vo.UsageStatChannelVo;
import kr.co.kbs.distribute.code.service.CodeService;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.program.service.UsageStatChannelService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Controller
public class UsageStatChannelController implements MDLController{
	
	@Autowired
	UsageStatChannelService service;
	
	@Autowired
	CodeService codeService;
	
	@RequestMapping(value="/program/managerUsageStatChannel", method=RequestMethod.GET)
	public String managerUsageStatChannelPage(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		CodeVo channelVo = new CodeVo();
		channelVo.setCodeType("18");
		model.addAttribute("channleList", codeService.getCodeList(channelVo));
		
		channelVo = new CodeVo();
		channelVo.setCodeType("09");
		model.addAttribute("sexGubunList", codeService.getCodeList(channelVo));
		return "/program/managerUsageStatChannelPage";
	}
	
	@RequestMapping(value="/program/getUsageStatChanneList", method=RequestMethod.GET)
	public String getUsageStatChanneList(ProgramParamVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<UsageStatChannelVo> list = service.getUsageStatChannelList(param);
		param.makePaging(service.getTotalRecords(param));
		
		log.debug("{}" ,param.getRecordsPerPage());
		
		model.addAttribute("list", list);
		model.addAttribute("param", param);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/program/saveUsageStatChanne", method=RequestMethod.POST)
	public String saveCode(UsageStatChannelVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		String resultCode = "00";
		try{
			service.saveUsageStatChannel(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
}
