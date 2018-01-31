package kr.co.kbs.distribute.program.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.kbs.distribute.mapper.program.ClipDetailMapper;
import kr.co.kbs.distribute.program.service.ClipDetailService;
import kr.co.kbs.distribute.program.vo.ClipDetailVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Service
public class ClipDetailServiceImpl implements ClipDetailService{

	@Autowired
	ClipDetailMapper mapper;
	
	@Override
	public List<ClipDetailVo> getClipDatailList1(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectClipDatailList1(param);
	}

	@Override
	public List<ClipDetailVo> getClipDatailList2(ProgramParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectClipDatailList2(param);
	}

}
