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


@Component("scheduleTempCSVPPVBean")
//@Component
public class SampleCsvForKBSPPVInsert {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(SampleCsvForKBSPPVInsert.class);

	@Autowired 
	private ScheduleDataMapper mapper;

	
	@Autowired 
	private ScheduleDataService service;
	
//	public static void main(String[] args) throws IOException {
//		try {
//			SampleCsvForKBSPPVInsert task = new SampleCsvForKBSPPVInsert();
//			task.requestAliveSelect();
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//	}
	//	
	@Autowired
	private Environment env;

//	@Scheduled(cron="40 15 23 * * *") 
	public void requestAliveSelect() {
		int totalCount = 0;
		int lProesult =0;
		int tapGubun= 0; //0:KBSPPM,1:MBCPPM,2:SBSPPM,3:KBSPPV

//				String rootDir ="/usr/local/tomcat/apache-tomcat-8.5.23/";
		String[] rootDir = {
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171130/KBS데이터_20171130/pooq/VOD/KBS2/201710/"
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/pooq/VOD/KBS2/201710/"
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/pooq/VOD/KBS2/201711/",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/pooq/VOD/KBS1/201710/",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/pooq/VOD/KBS1/201711/"
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/pooq/VOD/MBC/201710/",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/pooq/VOD/MBC/201711/",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/pooq/VOD/SBS/201710/",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/pooq/VOD/SBS/201711/"
//				"E:\\00.project\\00.2017\\16.KBS_유통정보시스템\\00.받은파일\\20171130\\KBS데이터_20171130\\pooq\\VOD\\MBC\\201710\\",
//				"E:\\00.project\\00.2017\\16.KBS_유통정보시스템\\00.받은파일\\20171130\\KBS데이터_20171130\\pooq\\VOD\\MBC\\201711\\"
				};
		
//		String[] rootDir = {
//				"E:\\00.project\\00.2017\\16.KBS_유통정보시스템\\00.받은파일\\20171130\\KBS데이터_20171130\\pooq\\VOD\\MBC\\201710\\"};
//String[] rootDir = {"E:\\00.project\\00.2017\\16.KBS_유통정보시스템\\00.받은파일\\20171130\\KBS데이터_20171130\\pooq\\VOD\\KBS1\\201710\\",
//		"E:\\00.project\\00.2017\\16.KBS_유통정보시스템\\00.받은파일\\20171130\\KBS데이터_20171130\\pooq\\VOD\\KBS1\\201711\\"};

		//		tapGubun =CastUtil.toInt(env.getProperty("kbsapi.tapGubun")); 
		try {
			//			String readStr = readCsv("D:\\KBS\\csv\\kbs1_ppv.csv");
			//			String readStr = readCsv("D:\\KBS\\csv\\kbs2_ppv.csv");
//			int RCnt =0;
			
			for(int dCnt=0;dCnt<rootDir.length;dCnt++) {
				String readStr = "";
				
				
				service.PooqPPMTask(rootDir[dCnt]);
				
//				File filedir = new File(rootDir[dCnt]);
//				
//				if(!filedir.exists()) {
//					continue;
//				}
//				
//				String [] listFileStr = filedir.list();
//				
//				for(int p=0;p<listFileStr.length;p++) {
//				
//					try {
//						readStr = JsonUtil.readCsv(rootDir[dCnt]+listFileStr[p],"UTF-8");
//					}catch(Exception ex) {
//						ex.printStackTrace();
//						continue;
//					}
//					//kbs_live.csv
//					String jsonStr = JsonUtil.CSVtoJSON(readStr);
//		
//					JSONParser parser = new JSONParser();
//					Object obj = parser.parse(jsonStr);
//		
//					JSONArray infoarray = (JSONArray) obj;
//		
//					for(int i=0;i<infoarray.size();i++) {
//						JSONObject jsonObj = (JSONObject) infoarray.get(i);
//						UseStatProVo uspVo = new UseStatProVo();
//						uspVo.setProType("PPM");
//						uspVo.setComNm("pooq");
//						uspVo.setInputId("SYSTEM");
//						
//						Iterator iter = jsonObj.keySet().iterator();
//						while (iter.hasNext()) {
//							String key = (String) iter.next();
//							int keyInt = CastUtil.toInt(key);
//							String value ="";
//							value = ""+jsonObj.get(""+keyInt);
//							
//							if(keyInt==0) {
//								uspVo.setViewDate(value);
//							}
//							if(keyInt==2) uspVo.setChannelId(value);
//							if(keyInt==3) uspVo.setProgramId(value);
//							if(keyInt==6) uspVo.setProgramNm(value);
//							if(keyInt==4) uspVo.setContentsId(value);
//							if(keyInt==8) {
//								if(value.equals("N")) {
//									uspVo.setChargeYn("Y");
//								}else {
//									uspVo.setChargeYn("N");
//								}
//							}
//							if(keyInt==10) uspVo.setChargeVtime(value);//유료시청시간
//							if(keyInt==11) uspVo.setHChargeVtime(value);//유료시청시간(초고화질)
//							if(keyInt==12) uspVo.setChargeCnt(value);//유료시청자수
//							if(keyInt==13) uspVo.setHChargeCnt(value);//초고화질유료시청자수
//							if(keyInt==14) uspVo.setFreeChargeVtime(value);//무료시청시간
//							if(keyInt==15) uspVo.setHFreeChargeVtime(value);//초고화질무료시청시간
//							if(keyInt==16) uspVo.setFreeChargeCnt(value);//무료시청자수
//							if(keyInt==17) uspVo.setHFreeChargeCnt(value);//무료시청자수(초고화질)
//						}
//						
//						try {
//						UseStatProVo uspVoResp = mapper.selectGetContentsIdInfo(uspVo);
//						
//						
////						if(dCnt<4) {
////							uspVo.setChannelId("KBS");
////						}else if (dCnt>5) {
////							uspVo.setChannelId("SBS");
////						}else {
////							uspVo.setChannelId("MBC");
////						}
////						
//						
//						if(uspVoResp!=null) {
//							uspVo.setPcSeq(uspVoResp.getPcSeq());
//							uspVo.setPSeq(uspVoResp.getPSeq());
//							uspVo.setTempData(uspVo.getProgramNm()+"|"+uspVo.getProgramId()+"|"+uspVo.getContentsId());
//							if(mapper.selectUseStatProgram(uspVo)>0){
//								lProesult += mapper.updateUseStatProgram(uspVo);
//							}else {
//								lProesult += mapper.insertUseStatProgram(uspVo);
//							}
//						}else {
//							uspVo.setPcSeq(-1);
//							uspVo.setPSeq(-1);
//							uspVo.setTempData(uspVo.getProgramNm()+"|"+uspVo.getProgramId()+"|"+uspVo.getContentsId());
//							lProesult += mapper.insertUseStatProgram(uspVo);
//						}
//						}catch(Exception ex) {
//							ex.printStackTrace();
//							throw ex;
//						}
//					}
//			}
			logger.debug("end-validCnt:"+lProesult);
			}
		}catch(Exception ex) {
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
