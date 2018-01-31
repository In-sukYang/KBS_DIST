package kr.co.kbs.distribute.mapper.program;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.channel.vo.UsageStatChannelVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Repository
public interface UsageStatChannelMapper {
	List<UsageStatChannelVo> selectUsageStatChannelList(ProgramParamVo param) throws SQLException;
	int selectTotalRecords(ProgramParamVo param) throws SQLException;
	void insertUsageStatChannel(UsageStatChannelVo param) throws SQLException;
	void updateUsageStatChannel(UsageStatChannelVo param) throws SQLException;
	List<String> selectDateYear() throws SQLException;
	Integer selectUspKey(UsageStatChannelVo param) throws SQLException;
}
