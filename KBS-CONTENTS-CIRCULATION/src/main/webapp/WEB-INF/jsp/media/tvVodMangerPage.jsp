<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		<link rel="stylesheet" href="/resources/css/jquery-ui.css">
		<script type="text/javascript" src="/resources/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/resources/js/common.js"></script>
		<script type="text/javascript" src="/resources/js/jquery-ui.js"></script>
		<script type="text/javascript" src="/resources/js/fusioncharts.js"></script>
		<script type="text/javascript" src="/resources/js/fusioncharts.theme.fint.js?cacheBust=56"></script>
		<script type="text/javascript" src="/resources/js/jquery.blockUI.js"></script>
		<!--[if lte IE 8]>
			<script type="text/javascript" src="/resources/js/html5.js"></script>
		<![endif]-->
	</head>
	<body>
		<div id="wrap">
			<!-- 상단 시작 -->
			<div id="hd">
				<jsp:include page="../include/left.jsp" flush="true" />
			</div>
			<!-- 상단 끝 -->
			<!-- 콘텐츠 시작  -->
			<div id="container">
				<div class="tnb">
					<h2 class="sound_only">상단메뉴</h2>
					<ul>
						<li><a >${sessionScope.userNm}</a></li>
						<c:choose> 
							<c:when test="${sessionScope.loginId ne null && sessionScope.loginId ne ''}" > 
								<li><a href="/logout">로그아웃</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/">로그인</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div class="nav">
					Home &gt; 매체별현황 &gt; <span>TV VOD</span>
				</div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth2">매체별 현황-TV VOD 조회</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="sch-date">기간선택</label>
									<ul>
										<li>
											<input type="text" id="searchValue" class="wx110" /> ~ <input type="text" id="searchValue2" class="wx110" title="편상일자 선택" />
										</li>
									</ul>
								</li>
								<li><span>02</span><label for="sch-sort">정렬선택</label>
									<ul>
										<li>
											<input type="radio" id="proType01" name="proType" value="PPM" checked="checked"><label for="proType01"><span></span>PPM</label>
											<input type="radio" id="proType02" name="proType" value="PPV"><label for="proType02"><span></span>PPV</label>
										</li>
										<li>
											<label for="sch-divis" class="mr10">구분</label> 
											<input type="radio" id="programSummaryType01" name="programSummaryType" value="01" checked="checked"><label for="programSummaryType01"><span></span>프로그램별</label>
											<input type="radio" id="programSummaryType02" name="programSummaryType" value="02"><label for="programSummaryType02"><span></span>단일회차별</label>
										</li>
									</ul>
								</li>
								<li><span>03</span>
									<ul>
										<li>
											<span>사업자</span> 
											<c:if test="${!empty distributionList}">
												<c:forEach items="${distributionList}" var="list" varStatus="status">
													<c:if test="${list.codeNm != 'pooq'}">
														<input type="checkbox" id="buisn${status.index}" checked="checked" name="buisn" value="${list.codeId}"><label for="buisn${status.index}"><span></span>${list.codeNm}</label>
													</c:if>
												</c:forEach>
											</c:if>
										</li>
									</ul>
								</li>
								<li><span>04</span>
									<ul>
										<li>
											<span >채널</span> 
											<c:if test="${!empty channleList}">
												<c:forEach items="${channleList}" var="list" varStatus="status">
													<input type="checkbox" id="channel${status.index}" checked="checked" name="channel" value="${list.codeId}"><label for="channel${status.index}"><span></span>${list.codeNm}</label>
												</c:forEach>
											</c:if>
													
										</li>
									</ul>
								</li>
								<li>
									<ul>
										<li>
											<span></span>
											<label><span class="new-text2">※ 기간이 주간단위(시작:월요일 ~ 종료:일요일)인 경우에만 KT 데이터가 반영됩니다.</span></label>
										</li>
									</ul>
								</li>
							</ul>
							<div id="searchBtn"><a href="#" class="btn-submit" ><span>조회</span></a></div>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth2 type02">매체별 현황 차트 – TV VOD</h3>
						<div class="grap-bx gr-type02">
							<p class="title">TV VOD 순위</p>
							<div class="gr-area">
								<div class="graph" id="chart-container">
								</div>
								<div style="text-align:center;vertical-align: top; ">
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#1AAF5D;margin-top:15px;"></a>&nbsp;&nbsp;KBS 1TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#0075C2;margin-top:15px;"></a>&nbsp;&nbsp;KBS 2TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F45B00;margin-top:15px;"></a>&nbsp;&nbsp;MBC
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F2C500;margin-top:15px;"></a>&nbsp;&nbsp;SBS
								</div>
							</div>
						</div>
						<div class="btn-area">
							<ul>
								<li><a href="#" class="btn-sort"><span>정렬조건 상위 20</span></a></li>
							</ul>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth2 type02">매체별 현황 데이터 – TV VOD</h3>
						<div class="tbl-bxn scroll-x">
							<table summary="시청률, pooq 실시간, pooq VOD, TV VOD, SMR 클립 집계 정보를 제공합니다 " id= "tableGrid">
								<caption>종합현황 집계</caption>
								<thead>
									<tr>
										<th scope="col" width="55">No</th>
										<th scope="col" width="349" id="programText">프로그램</th>
										<th scope="col" width="106" id="vodcnt">회차</th>
										<th scope="col" width="106" id="broadDate">방영일</th>
										<th scope="col" width="106">채널</th>
										<th scope="col" width="106">SKB</th>
										<th scope="col" width="106">KT Olleh</th>
										<th scope="col" width="106">LG U+</th>
										<th scope="col" width="106">CVOD</th>
										<th scope="col" width="106">합계</th>
									</tr>
								</thead>
								<tbody >
									<tr> 
										<td colspan='8'> 검색된 내용이 없습니다. </td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="btn-area">
							<ul>
								<li><a href="#" class="btn-sort"><span>정렬조건 상위 20</span></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- 콘텐츠 끝 -->
		</div>
		<!-- 하단 시작 -->
		<div id="ft">
			<div class="footer">COPYRIGHT(C) KBS. LTD. ALL RIGHTS RESERVED.</div>
		</div>
		<!-- 하단 끝 -->
	</body>
	<div id="image_container"></div>
	
	<script>
		$(document).ready(function() {
			var list = new Array();
			var chartData = new Array();
			
			var fusioncharts = new FusionCharts({
			    type: 'column2d',
			    renderAt: 'chart-container',
			    dataFormat: 'json',
			    width : "100%",
			    dataSource: {
			        "chart": {
			            "xAxisName": "",
			            "yAxisName": "조회수",
			            "formatNumberScale": "0",
			            "labelDisplay": "rotate",
			            "slantLabels": "1",	
			            "theme": "fint",
			            "exportEnabled" :"1",
			            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
			            "exportFileName": "TVVODTOP20"

			        },

			        "data": chartData
			    }
			});
			
			$( "#searchValue, #searchValue2" ).datepicker({
				showOn: 'button',
		        buttonImage: '/resources/img/btn/Icon_calendar.png',
		        dateFormat: 'yy-mm-dd',
		        prevText: '이전 달',
		        nextText: '다음 달',
		        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
		        showMonthAfterYear: true,
		        firstDay: 1,
		        minDate : '${dayInfo.minDay}',
		        maxDate : '${dayInfo.maxDay}',
		    });
		
			var today = new Date();
			var startDate = new Date(today);
			
			startDate.setDate(startDate.getDate()-7);
			
			var day = startDate.getDay();
			var k =1;
			if(day != 0 && day !=1 && day  < 7 ) {
				for(k; k<day; k++) {}
				startDate.setDate(startDate.getDate() - k + 1);
			}else if(day == 0){
				k = 6;
				startDate.setDate(startDate.getDate() - k);
			}else if(day == 1) {
				k = 0;
			}
			
			var endDate = new Date(startDate);
			endDate.setDate(startDate.getDate() + 5);
			
			
			$( "#searchValue" ).val($.datepicker.formatDate('yy-mm-dd', startDate));
			$( "#searchValue2" ).val($.datepicker.formatDate('yy-mm-dd', endDate));
			
			$( "#searchValue" ).attr("readonly", true);
			$( "#searchValue2" ).attr("readonly", true);
			
			$("#vodcnt").hide();
			$("#broadDate").hide();
			
			function search(){
				
				var searchType = "03";
				var searchValue = $("#searchValue").val();
				var searchValue2 = $("#searchValue2").val();
				
				var dateRange = calDateRange(searchValue, searchValue2);
				var start = new Date(searchValue);
				var end	= new Date(searchValue2);
				
				var searchValueReplace = searchValue.replace(/-/gi, '');
				var searchValue2Replace = searchValue2.replace(/-/gi, '');
				
				if(searchValueReplace > searchValue2Replace) {
					alert('시작일이 종료일보다 클 수 없습니다.');
					return;
				}
				
				var proType = $(":input:radio[name=proType]:checked").val();
				var programSummaryType = $(":input:radio[name=programSummaryType]:checked").val();
				
				var cSeq = new Array(); 
				var channel = new Array();
			 	$(":checkbox[name='buisn']:checked").each(function(pi,po){
			 		if(po.value == '15') {
			 			for(var k = 50 ; k < 60; k++) {
			 				cSeq.push(k);
			 			}
			 		}else if(po.value == '12'){
			 			if(start.getDay() == 1 && end.getDay() ==0 ){
			 				cSeq.push(po.value);
			 			}
			 		}else {
			 			cSeq.push(po.value);
			 		} 
				});
				
			 	$(":checkbox[name='channel']:checked").each(function(pi,po){
			 		channel.push(po.value);
				});
			 	
			 	if(channel.length < 1 ) {
			 		alert('채널을 선택해주세요');
			 		return;
			 	}
			 	
			 	if(cSeq.length < 1 ) {
			 		alert('사업자를 선택해주세요');
			 		return;
			 	}
			 	$.ajaxSettings.traditional = true;
				$.ajax({
					type :	"get",
					url  : 	"/media/getTvVodList",
					datatype : "json",
					async: true,
					data : {
						searchType 	: searchType,
						searchValue	: searchValue,
						searchValue2 : searchValue2,
						proType		 : proType,
						programSummaryType : programSummaryType,
						cSeq : cSeq,
						channel : channel
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						list = result.tvList;
						$( '#tableGrid > tbody').empty();
						chartData = new Array();
						
						
						if(list.length > 0){
							for(var k =0 ; k< list.length; k++){
								var programNm = "";
								var channelNm = "";
								if(programSummaryType == "01"){
									programNm = list[k].programNm;
									channelNm = list[k].channelNm;
									if(channelNm.indexOf("MBC")>-1){
										chartData.push( {"label" : programNm , "value" : list[k].totalCharge , "color": "F45B00","showLabel":1} );
									}else if(channelNm.indexOf("SBS")>-1){
										chartData.push( {"label" : programNm , "value" : list[k].totalCharge , "color": "F2C500","showLabel":1} );
									}else if(channelNm.indexOf("KBS 2TV")>-1){
										chartData.push( {"label" : programNm , "value" : list[k].totalCharge , "color": "0075C2","showLabel":1} );
									}else{
										chartData.push( {"label" : programNm , "value" : list[k].totalCharge , "color": "1AAF5D","showLabel":1} );
									}
									
									
									$("#vodcnt").hide();
									$("#broadDate").hide();
									$('#tableGrid > tbody:last ').append(
											"<tr> "
												+"<td width='55'>"+(k+1)+"</td>"
												+"<td width='349'>"+programNm+"</td>"
												+"<td width='106'>"+list[k].channelNm+"</td>"
												+"<td width='106'>"+AddComma(list[k].sk)+"</td>"
												+"<td width='106'>"+AddComma(list[k].kt)+"</td>"
												+"<td width='106'>"+AddComma(list[k].lg)+"</td>"
												+"<td width='106'>"+AddComma(list[k].cvod)+"</td>"
												+"<td>"+AddComma(list[k].totalCharge)+"</td>"
											+"</tr>" );
								}else{
									programNm = list[k].programNm ;
									
									if(channelNm.indexOf("MBC")>-1){
										chartData.push( {"label" : list[k].programNm + " (" + list[k].vodcnt+") ", "value" : list[k].totalCharge, "color": "F45B00","showLabel":1} );
									}else if(channelNm.indexOf("SBS")>-1){
										chartData.push( {"label" : list[k].programNm + " (" + list[k].vodcnt+") ", "value" : list[k].totalCharge, "color": "F2C500","showLabel":1} );
									}else if(channelNm.indexOf("KBS 2TV")>-1){
										chartData.push( {"label" : list[k].programNm + " (" + list[k].vodcnt+") ", "value" : list[k].totalCharge, "color": "0075C2","showLabel":1} );
									}else{
										chartData.push( {"label" : list[k].programNm + " (" + list[k].vodcnt+") ", "value" : list[k].totalCharge, "color": "1AAF5D" ,"showLabel":1} );
									}
									
									$("#vodcnt").show();
									$("#broadDate").show();
									$('#tableGrid > tbody:last ').append(
											"<tr>"
											+"<td width='55'>"+(k+1)+"</td>"
											+"<td width='349'>"+programNm+"</td> " 
											+"<td width='106'>"+list[k].vodcnt+"</td>"
											+"<td width='106'>"+list[k].broadDate+"</td>  "
											+"<td width='106'>"+list[k].channelNm+" </td>"
											+"<td width='106'>"+AddComma(list[k].sk)+"</td>"
											+"<td width='106'>"+AddComma(list[k].kt)+"</td>"
											+"<td width='106'>"+AddComma(list[k].lg)+"</td>"
											+"<td width='106'>"+AddComma(list[k].cvod)+"</td>"
											+"<td>"+AddComma(list[k].totalCharge)+"</td> "
										+"</tr>" );
								}
							}
							
							var awidth = $(".tbl-bxn thead").width();
							var bwidth = $(".tbl-bxn thead tr th").last().width() - "18";
							$(".tbl-bxn tbody").css("width", awidth);
							$(".tbl-bxn tbody tr td").last().css("width", bwidth);
							
							updateChart();
						 	fusioncharts.render();
						}else {
							if(programSummaryType == "01"){
								$('#tableGrid > tbody:last ').append("<tr> <td colspan='8'> 검색된 내용이 없습니다. </td></tr>");	
							}else {
								$('#tableGrid > tbody:last ').append("<tr> <td colspan='10'> 검색된 내용이 없습니다. </td></tr>");
							}
							
							$("#chart-container").empty();
						}
						
					},
					error:function(request,status,error){
						console.log("request.responseText:"+request.responseText);
			      		alert("code:"+request.status+"\n"+"message:시스템에 오류가 있습니다. 다시 조회해보시기 바랍니다.\n"+"error:"+error);
			     	}
				});
			 	
			 	
			}
			
			$("#searchBtn").click(function () {
				search();	
			});
			
			function updateChart() {
			    var jsonData = {
			        
			        "chart": {
			            "xAxisName": "",
			            "yAxisName": "조회수",
			            "width" : "900",
			            "formatNumberScale": "0",
			            "labelDisplay": "rotate",
			            "slantLabels": "1",	
			            "baseFontSize": "11",
			            "showValues": "0",
			            "drawAnchors":"0",
	                    "drawCrossLine": "1", 
	                    "crossLineColor": "#0d0d0d", 
	                    "crossLineAlpha": "100",
	                    "theme": "fint",
			            "exportEnabled" :"1",
			            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
			            "exportFileName": "TV_VOD_TOP20"
			        },

			        "data": chartData
			    };

			    fusioncharts.setJSONData(jsonData);
			}
			
			function AddComma(data_value) {
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
			
			function calDateRange(val1, val2) {
		        var FORMAT = "-";

		        // 년도, 월, 일로 분리
		        var start_dt = val1.split(FORMAT);
		        var end_dt = val2.split(FORMAT);

		        // 월 - 1(자바스크립트는 월이 0부터 시작하기 때문에...)
		        // Number()를 이용하여 08, 09월을 10진수로 인식하게 함.
		        start_dt[1] = (Number(start_dt[1]) - 1) + "";
		        end_dt[1] = (Number(end_dt[1]) - 1) + "";

		        var from_dt = new Date(start_dt[0], start_dt[1], start_dt[2]);
		        var to_dt = new Date(end_dt[0], end_dt[1], end_dt[2]);
		        
		        return (to_dt.getTime() - from_dt.getTime()) / 1000 / 60 / 60 / 24;
		    }
			
		});
	</script>
</html>