package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.program.vo.PointIndicatorsVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Repository
public interface PointIndicatorsMapper {
	List<PointIndicatorsVo> selectProgramPointIndicatorsList(ProgramParamVo param) throws SQLException;
	List<PointIndicatorsVo> selectProgramPointIndicatorsList2(ProgramParamVo param) throws SQLException;
}
