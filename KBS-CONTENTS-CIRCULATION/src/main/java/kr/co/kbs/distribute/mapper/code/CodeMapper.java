package kr.co.kbs.distribute.mapper.code;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.code.vo.CodeParamVo;
import kr.co.kbs.distribute.code.vo.CodeVo;

@Repository
public interface CodeMapper {
	List<CodeVo> selectCodeListByCodeVo(CodeVo param) throws SQLException;
	List<CodeVo> selectCodeListByCodeParamVo(CodeParamVo param) throws SQLException;
	int selectTotalRecords(CodeParamVo param) throws SQLException;
	void insertCode(CodeVo param) throws SQLException;
	void updateCode(CodeVo param) throws SQLException;
	void deleteCode(CodeVo param) throws SQLException;
}

