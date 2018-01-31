package kr.co.kbs.distribute.code.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.kbs.distribute.code.service.CodeTypeService;
import kr.co.kbs.distribute.code.vo.CodeParamVo;
import kr.co.kbs.distribute.code.vo.CodeTypeVo;
import kr.co.kbs.distribute.mapper.code.CodeTypeMapper;

@Service
public class CodeTypeServiceImpl implements CodeTypeService{

	@Autowired
	CodeTypeMapper mapper;
	
	@Override
	public List<CodeTypeVo> getCodeTypeList(CodeParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectCodeTypeList(param);
	}

	@Transactional
	@Override
	public void saveCodeType(CodeTypeVo param) throws Exception {

		CodeParamVo vo = new CodeParamVo();
		vo.setSearchType("01");
		vo.setSearchValue(param.getCodeType());
		
		int cnt = getTotalRecords(vo);
		if(cnt == 0){
			mapper.insertCodeType(param);
		}else {
			mapper.updateCodeType(param);
		}
	}

	@Override
	public int getTotalRecords(CodeParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectTotalRecords(param);
	}

	@Transactional
	@Override
	public void deleteCodeType(CodeTypeVo param) throws Exception {
		mapper.deleteCodeType(param);
		
	}

}
