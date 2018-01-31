package kr.co.kbs.distribute.common.service;

import kr.co.kbs.distribute.common.vo.MinMaxDayVo;

public interface MinMaxDayService extends CommonService{
	MinMaxDayVo getUsageStatProgramMinMaxDay(String param) throws Exception;
	MinMaxDayVo getClipMinMaxDay() throws Exception;
	MinMaxDayVo getDcViewRatingMinMaxDay() throws Exception;
	MinMaxDayVo getContentsMinMaxDay() throws Exception;
}
