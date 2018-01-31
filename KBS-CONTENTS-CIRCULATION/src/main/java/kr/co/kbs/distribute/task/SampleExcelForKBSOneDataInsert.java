package kr.co.kbs.distribute.task;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.common.util.CastUtil;
import kr.co.kbs.distribute.common.util.DateUtil;
import kr.co.kbs.distribute.common.util.FormatUtil;
import kr.co.kbs.distribute.common.util.excel.ExcelToJsonConverter;
import kr.co.kbs.distribute.common.util.excel.ExcelToJsonConverterConfig;
import kr.co.kbs.distribute.common.util.excel.pojo.ExcelWorkbook;
import kr.co.kbs.distribute.schedule.service.ScheduleDataService;
import kr.co.kbs.distribute.schedule.vo.ContentsVo;
import kr.co.kbs.distribute.schedule.vo.OrgProgramVo;
import kr.co.kbs.distribute.schedule.vo.ProgramVo;
import kr.co.kbs.distribute.schedule.vo.UseClipVo;


@Component("scheduleTempKBSOneDataBean")
public class SampleExcelForKBSOneDataInsert {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(SampleExcelForKBSOneDataInsert.class);

		
	@Autowired
	private ScheduleDataService service;
	
//		public static void main(String[] args) throws IOException {
//			
//			SampleExcelForKBSOneDataInsert task = new SampleExcelForKBSOneDataInsert();
//			task.requestAliveSelect();
//		}
	@Autowired
	private Environment env;


	@Autowired
    private ThreadPoolTaskExecutor  threadPoolTaskExecutor;
	
