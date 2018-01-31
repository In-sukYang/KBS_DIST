package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import kr.co.kbs.distribute.program.vo.ClipVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface ClipMapper {
	List<ClipVo> selectClipList(ProgramParamVo param) throws Exception;
	int selectTotalRecords(ProgramParamVo param) throws SQLException;
	void insertClip(ClipVo param) throws Exception;
	void updateClip(ClipVo param) throws Exception;

}
