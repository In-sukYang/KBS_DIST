package kr.co.kbs.distribute.statusboard.controller;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.media.vo.PooqVodVo;
import kr.co.kbs.distribute.media.vo.SMRClipVo;
import kr.co.kbs.distribute.media.vo.TvVodVo;
import kr.co.kbs.distribute.statusboard.service.PointStatusBoardService;
import kr.co.kbs.distribute.statusboard.vo.RateRankVo;

@Controller
public class PointStatusBoardController implements MDLController{
	
	@Autowired
	PointStatusBoardService service;
	
	@RequestMapping(value="/statusboard/managerPointStatusBoard")
	public String managerPointStatusBoard(HttpServletRequest req, Model model) {
		return "/statusboard/managerPointStatusBoardPage";
	}
	
	@RequestMapping(value="/statusboard/getPointStatusBoard", method=RequestMethod.GET)
	public String getPointStatusBoard(MediaParamVo param, Model model) throws Exception {
		
		List<TvVodVo> tvVodList =   service.getTvVodTopFiveList(param);
		List<PooqVodVo> pooqVodList = service.getPooqVodFiveList(param);
		List<SMRClipVo> smrClipList=  service.getSmrClipTopFiveList(param);
		List<RateRankVo> ratingList = service.getRatingTopFiveList(param); 
		List<RateRankVo> pooqLiveList = service.getPooqLiveTopFiveList(param);
		
		tvVodList.removeAll(Collections.singleton(null));
		pooqVodList.removeAll(Collections.singleton(null));
		smrClipList.removeAll(Collections.singleton(null));
		ratingList.removeAll(Collections.singleton(null));
		pooqLiveList.removeAll(Collections.singleton(null));
		
		model.addAttribute("tvVodList", tvVodList);
		model.addAttribute("pooqVodList", pooqVodList);
		model.addAttribute("smrClipList", smrClipList);
		model.addAttribute("ratingList", ratingList);
		model.addAttribute("pooqLiveList", pooqLiveList);
		
		
		return "jsonView";
		
	}
		
}
