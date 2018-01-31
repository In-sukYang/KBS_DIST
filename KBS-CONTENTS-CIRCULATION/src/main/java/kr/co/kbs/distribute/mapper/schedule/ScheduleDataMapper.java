package kr.co.kbs.distribute.mapper.schedule;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.kbs.distribute.schedule.vo.CompanyVo;
import kr.co.kbs.distribute.schedule.vo.ContentsVo;
import kr.co.kbs.distribute.schedule.vo.DataVo;
import kr.co.kbs.distribute.schedule.vo.DcViewRatingVo;
import kr.co.kbs.distribute.schedule.vo.MinuteScheduleRateVo;
import kr.co.kbs.distribute.schedule.vo.OrgProgramVo;
import kr.co.kbs.distribute.schedule.vo.ProgramVo;
import kr.co.kbs.distribute.schedule.vo.UseClipVo;
import kr.co.kbs.distribute.schedule.vo.UseStatChannelVo;
import kr.co.kbs.distribute.schedule.vo.UseStatProVo;

@Repository
public interface ScheduleDataMapper {
	
	public int selectCom(CompanyVo vo) throws Exception;
	
	public int insertCom(CompanyVo vo) throws Exception;
	
	public int updateCom(CompanyVo vo) throws Exception;
	
	public int selectProgram(ProgramVo vo) throws Exception;
	
	public int updateProgram(ProgramVo vo) throws Exception;
	
	public int insertProgram(ProgramVo vo) throws Exception;
	
	public int selectContents(ContentsVo vo) throws Exception;
	
	public int updateContents(ContentsVo vo) throws Exception;
	
	public int insertContents(ContentsVo vo) throws Exception;
	
	public int selectOrgProgram(OrgProgramVo vo) throws Exception;
	
	public int updateOrgProgram(OrgProgramVo vo) throws Exception;
	
	public int insertOrgProgram(OrgProgramVo vo) throws Exception;
	
	public int updateProgram(OrgProgramVo vo) throws Exception;
	
	public int selectCode(OrgProgramVo vo) throws Exception;
	
	public int updateCode(OrgProgramVo vo) throws Exception;
	
	public int insertCode(OrgProgramVo vo) throws Exception;
	
	
	public int selectDcViewRate(DcViewRatingVo vo) throws Exception;
	
	public int updateDcViewRate(DcViewRatingVo vo) throws Exception;
	
	public int insertDcViewRate(DcViewRatingVo vo) throws Exception;
	
	public DataVo selectGetContentsId(DataVo vo) throws Exception;
	
	public List<DataVo> selectGetContentsIdList(DataVo vo) throws Exception;
	
	public String selectGetProgramId(DataVo vo) throws Exception;
	
	
	public int selectUseStatProgram(UseStatProVo vo) throws Exception;
	
	public int selectUseStatProgramSub(UseStatProVo vo) throws Exception;
	
	public int updateUseStatProgram(UseStatProVo vo) throws Exception;
	
	public int insertUseStatProgram(UseStatProVo vo) throws Exception;
	
	
	public UseStatProVo selectGetContentsIdInfo(UseStatProVo vo) throws Exception;
	
	public List<UseStatProVo> selectGetContentsIdInfoList(UseStatProVo vo) throws Exception;
	
	public int selectMViewRate(MinuteScheduleRateVo vo) throws Exception;
	
	public int updateMViewRate(MinuteScheduleRateVo vo) throws Exception;
	
	public int insertMViewRate(MinuteScheduleRateVo vo) throws Exception;
	
	
	public int selectUsageStatChannel(UseStatChannelVo vo) throws Exception;
	
	public int updateUsageStatChannel(UseStatChannelVo vo) throws Exception;
	
	public int insertUsageStatChannel(UseStatChannelVo vo) throws Exception;
	
	public int selectClip(UseClipVo vo) throws Exception;


	public void updateClip(UseClipVo vo) throws Exception;

	public void insertClip(UseClipVo vo) throws Exception;
	
	public void excuteLast() throws Exception;
	
	public void excuteLastDcViewLating() throws Exception;
	
	public void excuteDViewRating() throws Exception;
	
	public void excuteClipPSETNotNullSet() throws Exception;
	
	public void excuteClipPCSETNotNullSet() throws Exception;
	
	
	public void excuteDcViewRatingUpdate() throws Exception;
	
	
}
