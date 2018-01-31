package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.mapper.program.UsageStatProgramMapper;
import kr.co.kbs.distribute.program.service.UsageStatProgramService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.UsageStatProgramVo;

@Service
public class UsageStatProgramServiceImpl implements  UsageStatProgramService{
	@Autowired
	UsageStatProgramMapper mapper;
	
	@Transactional(readOnly = true)
	@Override
	public List<String> getDateYear() throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectDateYear();
	}

	@Override
	public int getTotalRecords(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalRecords(param);
	}

	@Override
	public void saveUsageStatProgram(UsageStatProgramVo param) throws Exception {
		
		if(StringUtils.isEmpty(param.getUspSeq()) || param.getUspSeq() ==0 ){
			mapper.insertUsageStatProgram(param);
		}else { 
			mapper.updateUsageStatProgram(param);
		}
	}

	@Override
	public List<UsageStatProgramVo> getUsageStatProgramList(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectUsageStatProgramList(param);
	}
}
