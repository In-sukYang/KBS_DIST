package kr.co.kbs.distribute.media.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.media.TvVodMapper;
import kr.co.kbs.distribute.media.service.TvVodService;
import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.media.vo.TvVodVo;

@Service
public class TvVodServiceImpl implements TvVodService{
	
	@Autowired
	TvVodMapper mapper;
	
	public List<TvVodVo> getTvVodList(MediaParamVo param) throws Exception {
		return mapper.selectTvVodList(param);
	}
}
