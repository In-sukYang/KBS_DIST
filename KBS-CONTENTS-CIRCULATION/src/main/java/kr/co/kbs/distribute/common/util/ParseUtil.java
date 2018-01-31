package kr.co.kbs.distribute.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import net.sf.json.JSONException;

public class ParseUtil {

	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test = ParseUtil.getRexStr("09.06.27 [토]","[0-9]{2}.[0-9]{2}.[0-9]{2}|[0-9]{4}-[0-9]{2}-[0-9]{2}").trim();
		
		System.out.println("test:"+test);
	}
	*/
	/**
	 * 정규포현식을 통한 문자열 파싱 
	 * @param orgStr
	 * @param findRex
	 * @return
	 */
	public static String getRexStr(String orgStr, String findRex) {
		Pattern groupPattern = Pattern.compile(findRex); 
		Matcher groupMatcher = groupPattern.matcher(orgStr);
		String retStr ="";
		while(groupMatcher.find()) {
			retStr = groupMatcher.group();  // 정규표현식에 일치한 전체 문자열
			break;
		}
		return retStr;
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

	/**
	 * Reder를 통한 전체 문자열 정보 확인
	 * @param rd
	 * @return
	 * @throws IOException
	 */
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	/**
	 * URL을 통한 Data JSON으로 변환
	 * @param url
	 * @return
	 * @throws IOException
	 * @throws JSONException
	 * @throws ParseException
	 */
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
