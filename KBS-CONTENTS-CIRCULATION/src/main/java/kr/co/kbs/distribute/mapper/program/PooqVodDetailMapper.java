package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.program.vo.PooqVodDetailVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Repository
public interface PooqVodDetailMapper {
	List<PooqVodDetailVo> selectPooqVodDatailList1(ProgramParamVo param) throws SQLException;
	List<PooqVodDetailVo> selectPooqVodDatailList2(ProgramParamVo param) throws SQLException;
}
