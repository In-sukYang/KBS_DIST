package kr.co.kbs.distribute.schedule.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;
import kr.co.kbs.distribute.common.util.CastUtil;
import kr.co.kbs.distribute.common.util.JsonUtil;
import kr.co.kbs.distribute.common.util.ParseUtil;
import kr.co.kbs.distribute.common.util.excel.ExcelToJsonConverter;
import kr.co.kbs.distribute.common.util.excel.ExcelToJsonConverterConfig;
import kr.co.kbs.distribute.common.util.excel.pojo.ExcelWorkbook;
import kr.co.kbs.distribute.mapper.schedule.ScheduleDataMapper;
import kr.co.kbs.distribute.schedule.service.ScheduleDataService;
import kr.co.kbs.distribute.schedule.vo.CompanyVo;
import kr.co.kbs.distribute.schedule.vo.ContentsVo;
import kr.co.kbs.distribute.schedule.vo.DataVo;
import kr.co.kbs.distribute.schedule.vo.OrgProgramVo;
import kr.co.kbs.distribute.schedule.vo.ProgramVo;
import kr.co.kbs.distribute.schedule.vo.UseClipVo;
import kr.co.kbs.distribute.schedule.vo.UseStatProVo;

@Service
public class ScheduleDataServiceImpl  implements  ScheduleDataService {

	private static final Logger logger = (Logger) LoggerFactory.getLogger(ScheduleDataServiceImpl.class);

	@Autowired
	ScheduleDataMapper mapper;

	@Transactional
	@Override
	public int insertCode(OrgProgramVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.insertCode(vo);
	}

	@Transactional
	@Override
	public int insertCom(CompanyVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.insertCom(vo);
	}

	@Transactional
	@Override
	public int insertContents(ContentsVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.insertContents(vo);
	}

	@Transactional
	@Override
	public int insertOrgProgram(OrgProgramVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.insertOrgProgram(vo);
	}

	@Transactional
	@Override
	public int insertProgram(ProgramVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.insertProgram(vo);
	}

	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Override
	public int selectCode(OrgProgramVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectCode(vo);
	}

	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Override
	public int selectCom(CompanyVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectCom(vo);
	}

	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Override
	public int selectContents(ContentsVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectContents(vo);
	}

	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Override
	public int selectOrgProgram(OrgProgramVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectOrgProgram(vo);
	}

	@Transactional(readOnly = true, propagation=Propagation.SUPPORTS)
	@Override
	public int selectProgram(ProgramVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.selectProgram(vo);
	}

	@Transactional
	@Override
	public int updateCode(OrgProgramVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.updateCode(vo);
	}

	@Transactional
	@Override
	public int updateCom(CompanyVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.updateCom(vo);
	}

	@Transactional
	@Override
	public int updateContents(ContentsVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.updateContents(vo);
	}

	@Transactional
	@Override
	public int updateOrgProgram(OrgProgramVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.updateOrgProgram(vo);
	}

	@Transactional
	@Override
	public int updateProgram(OrgProgramVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.updateProgram(vo);
	}

	@Transactional
	@Override
	public int updateProgram(ProgramVo vo) throws Exception {
		// TODO Auto-generated method stub
		return mapper.updateProgram(vo);
	}


	@Override
	public int selectClip(UseClipVo vo) throws Exception{
		return mapper.selectClip(vo);
	}

	@Override
	@Transactional
	public void updateClip(UseClipVo vo) throws Exception{
		mapper.updateClip(vo);
	}

	@Override
	@Transactional
	public void insertClip(UseClipVo vo) throws Exception{
		mapper.insertClip(vo);
	}

	@Async
	@Override
	public void excuteClip(ArrayList<UseClipVo> al,ArrayList<String> dayal) throws Exception{

		for(int i=0;i<al.size();i++) {

			UseClipVo vo  =al.get(i);
			//				logger.debug("vo:"+vo);
			if(!ParseUtil.getRexStr(vo.getProgramSubNm(),"\\d{1,}회$").trim().equals("")) {
				String vodCnt = ParseUtil.getRexStr(vo.getProgramSubNm(),"\\d{1,}회$").trim();
				vo.setVodCnt(vodCnt.replace("회", ""));
				vo.setProgramNm(vo.getProgramSubNm().replace(vodCnt, "").replace("-", "").trim());
			}
			vo.setContentsId(vo.getClipId().replaceAll("_\\d{3,4}_SC\\d{2}$|_SC\\d{2}$|_\\d{3,4}-SC\\d{2}$", ""));
			for(int j=0;j<dayal.size();j++) {
				vo.setViewDate(dayal.get(j));
				switch (j) {
				case 0:
					vo.setViewCnt(CastUtil.toInt(vo.getViewCnt1()));
					break;
				case 1:
					vo.setViewCnt(CastUtil.toInt(vo.getViewCnt2()));
					break;
				case 2:
					vo.setViewCnt(CastUtil.toInt(vo.getViewCnt3()));
					break;
				case 3:
					vo.setViewCnt(CastUtil.toInt(vo.getViewCnt4()));
					break;
				case 4:
					vo.setViewCnt(CastUtil.toInt(vo.getViewCnt5()));
					break;
				case 5:
					vo.setViewCnt(CastUtil.toInt(vo.getViewCnt6()));
					break;
				case 6:
					vo.setViewCnt(CastUtil.toInt(vo.getViewCnt7()));
					break;

				default:
					break;
				}

				logger.debug("vo:"+vo);
				//				threadPoolTaskExecutor.execute(new MessagePrinterTask(vo),200);
				if(mapper.selectClip(vo)>0){
					//					lProesult += mapper.updateClip(vo);
					mapper.updateClip(vo);
				}else {
					//					lProesult += mapper.insertClip(vo);
					mapper.insertClip(vo);
				}
			}
		}

		mapper.excuteLast();
	}

	@Override
	@Transactional
	public void excuteLastDcViewLating() throws Exception{
		mapper.excuteLastDcViewLating();
	}

	@Override
	@Transactional
	public void excuteDViewRating() throws Exception{
		mapper.excuteDViewRating();
	}

	@Override
	@Transactional
	public void excuteClipPSETNotNullSet() throws Exception{
		mapper.excuteClipPSETNotNullSet();
	}

