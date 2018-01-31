package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import kr.co.kbs.distribute.program.vo.ProgramCommonVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface ProgramCommonMapper {
	List<ProgramCommonVo> selectProgramVodcntList(ProgramParamVo param) throws SQLException;
}
