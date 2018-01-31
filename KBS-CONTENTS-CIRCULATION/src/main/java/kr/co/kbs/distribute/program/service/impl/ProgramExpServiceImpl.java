package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.mapper.program.ProgramMapper;
import kr.co.kbs.distribute.program.service.ProgramService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.ProgramVo;

@Transactional
@Service
public class ProgramExpServiceImpl implements ProgramService {
	
	@Autowired
	ProgramMapper mapper;
	
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Override
	public List<ProgramVo> getProgramList(ProgramParamVo param) throws Exception {
		return mapper.selectProgramList(param);
	}
	
	@Transactional
	@Override
	public void saveProgram(ProgramVo param) throws Exception {

		if(StringUtils.isEmpty(param.getPSeq()) || param.getPSeq() == 0) {
			mapper.insertProgram(param);
		}else {
			mapper.updateProgram(param);
		}
	}
	
	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Override
	public int getTotalRecords(ProgramParamVo param) throws Exception {
		return mapper.selectTotalRecords(param);
	}

	@Override
	public List<ProgramVo> getProgramListLayer(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectProgramListLayer(param);
	}
	

}
