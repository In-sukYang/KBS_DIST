package kr.co.kbs.distribute.mapper.statusboard;

import java.sql.SQLException;
import java.util.List;

import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.media.vo.PooqVodVo;
import kr.co.kbs.distribute.media.vo.SMRClipVo;
import kr.co.kbs.distribute.media.vo.TvVodVo;
import kr.co.kbs.distribute.statusboard.vo.RateRankVo;

public interface PointStatusBoardMapper {
	List<TvVodVo> selectTvVodTopFiveList(MediaParamVo param) throws SQLException;
	List<PooqVodVo>selectPooqVodFiveList(MediaParamVo param) throws SQLException;
	List<SMRClipVo> selectSmrClipTopFiveList(MediaParamVo param) throws SQLException;
	List<RateRankVo> selectRatingTopFiveList(MediaParamVo param) throws SQLException;
	List<RateRankVo> selectPooqLiveTopFiveList(MediaParamVo param) throws SQLException;
		
}
