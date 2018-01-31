package kr.co.kbs.distribute.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.lang.ObjectUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.common.util.CastUtil;
import kr.co.kbs.distribute.common.util.DateUtil;
import kr.co.kbs.distribute.common.util.ParseUtil;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;
import kr.co.kbs.distribute.schedule.vo.CompanyVo;
import kr.co.kbs.distribute.schedule.vo.ContentsVo;
import kr.co.kbs.distribute.schedule.vo.ProgramVo;
import net.sf.json.JSONException;

@Component("schedulePooqDailyAPIBean")
public class DailyProgramInfoApiScheduleTask {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(DailyProgramInfoApiScheduleTask.class);
	
	@Autowired 
	private ScheduleDataMapper mapper;
	
	@Autowired
	private Environment env;
	
//	@Scheduled(cron="40 35 09 * * *") 
	public void requestAliveSelect() {
		try {
			String pooqVodProgramUrl =env.getProperty("pooq.api.program.url"); //https://wapie.pooq.co.kr/v1/vods30/all  : Vod 종류 (01드라마,2예능,03교양,04뉴스,05스포츠,06어린이,07라디오)
			String pooqVodTotalUrl =env.getProperty("pooq.api.total.url"); //https://wapie.pooq.co.kr/v1/vods30/all  : Vod 종류 (01드라마,2예능,03교양,04뉴스,05스포츠,06어린이,07라디오)
			String deviceTypeId =env.getProperty("pooq.api.common.deviceTypeId"); //pc : 요청하는 디바이스명을 입력한다. 형태는 'pc’, 'smarttv’,’ios’,’android’중 하나를 지정한다. [pc]
			String marketTypeId =env.getProperty("pooq.api.common.marketTypeId"); //generic : 요청하는 클라이언트가 앱인 경우(ios, android), 앱스토어의 이름을입력한다. 만약 앱이 아닌 경우에는 'generic’으로 입력한다. 모바일인 경우는 'tstore’,’google’,’itunes’중 하나를 지정한다. [tstore]
			String apiAccessCredential =env.getProperty("pooq.api.common.apiAccessCredential"); //07634EDC22AC363144F08B5643FBEC6B : 본 API를 접근하기 위한 키. 업체별 또는 앱 별로 발급된다.[dummykey]
			String totalOffset =env.getProperty("pooq.api.total.offset"); //0 : 시작 레코드 번호 [0]
			String totalLimit =env.getProperty("pooq.api.total.limit"); //10000 : 한번에 가져오는 데이타 숫자 [5]
			String totalOrderby =env.getProperty("pooq.api.total.orderby"); //D : 정렬방식. 'A’= 옜날 것 부터 보여주기, 'D’= 최신 것부터 보여주기’H’=인기순으로 보여주기 [D]
			String totalIsFree =env.getProperty("pooq.api.total.isFree"); //all : Vod 유무료 여부 (전체 'all’, 유료’n’, 무료 ‘y’)
			String totalChannelId =env.getProperty("pooq.api.total.channelId"); //all : Vod 방영 채널 ID, 지정하지 않으려면 'ANY’로 입력한다.
			
			String pooqVodTotalMain =env.getProperty("pooq.api.total.main"); //https://wapie.pooq.co.kr/v1/vods30/all  : Vod 종류 (01드라마,2예능,03교양,04뉴스,05스포츠,06어린이,07라디오)
			String pooqApiSearchType =env.getProperty("pooq.api.search.type"); //https://wapie.pooq.co.kr/v1/vods30/all  : Vod 종류 (01드라마,2예능,03교양,04뉴스,05스포츠,06어린이,07라디오)
			
			String[] pooqApiSearchTypeArray = pooqApiSearchType.split(",");
			logger.debug("pooqVodTotalUrl:"+pooqVodTotalUrl);
			JSONArray bodyArray = new JSONArray();
			JSONArray cbodyArray = new JSONArray();
			
			

//			=====================================
			//드라마 //pooq.api.yenung.url
			String dremaUrl =env.getProperty("pooq.api.drama.url"); //https://wapie.pooq.co.kr/v1/vods30/all  : Vod 종류 (01드라마,2예능
			logger.debug("dremaUrl:"+dremaUrl);
			bodyArray = new JSONArray();
			cbodyArray = new JSONArray();
			JSONObject items =null;
			JSONObject js1= null;
			logger.debug(pooqApiSearchTypeArray.length+">>>>>>>pooqApiSearchTypeArray.length>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			for(int tArrayCnt =0;tArrayCnt<pooqApiSearchTypeArray.length;tArrayCnt++) {
				
				
				String searchUrl = pooqVodTotalMain+pooqApiSearchTypeArray[tArrayCnt];
				//URL 호출
				//JSON Parsing // 예외처리
	//			JSONObject items =readJsonFromUrl("https://wapie.pooq.co.kr/v1/vods30/all?deviceTypeId=pc&marketTypeId=generic&apiAccessCredential=07634EDC22AC363144F08B5643FBEC6B&offset=0&limit=10000&orderby=D&isFree=all&channelId=ANY"); 
				items =readJsonFromUrl(searchUrl+"?deviceTypeId="+deviceTypeId+"&marketTypeId="+marketTypeId
						+"&apiAccessCredential="+apiAccessCredential+"&offset="+totalOffset+"&limit="+totalLimit
								+"&orderby="+totalOrderby+"&isFree="+totalIsFree+"&channelId="+totalChannelId); 
				// JSON 배열 변환
				js1= (JSONObject)items.get("result");
				String currentDay = ""+DateUtil.getSysYearDay();
				
				logger.debug(">>>>>>>URL>>>>>>>>>>>>>>"+searchUrl+"?deviceTypeId="+deviceTypeId+"&marketTypeId="+marketTypeId
						+"&apiAccessCredential="+apiAccessCredential+"&offset="+totalOffset+"&limit="+totalLimit
						+"&orderby="+totalOrderby+"&isFree="+totalIsFree+"&channelId="+totalChannelId);
				
				String transDay= DateUtil.getDateAdd(currentDay,3,-3,"yyyyMMdd");
				bodyArray = (JSONArray) js1.get("list");
				int comresult=0;
				int proresult=0;
				int conresult=0;
				
				
				logger.debug(bodyArray.size()+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
				
				for(int i=0;i<bodyArray.size();i++) {
					
					try {
						JSONObject js2= (JSONObject) bodyArray.get(i);
						CompanyVo cvo = new CompanyVo();
						cvo.setCNm(ObjectUtils.toString(js2.get("channelTitle")).trim());
						cvo.setCType("S");
						int lcomresult =0;
						if(mapper.selectCom(cvo)>0){
	//						lcomresult = mapper.updateCom(cvo);
						}else {
							lcomresult = mapper.insertCom(cvo);
						}
						comresult+=lcomresult;
						ProgramVo pvo = new ProgramVo();
						
						String broadNm = ObjectUtils.toString(js2.get("channelTitle")).trim();
						broadNm = ParseUtil.getRexStr(broadNm,"^KBS|^MBC|^SBS|^EBS");
						if("".equals(broadNm)) {
							broadNm = "ETC";
						}
						pvo.setBroadNm(broadNm);
						pvo.setProgramNm(ObjectUtils.toString(js2.get("title")).trim());
						pvo.setProgramId(ObjectUtils.toString(js2.get("programId")).trim());
						pvo.setChannelNm(ObjectUtils.toString(js2.get("channelTitle")).trim());
						pvo.setWeekday(ObjectUtils.toString(js2.get("airDateWeekday")).trim());
						pvo.setProgramType(pooqApiSearchTypeArray[tArrayCnt]);
						int lproresult =0;
						
						String proAirDate = ObjectUtils.toString(js2.get("airDate")).trim().replace("-", "");
						
						if(mapper.selectProgram(pvo)>0) {
//							lproresult = mapper.updateProgram(pvo);
						}else {
							lproresult = mapper.insertProgram(pvo);
						}
						proresult+=lproresult;
						
						//한글 처리 및 데이터 읽기
	//					JSONObject items2 =readJsonFromUrl("https://wapie.pooq.co.kr/v1/vods30/all/"+js2.get("programId").toString()+"/?deviceTypeId=pc&marketTypeId=generic&apiAccessCredential=EEBE901F80B3A4C4E5322D58110BE95C&offset=0&limit=10000&orderby=D&isFree=all&credential=none&dummy="); 
						JSONObject items2 =readJsonFromUrl(searchUrl+"/"+js2.get("programId").toString()+"/?"
								+ "deviceTypeId="+deviceTypeId+"&marketTypeId="+marketTypeId+"&apiAccessCredential="+apiAccessCredential+"&"
								+ "offset="+totalOffset+"&limit="+totalOffset+"&orderby="+totalOrderby+"&isFree="+totalIsFree+"&credential=none&dummy="); 
						if(items2!=null) {
							JSONObject js3= null;
							
							if(items2.get("result")!=null) {
								js3=(JSONObject)items2.get("result");
								
								//s1.get(list)
								if(js3.get("list")!=null) {
								
									cbodyArray = (JSONArray) js3.get("list");
									
									for(int j=0;j<cbodyArray.size();j++) {
										JSONObject js4= (JSONObject) cbodyArray.get(j);
										ContentsVo covo  = new ContentsVo();
										covo.setBroadDate(ObjectUtils.toString(js4.get("airDate")).trim());
										covo.setContentsId(ObjectUtils.toString(js4.get("id")).trim());
										covo.setContentsNm(ObjectUtils.toString(js4.get("episodeTitle")).trim());
										covo.setProgramNm(ObjectUtils.toString(js4.get("title")).trim());
										covo.setProgramId(ObjectUtils.toString(js4.get("programId")).trim());
										covo.setVodcnt(ObjectUtils.toString(js4.get("episodeNo")).trim());
										covo.setWeekday(ObjectUtils.toString(js4.get("airDateWeekday")).trim());
										covo.setCornerId(ObjectUtils.toString(js4.get("cornerId")).trim());
										covo.setContentsType("V");
										int lconresult =0;
										
										if(mapper.selectContents(covo)>0) {
	//										lconresult = mapper.updateContents(covo);
										}else {
											lconresult = mapper.insertContents(covo);
										}
										conresult+=lconresult;
									}
								}
							}
						}
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
	//			======================
			}
			
			logger.debug(bodyArray.size()+"###########################################");
			
			//URL 호출
			//JSON Parsing // 예외처리
//			JSONObject items =readJsonFromUrl("https://wapie.pooq.co.kr/v1/vods30/all?deviceTypeId=pc&marketTypeId=generic&apiAccessCredential=07634EDC22AC363144F08B5643FBEC6B&offset=0&limit=10000&orderby=D&isFree=all&channelId=ANY"); 
			items =readJsonFromUrl(pooqVodTotalUrl+"?deviceTypeId="+deviceTypeId+"&marketTypeId="+marketTypeId
					+"&apiAccessCredential="+apiAccessCredential+"&offset="+totalOffset+"&limit="+totalLimit
							+"&orderby="+totalOrderby+"&isFree="+totalIsFree+"&channelId="+totalChannelId); 
			// JSON 배열 변환
			
			logger.debug("pooqVodTotalUrl:"+pooqVodTotalUrl+"?deviceTypeId="+deviceTypeId+"&marketTypeId="+marketTypeId
					+"&apiAccessCredential="+apiAccessCredential+"&offset="+totalOffset+"&limit="+totalLimit
					+"&orderby="+totalOrderby+"&isFree="+totalIsFree+"&channelId="+totalChannelId);
			js1= (JSONObject)items.get("result");
			String currentDay = ""+DateUtil.getSysYearDay();
			String transDay= DateUtil.getDateAdd(currentDay,3,-5,"yyyyMMdd");
			bodyArray = (JSONArray) js1.get("list");
			int comresult=0;
			int proresult=0;
			int conresult=0;
			
			logger.debug("bodyArray.size():"+bodyArray.size());
			for(int i=0;i<bodyArray.size();i++) {
				
				try {
					JSONObject js2= (JSONObject) bodyArray.get(i);
					CompanyVo cvo = new CompanyVo();
					cvo.setCNm(ObjectUtils.toString(js2.get("channelTitle")).trim());
					cvo.setCType("S");
					int lcomresult =0;
					if(mapper.selectCom(cvo)>0){
//						lcomresult = mapper.updateCom(cvo);
					}else {
						lcomresult = mapper.insertCom(cvo);
					}
					comresult+=lcomresult;
					ProgramVo pvo = new ProgramVo();
					
					String broadNm = ObjectUtils.toString(js2.get("channelTitle")).trim();
					broadNm = ParseUtil.getRexStr(broadNm,"^KBS|^MBC|^SBS|^EBS");
					if("".equals(broadNm)) {
						broadNm = "ETC";
					}
					pvo.setBroadNm(broadNm);
					pvo.setProgramNm(ObjectUtils.toString(js2.get("title")).trim());
					pvo.setProgramId(ObjectUtils.toString(js2.get("programId")).trim());
					pvo.setChannelNm(ObjectUtils.toString(js2.get("channelTitle")).trim());
					pvo.setWeekday(ObjectUtils.toString(js2.get("airDateWeekday")).trim());
					int lproresult =0;
					
					String proAirDate = ObjectUtils.toString(js2.get("airDate")).trim().replace("-", "");
					
					
					if(CastUtil.toInt(proAirDate)<CastUtil.toInt(transDay)) {
						continue;
					}
					
					if(mapper.selectProgram(pvo)>0) {
//						lproresult = mapper.updateProgram(pvo);
					}else {
						lproresult = mapper.insertProgram(pvo);
					}
					proresult+=lproresult;
					
					//한글 처리 및 데이터 읽기
//					JSONObject items2 =readJsonFromUrl("https://wapie.pooq.co.kr/v1/vods30/all/"+js2.get("programId").toString()+"/?deviceTypeId=pc&marketTypeId=generic&apiAccessCredential=EEBE901F80B3A4C4E5322D58110BE95C&offset=0&limit=10000&orderby=D&isFree=all&credential=none&dummy="); 
					JSONObject items2 =readJsonFromUrl(pooqVodTotalUrl+"/"+js2.get("programId").toString()+"/?"
							+ "deviceTypeId="+deviceTypeId+"&marketTypeId="+marketTypeId+"&apiAccessCredential="+apiAccessCredential+"&"
							+ "offset="+totalOffset+"&limit="+totalOffset+"&orderby="+totalOrderby+"&isFree="+totalIsFree+"&credential=none&dummy="); 
					if(items2!=null) {
						JSONObject js3= null;
						
						if(items2.get("result")!=null) {
							js3=(JSONObject)items2.get("result");
							
							//s1.get(list)
							if(js3.get("list")!=null) {
							
								cbodyArray = (JSONArray) js3.get("list");
								
								for(int j=0;j<cbodyArray.size();j++) {
									JSONObject js4= (JSONObject) cbodyArray.get(j);
									ContentsVo covo  = new ContentsVo();
									covo.setBroadDate(ObjectUtils.toString(js4.get("airDate")).trim());
									covo.setContentsId(ObjectUtils.toString(js4.get("id")).trim());
									covo.setContentsNm(ObjectUtils.toString(js4.get("episodeTitle")).trim());
									covo.setProgramNm(ObjectUtils.toString(js4.get("title")).trim());
									covo.setProgramId(ObjectUtils.toString(js4.get("programId")).trim());
									covo.setVodcnt(ObjectUtils.toString(js4.get("episodeNo")).trim());
									covo.setWeekday(ObjectUtils.toString(js4.get("airDateWeekday")).trim());
									covo.setCornerId(ObjectUtils.toString(js4.get("cornerId")).trim());
									covo.setContentsType("V");
									int lconresult =0;
									
									if(mapper.selectContents(covo)>0) {
//										lconresult = mapper.updateContents(covo);
									}else {
										lconresult = mapper.insertContents(covo);
									}
									conresult+=lconresult;
								}
							}
						}
					}
					//https://wapie.pooq.co.kr/v1/programs30/K01_T2017-0706?deviceTypeId=pc&marketTypeId=generic&apiAccessCredential=07634EDC22AC363144F08B5643FBEC6B
					JSONObject items3 =readJsonFromUrl(pooqVodProgramUrl+"/"+js2.get("programId").toString()+"/?"
							+ "deviceTypeId="+deviceTypeId+"&marketTypeId="+marketTypeId+"&apiAccessCredential="+apiAccessCredential); 
					if(items3!=null) {
						JSONObject js3= null;
						
						if(items3.get("result")!=null) {
							js3=(JSONObject)items3.get("result");
							
							ProgramVo psvo  = new ProgramVo();
							String weekDay =ObjectUtils.toString(js3.get("airingInfo"));
							weekDay = ParseUtil. getRexStr(weekDay,"^[가-힣]{1},[가-힣]{1},[가-힣]{1},[가-힣]{1},[가-힣]{1}|[가-힣]{1},[가-힣]{1},[가-힣]{1},[가-힣]{1}|[가-힣]{1},[가-힣]{1},[가-힣]{1}|[가-힣]{1},[가-힣]{1}|^[가-힣]{1}");
							weekDay = weekDay.replace(",","");
							
							logger.debug("weekDay:"+weekDay);
							if(weekDay.equals("주")||weekDay.equals("매")) {
								weekDay ="월화수목금";
							}
							if(!"".equals(weekDay)) {
								psvo.setWeekday(weekDay);
								psvo.setProgramId(ObjectUtils.toString(js3.get("programId")));
								psvo.setProgramNm(ObjectUtils.toString(js3.get("programTitle")));
								mapper.updateProgram(psvo);
							}
						}
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			logger.debug("comresult:"+comresult+"proresult:"+proresult+"conresult:"+conresult);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * <pre>
	 * String형을 int형으로 변환하여 반환한다.
	 * </pre>
	 * @param	intStr : 변환할 문자열
	 * @param	initValue : 변환 실패시 반환할 초기값
	 * @return 	변환된 int
	 */
	public static int intValue(String intStr, int initValue) {
		int retInt = initValue;
		try {
			if (intStr != null && intStr.trim().length() > 0) {
				retInt = Integer.parseInt(intStr.trim());	
			}
		} catch (NumberFormatException e) {}
		return retInt;
	}

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException, ParseException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json =  (JSONObject) JSONValue.parseWithException(jsonText);
			return json;
		} finally {
			is.close();
		}
	}
}
