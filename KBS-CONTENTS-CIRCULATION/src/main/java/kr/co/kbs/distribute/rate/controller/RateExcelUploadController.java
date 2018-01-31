package kr.co.kbs.distribute.rate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kbs.distribute.code.service.CodeService;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.service.SaveGroupService;
import kr.co.kbs.distribute.common.vo.FileVo;
import kr.co.kbs.distribute.common.vo.SaveGroupVo;
import kr.co.kbs.distribute.rate.service.RateExcelUploadService;

@Controller
public class RateExcelUploadController {

	@Autowired
	CodeService codeService;
	
	@Autowired
	RateExcelUploadService service;
	
	@Autowired
	SaveGroupService saveGroupService;
	
	@RequestMapping(value="/rate/managerRateExcel")
	public String managerUsageStatProgramExcel(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
		CodeVo vo = new CodeVo();
		vo.setCodeType("19");
		
		model.addAttribute("companyList", codeService.getCodeList(vo));

		return "/rate/managerRateExcelPage";
	}
	
	@RequestMapping(value="/rate/saveRateExcelUploadFile", method=RequestMethod.POST) 
	public String saveRateExcelUploadFile(HttpServletRequest request, FileVo param, @RequestParam(value = "fileName") MultipartFile fileName, Model model) throws Exception {
		
		SaveGroupVo saveGroupVo = service.saveRateExcel(param, fileName);
		
		int tgSeq = 0;
		
		tgSeq = saveGroupService.getMaxKey();
		
		if(tgSeq != 0) {
			service.saveRateData(request, tgSeq, saveGroupVo);	
		}
		
		return "jsonView";
	}
	
}
