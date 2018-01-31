package kr.co.kbs.distribute.media.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.media.vo.PooqVodVo;

public interface PooqVodService extends CommonService{
	List<PooqVodVo> getPooqVodList(MediaParamVo param) throws Exception;
}
