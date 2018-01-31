package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.ProgramCommonVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface ProgramCommonService extends CommonService{
	List<ProgramCommonVo> getProgramVodcntList(ProgramParamVo param) throws Exception;
}
