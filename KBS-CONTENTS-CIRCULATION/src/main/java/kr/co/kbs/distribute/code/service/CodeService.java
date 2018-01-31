package kr.co.kbs.distribute.code.service;

import java.util.List;

import kr.co.kbs.distribute.code.vo.CodeParamVo;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.service.CommonService;

public interface CodeService extends CommonService{
	List<CodeVo> getCodeList(CodeVo param) throws Exception;
	List<CodeVo> getCodeList(CodeParamVo param) throws Exception;
	void saveCode(CodeVo param) throws Exception;
	void deleteCode(CodeVo param) throws Exception;
	int getTotalRecords(CodeParamVo param) throws Exception;
	
}
