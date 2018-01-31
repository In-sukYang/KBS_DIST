package kr.co.kbs.distribute.mapper.rate;

import java.sql.SQLException;
import java.util.List;

import kr.co.kbs.distribute.rate.vo.MinuteRateVo;
import kr.co.kbs.distribute.rate.vo.RateParamVo;

public interface MinuteRateMapper {
	List<MinuteRateVo> selectMinuteRateList(RateParamVo param) throws SQLException;
	int selectTotalRecords(RateParamVo param) throws SQLException;
	void insertMinuteRate(MinuteRateVo param) throws SQLException;
	void updateMinuteRate(MinuteRateVo param) throws SQLException;
}
