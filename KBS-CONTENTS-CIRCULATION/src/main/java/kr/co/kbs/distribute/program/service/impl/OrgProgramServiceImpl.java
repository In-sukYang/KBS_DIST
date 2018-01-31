package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.mapper.program.OrgProgramMapper;
import kr.co.kbs.distribute.program.service.OrgProgramService;
import kr.co.kbs.distribute.program.vo.OrgProgramVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Service
public class OrgProgramServiceImpl implements OrgProgramService{

	@Autowired
	OrgProgramMapper mapper;
	
	@Override
	public List<OrgProgramVo> getOrgProgramList(ProgramParamVo param) throws Exception {
		
		log.debug("getOrgProgramList Start ===================>");
		
		return mapper.selectOrgProgramList(param);
	}

	@Transactional
	@Override
	public void saveOrgProgram(OrgProgramVo param) throws Exception {
		
		log.debug("saveOrgProgram Start ===================>");
		
		if(StringUtils.isEmpty(param.getOpSeq()) || param.getOpSeq() == 0) {
			log.debug("insert orgProgram");
			mapper.insertOrgProgram(param);
		}else {
			log.debug("update orgProgram");
			mapper.updateOrgProgram(param);
		}
		log.debug("saveOrgProgram End ===================>");
	}

	@Override
	public int getTotalRecords(ProgramParamVo param) throws Exception {
		return mapper.selectTotalRecords(param);
	}
}
