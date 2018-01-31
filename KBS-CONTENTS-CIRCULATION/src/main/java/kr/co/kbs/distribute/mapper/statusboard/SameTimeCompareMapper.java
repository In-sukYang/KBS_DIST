package kr.co.kbs.distribute.mapper.statusboard;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.statusboard.vo.SameTimeCompareVo;
import kr.co.kbs.distribute.statusboard.vo.StatusBoardParamVo;

@Repository
public interface SameTimeCompareMapper {
	List<SameTimeCompareVo> selectSameTimeCompareList(StatusBoardParamVo param) throws SQLException;
}
