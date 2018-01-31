package kr.co.kbs.distribute.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.code.service.CodeService;
import kr.co.kbs.distribute.code.vo.CodeParamVo;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.mapper.code.CodeMapper;

@Service
public class CodeServiceImpl implements CodeService {
	
	@Autowired
	CodeMapper mapper;
	
	@Transactional(readOnly = true)
	@Override
	public List<CodeVo> getCodeList(CodeVo param) throws Exception{
		// TODO Auto-generated method stub
		return mapper.selectCodeListByCodeVo(param);
	}

	@Transactional(readOnly = true)
	@Override
	public int getTotalRecords(CodeParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalRecords(param);
	}

	@Transactional(readOnly = true)
	@Override
	public List<CodeVo> getCodeList(CodeParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectCodeListByCodeParamVo(param);
	}

	@Transactional
	@Override
	public void saveCode(CodeVo param) throws Exception {
		
		if(StringUtils.isEmpty(param.getCodeSeq()) || param.getCodeSeq() == 0) {
			mapper.insertCode(param);
		}else {
			mapper.updateCode(param);
		}
	}

	@Override
	public void deleteCode(CodeVo param) throws Exception {
		mapper.deleteCode(param);
		
	}
	
	

}
