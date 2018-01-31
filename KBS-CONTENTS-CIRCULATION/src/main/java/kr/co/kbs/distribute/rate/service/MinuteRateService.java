package kr.co.kbs.distribute.rate.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.rate.vo.MinuteRateVo;
import kr.co.kbs.distribute.rate.vo.RateParamVo;

public interface MinuteRateService extends CommonService{
	List<MinuteRateVo> getMinuteRateList(RateParamVo param) throws Exception;
	int getTotalRecords(RateParamVo param) throws Exception;
	void saveMinuteRate(MinuteRateVo param) throws Exception;
}
