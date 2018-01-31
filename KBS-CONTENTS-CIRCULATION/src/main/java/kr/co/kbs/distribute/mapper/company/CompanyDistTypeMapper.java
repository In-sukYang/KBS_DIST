package kr.co.kbs.distribute.mapper.company;

import java.sql.SQLException;
import java.util.List;

import kr.co.kbs.distribute.company.vo.CompanyDistTypeVo;
import kr.co.kbs.distribute.company.vo.CompanyParamVo;
import kr.co.kbs.distribute.company.vo.CompanyVo;

public interface CompanyDistTypeMapper {
	
	List<CompanyDistTypeVo> selectCompanyDistTypeList(CompanyVo param) throws SQLException;
	int selectTotalRecords() throws SQLException;
	void insertCompanyDistType(CompanyDistTypeVo param) throws SQLException;
	void updateCompanyDistType(CompanyDistTypeVo param) throws SQLException;
	void deleteComapnyDist(CompanyParamVo param) throws SQLException;
}
