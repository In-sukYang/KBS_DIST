<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" />
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
		<script type="text/javascript" src="/resources/js/fusioncharts.theme.fint.js"></script>
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
					<li><a>${sessionScope.userNm}</a></li>
					<c:choose>
						<c:when
							test="${sessionScope.loginId ne null && sessionScope.loginId ne ''}">
							<li><a href="/logout">로그아웃</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="/">로그아웃</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			<div class="nav">
				Home &gt; 매체별현황 &gt; <span>실시간 방송</span>
			</div>
			<div id="contents">
				<div class="content-bx">
					<h3 class="depth2">매체별 현황-실시간 방송 조회</h3>
					<div class="search-bx">
						<ul>
							<li><span>01</span><label for="sch-date">기간선택</label>
								<ul>
									<li><input type="text" id="searchValue" class="wx110" readonly="readonly" /></li>
									</ul>
								</li>
								<li><span>02</span><label for="sch-divis">구분선택</label>
									<ul>
										<li>
											<select id="ageType" title="구분" class="wx110">
												<option value="01">연령전체</option>
												<option value ="02" >20대미만</option>
												<option value ="03" >20대</option>
												<option value ="04" >30대</option>
												<option value ="05" >40대</option>
												<option value ="06" >50대</option>
											</select>
										</li>
										<li>
											<select id="sexGubun" title="성별선택" class="wx110">
												<option value="">성별전체</option>
												<option value ="F" >여자</option>
												<option value ="M" >남자</option>
											</select>
										</li>
										<li>
											<select id="indexGubun" title="채널선택" class="wx110" onchange="changeDiv(this.value);">
												<option value="01">기준-채널</option>
 												<option value="02">기준-지표</option>
											</select>
										</li>
									</ul>
								</li>
								<li><span>03</span>
									<ul>
										<li id="channelList" style="display:;">
											<span >채널</span> 
											<c:if test="${!empty channleList}">
												<c:forEach items="${channleList}" var="list" varStatus="status">
													<input type="checkbox" id="channel${status.index}" name="channel" checked="checked" value="${list.codeId}"><label for="channel${status.index}"><span></span>${list.codeNm}</label>
												</c:forEach>
												
											</c:if>
										</li>
										<li id="indexList" style="display:none;">
											<span >기준지표</span> 
											<input type="checkbox" id="index01" name="index" checked="checked" value="01"><label for="index01"><span></span>pooq Live</label>
											<input type="checkbox" id="index02" name="index" checked="checked" value="02"><label for="index02"><span></span>시청률</label>
										</li>
									</ul></li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>조회</span></a>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth2 type02">매체별 현황 차트 – 실시간 방송</h3>
						<div class="grap-bx gr-type03" id="grArea">
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
			var maxRate;
			var maxCnt;
			var grapList = new Array();
			var chartData = new Array();
			var chartCategory = new Array();
			
			$( "#searchValue" ).datepicker({
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
		    $( "#searchValue" ).val($.datepicker.formatDate('yy-mm-dd', new Date()));
		    
			function search(){
				
				var searchType = $("#searchType").val();
				var searchValue = $("#searchValue").val();
				var searchValue2 = "";
				var ageType = $("#ageType").val();
				var sexGubun = $("#sexGubun").val();
				var indexGubun = $("#indexGubun").val();
				
				var channel = new Array();
				var index = new Array();
				var indexCheck;
				
				if(indexGubun=="01"){
				 	$(":checkbox[name='channel']:checked").each(function(pi,po){
				 		channel.push(po.value);
					});
				 	
				 	if(channel.length < 1) {
				 		alert('채널을 1개이상 선택해주세요');
				 		return;
				 	}
				}
			 	
				if(indexGubun=="02"){
				 	$(":checkbox[name='index']:checked").each(function(pi,po){
				 		index.push(po.value);
				 		console.log("po.value:"+po.value);
				 		indexCheck = po.value;
					});
				 	
				 	if(index.length < 1) {
				 		alert('지표구분값을 1개이상 선택해주세요');
				 		return;
				 	}
				}
			 	
			 	
			 	$.ajaxSettings.traditional = true
				$.ajax({
					type :	"get",
					url  : 	"/media/getLiveBroadTotalTopList",
					datatype : "json",
					async: true,
					data : {
						searchType 	: searchType,
						searchValue	: searchValue,
						ageType : ageType,
						channel : channel,
						sexGubun : sexGubun,
						indexGubun : indexGubun
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						grapList = new Array();
						var key = Object.keys(result);
						$("#grArea").empty();
						maxRate = result.maxRate;
						maxCnt = result.maxCnt;
						console.log("key.length:"+key.length);
						if(key.length > 0) {
							for(var k=0; k < key.length ; k++) {
								if(key[k] != 'mediaParamVo') {
									var data = result[key[k]];
									var channelNm;
									if(key[k]=="TOTALINDEX"){
										if(index.length==2){
											$("#grArea").append(" <div class='gr-area' > "       
													+ "<p class='title'>pooq</p> "
													+ "<div class='graph' style='height:280px' id='grapDiv1'> "
													+ " </div> "
													+ " </div> "
													+ " <div class='gr-area' > "       
													+ "<p class='title'>시청률</p> "
													+ "<div class='graph' style='height:280px' id='grapDiv2'> "
													+ " </div> "
													+ " </div> "
											);
											updateChart2(data,1);
											updateChart2(data,2);
										}else{
											if(indexCheck=="01"){
												$("#grArea").append(" <div class='gr-area' > "       
														+ "<p class='title'>pooq</p> "
														+ "<div class='graph' style='height:280px' id='grapDiv1'> "
														+ " </div> "
														+ " </div> "
												);
												updateChart2(data,1);
											}else{
												$("#grArea").append(" <div class='gr-area' > "       
														+ "<p class='title'>시청률</p> "
														+ "<div class='graph' style='height:280px' id='grapDiv2'> "
														+ " </div> "
														+ " </div> "
												);
												updateChart2(data,2);
											}
										}
									}else{
										if(key[k]=="11") channelNm="KBS 1TV";
										if(key[k]=="12") channelNm="KBS 2TV";
										if(key[k]=="13") channelNm="MBC";
										if(key[k]=="14") channelNm="SBS";
										if(data.length > 0){					
											$("#grArea").append(" <div class='gr-area' > "       
													+ "<p class='title'>" +channelNm+ "</p> "
													+ "<div class='graph' style='height:280px' id='grapDiv"+ k +"'> "
													+ " </div> "
													+  " </div> "
											);
											updateChart(data,k, key[k]);
										}
									}
								}
							}
						}else {
							$("#grArea").empty();
						}
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	

			}
			
			$("#searchBtn").click(function () {
				search();	
			});
			
			function updateChart(data, index, broad) {
				chartCategory = new Array();
			   			
				var dataset = new Array();
				var datasetCnt = new Array();
				var dataValueCnt = new Array();
				
				var datasetRate = new Array();
				var dataValueRate = new Array();
				
				for(var i=0; i < data.length; i++ ){
			   		chartCategory.push( { label : data[i].broadDate } );
			   	   	dataValueCnt.push({ value : eval(data[i].totalVcnt) });
			   	   	dataValueRate.push({ value : data[i].totalRate });
			   }
				
				datasetCnt.push({ seriesname : "시청수", 
										"renderAs": "line",
						                "showValues": "0",
										data : dataValueCnt });
										
				datasetCnt.push({ seriesname : "시청률", 
										"renderAs": "line",
						                "parentYAxis": "S",
						                "showValues": "0",
										data : dataValueRate }  );
				
			    var fusioncharts = new FusionCharts({
				    type: 'zoomlinedy',
				    renderAt: 'grapDiv'+index,
				    dataFormat: 'json',
				    width : "100%",
				    dataSource: {
					    "chart": {
				            "xAxisname": "시청시간",
		                    "pYAxisName": "시청수",
		                    "sYAxisName": "시청률",
		                    "pYAxisMaxValue" : maxCnt,
		                    "sYAxisMaxValue" : maxRate,
		                    "labelDisplay": "rotate", 
		                    "slantLabels": "1",
		                    "baseFontSize": "11",
		                    "drawCrossLine": "1",
		                    "crossLineColor": "#0d0d0d",
		                    "crossLineAlpha": "100",
		                    "sNumberSuffix" : "%",
		                    "formatNumberScale": "0",
		                    "drawAnchors": "1",
		                    "theme": "fint",
				            "exportEnabled" :"1",
				            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
				            "exportFileName": "실시간_방송_조회"+broad
				        },
				        "categories": [
            			{
               				 "category": chartCategory
           				 }
          				],
				         "dataset":datasetCnt
				    }
				});
			    fusioncharts.render();
 			}
			
			function updateChart2(chartData,index) {
				chartCategory = new Array();
				var dataset = new Array();
				var datasetCnt = new Array();
				var dataValueCnt = new Array();
				
				var datasetRate = new Array();
				var dataValueRate = new Array();
				
				for(var i=0; i < chartData.length; i++ ){
			   		chartCategory.push( { label : chartData[i].broadDate } );
			   	   	dataValueCnt.push({ value : chartData[i].totalVcnt });
			   	   	dataValueRate.push({ value : chartData[i].totalRate });
			   	}
				if(index==1){
					datasetCnt.push({ seriesname : "pooq", 
											"renderAs": "line",
							                "showValues": "0",
											data : dataValueCnt });
				}
				if(index==2){						
					datasetCnt.push({ seriesname : "시청률", 
											"renderAs": "line",
							                "showValues": "0",
											data : dataValueRate }  );
				}
				
				var maxCntStr ="";
				 if(index==1){
					 maxCntStr= maxCnt;
                 }else{
                	 maxCntStr= maxRate;
                 }
				
			    var fusioncharts = new FusionCharts({
				    type: 'msline',
				    renderAt: 'grapDiv'+index,
				    dataFormat: 'json',
				    width : "100%",
				    dataSource: {
					    "chart": {
				            "xAxisname": "시청시간",
		                   	"pYAxisMaxValue" : maxCntStr,
		                    "labelDisplay": "rotate", 
		                    "slantLabels": "1",
		                    "baseFontSize": "11",
		                    "drawCrossLine": "1",
		                    "crossLineColor": "#0d0d0d",
		                    "crossLineAlpha": "100",
		                    "sNumberSuffix" : "%",
		                    "formatNumberScale": "0",
		                    "drawAnchors": "0",
		                    "theme": "fint",
				            "exportEnabled" :"1",
				            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
				            "exportFileName": "실시간_방송_조회"
				        },
				        "categories": [
            			{
               				 "category": chartCategory
           				 }
          				],
				         "dataset":datasetCnt
				    }
				});
			    fusioncharts.render();
			}
		});
		
		function changeDiv(val){
	    	if(val == "01") {
				$('#channelList').show();
				$('#indexList').hide();
			} else {
				$('#channelList').hide();
				$('#indexList').show();
			}
	    }
	</script>
	
</html>