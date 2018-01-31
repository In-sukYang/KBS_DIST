package kr.co.kbs.distribute.media.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.media.vo.TvVodVo;

public interface TvVodService extends CommonService{
	List<TvVodVo> getTvVodList(MediaParamVo param) throws Exception;
}
