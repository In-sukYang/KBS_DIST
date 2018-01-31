package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.PooqVodDetailVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface PooqVodDetailService extends CommonService{
	List<PooqVodDetailVo> getPooqVodDatailList1(ProgramParamVo param) throws Exception;
	List<PooqVodDetailVo> getPooqVodDatailList2(ProgramParamVo param) throws Exception;
}
