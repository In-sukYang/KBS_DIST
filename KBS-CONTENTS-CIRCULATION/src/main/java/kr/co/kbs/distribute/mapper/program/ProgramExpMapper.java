package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.program.vo.ProgramExpVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Repository
public interface ProgramExpMapper {
	void deleteProgramExp(ProgramParamVo param) throws Exception;
	void insertProgramExp(ProgramExpVo param) throws Exception;
	List<ProgramExpVo> selectProgramExpList(ProgramParamVo param) throws SQLException;
}
