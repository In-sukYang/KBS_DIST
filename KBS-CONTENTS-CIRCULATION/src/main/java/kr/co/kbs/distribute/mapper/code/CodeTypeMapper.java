package kr.co.kbs.distribute.mapper.code;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.code.vo.CodeParamVo;
import kr.co.kbs.distribute.code.vo.CodeTypeVo;

@Repository
public interface CodeTypeMapper {
	List<CodeTypeVo> selectCodeTypeList(CodeParamVo param) throws SQLException;
	int selectTotalRecords(CodeParamVo param) throws SQLException;
	void insertCodeType(CodeTypeVo param) throws SQLException;
	void updateCodeType(CodeTypeVo param) throws SQLException;
	void deleteCodeType(CodeTypeVo param) throws SQLException;
}
