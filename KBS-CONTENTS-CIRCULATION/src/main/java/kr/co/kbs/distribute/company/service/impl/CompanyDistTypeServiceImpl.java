package kr.co.kbs.distribute.company.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import kr.co.kbs.distribute.company.service.CompanyDistTypeService;
import kr.co.kbs.distribute.company.vo.CompanyDistTypeVo;
import kr.co.kbs.distribute.company.vo.CompanyParamVo;
import kr.co.kbs.distribute.company.vo.CompanyVo;
import kr.co.kbs.distribute.mapper.company.CompanyDistTypeMapper;

@Service
public class CompanyDistTypeServiceImpl implements CompanyDistTypeService {

	@Autowired
	CompanyDistTypeMapper mapper;
	

	@Transactional(readOnly = true)
	@Override
	public List<CompanyDistTypeVo> getCompanyDistTypeList(CompanyVo param) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectCompanyDistTypeList(param);
	}

	@Transactional
	@Override
	public void saveCompanyDistType(CompanyDistTypeVo param) throws Exception {
		
		if(StringUtils.isEmpty(param.getCdySeq()) || param.getCdySeq() == 0) {
			mapper.insertCompanyDistType(param);
		}else {
			mapper.updateCompanyDistType(param);
		}
	}

	@Override
	public void deleteComapnyDist(CompanyParamVo param) throws Exception {
		mapper.deleteComapnyDist(param);
	}

}
