package kr.co.kbs.distribute.program.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.common.vo.FileVo;
import kr.co.kbs.distribute.common.vo.SaveGroupVo;

public interface UsageStatProgramExcelService extends CommonService {
	FileVo saveUsageStatProgramExcel(FileVo fileVo, MultipartFile file) throws Exception;
	boolean checkFileList(FileVo fileVo, Model model) throws Exception;
	
	void saveSaveData(HttpServletRequest request, int tgSeq, SaveGroupVo param) throws Exception;
	
}
