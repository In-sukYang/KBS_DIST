package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.UsageStatProgramVo;

@Repository
public interface UsageStatProgramMapper {
	
	List<String> selectDateYear() throws SQLException;
	List<UsageStatProgramVo> selectUsageStatProgramList(ProgramParamVo param) throws SQLException;
	int selectTotalRecords(ProgramParamVo param) throws SQLException; 
	void insertUsageStatProgram(UsageStatProgramVo param) throws SQLException;
	void updateUsageStatProgram(UsageStatProgramVo param) throws SQLException;
}
