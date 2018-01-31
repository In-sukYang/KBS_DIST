package kr.co.kbs.distribute.mapper.common;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.common.vo.MinMaxDayVo;

@Repository
public interface MinMaxDayMapper {
	MinMaxDayVo selectUsageStatProgramMinMaxDay(String param) throws SQLException;
	MinMaxDayVo selectClipMinMaxDay() throws SQLException;
	MinMaxDayVo selectDcViewRatingMinMaxDay() throws SQLException;
	MinMaxDayVo selectContentsMinMaxDay() throws SQLException;
}
