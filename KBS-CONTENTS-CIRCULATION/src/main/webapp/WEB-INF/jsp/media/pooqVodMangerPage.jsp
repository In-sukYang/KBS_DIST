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
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
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
				<jsp:include page="../include/left.jsp" flush="false" />
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
					Home &gt; 매체별현황 &gt; <span>Pooq Vod</span>
				</div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth2">매체별 현황-Pooq VOD 조회</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="sch-date">기간선택</label>
									<ul>
										<li><input type="text" id="searchValue" class="wx110" /> ~ <input type="text" id="searchValue2" class="wx110" title="편상일자 선택" /></li>
									</ul>
								</li>
								<li><span>02</span><label for="sch-sort">정렬선택</label>
									<ul>
										<li>
											<select id="orderType" title="order" class="wx140">
												<option value='09'>시청자수(합계)</option>
												<option value='10'>시청시간(합계)</option>
												<option value='05'>유료시청자수</option>
												<option value='06'>유료시청자수-초고화질</option>
												<option value='07'>무료시청자수</option>
												<option value='08'>무료시청자수-초고화질</option>
												<option value='01'>유료시청시간</option>
												<option value='02'>유료시청시간-초고화질</option>
												<option value='03'>무료시청시간</option>
												<option value='04'>무료시청시간-초고화질</option>
											</select>
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
											<span >채널</span> 
											<c:if test="${!empty channleList}">
												<c:forEach items="${channleList}" var="list" varStatus="status">
													<input type="checkbox" id="channel${status.index}" name="channel" checked="checked" value="${list.codeId}"><label for="channel${status.index}"><span></span>${list.codeNm}</label>
												</c:forEach>
											</c:if>
													
										</li>
									</ul>
								</li>
							</ul>
							<div id="searchBtn"><a href="#" class="btn-submit" ><span>조회</span></a></div>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth2 type02">매체별 현황 차트 – pooq VOD </h3>
						<div class="grap-bx gr-type02">
							<p class="title">pooq VOD 순위</p>
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
						<h3 class="depth2 type02">매체별 현황 데이터 – pooq VOD </h3>
						<div class="tbl-bxn scroll-x">
							<table id="tableGrid" summary="프로그램, 유료 시청시간, 유료시청시간(초고화질), 무료 시청시간, 무료시청시간(초고화질), 유료 시청시간, 유료시청시간(초고화질),무료 시청시간,무료시청시간(초고화질),합계 (시청자수) 정보를 제공합니다">
								<caption>종합현황 집계</caption>
								<thead>
									<tr>
										<th scope="col" width="200" id="programText">프로그램</th>
										<th scope="col" width="150">채널</th>
										<th scope="col" width="150" id="vodcnt">회차</th>
										<th scope="col" width="150" id="broadDate">방영일</th>
										<th scope="col" width="150">합계<br />(시청자수)</th>
										<th scope="col" width="150">합계<br />(시청시간)</th>
										<th scope="col" width="150">유료 시청자수</th>
										<th scope="col" width="150">유료 시청자수<br />(초고화질)</th>
										<th scope="col" width="150">무료<br />시청자수</th>
										<th scope="col" width="150">무료 시청자수<br />(초고화질)</th>
										<th scope="col" width="150">유료<br />시청시간 (시간)</th>
										<th scope="col" width="150">유료시청시간<br />(초고화질) (시간)</th>
										<th scope="col" width="150">무료<br />시청시간 (시간)</th>
										<th scope="col" width="150">무료시청시간<br />(초고화질) (시간)</th>
									</tr>
								</thead>
								<tbody>
									<tr> 
										<td colspan='12'> 검색된 내용이 없습니다. </td>
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
			<div class="footer">
				COPYRIGHT(C) KBS. LTD. ALL RIGHTS RESERVED.
			</div>
		</div>
		<!-- 하단 끝 -->
	</body>
	<script>
		$(document).ready(function() {
			var chartData = new Array();
			
			var fusioncharts = new FusionCharts({
			    type: 'column2d',
			    renderAt: 'chart-container',
			    width : "100%",
			    dataFormat: 'json',
			    dataSource: {
			        "chart": {
			            "xAxisName": "",
			            "yAxisName": "조회수",
			            "formatNumberScale": "0",
			            "labelDisplay": "rotate",
			            "slantLabels": "1",	
			            "theme": "fint"
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
				
				var searchType = "03"
				var searchValue = $("#searchValue").val();
				var searchValue2 = $("#searchValue2").val();
				var programSummaryType = $(":input:radio[name=programSummaryType]:checked").val();
				var orderType = $("#orderType").val();
				
				searchValue = searchValue.replace(/-/gi, '');
				searchValue2 = searchValue2.replace(/-/gi, '');
				
				if(searchValue > searchValue2) {
					alert('시작일이 종료일보다 클 수 없습니다.');
					return;
				}
				
				var cSeq = new Array(); 
				var channel = new Array();
				
			 	$(":checkbox[name='channel']:checked").each(function(pi,po){
			 		channel.push(po.value);
				});
			 	
			 	if(channel.length < 1 ) {
			 		alert('채널을 선택해주세요');
			 		return;
			 	}
			 	
			 	$.ajaxSettings.traditional = true
				$.ajax({
					type :	"get",
					url  : 	"/media/getPooqVodList",
					datatype : "json",
					async: true,
					data : {
						searchType 	: searchType,
						searchValue	: searchValue,
						searchValue2 : searchValue2,
						programSummaryType : programSummaryType,
						channel : channel,
						orderType : orderType
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						var list = result.pooqList;
						chartData = new Array();
						
						$( '#tableGrid > tbody').empty();
						if(list.length > 0){
							for(var k =0 ; k< list.length; k++){
								var programNm = "";
								var channelNm = "";
								var colorVal = "";
								var totalCnt = 0;
								var totalView = 0.00;
								if(programSummaryType == "01"){
									
									$("#vodcnt").hide();
									$("#broadDate").hide();
									
									programNm = list[k].programNm;
									
									totalCnt = list[k].chargeCnt + list[k].hchargeCnt + list[k].freeChargeCnt +list[k].hfreeChargeCnt;
									totalView = list[k].chargeVtime + list[k].hchargeVtime + list[k].freeChargeVtime +list[k].hfreeChargeVtime;
									
									$('#tableGrid > tbody:last ').append("<tr>"
											+"<td width='200' class='subject'>"+programNm+"</td>"
											+"<td width='150'> "+  list[k].channelNm +" </td>"
											+"<td width='150'>"	+AddComma(totalCnt) + " </td>"
											+"<td width='150'>"+humanReadable(totalView)+" </td>"
											+"<td width='150'>"+AddComma(list[k].chargeCnt)+" </td>"
											+"<td width='150'>"+AddComma(list[k].hchargeCnt)+"</td>" 
											+"<td width='150'>"+AddComma(list[k].freeChargeCnt)+" </td>"
											+"<td width='150'>"+AddComma(list[k].hfreeChargeCnt)+"</td>"
											+"<td width='150'>"+humanReadable(list[k].chargeVtime)+"</td>"
											+"<td width='150'>"+humanReadable(list[k].hchargeVtime)+"</td>"
											+"<td width='150'>"+humanReadable(list[k].freeChargeVtime)+"</td>"
											+"<td>"+humanReadable(list[k].hfreeChargeVtime)+"</td>"
											+ "</tr>" 
									);
								}else{
									$("#vodcnt").show();
									$("#broadDate").show();
									
									programNm = list[k].programNm + " (" + list[k].vodcnt+" )";
									
									totalCnt = list[k].chargeCnt + list[k].hchargeCnt + list[k].freeChargeCnt +list[k].hfreeChargeCnt;
									totalView = list[k].chargeVtime + list[k].hchargeVtime + list[k].freeChargeVtime +list[k].hfreeChargeVtime;
									
									$('#tableGrid > tbody:last ').append("<tr>"
											+"<td width='200' class='subject'>" +list[k].programNm+"</td>"
											+"<td width='150'> "+  list[k].channelNm +" </td>"
											+"<td width='150'> "+  list[k].vodcnt +" </td>"
											+"<td width='150'>"+  list[k].broadDate +" </td> "
											+"<td width='150'>"+AddComma(totalCnt) + " </td>"
											+"<td width='150'>"+humanReadable(totalView)+" </td>"
											+"<td width='150'>"+AddComma(list[k].chargeCnt)+" </td>"
											+"<td width='150'>"+AddComma(list[k].hchargeCnt)+"</td>"
											+"<td width='150'>"+AddComma(list[k].freeChargeCnt)+" </td>"
											+"<td width='150'>"+AddComma(list[k].hfreeChargeCnt)+"</td>"
											+"<td width='150'>"+humanReadable(list[k].chargeVtime)+"</td>"
											+"<td width='150'>"+humanReadable(list[k].hchargeVtime)+"</td>"
											+"<td width='150'>"+humanReadable(list[k].freeChargeVtime)+"</td>"
											+"<td>"+humanReadable(list[k].hfreeChargeVtime)+"</td>"
											+ "  </tr>" 
									);
								}
								
								var awidth = $(".tbl-bxn thead").width();
								var bwidth = $(".tbl-bxn thead tr th").last().width() - "18";
								$(".tbl-bxn tbody").css("width", awidth);
								$(".tbl-bxn tbody tr td").last().css("width", bwidth);
								
								
								channelNm = list[k].channelNm;
								if(channelNm.indexOf("MBC")>-1){
									colorVal ="F45B00";
								}else if(channelNm.indexOf("SBS")>-1){
									colorVal ="F2C500";
								}else if(channelNm.indexOf("KBS 2TV")>-1){
									colorVal ="0075C2";
								}else{
									colorVal ="1AAF5D";
								}
								
								if(orderType == '01') {
									chartData.push( {"label" : programNm, "value" : list[k].chargeVtime ,"color": colorVal } );	
								}else if(orderType == '02'){
									chartData.push( {"label" : programNm, "value" : list[k].hchargeVtime  ,"color": colorVal } );	
								}else if(orderType == '03'){
									chartData.push( {"label" : programNm, "value" : list[k].freeChargeVtime  ,"color": colorVal } );	
								}else if(orderType == '04'){
									chartData.push( {"label" : programNm, "value" : list[k].hfreeChargeVtime  ,"color": colorVal } );	
								}else if(orderType == '05'){
									chartData.push( {"label" : programNm, "value" : list[k].chargeCnt  ,"color": colorVal } );	
								}else if(orderType == '06'){
									chartData.push( {"label" : programNm, "value" : list[k].hchargeCnt ,"color": colorVal  } );	
								}else if(orderType == '07'){
									chartData.push( {"label" : programNm, "value" : list[k].freeChargeCnt ,"color": colorVal  } );	
								}else if(orderType == '08'){
									chartData.push( {"label" : programNm, "value" : list[k].hfreeChargeCnt  ,"color": colorVal } );	
								}else if(orderType == '09'){
									chartData.push( {"label" : programNm, "value" :  totalCnt ,"color": colorVal } );
								}else if(orderType == '10'){
									chartData.push( {"label" : programNm, "value" : totalView ,"color": colorVal  } );
								}
							}
							updateChart();
						 	fusioncharts.render();
						}else {
							$("#chart-container").empty();
							if(programSummaryType == "01") {
								$('#tableGrid > tbody:last ').append("<tr> <td colspan='12'> 검색된 내용이 없습니다. </td></tr>");
							}else {
								$('#tableGrid > tbody:last ').append("<tr> <td colspan='14'> 검색된 내용이 없습니다. </td></tr>");
							}
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
			            "width" : "100%",
			            "formatNumberScale": "0",
			            "labelDisplay": "rotate",
			            "slantLabels": "1",	
			            "baseFontSize": "11",
			            "showValues": "0",
			            "theme": "fint",
			            "exportEnabled" :"1",
			            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
			            "exportFileName": "POOQ_VOD_TOP20"
			        },

			        "data": chartData
			    };

			    fusioncharts.setJSONData(jsonData);
			}
			
		});
	</script>	
</html>