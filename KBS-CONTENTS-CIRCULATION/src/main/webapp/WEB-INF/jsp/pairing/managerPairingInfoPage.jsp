<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
				<div class="nav">Home &gt; 편성일별 현황 &gt; <span>편성일별 현황</span></div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth3">편성일별 현황 조회</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="sch-date">편성일자</label>
									<ul>
										<li><input type="text" id="searchValue" class="wx110" readonly="readonly" /></li>
										<li><label for="addDay" class="mr10">수집기간</label>
										  <select id="addDay" class="wx110">
												<option value="1" >방송후 1일</option>
												<option value="2" >방송후 2일</option>
												<option value="3" >방송후 3일</option>
												<option value="4" >방송후 4일</option>
												<option value="5" >방송후 5일</option>
												<option value="6" >방송후 6일</option>
												<option value="7" >방송후 7일</option>
												
											</select>
										</li>
									</ul>
								</li>
								<li><span>02</span>
									<ul>
										<li><span class="ml15">채널</span>
											<c:if test="${!empty channleList}">
												<c:forEach items="${channleList}" var="list" varStatus="status">
													<input type="radio" id="channel${status.index}" name="channel" value="${list.codeId}"><label for="channel${status.index}"><span></span>${list.codeNm}</label>
												</c:forEach>
											</c:if>
										</li>
									</ul>
									<p class="new-text" style="color:gray">※ TV VOD의 경우 PPM만 반영하며, KT 데이터는 제외됩니다.</p>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>조회</span></a>
						  <a href="#" class="btn-submit2" id="excelBtn"><span>엑셀저장</span></a>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth3 type02">편성일별 현황 데이터</h3>
					  	<div class="tbl-bx scroll-x scroll-y">
							<table summary="방송시간, 프로그램명, 회차, 시청률, pooq VOD, TV VOD, SMR 클립 정보를 제공합니다." id="tableGrid">
								<caption>편성일별 현황 데이터</caption>
								<colgroup>
									<col width="140" />
									<col width="" />
									<col width="100" />
									<col width="100" />
									<col width="145" />
									<col width="145" />
									<col width="145" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col" width="140">방송시간</th>
										<th scope="col" width="189">프로그램명</th>
										<th scope="col" width="100">회차</th>
										<th scope="col" width="100">시청률</th>
										<th scope="col" width="145">pooq VOD</th>
										<th scope="col" width="145">TV VOD</th>
										<th scope="col" width="145">SMR 클립</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan=7>검색된 내용이 없습니다.</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				  <div class="content-bx">
						<h3 class="depth3 type02">편성일별 현황 차트</h3>
						<div class="grap-bx gr-type02">
							<div class="gr-area">
								<p class="title">시청률</p>
								<div class="graph1" id='graph1'>
									
								</div>
							</div>
						  
						  	<div class="gr-area">
								<p class="title">VOD</p>
								<div class="graph" id='graph2'>
									
								</div>
							</div>
						  	<div class="gr-area">
								<p class="title">SMR 클립</p>
								<div class="graph" id='graph4'>
									
								</div>
							</div>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth3 type02">편성일별 현황 합계</h3>
						<div class="tbl-bx2">
							<table summary="시청률, pooq 실시간, pooq VOD, TV VOD, SMR 클립 집계 정보를 제공합니다" id="tableGrid2">
								<caption>종합현황 집계</caption>
								<colgroup>
									<col width="" />
									<col width="190" />
									<col width="190" />
									<col width="190" />
									<col width="190" />
								</colgroup>
								<thead>
									<tr>
										<th rowspan="2" scope="row">집계</th>
										<th scope="col">시청률</th>
										<th scope="col">pooq VOD</th>
										<th scope="col">TV VOD</th>
										<th scope="col">SMR 클립</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan = 4>검색된 내용이 없습니다.</td>
									</tr>
								</tbody>
							</table>
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
		var viewRatingData = new Array();
		var pooqVodData = new Array();
		var tvVodData = new Array();
		var clipData = new Array();
		$(document).ready(function() {
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
		    $("input[name=channel]").eq(0).attr("checked", true);
		    
			$("#excelBtn").click(function() {
				
				var searchValue = $("#searchValue").val();
		    	var addDay = $("#addDay").val();
		    	var channel = $(":input:radio[name=channel]:checked").val();
		    	
		    	searchValue = searchValue.replace(/-/gi, '');
				
				location.href = '/pairing/downloadExcelFile?searchValue='+searchValue+'&addDay='+addDay+'&channel='+channel;
			});
		    
		    function search() {
		    	
		    	var searchValue = $("#searchValue").val();
		    	var addDay = $("#addDay").val();
		    	var channel = $(":input:radio[name=channel]:checked").val();
		    	
		    	searchValue = searchValue.replace(/-/gi, '');
		    	
		    	$.ajax({
					type :	"get",
					url  : 	"/pairing/getPairingInformationList",
					datatype : "json",
					async: true,
					data : {
						searchValue	: searchValue,
						addDay : addDay,
						channel : channel
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						var list = result.list;
						pooqVCntData = new Array();
						viewRatingData = new Array();
						pooqVodData = new Array();
						tvVodData = new Array();
						clipData = new Array();
						$( '#tableGrid > tbody').empty();
						$( '#tableGrid2 > tbody').empty();
						var viewRating = 0;
						var pooqVod = 0;
						var tvVod = 0;
						var clip = 0;
						var category = new Array();
						if(list.length > 0 && list != null ) {
							for(var k =0 ; k< list.length; k++){
								$('#tableGrid > tbody:last ').append("<tr> <td width='140'>  "+list[k].broadTime+"</td>  <td width='189'>" 
										+ list[k].programNm+"</td>  <td width='100'>"+list[k].vodcnt+"</td>   <td width='100'>"
										+ list[k].viewRating+"</td>  <td width='145'>"+AddComma(list[k].pooqVod)+"</td>   <td width='145'>"
										+ AddComma(list[k].tvVod)+"</td>  <td width='145'>"+AddComma(list[k].clipCnt)+"</td>    </tr>" 
								);
								
								viewRating += list[k].viewRating;
								pooqVod += list[k].pooqVod;
								tvVod  += list[k].tvVod;
								clip  += list[k].clipCnt;
								
								category.push({ label : list[k].programNm  });
								
								//viewRatingData.push({ label : list[k].programNm, value : list[k].viewRating  });
								viewRatingData.push({ value : list[k].viewRating  });
								pooqVCntData.push({ value : list[k].pooqVcnt  });
								pooqVodData.push( {value : list[k].pooqVod  });
								tvVodData.push( { value : list[k].tvVod  });
								clipData.push( { label : list[k].programNm, value : list[k].clipCnt  });
								
							}
							
							updateChart3(category,viewRatingData,pooqVCntData, 1);
							//updateChart(viewRatingData , 1);
							updateChart2(category, tvVodData , pooqVodData, 2);
							updateChart(clipData , 4);
							
							$('#tableGrid2 > tbody:last ').append("<tr> <td >  </td>  <td class='bdr-l1'>" 
									+ Math.floor(viewRating/list.length *100)/100+"</td>  <td >"+AddComma(pooqVod)+"</td>   <td >"
									+ AddComma(tvVod)+"</td>  <td >"+AddComma(clip)+"</td>    </tr>" 
							);
							
							
						}else {
							$('#tableGrid > tbody:last ').append("<tr> <td colspan='7'> 검색된 내용이 없습니다. </td></tr>");
							$('#tableGrid2 > tbody:last ').append("<tr> <td colspan='6'> 검색된 내용이 없습니다. </td></tr>");
							
							$("#graph1").empty();
							$("#graph2").empty();
							$("#graph4").empty();
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
			
			function updateChart(data, index) {
				
				var exportFileName = "";
				if(index == 1) {
					exportFileName = '시청률';
				}else if(index == 4) {
					exportFileName = 'SMR_클립';
				}
				
			    var jsonData = {
			        
			        "chart": {
			            "xAxisName": "프로그램",
			            "width" : "100%",
			            "formatNumberScale": "0",
			            "labelDisplay": "rotate", 
	                    "slantLabels": "1",
	                    "baseFontSize": "11",
	                    "drawAnchors":"0",
	                    "drawCrossLine": "1", 
	                    "crossLineColor": "#0d0d0d", 
	                    "crossLineAlpha": "100",
			            "theme": "fint",
			            "exportEnabled" :"1",
			            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
			            "exportFileName": "편성일별"+exportFileName
			        },
			        data : data
			    };
			    
			    var fusioncharts = new FusionCharts({
				    type: 'line',
				    renderAt: 'graph'+index,
				    dataFormat: 'json',
				    width : "100%",
				    dataSource: {
				        "chart": {
				            "xAxisName": "프로그램",
				            "formatNumberScale": "0",
				            "labelDisplay": "rotate", 
		                    "slantLabels": "1",
		                    "baseFontSize": "11",
		                    "drawAnchors":"0",
		                    "drawCrossLine": "1", 
		                    "crossLineColor": "#0d0d0d", 
		                    "crossLineAlpha": "100",
				            "theme": "fint",
				            "exportEnabled" :"1",
				            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
				            "exportFileName": "편성일별"+exportFileName
				            
				        }
				        
				    }
				});
			    
			    fusioncharts.setJSONData(jsonData);
			    fusioncharts.render();
 			}
			
			function updateChart2(category, data1, data2, index) {
			    var jsonData = {
			        
			        "chart": {
			            "xAxisName": "프로그램",
			            "pYAxisName": "TV VOD",
	                    "sYAxisName": "pooq VOD",
			            "width" : "100%",
			            "labelDisplay": "rotate", 
	                    "slantLabels": "1",
	                    "baseFontSize": "11",
	                    "drawAnchors":"0",
	                    "drawCrossLine": "1", 
	                    "crossLineColor": "#0d0d0d", 
	                    "crossLineAlpha": "100",
			            "theme": "fint",
			            "formatNumberScale": "0",
			            "exportEnabled" :"1",
			            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
			            "exportFileName": "편성일별 VOD"
			        },
			        categories : [{
			        	category : category
			        		
			        	
			        }],
			        dataset : [{
			        	seriesname : "TV VOD",
			        	"renderAs": "line",
		                "showValues": "0",
			        	data : data1
			        }, {
			        	seriesname : "pooq VOD",
			        	"renderAs": "line",
		                "parentYAxis": "S",
		                "showValues": "0",
				        data : data2
			        }]
			    };
			    
			    var fusioncharts = new FusionCharts({
				    type: 'mscombidy2d',
				    renderAt: 'graph'+index,
				    dataFormat: 'json',
				    width : "100%",
				    dataSource: {
				        "chart": {
				            "xAxisName": "프로그램",
				            "formatNumberScale": "0",
				            "labelDisplay": "rotate", 
		                    "slantLabels": "1",
		                    "baseFontSize": "11",
		                    "drawAnchors":"0",
		                    "drawCrossLine": "1", 
		                    "crossLineColor": "#0d0d0d", 
		                    "crossLineAlpha": "100",
				            "theme": "fint",
				            "exportEnabled" :"1",
				            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
				            "exportFileName": "편성일별 VOD"
				            
				        }
				        
				    }
				});
			    
			    fusioncharts.setJSONData(jsonData);
			    fusioncharts.render();
 			}
			
			function updateChart3(category, data1, data2, index) {
			    var jsonData = {
			        
			        "chart": {
			        	"xAxisName": "프로그램",
			            "pYAxisName": "시청률",
	                    "sYAxisName": "pooq Live",
			            "width" : "100%",
			            "labelDisplay": "rotate", 
	                    "slantLabels": "1",
	                    "baseFontSize": "11",
	                    "drawAnchors":"0",
	                    "drawCrossLine": "1", 
	                    "crossLineColor": "#0d0d0d", 
	                    "crossLineAlpha": "100",
			            "theme": "fint",
			            "formatNumberScale": "0",
			            "exportEnabled" :"1",
			            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
			            "exportFileName": "편성일별 VOD"
			        },
			        categories : [{
			        	category : category
			        		
			        	
			        }],
			        dataset : [{
			        	seriesname : "시청률",
			        	"renderAs": "line",
		                "showValues": "0",
			        	data : data1
			        }, {
			        	seriesname : "pooq Live",
			        	"renderAs": "line",
		                "parentYAxis": "S",
		                "showValues": "0",
				        data : data2
			        }]
			    };
			    
			    var fusioncharts = new FusionCharts({
				    type: 'mscombidy2d',
				    renderAt: 'graph'+index,
				    dataFormat: 'json',
				    width : "100%",
				    dataSource: {
				        "chart": {
				            "xAxisName": "프로그램",
				            "formatNumberScale": "0",
				            "labelDisplay": "rotate", 
		                    "slantLabels": "1",
		                    "baseFontSize": "11",
		                    "drawAnchors":"0",
		                    "drawCrossLine": "1", 
		                    "crossLineColor": "#0d0d0d", 
		                    "crossLineAlpha": "100",
				            "theme": "fint",
				            "exportEnabled" :"1",
				            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
				            "exportFileName": "편성일별 VOD"
				            
				        }
				        
				    }
				});
			    
			    fusioncharts.setJSONData(jsonData);
			    fusioncharts.render();
 			}
			
		});
	</script>
</html>