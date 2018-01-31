package kr.co.kbs.distribute.rate.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.rate.vo.DayRateVo;
import kr.co.kbs.distribute.rate.vo.RateParamVo;

public interface DateRateService extends CommonService{
	List<DayRateVo> getDateRateList(RateParamVo param) throws Exception;
	int getTotalRecords(RateParamVo param) throws Exception;
	void saveDateRate(DayRateVo param) throws Exception;
}
