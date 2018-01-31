package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.mapper.program.ClipMapper;
import kr.co.kbs.distribute.program.service.ClipService;
import kr.co.kbs.distribute.program.vo.ClipVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Service
public class ClipServiceImpl implements ClipService {
	
	@Autowired
	ClipMapper mapper;
	
	@Override
	public List<ClipVo> getClipList(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectClipList(param);
	}

	@Override
	public void saveClip(ClipVo param) throws Exception {
		
		if(StringUtils.isEmpty(param.getClipSeq()) || param.getClipSeq() == 0) {
			mapper.insertClip(param);
		}else {
			mapper.updateClip(param);
		}
	}

	@Override
	public int getTotalRecords(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalRecords(param);
	}

}
