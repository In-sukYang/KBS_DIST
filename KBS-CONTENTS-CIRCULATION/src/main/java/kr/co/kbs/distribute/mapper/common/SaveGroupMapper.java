package kr.co.kbs.distribute.mapper.common;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.common.vo.FileVo;
import kr.co.kbs.distribute.common.vo.SaveGroupVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Repository
public interface SaveGroupMapper {
	void updateSaveGroup(SaveGroupVo param) throws SQLException;
	int insertSaveGroup(SaveGroupVo param) throws SQLException;
	int selectMaxKey() throws SQLException;
	void deleteSaveGroup(FileVo param) throws SQLException;
	List<SaveGroupVo> selectSaveGroup(ProgramParamVo param) throws SQLException;
	int selectTotalRecords(ProgramParamVo param) throws SQLException;
}
