package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.OrgProgramVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface OrgProgramService extends CommonService{
	List<OrgProgramVo> getOrgProgramList(ProgramParamVo param) throws Exception;
	int getTotalRecords(ProgramParamVo param) throws Exception; 
	void saveOrgProgram(OrgProgramVo param) throws Exception;
}
