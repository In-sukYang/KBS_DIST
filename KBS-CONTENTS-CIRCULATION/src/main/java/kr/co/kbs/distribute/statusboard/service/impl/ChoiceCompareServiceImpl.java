package kr.co.kbs.distribute.statusboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.statusboard.ChoiceCompareMapper;
import kr.co.kbs.distribute.statusboard.service.ChoiceCompareService;
import kr.co.kbs.distribute.statusboard.vo.SameTimeCompareVo;
import kr.co.kbs.distribute.statusboard.vo.StatusBoardParamVo;

@Service
public class ChoiceCompareServiceImpl implements ChoiceCompareService{

	@Autowired
	ChoiceCompareMapper mapper;
	
	@Override
	public List<SameTimeCompareVo> getChoiceCompareList(StatusBoardParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectChoiceCompareList(param);
	}

}