	private static int lProesult =0;

//		@Scheduled(cron="40 33 14 * * *") 
	public void requestAliveSelect() {
		int totalCount = 0;
		lProesult =0;
		int tapGubun= 0; //0:프로그램Header,1:프로그램관련정보,2:회별프로그램정보,3:KBSPPV

		//		tapGubun =CastUtil.toInt(env.getProperty("kbsapi.tapGubun")); 

				String rootDir ="/usr/local/tomcat/apache-tomcat-8.5.23/";
//		String rootDir ="D:\\KBS\\excel\\";

		try {
			ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
			config.setSourceFile(rootDir+"kbs_171001_171127.xlsx");
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
			
			HashMap<String,OrgProgramVo> oproHm = new HashMap<String,OrgProgramVo>();
			HashMap<String,ProgramVo> proHm = new HashMap<String,ProgramVo>();

			
			for(int tabCnt=0;tabCnt<5;tabCnt++) {
			
				if(tabCnt==0) {
				
					JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(tabCnt);
		
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
		
					for(int i=1;i<bookInfoArray2.size();i++) {
		
						arrayObj = (JSONArray) bookInfoArray2.get(i);
						String program_code ="";
						String program_title = "";
						String media_code ="";
						String media_name ="";
						String channel_code ="";
						String channel_name="";
						String program_genre_code="";
						String program_genre="";
						String onAirDayCode="";
						String onAirDayName="";
						
						
						for(int j=0;j<arrayObj.size();j++) {
							if(j==0) {
								if(!ObjectUtils.toString(arrayObj.get(j),"").equals("")) {
									program_code = ObjectUtils.toString(arrayObj.get(j),"");
								}
							}
							if(j==1) {
								if(!ObjectUtils.toString(arrayObj.get(j),"").equals("")) {
									program_title = ObjectUtils.toString(arrayObj.get(j),"");
								}
							}
		
							if(j==2) {
								if(!ObjectUtils.toString(arrayObj.get(j),"").equals("")) {
									media_code = ObjectUtils.toString(arrayObj.get(j),"");
								}
							}
		
							if(j==3) {
								if(StringUtils.isNotEmpty(ObjectUtils.toString(arrayObj.get(j),""))) {
									media_name = ObjectUtils.toString(arrayObj.get(j),"");
								}
							}
		
							if(j==4) {
								channel_code = ""+CastUtil.toInt(ObjectUtils.toString(arrayObj.get(j),""));
							}
		
							if(j==5) {
								channel_name = ObjectUtils.toString(arrayObj.get(j),"");
							}
		
							if(j==6) {
								program_genre_code = ""+CastUtil.toInt(ObjectUtils.toString(arrayObj.get(j),"0"));
							}
							if(j==7) {
								program_genre = ObjectUtils.toString(arrayObj.get(j),"0");
							}
							if(j==8) {
								onAirDayCode = ObjectUtils.toString(arrayObj.get(j),"0");
							}
							if(j==9) {
								onAirDayName = ObjectUtils.toString(arrayObj.get(j),"0");
							}
						}
						
//						logger.debug("program_code:"+program_code+":program_title:"+program_title+":media_code:"+media_code+":media_name:"+media_name);
//						logger.debug("channel_code:"+channel_code+":channel_name:"+channel_name+":program_genre_code:"+program_genre_code);
//						logger.debug("program_genre:"+program_genre+":onAirDayCode:"+onAirDayCode+":onAirDayName:"+onAirDayName);
//						logger.debug("array-size: "+i);
						
						OrgProgramVo opvo = new OrgProgramVo();
						opvo.setOProgramId(program_code);
						opvo.setOProgramNm(program_title);
						opvo.setProgramType(program_genre_code);
						opvo.setProgramTypeNm(program_genre);
						opvo.setChannelNm("KBS "+ channel_name);
						opvo.setChannelId(channel_code);
						opvo.setWeekday(onAirDayName);
						if(program_genre.indexOf("드라마")>-1||program_genre.indexOf("예능")>-1) {
							opvo.setProgramTypeNm(program_genre);
							opvo.setProgramType(program_genre_code);
						}else {
							opvo.setProgramTypeNm("기타");
							opvo.setProgramType(program_genre_code);
						}
						oproHm.put(program_code, opvo);
						if(service.selectOrgProgram(opvo)>0) {
//						
						}else {
							lProesult+=service.insertOrgProgram(opvo);
						}
					}
					
					logger.debug("total_arrayCnt:"+al.size());
					
					logger.debug("end-validCnt:"+(validCnt)+":"+lProesult);
					logger.debug("end-size : "+(bookInfoArray2.size()));
					logger.debug("end:"+(validCnt/bookInfoArray2.size() *100+"%"));
					
				}
				
				if(tabCnt==1) {
					
				}
				
				if(tabCnt==2) {
					//회별프로그램정보
					JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(tabCnt);
					
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
		
					for(int i=1;i<bookInfoArray2.size();i++) {
		
						arrayObj = (JSONArray) bookInfoArray2.get(i);
						String program_code ="";
						String program_id="";
						String program_title = "";
						String media_code ="";
						String media_name ="";
						String channel_code ="";
						String channel_name="";
						String program_genre_code="";
						String program_genre="";
						String onAirDayCode="";
						String onAirDayName="";
						
						String program_ID_status_code="";
						String program_ID_status="";
						String program_sequence_number="";
						String program_subtitle="";
						String program_ID_workflow_kind_name="";
						
						String program_planned_date="";
						String program_planned_start_time="";
						String program_planned_duration_minute="";
						
						
						for(int j=0;j<arrayObj.size();j++) {
							if(j==0) {
								if(!ObjectUtils.toString(arrayObj.get(j),"").equals("")) {
									program_code = ObjectUtils.toString(arrayObj.get(j),"");
								}
							}
							if(j==1) {
								if(!ObjectUtils.toString(arrayObj.get(j),"").equals("")) {
									program_id = ObjectUtils.toString(arrayObj.get(j),"");
								}
							}
		
							if(j==2) {
								if(!ObjectUtils.toString(arrayObj.get(j),"").equals("")) {
									program_ID_status_code = ObjectUtils.toString(arrayObj.get(j),"");
								}
							}
		
							if(j==3) {
								if(StringUtils.isNotEmpty(ObjectUtils.toString(arrayObj.get(j),""))) {
									program_ID_status = ObjectUtils.toString(arrayObj.get(j),"");
								}
							}
		
							if(j==4) {
								program_sequence_number = ""+CastUtil.toInt(ObjectUtils.toString(arrayObj.get(j),"1"));
							}
		
							if(j==5) {
								program_subtitle = ObjectUtils.toString(arrayObj.get(j),"");
							}
		
							if(j==8) {
								program_ID_workflow_kind_name = ObjectUtils.toString(arrayObj.get(j),"0");
							}
							if(j==9) {
								program_planned_date = StringUtils.rightPad(ObjectUtils.toString(arrayObj.get(j),"0").replace(".","").replace("E7", ""), 8, "0");
							}
							if(j==10) {
								program_planned_start_time = ""+CastUtil.toInt(FormatUtil.matchFormat(ObjectUtils.toString(arrayObj.get(j),"0"), "####"));
								if(program_planned_start_time.length()==3) {
									program_planned_start_time="0"+program_planned_start_time;
								}
								
								if(program_planned_start_time.length()==1) {
									program_planned_start_time="000"+program_planned_start_time;
								}
							}
							if(j==11) {
								program_planned_duration_minute = ""+CastUtil.toInt(ObjectUtils.toString(arrayObj.get(j),"0"));
							}
						}
						
						logger.debug("program_code:"+program_code+":program_id:"+program_id+":program_ID_status_code:"+program_ID_status_code);
						logger.debug("program_ID_status:"+program_ID_status+":program_sequence_number:"+program_sequence_number+":program_subtitle:"+program_subtitle);
						logger.debug("program_ID_workflow_kind_name:"+program_ID_workflow_kind_name+":program_planned_date:"+program_planned_date+":program_planned_start_time:"+program_planned_start_time);
						logger.debug("program_planned_duration_minute:"+program_planned_duration_minute+":array-size: "+i);
						
						OrgProgramVo ovo = null;
						if(oproHm.get(program_code)!=null) {
							ovo = oproHm.get(program_code);
							String idH = "";
							if(ovo.getChannelId().equals("11")) {
								idH= "K01";
							}else {
								idH= "K02";
							}
							ProgramVo pVo = new ProgramVo();
							pVo.setOProgramId(program_code);
							pVo.setProgramId(idH+"_"+program_code); //
							pVo.setProgramNm(ovo.getOProgramNm());
							pVo.setProgramType(ovo.getProgramTypeNm());
							pVo.setCornerId("1");
							pVo.setChannelNm(ovo.getChannelNm());
							pVo.setBroadNm("KBS");
							pVo.setWeekday(ovo.getWeekday());
							if(program_ID_workflow_kind_name.equals("재방")) {
								pVo.setReviewYn("Y");
							}
							proHm.put(program_id, pVo);
							if(service.selectProgram(pVo)>0) {
//								
							}else {
								lProesult+=service.insertProgram(pVo);
							}
							
							ContentsVo cVo = new ContentsVo();
							cVo.setOProgramId(program_code);
							cVo.setProgramId(idH+"_"+program_code);
							cVo.setContentsId(idH+"_"+program_id);
							cVo.setContentsNm(StringUtils.defaultString(program_subtitle.trim(), ovo.getOProgramNm()));
							cVo.setCornerId("1");
							cVo.setWeekday(ovo.getWeekday());
							cVo.setVodcnt(program_sequence_number);
							cVo.setContentsType("V"); //V:VOD,C:CLIP
							cVo.setBroadDate(FormatUtil.matchFormat(program_planned_date,"####-##-##"));
							cVo.setBroadStdt(program_planned_date+program_planned_start_time+"00");
							
							logger.debug("program_planned_date:"+program_planned_date);
							logger.debug("program_planned_start_time:"+program_planned_start_time);
							logger.debug("program_planned_duration_minute:"+program_planned_duration_minute);
							
							
							String endDate = DateUtil.getDateAdd(DateUtil.getDate(program_planned_date+program_planned_start_time+"00"), 5, 
									CastUtil.toInt(program_planned_duration_minute), "yyyyMMddHHmmss");
							cVo.setBroadEddt(endDate);
							if(program_ID_workflow_kind_name.equals("재방")) {
								cVo.setReviewYn("Y");
							}
							
							if(service.selectContents(cVo)>0) {
								lProesult+=service.updateContents(cVo);
							}else {
								lProesult+=service.insertContents(cVo);
							}
							
							
						}
					}
					
					logger.debug("total_arrayCnt:"+al.size());
					
					logger.debug("end-validCnt:"+(validCnt)+":"+lProesult);
					logger.debug("end-size : "+(bookInfoArray2.size()));
					logger.debug("end:"+(validCnt/bookInfoArray2.size() *100+"%"));
					
				}
				
				if(tabCnt==3) {
					
				}
				
				if(tabCnt==4) {
					//시청률정보
					
				}
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("error:"+ex.getMessage());

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
