package kr.co.kbs.distribute.media.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.media.SMRClipMapper;
import kr.co.kbs.distribute.media.service.SmrClipService;
import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.media.vo.SMRClipVo;

@Service
public class SmrClipserviceImpl implements SmrClipService{

	@Autowired
	SMRClipMapper mapper;
	
	@Override
	public List<SMRClipVo> getSmrClipTopList(MediaParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectSmrClipTopList(param);
	}

}
