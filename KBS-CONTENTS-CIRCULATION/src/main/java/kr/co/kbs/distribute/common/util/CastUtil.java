/*
 * Created on 2004. 6. 29.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package kr.co.kbs.distribute.common.util;

import java.math.BigDecimal;

/**
 * 
 * @author ${Author}$
 * @version ${Date}$ ${Revision}$
 */
public class CastUtil {
	/**
	 * <pre>
	 * int형을 문자열로 변환한다.
	 * 참고) byte,short형은 int로 자동형변환되어 실행된다.
	 * 활용 예) CastUtil.toStr(111); //->111 
	 * </pre>
	 * @param	arg			int값
	 * @return 	변화된 문자열
	 */   
	public static String toStr(int arg) {return Integer.toString(arg); }
	/** 
	 * <pre>
	 * long형을 문자열로 변환한다.
	 * </pre>
	 * @param	arg			long값
	 * @return 	변화된 문자열
	 */
	public static String toStr(long arg) { return Long.toString(arg); }
	/**
	 * <pre>
	 * double형을 문자열로 변환한다.
	 * 참고) float형은 double로 자동형변환되어 실행된다.
	 * 주의) double형의 경우 scale이 크면 'E'표시형식으로 나타남
	 * </pre>
	 * @param	arg			double값
	 * @return 	변화된 문자열
	 */
	public static String toStr(double arg) { return Double.toString(arg); }
	
	/**
	 * <pre>
	 * long형을 정수로 변환한다.
	 * 주의) 정수범위 초과시 데이타손실
	 * </pre>
	 * @param	arg			long값
	 * @return 	변환된 정수
	 */
	public static int toInt(long arg) { return new Long(arg).intValue(); }
	/**
	 * <pre>
	 * double형을 정수로 변환한다.
	 * 참고) float는 double로 자동형변환되어 실행된다.
	 * 주의) 정수범위 초과시 또는 소수이하의 값 데이타손실
	 * </pre>
	 * @param	arg			double값
	 * @return 	변환된 정수
	 */
	public static int toInt(double arg) { return new Double(arg).intValue(); }
	/**
	 * <pre>
	 * String형을 정수로 변환한다.
	 * </pre>
	 * @param	arg			숫자형태의 문자열
	 * @return 	변환된 정수
	 * @throws	NumberFormatException	문자열이 정수범위를 초과하거나 숫자 이외의 문자가 있을때
	 */
	public static int toInt(String arg) { 
		int returnValue=0;
		try{
			if(arg.indexOf(".")>-1) arg=arg.substring(0, arg.indexOf("."));
			returnValue=new Integer(arg.trim()).intValue();
		}catch(Exception ex){System.out.println("ex:"+ex);}
		return returnValue; 
	}
	/*
	public static void main(String arg[]) {
	    System.out.println();
	}
	*/
	/**
	 * <pre>
	 * Object형을 정수로 변환한다.
	 * 참고) DB의 NUMBER 필드를 쿼리한 결과는 array에 Double형으로 저장되어 있다.
	 *       array에서 뽑아낸 Object형을 이 메소드를 통하여 정수형으로 변환할때 사용한다.
	 * 주의) Object는 Double형이어야 한다.
	 * </pre>
	 * @param	arg			Double형의 Object
	 * @return 	변환된 정수
	 * @throws	ClassCastException	Object가 Double형이 아닌경우
	 */
	public static int toInt(Object arg) { return ((Double)arg).intValue(); }
	
	public static int toInt(BigDecimal arg){ return arg.intValue(); }
	
	/**
	 * <pre>
	 * String형을 double형으로 변환한다.
	 * </pre>
	 * @param	arg			문자열
	 * @return 	변환된 double
	 * @throws	NumberFormatException	문자열이 불량일때(숫자,period,E 이외의 문자)
	 */
	public static double toDbl(String arg) { return new Double(arg.trim()).doubleValue(); }
	
	
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
	
	public static String StringReplace(String str){       
	      String match = "[^\uAC00-\uD7A3xfe0-9a-zA-Z\\s]";
	      str =str.replaceAll(match, " ");
	      return str;
   }
	
	
	/**
	 * 타입구분
	 * @param typeCd
	 * @param nameCheck
	 * @return
	 */
	public static String getRenreMap(int typeCd, boolean nameCheck) {
		String returnVal="";
		switch (typeCd) {
		case 101:
			if(nameCheck) {
				returnVal = "뉴스";
			}else {
				returnVal = "04";
			}
			break;
		case 102:
			if(nameCheck) {
				returnVal = "뉴스";
			}else {
				returnVal = "04";
			}
			break;
		case 103:
			if(nameCheck) {
				returnVal = "뉴스";
			}else {
				returnVal = "04";
			}
			break;
		case 110:
			if(nameCheck) {
				returnVal = "교양";
			}else {
				returnVal = "03";
			}
			break;
		case 121:
			if(nameCheck) {
				returnVal = "교양";
			}else {
				returnVal = "03";
			}
			break;
		case 201:
			if(nameCheck) {
				returnVal = "스포츠";
			}else {
				returnVal = "05";
			}
			break;
		case 203:
			if(nameCheck) {
				returnVal = "스포츠";
			}else {
				returnVal = "05";
			}
			break;
		case 221:
			if(nameCheck) {
				returnVal = "스포츠";
			}else {
				returnVal = "05";
			}
			break;
		case 321:
			if(nameCheck) {
				returnVal = "교양";
			}else {
				returnVal = "03";
			}
			break;
		case 322:
			if(nameCheck) {
				returnVal = "어린이";
			}else {
				returnVal = "06";
			}
			break;
		case 421:
			if(nameCheck) {
				returnVal = "예능";
			}else {
				returnVal = "02";
			}
			break;
		case 422:
			if(nameCheck) {
				returnVal = "예능";
			}else {
				returnVal = "02";
			}
			break;
		case 521:
			if(nameCheck) {
				returnVal = "교양";
			}else {
				returnVal = "03";
			}
			break;
		case 621:
			if(nameCheck) {
				returnVal = "드라마";
			}else {
				returnVal = "01";
			}
			break;
		case 721:
			if(nameCheck) {
				returnVal = "기타";
			}else {
				returnVal = "99";
			}
			break;
		case 722:
			if(nameCheck) {
				returnVal = "기타";
			}else {
				returnVal = "99";
			}
			break;
		case 821:
			if(nameCheck) {
				returnVal = "어린이";
			}else {
				returnVal = "06";
			}
			break;
			
		default:
			if(nameCheck) {
				returnVal = "기타";
			}else {
				returnVal = "99";
			}
			break;
		}
		
		return returnVal;
	}

}
