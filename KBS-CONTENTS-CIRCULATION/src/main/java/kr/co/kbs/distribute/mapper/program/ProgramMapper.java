package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.ProgramVo;

@Repository
public interface ProgramMapper {
	
	List<ProgramVo> selectProgramList(ProgramParamVo param) throws Exception;
	List<ProgramVo> selectProgramListLayer(ProgramParamVo param) throws Exception;
	
	int selectTotalRecords(ProgramParamVo param) throws SQLException;
	void insertProgram(ProgramVo param) throws Exception;
	void updateProgram(ProgramVo param) throws Exception;
}
