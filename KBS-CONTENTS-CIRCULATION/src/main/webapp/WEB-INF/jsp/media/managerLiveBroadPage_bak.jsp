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
		<script type="text/javascript" src="/resources/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/resources/js/common.js"></script>
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
						<li><a >${sessionScope.userNm}</a></li>
						<c:choose> 
							<c:when test="${sessionScope.loginId ne null && sessionScope.loginId ne ''}" > 
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
										<li>
											<select title="월별 선택" class="wx110" id="searchType">
												<option value="0">일별</option>
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
											<select id="sch-channel" title="채널선택" class="wx110">
												<option value="01">기준-채널</option>
<!-- 												<option value="02">기준-지표</option> -->
											</select>
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
			
			var grapList = new Array();
			var chartData = new Array();
			var chartCategory = new Array();
			
			$("#searchDayLi").show();
			makeDay();
			
			$("#searchYear").change(function(){
				makeDay();
				
			});
			
			$("#searchMonth").change(function(){
				makeDay();
			});
			
			function makeDay(){
				var type = $("#searchType").val();
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
			
			function search(){
				
				var searchType = $("#searchType").val();
				var searchValue = "";
				var searchValue2 = "";
				var ageType = $("#ageType").val();
				var sexGubun = $("#sexGubun").val();
				
				
				var year = $("#searchYear").val();
				var month = $("#searchMonth").val();
				var day = $("#searchDay").val();
				var week = $("#searchWeek").val();
				
				var channel = new Array();
				
				searchValue = year+""+month+""+day;
				
			 	$(":checkbox[name='channel']:checked").each(function(pi,po){
			 		channel.push(po.value);
				});
			 	
			 	if(channel.length < 1) {
			 		alert('채널을 입력해주세요');
			 		return;
			 	}
			 	
			 	$.ajaxSettings.traditional = true
				$.ajax({
					type :	"get",
					url  : 	"/media/getLiveBroadTopList",
					datatype : "json",
					async: true,
					data : {
						searchType 	: searchType,
						searchValue	: searchValue,
						searchValue2 : searchValue2,
						ageType : ageType,
						channel : channel,
						sexGubun : sexGubun
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
						if(key.length > 0) {
							chartCategory = new Array();
							for(var i=0; i< 24; i++) {
								if(i < 10) {
									chartCategory.push( { label : "0"+i  } );
								}else {
									chartCategory.push( { label : ""+i  } );
								}
							}
							for(var k=0; k < key.length ; k++) {
								if(key[k] != 'mediaParamVo') {
									var data = result[key[k]];
									if(data.length > 0){
										
										$("#grArea").append(" <div class='gr-area' > "       
												+ "<p class='title'>" +key[k]+ "</p> "
												+ "<div class='graph' style='height:280px' id='grapDiv"+ k +"'> "
												+ " </div> "
												+  " </div> "
										);
										
										
										updateChart(data,k, key[k]);
									
									}
									
								}
							}
						}else {
							$("#grArea").empty();
						}
						

//						chartData.push( {"label" : list[k]. programNm, "value" : list[k].totalCnt} );
						
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
			
			function updateChart(data, index, broad) {
				var dataset = new Array();
				   
			   	for(var i=0; i < data.length; i++ ){
				   	var dataValue = new Array(); 
					for(var j=0; j<24; j++ ){
						if(j < 10) {
							dataValue.push({ value : eval("data[i].vcntT0"+j) });
						}else {
							dataValue.push({ value : eval("data[i].vcntT"+j) });
						}	
					}
					dataset.push({ seriesname : data[i].age, data : dataValue }  );
				}
				
			    var jsonData = {
			        
			        "chart": {
			            "xAxisName": "시간대",
			            "yAxisName": "조회수",
			            "formatNumberScale": "0",
			            "theme": "fint",
			            "exportEnabled" :"1",
			            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
			            "exportFileName": "실시간_방송_조회"+broad
			        },
			        categories : [{
			        	category : chartCategory
			        		
			        	
			        }],
			        dataset : dataset
			    };
			    
			    var fusioncharts = new FusionCharts({
				    type: 'msline',
				    renderAt: 'grapDiv'+index,
				    dataFormat: 'json',
				    width : "100%",
				    dataSource: {
				        "chart": {
				            "xAxisName": "",
				            "formatNumberScale": "0",
				            "yAxisName": "조회수",
				            "theme": "fint",
				            "exportEnabled" :"1",
				            "exportFormats": "PNG=PNG로 이미지 저장|JPG=JPG로 이미지 저장|XLS=XLS로 데이터 저장",
				            "exportFileName": "실시간_방송_조회"+broad
				        }
				        
				    }
				});
			    
			    fusioncharts.setJSONData(jsonData);
			    fusioncharts.render();
 			}
		});
		
		
	</script>
	
</html>