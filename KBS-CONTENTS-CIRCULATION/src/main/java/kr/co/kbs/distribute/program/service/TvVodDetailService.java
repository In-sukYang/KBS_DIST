package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.TvVodDetailVo;

public interface TvVodDetailService extends CommonService {
	List<TvVodDetailVo> getTvVodDatailList1(ProgramParamVo param) throws Exception;
	List<TvVodDetailVo> getTvVodDatailList2(ProgramParamVo param) throws Exception;
}
