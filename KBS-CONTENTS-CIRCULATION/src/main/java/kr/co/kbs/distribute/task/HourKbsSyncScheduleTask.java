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

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;

@Component("scheduleKbsHourCheckBean")
public class HourKbsSyncScheduleTask {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(HourKbsSyncScheduleTask.class);
	
	@Autowired 
	private ScheduleDataMapper mapper;
	
	
//	@Autowired
//	private Environment env;
	
	
//	@Scheduled(cron="10 44 17 * * *") 
	public void requestAliveSelect() {
		int totalInsertCount = 0;
		int totalUpdateCount = 0;
		int totalInsertCodeCount = 0;
		
		
		try {
			//m_view_rating ==> d_view_rating 처리
			mapper.excuteDViewRating();
			
			//d_view_rating p_seq 가 null 일경우 -1로 업데이트
			mapper.excuteClipPSETNotNullSet();
			
			//d_view_rating pc_seq 가 null 일경우 -1로 업데이트
			mapper.excuteClipPCSETNotNullSet();
			
			mapper.excuteDcViewRatingUpdate();
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
