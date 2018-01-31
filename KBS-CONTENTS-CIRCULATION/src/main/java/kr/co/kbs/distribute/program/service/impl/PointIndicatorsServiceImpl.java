package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.program.PointIndicatorsMapper;
import kr.co.kbs.distribute.program.service.PointIndicatorsService;
import kr.co.kbs.distribute.program.vo.PointIndicatorsVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Service
public class PointIndicatorsServiceImpl implements PointIndicatorsService{
	
	@Autowired
	PointIndicatorsMapper mapper;
	
	@Override
	public List<PointIndicatorsVo> getProgramPointIndicatorsList(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectProgramPointIndicatorsList(param);
	}

	@Override
	public List<PointIndicatorsVo> getProgramPointIndicatorsList2(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectProgramPointIndicatorsList2(param);
	}
	
}
