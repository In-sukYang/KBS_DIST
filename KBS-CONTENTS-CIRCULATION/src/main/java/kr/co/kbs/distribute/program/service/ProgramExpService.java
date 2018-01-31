package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.ProgramExpVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface ProgramExpService extends CommonService{
	
	List<ProgramExpVo> getProgramExpList(ProgramParamVo param) throws Exception;
	void deleteProgramExp(ProgramParamVo param) throws Exception;
	void saveProgramExp(ProgramExpVo param) throws Exception;
	
}
