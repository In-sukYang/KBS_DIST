package kr.co.kbs.distribute.mapper.dashboard;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.dashboard.vo.DashBoardParamVo;
import kr.co.kbs.distribute.dashboard.vo.TvVodTotalDashBoardVo;

@Repository
public interface TotalDashBoardMapper {
	List<TvVodTotalDashBoardVo> selectTotalDashBoardTvVodList(DashBoardParamVo param) throws SQLException;
	List<TvVodTotalDashBoardVo> selectTotalDashBoardPooqVodList(DashBoardParamVo param) throws SQLException;
	List<TvVodTotalDashBoardVo> selectTotalDashBoardTvVodListForCseq(DashBoardParamVo param) throws SQLException;
	List<TvVodTotalDashBoardVo> selectTotalDashBoardTopViewRating20(DashBoardParamVo param) throws SQLException;
	
	List<TvVodTotalDashBoardVo> getPooqRealList(DashBoardParamVo param) throws SQLException;
	String getSmrClipCnt(DashBoardParamVo param) throws SQLException;
	List<TvVodTotalDashBoardVo> getTvVodVCntList(DashBoardParamVo param) throws SQLException;
}
