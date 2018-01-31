package kr.co.kbs.distribute.statusboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.statusboard.PointStatusBoardMapper;
import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.media.vo.PooqVodVo;
import kr.co.kbs.distribute.media.vo.SMRClipVo;
import kr.co.kbs.distribute.media.vo.TvVodVo;
import kr.co.kbs.distribute.statusboard.service.PointStatusBoardService;
import kr.co.kbs.distribute.statusboard.vo.RateRankVo;

@Service
public class PointStatusBoardServiceImpl implements PointStatusBoardService{
	
	@Autowired
	PointStatusBoardMapper mapper;

	@Override
	public List<TvVodVo> getTvVodTopFiveList(MediaParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTvVodTopFiveList(param);
	}

	@Override
	public List<PooqVodVo> getPooqVodFiveList(MediaParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectPooqVodFiveList(param);
	}

	@Override
	public List<SMRClipVo> getSmrClipTopFiveList(MediaParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectSmrClipTopFiveList(param);
	}

	@Override
	public List<RateRankVo> getRatingTopFiveList(MediaParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectRatingTopFiveList(param);
	}

	@Override
	public List<RateRankVo> getPooqLiveTopFiveList(MediaParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectPooqLiveTopFiveList(param);
	}
	
	
}
