package kr.co.kbs.distribute.pairing.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.kbs.distribute.code.service.CodeService;
import kr.co.kbs.distribute.code.vo.CodeVo;
import kr.co.kbs.distribute.common.service.MinMaxDayService;
import kr.co.kbs.distribute.common.util.ExcelWorkBookFactory;
import kr.co.kbs.distribute.pairing.service.PairingInformationService;
import kr.co.kbs.distribute.pairing.vo.PairingParamVo;
import kr.co.kbs.distribute.pairing.vo.PairingVo;

@Controller
public class PairingInformationController {
	
	@Autowired
	CodeService codeService;
	
	@Autowired
	PairingInformationService service;
	
	@Autowired
	MinMaxDayService minMaxDayService;
	
	@RequestMapping(value="/pairing/managerPairingInfo", method=RequestMethod.GET)
	public String managerPairingInfoPage(HttpServletRequest request ,Model model) throws Exception {
		
		CodeVo channelVo = new CodeVo();
		channelVo.setCodeType("18");
		
		model.addAttribute("channleList", codeService.getCodeList(channelVo));
		model.addAttribute("dayInfo", minMaxDayService.getDcViewRatingMinMaxDay());
		
		return "/pairing/managerPairingInfoPage";
	}
	
	@RequestMapping(value="/pairing/getPairingInformationList", method=RequestMethod.GET)
	public String getPairingInformationList(PairingParamVo param, Model model) throws Exception {
		
		model.addAttribute("list", service.getPairingInformationList(param));
		
		return "jsonView";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/pairing/downloadExcelFile", method = RequestMethod.GET)
	public byte[] downloadExcelFile(PairingParamVo param, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {

		String[] headerAry = "방송시간|프로그램명|회차|시청률|POOQVOD|TVVOD|SMR클립".split("\\|");
		
		String name ="";
		
		name += "편성일별 현황_"+ param.getSearchValue();
		ExcelWorkBookFactory.ExcelSheetFactory sheet = ExcelWorkBookFactory.create(10000).sheet(name);
		sheet.headers(1, headerAry);

		List<PairingVo> list = service.getPairingInformationList(param);
		
		if (list.size() > 0) {
			
			sheet.rowCellValues(list, new ExcelWorkBookFactory.CellValueRef<PairingVo>() {
				@Override
				public void setRowData(Row row, PairingVo vo) {
					int col = 1;
					row.createCell(col++).setCellValue(vo.getBroadTime());
					row.createCell(col++).setCellValue(vo.getProgramNm());
					row.createCell(col++).setCellValue(vo.getVodcnt());
					row.createCell(col++).setCellValue(vo.getViewRating().toString());
					row.createCell(col++).setCellValue(vo.getPooqVod());
					row.createCell(col++).setCellValue(vo.getTvVod());
					row.createCell(col++).setCellValue(vo.getClipCnt());
					
				}
			});
		}

		byte[] bytes = sheet.end().make();

		name = new String(name.getBytes("KSC5601"), "8859_1");
		
		response.setHeader("Content-Disposition", "attachment; filename=" + name + ".xlsx");
		response.setContentLength(bytes.length);
		response.setContentType("application/vnd.ms-excel");

		return bytes;
	}
}
