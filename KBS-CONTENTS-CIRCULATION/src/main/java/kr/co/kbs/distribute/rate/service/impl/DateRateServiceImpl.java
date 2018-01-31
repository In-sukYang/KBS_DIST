package kr.co.kbs.distribute.rate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.mapper.rate.DateRateMapper;
import kr.co.kbs.distribute.rate.service.DateRateService;
import kr.co.kbs.distribute.rate.vo.DayRateVo;
import kr.co.kbs.distribute.rate.vo.RateParamVo;

@Service
public class DateRateServiceImpl implements DateRateService{

	@Autowired
	DateRateMapper mapper;
	
	@Override
	public List<DayRateVo> getDateRateList(RateParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectDateRateList(param);
	}

	@Override
	public int getTotalRecords(RateParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalRecords(param);
	}

	@Override
	public void saveDateRate(DayRateVo param) throws Exception {
		if(StringUtils.isEmpty(param.getDvrSeq()) || param.getDvrSeq() == 0) {
			mapper.insertDateRate(param);
		}else {
			mapper.updateDateRate(param);
		}
	}
}
