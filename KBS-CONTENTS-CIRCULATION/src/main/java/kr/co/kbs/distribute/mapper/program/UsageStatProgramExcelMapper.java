package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.common.vo.FileVo;
import kr.co.kbs.distribute.common.vo.SaveGroupVo;
import kr.co.kbs.distribute.program.vo.StandardProgramExcelVo;

@Repository
public interface UsageStatProgramExcelMapper {
	void insertExcelTmpfile(FileVo param) throws SQLException;
	void deleteExcelTmpData(FileVo param) throws SQLException;
	
	void insertSaveData(StandardProgramExcelVo param) throws SQLException;
	
	void deleteUsageStatProgramForExcel(SaveGroupVo param) throws SQLException;
}
