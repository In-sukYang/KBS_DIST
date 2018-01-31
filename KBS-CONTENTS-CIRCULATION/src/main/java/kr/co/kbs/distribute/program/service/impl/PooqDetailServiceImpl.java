package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.program.PooqVodDetailMapper;
import kr.co.kbs.distribute.program.service.PooqVodDetailService;
import kr.co.kbs.distribute.program.vo.PooqVodDetailVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Service
public class PooqDetailServiceImpl implements PooqVodDetailService {

	@Autowired
	PooqVodDetailMapper mapper;
	
	@Override
	public List<PooqVodDetailVo> getPooqVodDatailList1(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectPooqVodDatailList1(param);
	}

	@Override
	public List<PooqVodDetailVo> getPooqVodDatailList2(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectPooqVodDatailList2(param);
	}

}
