package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.program.vo.ClipDetailVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Repository
public interface ClipDetailMapper {
	List<ClipDetailVo> selectClipDatailList1(ProgramParamVo param) throws SQLException;
	List<ClipDetailVo> selectClipDatailList2(ProgramParamVo param) throws SQLException;
	

}
