package kr.co.kbs.distribute.rate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.mapper.rate.MinuteRateMapper;
import kr.co.kbs.distribute.rate.service.MinuteRateService;
import kr.co.kbs.distribute.rate.vo.MinuteRateVo;
import kr.co.kbs.distribute.rate.vo.RateParamVo;

@Service
public class MinuteRateServiceImpl implements MinuteRateService {

	@Autowired
	MinuteRateMapper mapper;
	
	@Override
	public List<MinuteRateVo> getMinuteRateList(RateParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectMinuteRateList(param);
	}

	@Override
	public int getTotalRecords(RateParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalRecords(param);
	}

	@Override
	public void saveMinuteRate(MinuteRateVo param) throws Exception {
		if(StringUtils.isEmpty(param.getMvrSeq()) || param.getMvrSeq() == 0) {
			mapper.insertMinuteRate(param);
		}else {
			mapper.updateMinuteRate(param);
		}
		
	}


}
