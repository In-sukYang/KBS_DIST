package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.ClipVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface ClipService extends CommonService{
	List<ClipVo> getClipList(ProgramParamVo param) throws Exception;
	int getTotalRecords(ProgramParamVo param) throws Exception; 
	void saveClip(ClipVo param) throws Exception;
}
