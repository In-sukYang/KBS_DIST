package kr.co.kbs.distribute.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.common.service.MinMaxDayService;
import kr.co.kbs.distribute.common.vo.MinMaxDayVo;
import kr.co.kbs.distribute.mapper.common.MinMaxDayMapper;

@Service
public class MinMaxDayServiceImpl implements MinMaxDayService{

	@Autowired
	MinMaxDayMapper mapper;
	
	@Override
	public MinMaxDayVo getUsageStatProgramMinMaxDay(String param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectUsageStatProgramMinMaxDay(param);
	}

	@Override
	public MinMaxDayVo getClipMinMaxDay() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectClipMinMaxDay();
	}

	@Override
	public MinMaxDayVo getDcViewRatingMinMaxDay() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectDcViewRatingMinMaxDay();
	}

	@Override
	public MinMaxDayVo getContentsMinMaxDay() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectContentsMinMaxDay();
	}
	
}
