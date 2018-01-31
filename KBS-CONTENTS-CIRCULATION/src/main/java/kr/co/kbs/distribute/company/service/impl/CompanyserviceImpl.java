package kr.co.kbs.distribute.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.company.service.CompanyService;
import kr.co.kbs.distribute.company.vo.CompanyParamVo;
import kr.co.kbs.distribute.company.vo.CompanyVo;
import kr.co.kbs.distribute.mapper.company.CompanyMapper;

@Service
public class CompanyserviceImpl implements CompanyService{

	@Autowired
	CompanyMapper mapper;
	
	@Transactional(readOnly=true)
	@Override
	public List<CompanyVo> getCompanyList(CompanyParamVo param) throws Exception {
		log.debug("getCompanyList Start ===================>");
		
		return mapper.selectCompanyList(param);
		
	}
	
	@Transactional
	@Override
	public void saveCompnay(CompanyVo param) throws Exception {
		
		log.debug("saveCompnay Start ===================>");
		if(StringUtils.isEmpty(param.getCSeq()) || param.getCSeq() == 0) {
			log.debug("Compnay insert");
			mapper.insertCompany(param);
		}else {
			log.debug("Compnay update");
			mapper.updateCompany(param);
		}
		
		log.debug("saveCompnay End ===================>");
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public int getTotalRecords(CompanyParamVo param) throws Exception {
		return mapper.selectTotalRecords(param);
	}

	@Transactional(readOnly=true)
	@Override
	public List<CompanyVo> getCompanyListNoPage(CompanyParamVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectCompanyListNoPage(param);
	}
}
