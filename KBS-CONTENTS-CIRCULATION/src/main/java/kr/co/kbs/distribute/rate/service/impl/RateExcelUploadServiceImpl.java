package kr.co.kbs.distribute.rate.service.impl;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kbs.distribute.common.util.CastUtil;
import kr.co.kbs.distribute.common.util.FileUtil;
import kr.co.kbs.distribute.common.vo.FileVo;
import kr.co.kbs.distribute.common.vo.SaveGroupVo;
import kr.co.kbs.distribute.mapper.common.SaveGroupMapper;
import kr.co.kbs.distribute.mapper.rate.MinuteRateMapper;
import kr.co.kbs.distribute.rate.service.RateExcelUploadService;
import kr.co.kbs.distribute.rate.vo.MinuteRateVo;

@Service
public class RateExcelUploadServiceImpl implements RateExcelUploadService{

	@Autowired
	private Environment env;
	
	@Autowired
	SaveGroupMapper saveGroupMapper;
	
	@Autowired
	MinuteRateMapper mapper;
	
	@Override
	public SaveGroupVo saveRateExcel(FileVo fileVo, MultipartFile file) throws Exception {
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		
		HttpSession session = request.getSession();
		
		String loginUser = (String) session.getAttribute("loginId");
		
		String path = env.getProperty("file.path");
		Calendar toDay = Calendar.getInstance();
		FileVo param = new FileVo();
		SaveGroupVo saveGroupVo = new SaveGroupVo();
		
		String year = Integer.toString(toDay.get(Calendar.YEAR));
		String month = Integer.toString((toDay.get(Calendar.MONTH)+1));
		String date = Integer.toString(toDay.get(Calendar.DATE));

		path = path+"excel/temp";
		path = path + "/" + year + "/" +month + "/" + date;
		
		try{
			param = FileUtil.uploadFile(file, path);
			
			saveGroupVo.setAttachFile(param.getSaveFilenm());
			saveGroupVo.setAttachFileReal(param.getOriginFilenm());
			saveGroupVo.setCSeq(fileVo.getCSeq());
			saveGroupVo.setAttachFileDir(path);
			saveGroupVo.setDistType(fileVo.getDistType());
			saveGroupVo.setLoginUser(loginUser);
			saveGroupMapper.insertSaveGroup(saveGroupVo);
		}catch(Exception e) {
			log.error("파일 UPLOAD시 에러 발생");
			if(ObjectUtils.isEmpty(param)) {
				saveGroupMapper.deleteSaveGroup(fileVo);
				FileUtil.fileDelete(param.getSaveFilenm(), path);	
			}
			
			throw new Exception();
		}
		
		return saveGroupVo;
	}
	
	
	@Async
	@Override
	public void saveRateData(HttpServletRequest request, int tgSeq, SaveGroupVo saveGroupVo) throws Exception {
		
		HttpSession session = request.getSession();
		
		String loginUser = (String) session.getAttribute("loginId");
		
		FileInputStream fis = null;
		HSSFWorkbook workbook= null;
		
		String distType = saveGroupVo.getDistType();
		String fileName = saveGroupVo.getAttachFile();
		String path = saveGroupVo.getAttachFileDir();
		
		try{
			
			
			fis = new FileInputStream(path + "/" + fileName);
			
			workbook = new HSSFWorkbook(fis);
			
			HSSFSheet sheet;
			HSSFRow row;
			HSSFCell cell;
			
			sheet = workbook.getSheetAt(0);
			
			MinuteRateVo vo = null;
			List<MinuteRateVo> bodyList = new ArrayList<MinuteRateVo>();
			for(int rowIndex=1; rowIndex <  sheet.getLastRowNum(); rowIndex++) {
        		
        		row =  sheet.getRow(rowIndex);
        		
        		vo = new MinuteRateVo();
        		
        		for(int cellIndex=0;cellIndex<row.getPhysicalNumberOfCells(); cellIndex++) {
        			
        			cell = row.getCell(cellIndex);
        			String value = "";
    				
    				if(cell != null){
    					switch (cell.getCellTypeEnum()){
	                        case FORMULA:
	                            value = cell.getCellFormula();
	                            break;
	                        case NUMERIC:
	                        	if( DateUtil.isCellDateFormatted(cell)) {

	                				Date date = cell.getDateCellValue();

	                				value = new SimpleDateFormat("yyyy-MM-dd").format(date);

	                			}else {
	                				value =  cell.getNumericCellValue()+"";	
	                			}
	                            break;
	                        case STRING:
	                            value = cell.getStringCellValue();
	                            break;
	                        case BLANK:
	                            value = "0.00";
	                            break;
	                        case ERROR:
	                            value = cell.getErrorCellValue()+"";
	                            break;
	                        default:
	                            value = new String();
	                            break;
	                    }
    				}
    				
    				
	    				switch (cellIndex) {
	                    case 0:  
	                    	vo.setBroadDate(value);
	                        break;
	                    case 1: 
	                    	vo.setResearchAgency(value);
	                        break;
	                    case 2: 
	                    	vo.setArea(value);
	                    	break;
	                    case 3: 
	                    	
	                    	
	                        break;
	                    case 4: 
	                    	
	                    	String broadDate = kr.co.kbs.distribute.common.util.DateUtil.getDateAdd(vo.getBroadDate().replaceAll("-", ""), 4, CastUtil.toInt(value.substring(0, 2)),"yyyyMMddHHmmss");
//	            			logger.debug("voradDate:"+voradDate);
	                    	broadDate = kr.co.kbs.distribute.common.util.DateUtil.getDateAdd(kr.co.kbs.distribute.common.util.DateUtil.getDate(broadDate), 5, CastUtil.toInt(value.substring(3, 5)),"yyyyMMddHHmmss");
	                    	
	                    	vo.setBroadDate(broadDate);
	                    	vo.setAirTime(value);
	                        break;
	                    case 5: 
	                    	vo.setHouse(new BigDecimal(value));
	                        break;
	                    case 6: 
	                    	vo.setPersonal(new BigDecimal(value));
	                        break;
	                        
	                    case 7: 
	                    	vo.setCable(new BigDecimal(value));
	                        break;
	                    case 8: 
	                    	vo.setWireless(new BigDecimal(value));
	                        break;
	                    case 9: 
	                    	vo.setMan(new BigDecimal(value));
	                        break;
	                        
	                    case 10: 
	                    	vo.setWoman(new BigDecimal(value));
	                        break;
	                        
	                    case 11: 
	                    	vo.setU10(new BigDecimal(value));
	                        break;
	                    case 12: 
	                    	vo.setS10(new BigDecimal(value));
	                        break;
	                    case 13: 
	                    	vo.setS20(new BigDecimal(value));
	                        break;
	                        
	                    case 14: 
	                    	vo.setS30(new BigDecimal(value));
	                        break;
	                    case 15: 
	                    	vo.setS40(new BigDecimal(value));
	                        break;
	                        
	                    case 16: 
	                    	vo.setO50(new BigDecimal(value));
	                        break;
	                    case 17: 
	                    	vo.setMu10(new BigDecimal(value));
	                        break;
	                    case 18: 
	                    	vo.setMs10(new BigDecimal(value));
	                        break;
	                        
	                    case 19: 
	                    	vo.setMs20(new BigDecimal(value));
	                        break;
	                    case 20: 
	                    	vo.setMs30(new BigDecimal(value));
	                        break;
	                    case 21: 
	                    	vo.setMs40(new BigDecimal(value));
	                        break;
	                    case 22: 
	                    	vo.setMo50(new BigDecimal(value));
	                        break;
	                    case 23: 
	                    	vo.setWu10(new BigDecimal(value));
	                        break;    
	                    case 24: 
	                    	vo.setWs10(new BigDecimal(value));
	                        break;
	                    case 25: 
	                    	vo.setWs20(new BigDecimal(value));
	                        break;
	                    case 26: 
	                    	vo.setWs30(new BigDecimal(value));
	                        break;
	                    case 27: 
	                    	vo.setWs40(new BigDecimal(value));
	                        break;
	                    case 28: 
	                    	vo.setWo50(new BigDecimal(value));
	                        break;
	                    case 29: 
	                    	vo.setUnEl(new BigDecimal(value));
	                        break;
	                    case 30: 
	                    	vo.setMiSc(new BigDecimal(value));
	                        break;
	                    case 31: 
	                    	vo.setMiGr(new BigDecimal(value));
	                        break;
	                    case 32: 
	                    	vo.setHiGr(new BigDecimal(value));
	                        break;
	                    case 33: 
	                    	vo.setOvUn(new BigDecimal(value));
	                        break;
	                    case 34: 
	                    	vo.setHoWife(new BigDecimal(value));
	                        break;
	                    case 35: 
	                    	vo.setUnIncome150(new BigDecimal(value));
	                        break;
	                    case 36: 
	                    	vo.setOvIncome150(new BigDecimal(value));
	                        break;
	                    case 37: 
	                    	vo.setIncome200(new BigDecimal(value));
	                        break;
	                    case 38: 
	                    	vo.setIncome300(new BigDecimal(value));
	                        break;
	                    case 39: 
	                    	vo.setIncome400(new BigDecimal(value));
	                        break;
	                    case 40: 
	                    	vo.setOvIncome500(new BigDecimal(value));
	                        break;
	                    default:
					}
        			
        		}
        		
        		bodyList.add(vo);
        		
			}
			
			if(!bodyList.isEmpty() && bodyList.size() > 0 ) {
				
				for(MinuteRateVo minVo : bodyList) {
					minVo.setTgSeq(tgSeq);
					minVo.setLoginUser(loginUser);
					mapper.insertMinuteRate(minVo);
				}
				
			}
			
			SaveGroupVo saveGroupVoParam =  new SaveGroupVo();
			saveGroupVoParam.setTgSeq(tgSeq);
			saveGroupVoParam.setStatus("U");
			saveGroupVoParam.setLoginUser(loginUser);
			saveGroupMapper.updateSaveGroup(saveGroupVoParam);
			
		}catch(Exception e) {
			log.error(e.getMessage());
			log.error("{}" , e);
			
			SaveGroupVo saveGroupVoParam =  new SaveGroupVo();
			saveGroupVoParam.setTgSeq(tgSeq);
			saveGroupVoParam.setStatus("E");
			saveGroupVoParam.setLoginUser(loginUser);
			saveGroupMapper.updateSaveGroup(saveGroupVoParam);
			
		}finally {
			
			if(fis != null) {
				fis.close();
			}
			
		}
		
		
	}

}
