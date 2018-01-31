/*
 * Created on 2004. 6. 29.
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package kr.co.kbs.distribute.common.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 
 * @author ${Author}$
 * @version ${Date}$ ${Revision}$
 */ 
public class FormatUtil {
	/**
	 * <pre>
	 * 문자열을 주어진 포맷으로 일치시킨다.
	 * 용례) matchFormat("20010101", "####/##/##") -> "2001/01/01"
	 *       matchFormat("12345678", "##/## : ##") -> "12/34 : 56"
	 * </pre>
	 * @param	str			원본문자열
	 * @param	format		결과 포맷형태('#'문자에 원본 문자가 위치, 그외 문자는 그대로 표시)
	 * @return 	포맷으로 변환된 문자열
	 */ 
	public static String matchFormat(String str, String format) {
		if(str == null || str.length() == 0 ) return str;
		int len = format.length(); 
		char[] result = new char[len];
		for(int i=0,j=0; i<len; i++,j++) {
			if(format.charAt(i)=='#') {
				try {
					result[i]= str.charAt(j);
				}catch(StringIndexOutOfBoundsException e) {
					result[i]= '\u0000';
				}
			} else {
				result[i]= format.charAt(i);
				j--;
			}
		}
		return new String(result);
	}
	
	/**
	 * <pre>
	 * 포맷형태의 문자열에서 원본문자열을 뽑아낸다.
	 * 용례) releaseFormat("2001/01/01", "####/##/##") -> "20010101"
	 *       releaseFormat("123/456", "###/#########") -> "123456"
	 * </pre>
	 * @param	str			포맷형태의 문자열
	 * @param	format		원본문자열을 뽑아낼 포맷형태('#'문자 위치에 있는 원본 문자만 뽑아옴)
	 * @return 	원본문자열
	 */
	public static String releaseFormat(String str, String format) {
		if(str == null || str.length() == 0 ) return str;
		int len = format.length();
		char[] result = new char[len];
		for(int i=0,j=0; i<len; i++,j++) {
			if(format.charAt(i)=='#') {
				try {
					result[j] = str.charAt(i);
				}catch(StringIndexOutOfBoundsException e) {
					result[j]= '\u0000';
				}
			} else {
				j--;
			}
		}
		return (new String(result)).trim();
	}

	/**
	 * <pre>
	 * Date형을 주어진 날짜 포맷으로 변환한다.
	 * 용례) matchFormat(new Date(), "yyyy/MM/dd a hh:mm:ss") -> "2001/09/18 오후 03:31:32"
	 * </pre>
	 * @param	dt			Date 객체
	 * @param	format		변환 날짜포맷 (포맷형식은 Java API SimpleDateFormat 객체 참조)
	 * @return 	변환된 문자열, 포맷이 불량이면 "invalid format"
	 */
	public static String matchFormat(Date dt, String format) {
		if (dt == null) return "";
		try {
			return new SimpleDateFormat(format).format(dt);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
 	}
 	
	/**
	 * <pre>
	 * String형을 통화량형태의 문자열로 변환한다.
	 * </pre>
	 * @param	arg			String 또는 double
	 * @return 	통화형태로 변화된 문자열
	 * @throws	NumberFormatException	문자열이 불량일때(숫자,period,E 이외의 문자)
	 */
 	public static String toCurFormat(String arg) { return toCurFormat(CastUtil.toDbl(arg)); }
	/**
	 * <pre>
	 * double형을 통화량형태의 문자열로 변환한다.
	 * 주의) 소수이하 반올림 됨
	 * </pre>
	 * @param	arg			String 또는 double
	 * @return 	통화형태로 변화된 문자열
	 */
	public static String toCurFormat(double arg) { return new DecimalFormat("###,##0").format(arg); }
	
	/**
	 * <pre>
	 * String형을 소수점 이하 "0.00" 형태의 문자열로 변환한다.
	 * 용례) toPointFormat("35000", 1) -> "35000.1"
	 *      toPointFormat("35000", 234) -> "35000.234"
	 * </pre>
	 * @param	arg			String
	 * @param	pointDigit	소수점 이하 자리수
	 * @return 	소수점 이하 형태로 변화된 문자열
	 **/
	public static String toPointFormat(String arg, int pointDigit) { return toPointFormat(CastUtil.toDbl(arg), pointDigit); }
	
	/**
	 * <pre>
	 * double형을 소수점 이하 "0.00" 형태의  문자열로 변환한다.
	 * 용례) toPointFormat("35000.0", 123) -> "35000.123"
	 *      toPointFormat("35000.0", 432) -> "35000.432"* </pre>
	 * @param	arg			double
	 * @param	pointDigit	소수점 이하 자리수
	 * @return 	소수점 이하 형태로 변화된 문자열
	 **/
	public static String toPointFormat(double arg, int pointDigit) {
		if (pointDigit<=0) {
			return new DecimalFormat("##0").format(arg);
		} else {
			String formatStr = "##0.";
			//for (int i=0; i<pointDigit; i++) formatStr += "0";
			formatStr +=pointDigit;
			return new DecimalFormat(formatStr).format(arg);
		}
	}
	
	/**
	 * <pre>
	 * 문자열을 Date형으로 변환한다.
	 * </pre>
	 * @param	str			"YYYYMMDD", "YYYY/MM/DD", "YYYY-MM-DD" 형태의 문자열
	 * @return 	변환된 Date형, 불량 문자열이면 null 리턴
	 */
	public static Date toDate(String str) {
		if(str == null || str.length() == 0 ) return null;
		String format = (str.length()==8)? "yyyyMMdd":(str.indexOf("-")>=0)? "yyyy-MM-dd":"yyyy/MM/dd";
		try {
			return new SimpleDateFormat(format).parse(str);
		} catch (ParseException ex) { return null; }
	}

}
