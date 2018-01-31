package kr.co.kbs.distribute.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.common.util.CastUtil;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;
import kr.co.kbs.distribute.schedule.service.ScheduleDataService;


@Component("scheduleTempLGPPVBean")
//@Component
public class SampleExcelForLgPPVInsert {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(SampleExcelForLgPPVInsert.class);
	
	@Autowired 
	private ScheduleDataMapper mapper;
	
	@Autowired 
	private ScheduleDataService service;
	
//	public static void main(String[] args) throws IOException {
//		
//		SampleExcelForCVODInsert task = new SampleExcelForCVODInsert();
//		task.requestAliveSelect();
//	}
	
	@Autowired
	private Environment env;
	
//	@Scheduled(cron="20 36 09 * * *") 
	public void requestAliveSelect() {
		int totalCount = 0;
		int lProesult =0;
		int tapGubun= 0; //0:KBSPPM,1:MBCPPM,2:SBSPPM,3:KBSPPV
		
		String rootDir ="/usr/local/tomcat/apache-tomcat-8.5.23/";
//		String rootDir ="D:\\KBS\\excel\\LG\\";
//		String[] fileNameArray= {
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171002-171008/LG/KBS_PPM_20171015.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171002-171008/LG/KBS_PPV_20171015.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/LG/MBC_PPM_20171015.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/LG/SBS_PPM_20171015.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/LG/KBS_PPM_20171022.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/LG/KBS_PPV_20171022.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/KBS_PPM_20171029.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/KBS_PPV_20171029.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/KBS_PPM_20171105.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/KBS_PPV_20171105.xlsx"
//		};
		String[] fileNameArray= {
//				"LG_PPM_use.xlsx","LG_PPV_use.xlsx","LG_MBC_PPM_use.xlsx","LG_SBS_PPM_use.xlsx"
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171002-171008/LG/MBC_PPM_20171015.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171002-171008/LG/SBS_PPM_20171015.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171002-171008/LG/333.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171002-171008/LG/444.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/LG/KBS_PPM_20171015.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/LG/KBS_PPV_20171015.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/LG/MBC_PPM_20171015.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/LG/SBS_PPM_20171015.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/LG/333.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/LG/444.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/LG/KBS_PPM_20171022.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/LG/KBS_PPV_20171022.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/LG/MBC_PPM_20171022.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/LG/SBS_PPM_20171022.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/LG/333.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/LG/444.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/KBS_PPM_20171029.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/KBS_PPV_20171029.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/MBC_PPM_20171029.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/SBS_PPM_20171029.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/333.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/444.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/111.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/222.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/KBS_PPM_20171105.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/KBS_PPV_20171105.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/MBC_PPM_20171105.xlsx",
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/SBS_PPM_20171105.xlsx"
		};
		
		
//		String[] fileNameArray= {
////				
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710_SK/171016-171022/LG/KBS PPM 시청+통계_20171022.xlsx",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710_SK/171016-171022/LG/KBS PPV 시청통계_20171022.xlsx",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710_SK/171016-171022/LG/MBC PPM 시청+통계_20171022.xlsx",
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710_SK/171016-171022/LG/SBS PPM 시청+통계_20171022.xlsx"
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/LG/222.xlsx",KBS PPM 시청+통계_20171022
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/LG/333.xlsx",KBS PPV 시청통계_20171022_
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/LG/444.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/111.xlsx",MBC PPM 시청+통계_20171022
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/222.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/333.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/LG/444.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/111.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/222.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/333.xlsx",
////				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/LG/444.xlsx"
//		};
//		
		String[] channelNameArray= {
//				"LG_PPM_use.xlsx","LG_PPV_use.xlsx","LG_MBC_PPM_use.xlsx","LG_SBS_PPM_use.xlsx"
//				"SBS",
				"KBS",
				"KBS",
				"MBC",
				"SBS",
				"KBS",
				"KBS",
				"MBC",
				"SBS",
				"KBS",
				"KBS",
				"MBC",
				"SBS",
				"KBS",
				"KBS",
				"MBC",
				"SBS"
//				
		};
				
		tapGubun =CastUtil.toInt(env.getProperty("kbsapi.tapGubun")); 
		try {
			for(int fiCnt=0;fiCnt<fileNameArray.length;fiCnt++) {
				service.LGPPMTask(fileNameArray[fiCnt], channelNameArray[fiCnt]);
			}
		}catch(Exception ex) {
			System.out.println("error:"+ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	private static String getRexStr(String orgStr, String findRex) {
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
