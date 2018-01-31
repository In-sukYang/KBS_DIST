package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kbs.distribute.mapper.program.ProgramExpMapper;
import kr.co.kbs.distribute.program.service.ProgramExpService;
import kr.co.kbs.distribute.program.vo.ProgramExpVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Transactional
@Service
public class ProgramServiceImpl implements ProgramExpService {
	
	@Autowired
	ProgramExpMapper mapper;

	@Override
	public List<ProgramExpVo> getProgramExpList(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectProgramExpList(param);
	}

	@Override
	public void deleteProgramExp(ProgramParamVo param) throws Exception {
		mapper.deleteProgramExp(param);
		
	}

	@Override
	public void saveProgramExp(ProgramExpVo param) throws Exception {
		mapper.insertProgramExp(param);
		
	}
	
	
	

}
