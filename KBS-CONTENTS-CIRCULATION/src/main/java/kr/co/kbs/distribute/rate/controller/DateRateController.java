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
import kr.co.kbs.distribute.rate.service.DateRateService;
import kr.co.kbs.distribute.rate.vo.DayRateVo;
import kr.co.kbs.distribute.rate.vo.RateParamVo;

@Controller
public class DateRateController implements MDLController{
	
	@Autowired
	DateRateService service;
	
	@RequestMapping(value="/rate/managerDateRate")
	public String managerDateRatePage(HttpServletRequest request, HttpServletResponse response, Model model){
		return "/rate/managerDateRatePage";
	}
	
	@RequestMapping(value="/rate/getDateRateList", method=RequestMethod.GET)
	public String getDateRateList(RateParamVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		
		List<DayRateVo> list = service.getDateRateList(param);
		param.makePaging(service.getTotalRecords(param));
		
		model.addAttribute("list", list);
		model.addAttribute("param", param);
		return "jsonView";
	}
	
	@RequestMapping(value="/rate/saveDaterate", method=RequestMethod.POST)
	public String saveDateRate(DayRateVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		String resultCode = "00";
		try{
			service.saveDateRate(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
}
