package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.mapper.program.OrgProgramExpMapper;
import kr.co.kbs.distribute.program.service.OrgProgramExpService;
import kr.co.kbs.distribute.program.vo.OrgProgramExpVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Service
public class OrgProgramExpServiceImpl implements OrgProgramExpService{

	@Autowired
	OrgProgramExpMapper mapper;
	
	@Override
	public List<OrgProgramExpVo> getOrgProgramExpList(ProgramParamVo param) throws Exception {
		
		return mapper.selectOrgProgramExpList(param);
	}

	@Transactional
	@Override
	public void saveOrgProgramExp(OrgProgramExpVo param) throws Exception {
		
		if(StringUtils.isEmpty(param.getOpeSeq()) || param.getOpeSeq() == 0) {
			mapper.insertOrgProgramExp(param);
		}else {
			mapper.updateOrgProgramExp(param);
		}
	}

	@Override
	public void deleteOrgProgramExp(ProgramParamVo param) throws Exception {
		
		mapper.deleteOrgProgramExp(param);
	}

	@Override
	public int getTotalRecords(ProgramParamVo param) throws Exception {
		return mapper.selectTotalRecords(param);
	}

}
