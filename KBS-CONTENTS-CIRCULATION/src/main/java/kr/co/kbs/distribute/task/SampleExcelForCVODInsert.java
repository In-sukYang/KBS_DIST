package kr.co.kbs.distribute.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;
import kr.co.kbs.distribute.schedule.service.ScheduleDataService;


@Component("scheduleTempCVODBean")
//@Component
public class SampleExcelForCVODInsert {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(SampleExcelForCVODInsert.class);

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

//		@Scheduled(cron="50 05 09 * * *") 
	public void requestAliveSelect() throws Exception {
		int totalCount = 0;
		int lProesult =0;
		int tapGubun= 0; //0:KBSPPM,1:MBCPPM,2:SBSPPM,3:KBSPPV

		tapGubun= 5;

		String[] rootDir = {
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171023-171029/CVOD/111.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171002-171008/CVOD/111.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/CVOD/111.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/CVOD/111.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/CVOD/111.xlsx"
				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171023-171029/CVOD/111.xlsx"
				
		};
		
		
//		String[] rootDir = {
//				"E:/00.project/00.2017/16.KBS_유통정보시스템/00.받은파일/20171201/KBS데이터_20171201/TV_VOD/201710/171002-171008/CVOD/111.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171009-171015/CVOD/111.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171016-171022/CVOD/111.xlsx",
//				"/usr/local/tomcat/apache-tomcat-8.5.23/webapps/TV_VOD/201710/171030-171105/CVOD/111.xlsx"
//				
//		};
		
		

		
		String proTypeStr ="";
		for(int fileListCnt=0;fileListCnt<rootDir.length;fileListCnt++) {
			
			
			
			
//			for(int tabCnt=0;tabCnt<4;tabCnt++) {
				service.CVOTask(rootDir[fileListCnt],fileListCnt);
				/*
				tapGubun = tabCnt;
				try {
					ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
					config.setSourceFile(rootDir[fileListCnt]);
					StringBuffer sb = new StringBuffer();
					StringBuffer errsb = new StringBuffer();
					String valid = config.valid();
					if(valid!=null) {
						logger.error(valid);
						return;
					}

					ExcelToJsonConverter convert = new ExcelToJsonConverter(config);
					ExcelWorkbook book = convert.convert();
					String jsonStr = book.toJson(config.isPretty());
					logger.debug(""+jsonStr.length());

					logger.debug(jsonStr.substring(0, 200));
					JSONParser parser = new JSONParser();
					Object obj = parser.parse(jsonStr);
					JSONObject jsonObj = (JSONObject) obj;
					logger.debug("size:"+jsonObj.size());

					JSONArray bookInfoArray = (JSONArray) jsonObj.get("sheets");

					JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(tapGubun); //0:KBS,1:MBC, 2:SBS,3:KBS PPV
					//			JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(1);

					JSONArray bookInfoArray2 = (JSONArray) jsonObj2.get("data");

					float validCnt =0;
					JSONArray arrayObj = null;
					for(int i=0;i<bookInfoArray2.size();i++) {

						arrayObj = (JSONArray) bookInfoArray2.get(i);
						String originStr="";
						String titleStr ="";
						String dateStr ="";
						String orgStr = "";
						String replaceStr ="";
						String platStr ="";
						String vodStr ="";

						String[] cntArray = {"0","0","0","0","0","0","0"};
						
						String[] dayArray = {"0","0","0","0","0","0","0"};
						
						if(fileListCnt==0) {
							dayArray[0] = "20171002";
							dayArray[1] = "20171003";
							dayArray[2] = "20171004";
							dayArray[3] = "20171005";
							dayArray[4] = "20171006";
							dayArray[5] = "20171007";
							dayArray[6] = "20171008";
						}else if(fileListCnt==1) {
							dayArray[0] = "20171009";
							dayArray[1] = "20171010";
							dayArray[2] = "20171011";
							dayArray[3] = "20171012";
							dayArray[4] = "20171013";
							dayArray[5] = "20171014";
							dayArray[6] = "20171015";
						}else if(fileListCnt==2) {
							dayArray[0] = "20171016";
							dayArray[1] = "20171017";
							dayArray[2] = "20171018";
							dayArray[3] = "20171019";
							dayArray[4] = "20171020";
							dayArray[5] = "20171021";
							dayArray[6] = "20171022";
						}else if(fileListCnt==3) {
							dayArray[0] = "20171030";
							dayArray[1] = "20171031";
							dayArray[2] = "20171101";
							dayArray[3] = "20171102";
							dayArray[4] = "20171103";
							dayArray[5] = "20171104";
							dayArray[6] = "20171105";
						}
						
						for(int j=0;j<arrayObj.size();j++) {
							if(j==2) {
								dateStr="";
								dateStr = getRexStr(ObjectUtils.toString(arrayObj.get(j), ""),"[0-9]{1,}회\\(구작\\)$|[0-9]{1,}회$|[0-9]{1,}회|[0-9]{1,}화").trim(); //
								//						dateStr = dateStr.replace("회(구작)", "").replace("회",""); //

								//sb.append(ObjectUtils.toString(arrayObj.get(j),"")+":["+i+"]["+j+"]["+dateStr+"]\r\n");
								originStr =ObjectUtils.toString(arrayObj.get(j),"");
								titleStr ="";
								orgStr = ObjectUtils.toString(arrayObj.get(j),"");
								//						replaceStr = getRexStr(orgStr,"^TV 다시보기_[a-zA-Z가-힣\\d-_\\s\\Q[a-zA-Z가-힣]\\E]{12,}\\[[가-힣]{1}\\]|^지상파_[a-zA-Z\\d-_\\s]{1,9}|^지상파_[a-zA-Z\\d-_\\s]{1,9}|^지상파_|^KBS_[0-9/]{5,}\\s\\[[가-힣]{1}\\]|^KBS_[0-9-]{10,}\\s\\[[가-힣]{1}\\]\\s|^KBS_[0-9]{1,}\\[[가-힣]{1}\\]|^KBS_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]|^KBS_\\[HD\\]\\s[0-9/]{5,}\\s\\[[가-힣]\\]|^KBS_\\[HD\\]\\s[0-9]{1,}\\Q[a-zA-Z가-힣]\\E]|^KBS_[0-9]{1,}_|_[0-9]{1,3}화$|TV다시보기_[A-Z]{1,3}[0-9]{1,6}|^TV 다시보기_[A-Z]{1,3}\\s[0-9]{6}\\s\\[[가-힣]{1}\\]|^TV 다시보기_[A-Z]{1,3}[0-9]{6}\\[[가-힣]{1}\\]|TV다시보기_[0-9]{1,}\\[[가-힣]{1}\\]");
								replaceStr = getRexStr(orgStr,"^\\(HD\\)*|^\\(SD\\)*|^\\(HD\\)[0-9a-zA-Z가-힣\\s]*");

								titleStr = orgStr.replace(replaceStr, "").replace(dateStr, "").replace("(SD)", "").replace("(HD)", "").trim();
								dateStr = dateStr.replace("회(구작)", "").replace("회",""); //
								//sb.append("\t\t:["+i+"]["+j+"]:["+titleStr+"]\r\n");
							}

							if(j==4) {
								vodStr = ObjectUtils.toString(arrayObj.get(j),"");
								cntArray[0]=vodStr.replace(".","").replace("E7","");
							}
							if(j==5) {
								vodStr = ObjectUtils.toString(arrayObj.get(j),"");
								cntArray[1]=vodStr.replace(".","").replace("E7","");
							}
							if(j==6) {
								vodStr = ObjectUtils.toString(arrayObj.get(j),"");
								cntArray[2]=vodStr.replace(".","").replace("E7","");
							}
							if(j==7) {
								vodStr = ObjectUtils.toString(arrayObj.get(j),"");
								cntArray[3]=vodStr.replace(".","").replace("E7","");
							}
							if(j==8) {
								vodStr = ObjectUtils.toString(arrayObj.get(j),"");
								cntArray[4]=vodStr.replace(".","").replace("E7","");
							}
							if(j==9) {
								vodStr = ObjectUtils.toString(arrayObj.get(j),"");
								cntArray[5]=vodStr.replace(".","").replace("E7","");
							}
							if(j==10) {
								vodStr = ObjectUtils.toString(arrayObj.get(j),"");
								cntArray[6]=vodStr.replace(".","").replace("E7","");
							}
							if(j==1) {
								platStr = ObjectUtils.toString(arrayObj.get(j),"");
							}
						}
						
//						logger.debug("cntArray:"+cntArray);
//						logger.debug("cntArray:"+cntArray[0]+":"+cntArray[1]+":"+cntArray[2]+":"+cntArray[3]+":"+cntArray[4]+":"+cntArray[5]+":"+cntArray[6]);
//						
						
						if(i==0) {
							proTypeStr = cntArray[0].replace("(개별)", "").trim();
						}
//						
//						if(i==1) {
//							dayArray = cntArray;
//						}
						
						if(i<2) continue;

						ArrayList<UseStatProVo> al = new ArrayList<UseStatProVo>();

						if(titleStr.length()>0&&dateStr.length()>0) {
							DataVo dVo = new DataVo();
							dVo.setProgramNm(titleStr);
							dVo.setVodcnt(""+CastUtil.toInt(dateStr));
							String programId ="";
							String contentsId ="";

							//					programId = mapper.selectGetProgramId(dVo);
							DataVo reciveVo = mapper.selectGetContentsId(dVo);

							if(reciveVo!=null) {
								validCnt++;
								sb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]:["+programId+"]:["+contentsId+"]\r\n");
								//회사 입력
//								CompanyVo comVo = new CompanyVo(); 
//								comVo.setCNm(platStr);
//								comVo.setCType("P");
//								int lcomresult =0;
//								if(mapper.selectCom(comVo)>0){
//									lcomresult = mapper.updateCom(comVo);
//								}else {
//									lcomresult = mapper.insertCom(comVo);
//								}
								//
								for(int k=0;k<cntArray.length;k++) {

									if(CastUtil.toInt(cntArray[k])>0) {
										UseStatProVo uspVo = new UseStatProVo();
										uspVo.setPcSeq(CastUtil.toInt(reciveVo.getPcSeq()));
										uspVo.setPSeq(CastUtil.toInt(reciveVo.getPSeq()));
//										if(tapGubun==0) {
											uspVo.setProType(proTypeStr);
//										}else {
//											uspVo.setProType("PPM");
//										}
										uspVo.setComNm(platStr);
										uspVo.setChargeYn("Y");
										uspVo.setChargeCnt(""+(CastUtil.toInt(cntArray[k])/10));
										uspVo.setViewDate(dayArray[k]);
										//							al.add(uspVo);
										uspVo.setInputId("SYSTEM");
										
										if(tabCnt<2) {
											uspVo.setChannelId("KBS");
										}else if(tabCnt==3) {
											uspVo.setChannelId("SBS");
										}else {
											uspVo.setChannelId("MBC");
										}
										
										uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);
										if(mapper.selectUseStatProgram(uspVo)>0){
											lProesult += mapper.updateUseStatProgram(uspVo);
										}else {
											lProesult += mapper.insertUseStatProgram(uspVo);
										}
									}
								}

							}else {

								for(int k=0;k<cntArray.length;k++) {

									if(CastUtil.toInt(cntArray[k])>0) {
										UseStatProVo uspVo = new UseStatProVo();
										uspVo.setPcSeq(-1);
										uspVo.setPSeq(-1);
//										if(tapGubun==0) {
											uspVo.setProType(proTypeStr);
//										}else {
//											uspVo.setProType("PPM");
//										}
										uspVo.setComNm(platStr);
										uspVo.setChargeYn("Y");
										uspVo.setChargeCnt(""+(CastUtil.toInt(cntArray[k])/10));
										uspVo.setViewDate(dayArray[k]);
										//							al.add(uspVo);
										uspVo.setInputId("SYSTEM");
										uspVo.setChannelId("");
										uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);
										
										
										if(tabCnt<2) {
											uspVo.setChannelId("KBS");
										}else if(tabCnt==3) {
											uspVo.setChannelId("SBS");
										}else {
											uspVo.setChannelId("MBC");
										}
										
										lProesult += mapper.insertUseStatProgram(uspVo);

									}
								}

								errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
							}

						}else {
							if(vodStr.indexOf("시리즈")>0) {
								validCnt++;
								sb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
							}else{
								for(int k=0;k<cntArray.length;k++) {
									if(CastUtil.toInt(cntArray[k])>0) {
										UseStatProVo uspVo = new UseStatProVo();
										uspVo.setPcSeq(-1);
										uspVo.setPSeq(-1);
										if(tapGubun==3) {
											uspVo.setProType("PPV");
										}else {
											uspVo.setProType("PPM");
										}
										uspVo.setComNm(platStr);
										uspVo.setChargeYn("Y");
										uspVo.setChargeCnt(""+(CastUtil.toInt(cntArray[k])/10));
										uspVo.setViewDate(dayArray[k]);
										//							al.add(uspVo);
										uspVo.setInputId("SYSTEM");
										uspVo.setChannelId("");
										uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);
										
										if(tabCnt<2) {
											uspVo.setChannelId("KBS");
										}else if(tabCnt==3) {
											uspVo.setChannelId("SBS");
										}else {
											uspVo.setChannelId("MBC");
										}
										
										lProesult += mapper.insertUseStatProgram(uspVo);
									}
								}

								errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
								logger.debug(orgStr+":"+replaceStr+":"+titleStr);
							}

						}//else end
					}// for bookinfo

					//				FileUtils.writeStringToFile(new File("D:\\KBS\\excel\\kbs_cvod.txt"), sb.toString(),"UTF-8");
					//				FileUtils.writeStringToFile(new File("D:\\KBS\\excel\\kbs_cvod_err.txt"), errsb.toString(),"UTF-8");
//					FileUtils.writeStringToFile(new File(rootDir+"kbs_cvod_"+tabCnt+".txt"), sb.toString(),"UTF-8");
//					FileUtils.writeStringToFile(new File(rootDir+"kbs_cvod_err_"+tabCnt+".txt"), errsb.toString(),"UTF-8");
					logger.debug("end-validCnt:"+(validCnt)+":"+lProesult);
					logger.debug("end-size : "+(bookInfoArray2.size()));
					logger.debug("end:"+(validCnt/bookInfoArray2.size() *100+"%"));
			
				}catch(Exception ex) {
					System.out.println("error:"+ex.getMessage());
					ex.printStackTrace();
				}
				
			}//file loop for
			*/
		}
	}


