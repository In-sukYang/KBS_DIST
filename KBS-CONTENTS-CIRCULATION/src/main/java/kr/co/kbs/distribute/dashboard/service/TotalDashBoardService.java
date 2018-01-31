package kr.co.kbs.distribute.dashboard.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.dashboard.vo.DashBoardParamVo;
import kr.co.kbs.distribute.dashboard.vo.TvVodTotalDashBoardVo;

public interface TotalDashBoardService extends CommonService{
	List<TvVodTotalDashBoardVo> getTotalDashBoardTvVodList(DashBoardParamVo param) throws Exception;
	List<TvVodTotalDashBoardVo> getTotalDashBoardPooqVodList(DashBoardParamVo param) throws Exception;
	List<TvVodTotalDashBoardVo> getTotalDashBoardTvVodListForCseq(DashBoardParamVo param) throws Exception;
	List<TvVodTotalDashBoardVo> getTotalDashBoardTopViewRating20(DashBoardParamVo param) throws Exception;
	
	
	List<TvVodTotalDashBoardVo> getPooqRealList(DashBoardParamVo param) throws Exception;
	
	
	String getSmrClipCnt(DashBoardParamVo param) throws Exception;
	
	List<TvVodTotalDashBoardVo> getTvVodVCntList(DashBoardParamVo param) throws Exception;
}
