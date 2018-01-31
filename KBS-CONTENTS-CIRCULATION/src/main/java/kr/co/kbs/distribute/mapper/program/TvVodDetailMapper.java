package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.TvVodDetailVo;

@Repository
public interface TvVodDetailMapper {
	List<TvVodDetailVo> selectTvVodDatailList1(ProgramParamVo param) throws SQLException;
	List<TvVodDetailVo> selectTvVodDatailList2(ProgramParamVo param) throws SQLException;
}
