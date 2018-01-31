package kr.co.kbs.distribute.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping(value= "/error/menuCheck")
	public String menuCheckError(HttpServletRequest request ,Model model) throws Exception {
		
		
		model.addAttribute("referer", "/");
		
		return "/error/menuCheckPage";
	}
}
