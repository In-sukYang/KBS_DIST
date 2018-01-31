package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.ProgramVo;

public interface ProgramService extends CommonService{
	
	List<ProgramVo> getProgramList(ProgramParamVo param) throws Exception;
	List<ProgramVo> getProgramListLayer(ProgramParamVo param) throws Exception;
	
	int getTotalRecords(ProgramParamVo param) throws Exception; 
	void saveProgram(ProgramVo param) throws Exception;
	
}
