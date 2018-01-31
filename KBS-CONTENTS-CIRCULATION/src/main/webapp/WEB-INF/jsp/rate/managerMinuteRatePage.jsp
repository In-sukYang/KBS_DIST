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
				<div class="nav">Home &gt; <span>종합현황</span></div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth11">분당시청률 관리</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="startDate">조회일시</label>
									<ul>
										<li><input type=text id="startDate" class="wx140" readonly="readonly">
											 <input type=text id="endDate" class="wx140" readonly="readonly">
										</li>
									</ul>
								</li>
<!-- 								<li><span>02</span><label for="sch-opt">조화구분</label> -->
<!-- 									<ul> -->
<!-- 										<li><select id="sch-opt" class="wx140"> -->
<!-- 												<option>연력별</option> -->
<!-- 											</select> -->
<!-- 										</li> -->
<!-- 									</ul> -->
<!-- 								</li> -->
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>검색</span></a>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth11 type02">분당시청률 리스트</h3>
						<div class="tbl-bx2 scroll-x">
							<table summary="순번, 일시, 채널, 가구전체, 개인전체, 유선, 무선, 10대, 20대, 30대, 40대, 50대이상 정보를 제공합니다" id="tableGrid">
								<caption>분당시청률 리스트</caption>
								<colgroup>
									<col width="77" />
									<col width="77" />
									<col width="200" />
									<col width="90" />
									<col width="90" />
									<col width="90" />
									<col width="90" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
									<col width="72" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">방송일자</th>
										<th scope="col">조사기관</th>
										<th scope="col">지역</th>
										<th scope="col">채널</th>
										<th scope="col">방송시간</th>
										<th scope="col">가구전체</th>
										<th scope="col">개인전체</th>
										<th scope="col">유선</th>
										<th scope="col">무선</th>
										<th scope="col">남자</th>
										<th scope="col">여자</th>
										<th scope="col">10대미만</th>
										<th scope="col">10대</th>
										<th scope="col">20대</th>
										<th scope="col">30대</th>
										<th scope="col">40대</th>
										<th scope="col">50대 이상</th>
										<th scope="col">남자10대미만</th>
										<th scope="col">남자10대</th>
										<th scope="col">남자20대</th>
										<th scope="col">남자30대</th>
										<th scope="col">남자40대</th>
										<th scope="col">남자50대이상</th>
										<th scope="col">여자10대미만</th>
										<th scope="col">여자10대</th>
										<th scope="col">여자20대</th>
										<th scope="col">여자30대</th>
										<th scope="col">여자40대</th>
										<th scope="col">여자50대이상</th>
										<th scope="col">초등생이하</th>
										<th scope="col">중고생</th>
										<th scope="col">중졸</th>
										<th scope="col">고졸</th>
										<th scope="col">대재이상</th>
										<th scope="col">주부</th>
										<th scope="col">월소득150이하</th>
										<th scope="col">월소득150이상</th>
										<th scope="col">월소득200</th>
										<th scope="col">월소득300</th>
										<th scope="col">월소득400</th>
										<th scope="col">월소득500이상</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
						<div class="paging">
							
						</div>
						<div class="btn-set">
							<a href="#" class="btn-user-add2"><span>분당 시청률추가</span></a>
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
		
		var list = new Array();
		var paging = new Array();
		
		$(document).ready(function() {
			
			$( "#startDate, #endDate" ).datepicker({
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
			
			
			$( "#startDate" ).val($.datepicker.formatDate('yy-mm-dd', new Date()));
			$( "#endDate" ).val($.datepicker.formatDate('yy-mm-dd', new Date()));
			
			$( "#startDate" ).attr("readonly", true);
			$( "#endDate" ).attr("readonly", true);
			
			$("#searchBtn").click(function () {
				search();
			});
			
			function search() {
				var searchType = '02'
				var startDate = $("#startDate").val();
				var endDate = $("#endDate").val();
				
				startDate = startDate.replace(/-/gi, "");
				endDate = endDate.replace(/-/gi, "");
				
				if(startDate == ""){
					alert("검색 시작 일자를 입력해주세요");
					return;
				}
				
				if(searchType == '02' && endDate == '') {
					alert('검색 종료일자를 입력해주세요');
					return;
				}else if(searchType == '02' && endDate != ''){
					if(startDate > endDate) {
						alert('시작일시가 종료일시보다 작아야 합니다.');
						return;
					}
				}
				
				$.ajax({
					type :	"get",
					url  : 	"/rate/getMinuteRateList",
					datatype : "json",
					async: false,
					data : {
						searchType 	: searchType,
						startDate   : startDate,
						endDate		: endDate
						
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						list = result.list;
						paging = result.param;
						makeTable();
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
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
				url  : 	"/rate/getMinuteRateList",
				datatype : "json",
				async: false,
				data : {
					searchType 	: paging.searchType,
					startDate   : paging.startDate,
					endDate		: paging.endDate,
					currentPageNo : currPage
				},
				success : function(result){
					list = result.list;
					paging = result.param;
					makeTable();
				},
				error:function(request,status,error){
		      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     	}
			});	
			
		}
		
		function makeTable(){
			makePaging(paging)
			$('#tableGrid > tbody').empty();
			if(list.length > 0) {
				for(var k=0; k<list.length; k++) {	
					
					$('#tableGrid > tbody:last ').append("<tr> <td data='"+list[k].mvrSeq+"'>"+((k+1)+paging.offset)+"</td>  <td >" 
						+list[k].broadDate+"</td>  <td >"
						+list[k].researchAgency+ " </td> <td>"
						+list[k].area+ " </td> <td> "
						+list[k].channelNm+ " </td> <td> "
						+list[k].airTime+ " </td> <td> "
						+list[k].house+ " </td> <td> "
						+list[k].personal+ " </td> <td> "
						+list[k].cable+ " </td> <td> "
						+list[k].wireless+ " </td> <td> "
						+list[k].man+ " </td> <td> "
						+list[k].woman+ " </td> <td> "
						+list[k].u10+ " </td> <td> "
						+list[k].s10+ " </td> <td> "
						+list[k].s20+ " </td> <td> "
						+list[k].s30+ " </td> <td> "
						+list[k].s40+ " </td> <td> "
						+list[k].o50+ " </td> <td> "
						+list[k].mu10+ " </td> <td> "
						+list[k].ms10+ " </td> <td> "
						+list[k].ms20+ " </td> <td> "
						+list[k].ms30+ " </td> <td> "
						+list[k].ms40+ " </td> <td> "
						+list[k].mo50+ " </td> <td> "
						+list[k].wu10+ " </td> <td> "
						+list[k].ws10+ " </td> <td> "
						+list[k].ws20+ " </td> <td> "
						+list[k].ws30+ " </td> <td> "
						+list[k].ws40+ " </td> <td> "
						+list[k].wo50+ " </td> <td> "
						+list[k].unEl+ " </td> <td> "
						+list[k].miSc+ " </td> <td> "
						+list[k].miGr+ " </td> <td> "
						+list[k].hiGr+ " </td> <td> "
						+list[k].ovUn+ " </td> <td> "
						+list[k].hoWife+ " </td> <td> "
						+list[k].unIncome150+ " </td> <td> "
						+list[k].ovIncome150+ " </td> <td> "
						+list[k].income200+ " </td> <td> "
						+list[k].income300+ " </td> <td> "
						+list[k].income400+ " </td> <td> "
						+list[k].ovIncome500+ " </td> <td> "
						
						+ " </td>		</tr>" );
				}
				$("#tableGrid tbody tr").css('cursor', 'pointer');
				$("#tableGrid tbody tr").click(function() {
//					var pSeq = $(this).children().eq(0).attr('data');
//					modifyDiv(pSeq);
			});
			}else {
				$('#tableGrid > tbody:last ').append("<tr> <td colspan='42'> 검색된 내용이 없습니다. </td></tr>");
			}
		}
	</script>
	
</html>