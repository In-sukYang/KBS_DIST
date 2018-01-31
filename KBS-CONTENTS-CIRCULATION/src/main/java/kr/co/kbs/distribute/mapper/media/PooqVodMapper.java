package kr.co.kbs.distribute.mapper.media;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.media.vo.PooqVodVo;
import kr.co.kbs.distribute.media.vo.MediaParamVo;

@Repository
public interface PooqVodMapper {
	List<PooqVodVo> selectPooqVodList(MediaParamVo param) throws SQLException;
}
