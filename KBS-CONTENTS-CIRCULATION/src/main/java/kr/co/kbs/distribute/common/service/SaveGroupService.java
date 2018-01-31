package kr.co.kbs.distribute.common.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.co.kbs.distribute.common.vo.FileVo;
import kr.co.kbs.distribute.common.vo.SaveGroupVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

public interface SaveGroupService extends CommonService {
	int saveSaveGroup(HttpServletRequest request, SaveGroupVo param) throws Exception;
	void updateSaveGroup(SaveGroupVo param) throws Exception;
	int getMaxKey() throws Exception;
	void deleteSaveGroup(FileVo param) throws Exception;
	List<SaveGroupVo> getSaveGroup(ProgramParamVo param) throws Exception;
	int getTotalRecords(ProgramParamVo param) throws Exception;
}
