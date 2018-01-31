package kr.co.kbs.distribute.statusboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.statusboard.SameTimeCompareMapper;
import kr.co.kbs.distribute.statusboard.service.SameTimeCompareService;
import kr.co.kbs.distribute.statusboard.vo.SameTimeCompareVo;
import kr.co.kbs.distribute.statusboard.vo.StatusBoardParamVo;

@Service
public class SameTimeCompareServiceImpl implements SameTimeCompareService{
	
	@Autowired
	SameTimeCompareMapper mapper;

	@Override
	public List<SameTimeCompareVo> getSameTimeCompareList(StatusBoardParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectSameTimeCompareList(param);
	}
	
	
}
