package kr.co.kbs.distribute.media.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.media.PooqVodMapper;
import kr.co.kbs.distribute.media.service.PooqVodService;
import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.media.vo.PooqVodVo;

@Service
public class PooqVodServiceImpl implements PooqVodService{
	
	@Autowired
	PooqVodMapper mapper;
	
	@Override
	public List<PooqVodVo> getPooqVodList(MediaParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectPooqVodList(param);
	}
}
