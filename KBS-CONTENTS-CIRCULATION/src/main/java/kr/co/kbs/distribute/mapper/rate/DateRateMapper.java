package kr.co.kbs.distribute.mapper.rate;

import java.sql.SQLException;
import java.util.List;

import kr.co.kbs.distribute.rate.vo.DayRateVo;
import kr.co.kbs.distribute.rate.vo.RateParamVo;

public interface DateRateMapper {
	List<DayRateVo> selectDateRateList(RateParamVo param) throws SQLException;
	int selectTotalRecords(RateParamVo param) throws SQLException;
	void insertDateRate(DayRateVo param) throws SQLException;
	void updateDateRate(DayRateVo param) throws SQLException;
}
