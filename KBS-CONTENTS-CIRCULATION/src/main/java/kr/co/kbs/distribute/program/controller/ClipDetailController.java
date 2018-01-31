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

import kr.co.kbs.distribute.common.util.ExcelWorkBookFactory;
import kr.co.kbs.distribute.program.service.ClipDetailService;
import kr.co.kbs.distribute.program.vo.ClipDetailVo;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;

@Controller
public class ClipDetailController {
	@Autowired
	ClipDetailService service;
	
	@RequestMapping(value = "/program/detail/managerClipDetail")
	public String managerTvVodDetail(HttpServletRequest req, Model model)  throws Exception {
		
		return "/program/detail/managerClipDetailPage";
	}
	
	@RequestMapping(value="/program/detail/getClipDatailList1", method=RequestMethod.GET) 
	public String getClipDatailList1(ProgramParamVo param, Model model) throws Exception {
		
		model.addAttribute("list", service.getClipDatailList1(param));
		
		return "jsonView";
	}
	
	@RequestMapping(value="/program/detail/getClipDatailList2", method=RequestMethod.GET) 
	public String getClipDatailList2(ProgramParamVo param, Model model) throws Exception {
		
		model.addAttribute("list", service.getClipDatailList2(param));
		
		return "jsonView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/program/detail/clip/downloadExcelFile", method = RequestMethod.GET)
	public byte[] downloadExcelFile(ProgramParamVo param, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {

		String[] headerAry;
		
		String name ="";
		
		name += "프로그램상세_SMRCLIP";
		ExcelWorkBookFactory.ExcelSheetFactory sheet = ExcelWorkBookFactory.create(10000).sheet(name);

		List<ClipDetailVo> list = new ArrayList<ClipDetailVo>();
		if(param.getSearchType().equals("01")){
			headerAry = "방영일자|회차|시청자수 합계".split("\\|");
			sheet.headers(1, headerAry);
			list = service.getClipDatailList1(param);
			if (list.size() > 0) {
				
				sheet.rowCellValues(list, new ExcelWorkBookFactory.CellValueRef<ClipDetailVo>() {
					@Override
					public void setRowData(Row row, ClipDetailVo vo) {
						int col = 1;
						row.createCell(col++).setCellValue(vo.getBroadDate());
						row.createCell(col++).setCellValue(vo.getVodcnt());
						row.createCell(col++).setCellValue(vo.getClipCnt());
					}
				});
			}
		}else{
			headerAry = "기준일자|시청자수 합계".split("\\|");
			sheet.headers(1, headerAry);
			list = service.getClipDatailList2(param);
			if (list.size() > 0) {
				
				sheet.rowCellValues(list, new ExcelWorkBookFactory.CellValueRef<ClipDetailVo>() {
					@Override
					public void setRowData(Row row, ClipDetailVo vo) {
						int col = 1;
						row.createCell(col++).setCellValue(vo.getYyyymmdd());
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
