package kr.co.kbs.distribute.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtil {




	//	public static NodeList getXmlNodeList(String URL, String expression) throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
	//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	//		DocumentBuilder builder = factory.newDocumentBuilder();
	////		Document doc = builder.parse("D://program_list.xml");
	////		Document doc = builder.parse("http://ippsx.kbs.co.kr/KBS_PPS_IF.asmx/PPS_GET_PROGRAM_LIST_V2?request_system_ID=TEST_31075&request_system_password=31075&user_ID=31075&media_code=01&programming_local_station_code=00&program_classification_code= &UpdateDateStart=20170101&UpdateDateEnd=20170201");
	//		Document doc = builder.parse(URL);
	//		XPathFactory xPathfactory = XPathFactory.newInstance();
	//		XPath xpath = xPathfactory.newXPath();
	//
	//
	//		// NodeList 가져오기 : row 아래에 있는 모든 col1 을 선택
	//		
	//		////PROGRAM_LIST_INFO/program_code : 프로그램코드
	//		////PROGRAM_LIST_INFO/program_title : 프로그램명
	//		////PROGRAM_LIST_INFO/program_genre : 장르명
	//		////PROGRAM_LIST_INFO/program_genre_code:장르코드
	////		NodeList nodeList = (NodeList)xpath.evaluate("//PROGRAM_LIST_INFO/program_genre", doc, XPathConstants.NODESET);
	//		NodeList nodeList = (NodeList)xpath.evaluate(expression, doc, XPathConstants.NODESET);
	//		
	//		return nodeList;
	//	}

	/**
	 * URL을 통한 NodeList 정보를 가져오는 함수
	 * @param URL
	 * @param expression
	 * @return
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static NodeList getXmlNodeList(String URL, String expression) {
		NodeList nodeList =null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(URL);
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			nodeList = (NodeList)xpath.evaluate(expression, doc, XPathConstants.NODESET);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return nodeList;
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
	 * URL정보를 통하여 표현식의 Child NodeList를 가져오는 함수
	 * @param URL
	 * @param expression
	 * @return
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static ArrayList<NodeList> getXmlChildNodeList(Document doc, String expression) {
		ArrayList<NodeList> al = new ArrayList<NodeList>();
		try {
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
	 * URL정보를 통하여 표현식의 Child NodeList를 가져오는 함수
	 * @param URL
	 * @param expression
	 * @return
	 * @throws ParserConfigurationException
	 * @throws XPathExpressionException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static NodeList getChildNodeList(Document doc, String expression) {
		NodeList subList  = null;
		try {
			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			NodeList nodeList = (NodeList)xpath.evaluate(expression, doc, XPathConstants.NODESET);
			subList = nodeList.item(0).getChildNodes();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return subList;
	}
	
	/**
	 * URL정보를 통하여 Document를 가져오는 Function
	 * @param URL
	 * @return
	 */
	public static Document getDocument(String URL) {
		Document doc  = null;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.parse(URL);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return doc;
	}


	/**
	 * NodeList 에서 특정 NodeName을 갖는 Array 리턴
	 * @param nl
	 * @param nodeName
	 * @return
	 */
	public static ArrayList<String> getNodeContents(NodeList nl , String nodeName) {
		ArrayList<String> al =new ArrayList<String>();
		try {
			if(nl!=null) {
				for(int i=0;i<nl.getLength();i++) {
					if(nl.item(i).getNodeName().equals(nodeName)){
						al.add(nl.item(i).getTextContent());
					}
				}
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
		return returnStr.trim();
	}


	/**
	 * xml 파읽을 읽어들여 document 를 생성한다.
	 * @param filePath 파일 경로
	 * @param fileName 파일명
	 * @return
	 * @throws Exception
	 */
	public static Document parse(String filePath, String fileName) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
		DocumentBuilder builder = factory.newDocumentBuilder();
		factory.setNamespaceAware( true );
		factory.setValidating( true );
		Document doc = builder.parse(new File(filePath+fileName));
		return doc;
	}
	/**
	 * 소스의 Element로 부터 key에 해당하는 자식node를 Element로 형전환하여 반환한다.
	 * @param srcElement 조회를 원하는 element
	 * @param key 자식 로드 element
	 * @return
	 */
	public static Element getElement(Element srcElement, String key){
		Element retEle = null;
		NodeList ddNodeList = srcElement.getChildNodes();
		for(int i = 0 ; i < ddNodeList.getLength() ; i++){
			Node node =srcElement.getChildNodes().item(i);
			String nodeName = node.getNodeName();
			if(key.equals(nodeName)){
				retEle = (Element)node;
				break;
			}
		}
		return retEle;
	}

	/**
	 * Element를 추가한다.
	 * 
	 * @param srcElement 추가를 원하는 소스의 element
	 * @param document //document 
	 * @param elementName //추가를 원하는 element 이름
	 * @param removeFlag //기존에 elementName 유무에 따라 Y이면 삭제, N이면 element 계속 추가.
	 */
	public static void addElement(Element srcElement, Document document, String elementName, 
			boolean removeFlag){

		if(removeFlag){
			//삭제할 element를 찾는다.
			//조회할 element에 element가 없다면 null을 리턴
			Element removeElement = getElement(srcElement,elementName);
			if(removeElement!=null){
				removeElement(srcElement,removeElement); 
			}
		}
		Element addElement = document.createElement(elementName);
		srcElement.appendChild(addElement);
		//
	}

	/**
	 * parentsElements 에 elementName으로 element를 추가 후
	 * key와 value를 이용하여 attribute를 추가한다.
	 * removeFlag를 이용하여 Y이면 기존에 elementName 있으면 해당 element를 삭제한다.
	 * @param parentsElements
	 * @param document
	 * @param elementName
	 * @param key
	 * @param value
	 * @param removeFlag
	 */
	public static void addElementAttribute(Element parentsElements, Document document, String elementName, 
			String key, String value, boolean removeFlag){
		addElement(parentsElements,document,elementName,removeFlag);
		addAttribute(getElement(parentsElements,elementName),key, value);
	}
	/**
	 * 소스의 요소중 key값에 해당하는 요소가 있는지 조회
	 * @param srcElement
	 * @param key
	 * @return
	 */
	public static boolean isChildElement(Element srcElement, String key){
		boolean retFlag = false;
		NodeList ddNodeList = srcElement.getChildNodes();
		for(int i = 0 ; i < ddNodeList.getLength() ; i++){
			Node node =srcElement.getChildNodes().item(i);
			String nodeName = node.getNodeName();
			if(key.equals(nodeName)){
				retFlag = true;
				break;
			}
		}
		return retFlag;
	}
	/**
	 * 
	 * @param srcElement
	 * @param removeElement
	 */
	public static void removeElement(Element srcElement, Element removeElement){
		if(isChildElement(srcElement,removeElement.getTagName()))
			srcElement.removeChild(removeElement); 
	}
	public static void addAttribute(Element srcElement, String key, String value){
		srcElement.setAttribute(key, value);
	} 
	public static void makeFile(Document document, String fileName, String encoding, String docTypeSystem) throws Exception{
		//..변환기 생성
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		//..출력 포맷 설정
		transformer.setOutputProperty(OutputKeys.ENCODING, encoding);
		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, docTypeSystem);
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		//..DOMSource 객체 생성
		DOMSource source = new DOMSource(document);
		//..StreamResult 객체 생성
		StreamResult result = new StreamResult(new File(fileName));
		//..파일로 저장하기
		transformer.transform(source, result); 
	}

}
