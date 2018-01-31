package kr.co.kbs.distribute.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.common.util.CastUtil;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;
import kr.co.kbs.distribute.schedule.service.ScheduleDataService;


@Component("scheduleTempSKPPMBean")
//@Component
public class SampleExcelForSkPPMInsert {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(SampleExcelForSkPPMInsert.class);
	
	@Autowired 
	private ScheduleDataMapper mapper;
	
//	public static void main(String[] args) throws IOException {
//		
//		SampleExcelForCVODInsert task = new SampleExcelForCVODInsert();
//		task.requestAliveSelect();
//	}
	
	
	@Autowired
	private Environment env;
	
	@Autowired 
	private ScheduleDataService service;
	
	
//	@Scheduled(cron="50 22 11 * * *") 
	public void requestAliveSelect() {
		int totalCount = 0;
		int lProesult =0;
		int tapGubun= 0; //0:KBSPPM,1:MBCPPM,2:SBSPPM,3:KBSPPV
		
		String rootDir ="/usr/local/tomcat/apache-tomcat-8.5.23/";
//		String rootDir ="D:\\KBS\\excel\\SK\\";
		
		
		
//		String[] fileNameArray= {"LG_PPM_use.xlsx","LG_PPV_use.xlsx","LG_MBC_PPM_use.xlsx","LG_SBS_PPM_use.xlsx"};
		
		String[] fileNameArray= {
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201_1/TV_VOD/201710/171009-171015/SK/222.xls"
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171009-171015/SK/333.xls",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171009-171015/SK/444.xls",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171016-171022/SK/222.xls",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171016-171022/SK/333.xls",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171016-171022/SK/444.xls",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171023-171029/SK/222.xls",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171023-171029/SK/333.xls",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171023-171029/SK/444.xls",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171030-171105/SK/222.xls",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171030-171105/SK/333.xls",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171030-171105/SK/444.xls",
//				"LG_PPM_use.xlsx","LG_PPV_use.xlsx","LG_MBC_PPM_use.xlsx","LG_SBS_PPM_use.xlsx"
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171002-171008/SK/222.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171002-171008/SK/333.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171002-171008/SK/444.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/SK/222.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/SK/333.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/SK/444.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/SK/222.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/SK/333.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/SK/444.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/SK/222.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/SK/333.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/SK/444.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/SK/222.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/SK/333.xls",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/SK/444.xls"
		};
		
		
		
		tapGubun =CastUtil.toInt(env.getProperty("kbsapi.tapGubun")); 
		try {
			for(int fiCnt=0;fiCnt<fileNameArray.length;fiCnt++) {
				
				logger.debug("fileNameArray[fiCnt]:"+fileNameArray[fiCnt]+":"+fiCnt);
				
				service.SKPPMTask(fileNameArray[fiCnt], fiCnt);
			
//				ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
//				config.setSourceFile(fileNameArray[fiCnt]);
//				//			config.setSourceFile(rootDir+fileNameArray[fiCnt]);
//	//			ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
//	//			config.setSourceFile(rootDir+"kbs_sk_ppm.xls");
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
//					String totalAmount="";
//					String joinCnt="";
//					String chargeCnt="";
//					String onairStr="";
//					String proType="PPM";
//					for(int j=0;j<arrayObj.size();j++) {
//						if(j==2) {
//							titleStr ="";
//							orgStr = ObjectUtils.toString(arrayObj.get(j),"");
//							replaceStr  = getRexStr(orgStr,"^\\[HD\\]");
//							titleStr = orgStr.replace(replaceStr, "").trim();
//						}
//						
//						if(j==0) {
//							onairStr = ObjectUtils.toString(arrayObj.get(j),"");
//						}
//	//					if(j==3) {
//	//						vodStr = ObjectUtils.toString(arrayObj.get(j),"");
//	//					}
//						
//						if(j==3) {
//							dateStr = getRexStr(ObjectUtils.toString(arrayObj.get(j), ""),"[0-9]{1,2}.[0-9]{1,2}.[0-9]{1,2}|^[0-9]{1,}회").trim(); //
//						}
//						
//						if(j==5) {
//							chargeCnt = ObjectUtils.toString(arrayObj.get(j),"");
//						}
//						
//						if(j==4) {
//							joinCnt = ObjectUtils.toString(arrayObj.get(j),"");
//						}
//					}
//					
//					if(titleStr.length()>0&&dateStr.length()>0) {
//						DataVo dVo = new DataVo();
//						dVo.setProgramNm(CastUtil.StringReplace(titleStr));
//						dVo.setVodcnt(dateStr);
//						dVo.setBroadDate(dateStr.replace(".", ""));
//						String programId ="";
//						String contentsId ="";
//						
//	//					programId = mapper.selectGetProgramId(dVo);
//	//					contentsId = mapper.selectGetContentsId(dVo);
//						DataVo reciveVo = mapper.selectGetContentsId(dVo);
//						
//						if(reciveVo!=null) {
//							validCnt++;
//							
//							logger.debug("end-validCnt:"+(validCnt)+":[cnt]:"+i+":"+lProesult);
//							
//							UseStatProVo uspVo = new UseStatProVo();
//							uspVo.setPcSeq(CastUtil.toInt(reciveVo.getPcSeq()));
//							uspVo.setPSeq(CastUtil.toInt(reciveVo.getPSeq()));
//							uspVo.setProType(proType);
//							uspVo.setComNm("SK");
//							uspVo.setChargeYn("Y");
//	//						uspVo.setTotalAmount(totalAmount);
//	//						uspVo.setAmount(""+(CastUtil.toInt(totalAmount)/CastUtil.toInt(chargeCnt)));
//							uspVo.setChargeCnt(chargeCnt);
//							uspVo.setJoinCnt(joinCnt);
//							uspVo.setViewDate(onairStr);
//							uspVo.setInputId("admin");
//							
//							if(fiCnt%3==1) {
//								uspVo.setChannelId("KBS");//0
//							}else if(fiCnt%3==2) {
//								uspVo.setChannelId("MBC");//
//							}else {
//								uspVo.setChannelId("SBS");//
//							}
//							
//							uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);
//							if(mapper.selectUseStatProgram(uspVo)>0){
//								lProesult += mapper.updateUseStatProgram(uspVo);
//							}else {
//								lProesult += mapper.insertUseStatProgram(uspVo);
//							}
//							sb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]:["+programId+"]:["+contentsId+"]\r\n");
//						}else {
//							
//							UseStatProVo uspVo = new UseStatProVo();
//							uspVo.setPcSeq(-1);
//							uspVo.setPSeq(-1);
//							uspVo.setProType(proType);
//							uspVo.setComNm("SK");
//							uspVo.setChargeYn("Y");
//							uspVo.setChargeCnt(chargeCnt);
//							uspVo.setJoinCnt(joinCnt);
//							uspVo.setViewDate(onairStr);
//							uspVo.setInputId("admin");
//							if(fiCnt%3==0) {
//								uspVo.setChannelId("KBS");
//							}else if(fiCnt%3==2) {
//								uspVo.setChannelId("MBC");
//							}else {
//								uspVo.setChannelId("SBS");
//							}
//							
//							uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);
//							lProesult += mapper.insertUseStatProgram(uspVo);
//							errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
//						}
//						
//					}else {
//						if(vodStr.indexOf("시리즈")>0) {
//							
//								errsb.append("시리즈:["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+vodStr+"]\r\n");
//	//						}
//						}else{
//							errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]:["+vodStr+"]:["+onairStr+"]\r\n");
//							System.out.println(orgStr+":"+replaceStr+":"+titleStr);
//						}
//					}
//				}
				
//				FileUtils.writeStringToFile(new File(rootDir+"kbs_sk_ppv.txt"), sb.toString(),"UTF-8");
//				FileUtils.writeStringToFile(new File(rootDir+"kbs_sk_ppv_err.txt"), errsb.toString(),"UTF-8");
				
//				logger.debug("end-validCnt:"+(validCnt)+":"+lProesult);
//				logger.debug("end-size : "+(bookInfoArray2.size()));
//				logger.debug("end:"+(validCnt/bookInfoArray2.size() *100+"%"));
			}
		}catch(Exception ex) {
			System.out.println("error:"+ex.getMessage());
			ex.printStackTrace();
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
