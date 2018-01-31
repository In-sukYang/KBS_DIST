package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.program.vo.OrgProgramVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Repository
public interface OrgProgramMapper {
	
	List<OrgProgramVo> selectOrgProgramList(ProgramParamVo param) throws SQLException;
	int selectTotalRecords(ProgramParamVo param) throws SQLException;
	void insertOrgProgram(OrgProgramVo param) throws SQLException;
	void updateOrgProgram(OrgProgramVo param) throws Exception;
	
}
