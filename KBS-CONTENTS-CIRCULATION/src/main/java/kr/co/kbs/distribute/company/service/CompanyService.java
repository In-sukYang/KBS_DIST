package kr.co.kbs.distribute.company.service;

import java.util.List;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.company.vo.CompanyParamVo;
import kr.co.kbs.distribute.company.vo.CompanyVo;

public interface CompanyService extends CommonService{
	
	List<CompanyVo> getCompanyList(CompanyParamVo param) throws Exception;
	List<CompanyVo> getCompanyListNoPage(CompanyParamVo param) throws Exception;
	void saveCompnay(CompanyVo param) throws Exception;
	int getTotalRecords(CompanyParamVo param) throws Exception;  
}
