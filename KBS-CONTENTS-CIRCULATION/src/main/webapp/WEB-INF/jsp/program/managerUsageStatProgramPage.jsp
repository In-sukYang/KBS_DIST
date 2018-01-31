<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date"/>
<!doctype html>
<html lang="ko">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<title>KBS 유통정보시스템</title>
		<link rel="stylesheet" type="text/css" href="/resources/css/base.css" />
		<link rel="stylesheet" type="text/css" href="/resources/css/layout.css" />
		<link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		<script type="text/javascript" src="/resources/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/resources/js/common.js"></script>
		<script type="text/javascript" src="/resources/js/jquery-ui.js"></script>
		<script type="text/javascript" src="/resources/js/time.js"></script>
		<script type="text/javascript" src="/resources/js/jquery.blockUI.js"></script>
		<!--[if lte IE 8]>
		<script type="text/javascript" src="/resources/js/html5.js"></script>
		<![endif]-->
	</head>
	<body>
		<div id="wrap">
			<!-- 상단 시작 -->
			<div id="hd">
				<jsp:include page="../include/left.jsp" flush="false"/>
			</div>
			<!-- 상단 끝 -->
		    <!-- 콘텐츠 시작  -->
			<div id="container">
				<div class="tnb">
					<h2 class="sound_only">상단메뉴</h2>
					<ul>
						<li><a href="/">로그아웃</a></li>
					</ul>
				</div>
				<div class="nav">Home &gt; 프로그램관리  &gt; <span>프로그램이용현황</span></div>
				<div id="contents">
					<div class="content-bx" id="searchContents">
						<h3 class="depth10">프로그램이용현황</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="sch-date">기간선택</label>
									<ul>
										<li>
											<select title="월별 선택" class="wx110" id="searchType">
												<option value="01">월별</option>
												<option value="02">일별</option>
												<option value="03">주별</option>
											</select>
										</li>
										<li>
											<select title="년도 선택" class="wx110" id="searchYear">
												<c:if test="${!empty yearList}">
													<c:forEach items="${yearList}" var="list" varStatus="status">
														<option value ="${list}" <c:if test="${list == (now.year + 1900)}">selected</c:if> >${list}</option>
													</c:forEach>
												</c:if>
												<c:if test="${empty yearList}">
													<option value="${now.year+1900}">${now.year+1900}</option>
												</c:if>
											</select> <span>년</span>
										</li>
										<li>
											<select title="월 선택" class="wx110" id="searchMonth">
												<c:forEach var="i" begin="1" end="12" step="1" >
													<option value="<fmt:formatNumber value="${i}" minIntegerDigits="2"/>" <c:if test="${i == now.month + 1}">selected</c:if> > ${i}</option>
												</c:forEach>
											</select> <span>월</span>
										</li>
										<li id="searchDayLi">
											<select title="일 선택" class="wx110" id="searchDay">
											</select> <span>일</span>
										</li>
										<li id="searchWeekLi" >
											<select title="주 선택" class="wx110" id="searchWeek">
												
											</select> <span>주</span>
										</li>
									</ul>
								</li>
								<li><span>02</span><label for="sch-opt">검색조건</label>
									<ul>
										<li>
											<select id="searchType2" class="wx140">
												<option value= "01">프로그램명</option>
												<option value= "02">회별프로그램명</option>
												
											</select>
										</li>
										<li><input type="text" id="searchValue3" class="wx200" title="회별프로그램명" /></li>
									</ul>
								</li>
								<li><span>03</span>
									<ul>
										<li><span class="wx90 txt-l">프로그램타입</span>
											<input type="radio" checked="checked" id="opt01" name =proType value=""><label for="opt01"><span></span>전체</label>
											<input type="radio" id="opt02" name =proType value="PPM"> <label for="opt02"><span></span>PPM</label>
											<input type="radio" id="opt03" name =proType value="PPV"><label for="opt03"><span></span>PPV</label>
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>검색</span></a>
						</div>
					</div>
					<div class="content-bx" id="listContents">
						<h3 class="depth10 type02">프로그램리스트</h3>
						<div class="tbl-bx2">
							<table summary="순번, 방송사, 프로그램명, 방송일자, 유무료, 시청건수(구매건수), 건당금액, 제공사, 가입자수 정보를 제공합니다" id="tableGrid">
								<caption>원본 프로그램리스트</caption>
								<colgroup>
									<col width="67" />
									<col width="78" />
									<col width="" />
									<col width="114" />
									<col width="78" />
									<col width="128" />
									<col width="90" />
