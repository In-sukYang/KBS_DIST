<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<script type="text/javascript" src="/resources/js/jquery.blockUI.js"></script>
		<script type="text/javascript" src="/resources/js/fusioncharts.js"></script>
		<script type="text/javascript" src="/resources/js/fusioncharts.theme.fint.js?cacheBust=56"></script>
		
		<!--[if lte IE 8]>
		<script type="text/javascript" src="../js/html5.js"></script>
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
						<li><a href="/">로그아웃</a></li>
					</ul>
				</div>
				<div class="nav">Home &gt; 종합현황 &gt;<span>주요지표 현황</span></div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth1">주요지표 현황</h3>
						<div class="search-bx">
							<ul>
								<li>
									<span>01</span><label for="sch-date">기간선택</label>
									<ul>
										<li>
											<input type="text" id="searchValue" class="wx110" />
										</li>
										<li>
											~ <input type="text" id="searchValue2" class="wx110" title="편상일자 선택" />
										</li>
										<li>
											<a href="#" class="btn-submit3" id="searchBtn" style"line-height:28px;"><span>조회</span></a>
										</li>
									</ul>
									<p class="new-text" style="color:gray">※ 기간이 주간단위 (시작:월요일 ~ 종료:일요일)인 경우에만 KT데이터가 반영됩니다.</p>
								</li>
							</ul>
						</div>
					</div>
					<div class="content-bx">
						<div class="grap-bx gr-type01">
							<ul>
								<li><p class="title">TV VOD 점유율<br /><span>( 방송3사, PPM기준 )</span></p>
									<div class="gr-area">
										<div class="graph" id="chart-container1">
										</div>
									</div>
								</li>
								<li><p class="title">TV VOD 점유율 <br /><span>( 사업자별 , PPM 기준 )</span></p>
									<div class="gr-area">
										<div class="graph" id="chart-container3">
										</div>
									</div>
								</li>
								<li><p class="title">TV VOD 조회수<br /><span>(KBS,PPV 기준)</span>  <span id="totalTvVodCnt" style="font-size: 15px; "></span></p>
									<div class="gr-area">
										
										<div class="graph" id="chart-container6" style="height:200px;">
										</div>
										
									</div>
									
								</li>
								<li class="ml0"><p class="title">pooq VOD 점유율<br /><span>( 방송3사, PPM기준 )</span></p>
									<div class="gr-area">
										<div class="graph" id="chart-container2">
										</div>
									</div>
								</li>
								<li><p class="title">pooq 실시간 점유율<br /><span>( 채널별 )</span></p>
									<div class="gr-area">
										<div class="graph" id="chart-container4">
										</div>
									</div>
								</li>
								<li><p class="title">SMR 클립 조회수<br /><span>( KBS )</span></p>
									<div class="gr-area">
										<div class="graph">
											<span id="chart-container5" style="font-size: 30px; "></span>
										</div>
									</div>
								</li>
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
		$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI)
		
		$(document).ready(function() {
			
			var today = new Date();
			
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
			
			$("#searchBtn").click(function () {
				search();	
			});
			
			$( "#searchValue" ).val($.datepicker.formatDate('yy-mm-dd', startDate));
			$( "#searchValue2" ).val($.datepicker.formatDate('yy-mm-dd', endDate));
			
			$( "#searchValue" ).attr("readonly", true);
			$( "#searchValue2" ).attr("readonly", true);
			
			function search(){
			
				
				var searchValue = $("#searchValue").val();
				var searchValue2 = $("#searchValue2").val();
				
				var dateRange = calDateRange(searchValue, searchValue2);
				if(dateRange > 31 ) {
					alert("31일씩 검색이 가능합니다.");
					return;
				}
				
				var start = new Date(searchValue);
				var end	= new Date(searchValue2);
				var cSeq = "01";
				
				console.log("start.getDay():"+start.getDay());
				console.log("end.getDay():"+end.getDay());
				if(start.getDay() == 1 && end.getDay() ==0 ){
					cSeq = '02';
				}
				console.log("start.getDay():"+start.getDay());
				console.log("end.getDay():"+end.getDay());
				console.log("cSeq:"+cSeq);
				
				$.ajax({
					type :	"get",
					url  : 	"/dashboard/total/getTotalDashBoard",
					datatype : "json",
					async: true,
					data : {
						searchValue	: searchValue,
						searchValue2 : searchValue2,
						cSeq	: cSeq
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						var tvVodList = result.toVodList;
						var pooqVodList =result.pooqVodList;
						var tvVodListByCseq = result.tvVodListByCseq;
						//var smrTopList = result.smrTopList;
						var viewRatingList = result.viewRatingList;
						
						var pooqRealList = result.pooqRealList;
						var smrClipCnt = result.smrClipCnt;
						
						var tvVodCntList = result.tvVodCntList;
						var totalTvVodCnt =0 ;
						var toVodListData = new Array();
						var pooqVodListData = new Array();
						var tvVodListByCseqData =  new Array();
						var tvVodListByCseqDataK =  new Array();
						var tvVodListByCseqDataM =  new Array();
						var tvVodListByCseqDataS =  new Array();
						
						var pooqRealData =  new Array();
						var viewRatingListData =  new Array();
						var tvVodCntKtData = new Array();
						var tvVodCntLgData = new Array();
						var tvVodCntSkData = new Array();
						var tvVodCntCvodData = new Array();
						
						if(tvVodList.length > 0 && tvVodList != null ) {
							for(var k=0; k< tvVodList.length; k++) {
								toVodListData.push( {  label : tvVodList[k].channelId, value : tvVodList[k].chargeCnt } );
							}
							makeTvVodChart(toVodListData);
						}else {
							$("#chart-container1").empty();
						}
						if(tvVodListByCseq.length > 0 && tvVodListByCseq != null ) {
							for(var k=0; k< tvVodListByCseq.length; k++) {
								if(tvVodListByCseq[k].channelId=='KBS'){
									if(tvVodListByCseq[k].cseq=='KT' ){
										tvVodListByCseqDataK[0]=( { value : tvVodListByCseq[k].chargeCnt } );
									}else if(tvVodListByCseq[k].cseq=='LG' ){
										tvVodListByCseqDataK[1]=( { value : tvVodListByCseq[k].chargeCnt } );
									}else if(tvVodListByCseq[k].cseq=='SK' ){
										tvVodListByCseqDataK[2]=( { value : tvVodListByCseq[k].chargeCnt } );
									}else if(tvVodListByCseq[k].cseq=='CVOD' ){
										tvVodListByCseqDataK[3]=( { value : tvVodListByCseq[k].chargeCnt } );
									}
								}
								if(tvVodListByCseq[k].channelId=='MBC'){
									if(tvVodListByCseq[k].cseq=='KT' ){
										tvVodListByCseqDataM[0]=( { value : tvVodListByCseq[k].chargeCnt } );
									}else if(tvVodListByCseq[k].cseq=='LG' ){
										tvVodListByCseqDataM[1]=( { value : tvVodListByCseq[k].chargeCnt } );
									}else if(tvVodListByCseq[k].cseq=='SK' ){
										tvVodListByCseqDataM[2]=( { value : tvVodListByCseq[k].chargeCnt } );
									}else if(tvVodListByCseq[k].cseq=='CVOD' ){
										tvVodListByCseqDataM[3]=( { value : tvVodListByCseq[k].chargeCnt } );
									}
								}
								if(tvVodListByCseq[k].channelId=='SBS'){
									if(tvVodListByCseq[k].cseq=='KT' ){
										tvVodListByCseqDataS[0]=( { value : tvVodListByCseq[k].chargeCnt } );
									}else if(tvVodListByCseq[k].cseq=='LG' ){
										tvVodListByCseqDataS[1]=( { value : tvVodListByCseq[k].chargeCnt } );
									}else if(tvVodListByCseq[k].cseq=='SK' ){
										tvVodListByCseqDataS[2]=( { value : tvVodListByCseq[k].chargeCnt } );
									}else if(tvVodListByCseq[k].cseq=='CVOD' ){
										tvVodListByCseqDataS[3]=( { value : tvVodListByCseq[k].chargeCnt } );
									}
								}
							}
							makeTvVodCseqChart(tvVodListByCseqDataK,tvVodListByCseqDataM,tvVodListByCseqDataS);
						}else {
							$("#chart-container3").empty();
						}
						
						if(pooqVodList.length > 0 && pooqVodList != null ) {
							for(var k=0; k< pooqVodList.length; k++) {
								pooqVodListData.push( {  label : pooqVodList[k].channelId, value : pooqVodList[k].chargeCnt } );
							}
							makePooqVodChart(pooqVodListData);
						}else {
							$("#chart-container2").empty();
						}
						
						if(tvVodCntList.length > 0 && tvVodCntList != null ) {
							for(var k=0; k< tvVodCntList.length; k++) {
								if(tvVodCntList[k].channelId=='KT'){
									tvVodCntKtData.push( { 
										value : tvVodCntList[k].vodcnt 
										}
									);
								}
								if(tvVodCntList[k].channelId=='LG'){
									tvVodCntLgData.push( { 
										value : tvVodCntList[k].vodcnt 
										}
									);
								}
								if(tvVodCntList[k].channelId=='SK'){
									tvVodCntSkData.push( { 
										value : tvVodCntList[k].vodcnt 
										}
									);
								}
								if(tvVodCntList[k].channelId=='CVOD'){
									tvVodCntCvodData.push( { 
										value : tvVodCntList[k].vodcnt 
										}
									);
								}
								totalTvVodCnt+=parseInt(tvVodCntList[k].vodcnt);
							}
							maketVodCntChart(tvVodCntKtData,tvVodCntLgData,tvVodCntSkData,tvVodCntCvodData,AddComma(totalTvVodCnt));
							//$("#totalTvVodCnt").text("전체 : "+AddComma(totalTvVodCnt));
						}else {
							$("#chart-container6").empty();
						}
						
						
						if(pooqRealList.length > 0 && pooqRealList != null ) {
							for(var k=0; k< pooqRealList.length; k++) {
								pooqRealData.push( {  label : pooqRealList[k].channelId, value : pooqRealList[k].vodcnt } );
							}
							makePooqRealChart(pooqRealData);
						}else {
							$("#chart-container4").empty();
						}
						
						
						if(smrClipCnt!=null) {
							$("#chart-container5").text(AddComma(smrClipCnt));
						}else {
							$("#chart-container5").empty();
						}
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});
				
			}
			
			function makeTvVodChart(data) {
				var colorStr ="";
				for(var i=0;i<data.length;i++){
					if(data[i].label=='KBS'){
						colorStr = colorStr+"#0075C2,";
					}
					if(data[i].label=='MBC'){
						colorStr = colorStr+"#F45B00,";
					}
					if(data[i].label=='SBS'){
						colorStr = colorStr+"#F2C500,";
					}
				}
				var revenueChart = new FusionCharts({
			        type: 'doughnut2d',
			        renderAt: 'chart-container1',
			        width: '100%',
			        height : '100%',
			        dataFormat: 'json',
			        dataSource: {
			            "chart": {
			                
			                "paletteColors": colorStr,
			                "bgColor": "#ffffff",
			                "showBorder": "0",
			                "use3DLighting": "0",
			                "showShadow": "0",
			                "enableSmartLabels": "0",
			                "startingAngle": "310",
			                "showLabels": "0",
			                "showPercentValues": "1",
			                "showLegend": "1",
			                "legendShadow": "0",
			                "legendBorderAlpha": "0",
			                "showTooltip": "1",
			                "enableSmartLabels": "1",
			                "baseFontSize": "11",
			                "decimals": "0",
			                "captionFontSize": "14",
			                "subcaptionFontSize": "14",
			                "subcaptionFontBold": "0"
			            },
			            "data": data
			        }
			    }).render();
			}
			
			function makePooqVodChart(data) {
				var colorStr ="";
				for(var i=0;i<data.length;i++){
					if(data[i].label=='KBS'){
						colorStr = colorStr+"#0075C2,";
					}
					if(data[i].label=='MBC'){
						colorStr = colorStr+"#F45B00,";
					}
					if(data[i].label=='SBS'){
						colorStr = colorStr+"#F2C500,";
					}
				}
				
				var revenueChart = new FusionCharts({
			        type: 'doughnut2d',
			        renderAt: 'chart-container2',
			        width: '100%',
			        height : '100%',
			        dataFormat: 'json',
			        dataSource: {
			            "chart": {
			                
			                "paletteColors": colorStr,
			                "bgColor": "#ffffff",
			                "showBorder": "0",
			                "use3DLighting": "0",
			                "showShadow": "0",
			                "enableSmartLabels": "0",
			                "startingAngle": "310",
			                "showLabels": "0",
			                "showPercentValues": "1",
			                "showLegend": "1",
			                "legendShadow": "0",
			                "legendBorderAlpha": "0",
			                "showTooltip": "1",
			                "enableSmartLabels": "1",
			                "baseFontSize": "11",
			                "decimals": "0",
			                "captionFontSize": "14",
			                "subcaptionFontSize": "14",
			                "subcaptionFontBold": "0"
			            },
			            "data": data
			        }
			    }).render();
			}
			
			function makeTvVodCseqChart(dataK,dataM,dataS) {
				var revenueChart = new FusionCharts({
			        type: 'stackedcolumn2d',
			        renderAt: 'chart-container3',
			        width: '100%',
			        height : '100%',
			        dataFormat: 'json',
			        dataSource: {
			            "chart": {
			                 "caption": "",
			                 "subCaption": "",
			                 "xAxisname": "플랫폼사",
			                 "yAxisName": "시청수",
			                 "showSum": "0",
			                 "showValues": "1",
			                 "valueFontColor": "#ffffff",
			                 "theme": "fint",
			            	 "paletteColors": "#0075C2,#F45B00,#F2C500",
			                 "bgColor": "#ffffff"
			            },
			            "categories": [
			                {
			                    "category": [
			                        {
			                            "label": "KT"
			                        },
			                        {
			                            "label": "LG"
			                        },
			                        {
			                            "label": "SK"
			                        },
			                        {
			                            "label": "CVOD"
			                        }
			                    ]
			                }
			            ],
			            "dataset": [{
			                "seriesname": "KBS",
			                    "data":dataK
			            }, {
			                "seriesname": "MBC",
			                    "data": dataM
			            }, {
			                "seriesname": "SBS",
			                    "data":dataS
			            }]
			        }
			    }).render();
			}
			
			function makePooqRealChart(data) {
				var colorStr ="";
				for(var i=0;i<data.length;i++){
					if(data[i].label=='KBS 2TV'){
						colorStr = colorStr+"#0075C2,";
					}
					if(data[i].label=='MBC'){
						colorStr = colorStr+"#F45B00,";
					}
					if(data[i].label=='SBS'){
						colorStr = colorStr+"#F2C500,";
					}
					if(data[i].label=='KBS 1TV'){
						colorStr = colorStr+"#1AAF5D,";
					}
				}
				
				var revenueChart = new FusionCharts({
					type: 'doughnut2d',
			        renderAt: 'chart-container4',
			        width: '100%',
			        height : '100%',
			        dataFormat: 'json',
			        dataSource: {
			            "chart": {
			                
			                "paletteColors": colorStr,
			                "bgColor": "#ffffff",
			                "showBorder": "0",
			                "use3DLighting": "0",
			                "showShadow": "0",
			                "enableSmartLabels": "0",
			                "startingAngle": "310",
			                "showLabels": "0",
			                "showPercentValues": "1",
			                "showLegend": "1",
			                "legendShadow": "0",
			                "legendBorderAlpha": "0",
			                "showTooltip": "1",
			                "enableSmartLabels": "1",
			                "baseFontSize": "11",
			                "decimals": "0",
			                "captionFontSize": "14",
			                "subcaptionFontSize": "14",
			                "subcaptionFontBold": "0"
			            },
			            "data": data
			        }
			    }).render();
			}
			
			
			function maketVodCntChart(dataKt,dataLg,dataSk,dataCvod, totalCnt) {
				var revenueChart = new FusionCharts({
				type: 'stackedbar2d',
		        renderAt: 'chart-container6',
		        width: '100%',
		        height : '100%',
		        dataFormat: 'json',
		        dataSource: {
		        		 "chart": {
		                     "caption": "전체 : "+totalCnt,
		                     "subCaption": "",
		                     "xAxisname": "",
		                     "yAxisName": "시청수",
		                     "numberPrefix": "",
		                     "paletteColors": "#0075c2,#1aaf5d,#d43232,#3236D4",
		                     "bgColor": "#ffffff",
		                     "borderAlpha": "20",
		                     "showCanvasBorder": "0",
		                     "usePlotGradientColor": "0",
		                     "plotBorderAlpha": "10",
		                     "legendBorderAlpha": "0",
		                     "legendShadow": "0",
		                     "valueFontColor": "#ffffff",                
		                     "showXAxisLine": "1",
		                     "xAxisLineColor": "#999999",
		                     "divlineColor": "#999999",               
		                     "divLineIsDashed": "1",
		                     "showAlternateVGridColor": "0",
		                     "subcaptionFontBold": "0",
		                     "subcaptionFontSize": "14",
		                     "showHoverEffect":"1"
		        		 },
				        categories : [{
				        	category : 
				        	[
	                             {
	                                 "label": "KBS"
	                             }
	                         ]
				        }],
				        dataset : [{
				        	seriesname : "KT",
				        	data : dataKt
				        }, {
				        	seriesname : "LG",
					        data : dataLg
				        },{
				        	seriesname : "SK",
					        data : dataSk
				        },{
				        	seriesname : "CVOD",
					        data : dataCvod
				        }]
		        	}
				
			    }).render();
			}
			
			function makeTopRatingChart(data) {
				
				var fusioncharts = new FusionCharts({
				    type: 'column2d',
				    renderAt: 'chart-container5',
				    width : "100%",
				    height : '100%',
				    dataFormat: 'json',
				    dataSource: {
				        "chart": {
				            "xAxisName": "",
				            "yAxisName": "",
				            "formatNumberScale": "0",
				            "theme": "fint"
				        },
	
				        "data": data
				    }
				}).render();
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