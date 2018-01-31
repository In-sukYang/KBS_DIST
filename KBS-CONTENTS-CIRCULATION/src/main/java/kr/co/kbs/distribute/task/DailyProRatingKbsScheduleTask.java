package kr.co.kbs.distribute.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.common.util.CastUtil;
import kr.co.kbs.distribute.common.util.DateUtil;
import kr.co.kbs.distribute.common.util.XmlUtil;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;
import kr.co.kbs.distribute.schedule.vo.DcViewRatingVo;

@Component("scheduleKbsDailyProRatingBean")
public class DailyProRatingKbsScheduleTask {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(DailyProRatingKbsScheduleTask.class);
	
	@Autowired 
	private ScheduleDataMapper mapper;
	
	
	@Autowired
	private Environment env;
	
	
//	@Scheduled(cron="10 17 19 * * *") 
	public void requestAliveSelect() {
		int totalInsertCount = 0;
		int totalUpdateCount = 0;
		int totalInsertCodeCount = 0;
		
		String dailyRatingUrl =env.getProperty("kbsapi.dailyrating"); 
		
		try {
			String requestSystemId=env.getProperty("kbsapi.requestSystemId");
			String requestSystemPassword=env.getProperty("kbsapi.requestSystemPassword");
			String userId=env.getProperty("kbsapi.userId");
			
			//1. 주간 편성 조회
			//(1) http://ppsx.kbs.co.kr/KBS_PPS_IF.asmx?op=PPS_GET_WEEKLY_PROGRAMMING_V4
			// ① Request: 하루 전일을 기준으로, 새로 추가된 프로그램/회별프로그램 조회			
						
			String checkDay ="";
			
			boolean totalGubun=false;
			if(StringUtils.isEmpty(env.getProperty("kbsapi.startdt"))){
				checkDay = ""+DateUtil.getSysYearDay();
			}else {
				if(env.getProperty("kbsapi.startdt").equals("TOTAL")) {
					totalGubun= true;
				}else {
					checkDay = ""+env.getProperty("kbsapi.startdt");
				}
			}
			
			String yesterdayStr = "";
			if(totalGubun) {
				for(int dayCnt=0;dayCnt<86;dayCnt++) {
					yesterdayStr= "20171001";
					
					yesterdayStr = DateUtil.getDateAdd(yesterdayStr, 3, dayCnt,"yyyyMMdd");
					logger.debug("yesterdayStr:["+yesterdayStr+"]");
					
					
					String urlStr= dailyRatingUrl+"?request_system_ID="+requestSystemId+
							"&request_system_password="+requestSystemPassword+"&user_ID="+userId+"&program_onair_date="+yesterdayStr;
					
					logger.debug("urlStr:["+urlStr+"]");
					String expression = "//PROGRAM_RATE_AD";
					ArrayList<NodeList>nl =XmlUtil.getXmlChildNodeList(urlStr, expression);
					String programId ="";
					String channel ="";
					String programNm ="";
					String onAirDate ="";
					String onAirStTime ="";
					String onAirEdTime ="";
					String ratingKindCode ="";
					String rating ="";
					
					ArrayList<DcViewRatingVo> voal = new ArrayList<DcViewRatingVo>();
					HashMap<String,DcViewRatingVo> hm = new HashMap<String,DcViewRatingVo>();
					for(int i=0;i<nl.size();i++) {
						NodeList nl2 = nl.get(i);
						String programming_local_station_code = XmlUtil.getNodeContentStr(nl2, "programming_local_station_code");
						if("00".equals(programming_local_station_code)||"10".equals(programming_local_station_code)) {
							programId = XmlUtil.getNodeContentStr(nl2, "program_ID");
							channel = XmlUtil.getNodeContentStr(nl2, "channel_code");
							
							if(StringUtils.isNotEmpty(programId)) {
								if(channel.equals("11")) {
									programId = "K01_"+programId;
								}
								
								if(channel.equals("12")) {
									programId = "K02_"+programId;
								}
							}
							
							programNm = XmlUtil.getNodeContentStr(nl2, "program_title");
							onAirDate = XmlUtil.getNodeContentStr(nl2, "program_onair_date");
							onAirStTime = XmlUtil.getNodeContentStr(nl2, "program_onair_start_time");
							onAirEdTime = XmlUtil.getNodeContentStr(nl2, "program_onair_end_time");
							ratingKindCode = XmlUtil.getNodeContentStr(nl2, "ratings_kind_code");
							rating = XmlUtil.getNodeContentStr(nl2, "household_ratings");
							
							if(StringUtils.isNotEmpty(programId)) {
								String keyVal = programId+programNm+programming_local_station_code+channel;
								logger.debug("programId:"+programId);
								logger.debug("keyVal:"+keyVal+":"+hm.get(keyVal));
								if(hm.get(keyVal)==null) {
									DcViewRatingVo dcvrVo = new DcViewRatingVo();
									dcvrVo.setContentsId(programId);
									dcvrVo.setContentsNm(programNm);
									dcvrVo.setBroadDate(onAirDate+onAirStTime);
									dcvrVo.setBroadStdt(onAirDate+onAirStTime);
									dcvrVo.setBroadEddt(onAirDate+onAirEdTime);
									dcvrVo.setChannel(channel);
									if("1".equals(ratingKindCode)) {
										dcvrVo.setViewRating(rating);
										dcvrVo.setViewRatingHouse(rating);
									}else {
										dcvrVo.setViewShare(rating);
									}
									dcvrVo.setStatCode(programming_local_station_code);
									hm.put(keyVal, dcvrVo);
								}else {
									DcViewRatingVo dcvrVo = hm.get(keyVal);
									dcvrVo.setContentsId(programId);
									dcvrVo.setContentsNm(programNm);
									dcvrVo.setBroadDate(onAirDate+onAirStTime);
									dcvrVo.setBroadStdt(onAirDate+onAirStTime);
									dcvrVo.setBroadEddt(onAirDate+onAirEdTime);
									dcvrVo.setChannel(channel);
									if("1".equals(ratingKindCode)) {
										dcvrVo.setViewRating(rating);
										dcvrVo.setViewRatingHouse(rating);
									}else {
										dcvrVo.setViewShare(rating);
									}
									dcvrVo.setStatCode(programming_local_station_code);
									voal.add(dcvrVo);
								}
							}
						}
					}// end nl
					
					for(int k=0;k<voal.size();k++) {
						if(mapper.selectDcViewRate(voal.get(k))>0) {
							mapper.updateDcViewRate(voal.get(k));
							totalUpdateCount++;
						}else {
							mapper.insertDcViewRate(voal.get(k));
							totalInsertCount++;
						}
					}
					Thread.sleep(30000);
				}
				mapper.excuteLastDcViewLating();
			}else {
				String dailyTermUnit=StringUtils.defaultIfEmpty(env.getProperty("kbsapi.dailyTermUnit"),"3");
				
				for(int yCnt=-1*CastUtil.toInt(dailyTermUnit);yCnt<0;yCnt++) {
					yesterdayStr = DateUtil.getDateAdd(checkDay, 3, yCnt,"yyyyMMdd");
					
					logger.debug("yesterdayStr:["+yesterdayStr+"]");
					
					String urlStr= dailyRatingUrl+"?request_system_ID="+requestSystemId+
							"&request_system_password="+requestSystemPassword+"&user_ID="+userId+"&program_onair_date="+yesterdayStr;
					
					logger.debug("urlStr:["+urlStr+"]");
					String expression = "//PROGRAM_RATE_AD";
					ArrayList<NodeList>nl =XmlUtil.getXmlChildNodeList(urlStr, expression);
						
					String programId ="";
					String channel ="";
					String programNm ="";
					String onAirDate ="";
					String onAirStTime ="";
					String onAirEdTime ="";
					String ratingKindCode ="";
					String rating ="";
					
					ArrayList<DcViewRatingVo> voal = new ArrayList<DcViewRatingVo>();
					HashMap<String,DcViewRatingVo> hm = new HashMap<String,DcViewRatingVo>();
					for(int i=0;i<nl.size();i++) {
						NodeList nl2 = nl.get(i);
						String programming_local_station_code = XmlUtil.getNodeContentStr(nl2, "programming_local_station_code");
						if("00".equals(programming_local_station_code)||"10".equals(programming_local_station_code)) {
							programId = XmlUtil.getNodeContentStr(nl2, "program_ID");
							channel = XmlUtil.getNodeContentStr(nl2, "channel_code");
							
							if(StringUtils.isNotEmpty(programId)) {
								if(channel.equals("11")) {
									programId = "K01_"+programId;
								}
								
								if(channel.equals("12")) {
									programId = "K02_"+programId;
								}
							}
							
							programNm = XmlUtil.getNodeContentStr(nl2, "program_title");
							onAirDate = XmlUtil.getNodeContentStr(nl2, "program_onair_date");
							onAirStTime = XmlUtil.getNodeContentStr(nl2, "program_onair_start_time");
							onAirEdTime = XmlUtil.getNodeContentStr(nl2, "program_onair_end_time");
							ratingKindCode = XmlUtil.getNodeContentStr(nl2, "ratings_kind_code");
							rating = XmlUtil.getNodeContentStr(nl2, "household_ratings");
							
							if(StringUtils.isNotEmpty(programId)) {
								String keyVal = programId+programNm+programming_local_station_code+channel;
								logger.debug("programId:"+programId);
								logger.debug("keyVal:"+keyVal+":"+hm.get(keyVal));
								if(hm.get(keyVal)==null) {
									DcViewRatingVo dcvrVo = new DcViewRatingVo();
									dcvrVo.setContentsId(programId);
									dcvrVo.setContentsNm(programNm);
									dcvrVo.setBroadDate(onAirDate+onAirStTime);
									dcvrVo.setBroadStdt(onAirDate+onAirStTime);
									dcvrVo.setBroadEddt(onAirDate+onAirEdTime);
									dcvrVo.setChannel(channel);
									if("1".equals(ratingKindCode)) {
										dcvrVo.setViewRating(rating);
										dcvrVo.setViewRatingHouse(rating);
									}else {
										dcvrVo.setViewShare(rating);
									}
									dcvrVo.setStatCode(programming_local_station_code);
									hm.put(keyVal, dcvrVo);
								}else {
									DcViewRatingVo dcvrVo = hm.get(keyVal);
									dcvrVo.setContentsId(programId);
									dcvrVo.setContentsNm(programNm);
									dcvrVo.setBroadDate(onAirDate+onAirStTime);
									dcvrVo.setBroadStdt(onAirDate+onAirStTime);
									dcvrVo.setBroadEddt(onAirDate+onAirEdTime);
									dcvrVo.setChannel(channel);
									if("1".equals(ratingKindCode)) {
										dcvrVo.setViewRating(rating);
										dcvrVo.setViewRatingHouse(rating);
									}else {
										dcvrVo.setViewShare(rating);
									}
									dcvrVo.setStatCode(programming_local_station_code);
									voal.add(dcvrVo);
								}
							}
						}
					}// end nl
					
					for(int k=0;k<voal.size();k++) {
						if(mapper.selectDcViewRate(voal.get(k))>0) {
							mapper.updateDcViewRate(voal.get(k));
							totalUpdateCount++;
						}else {
							mapper.insertDcViewRate(voal.get(k));
							totalInsertCount++;
						}
					}
				}
				mapper.excuteLastDcViewLating();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		
		logger.debug("totalInsertCount :["+totalInsertCount+"]:totalUpdateCount:["+totalUpdateCount+"]: totalInsertCodeCount:["+totalInsertCodeCount+"]");
	}
	
	
	/**
	 * URL정보를 통하여 표현식의 Child NodeList를 가져오는 함수
	 * @param URL
	 * @param expression
	 * @return
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static ArrayList<NodeList> getXmlChildNodeList(String URL, String expression) {
		ArrayList<NodeList> al = new ArrayList<NodeList>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(URL);
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			NodeList nodeList = (NodeList)xpath.evaluate(expression, doc, XPathConstants.NODESET);
			
			for(int idx=0;idx<nodeList.getLength();idx++) {
				NodeList subList = nodeList.item(idx).getChildNodes();
				al.add(subList);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return al;
	}
	
	
	/**
	 * NodeList 에서 특정 NodeName을 갖는 Array 리턴
	 * @param nl
	 * @param nodeName
	 * @return
	 */
	public static String getNodeContentStr(NodeList nl , String nodeName) {
		String returnStr="";
		try {
			if(nl!=null) {
				for(int i=0;i<nl.getLength();i++) {
					if(nl.item(i).getNodeName().equals(nodeName)){
						returnStr = nl.item(i).getTextContent();
						break;
					}
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return returnStr;
	}
	
}
