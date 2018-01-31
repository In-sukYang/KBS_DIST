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
				<jsp:include page="../../include/left.jsp" flush="false"/>
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
				<div class="nav">Home &gt; 프로그램 상세  &gt;<span>주요지표-프로그램별</span></div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth5">프로그램 상세-주요지표 조회</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="sch-pr01" class="wx100 txt-l">구분</label>
									<ul>
										<li>
											<input type="radio" id="searchType01" name="searchType" value="01" checked="checked"><label for="searchType01"><span></span>프로그램별</label>
											<input type="radio" id="searchType02" name="searchType" value="02"><label for="searchType02"><span></span>단일회차별</label>
										</li>
									</ul>
								</li>
							
								<li><span>02</span><label for="sch-pr02" class="wx100 txt-l">프로그램 명</label>
									<ul>
										<li>
											<input type="hidden" id="pSeq" />
											<input type="text" id="programNm" class="wx160" />
											<a href="#" class="pro-select" id="programBtn"><span>프로그램선택</span></a>
										</li>
									</ul>
								</li>
								<li><span>03</span><label for="vodcnt" class="wx100 txt-l" id="vodcntText">회차 구간</label>
									<ul>
										<li id="vodcntZone">
											<select id="startVodcnt" class="wx130">
												
											</select> ~ 
											<select id="endVodcnt" class="wx130">
												
											</select>
										</li>
										
										<li id="broadDateZone">
											<select id="broadDate" class="wx130">
												
											</select> 
										</li>
									</ul>
								</li>
								<li id= "programZone"><span>04</span><label for="addDay" class="wx100 txt-l">수집 기간</label>
									<ul>
										<li>
											<select id="addDay" class="wx110">
												<option value="1" >방송후 1일</option>
												<option value="2" >방송후 2일</option>
												<option value="3" >방송후 3일</option>
												<option value="4" >방송후 4일</option>
												<option value="5" >방송후 5일</option>
												<option value="6" >방송후 6일</option>
												<option value="7" >방송후 7일</option>
												<option value="14" >방송후 14일</option>
												<option value="30" >방송후 30일</option>
											</select>
										</li>
									</ul>
									<p class="new-text" style="color:gray; margin-left:40px;">※ TV VOD의 경우 PPM만 반영하며, KT 데이터는 제외됩니다.</p>
								</li>
								
								<li id="contentsZone"><span>04</span><label for="" class="wx100 txt-l">기간 선택</label>
									<ul>
										<li>
											<input type="text" id="searchValue" class="wx110" /> ~ <input type="text" id="searchValue2" class="wx110" title="편상일자 선택" />
										</li>
									</ul>
									<p class="new-text" style="color:gray; margin-left:40px;">※ TV VOD의 경우 PPM만 반영하며, KT 데이터는 제외됩니다.</p>
								</li>
								
							</ul>
							<div class="info-bx" id="infoBox">
								<ul>
									<li>
										<p><img src="" alt="이미지 정보" style="width:299px; height:140px" id="infoImg"/></p>
									</li>
									<li style="width:124px; height:140px; margin-left:130px">
										<ul style="width:140px;">
											<li><span class="dr-info-name" style="width:70px;">프로그램명 : </span>
												<ul>
													<li><span class="s-info"  id="imgText"></span></li>
												</ul>
											</li>
											<li><span class="dr-info-name" style="width:70px;">방송사 : </span>
												<ul>
													<li><span class="s-info"  id="broad"></span></li>
												</ul>
											</li>
											<li><span class="dr-info-name" style="width:70px;">방송요일 : </span>
												<ul>
													<li> <span class="bar" id="weekday">I</span></li>
												</ul>
											</li>
											<!-- 
											<li><span class="dr-info-name" style="width:70px;">출연진 : </span>
												<ul>
													<li> <span class="bar" id="starlist">I</span></li>
												</ul>
											</li>
											 -->
										</ul>
									</li>
								</ul>
							</div>
							<a href="#" class="btn-submit" id="searchBtn"><span>조회</span></a>
						  <a href="#" class="btn-submit2" id="excelBtn"><span>엑셀저장</span></a>
						</div>
					</div>
				 	<div class="content-bx">
						<h3 class="depth3 type02">프로그램 상세 – 주요지표 차트</h3>
						<div class="grap-bx gr-type02">
							<div class="gr-area" id="graph1Area">
								<p class="title">시청률</p>
								<div class="graph" id="graph1">
								</div>
							</div>
							<div class="gr-area">
								<p class="title">VOD</p>
								<div class="graph" id="graph2">
								</div>
							</div>
						  	<div class="gr-area">
								<p class="title">SMR 클립</p>
								<div class="graph" id="graph4">
								</div>
							</div>
						</div>
					</div>
					<div class="content-bx" id="tableZone1">
						<h3 class="depth4 type02">프로그램 별 현황 – 주요지표 데이터</h3>
						<div class="tbl-bxn scroll-x">
							<table summary="방영일자, 회차, 시청률, pooq 실시간, pooq VOD, TV VOD, 클립영상 정보를 제공합니다" id="tableGrid">
								<caption>프로그램 별 현황 – 선택 비교 데이터 목록</caption>
								<thead>
									<tr>
										<th scope="col" width="130">방영일자</th>
										<th scope="col" width="120">회차</th>
										<th scope="col" width="180">시청률</th>
										<th scope="col" width="180">TV VOD</th>
										<th scope="col" width="180">Pooq VOD</th>
										<th scope="col" >클립영상</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
					</div>
					<div class="content-bx" id="tableZone2">
						<h3 class="depth4 type02">프로그램 별 현황 – 주요지표 데이터</h3>
						<div class="tbl-bxn scroll-x">
							<table summary="방영일자, 회차, 시청률, pooq 실시간, pooq VOD, TV VOD, 클립영상 정보를 제공합니다" id="tableGrid2">
								<caption>프로그램 별 현황 – 선택 비교 데이터 목록</caption>
								<thead>
									<tr>
										<th scope="col" width="200">기준일자</th>
										<th scope="col" width="275">TV VOD</th>
										<th scope="col" width="275">Pooq VOD</th>
										<th scope="col" >클립영상</th>
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
		var viewRatingData = new Array();
		var pooqVodData = new Array();
		var tvVodData = new Array();
		var clipData = new Array();
		var callback;
		$(document).ready(function() {
			$("#infoBox").hide();
			
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
			
			setting();
			$("#programNm").keypress(function(key) {
				if(key.keyCode == 13) {
					$("#programBtn").click();			
				}
			});
			
			$("#searchType01").click(function(){
				setting();				
			});
			
			$("#searchType02").click(function(){
				setting();				
			});
			
			$("#programBtn").click(function() {
		    	openPopupProgram($("#programNm").val());
			});
			
			 function openPopupProgram(searchValue) {
				if(searchValue == '' || searchValue.length <2 ){
					alert("검색어를 2자이상 입력해주세요");
					return;
				}
				var url = "/program/layer/parentProgramLayer?openrType=02&searchType=01&searchValue="+searchValue+"&callback=callback";
				window.open(url, 'program', 'width=1100, height=600, scrollbars=yes' );
	    	
			}
			 
		 	$("#searchBtn").click(function () {
				search();	
			}); 
			
		 	callback = function (obj) {
				
				$("#programNm").val(obj.eq(2).text());
				$("#pSeq").val(obj.eq(0).attr('data'));
				
				var programId = obj.eq(1).text();
				if(programId != '' ) {		
					var url = 'https://wapie.pooq.co.kr/v1/programs30/'+programId+'?deviceTypeId=pc&marketTypeId=generic&apiAccessCredential=07634EDC22AC363144F08B5643FBEC6B';
					$.ajax({
						type :	"get",
						url  : 	url,
						datatype : "json",
						async: false,
						success : function(result){
							var data =result.result;
							
							if(result.returnCode == 200) {
								$("#infoBox").show();
								$("#infoImg").attr("src", data.imageUrl);
								$("#imgText").text(data.programTitle);
								$("#broad").text(data.channelTitle);
								$("#weekday").text(data.airingInfo);
								$("#starlist").text(data.starling);
								getVodCnt(obj.eq(0).attr('data'));
							}else {
								$("#infoBox").hide();
							}
						},
						error:function(request,status,error){
							$("#infoBox").hide();
				      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				     	}
					});	
				}
			}
			
		 	function getVodCnt(pSeq) {
				
				$("#startVodcnt").find("option").remove();
				$("#endVodcnt").find("option").remove();
				$.ajax({
					type :	"get",
					url  : 	'/program/common/getProgramVodcntList',
					datatype : "json",
					async: false,
					data: {
						pSeq : pSeq
					},
					success : function(result){
						var data =result.vodcnt;
						
						if(data.length > 1) {
							for(var k=0; k< data.length; k++) {
								if(data.length > 1) {
									for(var k=0; k< data.length; k++) {
										$("#startVodcnt").append("<option value='"+data[k].broadDate+"'>"+data[k].vodcnt+"</option>");
										$("#endVodcnt").append("<option value='"+data[k].broadDate+"'>"+data[k].vodcnt+"</option>");

										$("#broadDate").append("<option value='"+data[k].broadDate+"'>"+data[k].vodcnt+"</option>");	
									}
								}
							}
							
							
						}
						
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
		 	$("#excelBtn").click(function() {
		 		var type = $(":input:radio[name=searchType]:checked").val();
				var pSeq = $("#pSeq").val();
				var programNm = $("#programNm");
				var searchValue ="";
				var searchValue2 = "";
				var broadDate = $("#broadDate").val();
				var addDay = $("#addDay").val();
				if(type == '01') {
					searchValue = $("#startVodcnt").val()
					searchValue2 = $("#endVodcnt").val();
				}else if(type == '02') {
					searchValue = $("#searchValue").val()
					searchValue2 = $("#searchValue2").val();
				}
				if(pSeq == '' || programNm == '') {
					alert('프로그램을 선택해주세요');
					return;
				}
				
				searchValue = searchValue.replace(/-/gi, '');
				searchValue2 = searchValue2.replace(/-/gi, '');
				
				if(searchValue > searchValue2) {
					alert('시작일이 종료일보다 클 수 없습니다.');
					return;
				}		
				location.href = '/program/detail/point/downloadExcelFile?searchType='+type+'&searchValue='+searchValue+'&searchValue2='+searchValue2+'&broadDate='+broadDate+'&pSeq='+pSeq+'&addDay='+addDay;
			});
		 	
			function search() {
				
				var type = $(":input:radio[name=searchType]:checked").val();
				
				var pSeq = $("#pSeq").val();
				var programNm = $("#programNm");
				var searchValue ="";
				var searchValue2 = "";
				var broadDate = $("#broadDate").val();
				var addDay = $("#addDay").val();
				var url = ""
				
				if(type == '01') {
					searchValue = $("#startVodcnt").val()
					searchValue2 = $("#endVodcnt").val();
					url = "/program/detail/getProgramPointIndicatorsList";
				}else if(type == '02') {
					searchValue = $("#searchValue").val()
					searchValue2 = $("#searchValue2").val();
					url = "/program/detail/getProgramPointIndicatorsList2";
				}
				 
				if(pSeq == '' || programNm == '') {
					alert('프로그램을 선택해주세요');
					return;
				}
				
				searchValue = searchValue.replace(/-/gi, '');
				searchValue2 = searchValue2.replace(/-/gi, '');
				
				if(searchValue > searchValue2) {
					alert('시작일이 종료일보다 클 수 없습니다.');
					return;
				}				
				
				$.ajax({
					type :	"get",
					url  : url,
					datatype : "json",
					async: true,
					data: {
						pSeq : pSeq,
						searchValue : searchValue,
						searchValue2 : searchValue2,
						addDay : addDay,
						broadDate : broadDate
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						var list =result.list;
						viewRatingData = new Array();
						pooqLiveData = new Array();
						pooqVodData = new Array();
						tvVodData = new Array();
						clipData = new Array();
						
						var category = new Array();
						
						$( '#tableGrid > tbody').empty();
						$( '#tableGrid2 > tbody').empty();
						if(list.length > 0) {
							for(var k=0; k< list.length; k++) {
								
								if(type == '01') {
								
									$('#tableGrid > tbody:last').append(
										"<tr>"
											+"<td width='130'>"+list[k].broadDate+"</td>"
											+"<td width='120'>"+list[k].vodcnt+"</td>"
											+"<td width='180'>"+list[k].viewRating+"%</td>"
											+"<td width='180'>"+AddComma(list[k].tvVod)+"</td>"
											+"<td width='180'>"+AddComma(list[k].pooqVod)+"</td>"
											+"<td>"+AddComma(list[k].clipCnt)+"</td> " 
										+ " </tr>" );
									
									category.push({ label:  list[k].broadDate});																
									viewRatingData.push({ value : list[k].viewRating  });
									pooqLiveData.push({ value : list[k].pooqLive  });
									pooqVodData.push( { value : list[k].pooqVod  });
									tvVodData.push( { value : list[k].tvVod  });
									clipData.push( { label : list[k].broadDate, value : list[k].clipCnt  });
									
								}else if(type == '02') {
									
									$('#tableGrid2 > tbody:last').append(
										"<tr>"
											+"<td width='200'>"+list[k].yyyymmdd+"</td>"
											+"<td width='275'>"+AddComma(list[k].tvVod)+"</td>"
											+"<td width='275'>"+AddComma(list[k].pooqVod)+"</td>"
											+"<td>"+AddComma(list[k].clipCnt)+"</td> "   
											+ " </tr>" );
									
									
									category.push( { label : list[k].yyyymmdd} );
									
									pooqVodData.push( { value : list[k].pooqVod  });
									tvVodData.push( { value : list[k].tvVod  });
									clipData.push( { label : list[k].yyyymmdd, value : list[k].clipCnt  });
								}
								
							}
							//updateChart(viewRatingData , 1);
							
							if(type == '01') {
								updateChart4(category,viewRatingData ,pooqLiveData, 1);
								updateChart2(category, tvVodData, pooqVodData, 2);
								updateChart(clipData , 4);	
							}else {
								
								updateChart2(category, tvVodData, pooqVodData , 2);
								updateChart(clipData , 4);
							}
							
						}else {
							
							if(type == '01') {
								$('#tableGrid > tbody:last ').append("<tr> <td colspan='6'> 검색된 내용이 없습니다. </td></tr>");	
								$("#graph1Area").show();
								
								$("#graph1").empty();
								$("#graph2").empty();
								$("#graph4").empty();	
							}else {
								$('#tableGrid2 > tbody:last ').append("<tr> <td colspan='4'> 검색된 내용이 없습니다. </td></tr>");	
								$("#graph1Area").hide();
								
								$("#graph2").empty();
								$("#graph4").empty();
							}
							
							
						}
						formatRange();
						$('#tableGrid2 > tbody').width("970px");
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
				
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
			
			function updateChart4(category,data1,data2, index) {
				var datasetCnt = new Array();
				datasetCnt.push({ seriesname : "시청률", 
										"renderAs": "line",
						                "showValues": "0",
										data : data1 });
										
				datasetCnt.push({ seriesname : "pooqLive", 
										"renderAs": "line",
						                "parentYAxis": "S",
						                "showValues": "0",
										data : data2 }  );
				
				 var fusioncharts = new FusionCharts({
				    type: 'zoomlinedy',
				    renderAt: 'graph1',
				    dataFormat: 'json',
				    width : "100%",
				    dataSource: {
					    "chart": {
				            "xAxisname": "시청시간",
		                    "pYAxisName": "시청률",
		                    "sYAxisName": "pooqLive",
		                    "labelDisplay": "rotate", 
		                    "slantLabels": "1",
		                    "baseFontSize": "11",
		                    "drawCrossLine": "1",
		                    "crossLineColor": "#0d0d0d",
		                    "crossLineAlpha": "100",
		                    "formatNumberScale": "0",
		                    "drawAnchors": "1",
		                    "theme": "fint",
				            "exportEnabled" :"1",
				            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
				            "exportFileName": "주요지표 프로그램별"
				        },
				        "categories": [
            			{
               				 "category": category
           				 }
          				],
				         "dataset":datasetCnt
				    }
				});
			    fusioncharts.render();
 			}
			
			function updateChart(data, index) {
				
				var exportFileName = "";
				if(index == 1) {
					exportFileName = '시청률';
				}else if(index ==2) {
					exportFileName = 'VOD';
				}else if(index ==3) {
					
				}else if(index ==4) {
					exportFileName = 'SMR 클립';
				}
				
			    var jsonData = {
			        "chart": {
			        	"labelDisplay": "rotate", 
			        	"slantLabels": "1",
			        	"baseFontSize": "11",
			        	"showValues": "0",
			        	"drawCrossLine": "1", "crossLineColor": "#0d0d0d", "crossLineAlpha": "100",
			        	
			        	"formatNumberScale": "0",
			            "xAxisName": "방영일",
			            "width" : "100%",
			            "theme": "fint",
			            "exportEnabled" :"1",
			            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
			            "exportFileName": exportFileName
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
				        	"labelDisplay": "rotate", 
				        	"slantLabels": "1",
				        	"baseFontSize": "11",
				        	"showValues": "0",
				        	"drawCrossLine": "1", "crossLineColor": "#0d0d0d", "crossLineAlpha": "100",
				        	
				        	"formatNumberScale": "0",
				            "xAxisName": "프로그램",
				            "theme": "fint",
				            "exportEnabled" :"1",
				            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
				            "exportFileName": exportFileName
				        }
				        
				    }
				});
			    
			    fusioncharts.setJSONData(jsonData);
			    fusioncharts.render();
 			}
			
			function updateChart2(category, data1, data2, index) {
				
				var jsonData = {
				        
				        "chart": {
				        	
				        	"labelDisplay": "rotate", 
				        	"slantLabels": "1",
				        	"baseFontSize": "11",
				        	"showValues": "0",
				        	"drawCrossLine": "1", "crossLineColor": "#0d0d0d", "crossLineAlpha": "100",
				        	
				            "xAxisName": "시간대",
				            "yAxisName": "조회수",
				            "formatNumberScale": "0",
				            "width" : "100%",
				            "theme": "fint",
				            "height" : "100%"
				        },
				        categories : [{
				        	category : category
				        		
				        	
				        }],
				        dataset : [{
				        	seriesname : "PooQ VOD",
				        	data : data2
				        }, {
				        	seriesname : "TV VOD",
					        data : data1
				        }]
				        
		    	};
				    
			    var fusioncharts = new FusionCharts({
					    type: 'msline',
					    renderAt: 'graph'+index,
					    dataFormat: 'json',
					    width : "100%",
					    dataSource: {
					        "chart": {
					            "xAxisName": "",
					            "formatNumberScale": "0",
					            "yAxisName": "조회수",
					            "theme": "fint"
					        }
					        
					    }
				});
				    
			    fusioncharts.setJSONData(jsonData);
			    fusioncharts.render();
 			}
			
			function setting() {
				var type = $(":input:radio[name=searchType]:checked").val();
				
				if(type == '01') {
					$("#vodcntZone").show();
					$("#broadDateZone").hide();
					
					$("#programZone").show();
					$("#contentsZone").hide();
					$("#broadText").text('방영일자');
					$("#vodcntText").text('회차 구간');
					
					$("#graph1Area").show();
					$("#graph1").empty();
					$("#graph2").empty();
					$("#graph4").empty();	
					
					$("#tableZone1").show();
					$("#tableZone2").hide();
					
				}else if(type == '02') {
					$("#vodcntZone").hide();
					$("#broadDateZone").show();
					
					$("#programZone").hide();
					$("#contentsZone").show();
					$("#broadText").text('기준일자');
					$("#vodcntText").text('회차 정보');
					
					$("#graph1Area").hide();
					
					$("#graph1").empty();
					$("#graph2").empty();
					$("#graph4").empty();	
					
					$("#tableZone1").hide();
					$("#tableZone2").show();
				}
				
			}
			
		});
		
		
	</script>
</html>