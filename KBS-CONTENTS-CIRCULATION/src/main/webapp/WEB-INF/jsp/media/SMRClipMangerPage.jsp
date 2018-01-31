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
					Home &gt; 매체별현황 &gt; <span>SMR클립</span>
				</div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth2">매체별 현황-SMR 클립 조회</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="sch-date">기간선택</label>
									<ul>
										<li><input type="text" id="searchValue" class="wx110" /> ~ <input type="text" id="searchValue2" class="wx110" title="편상일자 선택" /></li>
									</ul>
								</li>
								<li><span>02</span>
									<ul>
										<li>
											<span >채널</span> 
												<input type="checkbox" id="channel01" name="channel" checked="checked" value="11"><label for="channel01"><span></span>KBS 1TV</label>
												<input type="checkbox" id="channel02" name="channel" checked="checked" value="12"><label for="channel02"><span></span>KBS 2TV</label>
										</li>
									</ul>
								</li>
								<li><span>03</span><label for="sch-sort">구분</label>
									<ul>
										<li>
											<input type="radio" id="programSummaryType01" name="programSummaryType" value="01" checked="checked"><label for="programSummaryType01"><span></span>프로그램별</label>
											<input type="radio" id="programSummaryType02" name="programSummaryType" value="02"><label for="programSummaryType02"><span></span>단일회차별</label>
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>조회</span></a>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth2 type02">매체별 현황 차트 – SMR 클립</h3>
						<div class="grap-bx gr-type02">
							<p class="title">SMR 클립 순위</p>
							<div class="gr-area">
								<div class="graph" id="chart-container">
								</div>
								<div style="text-align:center;vertical-align: top; ">
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#1AAF5D;margin-top:15px;"></a>&nbsp;&nbsp;KBS 1TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#0075C2;margin-top:15px;"></a>&nbsp;&nbsp;KBS 2TV
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
						<h3 class="depth2 type02">매체별 현황 데이터 – SMR 클립</h3>
						<div class="tbl-bxn scroll-x">
							<table summary="시청률, pooq 실시간, pooq VOD, TV VOD, SMR 클립 집계 정보를 제공합니다" id=tableGrid>
								<caption>종합현황 집계</caption>
								<thead>
									<tr>
										<th scope="col">프로그램명</th>
										<th scope="col">조회수</th>
									</tr>
								</thead>
								<tbody>
									
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
	<script>
		$(document).ready(function() {
			var clipList = new Array();
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
			
			function search(){
				
				var searchType = "03"
				var searchValue = $("#searchValue").val();
				var searchValue2 = $("#searchValue2").val();
				var programSummaryType = $(":input:radio[name=programSummaryType]:checked").val();
				var searchValueReplace = searchValue.replace(/-/gi, '');
				var searchValue2Replace = searchValue2.replace(/-/gi, '');
				
				if(searchValueReplace > searchValue2Replace) {
					alert('시작일이 종료일보다 클 수 없습니다.');
					return;
				}
				
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
					url  : 	"/media/getSmrClipTopList",
					datatype : "json",
					async: true,
					data : {
						searchType 	: searchType,
						searchValue	: searchValue,
						searchValue2 : searchValue2,
						channel : channel,
						programSummaryType : programSummaryType
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						var list = result.clipList;
						chartData = new Array();
						$( '#tableGrid > tbody').empty();
						$( '#tableGrid > thead').empty();
						
						if(programSummaryType == '01') {
							$('#tableGrid > thead:last ').append("<tr> <th scope='col' width='300'>프로그램명</th> "   
								+ "<th scope='col' width= ''>조회수</th>  </tr>" );
						}else {
							$('#tableGrid > thead:last ').append("<tr> <th scope='col' width='300'>프로그램명</th> "
									+ "<th scope='col' width= '100'>회차</th>  "
									+ "<th scope='col' width= ''>조회수</th>  </tr>" );
						}
						var channelNm="";
						var colorVal="";
						if(list.length > 0){
							
							if(programSummaryType == '01') {
								for(var k =0 ; k< list.length; k++){
									
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
									
									$('#tableGrid > tbody:last ').append("<tr> <td width='300'>"+list[k].programNm+"</td>  <td >" 
											+AddComma(list[k].viewCnt)+"</td>  </tr>" );
									chartData.push( {"label" : list[k].programNm, "value" : list[k].viewCnt, "color":colorVal} );
								}
							}else {
								
								for(var k =0 ; k< list.length; k++){
									
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
									
									$('#tableGrid > tbody:last ').append("<tr> <td width=300>"+list[k].programNm+"</td>  <td width=100>" 
											+ list[k].vodcnt +"</td> <td > "
											+AddComma(list[k].viewCnt)+"</td>  </tr>" );
									chartData.push( {"label" : list[k].programNm +  "("+list[k].vodcnt + ")", "value" : list[k].viewCnt, "color":colorVal} );
								}
							}
							updateChart();
						 	fusioncharts.render();
						}else {
							$("#chart-container").empty();
							if(programSummaryType == '01') {
								$('#tableGrid > tbody:last ').append("<tr> <td colspan='2'> 검색된 내용이 없습니다. </td></tr>");
							} else {
								$('#tableGrid > tbody:last ').append("<tr> <td colspan='3'> 검색된 내용이 없습니다. </td></tr>");
							}
						}
						
					},
					error:function(request,status,error){
						alert("현재 시스템 사용량이 많습니다. 다시 조회해 주시기 바랍니다");
			      		//console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			 	
			}
			
			$("#searchBtn").click(function () {
				search();	
			});
			
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
			            "exportFileName": "SMR_클립_TOP20"
			        },

			        "data": chartData
			    };

			    fusioncharts.setJSONData(jsonData);
			}
			
		});
		
		</script>
</html>