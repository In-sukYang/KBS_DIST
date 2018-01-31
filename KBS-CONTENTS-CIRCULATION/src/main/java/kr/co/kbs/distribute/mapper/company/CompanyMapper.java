package kr.co.kbs.distribute.mapper.company;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.company.vo.CompanyParamVo;
import kr.co.kbs.distribute.company.vo.CompanyVo;

@Repository
public interface CompanyMapper {

	List<CompanyVo> selectCompanyList(CompanyParamVo param) throws SQLException;
	List<CompanyVo> selectCompanyListNoPage(CompanyParamVo param) throws SQLException;
	int selectTotalRecords(CompanyParamVo param) throws SQLException;
	void insertCompany(CompanyVo param) throws SQLException;
	void updateCompany(CompanyVo param) throws SQLException;
	int selectCSeq(CompanyParamVo param) throws SQLException;
}
