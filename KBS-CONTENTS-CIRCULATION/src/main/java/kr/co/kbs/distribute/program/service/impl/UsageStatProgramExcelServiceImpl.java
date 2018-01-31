package kr.co.kbs.distribute.program.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import kr.co.kbs.distribute.channel.vo.UsageStatChannelVo;
import kr.co.kbs.distribute.common.util.FileUtil;
import kr.co.kbs.distribute.common.util.StringUtil;
import kr.co.kbs.distribute.common.vo.FileVo;
import kr.co.kbs.distribute.common.vo.SaveGroupVo;
import kr.co.kbs.distribute.company.vo.CompanyParamVo;
import kr.co.kbs.distribute.mapper.common.SaveGroupMapper;
import kr.co.kbs.distribute.mapper.company.CompanyMapper;
import kr.co.kbs.distribute.mapper.program.UsageStatChannelMapper;
import kr.co.kbs.distribute.mapper.program.UsageStatProgramExcelMapper;
import kr.co.kbs.distribute.mapper.program.UsageStatProgramMapper;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;
import kr.co.kbs.distribute.program.service.UsageStatProgramExcelService;
import kr.co.kbs.distribute.program.vo.NotFoundContentsVo;
import kr.co.kbs.distribute.program.vo.UsageStatProgramVo;
import kr.co.kbs.distribute.schedule.vo.DataVo;
import kr.co.kbs.distribute.schedule.vo.UseStatProVo;

@Service
public class UsageStatProgramExcelServiceImpl implements UsageStatProgramExcelService {
	
	@Autowired
	private Environment env;
	
	@Autowired
	UsageStatProgramExcelMapper usageStatProgramExcelMapper;
	
	@Autowired
	UsageStatProgramMapper usageStatProgramMapper;
	
	
	@Autowired
	ScheduleDataMapper contentsMapper;

	@Autowired
	CompanyMapper companyMapper;
	
	@Autowired
	UsageStatChannelMapper usageStatChannelMapper;
	
	@Autowired
	SaveGroupMapper saveGroupMapper;
	
	@Override
	public FileVo saveUsageStatProgramExcel(FileVo fileVo, MultipartFile file) throws Exception {

		String path = env.getProperty("file.path");
		Calendar toDay = Calendar.getInstance();
		FileVo param = new FileVo();
		String year = Integer.toString(toDay.get(Calendar.YEAR));
		String month = Integer.toString((toDay.get(Calendar.MONTH)+1));
		String date = Integer.toString(toDay.get(Calendar.DATE));

		path = path+"excel/temp";
		path = path + "/" + year + "/" +month + "/" + date;
		
		try{
			param = FileUtil.uploadFile(file, path);
			param.setCSeq(fileVo.getCSeq());
			param.setDistType(fileVo.getDistType());
			param.setSaveDir(path);
			usageStatProgramExcelMapper.insertExcelTmpfile(param);
		}catch(Exception e) {
			log.error("파일 UPLOAD시 에러 발생");
			if(ObjectUtils.isEmpty(param)) {
				usageStatProgramExcelMapper.deleteExcelTmpData(fileVo);
				FileUtil.fileDelete(param.getSaveFilenm(), path);	
			}
			
			throw new Exception();
		}
		log.debug("TEMP FILE INSERT END");
		return param;
		
	}
	
