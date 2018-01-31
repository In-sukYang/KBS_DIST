package kr.co.kbs.distribute.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.common.util.DateUtil;
import kr.co.kbs.distribute.common.vo.WeekDateVo;

@Controller
public class DateUtilController implements MDLController{
	
	@RequestMapping(value="/comm/getWeekDateList", method=RequestMethod.GET)
	public String getWeekDateList(WeekDateVo param, Model model) throws Exception {
		
		String year = param.getYear();
		String month = param.getMonth();
		if(year != null && month != null) {
			model.addAttribute("weekList", DateUtil.getWeekInMonths(year, month));
		}
		
		return "jsonView";
	}

}
