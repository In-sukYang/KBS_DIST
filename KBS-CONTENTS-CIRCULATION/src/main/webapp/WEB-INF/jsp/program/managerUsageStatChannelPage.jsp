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
		<script type="text/javascript" src="/resources/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/resources/js/common.js"></script>
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
						<li><a href="/">로그아웃</a></li>
					</ul>
				</div>
				<div class="nav">Home &gt; 프로그램관리  &gt; <span>채널별 이용현황 리스트</span></div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth10">채널별 이용현황 리스트 </h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="sch-date">기간선택</label>
									<ul>
										<li>
											<select title="월별 선택" class="wx110" id="searchType">
												<option value="01">월별</option>
												<option value="02">일별</option>
												<option value="03">주별</option>
											</select>
										</li>
										<li>
											<select title="년도 선택" class="wx110" id="searchYear">
												<c:forEach var="i" begin="1980" end="${now.year + 1900}" step="1" >
													<option value ='${i}' <c:if test="${i == (now.year + 1900)}">selected</c:if> >${i}</option>
												</c:forEach>
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
										<li id="searchWeekLi" >
											<select title="주 선택" class="wx110" id="searchWeek">
												
											</select> <span>주</span>
										</li>
									</ul>
								</li>
								<li><span>02</span>
									<ul>
										<li>
											<span >채널</span> 
											<c:if test="${!empty channleList}">
												<c:forEach items="${channleList}" var="list" varStatus="status">
													<input type="checkbox" id="channel${status.index}" name="channel" value="${list.codeId}"><label for="channel${status.index}"><span></span>${list.codeNm}</label>
												</c:forEach>
											</c:if>
													
										</li>
									</ul>
								</li>
								<li><span>03</span>
									<ul>
										<li>
											<span >성별</span> 
											<c:if test="${!empty sexGubunList}">
												<c:forEach items="${sexGubunList}" var="list" varStatus="status">
													<input type="checkbox" id="sexGubun${status.index}" name="sexGubun" value="${list.codeId}"><label for="sexGubun${status.index}"><span></span>${list.codeNm}</label>
												</c:forEach>
											</c:if>
													
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>검색</span></a>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth10 type02">채널별 이용현황 리스트</h3>
						<div class="tbl-bx2">
							<table summary="순번, 방송사, 방송일자, 성별, 10대미만, 20대, 30대, 40대, 50대 이상 정보를 제공합니다" id="tableGrid">
								<caption>채널별 이용현황 리스트</caption>
								<colgroup>
									<col width="86" />
									<col width="99" />
									<col width="" />
									<col width="86" />
									<col width="112" />
									<col width="112" />
									<col width="112" />
									<col width="112" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">채널</th>
										<th scope="col">방송일자</th>
										<th scope="col">성별</th>
										<th scope="col">10대미만</th>
										<th scope="col">20대</th>
										<th scope="col">30대</th>
										<th scope="col">40대</th>
										<th scope="col">50대 이상</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<div class="paging">
						</div>
						<div class="btn-set">
							<a href="#" class="btn-user-add"><span>이용현황추가</span></a>
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
		
		var programList = new Array();
		var paging = new Array();
		$(document).ready(function() {
			
			$("#searchDayLi").hide();
			$("#searchWeekLi").hide();
			
			$("#searchType").change(function() {
				
				var type = $("#searchType").val();
				var year = $("#searchYear").val();
				var month = $("#searchMonth").val();
				
				if(type == '01') {
					$("#searchDayLi").hide();
					$("#searchWeekLi").hide();
				}else if(type == '02') {
					
					makeDay();
					
					$("#searchDayLi").show();
					$("#searchWeekLi").hide();
				}else if(type == '03') {
					makeWeek();
					
					$("#searchDayLi").hide();
					$("#searchWeekLi").show();
				}
			});
			
			search();
			
			$("#searchBtn").click(function () {
				search();	
			});
			
			$("#searchYear").change(function(){
				makeDay();
				makeWeek();
				
			});
			
			$("#searchMonth").change(function(){
				makeDay();
				makeWeek();
			});
			
			function search(){
				
				var searchType = $("#searchType").val();
				var searchValue = "";
				var searchValue2 = "";
				
				
				var channel = new Array();
				var sexGubun = new Array();
				
				$(":checkbox[name='channel']:checked").each(function(pi,po){
			 		channel.push(po.value);
				});
				
				$(":checkbox[name='sexGubun']:checked").each(function(pi,po){
					sexGubun.push(po.value);
				});
				
				var year = $("#searchYear").val();
				var month = $("#searchMonth").val();
				var day = $("#searchDay").val();
				var week = $("#searchWeek").val();
				
				if(searchType == "01") {
					searchValue = year+""+month;
				}else if(searchType == "02") {
					searchValue = year+""+month+""+day;
				}else if(searchType == "03") {
					searchValue = year+""+month+""+week.substring(0,2);
					searchValue2 = year+""+month+""+week.substring(2,4);
				}
				$.ajaxSettings.traditional = true
				$.ajax({
					type :	"get",
					url  : 	"/program/getUsageStatChanneList",
					datatype : "json",
					async: false,
					data : {
						searchType 	: searchType,
						searchValue :searchValue,
						searchValue2	: searchValue2,
						channel 	: channel,
						sexGubun : sexGubun 
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						programList = result.list;
						paging = result.param;
						makeTable();
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
			function makeDay(){
				var type = $("#searchType").val();
				if(type == '02') {
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
			}
			
			function makeWeek(){
				var type = $("#searchType").val();
				
				if(type == '03'){
					var year = $("#searchYear").val();
					var month = $("#searchMonth").val();
					
					$.ajax({
						type :	"get",
						url  : 	"/comm/getWeekDateList",
						datatype : "json",
						async: false,
						data : {
							year 	: year,
							month	: month 
						},
						success : function(result){
							var data = result.weekList;
							$("#searchWeek option").remove();
							
							for(var k =0 ; k<data.length; k++){
								var startDate 	= data[k].startDate;
								var endDate		= data[k].endDate;
								
								if(startDate < 10){
									startDate = "0"+startDate;
								}
								
								if(endDate < 10){
									endDate = "0"+endDate;
								}
								
								var date = startDate+""+endDate;
								
								$("#searchWeek").append(" <option value='"+date+"'>"+data[k].week+" ");
							}
							
						},
						error:function(request,status,error){
				      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				     	}
					});	
					
			    }
			}
		});
		
		var makePaging = function (paging) {
			if (paging.finalPageNo == 1) {
				$('.paging').hide();
				$('.paging').empty();
			} else {
				$('.paging').show();
			
				$('.paging').empty();
				if (paging.finalPageNo <= 10) {
					for (var k = 1; k <= paging.finalPageNo; k++) {
						if (k == paging.currentPageNo) {
							$('.paging').append("  <strong> " + k + "   </strong>  ");
							
						} else {
							$('.paging').append("  <a href='#' onclick='searchPaging("+k+")'> " + k + "  </a>  ");
							
						}
					}
				}else {
					
					$('.paging').append("  <a href='#' class='first' onclick='searchPaging("+paging.firstPageNo+")'>처음</a>  ");
					$('.paging').append("  <a href='#' class='prev' onclick='searchPaging("+paging.prevPageNo+")'>이전</a>  ");
					for (var k = paging.startPageNo; k <= paging.endPageNo; k++) {
						if (k == paging.currentPageNo) {
							$('.paging').append("  <strong> " + k + "   </strong>  ");
						} else {
							$('.paging').append("  <a href='#' onclick='searchPaging("+k+")'> " + k + "  </a>  ");
						}
					}
					
					$('.paging').append("  <a href='#' class='next' onclick='searchPaging("+paging.nextPageNo+")'>처음</a>  ");
					$('.paging').append("  <a href='#' class='end' onclick='searchPaging("+paging.finalPageNo+")'>이전</a>  ");
				}
			}
		}
		
		var searchPaging = function (currPage){
			$.ajax({
				type :	"get",
				url  : 	"/program/getUsageStatChanneList",
				datatype : "json",
				async: false,
				data : {
					searchType 	: paging.searchType,
					searchValue : paging.searchValue,
					searchValue2	: paging.searchValue2,
					channel 	: paging.channel,
					sexGubun : paging.sexGubun, 
					currentPageNo : currPage
					
				},
				success : function(result){
					programList = result.list;
					paging = result.param;
					makeTable();
				},
				error:function(request,status,error){
		      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     	}
			});	
		}
		
		function makeTable() {
			makePaging(paging);
			$('#tableGrid > tbody').empty();
			
			if(programList.length > 0) {
				for(var k=0; k<programList.length; k++) {	
					
					$('#tableGrid > tbody:last ').append("<tr> <td data='"+programList[k].uscSeq+"'>"+((k+1)+paging.offset)+"</td>  <td >" 
						+programList[k].channelNm+"</td>  <td >"
						+programList[k].broadDate+"</td>  <td >"
						+programList[k].sex+"</td>  <td >"
						+programList[k].age0Vcnt+"</td>  <td >"
						+programList[k].age20Vcnt+"</td>  <td >"
						+programList[k].age30Vcnt+"</td>  <td >"
						+programList[k].age40Vcnt+"</td>  <td >"
						+programList[k].age50Vcnt+"</td>  <td >"
						+" </td>    </tr>" );
				}
			}else {
				$('#tableGrid > tbody:last ').append("<tr> <td colspan='9'> 검색된 내용이 없습니다. </td></tr>");
			}
		}
	</script>
</html>