<%-- 									<col width="80" /> --%>
									<col width="95" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">방송사</th>
										<th scope="col">프로그램명</th>
										<th scope="col">방송일자</th>
										<th scope="col">유무료</th>
										<th scope="col">시청건수(구매건수)</th>
										<th scope="col">건당금액</th>
<!-- 										<th scope="col">제공사</th> -->
										<th scope="col">가입자수</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
						<div class="paging">
							
						</div>
						<div class="btn-set">
							<a href="#" class="btn-user-add"><span>이용현황 추가</span></a>
						</div>
					</div>
					<div class="content-bx" id="saveContents">
						<h3 class="depth10">프로그램 이용현황 프로그램 상세</h3>
						<div class="tbl-write">
							<table summary="">
								<input type="hidden" id="uspSeq">
								<caption>글 작성</caption>
								<tbody>
									<tr>
										<th scope="row"><label for="oProgramNm">원본 프로그램명</label></th>
										<td>
											<input type="hidden" id="opSeq"  />
											<input type="text" id="oProgramNm" class="wx340" />
											<a href="#" class="btn-sch" id="settingOriProgramBtn">
												<span>원본프로그램 찾기</span>
											</a>
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="pr-turn">회별프로그램</label></th>
										<td>
											<input type="hidden" id="pcSeq">
											<input type="text" id="contentsNm" class="wx340" />
											<a href="#" class="btn-sch" id="contentsSearchBtn">
												<span>회별 프로그램 찾기</span>
											</a>
										</td>
										
									</tr>
									<tr>
										<th scope="row"><label for="vodcnt">회차</label></th>
										<td><input type="text" id="vodcnt" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="viewDate">시청일자</label></th>
										<td>
											<input type="text" id="viewDate" class="wx340">
										</td>
												
									</tr>
									<tr>
										<th scope="row"><label for="proType">컨텐츠 유형</label></th>
										<td>
											<select id=proTypeList class="wx340">
												<c:if test="${!empty proTypeList}">
													<c:forEach items="${proTypeList}" var="list" varStatus="status">
														<option value ="${list.codeId}"  >${list.codeNm}</option>
													</c:forEach>
												</c:if>
											</select>
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="screenType">스크린 유형</label></th>
										<td><input type="text" id="screenType" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="chargeYn">유/무료</label></th>
										<td>
											<select id=chargeYn class="wx340" >
												<option value="Y">유료</option>
												<option value="N">무료</option>
											</select>
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="amount">건당금액</label></th>
										<td><input type="text" id="amount" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="totalAmount">총 매출액</label></th>
										<td><input type="text" id="totalAmount" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="chargeCnt">구매건수(시청건수)</label></th>
										<td><input type="text" id="chargeCnt" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="hChargeCnt">유료 시청건수(초고화질)</label></th>
										<td><input type="text" id="hChargeCnt" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="freeChargeCnt">무료 시청건수</label></th>
										<td><input type="text" id="freeChargeCnt" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="hFreeChargeCnt">무료 시청건수(초고화질)</label></th>
										<td><input type="text" id="hFreeChargeCnt" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="freeChargeVtime">무료 시청시간</label></th>
										<td><input type="text" id="freeChargeVtime" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="hFreeChargeVtime">무료 시청시간(초고화질)</label></th>
										<td><input type="text" id="hFreeChargeVtime" class="wx340" /></td>
									</tr>
									
									<tr>
										<th scope="row"><label for="chargeVtime">유료 시청시간</label></th>
										<td><input type="text" id="chargeVtime" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="hChargeVtime">유료 시청시간(초고화질)</label></th>
										<td><input type="text" id="hChargeVtime" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="channelId">채널정보</label></th>
										<td>
											<select id=channelId class="wx340">
												<c:if test="${!empty channelList}">
													<c:forEach items="${channelList}" var="list" varStatus="status">
														<option value ="${list.codeId}"  >${list.codeNm}</option>
													</c:forEach>
												</c:if>
											</select>
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="tempData">기타데이터</label></th>
										<td><input type="text" id="tempData" class="wx340" /></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="board-btn-set">
							<a href="#" class="change" id="saveBtn"><span id="saveBtnText">변경</span></a>
							<a href="#" class="cancle" id="cancleBtn"><span>취소</span></a>
						</div>
					</div>
					
				</div>
			</div>    
			<!-- 콘텐츠 끝 -->
		</div>
<!-- 하단 시작 -->
		<div id="ft">
			<div class="footer">
				COPYRIGHT(C) KBS. LTD. ALL RIGHTS RESERVED.
			</div>
		</div>
