package kr.co.kbs.distribute.rate.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.rate.service.MinuteRateService;
import kr.co.kbs.distribute.rate.vo.MinuteRateVo;
import kr.co.kbs.distribute.rate.vo.RateParamVo;

@Controller
public class MinuteRateController implements MDLController{
	
	@Autowired
	MinuteRateService service;
	
	@RequestMapping(value="/rate/managerMinuteRate")
	public String managerMinuteRatePage(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/rate/managerMinuteRatePage";
	}
	
	@RequestMapping(value="/rate/getMinuteRateList", method=RequestMethod.GET)
	public String getMinuteRateList(RateParamVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<MinuteRateVo> list = service.getMinuteRateList(param);
		param.makePaging(service.getTotalRecords(param));
		
		model.addAttribute("list", list);
		model.addAttribute("param", param);
		return "jsonView";
	}
	
	@RequestMapping(value="/program/saveMinuteRate", method=RequestMethod.POST)
	public String saveProgram(MinuteRateVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		String resultCode = "00";
		try{
			service.saveMinuteRate(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
}
