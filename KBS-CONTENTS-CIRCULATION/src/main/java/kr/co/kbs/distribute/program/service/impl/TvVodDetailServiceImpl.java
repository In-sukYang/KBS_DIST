package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.program.TvVodDetailMapper;
import kr.co.kbs.distribute.program.service.TvVodDetailService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.TvVodDetailVo;

@Service
public class TvVodDetailServiceImpl implements TvVodDetailService{

	@Autowired
	TvVodDetailMapper mapper;
	
	@Override
	public List<TvVodDetailVo> getTvVodDatailList1(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTvVodDatailList1(param);
	}

	@Override
	public List<TvVodDetailVo> getTvVodDatailList2(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTvVodDatailList2(param);
	}

}
