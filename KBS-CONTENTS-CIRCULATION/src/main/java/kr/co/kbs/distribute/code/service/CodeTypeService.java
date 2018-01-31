package kr.co.kbs.distribute.code.service;

import java.util.List;

import kr.co.kbs.distribute.code.vo.CodeParamVo;
import kr.co.kbs.distribute.code.vo.CodeTypeVo;
import kr.co.kbs.distribute.common.service.CommonService;

public interface CodeTypeService extends CommonService{
	List<CodeTypeVo> getCodeTypeList(CodeParamVo param) throws Exception;
	int getTotalRecords(CodeParamVo param) throws Exception;
	void saveCodeType(CodeTypeVo param) throws Exception;
	void deleteCodeType(CodeTypeVo param) throws Exception;
}
