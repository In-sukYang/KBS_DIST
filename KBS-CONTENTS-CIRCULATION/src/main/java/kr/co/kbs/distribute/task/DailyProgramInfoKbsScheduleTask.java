package kr.co.kbs.distribute.task;

import java.io.IOException;
import java.util.ArrayList;

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
import kr.co.kbs.distribute.common.util.FormatUtil;
import kr.co.kbs.distribute.common.util.XmlUtil;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;
import kr.co.kbs.distribute.schedule.vo.ContentsVo;
import kr.co.kbs.distribute.schedule.vo.OrgProgramVo;
import kr.co.kbs.distribute.schedule.vo.ProgramVo;

@Component("scheduleKbsDailyProgramInfoBean")
public class DailyProgramInfoKbsScheduleTask {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(DailyProgramInfoKbsScheduleTask.class);
	
	@Autowired 
	private ScheduleDataMapper mapper;
	
	
	@Autowired
	private Environment env;
	
//	@Scheduled(cron="00 55 21 * * *") 
	public void requestAliveSelect() {
		int totalInsertCount = 0;
		int totalUpdateCount = 0;
		int totalInsertCodeCount = 0;
		
		
		String weeklyProgramUrl =env.getProperty("kbsapi.weeklyprogramurl"); 
		//"http://ippsx.kbs.co.kr/KBS_PPS_IF.asmx/PPS_GET_WEEKLY_PROGRAMMING_V4";
		String programInfoUrl3 = env.getProperty("kbsapi.programInfoUrl3"); 
		//"http://ippsx.kbs.co.kr/KBS_PPS_IF.asmx/PPS_GET_PROGRAM_ID_INFORMATION_V3";
		
		
		String requestSystemId=env.getProperty("kbsapi.requestSystemId");
		String requestSystemPassword=env.getProperty("kbsapi.requestSystemPassword");
		String userId=env.getProperty("kbsapi.userId");
		String programLocalStationCode=env.getProperty("kbsapi.programLocalStationCode");
		String[] channelCode= {"12","11"};
		
		StringBuffer sssbuf= new StringBuffer();
		
		try {
			//1. 주간 편성 조회
			//(1) http://ppsx.kbs.co.kr/KBS_PPS_IF.asmx?op=PPS_GET_WEEKLY_PROGRAMMING_V4
			// ① Request: 하루 전일을 기준으로, 새로 추가된 프로그램/회별프로그램 조회			
			String checkDay ="";
			
			if(StringUtils.isEmpty(env.getProperty("kbsapi.startdt"))){
				checkDay = ""+DateUtil.getSysYearDay();
			}else {
				if(env.getProperty("kbsapi.startdt").equals("TOTAL")) {
				}else {
					checkDay = ""+env.getProperty("kbsapi.startdt");
				}
			}
			
			String yesterdayStr = "";
			String dailyTermUnit=StringUtils.defaultIfEmpty(env.getProperty("kbsapi.dailyTermUnit"),"3");
//			String dailyTermUnit="70";
			
			for(int yCnt=-1*CastUtil.toInt(dailyTermUnit);yCnt<0;yCnt++) {
				yesterdayStr = DateUtil.getDateAdd(checkDay, 3, yCnt,"yyyyMMdd");
				
	//			yesterdayStr = DateUtil.getDateAdd(checkDay, 3, -1,"yyyyMMdd");
				
				logger.debug("yesterdayStr:["+yesterdayStr+"]");
				for(int chCode=0;chCode<channelCode.length;chCode++) {
					String urlStr= weeklyProgramUrl+"?request_system_ID="+requestSystemId+
							"&request_system_password="+requestSystemPassword+"&user_ID="+userId+"&channel_code="+channelCode[chCode]
							+ "&programming_local_station_code="+programLocalStationCode+"&program_planned_date="+yesterdayStr;
					
					logger.debug("urlStr:["+urlStr+"]");
					String expression = "//WEEKLY_PROGRAMMING";
					ArrayList<NodeList>nl =XmlUtil.getXmlChildNodeList(urlStr, expression);
					System.out.println("nl.size():["+nl.size()+"]");
					
					
					String programId ="";
					for(int i=0;i<nl.size();i++) {
						
						ArrayList<ProgramVo> pList = new ArrayList<ProgramVo>();
						ArrayList<ContentsVo> cList = new ArrayList<ContentsVo>();
						
						NodeList nl2 = nl.get(i);
						logger.debug("program_code:"+XmlUtil.getNodeContentStr(nl2, "program_code"));
						logger.debug("program_title:"+XmlUtil.getNodeContentStr(nl2, "program_title"));
						logger.debug("program_ID:"+XmlUtil.getNodeContentStr(nl2, "program_ID"));
						logger.debug("channel_name:"+XmlUtil.getNodeContentStr(nl2, "channel_name"));
						
						programId = XmlUtil.getNodeContentStr(nl2, "program_ID");
						OrgProgramVo opVo = new OrgProgramVo();
						opVo.setOProgramId(XmlUtil.getNodeContentStr(nl2, "program_code"));
						opVo.setOProgramNm(XmlUtil.getNodeContentStr(nl2, "program_title"));
						opVo.setChannelNm("KBS "+XmlUtil.getNodeContentStr(nl2, "channel_name"));
						
						String genreNm = XmlUtil.getNodeContentStr(nl2, "program_genre");
						String genreNmCd = XmlUtil.getNodeContentStr(nl2, "program_genre_code");
						
						
						opVo.setProgramTypeNm(CastUtil.getRenreMap(CastUtil.toInt(genreNmCd), true));
						opVo.setProgramType(CastUtil.getRenreMap(CastUtil.toInt(genreNmCd), false));
						
						
						String pDate=XmlUtil.getNodeContentStr(nl2, "program_planned_date");
						String pStartTime=XmlUtil.getNodeContentStr(nl2, "program_planned_start_time");
						String pEndTime=XmlUtil.getNodeContentStr(nl2, "program_planned_end_time");
						
						//2. 편성표 상의 추가된 프로그램 및 회별프로그램에 대해 상세 정보 조회 및 DB 업데이트					
						//(1) http://ppsx.kbs.co.kr/KBS_PPS_IF.asmx?op=PPS_GET_PROGRAM_INFORMATION_V4					
						// ① Request: 프로그램코드 및 방송일자(하루 전일)를 기준으로 상세 정보 조회					
						urlStr= programInfoUrl3+"?request_system_ID="+requestSystemId+
								"&request_system_password="+requestSystemPassword+"&user_ID="+userId+"&program_ID="+programId;
						
						logger.debug("urlStr22:["+urlStr+"]");
						expression = "//PROGRAM_HEADER";
						
						Document doc = XmlUtil.getDocument(urlStr);
						NodeList nl3 =XmlUtil.getChildNodeList(doc, expression);
						logger.debug("program_code:"+XmlUtil.getNodeContentStr(nl3, "program_code"));
						opVo.setWeekday(XmlUtil.getNodeContentStr(nl3, "onair_day"));
						
						expression = "//PROGRAM_ID_HEADER";
						NodeList nl4 =XmlUtil.getChildNodeList(doc, expression);
						
						
						logger.debug("onair_day:"+XmlUtil.getNodeContentStr(nl3, "onair_day"));
	//						expression = "//SEGMENT_ID";
	//						ArrayList<NodeList>nl5 =XmlUtil.getXmlChildNodeList(doc, expression);
						
						String idH=""; 
						if(channelCode[chCode].equals("12")) {
							idH="K02";
						}else {
							idH="K01";
						}
						
						logger.debug("idH:"+idH);
						
						ProgramVo pVo = new ProgramVo();
						pVo.setOProgramId(opVo.getOProgramId());
						pVo.setProgramId(idH+"_"+opVo.getOProgramId()); //
						pVo.setProgramNm(XmlUtil.getNodeContentStr(nl3, "program_title"));
						pVo.setProgramType(opVo.getProgramType());
						pVo.setCornerId("1");
						pVo.setChannelNm(opVo.getChannelNm());
						pVo.setBroadNm("KBS");
						pVo.setWeekday(opVo.getWeekday());
						String reviewYn=XmlUtil.getNodeContentStr(nl2, "rerun_classification");
						if(reviewYn.equals("재방")) {
							pVo.setReviewYn("Y");
						}
						pList.add(pVo);
						String program_ID_status = XmlUtil.getNodeContentStr(nl4, "program_ID_status");
						if(program_ID_status.equals("방송종료")) {
							logger.debug("program_title:"+XmlUtil.getNodeContentStr(nl3, "program_title"));
							ContentsVo cVo = new ContentsVo();
							cVo.setOProgramId(opVo.getOProgramId());
							cVo.setProgramId(pVo.getProgramId());
							cVo.setContentsId(idH+"_"+programId);
							cVo.setContentsNm(XmlUtil.getNodeContentStr(nl4, "program_subtitle"));
							cVo.setCornerId("1");
							cVo.setWeekday(opVo.getWeekday());
							cVo.setVodcnt(XmlUtil.getNodeContentStr(nl4, "program_sequence_number"));
							cVo.setContentsType("V"); //V:VOD,C:CLIP
							cVo.setBroadDate(FormatUtil.matchFormat(pDate,"####-##-##"));
							cVo.setBroadStdt(pDate+pStartTime.substring(0, 6));
							cVo.setBroadEddt(pDate+pEndTime.substring(0, 6));
							reviewYn=XmlUtil.getNodeContentStr(nl4, "program_ID_workflow_kind_name");
							if(reviewYn.equals("재방")) {
								cVo.setReviewYn("Y");
								
								sssbuf.append("contents:id"+cVo.getContentsId()+"|");
							}
							cList.add(cVo);
							
							
							logger.debug("program_subtitle:"+XmlUtil.getNodeContentStr(nl4, "program_subtitle"));
							logger.debug("program_subtitle:"+XmlUtil.getNodeContentStr(nl4, "program_subtitle"));
						}
							
						logger.debug("opVogetOProgramId:"+opVo.getOProgramId());
						if(mapper.selectOrgProgram(opVo)>0) {
	//							mapper.updateOrgProgram(opVo);
							totalUpdateCount++;
						}else {
							mapper.insertOrgProgram(opVo);
							totalInsertCount++;
						}
						
						for(int k=0;k<pList.size();k++) {
							if(mapper.selectProgram(pList.get(k))>0) {
//									mapper.updateProgram(pList.get(k));
								totalUpdateCount++;
							}else {
								mapper.insertProgram(pList.get(k));
								totalInsertCount++;
							}
						}
						
						for(int k=0;k<cList.size();k++) {
							if(mapper.selectContents(cList.get(k))>0) {
	//								mapper.updateContents(cList.get(k));
								totalUpdateCount++;
							}else {
								mapper.insertContents(cList.get(k));
								totalInsertCount++;
							}
						}
					
					}// end nl
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		logger.debug("재방==========>"+sssbuf.toString());
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
