package kr.co.kbs.distribute.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.company.service.CompanyDistTypeService;
import kr.co.kbs.distribute.company.vo.CompanyDistTypeVo;
import kr.co.kbs.distribute.company.vo.CompanyParamVo;
import kr.co.kbs.distribute.company.vo.CompanyVo;

@Controller
public class CompanyDistTypeController implements MDLController{

	@Autowired
	CompanyDistTypeService service;
	
	@RequestMapping(value="/company/getCompanyDistTypeList", method = RequestMethod.GET) 
	public String getCompanyDistTypeList(CompanyVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		model.addAttribute("companyDistTypeList", service.getCompanyDistTypeList(param));
		return "jsonView";
	}
	
	@RequestMapping(value="/company/saveComapnyDistType", method = RequestMethod.POST)
	public String saveComapnyDistTypeList(CompanyDistTypeVo param, Model model) throws Exception {
		
		log.debug("{}.{} start parameter = {}",this.getClass().getName(), new Object(){}.getClass().getEnclosingMethod(), param.toString());
		String resultCode = "00";
		try{
			service.saveCompanyDistType(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}
	
	@RequestMapping(value="/company/deleteCompanyDist", method=RequestMethod.POST)
	public String deleteCompanyDist(CompanyParamVo param, Model model) throws Exception {
		
		String resultCode = "00";
		try{
			service.deleteComapnyDist(param);
		}catch(Exception e) {
			resultCode = "99";
		}
		
		model.addAttribute("resultCode", resultCode);
		
		return "jsonView";
	}

}
