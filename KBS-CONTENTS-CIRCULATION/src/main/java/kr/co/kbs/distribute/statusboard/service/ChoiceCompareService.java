package kr.co.kbs.distribute.statusboard.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.statusboard.vo.SameTimeCompareVo;
import kr.co.kbs.distribute.statusboard.vo.StatusBoardParamVo;

public interface ChoiceCompareService extends CommonService{
	List<SameTimeCompareVo> getChoiceCompareList(StatusBoardParamVo param) throws Exception;
}
