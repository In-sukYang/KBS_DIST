package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.UsageStatProgramVo;

public interface UsageStatProgramService extends CommonService {
	List<UsageStatProgramVo> getUsageStatProgramList(ProgramParamVo param) throws Exception;
	int getTotalRecords(ProgramParamVo param) throws Exception; 
	void saveUsageStatProgram(UsageStatProgramVo param) throws Exception;
	List<String> getDateYear() throws Exception;
}
