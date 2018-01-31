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
import kr.co.kbs.distribute.program.service.TvVodDetailService;
import kr.co.kbs.distribute.program.vo.ProgramParamVo;
import kr.co.kbs.distribute.program.vo.TvVodDetailVo;

@Controller
public class TvVodDetailController implements MDLController{
	
	@Autowired
	TvVodDetailService service;
	
	@RequestMapping(value = "/program/detail/managerTvVodDetail")
	public String managerTvVodDetail(HttpServletRequest req, Model model)  throws Exception {
		
		return "/program/detail/managerTvVodePage";
	}
	
	@RequestMapping(value="/program/detail/getTvVodDatailList1", method=RequestMethod.GET) 
	public String getTvVodDatailList1(ProgramParamVo param, Model model) throws Exception {
		
		model.addAttribute("list", service.getTvVodDatailList1(param));
		
		return "jsonView";
	}
	
	@RequestMapping(value="/program/detail/getTvVodDatailList2", method=RequestMethod.GET) 
	public String getTvVodDatailList2(ProgramParamVo param, Model model) throws Exception {
		
		model.addAttribute("list", service.getTvVodDatailList2(param));
		
		return "jsonView";
	}
	
	@ResponseBody
	@RequestMapping(value = "/program/detail/tv/downloadExcelFile", method = RequestMethod.GET)
	public byte[] downloadExcelFile(ProgramParamVo param, HttpServletRequest request, HttpServletResponse response,
			Model model) throws Exception {

		String[] headerAry;
		
		String name ="";
		
		name += "프로그램상세_TVVOD";
		ExcelWorkBookFactory.ExcelSheetFactory sheet = ExcelWorkBookFactory.create(10000).sheet(name);

		List<TvVodDetailVo> list = new ArrayList<TvVodDetailVo>();
		if(param.getSearchType().equals("01")){
			headerAry = "방영일자|LG_PPM|SK_PPM|CVOD_PPM|합계_PPM|LG_PPV|SK_PPV|CVOD_PPV|합계_PPV".split("\\|");
			sheet.headers(1, headerAry);
			list = service.getTvVodDatailList1(param);
			if (list.size() > 0) {
				
				sheet.rowCellValues(list, new ExcelWorkBookFactory.CellValueRef<TvVodDetailVo>() {
					@Override
					public void setRowData(Row row, TvVodDetailVo vo) {
						int col = 1;
						row.createCell(col++).setCellValue(vo.getBroadDate());
						row.createCell(col++).setCellValue(vo.getLg());
						row.createCell(col++).setCellValue(vo.getSk());
						row.createCell(col++).setCellValue(vo.getCvod());

						int lg = vo.getLg();
						int sk = vo.getSk();
						int cvod = vo.getCvod();
						
						row.createCell(col++).setCellValue(lg+sk+cvod);
						
						row.createCell(col++).setCellValue(vo.getLgPpv());
						row.createCell(col++).setCellValue(vo.getSkPpv());
						row.createCell(col++).setCellValue(vo.getCvodPpv());
						
						lg = vo.getLgPpv();
						sk = vo.getSkPpv();
						cvod = vo.getCvodPpv();
						
						row.createCell(col++).setCellValue(lg+sk+cvod);
						
						
					}
				});
			}
		}else{
			headerAry = "기준일자|LG_PPM|SK_PPM|CVOD_PPM|합계_PPM|LG_PPV|SK_PPV|CVOD_PPV|합계_PPV".split("\\|");
			sheet.headers(1, headerAry);
			list = service.getTvVodDatailList2(param);
			if (list.size() > 0) {
				
				sheet.rowCellValues(list, new ExcelWorkBookFactory.CellValueRef<TvVodDetailVo>() {
					@Override
					public void setRowData(Row row, TvVodDetailVo vo) {
						int col = 1;
						row.createCell(col++).setCellValue(vo.getYyyymmdd());
						row.createCell(col++).setCellValue(vo.getLg());
						row.createCell(col++).setCellValue(vo.getSk());
						row.createCell(col++).setCellValue(vo.getCvod());

						int lg = vo.getLg();
						int sk = vo.getSk();
						int cvod = vo.getCvod();
						
						row.createCell(col++).setCellValue(lg+sk+cvod);
						
						row.createCell(col++).setCellValue(vo.getLgPpv());
						row.createCell(col++).setCellValue(vo.getSkPpv());
						row.createCell(col++).setCellValue(vo.getCvodPpv());
						
						lg = vo.getLgPpv();
						sk = vo.getSkPpv();
						cvod = vo.getCvodPpv();
						
						row.createCell(col++).setCellValue(lg+sk+cvod);
						
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
