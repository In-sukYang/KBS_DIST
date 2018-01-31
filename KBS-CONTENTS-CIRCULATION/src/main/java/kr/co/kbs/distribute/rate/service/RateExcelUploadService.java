package kr.co.kbs.distribute.rate.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import kr.co.kbs.distribute.common.service.CommonService;
import kr.co.kbs.distribute.common.vo.FileVo;
import kr.co.kbs.distribute.common.vo.SaveGroupVo;

public interface RateExcelUploadService extends CommonService {
	SaveGroupVo saveRateExcel(FileVo fileVo, MultipartFile file) throws Exception;
	void saveRateData(HttpServletRequest request, int tgSeq, SaveGroupVo saveGroupVo) throws Exception;
}
