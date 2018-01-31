package kr.co.kbs.distribute.statusboard.controller;

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
import kr.co.kbs.distribute.common.controller.MDLController;
import kr.co.kbs.distribute.common.service.MinMaxDayService;
import kr.co.kbs.distribute.common.util.ExcelWorkBookFactory;
import kr.co.kbs.distribute.statusboard.service.SameTimeCompareService;
import kr.co.kbs.distribute.statusboard.vo.SameTimeCompareVo;
import kr.co.kbs.distribute.statusboard.vo.StatusBoardParamVo;

@Controller
public class SameTimeCompareController implements MDLController{

	@Autowired
	CodeService codeService;
	
	@Autowired
	SameTimeCompareService service;
	
	@Autowired
	MinMaxDayService minMaxDayService;
	
	@RequestMapping(value="/statusboard/managerSameTimeCompare")
	public String managerSameTimecompare(HttpServletRequest req, Model model) throws Exception{
		
		CodeVo vo = new CodeVo();
		vo.setCodeType("07");
		model.addAttribute("programTypeList", codeService.getCodeList(vo));
		model.addAttribute("dayInfo", minMaxDayService.getContentsMinMaxDay());
		
		return "/statusboard/managerSameTimeComparePage";
		
	}
	
	@RequestMapping(value="/statusboard/getSameTimeCompareList", method=RequestMethod.GET)
	public String getSameTimeCompareList(StatusBoardParamVo param, Model model) throws Exception {
		
		model.addAttribute("list", service.getSameTimeCompareList(param));
		return "jsonView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/statusboard/downloadExcelFile", method = RequestMethod.GET)
	public byte[] downloadExcelFile(StatusBoardParamVo param, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {

		String[] headerAry = "편성일자|프로그램명(회차)|시청률|TVVOD|POOQVOD|SMR클립".split("\\|");
		
		String name ="";
		
		name += "동시간대비교_"+ param.getSearchValue() +"~" + param.getSearchValue2() + "(" + param.getWeekday() +")";
		ExcelWorkBookFactory.ExcelSheetFactory sheet = ExcelWorkBookFactory.create(10000).sheet(name);
		sheet.headers(1, headerAry);

		List<SameTimeCompareVo> list = service.getSameTimeCompareList(param);
		
		if (list.size() > 0) {
			
			sheet.rowCellValues(list, new ExcelWorkBookFactory.CellValueRef<SameTimeCompareVo>() {
				@Override
				public void setRowData(Row row, SameTimeCompareVo vo) {
					int col = 1;
					row.createCell(col++).setCellValue(vo.getBroadDate());
					row.createCell(col++).setCellValue(vo.getProgramNm() +"("+ vo.getVodcnt()+")");
					row.createCell(col++).setCellValue(vo.getViewRating().toString());
					row.createCell(col++).setCellValue(vo.getTvVod());
					row.createCell(col++).setCellValue(vo.getPooqVod());
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
