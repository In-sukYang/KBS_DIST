package kr.co.kbs.distribute.dashboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.dashboard.service.TotalDashBoardService;
import kr.co.kbs.distribute.dashboard.vo.DashBoardParamVo;
import kr.co.kbs.distribute.dashboard.vo.TvVodTotalDashBoardVo;
import kr.co.kbs.distribute.mapper.dashboard.TotalDashBoardMapper;

@Service
public class TotalDashBoardServiceImpl implements TotalDashBoardService{

	@Autowired
	TotalDashBoardMapper mapper;
	
	@Override
	public List<TvVodTotalDashBoardVo> getTotalDashBoardTvVodList(DashBoardParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalDashBoardTvVodList(param);
	}

	@Override
	public List<TvVodTotalDashBoardVo> getTotalDashBoardPooqVodList(DashBoardParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalDashBoardPooqVodList(param);
	}

	@Override
	public List<TvVodTotalDashBoardVo> getTotalDashBoardTvVodListForCseq(DashBoardParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalDashBoardTvVodListForCseq(param);
	}

	@Override
	public List<TvVodTotalDashBoardVo> getTotalDashBoardTopViewRating20(DashBoardParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalDashBoardTopViewRating20(param);
	}
	
	@Override
	public String getSmrClipCnt(DashBoardParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getSmrClipCnt(param);
	}
	
	@Override
	public List<TvVodTotalDashBoardVo> getPooqRealList(DashBoardParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getPooqRealList(param);
	}
	
	
	@Override
	public List<TvVodTotalDashBoardVo> getTvVodVCntList(DashBoardParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.getTvVodVCntList(param);
	}
	

}
