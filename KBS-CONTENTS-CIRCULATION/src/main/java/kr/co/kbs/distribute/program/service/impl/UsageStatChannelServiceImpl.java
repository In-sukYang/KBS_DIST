package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.channel.vo.UsageStatChannelVo;
import kr.co.kbs.distribute.mapper.program.UsageStatChannelMapper;
import kr.co.kbs.distribute.program.service.UsageStatChannelService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Service
public class UsageStatChannelServiceImpl implements UsageStatChannelService{
	
	@Autowired
	UsageStatChannelMapper mapper;
	
	@Override
	public List<UsageStatChannelVo> getUsageStatChannelList(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectUsageStatChannelList(param);
	}

	@Override
	public int getTotalRecords(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalRecords(param);
	}

	@Override
	public void saveUsageStatChannel(UsageStatChannelVo param) throws Exception {
		if(StringUtils.isEmpty(param.getUscSeq()) || param.getUscSeq() == 0) {
			mapper.insertUsageStatChannel(param);
		}else {
			mapper.updateUsageStatChannel(param);
		}
	}
}
