package kr.co.kbs.distribute.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kr.co.kbs.distribute.common.vo.WeekDateVo;

public class DateUtil {
	
	public static List<WeekDateVo> getWeekInMonths(String year, String month) {
		Calendar cal = Calendar.getInstance();
		
		int intYear = Integer.parseInt(year);
		int intMonth = Integer.parseInt(month);
			
		cal.set(Calendar.YEAR, intYear);
		cal.set(Calendar.MONTH, intMonth -1);
		
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		List<WeekDateVo> list = new ArrayList<WeekDateVo>();
		
		for (int week = 1; week < cal.getMaximum(Calendar.WEEK_OF_MONTH); week++) {
			
			WeekDateVo vo = new WeekDateVo();
			
			cal.set(Calendar.WEEK_OF_MONTH, week);
			cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
			
			int startDay = cal.get(Calendar.DAY_OF_MONTH);

			cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			int endDay = cal.get(Calendar.DAY_OF_MONTH);
			
			if (week == 1 && startDay >= 7) {
				startDay = 1;
			}

			if (week == cal.getMaximum(Calendar.WEEK_OF_MONTH) - 1 && endDay <= 7) {
				endDay = lastDay;
			}
			
			vo.setWeek(week);
			vo.setStartDate(startDay);
			vo.setEndDate(endDay);
			list.add(vo);
			
			if (week == cal.getMaximum(Calendar.WEEK_OF_MONTH) - 1 && endDay >= 28 && endDay < lastDay) {
				endDay = lastDay;
				vo = new WeekDateVo();
				vo.setWeek(week+1);
				vo.setStartDate(endDay);
				vo.setEndDate(endDay);
				list.add(vo);
			}
			
		}
		return list;
	}
	
	
	/**
	 * 년 ,월을 입력받아 해당 월의 날짜를 리턴한다.<br>
	 * @param	year	YYYY 
	 * @param	month	7, 12
	 * @return 	int 해당 달의 날짜 리턴
	 */
	public static int getDaysOfMonth(int year, int month) { 
		int[] DOMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 
		int[] lDOMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31}; 	
		if ((year % 4) == 0) { 
			if ((year % 100) == 0 && (year % 400) != 0) {
				if(month>11){
					return DOMonth[0]; 
				}else{
					return DOMonth[month-1];
				}
			}
			if(month>11){
				return lDOMonth[0]; 
			}else{
				return lDOMonth[month-1];
			}
		} else {
			if(month>11){
				return DOMonth[0]; 
			}else{
				return DOMonth[month-1];
			}
		}
	} 			
	
	/**Calendar.get(year + 1900, month, date) 
	 * App서버의 현재년도월일을  리턴한다.
	 * @return 	App서버의 현재년도월일 
	 */
	public static Date getSysDate() { 
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}
	
	
	/**Calendar.get(year + 1900, month, date) 
	 * App서버의 현재년도월일을  리턴한다.
	 * @return 	App서버의 현재년도월일 
	 */
	public static int getSysYearDay() { return CastUtil.toInt(FormatUtil.matchFormat(new Date(), "yyyyMMdd")); }

	/**Calendar.get(year + 1900, month, date) 
	 * App서버의 현재년도월일을  리턴한다.
	 * @return 	App서버의 현재년도월일 
	 */
	public static int getSysYearMonth() { return CastUtil.toInt(FormatUtil.matchFormat(new Date(), "yyyyMM")); }

	/**
	 * App서버의 현재년도월일을  리턴한다.
	 * @return 	App서버의 현재년도월일 
	 */
	public static String getSysYearSecond() { return FormatUtil.matchFormat(new Date(), "yyyyMMddHHmmss"); }
	
	
	/**
	 * App서버의 현재년도를 리턴한다.
	 * @return 	App서버의 현재년도
	 */
	public static String getSysYear() { return CastUtil.toStr(Calendar.getInstance().get(Calendar.YEAR)); }
	
	/**
	 * App서버의 현재월을 리턴한다.
	 * @return 	App서버의 현재월(1 ~ 12)
	 */
	public static String getSysMonth() { return CastUtil.toStr(Calendar.getInstance().get(Calendar.MONTH) + 1); }
	
	/**
	 * App서버의 현재일을 리턴한다.
	 * @return 	App서버의 현재일(1 ~ 31)
	 */
	public static String getSysDay() { return CastUtil.toStr(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)); }
	
	/**
	 * App서버의 현재시를 리턴한다.
	 * @return 	App서버의 현재시(0 ~ 23)
	 */
	public static String getSysHour() { return CastUtil.toStr(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)); }
	
	/**
	 * App서버의 현재분을 리턴한다.
	 * @return 	App서버의 현재분(0 ~ 59)
	 */
	public static String getSysMinute() { return CastUtil.toStr(Calendar.getInstance().get(Calendar.MINUTE)); }
	
	/**
	 * App서버의 현재초을 리턴한다.
	 * @return 	App서버의 현재초(0 ~ 59)
	 */
	public static String getSysSecond() { return CastUtil.toStr(Calendar.getInstance().get(Calendar.SECOND)); }
	
	/**
	 * 년 또는 년월 또는 년월일 문자열을 입력받아 Date객체를 리턴한다.<br>
	 * 참고) 년의경우 월일은 1월1일로, 년월의경우 일은 1일로 설정된다.
	 * @param	date	"YYYY" 또는 "YYYYMM" 또는 "YYYYMMDD"
	 * @return 	Date 객체(시간은 0시0분0초), Date 객체로 변환할 수 없는 경우 null
	 */
	public static Date getDateMin(String date) {
		if (date==null || date.equals("") || !NumberUtil.isNumeric(date)) return null;
		int len = date.length();
		if (len<4 || len>8) return null;
		return getDate(date.substring(0, 4), len>=6? date.substring(4, 6):"1", len>=8? date.substring(6, 8):"1", "0", "0", "0");
	}
	
	
	
	/**
	 * 년월일을 입력받아 0시0분0초의 Date객체를 리턴한다.
	 * @param	year	년
	 * @param	month	월("1" ~ "12")
	 * @param	day		일("1" ~ "31")
	 * @return 	Date 객체(시간은 0시0분0초), 년월일 중 값이 없는 경우 null
	 */
	public static Date getDateMin(String year, String month, String day) {
		return getDate(year, month, day, "0", "0", "0");
	}
	
	/**
	 * 년월일을 입력받아 0시0분0초의 Date객체를 리턴한다.
	 * @param	year	년
	 * @param	month	월(1 ~ 12)
	 * @param	day		일(1 ~ 31)
	 * @return 	Date 객체(시간은 0시0분0초)
	 */
	public static Date getDateMin(int year, int month, int day) {
		return getDate(year, month, day, 0, 0, 0);
	}
	
	/**
	 * 년 또는 년월 또는 년월일 문자열을 입력받아 Date객체를 리턴한다.<br>
	 * 참고) 년의경우 월일은 1월1일로, 년월의경우 일은 1일로 설정된다.
	 * @param	date	"YYYY" 또는 "YYYYMM" 또는 "YYYYMMDD"
	 * @return 	Date 객체(시간은 23시59분59초), Date 객체로 변환할 수 없는 경우 null
	 */
	public static Date getDateMax(String date) {
		if (date==null || date.equals("") || !NumberUtil.isNumeric(date)) return null;
		int len = date.length();
		if (len<4 || len>8) return null;
		return getDate(date.substring(0, 4), len>=6? date.substring(4, 6):"1", len>=8? date.substring(6, 8):"1", "23", "59", "59");
	}
	
	/**
	 * 년월일을 입력받아 23시59분59초의 Date객체를 리턴한다.
	 * @param	year	년
	 * @param	month	월("1" ~ "12")
	 * @param	day		일("1" ~ "31")
	 * @return 	Date 객체(시간은 23시59분59초), 년월일 중 값이 없는 경우 null
	 */
	public static Date getDateMax(String year, String month, String day) {
		return getDate(year, month, day, "23", "59", "59");
	}
	
	/**
	 * 년월일을 입력받아 23시59분59초의 Date객체를 리턴한다.
	 * @param	year	년
	 * @param	month	월(1 ~ 12)
	 * @param	day		일(1 ~ 31)
	 * @return 	Date 객체(시간은 23시59분59초)
	 */
	public static Date getDateMax(int year, int month, int day) {
		return getDate(year, month, day, 23, 59, 59);
	}
	
	/**
	 * 년월일시분초를 입력받아 Date객체를 리턴한다.
	 * @param	year	년
	 * @param	month	월("1" ~ "12")
	 * @param	day		일("1" ~ "31")
	 * @param	hour	시("0" ~ "23")
	 * @param	minute	분("0" ~ "59")
	 * @param	second	초("0" ~ "59")
	 * @return 	Date 객체, 년월일시분초 중 값이 없는 경우 null
	 */
	public static Date getDate(String year, String month, String day, String hour, String minute, String second) {
		if (year.equals("") || month.equals("") || day.equals("") || hour.equals("") || minute.equals("") || second.equals("")) return null;
		return getDate(CastUtil.toInt(year), CastUtil.toInt(month), CastUtil.toInt(day), CastUtil.toInt(hour), CastUtil.toInt(minute), CastUtil.toInt(second));
	}
	
	/**
	 * 년월일시분초를 입력받아 Date객체를 리턴한다.
	 * @param	year	년
	 * @param	month	월(1 ~ 12)
	 * @param	day		일(1 ~ 31)
	 * @param	hour	시(0 ~ 23)
	 * @param	minute	분(0 ~ 59)
	 * @param	second	초(0 ~ 59)
	 * @return 	Date 객체
	 */
	public static Date getDate(int year, int month, int day, int hour, int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month-1, day, hour, minute, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	
	
	public static String getDateToString(Date dt){
		return FormatUtil.matchFormat(dt, "yyyyMMddHHmmss");
	}
	/**
	 * <pre>
	 * 년월일시분초 스트링을 입력받아 Date객체를 리턴한다.
	 * </pre>
	 * @param	datetime yyyyMMddHHmmss
	 * @return 	Date 객체, 값이 없는 경우 null
	 */
	public static Date getDate(String datetime) throws Exception {
		String year, month, day, hour, minute, second;
		
		if (datetime==null || 
		    "".equals(datetime) || 
		    datetime.length() != 14) return null;
		
		year = datetime.substring(0, 4);
		month = datetime.substring(4, 6);
		day = datetime.substring(6, 8);
		hour = datetime.substring(8, 10);
		minute = datetime.substring(10, 12);
		second = datetime.substring(12, 14);		
		
		return getDate(CastUtil.toInt(year), CastUtil.toInt(month), CastUtil.toInt(day), CastUtil.toInt(hour), CastUtil.toInt(minute), CastUtil.toInt(second));
	}
	
	/**
	 * <pre>
	 * 두 일시의 차를 시간차를 구한다.
	 * </pre>
	 * @param d1
	 * @param d2
	 * @return 두 일시의 차를 시간차
	 */
	public static int getTimeInterval(Date d1, Date d2) throws Exception {
	    int interval = 0;

	    interval = (int)((d1.getTime() - d2.getTime()) / (60 * 60 * 1000)); 
	    
	    return interval;
	    
	}
	
	
	/**
	 * <pre>
	 * Date 객체에 일정기간을 증감하여 리턴한다.
	 * </pre>
	 * @param	date	원래 Date 객체
	 * @param	ymd		증감필드(1:년, 2:월, 3:일, 4:시, 5:분)
	 * @param	amt		증감기간(양수:미래로~, 음수:과거로~)
	 * @return 	증감된 Date 객체
	 */
	public static Date getDateAdd(Date date, int ymd, int amt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(ymd==1? Calendar.YEAR:ymd==2? Calendar.MONTH:ymd==3? Calendar.DAY_OF_MONTH:ymd==4? Calendar.HOUR:Calendar.MINUTE, amt);
		return cal.getTime();
	}
	
	
	/**
	 * <pre>
	 * Date 객체에 일정기간을 증감하여 리턴한다.
	 * </pre>
	 * @param	date	원래 Date 객체
	 * @param	ymd		증감필드(1:년, 2:월, 3:일, 4:시, 5:분)
	 * @param	amt		증감기간(양수:미래로~, 음수:과거로~)
	 * @return 	증감된 Date 객체
	 */
	public static String getDateAdd(Date date, int ymd, int amt, String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(ymd==1? Calendar.YEAR:ymd==2? Calendar.MONTH:ymd==3? Calendar.DAY_OF_MONTH:ymd==4? Calendar.HOUR:Calendar.MINUTE, amt);
		return FormatUtil.matchFormat(cal.getTime(),format);
	}
	
	/**
	 * <pre>
	 * Date 객체에 일정기간을 증감하여 리턴한다.
	 * </pre>
	 * @param	date	원래 Date 객체
	 * @param	ymd		증감필드(1:년, 2:월, 3:일, 4:시, 5:분)
	 * @param	amt		증감기간(양수:미래로~, 음수:과거로~)
	 * @return 	증감된 Date 객체
	 * @throws Exception 
	 */
	public static String getDateAdd(String date, int ymd, int amt, String format) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDateMin(date));
		cal.add(ymd==1? Calendar.YEAR:ymd==2? Calendar.MONTH:ymd==3? Calendar.DAY_OF_MONTH:ymd==4? Calendar.HOUR:Calendar.MINUTE, amt);
		return FormatUtil.matchFormat(cal.getTime(),format);
	}
	
	
	/**
	 * <pre>
	 * Date 객체에 일정기간을 증감하여 리턴한다.
	 * </pre>
	 * @param	date	원래 Date 객체
	 * @param	ymd		증감필드(1:년, 2:월, 3:일, 4:시, 5:분)
	 * @param	amt		증감기간(양수:미래로~, 음수:과거로~)
	 * @return 	증감된 Date 객체
	 * @throws Exception 
	 */
	public static String getDateAdd2(String date, int ymd, int amt, String format) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getDateMin(date));
		cal.add(ymd==1? Calendar.YEAR:ymd==2? Calendar.MONTH:ymd==3? Calendar.DAY_OF_MONTH:ymd==4? Calendar.HOUR:Calendar.MINUTE, amt);
		return FormatUtil.matchFormat(cal.getTime(),format);
	}
	
	/**
	 * <pre>
	 * Date 객체에 일정기간을 증감하여 리턴한다.
	 * </pre>
	 * @param	date	원래 Date 객체
	 * @param	ymd		증감필드(1:년, 2:월, 3:일, 4:시, 5:분)
	 * @param	amt		증감기간(양수:미래로~, 음수:과거로~)
	 * @return 	증감된 Date 객체
	 * @throws Exception 
	 */
	public static String getDateAdd(int ymd, int amt, String format) throws Exception {
		Calendar cal = Calendar.getInstance();
		cal.add(ymd==1? Calendar.YEAR:ymd==2? Calendar.MONTH:ymd==3? Calendar.DAY_OF_MONTH:ymd==4? Calendar.HOUR:Calendar.MINUTE, amt);
		return FormatUtil.matchFormat(cal.getTime(),format);
	}
	
	
	/**
	 * <pre>
	 * 현재날짜에 대한 요일명을 얻는다.
	 * </pre>
	 * @param date 예:20040528
	 * @return 요일명
	 */
	public static String getDayOfWeek(String date) {
	    String result = "";
	    
	    if(date == null || date.length() < 8) {
	        return result;
	    }
	    
	    Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.getDateMin(date.substring(0, 8)));
	   
        switch(c.get(Calendar.DAY_OF_WEEK)) {
        	case Calendar.SUNDAY : 
        	    result = "일";
        	    break;
        	case Calendar.MONDAY :
        	    result = "월";        	    
        	    break;
        	case Calendar.TUESDAY :
        	    result = "화";        	    
        	    break;
        	case Calendar.WEDNESDAY :
        	    result = "수";        	    
        	    break;        	
        	case Calendar.THURSDAY :
        	    result = "목";        	    
        	    break;
        	case Calendar.FRIDAY :
        	    result = "금";        	    
        	    break;
        	case Calendar.SATURDAY :
        	    result = "토";        	    
        	    break;    	
        	
        }
        
        return result;
	}
	
	
	/**
	 * <pre>
	 * 현재날짜에 대한 요일명을 얻는다.
	 * </pre>
	 * @param date 예:20040528
	 * @return 요일명
	 */
	public static String getDayOfWeekSet(String date,int day) {
	    String result = "";
	    
	    if(date == null || date.length() < 8) {
	        return result;
	    }
	    SimpleDateFormat fomat = new SimpleDateFormat("yyyyMMdd"); 
	    Calendar c = Calendar.getInstance();
        c.setTime(DateUtil.getDateMin(date.substring(0, 8)));
        switch(day) {
	    	case Calendar.SUNDAY : 
	    		c.set(c.DAY_OF_WEEK, c.SUNDAY);
	    	    break;
	    	case Calendar.MONDAY :
	    		c.set(c.DAY_OF_WEEK, c.MONDAY);	    
	    	    break;
	    	case Calendar.TUESDAY :
	    		c.set(c.DAY_OF_WEEK, c.TUESDAY);
	    	    break;
	    	case Calendar.WEDNESDAY :
	    		c.set(c.DAY_OF_WEEK, c.WEDNESDAY);  	    
	    	    break;        	
	    	case Calendar.THURSDAY :
	    		c.set(c.DAY_OF_WEEK, c.THURSDAY);	    
	    	    break;
	    	case Calendar.FRIDAY :
	    		c.set(c.DAY_OF_WEEK, c.FRIDAY);   
	    	    break;
	    	case Calendar.SATURDAY :
	    		c.set(c.DAY_OF_WEEK, c.SATURDAY); 	    
	    	    break;
	    }
        result =fomat.format(c.getTime());
        return result;
	}

}
