package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.ClipDetailVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface ClipDetailService extends CommonService{
	List<ClipDetailVo> getClipDatailList1(ProgramParamVo param) throws Exception;
	List<ClipDetailVo> getClipDatailList2(ProgramParamVo param) throws Exception;
}
