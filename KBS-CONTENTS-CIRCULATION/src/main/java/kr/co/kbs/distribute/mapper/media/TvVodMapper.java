package kr.co.kbs.distribute.mapper.media;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.media.vo.TvVodVo;
import kr.co.kbs.distribute.media.vo.MediaParamVo;

@Repository
public interface TvVodMapper {
	List<TvVodVo> selectTvVodList(MediaParamVo param) throws SQLException;
}
