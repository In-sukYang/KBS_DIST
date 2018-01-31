package kr.co.kbs.distribute.mapper.program;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.program.vo.ProgramContentVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Repository
public interface ProgramContentMapper {
	List<ProgramContentVo> selectProgramContentList(ProgramParamVo param) throws Exception;
	int selectTotalRecords(ProgramParamVo param) throws Exception; 
	void insertProgramContent(ProgramContentVo param) throws Exception;
	void updateProgramContent(ProgramContentVo param) throws Exception;
}
