package kr.co.kbs.distribute.program.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kbs.distribute.code.service.CodeService;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.common.service.SaveGroupService;
import kr.co.kbs.distribute.common.vo.FileVo;
import kr.co.kbs.distribute.common.vo.SaveGroupVo;
import kr.co.kbs.distribute.program.service.UsageStatProgramExcelService;

@Controller
public class UsageStatProgramExcelController implements MDLController{
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	UsageStatProgramExcelService service;
	
	@Autowired
	SaveGroupService saveGroupService;
	
	@RequestMapping(value="/program/managerUsageStatProgramExcel")
	public String managerUsageStatProgramExcel(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception{
		
		CodeVo vo = new CodeVo();
		vo.setCodeType("19");
		
		model.addAttribute("companyList", codeService.getCodeList(vo));

		return "/program/managerUsageStatProgramExcelPage";
	}
	
	@RequestMapping(value="/program/savetUsageStatProgramExcelUploadFile", method=RequestMethod.POST) 
	public String saveUsageStatProgramExcelUploadFile(FileVo param, @RequestParam(value = "fileName") MultipartFile fileName, Model model) throws Exception {
		
		boolean check = false;
		FileVo fileVo = service.saveUsageStatProgramExcel(param, fileName);
		
		if(fileVo != null && !ObjectUtils.isEmpty(fileVo)) {
			check = service.checkFileList(fileVo, model);
		}
		
		model.addAttribute("typeCheck", check);
		model.addAttribute("fileVo", fileVo);
		log.debug("check===>"+check);
		
		
		return "jsonView";
	}
	
	@RequestMapping(value="/program/saveGroup", method=RequestMethod.POST) 
	public String savetGroup(SaveGroupVo param, HttpServletRequest request, Model model) throws Exception {
		
		int tgSeq = 0;
		saveGroupService.saveSaveGroup(request, param);
		
		tgSeq = saveGroupService.getMaxKey();
		
		if(tgSeq != 0) {
			service.saveSaveData(request, tgSeq, param);
		}
		
		log.debug("완료############################################################################################");
		
		return "jsonView";
	}
	
}
