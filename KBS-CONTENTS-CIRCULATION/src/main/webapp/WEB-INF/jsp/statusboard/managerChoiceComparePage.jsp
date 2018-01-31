<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
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
				<div class="nav">Home &gt; 프로그램별형황 &gt; <span>선택비교</span></div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth4">프로그램별 현황- 선택비교</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="sch-pr01" class="wx100 txt-l">프로그램 선택</label>
									<ul>
										<li>
											<input type="hidden" id="pSeq1" />
											<input type="text" id="programNm1"/><a href="#" class="pro-select" id="programBtn1"><span>프로그램선택</span></a>
										</li>
									</ul>
								</li>
								<li><span>02</span><label for="sch-pr02" class="wx100 txt-l">프로그램 선택</label>
									<ul>
										<li>
											<input type="hidden" id="pSeq2" />
											<input type="text" id="programNm2" /><a href="#" class="pro-select" id="programBtn2"><span>프로그램선택</span></a>
										</li>
									</ul>
								</li>
								<li><span>03</span><label for="sch-pr03" class="wx100 txt-l">프로그램 선택</label>
									<ul>
										<li>
											<input type="hidden" id="pSeq3" />
											<input type="text" id="programNm3" /><a href="#" class="pro-select" id="programBtn3"><span>프로그램선택</span></a>
										</li>
									</ul>
								</li>
								<li><span>04</span><label for="sch-date" class="wx100 txt-l">편성일자</label>
									<ul>
										<li><input type="text" id="searchValue" class="wx110" /> ~ <input type="text" id="searchValue2" class="wx110" title="편상일자 선택" /></li>
									</ul>
								</li>
								<li>
									<ul>
										<li>
											<span></span>
											<label><span class="new-text2">※ TV VOD의 경우 PPM만 반영하며, KT 데이터는 제외됩니다.</span></label>
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn" ><span>조회</span></a>
						  <a href="#" class="btn-submit2" id="excelBtn"><span>엑셀저장</span></a>
						</div>
					</div>
				 	<div class="content-bx">
						<h3 class="depth3 type02">프로그램별 현황-선택비교 차트</h3>
						<div class="grap-bx gr-type02">
							<div class="gr-area">
								<p class="title">시청률</p>
								<div class="graph" id="chart-container1">
								</div>
							</div>
						  	 <div class="gr-area">
								<p class="title">TV VOD</p>
								<div class="graph" id="chart-container2">
								</div>
							</div>
						  	
						  	<div class="gr-area">
								<p class="title">Pooq VOD</p>
								<div class="graph" id="chart-container3">
								</div>
							</div>
						 
						  	<div class="gr-area">
								<p class="title">SMR 클립</p>
								<div class="graph" id="chart-container4">
								</div>
							</div>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth4 type02">프로그램 별 현황 – 선택 비교 데이터</h3>
						<div class="tbl-bx2">
							<table summary="시청률, pooq 실시간, pooq VOD, TV VOD, SMR 클립 집계 정보를 제공합니다" id="tableGrid">
								<caption>종합현황 집계</caption>
								<colgroup>
									<col width="130" />
									<col width="154" />
									<col width="180" />
									<col width="173" />
									<col width="160" />
									<col width="" />
								</colgroup>
								<thead>
									<tr>	
										<th scope="col">편성일자</th>
										<th scope="col" class="subject">프로그램(회차)</th>
										<th scope="col">시청률</th>
										<th scope="col">TV VOD</th>
										<th scope="col">Pooq VOD</th>
										<th scope="col">SMR 클립</th>
									</tr>
								</thead>
								<tbody>
									
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
			
		    $("#searchBtn").click(function () {
				search();	
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
		    
		    $("#programBtn1").click(function() {
		    	openPopupProgram('programBtn1', $("#programNm1").val());
			});
		    
			$("#programBtn2").click(function() {
				openPopupProgram('programBtn2', $("#programNm2").val());			
			});
						
			$("#programBtn3").click(function() {
				openPopupProgram('programBtn3', $("#programNm3").val());
			});
		    
		    function openPopupProgram(callback, searchValue) {
				
				if(searchValue == '' || searchValue.length <2 ){
					alert("검색어를 2자이상 입력해주세요");
					return;
				}
				var url = "/program/layer/parentProgramLayer?openrType=02&searchType=01&searchValue="+searchValue+"&callback="+callback;
				window.open(url, 'program', 'width=1100, height=600, scrollbars=yes' );
		    }
			
		    var programNmList = new Array();
		    
			$("#excelBtn").click(function() {
				
				var searchValue = $("#searchValue").val();
				var searchValue2 = $("#searchValue2").val();
				var dateRange = calDateRange(searchValue, searchValue2);
				
				if(dateRange > 15 ) {
					alert("15일씩 검색이 가능합니다.");
					return;
				}
				programNmList = new Array();
				var pSeqList = new Array();
				
				var programNm1 = $("#programNm1").val();
				var programNm2 = $("#programNm2").val();
				var programNm3 = $("#programNm3").val();
				
				var pSeq1 = $("#pSeq1").val();
				var pSeq2 = $("#pSeq2").val();
				var pSeq3 = $("#pSeq3").val();
				
				if(programNm1 != '' && pSeq1 != '' ) {
					pSeqList.push(pSeq1);
					programNmList.push(programNm1);
				}
				
				if(programNm2 != '' && pSeq2 != '' ) {
					pSeqList.push(pSeq2);
					programNmList.push(programNm2);
				}
				
				if(programNm3 != '' && pSeq3 != '' ) {
					pSeqList.push(pSeq3);
					programNmList.push(programNm3);
				}
				
				if(pSeqList.length < 1 ) {
					alert('검색할 프로그램을 선택해 주세요');
					return false;
				}
				
				searchValue = searchValue.replace(/-/gi, '');
				searchValue2 = searchValue2.replace(/-/gi, '');
				
				if(searchValue > searchValue2) {
					alert('시작일이 종료일 보다 클 수 없습니다.');
					return false;
				}
				
				location.href = '/statusboard/choice/downloadExcelFile?searchValue='+searchValue+'&searchValue2='+searchValue2+'&pSeqList='+pSeqList;
			});
		    
			function search(){
				
				var searchValue = $("#searchValue").val();
				var searchValue2 = $("#searchValue2").val();
				var dateRange = calDateRange(searchValue, searchValue2);
				
				if(dateRange > 15 ) {
					alert("15일씩 검색이 가능합니다.");
					return;
				}
				programNmList = new Array();
				var pSeqList = new Array();
				
				var programNm1 = $("#programNm1").val();
				var programNm2 = $("#programNm2").val();
				var programNm3 = $("#programNm3").val();
				
				var pSeq1 = $("#pSeq1").val();
				var pSeq2 = $("#pSeq2").val();
				var pSeq3 = $("#pSeq3").val();
				
				if(programNm1 != '' && pSeq1 != '' ) {
					pSeqList.push(pSeq1);
					programNmList.push(programNm1);
				}
				
				if(programNm2 != '' && pSeq2 != '' ) {
					pSeqList.push(pSeq2);
					programNmList.push(programNm2);
				}
				
				if(programNm3 != '' && pSeq3 != '' ) {
					pSeqList.push(pSeq3);
					programNmList.push(programNm3);
				}
				
				if(pSeqList.length < 1 ) {
					alert('검색할 프로그램을 선택해 주세요');
					return false;
				}
				
				searchValue = searchValue.replace(/-/gi, '');
				searchValue2 = searchValue2.replace(/-/gi, '');
				
				if(searchValue > searchValue2) {
					alert('시작일이 종료일 보다 클 수 없습니다.');
					return false;
				}
				$.ajaxSettings.traditional = true;
				$.ajax({
					type :	"get",
					url  : 	"/statusboard/getChoiceCompareList",
					datatype : "json",
					async: true,
					data : {
						searchValue	: searchValue,
						searchValue2 : searchValue2,
						pSeqList : pSeqList
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						var list = result.list;
						
						var chartData = new Array();
						var broadDate = "";
						var makeYn = false;
						
						var chartData1 = new Array();
						var chartData2 = new Array();
						var chartData3 = new Array();
						var chartData4 = new Array();
						
						var category = new Array();
						$( '#tableGrid > tbody').empty();
						
						if(list.length > 0 && list != null ) {
							
							makeYn = true;
							//chartData.push( {"label" : tvVodList[k].programNm, "value" : tvVodList[k].chargeCnt} );
							for(var k =0 ; k< list.length; k++){
								var programNm = list[k].programNm + " (" + list[k].vodcnt+ ")";
								
								if(broadDate != list[k].broadDate) {
									broadDate = list[k].broadDate;
									category.push({ label : broadDate });
								}
								
								
								$('#tableGrid > tbody:last ').append("<tr> <td >  "+list[k].broadDate+"</td>  <td >" 
										+ programNm+"</td>  <td >"+AddComma(list[k].viewRating)+"%</td>   <td >"
										+ AddComma(list[k].tvVod)+"</td>  <td >"+AddComma(list[k].pooqVod)+"</td>   <td >"
										+AddComma(list[k].clipCnt)+"</td>   </tr>" );
							}
							
							
							var data1 = new Array();
							var data2 = new Array();
							var data3 = new Array();
							var data4 = new Array();
							if(makeYn){
								
								for(var i=0; i<programNmList.length; i++) {
									data1 = new Array();
									data2 = new Array();
									data3 = new Array();
									data4 = new Array()
									for(var j =0; j < category.length; j++){
										for(var k =0 ; k< list.length; k++){
											
											if(category[j].label == list[k].broadDate && programNmList[i].trim() == list[k].programNm.trim()) {
												data1.push( {  value : list[k].viewRating });
												data2.push( {  value : list[k].tvVod });
												data3.push( {  value : list[k].pooqVod });
												data4.push( {  value : list[k].clipCnt });
												
												break;
												
											}else {
												if(k == list.length-1){
													data1.push( {  value : 0.00 });
													data2.push( {  value : 0 });
													data3.push( {  value : 0 });
													data4.push( {  value : 0 });
													
												}
											}
										}
									}
									chartData1.push({  seriesname : programNmList[i], data : data1  });
									chartData2.push({  seriesname : programNmList[i], data : data2  });
									chartData3.push({  seriesname : programNmList[i], data : data3  });
									chartData4.push({  seriesname : programNmList[i], data : data4  });
								}
							}
							
							
							
							updateChart(category, chartData1, 1);
							updateChart(category, chartData2, 2);
							updateChart(category, chartData3, 3);
							updateChart(category, chartData4, 4);
						}else {
							$('#tableGrid > tbody:last ').append("<tr> <td colspan='6'> 검색된 내용이 없습니다. </td></tr>");
							$("#chart-container1").empty();
							$("#chart-container2").empty();
							$("#chart-container3").empty();
							$("#chart-container4").empty();
						}
						
						
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});
			}
			
			function updateChart(category, chartData, index) {
				
				var exportFileName = "";
				if(index == 1) {
					exportFileName = '시청률';
				}else if(index ==2) {
					exportFileName = 'TV VOD';
				}else if(index ==3) {
					exportFileName = 'POOQ VOD';
				}else if(index ==4) {
					exportFileName = 'SMR 클립';
				}
				
			    var jsonData = {
			        
			        "chart": {
			            "xAxisName": "시간대",
			            "yAxisName": "조회수",
			            "width" : "100%",
			            "formatNumberScale": "0",
			            "theme": "fint",
			            "exportEnabled" :"1",
			            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
			            "exportFileName": "동시간대비교_"+exportFileName
			        },
			        categories : [{
			        	category : category
			        		
			        	
			        }],
			        dataset : chartData
			    };
			    
			    var fusioncharts = new FusionCharts({
				    type: 'MSColumn2D',
				    renderAt: 'chart-container'+index,
				    dataFormat: 'json',
				    width : "100%",
				    dataSource: {
				        "chart": {
				            "xAxisName": "",
				            "yAxisName": "",
				            "formatNumberScale": "0",
				            "theme": "fint",
				            "exportEnabled" :"1",
				            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
				            "exportFileName": "동시간대비교_"+exportFileName
				            
				        }
				        
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