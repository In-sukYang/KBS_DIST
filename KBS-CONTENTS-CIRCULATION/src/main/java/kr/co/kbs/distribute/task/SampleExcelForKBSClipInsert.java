package kr.co.kbs.distribute.task;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.common.util.excel.ExcelToJsonConverter;
import kr.co.kbs.distribute.common.util.excel.ExcelToJsonConverterConfig;
import kr.co.kbs.distribute.common.util.excel.pojo.ExcelWorkbook;
import kr.co.kbs.distribute.schedule.service.ScheduleDataService;
import kr.co.kbs.distribute.schedule.vo.UseClipVo;


@Component("scheduleTempKBSClipBean")
public class SampleExcelForKBSClipInsert {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(SampleExcelForKBSClipInsert.class);

		
	@Autowired
	private ScheduleDataService service;
	
//	public static void main(String[] args) throws IOException {
//		
//		SampleExcelForKBSClipInsert task = new SampleExcelForKBSClipInsert();
//		task.requestAliveSelect();
//	}
	@Autowired
	private Environment env;


	@Autowired
    private ThreadPoolTaskExecutor  threadPoolTaskExecutor;
	
	private static int lProesult =0;

//		@Scheduled(cron="10 50 03 * * *") 
//	@Scheduled (fixedDelay = 5000)
	public void requestAliveSelect() {
		int totalCount = 0;
		lProesult =0;
		int tapGubun= 0; //0:KBSPPM,1:MBCPPM,2:SBSPPM,3:KBSPPV

		//		tapGubun =CastUtil.toInt(env.getProperty("kbsapi.tapGubun")); 

		String[] rootDir = {
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201710/20171002-1008_1TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201710/20171002-1008_2TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201710/20171009-1015_1TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201710/20171009-1015_2TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201710/20171016-1022_1TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201710/20171016-1022_2TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201710/20171023-1029_1TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201710/20171023-1029_2TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201711/20171030-1105_1TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201711/20171030-1105_2TV.xlsx",
//		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201711/20171106-1112_1TV.xlsx",
//		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201711/20171106-1112_2TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201711/20171113-1119_1TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201711/20171113-1119_2TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201711/20171120-1126_1TV.xlsx",
		"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/SMR/201711/20171120-1126_2TV.xlsx",
		};
		
		//E:\00.project\00.2017\16.KBS_유통정보시스템\00.받은파일\20171201\KBS데이터_20171201\TV_VOD\201710\171002-171008\CVOD
//				String rootDir ="/usr/local/tomcat/apache-tomcat-8.5.23/";
//		String rootDir[] ={
//		"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171130/KBS데이터_20171130/SMR/201711/20171030-1105_1TV.xlsx",
//		"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171130/KBS데이터_20171130/SMR/201711/20171030-1105_2TV.xlsx",
////		"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171130/KBS데이터_20171130/SMR/201711/20171113-1119_1TV 일별 재생수_20171120.xlsx"
//				};
		ArrayList<String> dayal = new ArrayList<String>();
		try {
			for(int dCnt=0;dCnt<rootDir.length;dCnt++) {
				ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
				config.setSourceFile(rootDir[dCnt]);
				//			config.setSourceFile("/usr/local/tomcat/apache-tomcat-8.5.23/kt_sell.xlsx");
				StringBuffer sb = new StringBuffer();
				StringBuffer errsb = new StringBuffer();
				String valid = config.valid();
				if(valid!=null) {
					logger.error(valid);
					return;
				}
	
				ExcelToJsonConverter convert = new ExcelToJsonConverter(config);
				ExcelWorkbook book = convert.convert();
				String jsonStr = book.toJson(config.isPretty());
				logger.debug("jsonlength:"+jsonStr.length());
	
				logger.debug(jsonStr.substring(0, 200));
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(jsonStr);
				JSONObject jsonObj = (JSONObject) obj;
				logger.debug("size:"+jsonObj.size());
				
				JSONArray bookInfoArray = (JSONArray) jsonObj.get("sheets");
	
				logger.debug("array-size: "+bookInfoArray.size());
				JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(0);
	
				JSONArray bookInfoArray2 = (JSONArray) jsonObj2.get("data");
	
				logger.debug("array2-size: "+bookInfoArray2.size());
				float validCnt =0;
				JSONArray arrayObj = null;
				ArrayList<UseClipVo> al = new ArrayList<UseClipVo>();
				//			HashMap <String,UseClipVo> hm = new HashMap<String,UseClipVo>();
				//			HashMap <String,UseClipVo> hm = null;
				String channelNm ="";
				String programNm ="";
				String programId ="";
				String programSubNm ="";
	
				for(int i=5;i<bookInfoArray2.size();i++) {
	
					arrayObj = (JSONArray) bookInfoArray2.get(i);
					String dateStr ="";
					String orgStr = "";
					String replaceStr ="";
					String titleStr ="";
					String vodStr ="";
					String screenType="";
					String chargeYn="";
					String amount="";
					String tamount="";
					String chargeCnt="";
					String clipNm="";
					String clipId="";
					String mainTitle="";
					String viewCnt1="";
					String viewCnt2="";
					String viewCnt3="";
					String viewCnt4="";
					String viewCnt5="";
					String viewCnt6="";
					String viewCnt7="";
					String viewCnt8="";
					for(int j=0;j<arrayObj.size();j++) {
						if(j==0) {
							if(!ObjectUtils.toString(arrayObj.get(j),"").equals("")) {
								channelNm = ObjectUtils.toString(arrayObj.get(j),"");
							}
							//						logger.debug("channelNm:"+channelNm);
						}
						
//						if((i==0 &&dCnt>3)||((i==1 &&dCnt<2))){
						
							if(j==1) {
								if(!ObjectUtils.toString(arrayObj.get(j),"").equals("")) {
									programNm = ObjectUtils.toString(arrayObj.get(j),"");
								}
								//						logger.debug("programNm:"+programNm);
							}
		
							if(j==2) {
								if(!ObjectUtils.toString(arrayObj.get(j),"").equals("")) {
									programId = ObjectUtils.toString(arrayObj.get(j),"");
								}
								//						logger.debug("programId:"+programId);
							}
		
							if(j==3) {
								if(StringUtils.isNotEmpty(ObjectUtils.toString(arrayObj.get(j),""))) {
									titleStr = ObjectUtils.toString(arrayObj.get(j),"");
									programSubNm = titleStr;
									//							logger.debug("titleStr>>>>:"+titleStr);
								}
								//						logger.debug("titleStr:"+titleStr);
							}
		
							if(j==4) {
								clipNm = ObjectUtils.toString(arrayObj.get(j),"");
							}
		
							if(j==5) {
								clipId = ObjectUtils.toString(arrayObj.get(j),"");
							}
		
							if(j==6) {
								viewCnt1 = ObjectUtils.toString(arrayObj.get(j),"0");
							}
							if(j==7) {
								viewCnt2 = ObjectUtils.toString(arrayObj.get(j),"0");
							}
							if(j==8) {
								viewCnt3 = ObjectUtils.toString(arrayObj.get(j),"0");
							}
							if(j==9) {
								viewCnt4 = ObjectUtils.toString(arrayObj.get(j),"0");
							}
							if(j==10) {
								viewCnt5 = ObjectUtils.toString(arrayObj.get(j),"0");
							}
							if(j==11) {
								viewCnt6 = ObjectUtils.toString(arrayObj.get(j),"0");
							}
							if(j==12) {
								viewCnt7 = ObjectUtils.toString(arrayObj.get(j),"0");
							}
					}
					
					if(i==5){
						dayal = new ArrayList<String>();
						
						if( dCnt==0||dCnt==1) {
							dayal.add("20171002");
							dayal.add("20171003");
							dayal.add("20171004");
							dayal.add("20171005");
							dayal.add("20171006");
							dayal.add("20171007");
							dayal.add("20171008");
						}
						if( dCnt==2||dCnt==3) {
							dayal.add("20171009");
							dayal.add("20171010");
							dayal.add("20171011");
							dayal.add("20171012");
							dayal.add("20171013");
							dayal.add("20171014");
							dayal.add("20171015");
						}
						
						if( dCnt==4||dCnt==5) {
							dayal.add("20171016");
							dayal.add("20171017");
							dayal.add("20171018");
							dayal.add("20171019");
							dayal.add("20171020");
							dayal.add("20171021");
							dayal.add("20171022");
						}
						
						if( dCnt==6||dCnt==7) {
							dayal.add("20171023");
							dayal.add("20171024");
							dayal.add("20171025");
							dayal.add("20171026");
							dayal.add("20171027");
							dayal.add("20171028");
							dayal.add("20171029");
						}
						
						
						if( dCnt==8||dCnt==9) {
							dayal.add("20171030");
							dayal.add("20171031");
							dayal.add("20171101");
							dayal.add("20171102");
							dayal.add("20171103");
							dayal.add("20171104");
							dayal.add("20171105");
						}
						
						
						
						if( dCnt==10||dCnt==11) {
							dayal.add("20171113");
							dayal.add("20171114");
							dayal.add("20171115");
							dayal.add("20171116");
							dayal.add("20171117");
							dayal.add("20171118");
							dayal.add("20171119");
						}
						
						if( dCnt==12||dCnt==13) {
							dayal.add("20171120");
							dayal.add("20171121");
							dayal.add("20171122");
							dayal.add("20171123");
							dayal.add("20171124");
							dayal.add("20171125");
							dayal.add("20171126");
						}
						
						logger.debug("viewCnt1:"+viewCnt1+":viewCnt2:"+viewCnt2+":viewCnt3:"+viewCnt3);
					}
					
					//				logger.debug("titleStr111:"+titleStr+":clip:"+clipNm+":clipId:"+clipId);
	
					if(titleStr.length()>0) {
						//					if(hm!=null) {
						//						al.add(hm);
						//					}
						//					hm = new HashMap<String,UseClipVo>();
					}
					if(clipNm.length()>0&&clipId.length()>0) {
						//					logger.debug("titleStr:"+titleStr+":clip:"+clipNm+":clipId:"+clipId);
						if(clipId.length()<10) {
							continue;
						}
						if(!("프로그램ID".equals(programId)||clipId.equals("clipId"))) {
							UseClipVo vo = new UseClipVo();
							vo.setClipId(clipId);
							vo.setClipNm(clipNm);
							vo.setChannelNm(channelNm);
							vo.setProgramId(programId);
							vo.setProgramNm(programNm);
							vo.setProgramSubNm(programSubNm);
							vo.setViewCnt1(viewCnt1);
							vo.setViewCnt2(viewCnt2);
							vo.setViewCnt3(viewCnt3);
							vo.setViewCnt4(viewCnt4);
							vo.setViewCnt5(viewCnt5);
							vo.setViewCnt6(viewCnt6);
							vo.setViewCnt7(viewCnt7);
							vo.setChannelNm(channelNm);
							
							al.add(vo);
						}
						
					}

				}
			
				logger.debug("total_arrayCnt:"+al.size());
				
				service.excuteClip(al,dayal);
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("error:"+ex.getMessage());

		}
	}
	
	@Async("executorSubmit")
	public void executorSubmit(UseClipVo vo) throws Exception {
		logger.debug("TEST Thread >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ Thread.currentThread().getName());
		if(service.selectClip(vo)>0){
//			lProesult += mapper.updateClip(vo);
			service.updateClip(vo);
		}else {
//			lProesult += mapper.insertClip(vo);
			service.insertClip(vo);
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
