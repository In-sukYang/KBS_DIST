package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.ProgramContentVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface ProgramContentService extends CommonService{
	List<ProgramContentVo> getProgramContentList(ProgramParamVo param) throws Exception;
	int getTotalRecords(ProgramParamVo param) throws Exception; 
	void saveProgramContent(ProgramContentVo param) throws Exception;
}
