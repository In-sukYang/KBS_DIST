/*
 * Created on 2004. 6. 29.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package kr.co.kbs.distribute.common.util;

import java.text.DecimalFormat;
import java.text.ParseException;
/**
 * 
 *  
 * @author ${Author}$
 * @version ${Date}$ ${Revision}$
 */
public class NumberUtil {
	/**
	 * <pre>
	 * 문자열이 숫자로만 되어있는지 체크한다.
	 * 용례) isNumeric("99999") -> true, isNumeric("99,999") -> false
	 * </pre> 
	 * @param	str			문자열
	 * @return 	문자열이 모두 숫자로만 이루어진경우 true
	 */
	public static boolean isNumeric(String str) {
		try{ 
			Integer.parseInt(str);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
	
	/**
	 * <pre>
	 * 문자열이 통화형태(comma/period 포함)로 되어있는지 체크한다.
	 * 용례) isNumeric("99999") -> true, isNumeric("99,999.99") -> true
	 * </pre>
	 * @param	str			문자열
	 * @return 	문자열이 모두 숫자와 comma, period로만 이루어진경우 true
	 */
	public static boolean isCurrency(String str) {
		try{
			new DecimalFormat().parse(str.trim());
		} catch (ParseException ex) {
			return false;
		}
		return true;
	}
	
	/**
	 * <pre>
	 * 통화량 형태의 문자열을 정수로 변환한다.
	 * 주의) 정수범위를 넘어가거나 소수이하 데이타손실
	 * 용례) curToInt("99,999.99") -> 99999
	 * </pre>
	 * @param	arg			통화량형태 문자열
	 * @return 	변환된 정수, 문자열이 불량일경우 -1
	 */
	public static int curToInt(String arg) {
		try {
			return new DecimalFormat().parse(arg.trim()).intValue();
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * <pre>
	 * 통화량 형태의 문자열을 long으로 변환 (데이타손실 가능)
	 * 주의) long범위를 넘어가거나 소수이하 데이타손실
	 * 용례) curToInt("99,999.99") -> 99999
	 * </pre>
	 * @param	arg			통화량형태 문자열
	 * @return 	변환된 long, 문자열이 불량일경우 -1
	 */
	public static long curToLong(String arg) {
		try {
			return new DecimalFormat().parse(arg.trim()).longValue();
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return -1;
	}
}
