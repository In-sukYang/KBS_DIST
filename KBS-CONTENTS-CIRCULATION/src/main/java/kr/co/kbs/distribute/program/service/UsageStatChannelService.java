package kr.co.kbs.distribute.program.service;

import java.util.List;

import kr.co.kbs.distribute.channel.vo.UsageStatChannelVo;
import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface UsageStatChannelService extends CommonService{
	List<UsageStatChannelVo> getUsageStatChannelList(ProgramParamVo param) throws Exception;
	int getTotalRecords(ProgramParamVo param) throws Exception; 
	void saveUsageStatChannel(UsageStatChannelVo param) throws Exception;
}
