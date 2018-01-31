package kr.co.kbs.distribute.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.common.util.CastUtil;
import kr.co.kbs.distribute.common.util.DateUtil;
import kr.co.kbs.distribute.common.util.excel.ExcelToJsonConverter;
import kr.co.kbs.distribute.common.util.excel.ExcelToJsonConverterConfig;
import kr.co.kbs.distribute.common.util.excel.pojo.ExcelWorkbook;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;
import kr.co.kbs.distribute.schedule.vo.MinuteScheduleRateVo;


@Component("scheduleTempRateBean")
//@Component
public class SampleExcelForRateInsert {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(SampleExcelForRateInsert.class);

	@Autowired 
	private ScheduleDataMapper mapper;

//	public static void main(String[] args) throws IOException {
//		
//		SampleExcelForRateInsert task = new SampleExcelForRateInsert();
//		task.requestAliveSelect();
//	}
//	
	@Autowired
	private Environment env;

//	@Scheduled(cron="50 51 17 * * *") 
//	@Scheduled (fixedDelay = 5000)
	public void requestAliveSelect() {
		int totalCount = 0;
		int lProesult =0;
		int tapGubun= 0; //0:KBSPPM,1:MBCPPM,2:SBSPPM,3:KBSPPV

//				String rootDir ="/usr/local/tomcat/apache-tomcat-8.5.23/";
//		String rootDir ="D:\\KBS\\excel\\rate\\";

		String[] rootDir = {"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/rate/201710/",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/rate/201711/"};
		
//		tapGubun =CastUtil.toInt(env.getProperty("kbsapi.tapGubun")); 
		try {
			
			for(int dCnt=0;dCnt<rootDir.length;dCnt++) {
				String currentDay = "20171002";
				String transDay= DateUtil.getDateAdd(currentDay,3,dCnt,"yyyyMMdd");
				String readStr = "";
				
				File filedir = new File(rootDir[dCnt]);
				
				if(!filedir.exists()) {
					continue;
				}
				
				String [] listFileStr = filedir.list();
				
				for(int p=0;p<listFileStr.length;p++) {
					ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
					config.setSourceFile(rootDir[dCnt]+listFileStr[p]);
					//			config.setSourceFile("/usr/local/tomcat/apache-tomcat-8.5.23/kt_sell.xlsx");
					StringBuffer sb = new StringBuffer();
					StringBuffer errsb = new StringBuffer();
					String valid = config.valid();
					if(valid!=null) {
						logger.error(valid);
						return;
					}
		
					ExcelToJsonConverter convert = new ExcelToJsonConverter(config);
					ExcelWorkbook book = convert.convert(true);
					String jsonStr = book.toJson(config.isPretty());
//					logger.debug("####"+jsonStr.length());
		
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(jsonStr);
		
					JSONObject jsonObj2 = (JSONObject) obj;
					logger.debug("size:"+jsonObj2.size());
		
					JSONArray bookInfoArray = (JSONArray) jsonObj2.get("sheets");
					logger.debug("size:"+bookInfoArray.size());
		
					if(bookInfoArray.size()>0) {
						JSONObject jsonObj = (JSONObject) bookInfoArray.get(0);
//						logger.debug("jsonObj:"+jsonObj.values().toString().substring(0, 100));
		
						JSONArray jsonArray3 =  (JSONArray) jsonObj.get("data");
		
//						logger.debug("jsonArray3: "+ jsonArray3.size());
		
						for(int j =0;j<jsonArray3.size();j++) {
							if(j==0) continue;
							//					JSONObject jsonObj5 = (JSONObject) jsonArray3.get(j);
//							logger.debug("====="+j+"번째 START==============");
							JSONArray jarray5 = (JSONArray)jsonArray3.get(j);
							String dateStr="";
							
							MinuteScheduleRateVo mrVo = new MinuteScheduleRateVo();
							mrVo.setInputId("admin");
							for(int i=0;i<jarray5.size();i++) {
//								System.out.println("=="+i+"==="+ObjectUtils.toString(jarray5.get(i), ""));
								if(jarray5.get(i)!=null) {
//									logger.debug("====="+jarray5.get(i).toString());
								}
								if(i==0 && j!=0) {
									SimpleDateFormat dayTime = new SimpleDateFormat("yyyy-MM-dd");
									dateStr = dayTime.format(new Date(Long.parseLong(ObjectUtils.toString(jarray5.get(i), ""))));
									logger.debug(dateStr);
									mrVo.setBroadDate(dateStr.replace("-", ""));
								}
								
								switch (i) {
						            case 1:  mrVo.setResearchAgency(ObjectUtils.toString(jarray5.get(i), ""));
						                     break;
						            case 2:  mrVo.setArea(ObjectUtils.toString(jarray5.get(i), ""));
						                     break;
						            case 3:  mrVo.setChannel(ObjectUtils.toString(jarray5.get(i), ""));
						                     break;
						            case 4:  
						            			String broadTime=ObjectUtils.toString(jarray5.get(i), "");
//						            			logger.debug("mrVo.getBroadDate():"+mrVo.getBroadDate());
						            			String voradDate = DateUtil.getDateAdd(mrVo.getBroadDate(), 4, CastUtil.toInt(broadTime.substring(0, 2)),"yyyyMMddHHmmss");
//						            			logger.debug("voradDate:"+voradDate);
						            			voradDate = DateUtil.getDateAdd(DateUtil.getDate(voradDate), 5, CastUtil.toInt(broadTime.substring(3, 5)),"yyyyMMddHHmmss");
						            			mrVo.setBroadDate(voradDate);
						            			mrVo.setAir_time(ObjectUtils.toString(jarray5.get(i), ""));
						                     break;
						            case 5:  mrVo.setHouse(""+jarray5.get(i));
						                     break;
						            case 6:  mrVo.setPersonal((""+jarray5.get(i)));
						                     break;
						            case 7:  mrVo.setCable((""+jarray5.get(i)));
						                     break;
						            case 8:  mrVo.setWireless((""+jarray5.get(i)));
						                     break;
						            case 9:  mrVo.setMan((""+jarray5.get(i)));
						                     break;
						            case 10: mrVo.setWoman((""+jarray5.get(i)));
						                     break;
						            case 11: mrVo.setU10((""+jarray5.get(i)));
						                     break;
						            case 12: mrVo.setS10((""+jarray5.get(i)));
						                     break;
						            case 13: mrVo.setS20((""+jarray5.get(i)));
				                     		break;
						            case 14: mrVo.setS30((""+jarray5.get(i)));
			                 				break;
						            case 15: mrVo.setS40((""+jarray5.get(i)));
			                 				break;
						            case 16: mrVo.setO50((""+jarray5.get(i)));
			                 				break;
						            case 17: mrVo.setMu10((""+jarray5.get(i)));
			         					break;
						            case 18: mrVo.setMs10((""+jarray5.get(i)));
			         					break;
						            case 19: mrVo.setMs20((""+jarray5.get(i)));
			         					break;
						            case 20: mrVo.setMs30((""+jarray5.get(i)));
			         					break;
						            case 21: mrVo.setMs40((""+jarray5.get(i)));
			         					break;
						            case 22: mrVo.setMo50((""+jarray5.get(i)));
			         					break;
						            case 23: mrVo.setWu10((""+jarray5.get(i)));
			         					break;
						            case 24: mrVo.setWs10((""+jarray5.get(i)));
			         					break;
						            case 25: mrVo.setWs20((""+jarray5.get(i)));
			         					break;
						            case 26: mrVo.setWs30((""+jarray5.get(i)));
			         					break;
						            case 27: mrVo.setWs40((""+jarray5.get(i)));
			         					break;
						            case 28: mrVo.setWo50((""+jarray5.get(i)));
			         					break;
						            case 29: mrVo.setUnEl((""+jarray5.get(i)));
			         					break;
						            case 30: mrVo.setMiSc((""+jarray5.get(i)));
			         					break;
						            case 31: mrVo.setMiGr((""+jarray5.get(i)));
			         					break;
						            case 32: mrVo.setHiGr((""+jarray5.get(i)));
			         					break;
						            case 33: mrVo.setOvUn((""+jarray5.get(i)));
		         						break;
						            case 34: mrVo.setHoWife((""+jarray5.get(i)));
			         					break;
						            case 35: mrVo.setUnIncome150((""+jarray5.get(i)));
			         					break;
						            case 36: mrVo.setOvIncome150((""+jarray5.get(i)));
			         					break;
						            case 37: mrVo.setIncome200((""+jarray5.get(i)));
			         					break;
						            case 38: mrVo.setIncome300((""+jarray5.get(i)));
			     						break;
						            case 39: mrVo.setIncome400((""+jarray5.get(i)));
			     						break;
						            case 40: mrVo.setOvIncome500((""+jarray5.get(i)));
			     						break;
						         
						            default: 
						                     break;
						        }
		
							}
							
							if(mrVo.getChannel().equals("KBS1")) {
								mrVo.setChannel("KBS 1TV");
							}else if(mrVo.getChannel().equals("KBS2")) {
								mrVo.setChannel("KBS 2TV");
							}
							
							
							if(mapper.selectMViewRate(mrVo)>0) {
								totalCount+=mapper.updateMViewRate(mrVo);
							}else {
								totalCount+=mapper.insertMViewRate(mrVo);
							}
						}
					}
				}
			}
//			logger.debug("===totalCount==["+totalCount+"/"+bookInfoArray.size()+"]==============");
//			FileUtils.writeStringToFile(new File(rootDir+"kbs_sk_ppv.txt"), sb.toString(),"UTF-8");
//			FileUtils.writeStringToFile(new File(rootDir+"kbs_sk_ppv_err.txt"), errsb.toString(),"UTF-8");
//
//			logger.debug("end-validCnt:"+(validCnt)+":"+lProesult);
//			logger.debug("end-size : "+(bookInfoArray2.size()));
//			logger.debug("end:"+(validCnt/bookInfoArray2.size() *100+"%"));
		}catch(Exception ex) {
			System.out.println("error:"+ex.getMessage());
			ex.printStackTrace();
		}
	}

	//지상파_인간극장 베스트_내 이름은 산다라박 이름, :([a-zA-Z가-힣_\\s]{1,})
	//회차 : "[0-9]{1,}회$|[0-9]{1,}회 |[0-9]{1,3}화$|[0-9]{1,3}화 |[0-9]{1,}부$


	private static String getRexStr(String orgStr, String findRex) {
		Pattern groupPattern = Pattern.compile(findRex); //지상파_인간극장 베스트_내 이름은 산다라박 
		Matcher groupMatcher = groupPattern.matcher(orgStr);
		String retStr ="";
		while(groupMatcher.find()) {
			retStr = groupMatcher.group();  // 정규표현식에 일치한 전체 문자열
			break;
		}
		return retStr;

	}



}
