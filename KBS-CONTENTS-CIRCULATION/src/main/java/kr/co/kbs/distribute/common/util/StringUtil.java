package kr.co.kbs.distribute.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *	
 * @Author : yhkim
 * @Date   : 2017. 4. 10.
 */
public class StringUtil {
	
	private static final Logger log = LoggerFactory.getLogger(Runnable.class.getName()); 
	/**
	 * <pre>
	 * 1.개요 : 
	 * 2. 처리내용 :
	 *    - Biz Logic 처리
	 *    	1)
	 *		2)
	 *		3)
	 *
	 *	</pre>
	 * @Method Name : replaceAll
	 * @작성일        : 2017. 4. 10.
	 * @변경이력 :     :
	 * @param strWord 
	 * @param strTargetWord
	 * @param strReplaceWord
	 * @return
	*/
	public static String replaceAll(String strWord, String strTargetWord, String strReplaceWord) {
		String str = strWord;
		if ((str != null) && (strWord.indexOf(strTargetWord) > -1)) {
			String[] strSplitAry = str.split(strTargetWord);
			for (int i = 0; i < strSplitAry.length; i++) {
				if (i == 0) {
					str = strSplitAry[0] + strReplaceWord;
				} else {
					str = str + strSplitAry[i] + strReplaceWord;
				}
			}
		}
		return str;
	}
	
	public static int DateCompare(String firstDate, String secondDate) throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyyMM");
		
		Date day1 = null;
		Date day2 = null;
		
		int result;
		
		try{
			day1 = format.parse(firstDate);
			day2 = format.parse(secondDate);
		} catch(Exception e){
			log.error("DateCompare ERROR : {}" , e);
			throw new Exception();
		}
		
		result = day1.compareTo(day2);
		
		return result;
	}
	
	public static String getRexStr(String orgStr, String findRex) throws Exception{
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
