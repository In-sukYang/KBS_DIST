package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.mapper.program.ProgramContentMapper;
import kr.co.kbs.distribute.program.service.ProgramContentService;
import kr.co.kbs.distribute.program.vo.ProgramContentVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Service
public class ProgramContentServiceImpl implements ProgramContentService{

	@Autowired
	ProgramContentMapper mapper;
	
	@Transactional(readOnly = true)
	@Override
	public List<ProgramContentVo> getProgramContentList(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectProgramContentList(param);
	}

	@Transactional(readOnly = true)
	@Override
	public int getTotalRecords(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalRecords(param);
	}

	@Transactional
	@Override
	public void saveProgramContent(ProgramContentVo param) throws Exception {
		
		if(StringUtils.isEmpty(param.getPcSeq())  || param.getPcSeq() == 0 ) {
			mapper.insertProgramContent(param);
		}else {
			mapper.updateProgramContent(param);
		}
	}



}
