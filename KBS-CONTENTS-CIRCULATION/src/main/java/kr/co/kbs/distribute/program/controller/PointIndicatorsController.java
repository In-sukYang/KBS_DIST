package kr.co.kbs.distribute.program.controller;

import java.util.ArrayList;
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

import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.common.util.ExcelWorkBookFactory;
import kr.co.kbs.distribute.program.service.PointIndicatorsService;
import kr.co.kbs.distribute.program.vo.PointIndicatorsVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Controller
public class PointIndicatorsController implements MDLController{

	@Autowired
	PointIndicatorsService service;
	
	@RequestMapping(value="/program/detail/managerPointIndicators")
	public String managerPointIndicators(HttpServletRequest req, Model model) {
		
		return "/program/detail/managerPointIndicatorsPage";
	}
	
	@RequestMapping(value="/program/detail/getProgramPointIndicatorsList", method=RequestMethod.GET) 
	public String getProgramPointIndicatorsList(ProgramParamVo param, Model model) throws Exception {
		
		model.addAttribute("list", service.getProgramPointIndicatorsList(param));
		
		return "jsonView";
	}
	
	@RequestMapping(value="/program/detail/managerPointIndicators2")
	public String managerPointIndicators2(HttpServletRequest req, Model model) {
		
		return "/program/detail/managerPointIndicatorsPage2";
	}
	
	@RequestMapping(value="/program/detail/getProgramPointIndicatorsList2", method=RequestMethod.GET) 
	public String getProgramPointIndicatorsList2(ProgramParamVo param, Model model) throws Exception {
		
		model.addAttribute("list", service.getProgramPointIndicatorsList2(param));
		
		return "jsonView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/program/detail/point/downloadExcelFile", method = RequestMethod.GET)
	public byte[] downloadExcelFile(ProgramParamVo param, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {

		String[] headerAry;
		
		String name ="";
		
		name += "프로그램상세_주요지표";
		ExcelWorkBookFactory.ExcelSheetFactory sheet = ExcelWorkBookFactory.create(10000).sheet(name);
		

		List<PointIndicatorsVo> list = new ArrayList<PointIndicatorsVo>();
		if(param.getSearchType().equals("01")){
			headerAry = "방영일자|회차|시청률|TVVOD|POOQVOD|SMR클립".split("\\|");
			sheet.headers(1, headerAry);
			list = service.getProgramPointIndicatorsList(param);
			if (list.size() > 0) {
				
				sheet.rowCellValues(list, new ExcelWorkBookFactory.CellValueRef<PointIndicatorsVo>() {
					@Override
					public void setRowData(Row row, PointIndicatorsVo vo) {
						int col = 1;
						row.createCell(col++).setCellValue(vo.getBroadDate());
						row.createCell(col++).setCellValue(vo.getVodcnt());
						row.createCell(col++).setCellValue(vo.getViewRating().toString());
						row.createCell(col++).setCellValue(vo.getTvVod());
						row.createCell(col++).setCellValue(vo.getPooqVod());
						row.createCell(col++).setCellValue(vo.getClipCnt());
						
					}
				});
			}
		}else{
			headerAry = "기준일자|시청률|TVVOD|POOQVOD|SMR클립".split("\\|");
			sheet.headers(1, headerAry);
			list = service.getProgramPointIndicatorsList2(param);
			if (list.size() > 0) {
				
				sheet.rowCellValues(list, new ExcelWorkBookFactory.CellValueRef<PointIndicatorsVo>() {
					@Override
					public void setRowData(Row row, PointIndicatorsVo vo) {
						int col = 1;
						row.createCell(col++).setCellValue(vo.getYyyymmdd());
						row.createCell(col++).setCellValue(vo.getViewRating().toString());
						row.createCell(col++).setCellValue(vo.getTvVod());
						row.createCell(col++).setCellValue(vo.getPooqVod());
						row.createCell(col++).setCellValue(vo.getClipCnt());
					}
				});
			}
		}
		
		byte[] bytes = sheet.end().make();

		name = new String(name.getBytes("KSC5601"), "8859_1");
		
		response.setHeader("Content-Disposition", "attachment; filename=" + name + ".xlsx");
		response.setContentLength(bytes.length);
		response.setContentType("application/vnd.ms-excel");

		return bytes;
	}
}
