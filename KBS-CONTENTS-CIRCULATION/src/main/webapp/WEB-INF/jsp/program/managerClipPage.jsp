<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<div class="nav">Home &gt; 프로그램관리 &gt; <span>클립관리</span></div>
				<div id="contents">
					<div class="content-bx" id="searchContents">
						<h3 class="depth10">클립관리</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="prname">검색조건</label>
									<ul>
										<li><select id="searchType" class="wx140">
											<option value="01">클립ID</option>
											<option value="02">클립명</option>
											<option value="03">프로그램명</option>
											<option value="04">프로그램ID</option>
											</select>
										</li>
										<li>
											<label for="prname-bx" class="sound_only">프로그램명</label>
											<input type="text" id="searchValue" class="wx140" />
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>검색</span></a>
						</div>
					</div>
					<div class="content-bx" id="listContents">
						<h3 class="depth10 type02">클립리스트</h3>
						<div class="tbl-bxn scroll-x">
							<table summary="순번, 프로그램ID, 클립명, 프로그램명, 방송사, 방영일 정보를 제공합니다" id="tableGrid">
								<caption>클립 리스트</caption>
								<thead>
									<tr>
										<th scope="col" width="70">순번</th>
										<th scope="col" width="250" >클립ID</th>
										<th scope="col" width="250" >클립명</th>
										<th scope="col" width="250" >프로그램명</th>
										<th scope="col" width="70" >조회수</th>
										<th scope="col" width="130" >방영일</th>
										<th scope="col" width="80" >상태</th>
										<th scope="col" width="80">선택</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td colspan='8'> 검색된 내용이 없습니다. </td>
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
					<div class="content-bx" id="saveContents">
						<h3 class="depth10">클립 상세</h3>
						<div class="tbl-write">
							<table summary="">
								<input type="hidden" id="clipSeq"/>
								<caption>글 작성</caption>
								<tbody>
									<tr>
										<th scope="row"><label for="programNm">프로그램명</label></th>
										<td>
											<input type="hidden" id="pSeq">
											<input type="text" id="programNm" class="wx340" />
											<a href="#" class="btn-sch" id="programSearchBtn">
												<span>프로그램 찾기</span>
											</a>
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="pr-turn">회별프로그램</label></th>
										<td>
											<input type="hidden" id="pcSeq">
											<input type="text" id="contentsNm" class="wx340" />
											<a href="#" class="btn-sch" id="contentsSearchBtn">
												<span>회별 프로그램 찾기</span>
											</a>
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="clipId">클립ID</label></th>
										<td><input type="text" id="clipId" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="clipNm">클립명</label></th>
										<td><input type="text" id="clipNm" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="viewDate">시청일자</label></th>
										<td><input type="text" id="viewDate" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="viewCnt">시청횟수</label></th>
										<td><input type="text" id="viewCnt" class="wx340" /></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="board-btn-set">
							<a href="#" class="change" id=saveBtn><span id=saveBtnText>변경</span></a>
							<a href="#" class="cancle" id="cancleBtn"><span>취소</span></a>
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
	
		var paging = new Array();
		var programList = new Array();
		var modifyDiv = new Array();
		$(document).ready(function() {
			
			
			$("#searchContents").show();
			$("#listContents").show();
			
			$("#saveContents").hide();
			
			
			$("#contentsSearchBtn").click(function() {
				var searchValue = $("#contentsNm").val();
				
				if(searchValue == '' && searchValue.length <2 ){
					alert("검색어를 2자이상 입력해주세요");
					return;
				}
				var url = "/program/layer/contentLayer?searchType=01&searchValue="+searchValue;
				window.open(url, 'contents', 'width=1100, height=600, scrollbars=yes' );
			});
			
			$( "#viewDate" ).datepicker({
				dateFormat: 'yy-mm-dd'
		    });
			
			$("#programSearchBtn").click(function() {
				
				var searchValue = $("#programNm").val();
				
				if(searchValue == '' && searchValue.length <2 ){
					alert("검색어를 2자이상 입력해주세요");
					return;
				}
				var url = "/program/layer/parentProgramLayer?&searchType=01&searchValue="+searchValue;
				window.open(url, 'program', 'width=1100, height=600, scrollbars=yes' );
			});
			
			$("#searchBtn").click(function() {
				search();
			});
			
			$("#addBtn").click(function() {
				iniSaveDiv();
				showSaveDiv();
			});
			
			$("#saveBtn").click(function() {
				saveProc();
			});
			
			$("#cancleBtn").click(function() {
				
				$("#searchContents").show();
				$("#listContents").show();
				$("#saveContents").hide();
				iniSaveDiv();
			});
			
			function search(){
				var searchType = $("#searchType").val();
				var searchValue = $("#searchValue").val();
				$.ajax({
					type :	"get",
					url  : 	"/program/getClipList",
					datatype : "json",
					async: true,
					data : {
						searchType 	: searchType,
						searchValue :searchValue 
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						programList = result.programList;
						paging = result.param;
						makeTable();
						
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});
				$("#tableGrid tbody tr").css('cursor', 'pointer');
				$("#tableGrid tbody tr").click(function() {
					var clipSeq = $(this).children().eq(0).attr('data');
					modifyDiv(clipSeq);
				});
				
			}
			
			function saveProc() {
				var clipSeq = $("#clipSeq").val();
				var clipId = $("#clipId").val();
				var clipNm = $("#clipNm").val();
				
				var pSeq = $("#pSeq").val();
				var programNm = $("#programNm").val();
				
				var pcSeq = $("#pcSeq").val();
				var contentsNm = $("#contentsNm").val();
				var viewDate = $("#viewDate").val();
				var viewCnt = $("#viewCnt").val();
				
				if(clipSeq == '') {
					clipSeq = 0;
				}
				
				if(pSeq == '') {
					alert('프로그램을 선택해주세요');
					return;
				}
				if(pcSeq == '') {
					alert('회별 프로그램을 선택해주세요');
					return;
				}
				if(clipId == '') {
					alert('클립ID를 입력해주세요');
					return;
				}
				if(clipNm == '') {
					alert('클립명를 입력해주세요');
					return;
				}
				
				$.ajax({
					type :	"post",
					url  : 	"/program/saveClip",
					datatype : "json",
					async: false,
					data : {
						clipSeq : clipSeq,
						clipId : clipId,
						clipNm : clipNm,
						
						pSeq : pSeq,
						programNm : programNm,
						
						pcSeq : pcSeq,
						contentsNm : contentsNm,
						viewDate : viewDate,
						viewCnt : viewCnt
					},
					success : function(result){
						$(location).attr('href', '/program/managerClip');
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
			
			modifyDiv = function (clipSeq){
				iniSaveDiv();
				showSaveDiv();
				
				for(var k = 0; k < programList.length; k++ ) {
					if(programList[k].clipSeq == clipSeq) {
						$("#saveBtnText").html("변경")
						$("#clipSeq").val(programList[k].clipSeq);
						$("#clipId").val(programList[k].clipId);
						$("#clipNm").val(programList[k].clipNm);
						
						
						$("#pSeq").val(programList[k].pseq);
						$("#programNm").val(programList[k].programNm);
						
						$("#pcSeq").val(programList[k].pcSeq);
						$("#contentsNm").val(programList[k].contentsNm);
						
						
						$("#viewDate").val(programList[k].viewDate);
						$("#viewCnt").val(programList[k].viewCnt);
						break;
					}
				}
			}
			
			function iniSaveDiv() {
				$("#saveBtnText").html("저장")
				
				$("#clipSeq").val('');
				$("#clipId").val('');
				$("#clipNm").val('');
				
				
				$("#pSeq").val('');
				$("#programNm").val('');
				
				$("#pcSeq").val('');
				$("#contentsNm").val('');
				
				
				$("#viewDate").val('');
				$("#viewCnt").val('');
				
			}
			
			function showSaveDiv(){
				
				$("#searchContents").hide();
				$("#listContents").hide();
				
				$("#saveContents").show();
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
				url  : 	"/program/getClipList",
				datatype : "json",
				async: false,
				data : {
					searchType : paging.searchType,
					searchValue : paging.searchValue,
					currentPageNo : currPage
				},
				success : function(result){
					programList = result.programList;
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
			if(programList.length > 0) {
				for(var k=0; k<programList.length; k++) {	
						
					var status = "";
					
					if(programList[k].status == 'A'){
						status = '업로드중';
					}else if(programList[k].status == 'U'){
						status = '업로드완료';
					}else if(programList[k].status == 'I'){
						status = '데이터반영중';
					}else if(programList[k].status == 'D'){
						status = '삭제';
					}else {
						
					}
										
					$('#tableGrid > tbody:last ').append(
						"<tr>"
						+"<td width='70' data='"+programList[k].clipSeq+"'>"+((k+1)+paging.offset)+"</td>"
						+"<td width='250' >"+programList[k].clipId+"</td>"
						+"<td width='250' >"+programList[k].clipNm+"</td>"
						+"<td width='250' >"+programList[k].programNm+"</td>"
						+"<td width='70' >"+programList[k].viewCnt+"</td>"
						+"<td width='130' >"+programList[k].viewDate+"</td>"
						+"<td width='80' >"+status+"</td>"
						+"<td> </td>  "
						+ " </tr>" );
				}
				
				$("#tableGrid tbody tr").click(function() {
					var clipSeq = $(this).children().eq(0).attr('data');
					modifyDiv(clipSeq);
				});
				
				formatRange();
			}else {
				$('#tableGrid > tbody:last ').append("<tr> <td colspan='8'> 검색된 내용이 없습니다. </td></tr>");
			}
		}
		
	</script>
</html>