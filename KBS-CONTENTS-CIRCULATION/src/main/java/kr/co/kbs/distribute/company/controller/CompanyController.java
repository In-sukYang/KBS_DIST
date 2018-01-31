package kr.co.kbs.distribute.company.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.code.service.CodeService;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.company.service.CompanyDistTypeService;
import kr.co.kbs.distribute.company.service.CompanyService;
import kr.co.kbs.distribute.company.vo.CompanyParamVo;
import kr.co.kbs.distribute.company.vo.CompanyVo;

@Controller
public class CompanyController implements MDLController{
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	CompanyService service;
	
	@Autowired
	CompanyDistTypeService companyDistTypeService;
	
	@RequestMapping(value="/company/managerCompany", method=RequestMethod.GET)
	public String managerCompanyPage(HttpServletRequest request ,Model model) throws Exception{
		
		CodeVo codeVo = new CodeVo();
		codeVo.setCodeType("05");
		
		model.addAttribute("companyTypeList", codeService.getCodeList(codeVo));
		
		codeVo = new CodeVo();
		codeVo.setCodeType("06");
		
		model.addAttribute("typeList", codeService.getCodeList(codeVo));
		
		return "/company/managerCompanyPage";
	}
	
	@RequestMapping(value="/company/getCompanyList", method=RequestMethod.GET)
	public String getCompanyList(CompanyParamVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		param.getCurrentPageNo();
		List<CompanyVo> companyList = service.getCompanyList(param);
		
		param.makePaging(service.getTotalRecords(param));
		
		model.addAttribute("companyList", companyList);
		model.addAttribute("param", param);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/company/saveCompany", method=RequestMethod.POST)
	public String saveCompany(CompanyVo param, Model model) throws Exception{
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		String resultCode = "00";
		try{
			service.saveCompnay(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
	
}
