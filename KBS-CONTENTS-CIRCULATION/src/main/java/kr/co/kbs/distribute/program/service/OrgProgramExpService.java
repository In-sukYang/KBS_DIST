package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.OrgProgramExpVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface OrgProgramExpService extends CommonService{
	
	List<OrgProgramExpVo> getOrgProgramExpList(ProgramParamVo param) throws Exception;
	int getTotalRecords(ProgramParamVo param) throws Exception;  
	void saveOrgProgramExp(OrgProgramExpVo param) throws Exception;
	void deleteOrgProgramExp(ProgramParamVo param) throws Exception;
}
