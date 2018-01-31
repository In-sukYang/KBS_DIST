package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.program.vo.OrgProgramExpVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Repository
public interface OrgProgramExpMapper {
	
	List<OrgProgramExpVo> selectOrgProgramExpList(ProgramParamVo param) throws SQLException;
	int selectTotalRecords(ProgramParamVo param) throws SQLException;
	void insertOrgProgramExp(OrgProgramExpVo param) throws SQLException;
	void updateOrgProgramExp(OrgProgramExpVo param) throws SQLException;
	void deleteOrgProgramExp(ProgramParamVo param) throws SQLException;
}
