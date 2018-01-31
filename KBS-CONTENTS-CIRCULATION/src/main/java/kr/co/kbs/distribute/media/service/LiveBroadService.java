package kr.co.kbs.distribute.media.service;

import java.util.List;

import org.springframework.ui.Model;

import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.media.vo.LiveBroadVo;
import kr.co.kbs.distribute.media.vo.MediaParamVo;

public interface LiveBroadService extends CommonService{
	List<LiveBroadVo> getLiveBroadTopList(MediaParamVo param, List<CodeVo> channelList, Model model) throws Exception;
	
	List<LiveBroadVo> getLiveBroadTotalTopList(MediaParamVo param, List<CodeVo> channelList, Model model) throws Exception;
}
