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
				<jsp:include page="../include/left.jsp" flush="false"/>
			</div>
			<!-- 상단 끝 -->
		    <!-- 콘텐츠 시작  -->
			<div id="container">
				<div class="tnb">
					<h2 class="sound_only">상단메뉴</h2>
					<ul>
						<li><a >${sessionScope.userNm}</a></li>
						<li><a href="/">로그아웃</a></li>
					</ul>
				</div>
				<div class="nav">Home &gt; 종합현황  &gt; <span>프로그램 순위</span></div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth4">프로그램 순위</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="sch-date">기간선택</label>
									<ul>
										<li><input type="text" id="searchValue" class="wx110" /> ~ <input type="text" id="searchValue2" class="wx110" title="편상일자 선택" /></li>
									</ul>
									<p class="new-text" style="color:gray; margin-left:40px;">※ TV VOD의 경우 PPM만 반영하며, KT 데이터는 제외됩니다.</p>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>조회</span></a>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth2 type02">주요지표</h3>
						<div class="grap-bx gr-type02">
							<p class="title">TV VOD 순위</p>
							<div class="gr-area">
								<div class="graph" id="chart-container1">
								</div>
								<div style="text-align:center;vertical-align: top; ">
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#1AAF5D;margin-top:15px;"></a>&nbsp;&nbsp;KBS 1TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#0075C2;margin-top:15px;"></a>&nbsp;&nbsp;KBS 2TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F45B00;margin-top:15px;"></a>&nbsp;&nbsp;MBC
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F2C500;margin-top:15px;"></a>&nbsp;&nbsp;SBS
								</div>
							</div>
						</div>
						
						<div class="grap-bx gr-type02">
							<p class="title">pooq 실시간 순위</p>
							<div class="gr-area">
								<div class="graph" id="chart-container2">
								</div>
								<div style="text-align:center;vertical-align: top; ">
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#1AAF5D;margin-top:15px;"></a>&nbsp;&nbsp;KBS 1TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#0075C2;margin-top:15px;"></a>&nbsp;&nbsp;KBS 2TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F45B00;margin-top:15px;"></a>&nbsp;&nbsp;MBC
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F2C500;margin-top:15px;"></a>&nbsp;&nbsp;SBS
								</div>
							</div>
						</div>
						
						<div class="grap-bx gr-type02">
							<p class="title">TV VOD 순위</p>
							<div class="gr-area">
								<div class="graph" id="chart-container3">
								</div>
								<div style="text-align:center;vertical-align: top; ">
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#1AAF5D;margin-top:15px;"></a>&nbsp;&nbsp;KBS 1TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#0075C2;margin-top:15px;"></a>&nbsp;&nbsp;KBS 2TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F45B00;margin-top:15px;"></a>&nbsp;&nbsp;MBC
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F2C500;margin-top:15px;"></a>&nbsp;&nbsp;SBS
								</div>
							</div>
						</div>
						
						<div class="grap-bx gr-type02">
							<p class="title">pooq VOD 순위</p>
							<div class="gr-area">
								<div class="graph" id="chart-container4">
								</div>
								<div style="text-align:center;vertical-align: top; ">
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#1AAF5D;margin-top:15px;"></a>&nbsp;&nbsp;KBS 1TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#0075C2;margin-top:15px;"></a>&nbsp;&nbsp;KBS 2TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F45B00;margin-top:15px;"></a>&nbsp;&nbsp;MBC
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F2C500;margin-top:15px;"></a>&nbsp;&nbsp;SBS
								</div>
							</div>
						</div>
						
						<div class="grap-bx gr-type02">
							<p class="title">SMR 클립 순위</p>
							<div class="gr-area">
								<div class="graph" id="chart-container5">
								</div>
								<div style="text-align:center;vertical-align: top; ">
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#1AAF5D;margin-top:15px;"></a>&nbsp;&nbsp;KBS 1TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#0075C2;margin-top:15px;"></a>&nbsp;&nbsp;KBS 2TV
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F45B00;margin-top:15px;"></a>&nbsp;&nbsp;MBC
									<a href="#" class="pro-select" style="width:40px;height:15px;background:#F2C500;margin-top:15px;"></a>&nbsp;&nbsp;SBS
								</div>
							</div>
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
			        firstDay: 1 
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
				
				var searchType = '03';
				var searchValue = $("#searchValue").val();
				var searchValue2 = $("#searchValue2").val();
				
				var cSeq = new Array(); 
				var channel = new Array();
				
				$.ajax({
					type :	"get",
					url  : 	"/statusboard/getPointStatusBoard",
					datatype : "json",
					async: true,
					timeout: 30000,
					data : {
						searchType 	: searchType,
						searchValue	: searchValue,
						searchValue2 : searchValue2
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						var tvVodList = result.tvVodList;
						var pooqVodList = result.pooqVodList;
						var smrClipList = result.smrClipList;
						var ratingList = result.ratingList;
						var pooqLiveList = result.pooqLiveList;
						
						if(ratingList.length > 0 && ratingList != null ) {
							var chartData = new Array();
							for(var k =0 ; k< ratingList.length; k++){

								var programNm = ratingList[k].contentsNm + "(" + ratingList[k].vodcnt + ")"; 
								var channelNm = ratingList[k].channelNm;
								
								if(channelNm.indexOf("MBC")>-1){
									chartData.push( {"label" : programNm , "value" : ratingList[k].viewRating  , "color": "F45B00","showLabel":1} );
								}else if(channelNm.indexOf("SBS")>-1){
									chartData.push( {"label" : programNm , "value" : ratingList[k].viewRating  , "color": "F2C500","showLabel":1} );
								}else if(channelNm.indexOf("KBS 2TV")>-1){
									chartData.push( {"label" : programNm , "value" : ratingList[k].viewRating  , "color": "0075C2","showLabel":1} );
								}else{
									chartData.push( {"label" : programNm , "value" : ratingList[k].viewRating  , "color": "1AAF5D","showLabel":1} );
								}
							}
							updateChart(chartData, 1);
						}else {
							$("#chart-container1").empty();
						}
						
						if(pooqLiveList.length > 0 && pooqLiveList != null ) {
							
							var chartData = new Array();
							for(var k =0 ; k< pooqLiveList.length; k++){
								
								var programNm = pooqLiveList[k].contentsNm + "(" + pooqLiveList[k].vodcnt + ")"; 
								var channelNm = pooqLiveList[k].channelNm;		
								
								if(channelNm.indexOf("MBC")>-1){
									chartData.push( {"label" : programNm , "value" : pooqLiveList[k].viewRating  , "color": "F45B00","showLabel":1} );
								}else if(channelNm.indexOf("SBS")>-1){
									chartData.push( {"label" : programNm , "value" : pooqLiveList[k].viewRating  , "color": "F2C500","showLabel":1} );
								}else if(channelNm.indexOf("KBS 2TV")>-1){
									chartData.push( {"label" : programNm , "value" : pooqLiveList[k].viewRating  , "color": "0075C2","showLabel":1} );
								}else{
									chartData.push( {"label" : programNm , "value" : pooqLiveList[k].viewRating  , "color": "1AAF5D","showLabel":1} );
								}
							}
							updateChart(chartData, 2);
						}else {
							$("#chart-container2").empty();
						}
						
						
						if(tvVodList.length > 0 && tvVodList != null ) {
							
							var chartData = new Array();
							
							for(var k =0 ; k< tvVodList.length; k++){
								
								var programNm = tvVodList[k].programNm + "(" + tvVodList[k].vodcnt + ")";
								var channelNm = tvVodList[k].channelNm;		
								
								if(channelNm.indexOf("MBC")>-1){
									chartData.push( {"label" : programNm , "value" : tvVodList[k].chargeCnt  , "color": "F45B00","showLabel":1} );
								}else if(channelNm.indexOf("SBS")>-1){
									chartData.push( {"label" : programNm , "value" : tvVodList[k].chargeCnt  , "color": "F2C500","showLabel":1} );
								}else if(channelNm.indexOf("KBS 2TV")>-1){
									chartData.push( {"label" : programNm , "value" : tvVodList[k].chargeCnt  , "color": "0075C2","showLabel":1} );
								}else{
									chartData.push( {"label" : programNm , "value" : tvVodList[k].chargeCnt  , "color": "1AAF5D","showLabel":1} );
								}
							}
							updateChart(chartData, 3);
							
						}else {
							$("#chart-container3").empty();
						}
						
						if(pooqVodList.length > 0 && pooqVodList != null ) {
							
							var chartData = new Array();
							
							for(var k =0 ; k< pooqVodList.length; k++){
								
								var programNm = pooqVodList[k].programNm + "(" + pooqVodList[k].vodcnt + ")";
								var channelNm = pooqVodList[k].channelNm;	
								
								if(channelNm.indexOf("MBC")>-1){
									chartData.push( {"label" : programNm , "value" : pooqVodList[k].chargeCnt  , "color": "F45B00","showLabel":1} );
								}else if(channelNm.indexOf("SBS")>-1){
									chartData.push( {"label" : programNm , "value" : pooqVodList[k].chargeCnt  , "color": "F2C500","showLabel":1} );
								}else if(channelNm.indexOf("KBS 2TV")>-1){
									chartData.push( {"label" : programNm , "value" : pooqVodList[k].chargeCnt  , "color": "0075C2","showLabel":1} );
								}else{
									chartData.push( {"label" : programNm , "value" : pooqVodList[k].chargeCnt  , "color": "1AAF5D","showLabel":1} );
								}
							}
							updateChart(chartData, 4);
							
						}else {
							$("#chart-container4").empty();
						}
						
						if(smrClipList.length > 0 && smrClipList != null ) {
							
							var chartData = new Array();
							
							for(var k =0 ; k< smrClipList.length; k++){
								
								var programNm = smrClipList[k].programNm + "(" + smrClipList[k].vodcnt + ")";
								var channelNm = smrClipList[k].channelNm;	
								
								if(channelNm.indexOf("MBC")>-1){
									chartData.push( {"label" : programNm , "value" : smrClipList[k].viewCnt  , "color": "F45B00","showLabel":1} );
								}else if(channelNm.indexOf("SBS")>-1){
									chartData.push( {"label" : programNm , "value" : smrClipList[k].viewCnt  , "color": "F2C500","showLabel":1} );
								}else if(channelNm.indexOf("KBS 2TV")>-1){
									chartData.push( {"label" : programNm , "value" : smrClipList[k].viewCnt  , "color": "0075C2","showLabel":1} );
								}else{
									chartData.push( {"label" : programNm , "value" : smrClipList[k].viewCnt  , "color": "1AAF5D","showLabel":1} );
								}
							}
							updateChart(chartData, 5);
						}else {
							$("#chart-container5").empty();
						}
						
					},
					error:function(request,status,error){
			      		alert("현재 시스템 사용량이 많습니다. 다시 조회해 주시기 바랍니다.");
						//console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});
			}
			
			$("#searchBtn").click(function () {
				search();	
			});
			
			function updateChart(chartData, index) {
				
				var exportFileName = "";
				if(index == 1) {
					exportFileName = "시청률"
				}else if(index ==2) {
					exportFileName = 'pooqLive';
				}else if(index ==3) {
					exportFileName = 'TV VOD(PPM)';
				}else if(index ==4) {
					exportFileName = 'POOQ VOD';
				}else if(index ==5) {
					exportFileName = 'SMR 클립';
				}
				
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
			            "exportFileName": exportFileName
			        },

			        "data": chartData
			    };
			    
			    var fusioncharts = new FusionCharts({
				    type: 'column2d',
				    renderAt: 'chart-container'+ index,
				    dataFormat: 'json',
				    width : "100%",
				    dataSource: {
				        "chart": {
				            "xAxisName": "",
				            "yAxisName": "",
				            "formatNumberScale": "0",
				            "labelDisplay": "rotate",
				            "slantLabels": "1",	
				            "theme": "fint",
				            "exportEnabled" :"1",
				            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
				            "exportFileName": exportFileName

				        },

				        "data": chartData
				    }
				});
			    
			    fusioncharts.setJSONData(jsonData);
			    fusioncharts.render();
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
		});
	</script>
</html>