	/*
	public static void main(String[] args) throws IOException {
		try {
			ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
			config.setSourceFile("D:\\KBS\\excel\\CVOD\\cvod_use.xlsx");
			StringBuffer sb = new StringBuffer();
			StringBuffer errsb = new StringBuffer();
			String valid = config.valid();
			if(valid!=null) {
				System.out.println(valid);
				return;
			}

			ExcelToJsonConverter convert = new ExcelToJsonConverter(config);
			ExcelWorkbook book = convert.convert();
			String jsonStr = book.toJson(config.isPretty());
			System.out.println(jsonStr.length());

			System.out.println(jsonStr.substring(0, 200));
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(jsonStr);
			JSONObject jsonObj = (JSONObject) obj;
			System.out.println("size:"+jsonObj.size());

			JSONArray bookInfoArray = (JSONArray) jsonObj.get("sheets");

			System.out.println("array-size: "+bookInfoArray.size());
			JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(0); //0:KBS,1:MBC, 2:SBS,3:KBS PPV
//			JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(1);

			JSONArray bookInfoArray2 = (JSONArray) jsonObj2.get("data");

			System.out.println("array2-size: "+bookInfoArray2.size());
float validCnt =0;
JSONArray arrayObj = null;
			for(int i=0;i<bookInfoArray2.size();i++) {

				arrayObj = (JSONArray) bookInfoArray2.get(i);
				String originStr="";
				String titleStr ="";
				String dateStr ="";
				String orgStr = "";
				String replaceStr ="";
				String platStr ="";
				String vodStr ="";
				for(int j=0;j<arrayObj.size();j++) {
					if(j==2) {
						dateStr="";
						dateStr = getRexStr(ObjectUtils.toString(arrayObj.get(j), ""),"[0-9]{1,}회\\(구작\\)$|[0-9]{1,}회$|[0-9]{1,}회|[0-9]{1,}화").trim(); //
//						dateStr = dateStr.replace("회(구작)", "").replace("회",""); //

						//sb.append(ObjectUtils.toString(arrayObj.get(j),"")+":["+i+"]["+j+"]["+dateStr+"]\r\n");
						originStr =ObjectUtils.toString(arrayObj.get(j),"");
						titleStr ="";
						orgStr = ObjectUtils.toString(arrayObj.get(j),"");
//						replaceStr = getRexStr(orgStr,"^TV 다시보기_[a-zA-Z가-힣\\d-_\\s\\Q[a-zA-Z가-힣]\\E]{12,}\\[[가-힣]{1}\\]|^지상파_[a-zA-Z\\d-_\\s]{1,9}|^지상파_[a-zA-Z\\d-_\\s]{1,9}|^지상파_|^KBS_[0-9/]{5,}\\s\\[[가-힣]{1}\\]|^KBS_[0-9-]{10,}\\s\\[[가-힣]{1}\\]\\s|^KBS_[0-9]{1,}\\[[가-힣]{1}\\]|^KBS_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]|^KBS_\\[HD\\]\\s[0-9/]{5,}\\s\\[[가-힣]\\]|^KBS_\\[HD\\]\\s[0-9]{1,}\\Q[a-zA-Z가-힣]\\E]|^KBS_[0-9]{1,}_|_[0-9]{1,3}화$|TV다시보기_[A-Z]{1,3}[0-9]{1,6}|^TV 다시보기_[A-Z]{1,3}\\s[0-9]{6}\\s\\[[가-힣]{1}\\]|^TV 다시보기_[A-Z]{1,3}[0-9]{6}\\[[가-힣]{1}\\]|TV다시보기_[0-9]{1,}\\[[가-힣]{1}\\]");
						replaceStr = getRexStr(orgStr,"^\\(HD\\)*|^\\(SD\\)*|^\\(HD\\)[0-9a-zA-Z가-힣\\s]*");

						titleStr = orgStr.replace(replaceStr, "").replace(dateStr, "").replace("(SD)", "").replace("(HD)", "").trim();
						dateStr = dateStr.replace("회(구작)", "").replace("회",""); //
						//sb.append("\t\t:["+i+"]["+j+"]:["+titleStr+"]\r\n");
					}

					if(j==4) {
						vodStr = ObjectUtils.toString(arrayObj.get(j),"");
					}
					if(j==1) {
						platStr = ObjectUtils.toString(arrayObj.get(j),"");
					}
				}

				if(titleStr.length()>0&&dateStr.length()>0) {
					validCnt++;
					sb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
				}else {
					if(vodStr.indexOf("시리즈")>0) {
						validCnt++;
						sb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
					}else{
						errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
						System.out.println(orgStr+":"+replaceStr+":"+titleStr);
					}
				}
			}

			FileUtils.writeStringToFile(new File("D:\\KBS\\excel\\kbs_cvod.txt"), sb.toString(),"UTF-8");
			FileUtils.writeStringToFile(new File("D:\\KBS\\excel\\kbs_cvod_err.txt"), errsb.toString(),"UTF-8");
			System.out.println("end-validCnt:"+(validCnt));
			System.out.println("end-size : "+(bookInfoArray2.size()));
			System.out.println("end:"+(validCnt/bookInfoArray2.size() *100+"%"));
		}catch(Exception ex) {
			System.out.println("error:"+ex.getMessage());
			ex.printStackTrace();
		}

	}

	 */
	//지상파_인간극장 베스트_내 이름은 산다라박 이름, :([a-zA-Z가-힣_\\s]{1,})
	//회차 : "[0-9]{1,}회$|[0-9]{1,}회 |[0-9]{1,3}화$|[0-9]{1,3}화 |[0-9]{1,}부$


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