<!-- 하단 끝 -->
	</body>
	<script>
	
		var modifyDiv = new Array();	
		var programList = new Array();
		var paging = new Array();
		var AddComma = "";
		$(document).ready(function() {
			
			$("#searchDayLi").hide();
			$("#searchWeekLi").hide();
			
			$("#searchContents").show();
			$("#listContents").show();
			$("#saveContents").hide();
			
			$("#searchType").change(function() {
				
				var type = $("#searchType").val();
				var year = $("#searchYear").val();
				var month = $("#searchMonth").val();
				
				if(type == '01') {
					$("#searchDayLi").hide();
					$("#searchWeekLi").hide();
				}else if(type == '02') {
					
					makeDay();
					
					$("#searchDayLi").show();
					$("#searchWeekLi").hide();
				}else if(type == '03') {
					makeWeek();
					
					$("#searchDayLi").hide();
					$("#searchWeekLi").show();
				}
			});
			
			$("#contentsSearchBtn").click(function() {
				var searchValue = $("#contentsNm").val();
				
				if(searchValue == '' && searchValue.length <2 ){
					alert("검색어를 2자이상 입력해주세요");
					return;
				}
				var url = "/program/layer/contentLayer?searchType=01&searchValue="+searchValue;
				window.open(url, 'contents', 'width=1100, height=600, scrollbars=yes' );
			});
			
			$("#settingOriProgramBtn").click(function() {
				var searchValue = $("#oProgramNm").val();
				
				if(searchValue == '' && searchValue.length <2 ){
					alert("검색어를 2자이상 입력해주세요");
					return;
				}
				var url = "/program/layer/orgProgramLayer?searchType=02&searchValue="+searchValue;
				window.open(url, 'orgProgram', 'width=1100, height=600, scrollbars=yes' );
			});
			
			$("#searchBtn").click(function () {
				search();	
			});
			
			$("#cancleBtn").click(function() {
				
				$("#searchContents").show();
				$("#listContents").show();
				$("#saveContents").hide();
				iniSaveDiv();
			});
			
			$("#searchYear").change(function(){
				makeDay();
				makeWeek();
				
			});
			
			$("#searchMonth").change(function(){
				makeDay();
				makeWeek();
			});
			
			$( "#viewDate" ).datetimepicker({
				//	format: 'DDMMYYYY HH:mm:ss'
				dateFormat: 'yy-mm-dd', 
		        timeFormat: 'HH:mm:ss'
		    });
			
			function search(){
				
				var searchType = $("#searchType").val();
				var searchType2 = $("#searchType2").val();
				var searchValue = "";
				var searchValue2 = "";
				var searchValue3 = $("#searchValue3").val();
				var proType = $(":input:radio[name=proType]:checked").val();
				
				var year = $("#searchYear").val();
				var month = $("#searchMonth").val();
				var day = $("#searchDay").val();
				var week = $("#searchWeek").val();
				
				if(searchType == "01") {
					searchValue = year+"-"+month;
				}else if(searchType == "02") {
					searchValue = year+"-"+month+"-"+day;
				}else if(searchType == "03") {
					searchValue = year+"-"+month+"-"+week.substring(0,2);
					searchValue2 = year+"-"+month+"-"+week.substring(2,4);
				}
				
				 
			 	$.ajaxSettings.traditional = true
				$.ajax({
					type :	"get",
					url  : 	"/program/getUsageStatProgramList",
					datatype : "json",
					async: true,
					data : {
						searchType 	: searchType,
						searchValue	: searchValue,
						searchValue2 : searchValue2,
						searchType2 	: searchType2,
						searchValue3	: searchValue3,
						proType		 : proType
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						programList = result.programList;
						paging = result.param;
						makeTable();
						
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});
			 	$("#tableGrid tbody tr").css('cursor', 'pointer');
			 	$("#tableGrid tbody tr").click(function() {
					var uspSeq = $(this).children().eq(0).attr('data');
					modifyDiv(uspSeq);
				});
			}
			
			function makeDay(){
				var type = $("#searchType").val();
				if(type == '02') {
					var year = $("#searchYear").val();
					var month = $("#searchMonth").val();
					
					day = new Date(year, month, 0).getDate();
					
					$("#searchDay option").remove();
					
					for(var k =1 ; k<=day; k++){
						if(k < 10) {
							$("#searchDay").append(" <option value='0"+k+"'>"+k+" ");
						}else {
							$("#searchDay").append(" <option value='"+k+"'>"+k+" ");	
						}
					}
				}	
			}
			
			function makeWeek(){
				var type = $("#searchType").val();
				
				if(type == '03'){
					var year = $("#searchYear").val();
					var month = $("#searchMonth").val();
					
					$.ajax({
						type :	"get",
						url  : 	"/comm/getWeekDateList",
						datatype : "json",
						async: false,
						data : {
							year 	: year,
							month	: month 
						},
						success : function(result){
							var data = result.weekList;
							$("#searchWeek option").remove();
							
							for(var k =0 ; k<data.length; k++){
								var startDate 	= data[k].startDate;
								var endDate		= data[k].endDate;
								
								if(startDate < 10){
									startDate = "0"+startDate;
								}
								
								if(endDate < 10){
									endDate = "0"+endDate;
								}
								
								var date = startDate+""+endDate;
								
								$("#searchWeek").append(" <option value='"+date+"'>"+data[k].week+" ");
							}
							
						},
						error:function(request,status,error){
				      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				     	}
					});	
					
			    }
			}
			
			modifyDiv = function (uspSeq){
				iniSaveDiv();
				showSaveDiv();
				for(var k = 0; k < programList.length; k++ ) {
					if(programList[k].uspSeq == uspSeq) {

						$("#saveBtnText").html("변경")
						
						$("#uspSeq").val(programList[k].uspSeq);
						$("#opSeq").val(programList[k].opSeq);
						$("#oProgramNm").val(programList[k].oprogramNm);

						$("#pcSeq").val(programList[k].pcSeq);
						$("#contentsNm").val(programList[k].contentsNm);
						$("#vodcnt").val(programList[k].vodcnt);
						$("#viewDate").val(programList[k].viewDate);
						$("#proType").val(programList[k].proType);
						
						$("#screenType").val(programList[k].screenType);
						$("#chargeYn").val(programList[k].chargeYn);
						
						$("#amount").val(programList[k].amount);
						$("#totalAmount").val(programList[k].totalAmount);
						
						$("#chargeCnt").val(programList[k].chargeCnt);
						$("#hChargeCnt").val(programList[k].hchargeCnt);
						$("#chargeCnt").val(programList[k].chargeCnt);
						$("#hChargeCnt").val(programList[k].hchargeCnt);
						
						$("#freeChargeCnt").val(programList[k].freeChargeCnt);
						$("#hCFreehargeCnt").val(programList[k].hfreeChargeCnt);
						
						$("#freeChargeVtime").val(programList[k].freeChargeVtime);
						$("#hFreeChargeVtime").val(programList[k].hfreeChargeVtime);
						$("#chargeVtime").val(programList[k].chargeVtime);
						$("#hChargeVtime").val(programList[k].hchargeVtime);
							
						
						$("#channelId").val(programList[k].channelId);
						break;
					}
				}
			}
			
			function iniSaveDiv() {
				$("#saveBtnText").html("저장")
				
				$("#uspSeq").val('');
				$("#opSeq").val('');
				$("#oProgramNm").val('');

				$("#pcSeq").val('');
				$("#contentsNm").val('');
				$("#vodcnt").val('');
				$("#viewDate").val('');
				$("#proType").val('');
				
				$("#screenType").val('');
				$("#chargeYn").val('');
				
				$("#amount").val('');
				$("#totalAmount").val('');
				
				$("#chargeCnt").val('');
				$("#hChargeCnt").val('');
				$("#chargeCnt").val('');
				$("#hChargeCnt").val('');
				
				$("#freeChargeCnt").val('');
				$("#hCFreehargeCnt").val('');
				
				$("#freeChargeVtime").val('');
				$("#hFreeChargeVtime").val('');
				$("#chargeVtime").val('');
				$("#hChargeVtime").val('');
					
				$("#channelId").val('');
				
				$("#tempData").val('');
				$("#tempData").attr("readonly", "true"); 
				
			}
			
			function showSaveDiv(){
				
				$("#searchContents").hide();
				$("#listContents").hide();
				
				$("#saveContents").show();
			}
			
			AddComma = function (data_value) {
				 
			    var txtNumber = '' + data_value;    // 입력된 값을 문자열 변수에 저장합니다.
			 
			    if (isNaN(txtNumber) || txtNumber == "") {    // 숫자 형태의 값이 정상적으로 입력되었는지 확인합니다.
			        alert("숫자만 입력 하세요");
			        return;
			    }
			    else {
			        var rxSplit = new RegExp('([0-9])([0-9][0-9][0-9][,.])');    // 정규식 형태 생성
			        var arrNumber = txtNumber.split('.');    // 입력받은 숫자를 . 기준으로 나눔. (정수부와 소수부분으로 분리)
			        arrNumber[0] += '.'; // 정수부 끝에 소수점 추가
			 
			        do {
			            arrNumber[0] = arrNumber[0].replace(rxSplit, '$1,$2'); // 정수부에서 rxSplit 패턴과 일치하는 부분을 찾아 replace 처리
			        } while (rxSplit.test(arrNumber[0])); // 정규식 패턴 rxSplit 가 정수부 내에 있는지 확인하고 있다면 true 반환. 루프 반복.
			 
			        if (arrNumber.length > 1) { // txtNumber를 마침표(.)로 분리한 부분이 2개 이상이라면 (즉 소수점 부분도 있다면)
			            return arrNumber.join(''); // 배열을 그대로 합칩. (join 함수에 인자가 있으면 인자를 구분값으로 두고 합침)
			        }
			        else { // txtNumber 길이가 1이라면 정수부만 있다는 의미.
			            return arrNumber[0].split('.')[0]; // 위에서 정수부 끝에 붙여준 마침표(.)를 그대로 제거함.
			        }
			    }
			}
		});
		
		var makePaging = function (paging) {
			if (paging.finalPageNo == 1) {
				$('.paging').hide();
				$('.paging').empty();
			} else {
				$('.paging').show();
			
				$('.paging').empty();
				if (paging.finalPageNo <= 10) {
					for (var k = 1; k <= paging.finalPageNo; k++) {
						if (k == paging.currentPageNo) {
							$('.paging').append("  <strong> " + k + "   </strong>  ");
							
						} else {
							$('.paging').append("  <a href='#' onclick='searchPaging("+k+")'> " + k + "  </a>  ");
							
						}
					}
				}else {
					
					$('.paging').append("  <a href='#' class='first' onclick='searchPaging("+paging.firstPageNo+")'>처음</a>  ");
					$('.paging').append("  <a href='#' class='prev' onclick='searchPaging("+paging.prevPageNo+")'>이전</a>  ");
					for (var k = paging.startPageNo; k <= paging.endPageNo; k++) {
						if (k == paging.currentPageNo) {
							$('.paging').append("  <strong> " + k + "   </strong>  ");
						} else {
							$('.paging').append("  <a href='#' onclick='searchPaging("+k+")'> " + k + "  </a>  ");
						}
					}
					
					$('.paging').append("  <a href='#' class='next' onclick='searchPaging("+paging.nextPageNo+")'>처음</a>  ");
					$('.paging').append("  <a href='#' class='end' onclick='searchPaging("+paging.finalPageNo+")'>이전</a>  ");
				}
			}
		}
		
		
		var searchPaging = function (currPage){
			$.ajax({
				type :	"get",
				url  : 	"/program/getUsageStatProgramList",
				datatype : "json",
				async: false,
				data : {
					searchType 	: paging.searchType,
					searchValue	: paging.searchValue,
					searchValue2 : paging.searchValue2,
					searchType2 	: paging.searchType2,
					searchValue3	: paging.searchValue3,
					proType		 : paging.proType,
					currentPageNo : currPage
				},
				success : function(result){
					programList = result.programList;
					paging = result.param;
					makeTable();
					
				},
				error:function(request,status,error){
		      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     	}
			});
		 	
		}
		
		function makeTable() {
			makePaging(paging);
			$( '#tableGrid > tbody').empty();
			if(programList.length > 0){
				for(var k =0 ; k< programList.length; k++){
					var charge ="";
					if(programList[k].chargeYn == 'Y'){
						charge = "유료";
					}else{
						charge = "무료";
					}
					$('#tableGrid > tbody:last ').append("<tr> <td data='"+programList[k].uspSeq+"'>"+((k+1)+paging.offset)+"</td>  <td >" 
							+programList[k].broadNm+"</td>  <td >"+ programList[k].programNm+"</td>   <td >"
							+programList[k].broadDate+"</td>   <td w>"+ charge+"</td>   <td >"
							+AddComma(programList[k].chargeCnt)+"</td>    <td >"+AddComma(programList[k].amount)+"</td> <td>"
							+AddComma(programList[k].joinCnt)+"</td> <td>"
							+ " </tr>" )
				}
				$("#tableGrid tbody tr").click(function() {
					var uspSeq = $(this).children().eq(0).attr('data');
					modifyDiv(uspSeq);
				});
			}else {
				$('#tableGrid > tbody:last ').append("<tr> <td colspan='8'> 검색된 내용이 없습니다. </td></tr>");
			}
		}
	</script>
	
</html>