package kr.co.kbs.distribute.mapper.media;

import java.sql.SQLException;
import java.util.List;

import kr.co.kbs.distribute.media.vo.MediaParamVo;
import kr.co.kbs.distribute.media.vo.SMRClipVo;

public interface SMRClipMapper {
	List<SMRClipVo> selectSmrClipTopList(MediaParamVo param) throws SQLException;
}
