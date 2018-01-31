package kr.co.kbs.distribute.mapper.media;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.media.vo.LiveBroadVo;
import kr.co.kbs.distribute.media.vo.MediaParamVo;

@Repository
public interface LiveBroadMapper {
	List<LiveBroadVo> selectLiveBroadTopList(MediaParamVo param) throws SQLException;
	
	List<LiveBroadVo> selectLiveBroadTotalTopList(MediaParamVo param) throws SQLException;
	
}
