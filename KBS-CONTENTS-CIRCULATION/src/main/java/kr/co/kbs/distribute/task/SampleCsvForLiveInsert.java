package kr.co.kbs.distribute.task;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.common.util.JsonUtil;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;
import kr.co.kbs.distribute.schedule.vo.UseStatChannelVo;


@Component("scheduleTempCSVLiveBean")
//@Component
public class SampleCsvForLiveInsert {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(SampleCsvForLiveInsert.class);

	@Autowired 
	private ScheduleDataMapper mapper;

//	public static void main(String[] args) throws IOException {
//
//		SampleCsvForLiveInsert task = new SampleCsvForLiveInsert();
//		task.requestAliveSelect();
//	}
	//	
	@Autowired
	private Environment env;

//	@Scheduled(cron="10 42 16 * * *") 
	public void requestAliveSelect() {
		int totalCount = 0;
		int lProesult =0;
		int tapGubun= 0; //0:KBSPPM,1:MBCPPM,2:SBSPPM,3:KBSPPV

		String[] rootDir = {
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/pooq/Live/201711/"};
//		String rootDir ="D:\\KBS\\csv\\";
//		String[] rootDir = {"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171130/KBS데이터_20171130/pooq/Live/201710/"
//				,"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171130/KBS데이터_20171130/pooq/Live/201711/"
//		};
		//		tapGubun =CastUtil.toInt(env.getProperty("kbsapi.tapGubun")); 
		try {
			int RCnt=0;
			String readStr = "";
				
			File filedir = new File(rootDir[0]);
			
			String [] listFileStr = filedir.list();
			
			for(int p=11;p<listFileStr.length;p++) {
				try {
					readStr = JsonUtil.readCsv(rootDir[0]+listFileStr[p],"UTF-8");
				}catch(Exception ex) {
					ex.printStackTrace();
					continue;
				}
					
	//			String readStr = JsonUtil.readCsv(rootDir+"live.csv","UTF-8");
				//kbs_live.csv
				String jsonStr = JsonUtil.CSVtoJSON(readStr);
	
	
				System.out.println("=====번째 START==============");
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(jsonStr);
	
				JSONArray infoarray = (JSONArray) obj;
				
				
				UseStatChannelVo uscVo = new UseStatChannelVo();
				HashMap<String,UseStatChannelVo> hm = new HashMap<String,UseStatChannelVo>();
				for(int i=0;i<infoarray.size();i++) {
					JSONObject jsonObj = (JSONObject) infoarray.get(i);
					String tempTime="";
					String ageGroup="";
					String viewCnt="";
					String sibun="";
					String channel="";
					String viewDate="";
					String sexGubun="";
					String tempStr="";
					Iterator iter = jsonObj.keySet().iterator();
					
					while (iter.hasNext()) {
						String key = (String) iter.next();
						String value = ""+jsonObj.get(key);
						logger.debug("====="+key+":"+value+"==============");
	//					if(key.trim().equals("날짜")) {
	//						logger.debug("===skfdsfdsfsd=="+key+":"+value+"==============");
	//					}
						
						
						if(key.equals("2")) {
							tempStr=tempStr+value;
							sibun=value;
						}
						
						if(key.equals("1")) {
							tempStr=tempStr+value;
							channel=value;
						}
						
						if(key.equals("0")) {
							tempStr=tempStr+value;
							viewDate=value;
							logger.debug("====viewDate="+viewDate+":tempStr:"+tempStr+"==============");
						}
						
						if(key.equals("4")) {
							ageGroup = value;
						}
						
						if(key.equals("3")) {
							tempStr+=value;
							sexGubun = value;
						}
						
						if(key.equals("5")) {
							viewCnt = value;
						}
					}
					if(hm.get(tempStr)==null) {
						uscVo = new UseStatChannelVo();
					}else {
						uscVo = hm.get(tempStr);
					}
					uscVo.setBroadDate(viewDate+sibun);
					uscVo.setChannel(channel);
					if(ageGroup.equals("0")) uscVo.setAge0Vcnt(viewCnt);
					if(ageGroup.equals("20")) uscVo.setAge20Vcnt(viewCnt);
					if(ageGroup.equals("30")) uscVo.setAge30Vcnt(viewCnt);
					if(ageGroup.equals("40")) uscVo.setAge40Vcnt(viewCnt);
					if(ageGroup.equals("50")) uscVo.setAge50Vcnt(viewCnt);
					uscVo.setSexGubun(sexGubun);
					uscVo.setCNm("pooq");
					hm.put(tempStr, uscVo);
					logger.debug("====="+i+"번째 END==============");
				}
				
				Iterator<String> keys = hm.keySet().iterator();
		        while( keys.hasNext() ){
		            String key = keys.next();
		            UseStatChannelVo vo = hm.get(key);
		            if(mapper.selectUsageStatChannel(vo)>0) {
		            	lProesult+=mapper.updateUsageStatChannel(vo);
		            }else {
		            	lProesult+=mapper.insertUsageStatChannel(vo);
		            }
		        }
				logger.debug("end-validCnt:"+infoarray.size()+":"+hm.size()+":"+lProesult);
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