	@Override
	public boolean checkFileList(FileVo fileVo, Model model) throws Exception {
		
		boolean result = false;
		int cSeq = fileVo.getCSeq();
		String distType = fileVo.getDistType();
		String fileName = fileVo.getSaveFilenm();
		List<String> headerList = new ArrayList<String>();
		
		String path = fileVo.getSaveDir();
		
		if(cSeq == 11 ) {
			if("US".equals(distType)) {
				
				List<UsageStatProgramVo> bodyList = new ArrayList<UsageStatProgramVo>();
			
				CSVReader reader = null;
				List<String[]> content = new ArrayList<String[]>();
				try{
					reader = new CSVReader(new InputStreamReader(new FileInputStream(path + "/" + fileName), "UTF-8"));
					
					content = reader.readAll();
					
					UsageStatProgramVo vo = null;
					
					for(int k=0; k < content.size(); k++){
						String[] data = content.get(k);
					
						vo = new UsageStatProgramVo();
						
						for(int i=0; i < data.length ; i++) {
							if(k == 0) {
								headerList.add(data[i]);
							}else {
								
								switch (i){
                                case 0:
                                    vo.setViewDate(data[i]);
                                    break;
                                case 1:
                                    vo.setBroadNm(data[i]);
                                    break;
                                case 2:
                                	
                                    break;
                                case 3:
                                    
                                    break;
                                case 4:
                                    vo.setContentsId(data[i]);
                                    break;
                                case 8:
                                    vo.setChargeYn(data[i]);
                                    break;
                                case 10:
                                    vo.setChargeVtime(new BigDecimal(data[i]));
                                    break;
                                case 11:
                                    vo.setHChargeVtime(new BigDecimal(data[i]));
                                    break;    
                                case 12:
                                    vo.setChargeCnt(Integer.parseInt(data[i]));
                                    break;
                                case 13:
                                    vo.setHChargeCnt(Integer.parseInt(data[i]));
                                    break;
                                case 14:
                                    vo.setFreeChargeVtime(new BigDecimal(data[i]));
                                    break;
								case 15:
	                                vo.setHFreeChargeVtime(new BigDecimal(data[i]));
	                                break;
	                            case 16:
	                                vo.setFreeChargeCnt(Integer.parseInt(data[i]));
	                                break;
						 		case 17:
						 			vo.setHFreeChargeCnt(Integer.parseInt(data[i]));
	                             break;
								}
								
							}
						}
						
        				bodyList.add(vo);
					}
					
					HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	        		HttpSession session = request.getSession(false);
	            	
	            	session.setAttribute("headerList", headerList);
	            	session.setAttribute("bodyList", bodyList);
	            	result = true;
					
				}catch(Exception e) {
					log.error(e.getMessage());
					log.error("엑셀 읽으면서 에러", e);
					result = false;
					return result;
				}finally {
					
					if(reader != null) {
						reader.close();
					}
					
					if(result == false ) {
						FileUtil.fileDelete(fileName, path);
						usageStatProgramExcelMapper.deleteExcelTmpData(fileVo);
					}
				}
				
			}else if("UC".equals(distType)) {
				
				List<UsageStatChannelVo> bodyList = new ArrayList<UsageStatChannelVo>();
				
				CSVReader reader = null;
				List<String[]> content = new ArrayList<String[]>();
				try{
					reader = new CSVReader(new InputStreamReader(new FileInputStream(path + "/" + fileName), "UTF-8"));
					
					content = reader.readAll();
					
					UsageStatChannelVo vo = null;
					
					for(int k=0; k < content.size(); k++){
						String[] data = content.get(k);
					
						vo = new UsageStatChannelVo();
						
						for(int i=0; i < data.length ; i++) {
							if(k == 0) {
								headerList.add(data[i]);
							}else {
								String age = "";
								switch (i){
                                case 0:
                                    vo.setBroadDate(data[i]);
                                    break;
                                case 1:
                                	String channel = data[i];
                                	
                                	if("KBS 1TV".equals(channel)) {
                                		channel = "11";
                                	}else if ("KBS 2TV".equals(channel)) {
                                		channel = "12";
                                	}else if("MBC".equals(channel)) {
                                		channel = "13";
                                	}else if("SBS".equals(channel)) {
                                		channel = "14";
                                	}
                                    vo.setChannel(channel);
                                    break;
                                case 2:
                                	String date = vo.getBroadDate();
                                	if(date != null && !"".equals(date) && date.length() == 8 ) {
                                		date = date.substring(0, 4) +"-" + date.substring(4, 6) + "-" + date.substring(6, 8)+" ";
                                		int str = Integer.parseInt(data[i]);
                                		String time = String.format("%04d", str);
                                		time = time.substring(0,2) + ":" + time.substring(2,4) + ":00";
                                		date = date+time;
                                	}
                                	
                                	vo.setBroadDate(date);
                                    break;
                                case 3:
                                    vo.setSexGubun(data[i]);
                                    break;
                                case 4:
                                    age = data[i];
                                	
                                    break;
                                case 5:
                                	
                                	int value = Integer.parseInt(data[i]);
                                	
                                	if("0".equals(age)) {
                                		vo.setAge0Vcnt(value);
                                	}else if("20".equals(age)) {
                                		vo.setAge20Vcnt(value);
                                	}else if("30".equals(age)) {
                                		vo.setAge30Vcnt(value);
                                	}else if("40".equals(age)) {
                                		vo.setAge40Vcnt(value);
                                	}else if("50".equals(age)) {
                                		vo.setAge50Vcnt(value);
                                	}
                                    break;
                               
								}
								
							}
						}
						
        				bodyList.add(vo);
					}
					
					HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	        		HttpSession session = request.getSession(false);
	            	
	            	session.setAttribute("headerList", headerList);
	            	session.setAttribute("bodyList", bodyList);
	            	result = true;
					
				}catch(Exception e) {
					log.error(e.getMessage());
					log.error("엑셀 읽으면서 에러", e);
					result = false;
					return result;
				}finally {
					
					if(reader != null) {
						reader.close();
					}
					
					if(result == false ) {
						FileUtil.fileDelete(fileName, path);
						usageStatProgramExcelMapper.deleteExcelTmpData(fileVo);
					}
				}
				
				
			}
		}else if( cSeq == 12 ) {
			
			OPCPackage opcPackage = OPCPackage.open(new File(path + "/" + fileName));
			XSSFWorkbook workbook = null;
			
			if("US".equals(distType)) {
				
				try {
					
					List<UsageStatProgramVo> bodyList = new ArrayList<UsageStatProgramVo>();
					List<NotFoundContentsVo> ErrorList = new ArrayList<NotFoundContentsVo>();
					
					workbook = new XSSFWorkbook(opcPackage);
					XSSFSheet curSheet;
		            XSSFRow   curRow;
		            XSSFCell  curCell;
		            int lastCell = 0;
		            int haderRow = 0;
		            int firstBody = 0;
		            
		            curSheet = workbook.getSheetAt(0);
		            curRow =  curSheet.getRow(haderRow);
		            lastCell = curRow.getLastCellNum();
		            
		            UsageStatProgramVo vo = null;
		            
		            if(lastCell == 6 || lastCell == 12) {
		            	
		            	if(lastCell == 6) {
		            		firstBody = 1;
		            	}else if(lastCell == 12) {
		            		firstBody = 3;
		            	}
		            	
		            	
		            	for(int rowIndex=0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
		            		
		            		String titleStr ="";
							String dateStr ="";
							String programNm ="";
							String replaceStr ="";
		            		
		            		if(rowIndex != haderRow && rowIndex < firstBody  ) {
		            			continue;
		            		}
		            		curRow =  curSheet.getRow(rowIndex);
		            		
		            		if(curRow == null ) {
		            			continue;
		            		}
		            		
		            		NotFoundContentsVo errorVo = new NotFoundContentsVo();
		            		vo = new UsageStatProgramVo();
		            		
		            		for(int cellIndex=0;cellIndex<curRow.getPhysicalNumberOfCells(); cellIndex++) {
		            			curCell = curRow.getCell(cellIndex);
		            			if(haderRow == rowIndex) {
		            				String header = "";
		                        	if(curCell != null) {
		                        		header = curCell.getStringCellValue();
		                        	}
				                	headerList.add(header);
				                	continue;
		            			}else {
		            				String value = "";
		            				if(curCell != null){
		            							            					
			            				switch (curCell.getCellTypeEnum()){
		                                    case FORMULA:
		                                        value = curCell.getCellFormula();
		                                        break;
		                                    case NUMERIC:
		                                        value =  String.valueOf((int) Math.round(curCell.getNumericCellValue()));
		                                        break;
		                                    case STRING:
		                                        value = curCell.getStringCellValue();
		                                        break;
		                                    case BLANK:
		                                        value = "0";
		                                        break;
		                                    case ERROR:
		                                        value = curCell.getErrorCellValue()+"";
		                                        break;
		                                    default:
		                                        value = new String();
		                                        break;
	                                    }
		            				}
		            				
		            				
		            				if(lastCell == 6) {
		            					
		            					if(!"순번".equals(headerList.get(0))) {
		            						return false;
		            					}
		            					
		            					switch (cellIndex) {
		                                    case 0:  
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
		                                    		return false;
		                                    	}
		                                        break;
		                                    case 1: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
		                                    		return false;
		                                    	}
		                                        break;
		                                    case 2: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
		                                    		return false;
		                                    	}	                                    	
		            							
		                                        break;
		                                    case 3: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
		                                    		return false;
		                                    	}	 
	                                    		dateStr = StringUtil.getRexStr(value,"[0-9]{1,}회$|[0-9]{1,}회$|[0-9]{1,}회 |[0-9]{1,3}화$|[0-9]{1,3}화 |[0-9]{1,}부$|[0-9]{6,}|[0-9-]{10,}").trim(); //
	                                    		replaceStr = StringUtil.getRexStr(value,"^TEST_[a-zA-Z]{3}[0-9]{6}|^지상파_[a-zA-Z]{3}[0-9]{6}|"
		            									+ "^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}[0-9]{8}|"
		            									+ "^TV다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^TV다시보기_[a-zA-Z]{3}[0-9]{6}|"
		            									+ "^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|"
		            									+ "^TV 다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV 다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{1,}화\\s|"
		            									+ "^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회\\s|^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{4}-[0-9]{2}-[0-9]{2}\\s\\[[가-힣]{1}\\]|"
		            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\][0-9]{1,}회|"
		            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{2}\\/[0-9]{2}\\s\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]|"
		            									+ "^[a-zA-Z]{3}_HD[0-9]{6}\\[[가-힣]{1}\\]\\s|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|"
		            									+ "^[a-zA-Z]{3}_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{8}_\\[HD\\]|^[a-zA-Z]{3}_[0-9]{8}_|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}|"
		            									+ "^[a-zA-Z]{3}_[0-9]{2}-[0-9]{2}-[0-9]{2}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}");
		                                    	titleStr = value.replace( replaceStr, "").replace("_재편성", "").replace("_수정", "").replace("오늘의 추천_", "").replace("지상파_", "").replace("TV다시보기_","").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").replaceAll("[a-zA-Z]{3}_", "").replaceAll("[_]$", "").trim();
		            							titleStr = titleStr.replace(StringUtil.getRexStr(value,"[0-9]{1,}회$|\\(HD\\)$"),"").trim();
		                                    	programNm = value;
		                                    	vo.setProgramNm(titleStr);
		                                    	
	                                    		if(!"".equals(dateStr) && dateStr.length() == 6 ) {
	                                    			vo.setBroadDate("20"+dateStr.replaceAll("-", ""));
	                                    		}else if(!"".equals(dateStr) && dateStr.length() == 8 ) {
	                                    			vo.setBroadDate(dateStr.replaceAll("-", ""));
	                                    		}else {
	                                    			if(!"".equals(dateStr) && dateStr.length() < 8 ){
	                                    				vo.setVodcnt(dateStr.replaceAll("회", ""));
	                                    			}
	                                    		}
		                                    	
		                                        break;
		                                    case 4: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
		                                    		return false;
		                                    	}	 
		                                    	vo.setAmount(Integer.parseInt(value));
		                                        break;
		                                    case 5: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
		                                    		return false;
		                                    	}	 
		                                    	vo.setChargeCnt(Integer.parseInt(value));
		                                    	vo.setTotalAmount(vo.getAmount() * vo.getChargeCnt());
		                                    	vo.setProType("PPM");
		                                        break;
		                                    default:
		            					}
		            					
		            				}else if(lastCell == 12) {
		            					
		            					if(!"CP명".equals(headerList.get(0))) {
		            						return false;
		            					}
		            					
		            					switch (cellIndex) {
		                                    case 0:  
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
		                                    		return false;
		                                    	}
		                                        break;
		                                    case 1: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
		                                    		return false;
		                                    	}
		                                        break;
		                                    case 2: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
		                                    		return false;
		                                    	}
		                                    	dateStr = StringUtil.getRexStr(value,"[0-9]{1,}회$|[0-9]{1,}회$|[0-9]{1,}회 |[0-9]{1,3}화$|[0-9]{1,3}화 |[0-9]{1,}부$|[0-9]{6,}|[0-9-]{10,}").trim(); //
		                                    	replaceStr = StringUtil.getRexStr(value,"^TEST_[a-zA-Z]{3}[0-9]{6}|^지상파_[a-zA-Z]{3}[0-9]{6}|"
		            									+ "^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}[0-9]{8}|"
		            									+ "^TV다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^TV다시보기_[a-zA-Z]{3}[0-9]{6}|"
		            									+ "^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|"
		            									+ "^TV 다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV 다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{1,}화\\s|"
		            									+ "^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회\\s|^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{4}-[0-9]{2}-[0-9]{2}\\s\\[[가-힣]{1}\\]|"
		            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\][0-9]{1,}회|"
		            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{2}\\/[0-9]{2}\\s\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]|"
		            									+ "^[a-zA-Z]{3}_HD[0-9]{6}\\[[가-힣]{1}\\]\\s|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|"
		            									+ "^[a-zA-Z]{3}_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{8}_\\[HD\\]|^[a-zA-Z]{3}_[0-9]{8}_|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}|"
		            									+ "^[a-zA-Z]{3}_[0-9]{2}-[0-9]{2}-[0-9]{2}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}");
		                                    	titleStr = value.replace( replaceStr, "").replace("_재편성", "").replace("_수정", "").replace("오늘의 추천_", "").replace("지상파_", "").replace("TV다시보기_","").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").replaceAll("[a-zA-Z]{3}_", "").replaceAll("[_]$", "").trim();
		            							titleStr = titleStr.replace(StringUtil.getRexStr(value,"[0-9]{1,}회$|\\(HD\\)$"),"").trim();
		            							
		            							programNm = value;
		                                        
		            							vo.setProgramNm(titleStr);
		            							if(!"".equals(dateStr) && dateStr.length() == 6 ) {
	                                    			vo.setBroadDate("20"+dateStr.replaceAll("-", ""));
	                                    		}else if(!"".equals(dateStr) && dateStr.length() == 8 ) {
	                                    			vo.setBroadDate(dateStr.replaceAll("-", ""));
	                                    		}else {
	                                    			if(!"".equals(dateStr) && dateStr.length() < 8 ){
	                                    				vo.setVodcnt(dateStr.replaceAll("회", ""));
	                                    			}
	                                    		}
		            							
		                                        break;
		                                    case 3: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
		                                    		return false;
		                                    	}
		                                    	if("".equals(dateStr)){
		                                    		dateStr = StringUtil.getRexStr(value,"[0-9]{1,}회$|[0-9]{1,}회$|[0-9]{1,}회 |[0-9]{1,3}화$|[0-9]{1,3}화 |[0-9]{1,}부$|[0-9]{6,}|[0-9-]{10,}").trim(); //
		                                    		
		                                    		if(!"".equals(dateStr) && dateStr.length() == 6 ) {
		                                    			vo.setBroadDate("20"+dateStr.replaceAll("-", ""));
		                                    		}else if(!"".equals(dateStr) && dateStr.length() == 8 ) {
		                                    			vo.setBroadDate(dateStr.replaceAll("-", ""));
		                                    		}else {
		                                    			if(!"".equals(dateStr) && dateStr.length() < 8 ){
		                                    				vo.setVodcnt(dateStr.replaceAll("회", ""));
		                                    			}
		                                    		}
		                                    		
		                                    	}
		                                    	if("".equals(titleStr)) {
		                                    		replaceStr = StringUtil.getRexStr(value,"^TEST_[a-zA-Z]{3}[0-9]{6}|^지상파_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}[0-9]{8}|"
			            									+ "^TV다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^TV다시보기_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|"
			            									+ "^TV 다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV 다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{1,}화\\s|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회\\s|^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{4}-[0-9]{2}-[0-9]{2}\\s\\[[가-힣]{1}\\]|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\][0-9]{1,}회|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{2}\\/[0-9]{2}\\s\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]|"
			            									+ "^[a-zA-Z]{3}_HD[0-9]{6}\\[[가-힣]{1}\\]\\s|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|"
			            									+ "^[a-zA-Z]{3}_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{8}_\\[HD\\]|^[a-zA-Z]{3}_[0-9]{8}_|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^[a-zA-Z]{3}_[0-9]{2}-[0-9]{2}-[0-9]{2}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}");
			                                    	titleStr = value.replace( replaceStr, "").replace("_재편성", "").replace("_수정", "").replace("오늘의 추천_", "").replace("지상파_", "").replace("TV다시보기_","").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").replaceAll("[a-zA-Z]{3}_", "").replaceAll("[_]$", "").trim();
			            							titleStr = titleStr.replace(StringUtil.getRexStr(value,"[0-9]{1,}회$|\\(HD\\)$"),"").trim();
			            							vo.setProgramNm(titleStr);
		                                    	}
		                                    	
		                                        break;
		                                    case 4: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
		                                    		return false;
		                                    	}
		                                    	if("VOD시리즈".equals(value)){
		                                    		vo.setVodcnt("1");
		                                    	}
		                                    	
		                                        break;
		                                    case 5: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
		                                    		return false;
		                                    	}
		                                    	vo.setScreenType(value);
		                                        break;
		                                    case 6: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
		                                    		return false;
		                                    	}
		                                    	if("유료".equals(value)) {
		                                    		value = "Y";
		                                    	}else {
		                                    		value = "N";
		                                    	}
		                                    	
		                                        vo.setChargeYn(value);
		                                        break;
		                                    case 7: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
		                                    		return false;
		                                    	}
		                                        vo.setAmount(Integer.parseInt(value));
		                                        break;
		                                    case 8: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
		                                    		return false;
		                                    	}
		                                        break;
		                                        
		                                    case 9: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
		                                    		return false;
		                                    	}
		                                        break;
		                                    case 10: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
		                                    		return false;
		                                    	}
		                                        vo.setChargeCnt(Integer.parseInt(value));
		                                        break;
		                                    case 11: 
		                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
		                                    		return false;
		                                    	}
		                                    	vo.setTotalAmount(Integer.parseInt(value));
		                                    	vo.setProType("PPV");
		                                        break;
		                                    default:
		            					}
		            				
		            				}
		            				
		            				
		            				
		            			}
		            		}
		            		if(rowIndex !=haderRow && ( "".equals(titleStr) || "".equals(dateStr) )) {
	            				errorVo.setTitle(titleStr);
	            				errorVo.setBroadDate(dateStr);
	            				errorVo.setProgramNm(programNm);
	            				errorVo.setRow(rowIndex);
	            				ErrorList.add(errorVo);
//	            				continue;
	            			}else if (rowIndex !=haderRow && ( !"".equals(titleStr) && !"".equals(dateStr) )){
	            				
	            			}
		            		
		            		vo.setTempData(programNm+"|"+titleStr+"|"+dateStr);
		            		
            				bodyList.add(vo);
		            	}
		            	
		            	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		        		HttpSession session = request.getSession(false);
		            	
		            	model.addAttribute("errorList", ErrorList);
		            	session.setAttribute("headerList", headerList);
		            	session.setAttribute("bodyList", bodyList);
		            	result = true;
		            	
		            }else {
		            	result = false;
		            }
		            		            
				}catch(Exception e) {
					log.error(e.getMessage());
					log.error("엑셀 읽으면서 에러", e);
					result = false;
					return result;
				}finally {
					workbook.close();
					
					if(result == false ) {
						FileUtil.fileDelete(fileName, path);
						usageStatProgramExcelMapper.deleteExcelTmpData(fileVo);
					}
				}
				
			}
		}else if(cSeq == 13) {

			if("US".equals(distType)) {
				
				OPCPackage opcPackage = OPCPackage.open(new File(path + "/" + fileName));
				XSSFWorkbook workbook = null;
				
				try {
					
					List<UsageStatProgramVo> bodyList = new ArrayList<UsageStatProgramVo>();
					List<NotFoundContentsVo> ErrorList = new ArrayList<NotFoundContentsVo>();
					
					workbook = new XSSFWorkbook(opcPackage);
					
					XSSFSheet curSheet;
		            XSSFRow   curRow;
		            XSSFCell  curCell;
		            int lastCell = 0;
		            int haderRow = 0;
		            int firstBody = 1;
		            
		            curSheet = workbook.getSheetAt(0);
		            curRow =  curSheet.getRow(haderRow);
		            lastCell = curRow.getLastCellNum();
		            
		            UsageStatProgramVo vo = null;
		            
		            if(lastCell == 6) {
		            	
		            	for(int rowIndex=0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
		            		if(rowIndex != haderRow && rowIndex < firstBody  ) {
		            			continue;
		            		}
		            		curRow =  curSheet.getRow(rowIndex);
		            		if(curRow == null ) {
		            			continue;
		            		}
		            		
		            		String titleStr ="";
							String dateStr ="";
							String programNm ="";
							String replaceStr ="";
		            		
		            		NotFoundContentsVo errorVo = new NotFoundContentsVo();
		            		vo = new UsageStatProgramVo();
		            		for(int cellIndex=0;cellIndex<curRow.getPhysicalNumberOfCells(); cellIndex++) {
		            			curCell = curRow.getCell(cellIndex);
		            			if(haderRow == rowIndex) {
		            				String header = "";
		                        	if(curCell != null) {
		                        		header = curCell.getStringCellValue();
		                        	}
				                	headerList.add(header);
				                	continue;
		            			}else {
		            				String value = "";
		            				if(curCell != null){
		            							            					
			            				switch (curCell.getCellTypeEnum()){
		                                    case FORMULA:
		                                        value = curCell.getCellFormula();
		                                        break;
		                                    case NUMERIC:
		                                        value =  String.valueOf((int) Math.round(curCell.getNumericCellValue()));
		                                        break;
		                                    case STRING:
		                                        value = curCell.getStringCellValue();
		                                        break;
		                                    case BLANK:
		                                        value = "-1";
		                                        break;
		                                    case ERROR:
		                                        value = curCell.getErrorCellValue()+"";
		                                        break;
		                                    default:
		                                        value = new String();
		                                        break;
	                                    }
		            				}
		            				
		            				
	            					
	            					if(!"콘텐츠명".equals(headerList.get(0))) {
	            						return false;
	            					}
	            					
	            					switch (cellIndex) {
	                                    case 0:  
	                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
	                                    		return false;
	                                    	}
                                    		replaceStr = StringUtil.getRexStr(value,"^TEST_[a-zA-Z]{3}[0-9]{6}|^지상파_[a-zA-Z]{3}[0-9]{6}|"
	            									+ "^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}[0-9]{8}|"
	            									+ "^TV다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^TV다시보기_[a-zA-Z]{3}[0-9]{6}|"
	            									+ "^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|"
	            									+ "^TV 다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV 다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{1,}화\\s|"
	            									+ "^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회\\s|^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{4}-[0-9]{2}-[0-9]{2}\\s\\[[가-힣]{1}\\]|"
	            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\][0-9]{1,}회|"
	            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{2}\\/[0-9]{2}\\s\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]|"
	            									+ "^[a-zA-Z]{3}_HD[0-9]{6}\\[[가-힣]{1}\\]\\s|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|"
	            									+ "^[a-zA-Z]{3}_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{8}_\\[HD\\]|^[a-zA-Z]{3}_[0-9]{8}_|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}|"
	            									+ "^[a-zA-Z]{3}_[0-9]{2}-[0-9]{2}-[0-9]{2}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}");
	                                    	titleStr = value.replace( replaceStr, "").replace("_재편성", "").replace("_수정", "").replace("오늘의 추천_", "").replace("지상파_", "").replace("TV다시보기_","").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").replaceAll("[a-zA-Z]{3}_", "").replaceAll("[_]$", "").trim();
	            							titleStr = titleStr.replace(StringUtil.getRexStr(value,"^[0-9]{1,}회|[0-9]{1,}회$|\\(HD\\)$"),"").trim();
	                                    	programNm = value;
	                                    	vo.setProgramNm(titleStr);
	                                        break;
	                                    case 1: 
	                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
	                                    		return false;
	                                    	}
	                                    	dateStr = value;
	                                    	vo.setBroadDate(value);
	                                        break;
	                                    case 2: 
	                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
	                                    		return false;
	                                    	}	                                    	
	                                    	vo.setProType(value);
	                                        break;
	                                    case 3: 
	                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
	                                    		return false;
	                                    	}
	                                    	vo.setViewDate(value);
	                                        break;
	                                    case 4: 
	                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
	                                    		return false;
	                                    	}
	                                    	vo.setJoinCnt(Integer.parseInt(value));
	                                        break;
	                                    case 5: 
	                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
	                                    		return false;
	                                    	}
	                                    	vo.setChargeCnt(Integer.parseInt(value));
	                                        break;
	                                    default:
	            					}
		            					
		            			}
		            		}
		            		if(rowIndex !=haderRow && ( "".equals(titleStr) || "".equals(dateStr) )) {
	            				errorVo.setTitle(titleStr);
	            				errorVo.setBroadDate(dateStr);
	            				errorVo.setProgramNm(programNm);
	            				errorVo.setRow(rowIndex);
	            				ErrorList.add(errorVo);
	            				continue;
	            			}
		            		vo.setTempData(programNm+"|"+titleStr+"|"+dateStr);
            				bodyList.add(vo);
		            	}
		            	
		            	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		        		HttpSession session = request.getSession(false);
		            	
		            	model.addAttribute("errorList", ErrorList);
		            	session.setAttribute("headerList", headerList);
		            	session.setAttribute("bodyList", bodyList);
		            	result = true;
		            	
		            }else {
		            	result = false;
		            }
		            		            
				}catch(Exception e) {
					log.error(e.getMessage());
					log.error("엑셀 읽으면서 에러", e);
					result = false;
					return result;
				}finally {
					workbook.close();
					if(result == false ) {
						FileUtil.fileDelete(fileName, path);
						usageStatProgramExcelMapper.deleteExcelTmpData(fileVo);
					}
				}
				
			}
		
		}else if(cSeq == 14) {

			if("US".equals(distType)) {
				
				int idx = fileName.lastIndexOf(".");
				String _fileName = fileName.substring(idx, fileName.length());
				
				FileInputStream fis = null;
				XSSFWorkbook workbook = null;
				HSSFWorkbook workbook2= null;
				OPCPackage opcPackage = null;
				
				String titleStr ="";
				String dateStr ="";
				String programNm ="";
				String replaceStr ="";
				String subName = "";
				try {
					
					List<UsageStatProgramVo> bodyList = new ArrayList<UsageStatProgramVo>();
					List<NotFoundContentsVo> ErrorList = new ArrayList<NotFoundContentsVo>();
					fis = new FileInputStream(path + "/" + fileName);
					
					UsageStatProgramVo vo = null;
					if(".xls".equals(_fileName) ) {
						workbook2=new HSSFWorkbook(fis);
						
						HSSFSheet sheet;
						HSSFRow row;
						HSSFCell cell;
						
						int lastCell = 0;
			            int haderRow = 0;
			            int firstBody = 1;		
			            
			            sheet = workbook2.getSheetAt(0);
			            row = sheet.getRow(haderRow);
			            
			            lastCell = row.getLastCellNum();
			            
			            if(lastCell == 6 ) {
			            	for(int rowIndex=0; rowIndex < sheet.getLastRowNum(); rowIndex++) {
			            		if(rowIndex != haderRow && rowIndex < firstBody  ) {
			            			continue;
			            		}
			            		row =  sheet.getRow(rowIndex);
			            		
			            		if(row == null ) {
			            			continue;
			            		}
			            		subName = "";
			            		titleStr ="";
			    				dateStr ="";
			    				programNm ="";
			    				replaceStr ="";
			            		NotFoundContentsVo errorVo = new NotFoundContentsVo();
			            		vo = new UsageStatProgramVo();
			            		for(int cellIndex=0;cellIndex<row.getPhysicalNumberOfCells(); cellIndex++) {
			            			
			            			cell = row.getCell(cellIndex);
			            			if(haderRow == rowIndex) {
			            				String header = "";
			                        	if(cell != null) {
			                        		header = cell.getStringCellValue();
			                        	}
					                	headerList.add(header);
					                	continue;
			            			}else {
			            				String value = "";
			            				
			            				if(cell != null){
			            							            					
				            				switch (cell.getCellTypeEnum()){
			                                    case FORMULA:
			                                        value = cell.getCellFormula();
			                                        break;
			                                    case NUMERIC:
			                                        value =  String.valueOf((int) Math.round(cell.getNumericCellValue()));
			                                        break;
			                                    case STRING:
			                                        value = cell.getStringCellValue();
			                                        break;
			                                    case BLANK:
			                                        value = "0";
			                                        break;
			                                    case ERROR:
			                                        value = cell.getErrorCellValue()+"";
			                                        break;
			                                    default:
			                                        value = new String();
			                                        break;
		                                    }
				            				
			            					if(!"0.기준일자".equals(headerList.get(0))) {
			            						return false;
			            					}
			            					
			            					switch (cellIndex) {
			                                    case 0:  
			                                    	if(!"".equals(value) && cell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}
			                                    	
			                                    	vo.setViewDate(value);
			                                        break;
			                                    case 1: 
			                                    	if(!"".equals(value) && cell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}
			                                        break;
			                                    case 2: 
			                                    	if(!"".equals(value) && cell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}                                    	
			                                    	replaceStr = StringUtil.getRexStr(value,"^TEST_[a-zA-Z]{3}[0-9]{6}|^지상파_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}[0-9]{8}|"
			            									+ "^TV다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^TV다시보기_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|"
			            									+ "^TV 다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV 다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{1,}화\\s|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회\\s|^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{4}-[0-9]{2}-[0-9]{2}\\s\\[[가-힣]{1}\\]|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\][0-9]{1,}회|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{2}\\/[0-9]{2}\\s\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]|"
			            									+ "^[a-zA-Z]{3}_HD[0-9]{6}\\[[가-힣]{1}\\]\\s|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|"
			            									+ "^[a-zA-Z]{3}_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{8}_\\[HD\\]|^[a-zA-Z]{3}_[0-9]{8}_|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^[a-zA-Z]{3}_[0-9]{2}-[0-9]{2}-[0-9]{2}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}");
			                                    	titleStr = value.replace( replaceStr, "").replace("_재편성", "").replace("_수정", "").replace("오늘의 추천_", "").replace("지상파_", "").replace("TV다시보기_","").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").replaceAll("[a-zA-Z]{3}_", "").replaceAll("[_]$", "").trim();
			            							titleStr = titleStr.replace(StringUtil.getRexStr(value,"[0-9]{1,}회$|\\(HD\\)$"),"").trim();
			                                    	
			            							programNm = value;
			                                    	vo.setProgramNm(titleStr);
			                                        break;
			                                    case 3: 
			                                    	if(!"".equals(value) && cell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}
			                                    	dateStr = StringUtil.getRexStr(value,"[0-9]{1,}회$|[0-9]{1,}회$|[0-9]{1,}회 |[0-9]{1,3}화$|[0-9]{1,3}화 |[0-9]{1,}부$|[0-9]{6,}|[0-9-]{10,}").trim(); //
			                                    	if(!"".equals(dateStr) && dateStr.length() == 6 ) {
		                                    			vo.setBroadDate("20"+dateStr);
		                                    		}else if(!"".equals(dateStr) && dateStr.length() == 8 ) {
		                                    			vo.setBroadDate(dateStr);
		                                    		}else {
		                                    			if(!"".equals(dateStr) && dateStr.length() < 8 ){
		                                    				vo.setVodcnt(dateStr.replaceAll("회", ""));
		                                    			}
		                                    		}
			                                        break;
			                                    case 4: 
			                                    	if(!"".equals(value) && cell.getCellTypeEnum() != CellType.NUMERIC) {
			                                    		return false;
			                                    	}
			                                    	vo.setJoinCnt(Integer.parseInt(value));
			                                        break;
			                                    case 5: 
			                                    	if(!"".equals(value) && cell.getCellTypeEnum() != CellType.NUMERIC) {
			                                    		return false;
			                                    	}
			                                    	vo.setChargeCnt(Integer.parseInt(value));
			                                    	vo.setProType("PPM");
			                                        break;
			                                    default:
			            					}
			            				}
			            				
		            				}
			            		}
			            		if(rowIndex !=haderRow && ( "".equals(titleStr) || "".equals(dateStr) )) {
		            				errorVo.setTitle(titleStr);
		            				errorVo.setBroadDate(dateStr);
		            				errorVo.setProgramNm(programNm);
		            				errorVo.setRow(rowIndex);
		            				ErrorList.add(errorVo);
		            				continue;
		            			}
			            		vo.setTempData(programNm+"|"+titleStr+"|"+subName+ "|"+dateStr);
	            				bodyList.add(vo);
			            	}
		            	
			            	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			        		HttpSession session = request.getSession(false);
			            	
			            	model.addAttribute("errorList", ErrorList);
			            	session.setAttribute("headerList", headerList);
			            	session.setAttribute("bodyList", bodyList);
			            	result = true;
			            	
			            }else {
			            	
			            	result = false;
			            }
					}else {
						opcPackage = OPCPackage.open(new File(path + "/" + fileName));
						workbook = new XSSFWorkbook(opcPackage);	
						
						XSSFSheet curSheet;
			            XSSFRow   curRow;
			            XSSFCell  curCell;
			            int lastCell = 0;
			            int haderRow = 2;
			            int firstBody = 3;
			            
			            curSheet = workbook.getSheetAt(0);
			            curRow =  curSheet.getRow(haderRow);
			            lastCell = curRow.getLastCellNum();
						
						 if(lastCell == 8 || lastCell == 6) {
							 
							 if(lastCell == 8) {
								 haderRow = 2;
								 firstBody = 3;
							 }else {
								 haderRow = 0;
						         firstBody = 1;		
							 }
							 
			            	for(int rowIndex=0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
			            		if(rowIndex != haderRow && rowIndex < firstBody  ) {
			            			continue;
			            		}
			            		curRow =  curSheet.getRow(rowIndex);
			            		
			            		if(curRow == null ) {
			            			continue;
			            		}
			            		
			            		subName = "";
			            		titleStr ="";
			    				dateStr ="";
			    				programNm ="";
			    				replaceStr ="";
			    				
			            		NotFoundContentsVo errorVo = new NotFoundContentsVo();
			            		vo = new UsageStatProgramVo();
			            		for(int cellIndex=0;cellIndex<curRow.getPhysicalNumberOfCells(); cellIndex++) {
			            			curCell = curRow.getCell(cellIndex);
			            			if(cellIndex == 0) {
			            				continue;
			            			}
			            			
			            			if(haderRow == rowIndex) {
			            				String header = "";
			                        	if(curCell != null) {
			                        		header = curCell.getStringCellValue();
			                        	}
					                	headerList.add(header);
					                	continue;
			            			}else {
			            				String value = "";
			            				
			            				if(curCell != null){
			            							            					
				            				switch (curCell.getCellTypeEnum()){
			                                    case FORMULA:
			                                        value = curCell.getCellFormula();
			                                        break;
			                                    case NUMERIC:
			                                        value =  String.valueOf((int) Math.round(curCell.getNumericCellValue()));
			                                        break;
			                                    case STRING:
			                                        value = curCell.getStringCellValue();
			                                        break;
			                                    case BLANK:
			                                        value = "0";
			                                        break;
			                                    case ERROR:
			                                        value = curCell.getErrorCellValue()+"";
			                                        break;
			                                    default:
			                                        value = new String();
			                                        break;
		                                    }
			            				}
			            				if(lastCell == 6) {
			            					if(!"0.기준일자".equals(headerList.get(0))) {
			            						return false;
			            					}
			            					
			            					switch (cellIndex) {
			                                    case 0:  
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}
			                                    	
			                                    	vo.setViewDate(value);
			                                        break;
			                                    case 1: 
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}
			                                        break;
			                                    case 2: 
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}                                    	
			                                    	replaceStr = StringUtil.getRexStr(value,"^TEST_[a-zA-Z]{3}[0-9]{6}|^지상파_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}[0-9]{8}|"
			            									+ "^TV다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^TV다시보기_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|"
			            									+ "^TV 다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV 다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{1,}화\\s|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회\\s|^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{4}-[0-9]{2}-[0-9]{2}\\s\\[[가-힣]{1}\\]|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\][0-9]{1,}회|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{2}\\/[0-9]{2}\\s\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]|"
			            									+ "^[a-zA-Z]{3}_HD[0-9]{6}\\[[가-힣]{1}\\]\\s|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|"
			            									+ "^[a-zA-Z]{3}_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{8}_\\[HD\\]|^[a-zA-Z]{3}_[0-9]{8}_|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^[a-zA-Z]{3}_[0-9]{2}-[0-9]{2}-[0-9]{2}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}");
			                                    	titleStr = value.replace( replaceStr, "").replace("_재편성", "").replace("_수정", "").replace("오늘의 추천_", "").replace("지상파_", "").replace("TV다시보기_","").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").replaceAll("[a-zA-Z]{3}_", "").replaceAll("[_]$", "").trim();
			            							titleStr = titleStr.replace(StringUtil.getRexStr(value,"[0-9]{1,}회$|\\(HD\\)$"),"").trim();
			                                    	
			            							programNm = value;
			                                    	vo.setProgramNm(titleStr);
			                                        break;
			                                    case 3: 
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}
			                                    	dateStr = StringUtil.getRexStr(value,"[0-9]{1,}회$|[0-9]{1,}회$|[0-9]{1,}회 |[0-9]{1,3}화$|[0-9]{1,3}화 |[0-9]{1,}부$|[0-9]{6,}|[0-9-]{10,}").trim(); //
			                                    	if(!"".equals(dateStr) && dateStr.length() == 6 ) {
		                                    			vo.setBroadDate("20"+dateStr);
		                                    		}else if(!"".equals(dateStr) && dateStr.length() == 8 ) {
		                                    			vo.setBroadDate(dateStr);
		                                    		}else {
		                                    			if(!"".equals(dateStr) && dateStr.length() < 8 ){
		                                    				vo.setVodcnt(dateStr.replaceAll("회", ""));
		                                    			}
		                                    		}
			                                        break;
			                                    case 4: 
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
			                                    		return false;
			                                    	}
			                                    	vo.setJoinCnt(Integer.parseInt(value));
			                                        break;
			                                    case 5: 
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
			                                    		return false;
			                                    	}
			                                    	vo.setChargeCnt(Integer.parseInt(value));
			                                    	vo.setProType("PPM");
			                                        break;
			                                    default:
			            					}
			            				}if(lastCell == 8) {
			            					if(!"기준일자".equals(headerList.get(0))) {
			            						return false;
			            					}
			            					switch (cellIndex) {
			                                    case 1:  
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}
			                                    	vo.setViewDate(value);
			                                        break;
			                                    case 2: 
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}
			                                        break;
			                                    case 3: 
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}                                    	
			                                        break;
			                                    case 4: 
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}
			                                    	replaceStr = StringUtil.getRexStr(value,"^TEST_[a-zA-Z]{3}[0-9]{6}|^지상파_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}[0-9]{8}|"
			            									+ "^TV다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^TV다시보기_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|"
			            									+ "^TV 다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV 다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{1,}화\\s|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회\\s|^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{4}-[0-9]{2}-[0-9]{2}\\s\\[[가-힣]{1}\\]|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\][0-9]{1,}회|"
			            									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{2}\\/[0-9]{2}\\s\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]|"
			            									+ "^[a-zA-Z]{3}_HD[0-9]{6}\\[[가-힣]{1}\\]\\s|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|"
			            									+ "^[a-zA-Z]{3}_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{8}_\\[HD\\]|^[a-zA-Z]{3}_[0-9]{8}_|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}|"
			            									+ "^[a-zA-Z]{3}_[0-9]{2}-[0-9]{2}-[0-9]{2}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}");
			                                    	titleStr = value.replace( replaceStr, "").replace("_재편성", "").replace("_수정", "").replace("오늘의 추천_", "").replace("지상파_", "").replace("TV다시보기_","").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").replaceAll("[a-zA-Z]{3}_", "").replaceAll("[_]$", "").trim();
			            							titleStr = titleStr.replace(StringUtil.getRexStr(value,"[0-9]{1,}회$|\\(HD\\)$"),"").trim();
			                                    	
			            							programNm = value;
			                                    	vo.setProgramNm(titleStr);
			                                        break;
			                                    case 5: 
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
			                                    		return false;
			                                    	}
			                                    	dateStr = StringUtil.getRexStr(value,"[0-9]{1,}회$|[0-9]{1,}회$|[0-9]{1,}회 |[0-9]{1,3}화$|[0-9]{1,3}화 |[0-9]{1,}부$|[0-9]{6,}|[0-9-]{10,}").trim(); //
			                                    	if(!"".equals(dateStr) && dateStr.length() == 6 ) {
		                                    			vo.setBroadDate("20"+dateStr);
		                                    		}else if(!"".equals(dateStr) && dateStr.length() == 8 ) {
		                                    			vo.setBroadDate(dateStr);
		                                    		}else {
		                                    			if(!"".equals(dateStr) && dateStr.length() < 8 ){
		                                    				vo.setVodcnt(dateStr.replaceAll("회", ""));
		                                    			}
		                                    		}
			                                    	subName = value;
			                                        break;
			                                    case 6: 
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
			                                    		return false;
			                                    	}
			                                    	vo.setChargeCnt(Integer.parseInt(value));
			                                        break;
			                                    case 7: 
			                                    	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
			                                    		return false;
			                                    	}
			                                    	vo.setTotalAmount(Integer.parseInt(value));
			                                    	vo.setProType("PPV");
			                                        break;
			                                    default:
			            					}
			            				}
			            			}
			            		}
			            		if(rowIndex !=haderRow && ( "".equals(titleStr) || "".equals(dateStr) )) {
		            				errorVo.setTitle(titleStr);
		            				errorVo.setBroadDate(dateStr);
		            				errorVo.setProgramNm(programNm);
		            				errorVo.setRow(rowIndex);
		            				ErrorList.add(errorVo);
		            				continue;
		            			}
			            		vo.setTempData(programNm+"|"+titleStr+"|"+subName+ "|"+dateStr);
	            				bodyList.add(vo);
			            	}
			            	
			            	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			        		HttpSession session = request.getSession(false);
			            	
			            	model.addAttribute("errorList", ErrorList);
			            	session.setAttribute("headerList", headerList);
			            	session.setAttribute("bodyList", bodyList);
			            	result = true;
			            	
			            }else {
			            	
			            	result = false;
			            }
					}
				}catch(Exception e) {
					log.error(e.getMessage());
					log.error("엑셀 읽으면서 에러", e);
					result = false;
					return result;
				}finally {
					if(fis != null) {
						fis.close();	
					}
					
					if(workbook != null) {
						workbook.close();
					}
					
					if(workbook2 != null) {
						workbook2.close();
					}
					if(result == false ) {
						FileUtil.fileDelete(fileName, path);
						usageStatProgramExcelMapper.deleteExcelTmpData(fileVo);
					}
				}
				
			}
		}else if(cSeq == 15) {
			
			OPCPackage opcPackage = OPCPackage.open(new File(path + "/" + fileName));
			XSSFWorkbook workbook = null;
			log.debug("FILE DIR:====>"+path + "/" + fileName);
			try {
				
				List<UsageStatProgramVo> bodyList = new ArrayList<UsageStatProgramVo>();
				List<NotFoundContentsVo> ErrorList = new ArrayList<NotFoundContentsVo>();
				
				String titleStr ="";
				String dateStr ="";
				String programNm ="";
				String replaceStr ="";
				
				workbook = new XSSFWorkbook(opcPackage);	
				
				XSSFSheet curSheet;
	            XSSFRow   curRow;
	            XSSFCell  curCell;
	            int lastCell = 0;
	            int haderRow = 0;
	            int firstBody = 2;
	            
	            curSheet = workbook.getSheetAt(0);
	            
	            int sheetCnt = workbook.getNumberOfSheets();
	            
	            for(int sheetIndex = 0; sheetIndex < sheetCnt; sheetIndex++) {
	            	
	            	curSheet = workbook.getSheetAt(sheetIndex);
	            	
	            	curRow =  curSheet.getRow(haderRow);
		            lastCell = curRow.getPhysicalNumberOfCells();
		            
		            UsageStatProgramVo vo = null;
		            
		            if(lastCell == 11) {
		            	
		            	for(int rowIndex=0; rowIndex < curSheet.getPhysicalNumberOfRows(); rowIndex++) {
		            		if(rowIndex != haderRow && rowIndex < firstBody  ) {
		            			continue;
		            		}
		            		curRow =  curSheet.getRow(rowIndex);
	            		
		            		if(curRow == null ) {
		            			continue;
		            		}
		            		
		            		NotFoundContentsVo errorVo = new NotFoundContentsVo();
		            		vo = new UsageStatProgramVo();
		            		for(int cellIndex=0;cellIndex<curRow.getPhysicalNumberOfCells(); cellIndex++) {
		            			curCell = curRow.getCell(cellIndex);
		            			if(cellIndex == 0) {
		            				continue;
		            			}
	            			
		            			if(haderRow == rowIndex) {
		            				String header = "";
		            				if(curCell != null) {
		            					header = curCell.getStringCellValue();
		            				}
		            				headerList.add(header);
		            				continue;
		            			}else {
		            				String value = "";
		            				if(curCell != null){
		            					
		            					switch (curCell.getCellTypeEnum()){
		            						case FORMULA:
		            							value = curCell.getCellFormula();
		            							break;
		                                    case NUMERIC:
		                                        value =  String.valueOf((int) Math.round(curCell.getNumericCellValue()));
		                                        break;
		                                    case STRING:
		                                        value = curCell.getStringCellValue();
		                                        break;
		                                    case BLANK:
		                                        value = "0";
		                                        break;
		                                    case ERROR:
		                                        value = curCell.getErrorCellValue()+"";
		                                        break;
		                                    default:
		                                        value = new String();
		                                        break;
		                                }
		            				}
		            				
		            				switch (cellIndex) {
		            					case 1:
		            						if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
	                                    		return false;
	                                    	}
		            						vo.setCSeqNm(value);
		            						break;
		                                case 2:
		                                	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.STRING) {
	                                    		return false;
	                                    	}
		                                	dateStr = StringUtil.getRexStr(value,"[0-9]{1,}회$|[0-9]{1,}회$|[0-9]{1,}회 |[0-9]{1,3}화$|[0-9]{1,3}화 |[0-9]{1,}부$|[0-9]{6,}|[0-9-]{10,}").trim(); //
		                                	
		                                	replaceStr = StringUtil.getRexStr(value,"^TEST_[a-zA-Z]{3}[0-9]{6}|^지상파_[a-zA-Z]{3}[0-9]{6}|"
		        									+ "^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}[0-9]{8}|"
		        									+ "^TV다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^TV다시보기_[a-zA-Z]{3}[0-9]{6}|"
		        									+ "^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|"
		        									+ "^TV 다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV 다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{1,}화\\s|"
		        									+ "^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회\\s|^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{4}-[0-9]{2}-[0-9]{2}\\s\\[[가-힣]{1}\\]|"
		        									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\][0-9]{1,}회|"
		        									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{2}\\/[0-9]{2}\\s\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]|"
		        									+ "^[a-zA-Z]{3}_HD[0-9]{6}\\[[가-힣]{1}\\]\\s|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|"
		        									+ "^[a-zA-Z]{3}_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{8}_\\[HD\\]|^[a-zA-Z]{3}_[0-9]{8}_|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}|"
		        									+ "^[a-zA-Z]{3}_[0-9]{2}-[0-9]{2}-[0-9]{2}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}");
		                                	titleStr = value.replace( replaceStr, "").replace("_재편성", "").replace("_수정", "").replace("오늘의 추천_", "").replace("지상파_", "").replace("TV다시보기_","").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").replaceAll("[a-zA-Z]{3}_", "").replaceAll("[_]$", "").trim();
		        							titleStr = titleStr.replace(StringUtil.getRexStr(value,"[0-9]{1,}회$|\\(HD\\)$"),"").trim();
		                                	
		        							programNm = value;
		                                	vo.setProgramNm(titleStr);
		                                	
		                                	if(!"".equals(dateStr) && dateStr.length() == 6 ) {
                                    			vo.setBroadDate("20"+dateStr);
                                    		}else if(!"".equals(dateStr) && dateStr.length() == 8 ) {
                                    			vo.setBroadDate(dateStr);
                                    		}else {
                                    			if(!"".equals(dateStr) && dateStr.length() < 8 ){
                                    				vo.setVodcnt(dateStr.replaceAll("회", ""));
                                    			}
                                    		}
		                                	
		            						break;
		                                case 4:
		                                	
		                                	if(!"".equals(value) && curCell.getCellTypeEnum() != CellType.NUMERIC) {
	                                    		return false;
	                                    	}
		                                	vo.setProType(curSheet.getRow(0).getCell(cellIndex).toString());
		                                	curSheet.getRow(1).getCell(cellIndex).setCellType(CellType.STRING);
		                                	vo.setViewDate(curSheet.getRow(1).getCell(cellIndex).getStringCellValue());
		                                	
		            						vo.setChargeCnt(Integer.parseInt(value));
		            						
		            						bodyList.add(vo);
		            						
		            						break;
		                                case 5:
		                                	vo.setProType(curSheet.getRow(0).getCell(cellIndex).toString());
		                                	curSheet.getRow(1).getCell(cellIndex).setCellType(CellType.STRING);
		                                	vo.setViewDate(curSheet.getRow(1).getCell(cellIndex).getStringCellValue());
		                                	
		            						vo.setChargeCnt(Integer.parseInt(value));
		            						
		            						bodyList.add(vo);
		            						
		            						
		            						break;	
		                                case 6:
		                                	vo.setProType(curSheet.getRow(0).getCell(cellIndex).toString());
		                                	curSheet.getRow(1).getCell(cellIndex).setCellType(CellType.STRING);
		                                	vo.setViewDate(curSheet.getRow(1).getCell(cellIndex).getStringCellValue());
		                                	
		            						vo.setChargeCnt(Integer.parseInt(value));
		            						
		            						bodyList.add(vo);
		            						
		            						
		            						break;		
		                                case 7:
		                                	vo.setProType(curSheet.getRow(0).getCell(cellIndex).toString());
		                                	curSheet.getRow(1).getCell(cellIndex).setCellType(CellType.STRING);
		                                	vo.setViewDate(curSheet.getRow(1).getCell(cellIndex).getStringCellValue());
		                                	
		            						vo.setChargeCnt(Integer.parseInt(value));
		            						
		            						bodyList.add(vo);
		            						
		            						break;	
		                                case 8:
		                                	vo.setProType(curSheet.getRow(0).getCell(cellIndex).toString());
		                                	curSheet.getRow(1).getCell(cellIndex).setCellType(CellType.STRING);
		                                	vo.setViewDate(curSheet.getRow(1).getCell(cellIndex).getStringCellValue());
		                                	
		            						vo.setChargeCnt(Integer.parseInt(value));
		            						
		            						bodyList.add(vo);
		            						
		            						break;
		                                case 9:
		                                	vo.setProType(curSheet.getRow(0).getCell(cellIndex).toString());
		                                	curSheet.getRow(1).getCell(cellIndex).setCellType(CellType.STRING);
		                                	vo.setViewDate(curSheet.getRow(1).getCell(cellIndex).getStringCellValue());
		                                	
		            						vo.setChargeCnt(Integer.parseInt(value));
		            						
		            						bodyList.add(vo);
		            						
		            						break;	
		                                case 10:
		                                	vo.setProType(curSheet.getRow(0).getCell(cellIndex).toString());
		                                	curSheet.getRow(1).getCell(cellIndex).setCellType(CellType.STRING);
		                                	vo.setViewDate(curSheet.getRow(1).getCell(cellIndex).getStringCellValue());
		                                	
		            						vo.setChargeCnt(Integer.parseInt(value));
		            						
		            						bodyList.add(vo);
		            						
		            						
		            						break;
		            				}
		            			}
		            		}
		            		
		            		if(rowIndex !=haderRow && ( "".equals(titleStr) || "".equals(dateStr) )) {
	            				errorVo.setTitle(titleStr);
	            				errorVo.setBroadDate(dateStr);
	            				errorVo.setProgramNm(programNm);
	            				errorVo.setRow(rowIndex);
	            				ErrorList.add(errorVo);
	            			}
		            	}
		            	
		            }else {
		            	result = false;
		            }
		            log.debug("sheetIndex:"+sheetIndex);
	            }
	            log.debug("FILE DIR-END:====>"+path + "/" + fileName);
	            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        		HttpSession session = request.getSession(false);
            	
            	model.addAttribute("errorList", ErrorList);
            	session.setAttribute("headerList", headerList);
            	session.setAttribute("bodyList", bodyList);
            	result = true;
			}catch(Exception e) {
				log.error(e.getMessage());
				log.error("엑셀 읽으면서 에러", e);
				result = false;
				return result;
			}finally {
				if(workbook != null) {
					workbook.close();
				}	
			
				if(result == false ) {
					FileUtil.fileDelete(fileName, path);
					usageStatProgramExcelMapper.deleteExcelTmpData(fileVo);
				}
			}
		}
		return result;
	}
	
	

	@Async
	@Override
	public void saveSaveData(HttpServletRequest request, int tgSeq, SaveGroupVo param) throws Exception {

		HttpSession session = request.getSession();
		
		if(session.getAttribute("headerList") != null ) {
			List<String> headerList = (List<String>) session.getAttribute("headerList");	
		}else {
			throw new Exception();
		}
		
		
		String loginUser = (String) session.getAttribute("loginId");
		
		try{
			if(session.getAttribute("bodyList") != null &&  "US".equals(param.getDistType()) ) {
				
				List<UsageStatProgramVo> bodyList = (List<UsageStatProgramVo>) session.getAttribute("bodyList");	
				
				for(UsageStatProgramVo vo : bodyList) {
					int cSeq = param.getCSeq();
					if(cSeq != 15) {
						vo.setCSeq(cSeq);
					} else {
						
						CompanyParamVo cSeqParam = new CompanyParamVo();
						cSeqParam.setSearchType("01");
						cSeqParam.setSearchValue(vo.getCSeqNm());
						cSeqParam.setCType("P");
						int company = companyMapper.selectCSeq(cSeqParam);
						vo.setCSeq(company);	
						
					}
					
					vo.setTgSeq(tgSeq);
					vo.setLoginUser(loginUser);
					
					
    				if(cSeq == 11) {
    					
    					if(vo.getContentsId() == null) {
        					continue;
        				}
    					
    					UseStatProVo useStatProVo = new UseStatProVo();
    					
    					useStatProVo.setContentsId(vo.getContentsId());
    					
    					List<UseStatProVo> reciveList = contentsMapper.selectGetContentsIdInfoList(useStatProVo);
    					
    					if(reciveList != null && reciveList.size() ==1 ) {
        					vo.setOpSeq(reciveList.get(0).getOpSeq());
        					vo.setPcSeq(reciveList.get(0).getPcSeq());
        					vo.setChannelId(reciveList.get(0).getChannelId());
        				}else {
        					vo.setOpSeq(-1);
        					vo.setPcSeq(-1);
        					
        					if(reciveList.size() > 1 ) {
        						vo.setChannelId(reciveList.get(0).getChannelId());	
        					}
        				}
    					
    				}else {
    					if(vo.getProgramNm() == null && (vo.getVodcnt() == null || vo.getProgramNm() == null )) {
        					continue;
        				}
        				
    					DataVo dataVo = new DataVo();
        				dataVo.setBroadDate(vo.getBroadDate());
        				dataVo.setVodcnt(vo.getVodcnt());
        				dataVo.setProgramNm(vo.getProgramNm());
    					
        				List<DataVo> reciveList = contentsMapper.selectGetContentsIdList(dataVo);
        				
        				if(reciveList != null && reciveList.size() ==1 ) {
        					vo.setOpSeq(reciveList.get(0).getOpSeq());
        					vo.setPcSeq(reciveList.get(0).getPcSeq());
        					vo.setChannelId(reciveList.get(0).getChannelId());
        				}else {
        					vo.setOpSeq(-1);
        					vo.setPcSeq(-1);
        					
        					if(reciveList.size() > 1 ) {
        						vo.setChannelId(reciveList.get(0).getChannelId());	
        					}
        					
        				}
    				}
    				
					
					usageStatProgramMapper.insertUsageStatProgram(vo);;
				}
				
				SaveGroupVo saveGroupVo =  new SaveGroupVo();
				saveGroupVo.setTgSeq(tgSeq);
				saveGroupVo.setStatus("U");
				saveGroupVo.setLoginUser(loginUser);
				saveGroupMapper.updateSaveGroup(saveGroupVo);
				
	//			Iterator<StandardProgramExcelVo> it = bodyList.iterator();
	//			while ( it.hasNext()) {
	//				it.next().setTgSeq(tgSeq);
	//				mapper.insertSaveData(it.next());
	//			}
			
			}else if(session.getAttribute("bodyList") != null &&  "UC".equals(param.getDistType()) ) {
				
				List<UsageStatChannelVo> bodyList = (List<UsageStatChannelVo>) session.getAttribute("bodyList");	
				
				for(UsageStatChannelVo vo : bodyList) {
					
					if(vo.getBroadDate() == null ) {
						continue;
					}
					
					vo.setCSeq(param.getCSeq());
					vo.setTgSeq(tgSeq);
					vo.setLoginUser(loginUser);
					int uscSeq = usageStatChannelMapper.selectUspKey(vo);
					
					if(uscSeq == 0) {
						usageStatChannelMapper.insertUsageStatChannel(vo);
					}else {
						vo.setUscSeq(uscSeq);
						usageStatChannelMapper.updateUsageStatChannel(vo);
					}
				}
				
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			log.error("saveSaveData ERROR +++++++++ ", e);
			
			SaveGroupVo saveGroupVo =  new SaveGroupVo();
			saveGroupVo.setTgSeq(tgSeq);
			saveGroupVo.setStatus("E");
			saveGroupVo.setLoginUser(loginUser);
			saveGroupMapper.updateSaveGroup(saveGroupVo);
			
			//usageStatProgramExcelMapper.deleteUsageStatProgramForExcel(saveGroupVo);
			throw new Exception();
		}finally {
			session.removeAttribute("headerList");
			session.removeAttribute("bodyList");
		}
		
	}

}
