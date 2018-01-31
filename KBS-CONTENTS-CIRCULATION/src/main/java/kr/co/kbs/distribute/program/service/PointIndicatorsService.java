package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.PointIndicatorsVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface PointIndicatorsService extends CommonService{
	List<PointIndicatorsVo> getProgramPointIndicatorsList(ProgramParamVo param) throws Exception;
	List<PointIndicatorsVo> getProgramPointIndicatorsList2(ProgramParamVo param) throws Exception;
}
