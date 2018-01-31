package kr.co.kbs.distribute.media.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.util.CastUtil;
import kr.co.kbs.distribute.mapper.media.LiveBroadMapper;
import kr.co.kbs.distribute.media.service.LiveBroadService;
import kr.co.kbs.distribute.media.vo.LiveBroadVo;
import kr.co.kbs.distribute.media.vo.MediaParamVo;

@Service
public class LiveBroadServiceImpl implements LiveBroadService{
	
	@Autowired
	LiveBroadMapper mapper;
	
	@Override
	public List<LiveBroadVo> getLiveBroadTopList(MediaParamVo param, List<CodeVo> channelList, Model model) throws Exception {

		List<LiveBroadVo> list = new ArrayList<LiveBroadVo>();
		
		if(param.getChannel() == null) {
			List<String> channel = new ArrayList<String>(); 
			for(int k =0; k < channelList.size(); k++){
				channel.add(channelList.get(k).getCodeId());
			}
			param.setChannel(channel);
		}
		String ageType = param.getAgeType();
		for(int k=0; k<param.getChannel().size(); k++) {
			list = new ArrayList<LiveBroadVo>();
			param.setChannelId(param.getChannel().get(k).toString());
			param.setAgeType(ageType);
			
			if(param.getAgeType().equals("01")){
				param.setAgeType("02");
			//	List<LiveBroadVo> voList = new ArrayList<LiveBroadVo>();
				list.addAll(mapper.selectLiveBroadTopList(param));
				param.setAgeType("03");
				list.addAll(mapper.selectLiveBroadTopList(param));
				list.removeAll(Collections.singleton(null));
				param.setAgeType("04");
				list.addAll(mapper.selectLiveBroadTopList(param));
				list.removeAll(Collections.singleton(null));
				param.setAgeType("05");
				list.addAll(mapper.selectLiveBroadTopList(param));
				list.removeAll(Collections.singleton(null));
				param.setAgeType("06");
				list.addAll(mapper.selectLiveBroadTopList(param));
				list.removeAll(Collections.singleton(null));
					
			}else {
				list = mapper.selectLiveBroadTopList(param);
				list.removeAll(Collections.singleton(null));
			}
			
			
			if(!list.isEmpty()){
				for(int i =0; i < channelList.size(); i++) {
					if(param.getChannel().get(k).toString().equals(channelList.get(i).getCodeId())) {
						model.addAttribute(channelList.get(i).getCodeNm(), list);
					}
				}
			}
		}
		
		return null;
	}
	
	
	@Override
	public List<LiveBroadVo> getLiveBroadTotalTopList(MediaParamVo param, List<CodeVo> channelList, Model model) throws Exception {

		List<LiveBroadVo> list = new ArrayList<LiveBroadVo>();
		
		if(param.getChannel() == null) {
			List<String> channel = new ArrayList<String>(); 
			for(int k =0; k < channelList.size(); k++){
				channel.add(channelList.get(k).getCodeId());
			}
			param.setChannel(channel);
		}
		String ageType = param.getAgeType();
		
		int maxCnt=0;
		double maxRate=0.0;
		
		if(param.getIndexGubun().equals("01")) {
			for(int k=0; k<param.getChannel().size(); k++) {
				list = new ArrayList<LiveBroadVo>();
				param.setChannelId(param.getChannel().get(k).toString());
				param.setAgeType(ageType);
				
				list = mapper.selectLiveBroadTotalTopList(param);
				list.removeAll(Collections.singleton(null));
				
				for(int i=0;i<list.size();i++) {
					double mrate = CastUtil.toDbl(list.get(i).getTotalRate());
					if(maxRate<mrate) {
						maxRate = mrate;
					}
					int mcnt =list.get(i).getTotalVcnt();
					if(maxCnt<mcnt) {
						maxCnt = mcnt;
					}
				}
				if(!list.isEmpty()){
					for(int i =0; i < channelList.size(); i++) {
						if(param.getChannel().get(k).toString().equals(channelList.get(i).getCodeId())) {
							model.addAttribute(channelList.get(i).getCodeId(), list);
						}
					}
				}
			}
		}else {
			list = new ArrayList<LiveBroadVo>();
			param.setChannelId("");
			param.setAgeType(ageType);
			
			list = mapper.selectLiveBroadTotalTopList(param);
			list.removeAll(Collections.singleton(null));
			
			for(int i=0;i<list.size();i++) {
				double mrate = CastUtil.toDbl(list.get(i).getTotalRate());
				if(maxRate<mrate) {
					maxRate = mrate;
				}
				int mcnt =list.get(i).getTotalVcnt();
				if(maxCnt<mcnt) {
					maxCnt = mcnt;
				}
			}
			if(!list.isEmpty()){
				model.addAttribute("TOTALINDEX", list);
			}
		}
		
		model.addAttribute("maxRate",Math.ceil(maxRate));
		model.addAttribute("maxCnt",(maxCnt/1000)*1000);
		
		return null;
	}
	
}
