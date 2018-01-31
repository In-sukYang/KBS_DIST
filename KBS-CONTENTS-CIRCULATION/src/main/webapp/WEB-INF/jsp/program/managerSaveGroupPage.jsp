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
		<link rel="stylesheet" href="/resources/css/jquery-ui.css">
		<script type="text/javascript" src="/resources/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/resources/js/common.js"></script>
		<script type="text/javascript" src="/resources/js/jquery-ui.js"></script>
		<script type="text/javascript" src="/resources/js/jquery.blockUI.js"></script>
		<!--[if lte IE 8]>
		<script type="text/javascript" src="/resources/js/html5.js"></script>
		<![endif]-->
		<style>
			.bar {
			    height: 18px;
			    background: green;
			}
		</style>
		
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
				<div class="nav">Home &gt; 프로그램관리 &gt; <span>프로그램 이용현황 EXCEL 업로드</span></div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth10">프로그램 이용현황 EXCEL 리스트</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="cSeq">등록일</label>
									<ul>
										<li>
											<input type="text" id="searchValue" class="wx110" />
										</li>
									</ul>
								</li>
								<li><span>02</span><label for="distType">수급타입</label>
									<ul>
										<li>
											<select id="distType" class="wx180">
												<c:if test="${!empty distTypeList}">
													<c:forEach items="${distTypeList}" var="list" varStatus="status">
														<option value ="${list.codeId}"  >${list.codeNm}</option>
													</c:forEach>
												</c:if>
											</select>
										</li>
									</ul>
								</li>
								<li><span>03</span><label for="status">상태</label>
									<ul>
										<li>
											<select id="status" class="wx180">
												<option value="A">업로드중</option>
												<option value="U">업로드완료</option>
												<option value="D">데이터삭제</option>
												<option value="E">입력중에러</option>
											</select>
										</li>
									</ul>
								</li>
								
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>검색</span></a>
						</div>
					</div>
					<div class="content-bx" id="listContents">
						<h3 class="depth10 type02"></h3>
						<div class="tbl-bxn scroll-x">
							<table summary="" id="tableGrid">
								<caption>리스트</caption>
								<thead>
									<tr>
										<th scope="col" width="70">순번</th>
										<th scope="col" width="250" >회사명</th>
										<th scope="col" width="250" >수급타입</th>
										<th scope="col" width="200" >상태</th>
										<th scope="col" width="130" >등록일</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan='5'> 검색된 내용이 없습니다. </td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="paging" id="paging">
							
						</div>
						<div class="btn-set">
							<a href="#" class="btn-user-add" id="addBtn"><span>클립추가</span></a>
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
			
			$( "#searchValue" ).val($.datepicker.formatDate('yy-mm-dd', new Date()));
			
			$( "#searchValue" ).attr("readonly", true);
			
			$("#searchBtn").click(function () {
				search();
			});
			
			function search() {
				
				var inputDate = $("#searchValue").val();
				var status = $("#status").val();
				var distType = $("#distType").val(); 
				
				$.ajax({
					type :	"get",
					url  : 	"/program/getSaveGroupList",
					datatype : "json",
					async: true,
					data : {
						inputDate 	: inputDate,
						status :status,
						distType :distType
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
			console.log(paging);
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
				url  : 	"/program/getSaveGroupList",
				datatype : "json",
				async: false,
				data : {
					inputDate 	: paging.inputDate,
					status :paging.status,
					distType :paging.distType,
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
		
		function makeTable(){
			makePaging(paging);
			$('#tableGrid > tbody').empty();
			if(list.length > 0) {
				for(var k=0; k<list.length; k++) {	
						
					var status = "";
					
					if(list[k].status == 'A'){
						status = '업로드중';
					}else if(list[k].status == 'U'){
						status = '업로드완료';
					}else if(list[k].status == 'I'){
						status = '데이터반영중';
					}else if(list[k].status == 'D'){
						status = '삭제';
					}else {
						status = '에러';
					}
										
					$('#tableGrid > tbody:last ').append(
						"<tr>"
						+"<td width='70' data='"+list[k].tgSeq+"'>"+((k+1)+paging.offset)+"</td>"
						+"<td width='250' >"+list[k].cnm+"</td>"
						+"<td width='250' >"+list[k].distTypeNm+"</td>"
						+"<td width='200' >"+status+"</td>"
						+"<td width='130' >"+list[k].inputDt+"</td>"
						+ " </tr>" );
				}
				
				formatRange();
			}else {
				$('#tableGrid > tbody:last ').append("<tr> <td colspan='5'> 검색된 내용이 없습니다. </td></tr>");
			}
		}
	</script>
	
</html>