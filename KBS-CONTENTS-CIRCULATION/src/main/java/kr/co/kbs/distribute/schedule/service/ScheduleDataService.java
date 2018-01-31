package kr.co.kbs.distribute.schedule.service;

import java.util.ArrayList;

import org.springframework.scheduling.annotation.Async;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.schedule.vo.CompanyVo;
import kr.co.kbs.distribute.schedule.vo.ContentsVo;
import kr.co.kbs.distribute.schedule.vo.OrgProgramVo;
import kr.co.kbs.distribute.schedule.vo.ProgramVo;
import kr.co.kbs.distribute.schedule.vo.UseClipVo;

public interface ScheduleDataService extends CommonService{
	
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
	
	
	public int selectClip(UseClipVo vo) throws Exception;

	public void updateClip(UseClipVo vo) throws Exception;
	
	public void insertClip(UseClipVo vo) throws Exception;
	public void excuteClip(ArrayList<UseClipVo> al,ArrayList<String> dayal) throws Exception;
	
	
	public void excuteLastDcViewLating() throws Exception;
	
	public void excuteDViewRating() throws Exception;
	
	public void excuteClipPSETNotNullSet() throws Exception;
	
	public void excuteClipPCSETNotNullSet() throws Exception;
	
	@Async
	public void CVOTask(String filePath,int fileListCnt) throws Exception;
	
	
	@Async
	public void SKPPVTask(String filePath,String channelId) throws Exception;
	
	@Async
	public void SKPPMTask(String filePath,int fileListCnt) throws Exception;
	
	@Async
	public void PooqPPMTask(String filePath) throws Exception;
	
	@Async
	public void LGPPMTask(String filePath, String proType) throws Exception;
	
	@Async
	public void KtUseTask(String filePath,int fileListCnt, String viewDateStr) throws Exception;
	
	
}
