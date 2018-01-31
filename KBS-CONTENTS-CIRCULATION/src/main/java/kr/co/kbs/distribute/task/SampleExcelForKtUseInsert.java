package kr.co.kbs.distribute.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;
import kr.co.kbs.distribute.schedule.service.ScheduleDataService;


@Component("scheduleTempKtUseBean")
//@Component
public class SampleExcelForKtUseInsert {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(SampleExcelForKtUseInsert.class);
	
	@Autowired 
	private ScheduleDataMapper mapper;
	
	@Autowired 
	private ScheduleDataService service;
	
//	public static void main(String[] args) throws IOException {
//		
//		SampleExcelForCVODInsert task = new SampleExcelForCVODInsert();
//		task.requestAliveSelect();
//	}
	@Autowired
	private Environment env;
	
//	@Scheduled(cron="20 32 20 * * *") 
	public void requestAliveSelect() {
		int totalCount = 0;
		int lProesult =0;
		int tapGubun= 0; //0:KBSPPM,1:MBCPPM,2:SBSPPM,3:KBSPPV
		
//		tapGubun =CastUtil.toInt(env.getProperty("kbsapi.tapGubun")); 
//		
//		String rootDir ="/usr/local/tomcat/apache-tomcat-8.5.23/";
//		String rootDir ="D:\\KBS\\excel\\KT\\";
		//171023-171029
		String[] rootDir = {
				
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171002-171008/KT/222.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/KT/222.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/KT/222.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/KT/222.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/KT/222.xlsx"
//				
//				"E:\\00.project\\00.2017\\16.KBS_유통정보시스템\\00.받은파일\\20171201\\KBS데이터_20171201\\TV_VOD\\201710\\171002-171008\\KT\\통합월정액이용현황(17.10.02일주).xlsx",
		};
		String[] dayArray= {"20171002","20171009","20171016","20171023","20171030"};
//		String[] dayArray= {"20171030"};
		String proTypeStr ="";
		for(int fileListCnt=0;fileListCnt<rootDir.length;fileListCnt++) {
		
			try {
				
				service.KtUseTask(rootDir[fileListCnt],fileListCnt,dayArray[fileListCnt]);
//				ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
//				config.setSourceFile(rootDir[fileListCnt]);
//	//			config.setSourceFile("/usr/local/tomcat/apache-tomcat-8.5.23/kt_sell.xlsx");
//				StringBuffer sb = new StringBuffer();
//				StringBuffer errsb = new StringBuffer();
//				String valid = config.valid();
//				if(valid!=null) {
//					logger.error(valid);
//					return;
//				}
//	
//				ExcelToJsonConverter convert = new ExcelToJsonConverter(config);
//				ExcelWorkbook book = convert.convert();
//				String jsonStr = book.toJson(config.isPretty());
//				logger.debug("jsonlength:"+jsonStr.length());
//				
//				logger.debug(jsonStr.substring(0, 200));
//				JSONParser parser = new JSONParser();
//				Object obj = parser.parse(jsonStr);
//				JSONObject jsonObj = (JSONObject) obj;
//				logger.debug("size:"+jsonObj.size());
//	
//				JSONArray bookInfoArray = (JSONArray) jsonObj.get("sheets");
//	
//				logger.debug("array-size: "+bookInfoArray.size());
//				JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(0);
//	
//				JSONArray bookInfoArray2 = (JSONArray) jsonObj2.get("data");
//	
//				logger.debug("array2-size: "+bookInfoArray2.size());
//				float validCnt =0;
//				JSONArray arrayObj = null;
//				for(int i=1;i<bookInfoArray2.size();i++) {
//	
//					arrayObj = (JSONArray) bookInfoArray2.get(i);
//					String titleStr ="";
//					String dateStr ="";
//					String orgStr = "";
//					String replaceStr ="";
//					
//					String vodStr ="";
//					String screenType="";
//					String chargeYn="";
//					String amount="";
//					String tamount="";
//					String chargeCnt="";
//					
//					String channelNm="";
//					
//					for(int j=0;j<arrayObj.size();j++) {
//						if(j==3) {
//							dateStr="";
//							dateStr = getRexStr(ObjectUtils.toString(arrayObj.get(j), ""),"[0-9]{1,}회$|[0-9]{1,}회$|[0-9]{1,}회 |[0-9]{1,3}화$|[0-9]{1,3}화 |[0-9]{1,}부$|[0-9]{6,}|[0-9-]{10,}").trim(); //
//							//sb.append(ObjectUtils.toString(arrayObj.get(j),"")+":["+i+"]["+j+"]["+dateStr+"]\r\n");
//							
//							titleStr ="";
//							orgStr = ObjectUtils.toString(arrayObj.get(j),"");
//	//						replaceStr = getRexStr(orgStr,"^TV 다시보기_[a-zA-Z가-힣\\d-_\\s\\Q[a-zA-Z가-힣]\\E]{12,}\\[[가-힣]{1}\\]|^지상파_[a-zA-Z\\d-_\\s]{1,9}|^지상파_[a-zA-Z\\d-_\\s]{1,9}|^지상파_|^KBS_[0-9/]{5,}\\s\\[[가-힣]{1}\\]|^KBS_[0-9-]{10,}\\s\\[[가-힣]{1}\\]\\s|^KBS_[0-9]{1,}\\[[가-힣]{1}\\]|^KBS_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]|^KBS_\\[HD\\]\\s[0-9/]{5,}\\s\\[[가-힣]\\]|^KBS_\\[HD\\]\\s[0-9]{1,}\\Q[a-zA-Z가-힣]\\E]|^KBS_[0-9]{1,}_|_[0-9]{1,3}화$|TV다시보기_[A-Z]{1,3}[0-9]{1,6}|^TV 다시보기_[A-Z]{1,3}\\s[0-9]{6}\\s\\[[가-힣]{1}\\]|^TV 다시보기_[A-Z]{1,3}[0-9]{6}\\[[가-힣]{1}\\]|TV다시보기_[0-9]{1,}\\[[가-힣]{1}\\]");
//							replaceStr = getRexStr(orgStr,"TEST_[a-zA-Z]{3}[0-9]{6}|^지상파_[a-zA-Z]{3}[0-9]{6}|"
//									+ "^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}[0-9]{8}|"
//									+ "^TV다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^TV다시보기_[a-zA-Z]{3}[0-9]{6}|"
//									+ "^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|"
//									+ "^TV 다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV 다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{1,}화\\s|"
//									+ "^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회\\s|^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{4}-[0-9]{2}-[0-9]{2}\\s\\[[가-힣]{1}\\]|"
//									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\][0-9]{1,}회|"
//									+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{2}\\/[0-9]{2}\\s\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]|"
//									+ "^[a-zA-Z]{3}_HD[0-9]{6}\\[[가-힣]{1}\\]\\s|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|"
//									+ "^[a-zA-Z]{3}_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{8}_\\[HD\\]|^[a-zA-Z]{3}_[0-9]{8}_|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}|"
//									+ "^[a-zA-Z]{3}_[0-9]{2}-[0-9]{2}-[0-9]{2}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}");
//							
//	//						titleStr = orgStr.replace(replaceStr, "").replace("지상파_", "").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").trim();
//							titleStr = orgStr.replace( replaceStr, "").replace("_재편성", "").replace("_수정", "").replace("오늘의 추천_", "").replace("지상파_", "").replace("TV다시보기_","").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").replaceAll("[a-zA-Z]{3}_", "").replaceAll("[_]$", "").trim();
//							titleStr = titleStr.replace(getRexStr(orgStr,"[0-9]{1,}회$|\\(HD\\)$"),"").trim();
//							
//							//sb.append("\t\t:["+i+"]["+j+"]:["+titleStr+"]\r\n");
//						}
//						
//						if(j==1) {
//							channelNm = ObjectUtils.toString(arrayObj.get(j),"");
//							logger.debug("channelNm:"+channelNm);
//							if(channelNm.indexOf("KBS")>-1) {
//								channelNm = "KBS";
//							}else if(channelNm.indexOf("SBS")>-1) {
//								channelNm = "SBS";
//							}else {
//								channelNm = "MBC";
//							}
//						}
//						
//						if(fileListCnt==3) {
//							if(j==5) {//임시
//								amount = ObjectUtils.toString(arrayObj.get(j),"");
//							}
//							
//							if(j==6) {//임시
//								chargeCnt = ObjectUtils.toString(arrayObj.get(j),"");
//							}
//						}else {
//							if(j==4) {
//								amount = ObjectUtils.toString(arrayObj.get(j),"");
//							}
//							
//							if(j==5) {
//								chargeCnt = ObjectUtils.toString(arrayObj.get(j),"");
//							}
//						}
//					}
//					
//					if(titleStr.length()>0&&dateStr.length()>0) {
//						DataVo dVo = new DataVo();
//						dVo.setProgramNm(titleStr);
//						String dateStrSub =dateStr;
//						if(dateStrSub.length()==6) {
//							if(dateStrSub.startsWith("9")) {
//								dateStrSub = "19"+dateStrSub;
//							}else {
//								dateStrSub="20"+dateStrSub;
//							}
//						}
//						
//						dVo.setVodcnt(dateStrSub);
//						dVo.setBroadDate(dateStrSub);
//						String programId ="";
//						String contentsId ="";
//						
//	//					programId = mapper.selectGetProgramId(dVo);
//	//					contentsId = mapper.selectGetContentsId(dVo);
//						List<DataVo> reciveVo = mapper.selectGetContentsIdList(dVo);
//						
//						if(reciveVo!=null) {
//							validCnt++;
//							UseStatProVo uspVo = new UseStatProVo();
//							if(reciveVo.size()==1) {
//								uspVo.setPcSeq(CastUtil.toInt(reciveVo.get(0).getPcSeq()));
//								uspVo.setPSeq(CastUtil.toInt(reciveVo.get(0).getPSeq()));
//							}else {
//								uspVo.setPcSeq(-1);
//								uspVo.setPSeq(-1);
//							}
//							uspVo.setProType("PPM");
//							uspVo.setComNm("KT");
//							uspVo.setChargeYn("Y");
//							uspVo.setAmount(amount);
//							uspVo.setTotalAmount(""+(CastUtil.toInt(amount) * CastUtil.toInt(chargeCnt)));
//							uspVo.setChargeCnt(chargeCnt);
//							uspVo.setViewDate(dayArray[fileListCnt]);
//							uspVo.setInputId("admin");
//							uspVo.setChannelId(channelNm);
//							uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);
////							if(mapper.selectUseStatProgram(uspVo)>0){
////								lProesult += mapper.updateUseStatProgram(uspVo);
////							}else {
//								lProesult += mapper.insertUseStatProgram(uspVo);
////							}
//							sb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]:["+programId+"]:["+contentsId+"]\r\n");
//						}else {
//							UseStatProVo uspVo = new UseStatProVo();
//							uspVo.setPcSeq(-1);
//							uspVo.setPSeq(-1);
//							uspVo.setProType("PPM");
//							uspVo.setComNm("KT");
//							uspVo.setChargeYn("Y");
//							uspVo.setAmount(amount);
//							uspVo.setTotalAmount(""+(CastUtil.toInt(amount) * CastUtil.toInt(chargeCnt)));
//							uspVo.setChargeCnt(chargeCnt);
//							uspVo.setViewDate(dayArray[fileListCnt]);
//							uspVo.setInputId("admin");
//							uspVo.setChannelId(channelNm);
//							uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);
////							if(mapper.selectUseStatProgram(uspVo)>0){
////								lProesult += mapper.updateUseStatProgram(uspVo);
////							}else {
//								lProesult += mapper.insertUseStatProgram(uspVo);
//							
//							errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
//						}
//						
//					}else {
//						if(vodStr.indexOf("시리즈")>0) {
//							DataVo dVo = new DataVo();
//							dVo.setProgramNm(titleStr);
//							dVo.setVodcnt("1");
//							dVo.setBroadDate(dateStr);
//							String programId ="";
//							String contentsId ="";
//							DataVo reciveVo = mapper.selectGetContentsId(dVo);
//							if(reciveVo!=null) {
//								validCnt++;
//							
//								UseStatProVo uspVo = new UseStatProVo();
//								uspVo.setPcSeq(CastUtil.toInt(contentsId));
//								uspVo.setPSeq(CastUtil.toInt(programId));
//								uspVo.setProType("PPM");
//								uspVo.setComNm("KT");
//								uspVo.setChargeYn("Y");
//								
//								uspVo.setAmount(amount);
//								uspVo.setTotalAmount(""+(CastUtil.toInt(amount) * CastUtil.toInt(chargeCnt)));
//								uspVo.setChargeCnt(chargeCnt);
//		
//								uspVo.setViewDate(dayArray[fileListCnt]);
//								uspVo.setInputId("admin");
//								uspVo.setChannelId(channelNm);
////								if(mapper.selectUseStatProgram(uspVo)>0){
////									lProesult += mapper.updateUseStatProgram(uspVo);
////								}else {
//									lProesult += mapper.insertUseStatProgram(uspVo);
////								}
//								
//								sb.append("시리즈:["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+vodStr+"]:["+programId+"]:["+contentsId+"]\r\n");
//							}else {
//								
//								UseStatProVo uspVo = new UseStatProVo();
//								uspVo.setPcSeq(-1);
//								uspVo.setPSeq(-1);
//								uspVo.setProType("PPM");
//								uspVo.setComNm("KT");
//								uspVo.setChargeYn("Y");
//								
//								uspVo.setAmount(amount);
//								uspVo.setTotalAmount(""+(CastUtil.toInt(amount) * CastUtil.toInt(chargeCnt)));
//								uspVo.setChargeCnt(chargeCnt);
//		
//								uspVo.setViewDate(dayArray[fileListCnt]);
//								uspVo.setInputId("admin");
//								uspVo.setChannelId(channelNm);
//								uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);
//								
//								lProesult += mapper.insertUseStatProgram(uspVo);
//								errsb.append("시리즈:["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+vodStr+"]\r\n");
//							}
//						}else{
//							UseStatProVo uspVo = new UseStatProVo();
//							uspVo.setPcSeq(-1);
//							uspVo.setPSeq(-1);
//							uspVo.setProType("PPM");
//							uspVo.setComNm("KT");
//							uspVo.setChargeYn("Y");
//							
//							uspVo.setAmount(amount);
//							uspVo.setTotalAmount(""+(CastUtil.toInt(amount) * CastUtil.toInt(chargeCnt)));
//							uspVo.setChargeCnt(chargeCnt);
//	
//							uspVo.setViewDate(dayArray[fileListCnt]);
//							uspVo.setInputId("admin");
//							
//							uspVo.setChannelId(channelNm);
//							uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);
//							
//							lProesult += mapper.insertUseStatProgram(uspVo);
//							
//							errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
//							System.out.println(orgStr+":"+replaceStr+":"+titleStr);
//						}
//					}
//				}
//				
//				logger.debug("end-validCnt:"+(validCnt)+":"+lProesult);
//				logger.debug("end-size : "+(bookInfoArray2.size()));
//				logger.debug("end:"+(validCnt/bookInfoArray2.size() *100+"%"));
			}catch(Exception ex) {
				System.out.println("error:"+ex.getMessage());
				ex.printStackTrace();
			}
		}
	}

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
