package kr.co.kbs.distribute.company.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.company.vo.CompanyDistTypeVo;
import kr.co.kbs.distribute.company.vo.CompanyParamVo;
import kr.co.kbs.distribute.company.vo.CompanyVo;

public interface CompanyDistTypeService extends CommonService {
	List<CompanyDistTypeVo> getCompanyDistTypeList(CompanyVo param) throws Exception;
	void saveCompanyDistType(CompanyDistTypeVo param) throws Exception;
	void deleteComapnyDist(CompanyParamVo param) throws Exception;
}
