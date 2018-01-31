package kr.co.kbs.distribute.dashboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.dashboard.service.TotalDashBoardService;
import kr.co.kbs.distribute.dashboard.vo.DashBoardParamVo;
import kr.co.kbs.distribute.media.service.SmrClipService;
import kr.co.kbs.distribute.media.vo.MediaParamVo;

@Controller
public class TotalDashBoardController implements MDLController{
	
	@Autowired
	TotalDashBoardService service;
	
	@Autowired
	SmrClipService smrClipService;
	
	@RequestMapping(value="/dashboard/total/managerTotalDashBoard")
	public String managerTotalDashBoard(HttpServletRequest req, Model model) throws Exception {
		
		return "/dashboard/managerTotalDashBoardPage";
	}
	
	@RequestMapping(value="/dashboard/total/getTotalDashBoard", method=RequestMethod.GET)
	public String getTotalDashBoard(DashBoardParamVo param, Model model) throws Exception {
		
		
		MediaParamVo mediaParam = new MediaParamVo();
		mediaParam.setSearchType("03");
		mediaParam.setSearchValue(param.getSearchValue());
		mediaParam.setSearchValue2(param.getSearchValue2());
		
		model.addAttribute("toVodList",  service.getTotalDashBoardTvVodList(param));
		model.addAttribute("pooqVodList", service.getTotalDashBoardPooqVodList(param));
		model.addAttribute("tvVodListByCseq", service.getTotalDashBoardTvVodListForCseq(param));
//		model.addAttribute("smrTopList", smrClipService.getSmrClipTopList(mediaParam));
//		model.addAttribute("viewRatingList", service.getTotalDashBoardTopViewRating20(param));
		model.addAttribute("pooqRealList", service.getPooqRealList(param));
		model.addAttribute("smrClipCnt", service.getSmrClipCnt(param));
		model.addAttribute("tvVodCntList", service.getTvVodVCntList(param));
		
		
		return "jsonView";
	}
}
