package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.program.ProgramCommonMapper;
import kr.co.kbs.distribute.program.service.ProgramCommonService;
import kr.co.kbs.distribute.program.vo.ProgramCommonVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Service
public class ProgramCommonServiceImpl implements ProgramCommonService{

	@Autowired
	ProgramCommonMapper mapper;
	
	@Override
	public List<ProgramCommonVo> getProgramVodcntList(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectProgramVodcntList(param);
	}

}
