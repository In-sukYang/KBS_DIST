package kr.co.kbs.distribute.media.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.media.vo.SMRClipVo;

public interface SmrClipService extends CommonService{
	
	List<SMRClipVo> getSmrClipTopList(MediaParamVo param) throws Exception;

}