	@Override
	@Transactional
	public void excuteClipPCSETNotNullSet() throws Exception{
		mapper.excuteClipPCSETNotNullSet();
	}


	@Async
	@Override
	public void CVOTask(String filePath,int fileListCnt) throws Exception {
		int totalCount = 0;
		int lProesult =0;
		int tapGubun= 0; //0:KBSPPM,1:MBCPPM,2:SBSPPM,3:KBSPPV
		String proTypeStr="";
		tapGubun= 5;

		for(int tabCnt=0;tabCnt<4;tabCnt++) {
			tapGubun = tabCnt;
			try {
				ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
				config.setSourceFile(filePath);
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
				for(int i=2;i<bookInfoArray2.size();i++) {

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
					
					dayArray[0] = "20171023";
					dayArray[1] = "20171024";
					dayArray[2] = "20171025";
					dayArray[3] = "20171026";
					dayArray[4] = "20171027";
					dayArray[5] = "20171028";
					dayArray[6] = "20171029";
/*
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
*/
					for(int j=0;j<arrayObj.size();j++) {
						if(j==2) {
							dateStr="";
							dateStr = ParseUtil.getRexStr(ObjectUtils.toString(arrayObj.get(j), ""),"[0-9]{1,}회\\(구작\\)$|[0-9]{1,}회$|[0-9]{1,}회|[0-9]{1,}화").trim(); //
							//						dateStr = dateStr.replace("회(구작)", "").replace("회",""); //

							//sb.append(ObjectUtils.toString(arrayObj.get(j),"")+":["+i+"]["+j+"]["+dateStr+"]\r\n");
							originStr =ObjectUtils.toString(arrayObj.get(j),"");
							titleStr ="";
							orgStr = ObjectUtils.toString(arrayObj.get(j),"");
							//						replaceStr = getRexStr(orgStr,"^TV 다시보기_[a-zA-Z가-힣\\d-_\\s\\Q[a-zA-Z가-힣]\\E]{12,}\\[[가-힣]{1}\\]|^지상파_[a-zA-Z\\d-_\\s]{1,9}|^지상파_[a-zA-Z\\d-_\\s]{1,9}|^지상파_|^KBS_[0-9/]{5,}\\s\\[[가-힣]{1}\\]|^KBS_[0-9-]{10,}\\s\\[[가-힣]{1}\\]\\s|^KBS_[0-9]{1,}\\[[가-힣]{1}\\]|^KBS_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]|^KBS_\\[HD\\]\\s[0-9/]{5,}\\s\\[[가-힣]\\]|^KBS_\\[HD\\]\\s[0-9]{1,}\\Q[a-zA-Z가-힣]\\E]|^KBS_[0-9]{1,}_|_[0-9]{1,3}화$|TV다시보기_[A-Z]{1,3}[0-9]{1,6}|^TV 다시보기_[A-Z]{1,3}\\s[0-9]{6}\\s\\[[가-힣]{1}\\]|^TV 다시보기_[A-Z]{1,3}[0-9]{6}\\[[가-힣]{1}\\]|TV다시보기_[0-9]{1,}\\[[가-힣]{1}\\]");
							replaceStr = ParseUtil.getRexStr(orgStr,"^\\(HD\\)*|^\\(SD\\)*|^\\(HD\\)[0-9a-zA-Z가-힣\\s]*");

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

					//					logger.debug("cntArray:"+cntArray);
					//					logger.debug("cntArray:"+cntArray[0]+":"+cntArray[1]+":"+cntArray[2]+":"+cntArray[3]+":"+cntArray[4]+":"+cntArray[5]+":"+cntArray[6]);
					//					

//					if(i==0) {
//						proTypeStr = cntArray[0].replace("(개별)", "").trim();
//					}
					if(tabCnt==0) {
						proTypeStr="PPV";
					}else {
						proTypeStr="PPM";
					}
					//					
					//					if(i==1) {
					//						dayArray = cntArray;
					//					}
logger.debug("proTypeStr===================>"+proTypeStr);
					ArrayList<UseStatProVo> al = new ArrayList<UseStatProVo>();

					if(titleStr.length()>0&&dateStr.length()>0) {
						DataVo dVo = new DataVo();
						dVo.setProgramNm(titleStr);
						dVo.setVodcnt(""+CastUtil.toInt(dateStr));
						String programId ="";
						String contentsId ="";

						//					programId = mapper.selectGetProgramId(dVo);
						List<DataVo> reciveVo = mapper.selectGetContentsIdList(dVo);
						
						if(reciveVo!=null) {
							validCnt++;
							sb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]:["+programId+"]:["+contentsId+"]\r\n");
							//회사 입력
							//							CompanyVo comVo = new CompanyVo(); 
							//							comVo.setCNm(platStr);
							//							comVo.setCType("P");
							//							int lcomresult =0;
							//							if(mapper.selectCom(comVo)>0){
							//								lcomresult = mapper.updateCom(comVo);
							//							}else {
							//								lcomresult = mapper.insertCom(comVo);
							//							}
							//
							for(int k=0;k<cntArray.length;k++) {

								if(CastUtil.toInt(cntArray[k])>0) {
									UseStatProVo uspVo = new UseStatProVo();
									if(reciveVo.size()==1) {
										uspVo.setPcSeq(CastUtil.toInt(reciveVo.get(0).getPcSeq()));
										uspVo.setPSeq(CastUtil.toInt(reciveVo.get(0).getPSeq()));
									}else {
										uspVo.setPcSeq(-1);
										uspVo.setPSeq(-1);
									}
									//									if(tapGubun==0) {
									uspVo.setProType(proTypeStr);
									//									}else {
									//										uspVo.setProType("PPM");
									//									}
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
//									if(mapper.selectUseStatProgram(uspVo)>0){
//										lProesult += mapper.updateUseStatProgram(uspVo);
//									}else {
										lProesult += mapper.insertUseStatProgram(uspVo);
//									}
								}
							}

						}else {

							for(int k=0;k<cntArray.length;k++) {

								if(CastUtil.toInt(cntArray[k])>0) {
									UseStatProVo uspVo = new UseStatProVo();
									uspVo.setPcSeq(-1);
									uspVo.setPSeq(-1);
									//									if(tapGubun==0) {
									uspVo.setProType(proTypeStr);
									//									}else {
									//										uspVo.setProType("PPM");
									//									}
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
				//				FileUtils.writeStringToFile(new File(rootDir+"kbs_cvod_"+tabCnt+".txt"), sb.toString(),"UTF-8");
				//				FileUtils.writeStringToFile(new File(rootDir+"kbs_cvod_err_"+tabCnt+".txt"), errsb.toString(),"UTF-8");
				logger.debug("end-validCnt:"+(validCnt)+":"+lProesult);
				logger.debug("end-size : "+(bookInfoArray2.size()));
				logger.debug("end:"+(validCnt/bookInfoArray2.size() *100+"%"));

			}catch(Exception ex) {
				System.out.println("error:"+ex.getMessage());
				ex.printStackTrace();
			}
		}

	}

	@Async
	@Override
	public void SKPPVTask(String filePath, String channelId) throws Exception {


		ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
		config.setSourceFile(filePath);

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
		logger.debug("jsonlength:"+jsonStr.length());

		logger.debug(jsonStr.substring(0, 200));
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonStr);
		JSONObject jsonObj = (JSONObject) obj;
		logger.debug("size:"+jsonObj.size());

		JSONArray bookInfoArray = (JSONArray) jsonObj.get("sheets");

		logger.debug("array-size: "+bookInfoArray.size());
		JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(0);

		JSONArray bookInfoArray2 = (JSONArray) jsonObj2.get("data");
		int lProesult=0;
		logger.debug("array2-size: "+bookInfoArray2.size());
		float validCnt =0;
		JSONArray arrayObj = null;
		for(int i=2;i<bookInfoArray2.size();i++) {
			arrayObj = (JSONArray) bookInfoArray2.get(i);
			String titleStr ="";
			String dateStr ="";
			String orgStr = "";
			String replaceStr ="";
			String dateStrSub="";
			String vodStr ="";
			String screenType="";
			String chargeYn="";
			String amount="";
			String totalAmount="";
			String joinCnt="";
			String chargeCnt="";
			String onairStr="";
			String proType="PPV";
			for(int j=0;j<arrayObj.size();j++) {
				if(j==4) {
					titleStr ="";
					orgStr = ObjectUtils.toString(arrayObj.get(j),"");
					replaceStr  = ParseUtil.getRexStr(orgStr,"^\\[HD\\]");
					titleStr = orgStr.replace(replaceStr, "").trim();
				}

				if(j==1) {
					onairStr = ObjectUtils.toString(arrayObj.get(j),"");
				}
				//					if(j==3) {
				//						vodStr = ObjectUtils.toString(arrayObj.get(j),"");
				//					}

				if(j==5) {
					dateStr = ParseUtil.getRexStr(ObjectUtils.toString(arrayObj.get(j), ""),"[0-9]{1,2}.[0-9]{1,2}.[0-9]{1,2}").trim(); //
					vodStr = ParseUtil.getRexStr(ObjectUtils.toString(arrayObj.get(j), ""),"^[0-9]{1,}회").trim(); //

					dateStrSub = dateStr.replace(".", "");
					if(dateStrSub.length()==6) {
						if(dateStrSub.startsWith("9")) {
							dateStrSub = "19"+dateStrSub;
						}else {
							dateStrSub="20"+dateStrSub;
						}
					}

				}

				if(j==6) {
					chargeCnt = ObjectUtils.toString(arrayObj.get(j),"0");
				}

				if(j==7) {
					totalAmount = ObjectUtils.toString(arrayObj.get(j),"0");
				}
			}


			logger.debug("titleStr:"+titleStr+":dateStr:"+dateStrSub+":chargeCnt:"+chargeCnt+":onairStr:"+onairStr+":totalAmount:"+totalAmount);
			if(titleStr.length()>0&&(dateStrSub.length()>0 || vodStr.length()>0)) {
				DataVo dVo = new DataVo();
				dVo.setProgramNm(CastUtil.StringReplace(titleStr));
				dVo.setVodcnt(vodStr);
				dVo.setBroadDate(dateStrSub);
				String programId ="";
				String contentsId ="";

				DataVo reciveVo = mapper.selectGetContentsId(dVo);
				if(reciveVo!=null) {
					validCnt++;

					UseStatProVo uspVo = new UseStatProVo();
					uspVo.setPcSeq(CastUtil.toInt(reciveVo.getPcSeq()));
					uspVo.setPSeq(CastUtil.toInt(reciveVo.getPSeq()));
					uspVo.setProType(proType);
					uspVo.setComNm("SK");
					uspVo.setChargeYn("Y");
					uspVo.setTotalAmount(totalAmount);
					uspVo.setAmount(""+(CastUtil.toInt(totalAmount)/CastUtil.toInt(chargeCnt)));
					uspVo.setChargeCnt(chargeCnt);
					//uspVo.setJoinCnt(joinCnt);
					uspVo.setViewDate(onairStr);
					uspVo.setInputId("SYSTEM");
					uspVo.setChannelId(channelId);

					uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStrSub+"|"+vodStr);
					//					if(mapper.selectUseStatProgram(uspVo)>0){
					//						lProesult += mapper.updateUseStatProgram(uspVo);
					//					}else {
					lProesult += mapper.insertUseStatProgram(uspVo);
					//					}
					sb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]:["+programId+"]:["+contentsId+"]\r\n");
				}else {
					UseStatProVo uspVo = new UseStatProVo();
					uspVo.setPcSeq(-1);
					uspVo.setPSeq(-1);
					uspVo.setProType(proType);
					uspVo.setComNm("SK");
					uspVo.setChargeYn("Y");
					uspVo.setTotalAmount(totalAmount);
					uspVo.setAmount(""+(CastUtil.toInt(totalAmount)/CastUtil.toInt(chargeCnt)));
					uspVo.setChargeCnt(chargeCnt);
					//uspVo.setJoinCnt(joinCnt);
					uspVo.setViewDate(onairStr);
					uspVo.setInputId("SYSTEM");
					uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStrSub+"|"+vodStr);
					uspVo.setChannelId(channelId);


					//					if(mapper.selectUseStatProgramSub(uspVo)<1){
					lProesult += mapper.insertUseStatProgram(uspVo);
					//					}
					errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
				}

			}else {
				UseStatProVo uspVo = new UseStatProVo();
				uspVo.setPcSeq(-1);
				uspVo.setPSeq(-1);
				uspVo.setProType(proType);
				uspVo.setComNm("SK");
				uspVo.setChargeYn("Y");
				uspVo.setTotalAmount(totalAmount);
				uspVo.setAmount(""+(CastUtil.toInt(totalAmount)/CastUtil.toInt(chargeCnt)));
				uspVo.setChargeCnt(chargeCnt);
				//uspVo.setJoinCnt(joinCnt);
				uspVo.setViewDate(onairStr);
				uspVo.setInputId("SYSTEM");
				uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStrSub+"|"+vodStr);
				uspVo.setChannelId(channelId);

				//				if(mapper.selectUseStatProgramSub(uspVo)<1){
				lProesult += mapper.insertUseStatProgram(uspVo);
				//				}
			}
		}

		//		FileUtils.writeStringToFile(new File(rootDir+"kbs_sk_ppv.txt"), sb.toString(),"UTF-8");
		//		FileUtils.writeStringToFile(new File(rootDir+"kbs_sk_ppv_err.txt"), errsb.toString(),"UTF-8");

		logger.debug("end-validCnt:"+(validCnt)+":"+lProesult);
		logger.debug("end-size : "+(bookInfoArray2.size()));
		logger.debug("end:"+(validCnt/bookInfoArray2.size() *100+"%"));

	}

	@Async
	@Override
	public void SKPPMTask(String filePath, int fileListCnt) throws Exception {


		int lProesult =0;

		logger.debug("filePath:"+filePath);

		ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
		config.setSourceFile(filePath);
		//			config.setSourceFile(rootDir+fileNameArray[fiCnt]);
		//			ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
		//			config.setSourceFile(rootDir+"kbs_sk_ppm.xls");
		//			config.setSourceFile("/usr/local/tomcat/apache-tomcat-8.5.23/kt_sell.xlsx");
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
		logger.debug("jsonlength:"+jsonStr.length());

		logger.debug(jsonStr.substring(0, 200));
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonStr);
		JSONObject jsonObj = (JSONObject) obj;
		logger.debug("size:"+jsonObj.size());

		JSONArray bookInfoArray = (JSONArray) jsonObj.get("sheets");

		logger.debug("array-size: "+bookInfoArray.size());
		JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(0);

		JSONArray bookInfoArray2 = (JSONArray) jsonObj2.get("data");

		logger.debug("array2-size: "+bookInfoArray2.size());
		float validCnt =0;
		JSONArray arrayObj = null;
		for(int i=1;i<bookInfoArray2.size();i++) {

			arrayObj = (JSONArray) bookInfoArray2.get(i);
			String titleStr ="";
			String titleSubStr ="";
			String dateStr ="";
			String dateStrSub ="";
			String orgStr = "";
			String replaceStr ="";
			String channel ="";
			String vodStr ="";
			String screenType="";
			String chargeYn="";
			String amount="";
			String totalAmount="";
			String joinCnt="";
			String chargeCnt="";
			String onairStr="";
			String proType="PPM";
			for(int j=0;j<arrayObj.size();j++) {
				if(j==2) {
					titleStr ="";
					orgStr = ObjectUtils.toString(arrayObj.get(j),"");
					replaceStr  = ParseUtil.getRexStr(orgStr,"^\\[HD\\]");
					titleStr = orgStr.replace(replaceStr, "").trim();
				}

				if(j==0) {
					onairStr = ObjectUtils.toString(arrayObj.get(j),"");
				}
				if(j==1) {
					channel = ObjectUtils.toString(arrayObj.get(j),"");

					if(channel.indexOf("KBS")>-1) {
						channel = "KBS";
					}else if(channel.indexOf("에스비에스")>-1) {
						channel = "SBS";
					}else {
						channel = "MBC";
					}
				}

				if(j==3) {
					titleSubStr =ObjectUtils.toString(arrayObj.get(j), "");
					vodStr = ParseUtil.getRexStr(titleSubStr,"^[0-9]{1,}회").trim(); //
					dateStrSub = ParseUtil.getRexStr(titleSubStr,"[0-9]{2}.[0-9]{2}.[0-9]{2}|[0-9]{4}-[0-9]{2}-[0-9]{2}").trim(); //
					dateStrSub = dateStrSub.replace(".", "").replace("-", "");
					if(dateStrSub.length()==6) {
						if(dateStrSub.startsWith("9")) {
							dateStrSub = "19"+dateStrSub;
						}else {
							dateStrSub="20"+dateStrSub;
						}
					}
				}

				if(j==5) {
					chargeCnt = ObjectUtils.toString(arrayObj.get(j),"");
				}

				if(j==4) {
					joinCnt = ObjectUtils.toString(arrayObj.get(j),"");
				}
			}

			
			if(titleStr.length()>0&&(dateStrSub.length()>0 || vodStr.length()>0)) {
				DataVo dVo = new DataVo();
				dVo.setProgramNm(CastUtil.StringReplace(titleStr.trim()));
				dVo.setVodcnt(StringUtils.defaultIfEmpty(vodStr.replace("회", ""), "-9999"));
				dVo.setBroadDate(dateStrSub.trim());
				String programId ="";
				String contentsId ="";
				
				List<DataVo> reciveVo = mapper.selectGetContentsIdList(dVo);
				String seqStr="";
				if(reciveVo!=null) {
					validCnt++;
					UseStatProVo uspVo = new UseStatProVo();
					if(reciveVo.size()==1) {
						uspVo.setPcSeq(CastUtil.toInt(reciveVo.get(0).getPcSeq()));
						uspVo.setPSeq(CastUtil.toInt(reciveVo.get(0).getPSeq()));
					}else if(reciveVo.size()==1) {
						seqStr="|"+reciveVo.get(0).getPSeq()+"|"+reciveVo.get(0).getPcSeq();
					}else {
						uspVo.setPcSeq(-1);
						uspVo.setPSeq(-1);
					}
					uspVo.setProType(proType);
					uspVo.setComNm("SK");
					uspVo.setChargeYn("Y");
					uspVo.setChargeCnt(chargeCnt);
					uspVo.setJoinCnt(joinCnt);
					uspVo.setViewDate(onairStr);
					uspVo.setInputId("admin");
					uspVo.setChannelId(channel);
					uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStrSub+"|"+vodStr+seqStr);
					//					if(mapper.selectUseStatProgram(uspVo)>0){
					//						lProesult += mapper.updateUseStatProgram(uspVo);
					//					}else {
					lProesult += mapper.insertUseStatProgram(uspVo);
					//					}
					sb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]:["+programId+"]:["+contentsId+"]\r\n");
				}else {

					UseStatProVo uspVo = new UseStatProVo();
					uspVo.setPcSeq(-1);
					uspVo.setPSeq(-1);
					uspVo.setProType(proType);
					uspVo.setComNm("SK");
					uspVo.setChargeYn("Y");
					uspVo.setChargeCnt(chargeCnt);
					uspVo.setJoinCnt(joinCnt);
					uspVo.setViewDate(onairStr);
					uspVo.setInputId("admin");
					uspVo.setChannelId(channel);

					uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStrSub+"|"+vodStr);
					//					if(mapper.selectUseStatProgramSub(uspVo)<1){
					lProesult += mapper.insertUseStatProgram(uspVo);
					//					}
					//					lProesult += mapper.insertUseStatProgram(uspVo);
					errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
				}

			}else {
				UseStatProVo uspVo = new UseStatProVo();
				uspVo.setPcSeq(-1);
				uspVo.setPSeq(-1);
				uspVo.setProType(proType);
				uspVo.setComNm("SK");
				uspVo.setChargeYn("Y");
				uspVo.setChargeCnt(chargeCnt);
				uspVo.setJoinCnt(joinCnt);
				uspVo.setViewDate(onairStr);
				uspVo.setInputId("admin");
				uspVo.setChannelId(channel);
				uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStrSub+"|"+vodStr);
				//				if(mapper.selectUseStatProgramSub(uspVo)<1){
				lProesult += mapper.insertUseStatProgram(uspVo);
				//				}
				//				lProesult += mapper.insertUseStatProgram(uspVo);
				errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
			}
		}

		logger.debug("end-validCnt:"+(validCnt)+":"+lProesult);
		logger.debug("end-size : "+(bookInfoArray2.size()));
		logger.debug("end:"+(validCnt/bookInfoArray2.size() *100+"%"));

	}


	@Async
	@Override
	public void PooqPPMTask(String fileListDir) throws Exception{
		File filedir = new File(fileListDir);

		int lProesult=0;
		String [] listFileStr = filedir.list();
		logger.debug("listFileStr.length:"+listFileStr.length);
		for(int p=0;p<listFileStr.length;p++) {
			if(p>0) break;
			logger.debug("listFileStr.length############:"+listFileStr[p]);
			String readStr="";
			try {
				readStr = JsonUtil.readCsv(fileListDir+listFileStr[p],"UTF-8");
			}catch(Exception ex) {
				ex.printStackTrace();
				continue;
			}
			//kbs_live.csv
			String jsonStr = JsonUtil.CSVtoJSON(readStr);

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(jsonStr);

			JSONArray infoarray = (JSONArray) obj;

			for(int i=1;i<infoarray.size();i++) {
				JSONObject jsonObj = (JSONObject) infoarray.get(i);
				UseStatProVo uspVo = new UseStatProVo();
				uspVo.setProType("PPM");
				uspVo.setComNm("pooq");
				uspVo.setInputId("SYSTEM");

				Iterator iter = jsonObj.keySet().iterator();
				while (iter.hasNext()) {
					String key = (String) iter.next();
					int keyInt = CastUtil.toInt(key);
					String value ="";
					value = ""+jsonObj.get(""+keyInt);

					if(keyInt==0) {
						uspVo.setViewDate(value);
					}
					if(keyInt==1) uspVo.setChannelId(value);
					if(keyInt==3) uspVo.setProgramId(value);
					if(keyInt==6) uspVo.setProgramNm(value);
					if(keyInt==4) uspVo.setContentsId(value);
					if(keyInt==8) {
						if(value.equals("N")) {
							uspVo.setChargeYn("Y");
						}else {
							uspVo.setChargeYn("N");
						}
					}
					if(keyInt==10) uspVo.setChargeVtime(value);//유료시청시간
					if(keyInt==11) uspVo.setHChargeVtime(value);//유료시청시간(초고화질)
					if(keyInt==12) uspVo.setChargeCnt(value);//유료시청자수
					if(keyInt==13) uspVo.setHChargeCnt(value);//초고화질유료시청자수
					if(keyInt==14) uspVo.setFreeChargeVtime(value);//무료시청시간
					if(keyInt==15) uspVo.setHFreeChargeVtime(value);//초고화질무료시청시간
					if(keyInt==16) uspVo.setFreeChargeCnt(value);//무료시청자수
					if(keyInt==17) uspVo.setHFreeChargeCnt(value);//무료시청자수(초고화질)
				}
				try {
					UseStatProVo uspVoResp = mapper.selectGetContentsIdInfo(uspVo);
					if(uspVoResp!=null) {
						uspVo.setPcSeq(uspVoResp.getPcSeq());
						uspVo.setPSeq(uspVoResp.getPSeq());
						uspVo.setTempData(uspVo.getProgramNm()+"|"+uspVo.getProgramId()+"|"+uspVo.getContentsId());
						//						if(mapper.selectUseStatProgram(uspVo)>0){
						//							lProesult += mapper.updateUseStatProgram(uspVo);
						//						}else {
						lProesult += mapper.insertUseStatProgram(uspVo);
						//						}
					}else {
						uspVo.setPcSeq(-1);
						uspVo.setPSeq(-1);
						uspVo.setTempData(uspVo.getProgramNm()+"|"+uspVo.getProgramId()+"|"+uspVo.getContentsId());

						//						if(mapper.selectUseStatProgramSub(uspVo)<1){
						lProesult += mapper.insertUseStatProgram(uspVo);
						//						}
					}
				}catch(Exception ex) {
					ex.printStackTrace();
					throw ex;
				}
			}

			logger.debug("listFileStr.length#################:"+listFileStr.length+":p;"+p);
		}
	}

	@Async
	@Override
	public void LGPPMTask(String filePath, String channelName) throws Exception {
		ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
		config.setSourceFile(filePath);

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
		logger.debug("jsonlength:"+jsonStr.length());

		logger.debug(jsonStr.substring(0, 200));
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonStr);
		JSONObject jsonObj = (JSONObject) obj;
		logger.debug("size:"+jsonObj.size());

		JSONArray bookInfoArray = (JSONArray) jsonObj.get("sheets");

		logger.debug("array-size: "+bookInfoArray.size());
		JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(0);

		JSONArray bookInfoArray2 = (JSONArray) jsonObj2.get("data");
		int lProesult =0;
		logger.debug("array2-size: "+bookInfoArray2.size());
		float validCnt =0;
		JSONArray arrayObj = null;
		for(int i=1;i<bookInfoArray2.size();i++) {

			arrayObj = (JSONArray) bookInfoArray2.get(i);
			String titleStr ="";
			String dateStr ="";
			String orgStr = "";
			String replaceStr ="";
			String vodStr ="";
			String screenType="";
			String chargeYn="";
			String amount="";
			String joinCnt="";
			String chargeCnt="";
			String onairStr="";
			String proType="PPM";
			for(int j=0;j<arrayObj.size();j++) {
				if(j==0) {
					titleStr ="";
					orgStr = ObjectUtils.toString(arrayObj.get(j),"");
					replaceStr  = ParseUtil.getRexStr(orgStr,"^[0-9]{1,}\\/[0-9]{1,}\\([가-힣]{1}\\)|^[0-9]{1,}회\\s\\s[0-9]{1,}\\/[0-9]{1,}\\([가-힣]{1}\\)|^[0-9]{1,}회\\s[0-9]{1,}\\/[0-9]{1,}\\([가-힣]{1}\\)|^[0-9]{1,}회\\s");
					titleStr = orgStr.replace(replaceStr, "").trim();
					vodStr = ParseUtil.getRexStr(orgStr,"^[0-9]{1,}회");
				}
				if(j==1) {
					onairStr = ObjectUtils.toString(arrayObj.get(j),"");
				}
				if(j==2) {
					proType = ObjectUtils.toString(arrayObj.get(j),""); //PPM,PPV
				}
				if(j==3) {
					dateStr = ObjectUtils.toString(arrayObj.get(j),"");
				}
				if(j==4) {
					joinCnt = ObjectUtils.toString(arrayObj.get(j),"");
				}
				if(j==5) {
					chargeCnt = ObjectUtils.toString(arrayObj.get(j),"");
				}
			}
			vodStr = vodStr.trim();
			if(titleStr.length()>0&&dateStr.length()>0) {
				DataVo dVo = new DataVo();
				dVo.setProgramNm(CastUtil.StringReplace(titleStr));
//				dVo.setVodcnt(StringUtils.defaultIfEmpty(vodStr.replace("회", ""), "-9999"));
				dVo.setVodcnt("-9999");
				dVo.setBroadDate(onairStr);
				String programId ="";
				String contentsId ="";

				List<DataVo> reciveVo = mapper.selectGetContentsIdList(dVo);
				String seqStr="";
				if(reciveVo!=null) {
					validCnt++;
					UseStatProVo uspVo = new UseStatProVo();
					if(reciveVo.size()==1) {
						uspVo.setPcSeq(CastUtil.toInt(reciveVo.get(0).getPcSeq()));
						uspVo.setPSeq(CastUtil.toInt(reciveVo.get(0).getPSeq()));
					}else if(reciveVo.size()>1) {
						seqStr="|"+reciveVo.get(0).getPSeq()+"|"+reciveVo.get(0).getPcSeq();
					}else {
						uspVo.setPcSeq(-1);
						uspVo.setPSeq(-1);
					}
					uspVo.setProType(proType);
					uspVo.setComNm("LG");
					uspVo.setChargeYn("Y");
					uspVo.setChargeCnt(chargeCnt);
					uspVo.setJoinCnt(joinCnt);
					uspVo.setViewDate(dateStr);
					uspVo.setInputId("SYSTEM");
					uspVo.setChannelId(channelName);
					uspVo.setTempData(orgStr+"|"+titleStr+"|"+onairStr+"|"+dateStr+seqStr);
					lProesult += mapper.insertUseStatProgram(uspVo);
					sb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]:["+programId+"]:["+contentsId+"]\r\n");
				}else {
					UseStatProVo uspVo = new UseStatProVo();
					uspVo.setPcSeq(-1);
					uspVo.setPSeq(-1);
					uspVo.setProType(proType);
					uspVo.setComNm("LG");
					uspVo.setChargeYn("Y");
					uspVo.setChargeCnt(chargeCnt);
					uspVo.setJoinCnt(joinCnt);
					uspVo.setViewDate(dateStr);
					uspVo.setInputId("SYSTEM");
					uspVo.setChannelId(channelName);
					uspVo.setTempData(orgStr+"|"+titleStr+"|"+onairStr+"|"+dateStr);
					lProesult += mapper.insertUseStatProgram(uspVo);
					errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
				}
			}else {
				UseStatProVo uspVo = new UseStatProVo();
				uspVo.setPcSeq(-1);
				uspVo.setPSeq(-1);
				uspVo.setProType(proType);
				uspVo.setComNm("LG");
				uspVo.setChargeYn("Y");
				uspVo.setChargeCnt(chargeCnt);
				uspVo.setJoinCnt(joinCnt);
				uspVo.setViewDate(dateStr);
				uspVo.setInputId("SYSTEM");
				uspVo.setChannelId(channelName);
				uspVo.setTempData(orgStr+"|"+titleStr+"|"+onairStr+"|"+dateStr);
				lProesult += mapper.insertUseStatProgram(uspVo);
				errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]:["+vodStr+"]:["+onairStr+"]\r\n");
				System.out.println(orgStr+":"+replaceStr+":"+titleStr);
			}
		}
	}

	@Async
	@Override
	public void KtUseTask(String filePath,int fileListCnt, String viewDateStr) throws Exception {
		int lProesult =0;
		ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
		config.setSourceFile(filePath);
		//			config.setSourceFile("/usr/local/tomcat/apache-tomcat-8.5.23/kt_sell.xlsx");
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
		logger.debug("jsonlength:"+jsonStr.length());

		logger.debug(jsonStr.substring(0, 200));
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(jsonStr);
		JSONObject jsonObj = (JSONObject) obj;
		logger.debug("size:"+jsonObj.size());

		JSONArray bookInfoArray = (JSONArray) jsonObj.get("sheets");

		logger.debug("array-size: "+bookInfoArray.size());
		JSONObject jsonObj2 = (JSONObject) bookInfoArray.get(0);

		JSONArray bookInfoArray2 = (JSONArray) jsonObj2.get("data");

		logger.debug("array2-size: "+bookInfoArray2.size());
		float validCnt =0;
		JSONArray arrayObj = null;
		for(int i=2;i<bookInfoArray2.size();i++) {

			arrayObj = (JSONArray) bookInfoArray2.get(i);
			String titleStr ="";
			String dateStr ="";
			String orgStr = "";
			String replaceStr ="";

			String vodStr ="";
			String screenType="";
			String chargeYn="";
			String amount="";
			String tamount="";
			String chargeCnt="";

			String channelNm="";

			for(int j=0;j<arrayObj.size();j++) {
				if(j==3) {
					dateStr="";
					dateStr = ParseUtil.getRexStr(ObjectUtils.toString(arrayObj.get(j), ""),"[0-9]{1,}회$|[0-9]{1,}회$|[0-9]{1,}회 |[0-9]{1,3}화$|[0-9]{1,3}화 |[0-9]{1,}부$|[0-9]{6,}|[0-9-]{10,}").trim(); //
					//sb.append(ObjectUtils.toString(arrayObj.get(j),"")+":["+i+"]["+j+"]["+dateStr+"]\r\n");

					titleStr ="";
					orgStr = ObjectUtils.toString(arrayObj.get(j),"");
					//						replaceStr = getRexStr(orgStr,"^TV 다시보기_[a-zA-Z가-힣\\d-_\\s\\Q[a-zA-Z가-힣]\\E]{12,}\\[[가-힣]{1}\\]|^지상파_[a-zA-Z\\d-_\\s]{1,9}|^지상파_[a-zA-Z\\d-_\\s]{1,9}|^지상파_|^KBS_[0-9/]{5,}\\s\\[[가-힣]{1}\\]|^KBS_[0-9-]{10,}\\s\\[[가-힣]{1}\\]\\s|^KBS_[0-9]{1,}\\[[가-힣]{1}\\]|^KBS_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]|^KBS_\\[HD\\]\\s[0-9/]{5,}\\s\\[[가-힣]\\]|^KBS_\\[HD\\]\\s[0-9]{1,}\\Q[a-zA-Z가-힣]\\E]|^KBS_[0-9]{1,}_|_[0-9]{1,3}화$|TV다시보기_[A-Z]{1,3}[0-9]{1,6}|^TV 다시보기_[A-Z]{1,3}\\s[0-9]{6}\\s\\[[가-힣]{1}\\]|^TV 다시보기_[A-Z]{1,3}[0-9]{6}\\[[가-힣]{1}\\]|TV다시보기_[0-9]{1,}\\[[가-힣]{1}\\]");
					replaceStr = ParseUtil.getRexStr(orgStr,"TEST_[a-zA-Z]{3}[0-9]{6}|^지상파_[a-zA-Z]{3}[0-9]{6}|"
							+ "^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}[0-9]{8}|"
							+ "^TV다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^TV다시보기_[a-zA-Z]{3}[0-9]{6}|"
							+ "^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]\\s|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}\\[[가-힣]{1}\\]|"
							+ "^TV 다시보기_[a-zA-Z]{3}\\s{1,2}[0-9]{6}\\s{0,1}\\[[가-힣]{1}\\]|^TV 다시보기_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{1,}화\\s|"
							+ "^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회\\s|^[a-zA-Z]{3}_\\[HD\\][0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{4}-[0-9]{2}-[0-9]{2}\\s\\[[가-힣]{1}\\]|"
							+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\][0-9]{1,}회|"
							+ "^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{2}\\/[0-9]{2}\\s\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6}\\[[가-힣]{1}\\]|"
							+ "^[a-zA-Z]{3}_HD[0-9]{6}\\[[가-힣]{1}\\]\\s|^[a-zA-Z]{3}_\\[HD\\]\\s[0-9]{6,}\\[[가-힣]{1}\\]\\s[0-9]{1,}회|"
							+ "^[a-zA-Z]{3}_[0-9]{6}\\[[가-힣]{1}\\]|^[a-zA-Z]{3}_[0-9]{8}_\\[HD\\]|^[a-zA-Z]{3}_[0-9]{8}_|^TV 다시보기_[a-zA-Z]{3}[0-9]{6}|"
							+ "^[a-zA-Z]{3}_[0-9]{2}-[0-9]{2}-[0-9]{2}\\[[가-힣]{1}\\]|^TV 다시보기_[a-zA-Z]{3}");

					//						titleStr = orgStr.replace(replaceStr, "").replace("지상파_", "").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").trim();
					titleStr = orgStr.replace( replaceStr, "").replace("_재편성", "").replace("_수정", "").replace("오늘의 추천_", "").replace("지상파_", "").replace("TV다시보기_","").replace("TV 다시보기_", "").replace(dateStr, "").replace("_1", "").replace("[HD]", "").replaceAll("[a-zA-Z]{3}_", "").replaceAll("[_]$", "").trim();
					titleStr = titleStr.replace(ParseUtil.getRexStr(orgStr,"[0-9]{1,}회$|\\(HD\\)$"),"").trim();

					//sb.append("\t\t:["+i+"]["+j+"]:["+titleStr+"]\r\n");
				}

				if(j==1) {
					channelNm = ObjectUtils.toString(arrayObj.get(j),"");
					logger.debug("channelNm:"+channelNm);
					if(channelNm.indexOf("KBS")>-1) {
						channelNm = "KBS";
					}else if(channelNm.indexOf("SBS")>-1) {
						channelNm = "SBS";
					}else {
						channelNm = "MBC";
					}
				}

				if(fileListCnt==3) {
					if(j==5) {//임시
						amount = ObjectUtils.toString(arrayObj.get(j),"");
					}

					if(j==6) {//임시
						chargeCnt = ObjectUtils.toString(arrayObj.get(j),"");
					}
				}else {
					if(j==4) {
						amount = ObjectUtils.toString(arrayObj.get(j),"");
					}

					if(j==5) {
						chargeCnt = ObjectUtils.toString(arrayObj.get(j),"");
					}
				}
			}

			if(titleStr.length()>0&&dateStr.length()>0) {
				DataVo dVo = new DataVo();
				dVo.setProgramNm(titleStr);
				String dateStrSub =dateStr;
				if(dateStrSub.length()==6) {
					if(dateStrSub.startsWith("9")) {
						dateStrSub = "19"+dateStrSub;
					}else {
						dateStrSub="20"+dateStrSub;
					}
				}

				dVo.setVodcnt(dateStrSub);
				dVo.setBroadDate(dateStrSub);
				String programId ="";
				String contentsId ="";

				//					programId = mapper.selectGetProgramId(dVo);
				//					contentsId = mapper.selectGetContentsId(dVo);
				List<DataVo> reciveVo = mapper.selectGetContentsIdList(dVo);

				if(reciveVo!=null) {
					validCnt++;
					UseStatProVo uspVo = new UseStatProVo();
					if(reciveVo.size()==1) {
						uspVo.setPcSeq(CastUtil.toInt(reciveVo.get(0).getPcSeq()));
						uspVo.setPSeq(CastUtil.toInt(reciveVo.get(0).getPSeq()));
					}else {
						uspVo.setPcSeq(-1);
						uspVo.setPSeq(-1);
					}
					uspVo.setProType("PPM");
					uspVo.setComNm("KT");
					uspVo.setChargeYn("Y");
					uspVo.setAmount(amount);
					uspVo.setTotalAmount(""+(CastUtil.toInt(amount) * CastUtil.toInt(chargeCnt)));
					uspVo.setChargeCnt(chargeCnt);
					uspVo.setViewDate(viewDateStr);
					uspVo.setInputId("admin");
					uspVo.setChannelId(channelNm);
					uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);
					//							if(mapper.selectUseStatProgram(uspVo)>0){
					//								lProesult += mapper.updateUseStatProgram(uspVo);
					//							}else {
					lProesult += mapper.insertUseStatProgram(uspVo);
					//							}
					sb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]:["+programId+"]:["+contentsId+"]\r\n");
				}else {
					UseStatProVo uspVo = new UseStatProVo();
					uspVo.setPcSeq(-1);
					uspVo.setPSeq(-1);
					uspVo.setProType("PPM");
					uspVo.setComNm("KT");
					uspVo.setChargeYn("Y");
					uspVo.setAmount(amount);
					uspVo.setTotalAmount(""+(CastUtil.toInt(amount) * CastUtil.toInt(chargeCnt)));
					uspVo.setChargeCnt(chargeCnt);
					uspVo.setViewDate(viewDateStr);
					uspVo.setInputId("admin");
					uspVo.setChannelId(channelNm);
					uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);
					//							if(mapper.selectUseStatProgram(uspVo)>0){
					//								lProesult += mapper.updateUseStatProgram(uspVo);
					//							}else {
					lProesult += mapper.insertUseStatProgram(uspVo);

					errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
				}

			}else {
				if(vodStr.indexOf("시리즈")>0) {
					DataVo dVo = new DataVo();
					dVo.setProgramNm(titleStr);
					dVo.setVodcnt("1");
					dVo.setBroadDate(dateStr);
					String programId ="";
					String contentsId ="";
					DataVo reciveVo = mapper.selectGetContentsId(dVo);
					if(reciveVo!=null) {
						validCnt++;

						UseStatProVo uspVo = new UseStatProVo();
						uspVo.setPcSeq(CastUtil.toInt(contentsId));
						uspVo.setPSeq(CastUtil.toInt(programId));
						uspVo.setProType("PPM");
						uspVo.setComNm("KT");
						uspVo.setChargeYn("Y");

						uspVo.setAmount(amount);
						uspVo.setTotalAmount(""+(CastUtil.toInt(amount) * CastUtil.toInt(chargeCnt)));
						uspVo.setChargeCnt(chargeCnt);

						uspVo.setViewDate(viewDateStr);
						uspVo.setInputId("admin");
						uspVo.setChannelId(channelNm);
						//								if(mapper.selectUseStatProgram(uspVo)>0){
						//									lProesult += mapper.updateUseStatProgram(uspVo);
						//								}else {
						lProesult += mapper.insertUseStatProgram(uspVo);
						//								}

						sb.append("시리즈:["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+vodStr+"]:["+programId+"]:["+contentsId+"]\r\n");
					}else {

						UseStatProVo uspVo = new UseStatProVo();
						uspVo.setPcSeq(-1);
						uspVo.setPSeq(-1);
						uspVo.setProType("PPM");
						uspVo.setComNm("KT");
						uspVo.setChargeYn("Y");

						uspVo.setAmount(amount);
						uspVo.setTotalAmount(""+(CastUtil.toInt(amount) * CastUtil.toInt(chargeCnt)));
						uspVo.setChargeCnt(chargeCnt);

						uspVo.setViewDate(viewDateStr);
						uspVo.setInputId("admin");
						uspVo.setChannelId(channelNm);
						uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);

						lProesult += mapper.insertUseStatProgram(uspVo);
						errsb.append("시리즈:["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+vodStr+"]\r\n");
					}
				}else{
					UseStatProVo uspVo = new UseStatProVo();
					uspVo.setPcSeq(-1);
					uspVo.setPSeq(-1);
					uspVo.setProType("PPM");
					uspVo.setComNm("KT");
					uspVo.setChargeYn("Y");

					uspVo.setAmount(amount);
					uspVo.setTotalAmount(""+(CastUtil.toInt(amount) * CastUtil.toInt(chargeCnt)));
					uspVo.setChargeCnt(chargeCnt);

					uspVo.setViewDate(viewDateStr);
					uspVo.setInputId("admin");

					uspVo.setChannelId(channelNm);
					uspVo.setTempData(orgStr+"|"+titleStr+"|"+dateStr);

					lProesult += mapper.insertUseStatProgram(uspVo);

					errsb.append("["+orgStr+"]:["+replaceStr+"]:["+titleStr+"]:["+dateStr+"]\r\n");
					System.out.println(orgStr+":"+replaceStr+":"+titleStr);
				}
			}
		}

		logger.debug("end-validCnt:"+(validCnt)+":"+lProesult);
		logger.debug("end-size : "+(bookInfoArray2.size()));
		logger.debug("end:"+(validCnt/bookInfoArray2.size() *100+"%"));

	}
}
