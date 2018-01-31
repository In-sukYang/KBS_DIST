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
import kr.co.kbs.distribute.program.service.PooqVodDetailService;
import kr.co.kbs.distribute.program.vo.PooqVodDetailVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Controller
public class PooqVodDetailController implements MDLController{
	
	@Autowired
	PooqVodDetailService service;
	
	@RequestMapping(value = "/program/detail/managerPooqVodDetail")
	public String managerTvVodDetail(HttpServletRequest req, Model model)  throws Exception {
		
		return "/program/detail/managerPooqVodePage";
	}
	
	@RequestMapping(value="/program/detail/getPooqVodDatailList1", method=RequestMethod.GET) 
	public String getPooqVodDatailList1(ProgramParamVo param, Model model) throws Exception {
		
		model.addAttribute("list", service.getPooqVodDatailList1(param));
		
		return "jsonView";
	}
	
	@RequestMapping(value="/program/detail/getPooqVodDatailList2", method=RequestMethod.GET) 
	public String getPooqVodDatailList2(ProgramParamVo param, Model model) throws Exception {
		
		model.addAttribute("list", service.getPooqVodDatailList2(param));
		
		return "jsonView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/program/detail/pooq/downloadExcelFile", method = RequestMethod.GET)
	public byte[] downloadExcelFile(ProgramParamVo param, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {

		String[] headerAry;
		
		String name ="";
		
		name += "프로그램상세_POOQVOD";
		ExcelWorkBookFactory.ExcelSheetFactory sheet = ExcelWorkBookFactory.create(10000).sheet(name);

		List<PooqVodDetailVo> list = new ArrayList<PooqVodDetailVo>();
		if(param.getSearchType().equals("01")){
			headerAry = "방영일자|회차|유료 시청자수|초 고화질 유료시청자수|무료 시청자수|초 고화질 무료시청자수|유료 시청시간(분)|초 고화질 유료 시청시간(분)|무료 시청시간(분)|초 고화질 무료 시청시간(분)".split("\\|");
			sheet.headers(1, headerAry);
			list = service.getPooqVodDatailList1(param);
			if (list.size() > 0) {
				
				sheet.rowCellValues(list, new ExcelWorkBookFactory.CellValueRef<PooqVodDetailVo>() {
					@Override
					public void setRowData(Row row, PooqVodDetailVo vo) {
						int col = 1;
						row.createCell(col++).setCellValue(vo.getBroadDate());
						row.createCell(col++).setCellValue(vo.getVodcnt());
						
						row.createCell(col++).setCellValue(vo.getChargeCnt());
						row.createCell(col++).setCellValue(vo.getHChargeCnt());
						row.createCell(col++).setCellValue(vo.getFreeChargeCnt());
						row.createCell(col++).setCellValue(vo.getHFreeChargeCnt());

						row.createCell(col++).setCellValue(vo.getChargeVtime().toEngineeringString());
						row.createCell(col++).setCellValue(vo.getHChargeVtime().toEngineeringString());
						row.createCell(col++).setCellValue(vo.getFreeChargeVtime().toEngineeringString());
						row.createCell(col++).setCellValue(vo.getHFreeChargeVtime().toEngineeringString());
						
					}
				});
			}
		}else{
			headerAry = "기준일자|유료 시청자수|초 고화질 유료시청자수|무료 시청자수|초 고화질 무료시청자수|유료 시청시간(분)|초 고화질 유료 시청시간(분)|무료 시청시간(분)|초 고화질 무료 시청시간(분)".split("\\|");
			sheet.headers(1, headerAry);
			list = service.getPooqVodDatailList2(param);
			if (list.size() > 0) {
				
				sheet.rowCellValues(list, new ExcelWorkBookFactory.CellValueRef<PooqVodDetailVo>() {
					@Override
					public void setRowData(Row row, PooqVodDetailVo vo) {
						int col = 1;
						row.createCell(col++).setCellValue(vo.getYyyymmdd());
						
						row.createCell(col++).setCellValue(vo.getChargeCnt());
						row.createCell(col++).setCellValue(vo.getHChargeCnt());
						row.createCell(col++).setCellValue(vo.getFreeChargeCnt());
						row.createCell(col++).setCellValue(vo.getHFreeChargeCnt());

						row.createCell(col++).setCellValue(vo.getChargeVtime().toEngineeringString());
						row.createCell(col++).setCellValue(vo.getHChargeVtime().toEngineeringString());
						row.createCell(col++).setCellValue(vo.getFreeChargeVtime().toEngineeringString());
						row.createCell(col++).setCellValue(vo.getHFreeChargeVtime().toEngineeringString());
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
