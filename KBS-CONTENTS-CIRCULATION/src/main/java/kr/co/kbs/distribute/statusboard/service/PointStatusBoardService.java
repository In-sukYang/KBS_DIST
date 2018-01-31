package kr.co.kbs.distribute.statusboard.service;

import java.sql.SQLException;
import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.media.vo.PooqVodVo;
import kr.co.kbs.distribute.media.vo.SMRClipVo;
import kr.co.kbs.distribute.media.vo.TvVodVo;
import kr.co.kbs.distribute.statusboard.vo.RateRankVo;

public interface PointStatusBoardService extends CommonService{
	List<TvVodVo> getTvVodTopFiveList(MediaParamVo param) throws Exception;
	List<PooqVodVo>getPooqVodFiveList(MediaParamVo param) throws Exception;
	List<SMRClipVo> getSmrClipTopFiveList(MediaParamVo param) throws Exception;
	List<RateRankVo> getRatingTopFiveList(MediaParamVo param) throws Exception;
	List<RateRankVo> getPooqLiveTopFiveList(MediaParamVo param) throws Exception;
}
