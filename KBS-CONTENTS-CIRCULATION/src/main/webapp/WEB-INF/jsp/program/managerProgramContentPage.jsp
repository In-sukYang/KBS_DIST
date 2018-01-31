<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<link rel="stylesheet" href="/resources/css/time.css">
		<script type="text/javascript" src="/resources/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/resources/js/common.js"></script>
		<script type="text/javascript" src="/resources/js/jquery-ui.js"></script>
		<script type="text/javascript" src="/resources/js/time.js"></script>
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
				<div class="nav">Home  &gt; 프로그램관리 &gt; <span>회별프로그램 상세 관리</span></div>
				<div id="contents">
					<div class="content-bx" id="searchContents">
						<h3 class="depth10">회별프로그램 상세 관리</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="prname">검색조건</label>
									<ul>
										<li><select id="searchType" class="wx140">
											<option value="01">회별 프로그램명</option>
											<option value="02">회별 프로그램ID</option>
											<option value="03">방송일자</option>
											<option value="04">프로그램명</option>
											</select>
										</li>
										<li>
											<label for="prname-bx" class="sound_only">프로그램명</label>
											<input type="text" id="searchValue" class="wx140" />
											<input type="text" id="searchDate" class="wx140" />
											
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>검색</span></a>
						</div>
					</div>
					<div class="content-bx" id="listContents">
						<h3 class="depth10 type02">회별프로그램 리스트</h3>
						<div class="tbl-bx2">
							<table summary="순번, 프로그램ID, 회별프로그램명, 회차, 방송일자, 프로그램ID 정보를 제공합니다" id= "tableGrid">
								<caption>원본 프로그램 리스트</caption>
								<colgroup>
									<col width="86" />
									<col width="168" />
									<col width="" />
									<col width="86" />
									<col width="150" />
									<col width="215" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">회별 프로그램 ID</th>
										<th scope="col">회별프로그램명</th>
										<th scope="col">회차</th>
										<th scope="col">방송일자</th>
										<th scope="col">프로그램ID</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
						<div class="paging">
							<a href="#" class="first">처음</a>
							<a href="#" class="prev">이전</a>
							<strong>1</strong>
							<a href="#">2</a>
							<a href="#">3</a>
							<a href="#" class="next">다음</a>
							<a href="#" class="end">맨끝</a>
						</div>
						<div class="btn-set">
							<a href="#" class="btn-user-add2" id="addBtn"><span>회별 프로그램 추가</span></a>
						</div>
					</div>
					<div class="content-bx" id="saveContents">
						<h3 class="depth10">회별 프로그램 상세</h3>
						<div class="tbl-write">
							<table summary="">
								<input type="hidden" id="pcSeq">
								<caption>글 작성</caption>
								<tbody>
									<tr>
										<th scope="row"><label for="contentsId">회별프로그램ID</label></th>
										<td><input type="text" id="contentsId" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="contentsNm">회별프로그램명</label></th>
										<td><input type="text" id="contentsNm" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="vodcnt">회차</label></th>
										<td><input type="text" id="vodcnt" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="oProgramNm">원본 프로그램명</label></th>
										<td>
											<input type="hidden" id="opSeq"  />
											<input type="text" id="oProgramNm" class="wx340" />
											<a href="#" class="btn-sch" id="settingOriProgramBtn">
												<span>원본 프로그램 찾기</span>
											</a>
										</td>
												
									</tr>
									<tr>
										<th scope="row"><label for="programNm">프로그램명</label></th>
										<td>
											<input type="hidden" id="pSeq"  />
											<input type="text" id="programNm" class="wx340" />
											<a href="#" class="btn-sch" id="settingParentBtn">
												<span>프로그램 찾기</span>
											</a>
										</td>
	
									</tr>
									<tr>
										<th scope="row"><label for="broadDate">방송일자</label></th>
										<td><input type="text" id="broadDate" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="broadStdt">방송시작시간</label></th>
										<td><input type="text" id="broadStdt" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="broadEddt">방송종료시간</label></th>
										<td><input type="text" id="broadEddt" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="weekday">요일</label></th>
										<td><input type="text" id="weekday" class="wx340" /></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="board-btn-set">
							<a href="#" class="change" id="saveBtn"><span id="saveBtnText">변경</span></a>
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

			$("#searchDate").hide();
			
			$("#searchType").change(function() {
				
				$("#searchValue").val('');
				var type = $("#searchType").val();
				
				if(type == '03'){
					$("#searchDate").show();
					$("#searchValue").hide();
				}else {
					$("#searchDate").hide();
					$("#searchValue").show();
				}
			});
			
			search();
			
			$("#searchContents").show();
			$("#listContents").show();
			
			$("#saveContents").hide();
			
			$( "#broadDate" ).datepicker({
				dateFormat: 'yy-mm-dd'
		    });
			
			$( "#searchDate" ).datepicker({
				dateFormat: 'yy-mm-dd'
		    });
			
			$( "#broadStdt" ).datetimepicker({
			//	format: 'DDMMYYYY HH:mm:ss'
				dateFormat: 'yy-mm-dd', 
		        timeFormat: 'HH:mm:ss'
			});
				
		    
			$( "#broadEddt" ).datetimepicker({
				//	format: 'DDMMYYYY HH:mm:ss'
					dateFormat: 'yy-mm-dd', 
			        timeFormat: 'HH:mm:ss'
			});
			
			$("#cancleBtn").click(function() {
				
				$("#searchContents").show();
				$("#listContents").show();
				$("#saveContents").hide();
				iniSaveDiv();
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
			
			$("#tableGrid tbody tr").click(function() {
				var pcSeq = $(this).children().eq(0).attr('data');
				modifyDiv(pcSeq);
			});
			
			
			$("#settingOriProgramBtn").click(function() {
				var searchValue = $("#oProgramNm").val();
				
				if(searchValue == '' && searchValue.length <2 ){
					alert("검색어를 2자이상 입력해주세요");
					return;
				}
				var url = "/program/layer/orgProgramLayer?searchType=02&searchValue="+searchValue;
				window.open(url, 'orgProgram', 'width=1100, height=600, scrollbars=yes' );
			});
			
			$("#settingParentBtn").click(function() {
				var searchValue = $("#programNm").val();
				
				if(searchValue == '' && searchValue.length <2 ){
					alert("검색어를 2자이상 입력해주세요");
					return;
				}
				var url = "/program/layer/parentProgramLayer?pSeqType=01&searchType=01&searchValue="+searchValue;
				window.open(url, 'program', 'width=1100, height=600, scrollbars=yes' );
			});
			
			
			function search(){
				var searchType = $("#searchType").val();
				var searchValue = $("#searchValue").val();
				var searchDate = $("#searchDate").val();
				
				searchDate = searchDate.replace(/-/gi, '');
				
				if(searchType == '03'){
					searchValue = searchDate;
				}
					
				$.ajax({
					type :	"get",
					url  : 	"/program/getProgramContentList",
					datatype : "json",
					async: false,
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
				
			}
			
			function saveProc() {
				var pcSeq = $("#pcSeq").val();
				var contentsId = $("#contentsId").val();
				var contentsNm = $("#contentsNm").val();
				
				var vodcnt =  $("#vodcnt").val();
				var broadDate = $("#broadDate").val();
				var broadStdt = $("#broadStdt").val();
				var broadEddt = $("#broadEddt").val();
								
				var opSeq = $("#opSeq").val();
				var oProgramId = $("#oProgramId").val();
				var oProgramNm = $("#oProgramNm").val();
				
				var programId = $("#programId").val();
				var programNm = $("#programNm").val();
				
				
				var weekday = $("#weekday").val();
				
				var cSeq = "";
				var cornerId = "";
				
				if(pSeq == ''){
					pSeq = 0;
				}
				
				if(cSeq == ''){
					cSeq = 0;
				}
				
				if(opSeq == ''){
					opSeq = 0;
				}
				
				if(pcSeq == '') {
					pcSeq = 0;
				}
				
				if(contentsId == ''){
					alert('컨텐츠 ID를 입력해주세요');
					return;
				}
				
				if(contentsNm == ''){
					alert('컨텐츠명을 입력해주세요');
					return;
				}
				$.ajax({
					type :	"post",
					url  : 	"/program/saveProgramContent",
					datatype : "json",
					async: false,
					data : {
						pcSeq  : pcSeq,
						contentsId : contentsId,
						contentsNm :contentsNm,
						
						vodcnt :vodcnt,
						broadDate :broadDate,
						broadStdt :broadStdt,
						broadEddt :broadEddt,
										
						opSeq :opSeq,
						oProgramId :oProgramId,
						oProgramNm :oProgramNm,
						
						programId : programId,
						programNm : programNm,
						weekday :  weekday,
						cSeq : cSeq,
						cornerId : cornerId
					},
					success : function(result){
						$(location).attr('href', '/program/managerProgramContent');
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
			
			
			modifyDiv = function (pcSeq){
				iniSaveDiv();
				showSaveDiv();
				
				for(var k = 0; k < programList.length; k++ ) {
					if(programList[k].pcSeq == pcSeq) {

						$("#saveBtnText").html("변경")
						$("#pcSeq").val(programList[k].pcSeq);
						$("#contentsId").val(programList[k].contentsId);
						$("#contentsNm").val(programList[k].contentsNm);
						
						$("#vodcnt").val(programList[k].vodcnt);
						$("#broadDate").val(programList[k].broadDate);
						$("#broadStdt").val(programList[k].broadStdt);
						$("#broadEddt").val(programList[k].broadEddt);
										
						$("#opSeq").val(programList[k].opSeq);
						$("#oProgramId").val(programList[k].oprogramId);
						$("#oProgramNm").val(programList[k].oprogramNm);
						
						$("#programId").val(programList[k].programId);
						$("#programNm").val(programList[k].programNm);
						
						$("#weekday").val(programList[k].weekday);
						break;
					}
				}
			}
			
			function iniSaveDiv() {
				$("#saveBtnText").html("저장")
				
				$("#pcSeq").val('');
				$("#contentsId").val('');
				$("#contentsNm").val('');
				
				$("#vodcnt").val('');
				$("#broadDate").val('');
				$("#broadStdt").val('');
				$("#broadEddt").val('');
								
				$("#opSeq").val('');
				$("#oProgramId").val('');
				$("#oProgramNm").val('');
				
				$("#programId").val('');
				$("#programNm").val('');
				
				$("#weekday").val('');
				
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
				url  :  "/program/getProgramContentList",
				datatype : "json",
				async: false,
				data : {
					searchType 	: paging.searchType,
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
		
		function makeTable() {
			makePaging(paging);
			
			$('#tableGrid > tbody').empty();
			
			if(programList.length > 0) {
				for(var k=0; k<programList.length; k++) {	
					
					$('#tableGrid > tbody:last ').append("<tr> <td data='"+programList[k].pcSeq+"'>"+((k+1)+paging.offset)+"</td>  <td >" 
						+programList[k].contentsId+"</td>  <td >"
						+programList[k].contentsNm+"</td>  <td >"
						+programList[k].vodcnt+"</td>  <td >"
						+programList[k].broadDate+"</td>  <td >"
						+programList[k].programId+"</td>   </tr>" );
				}
				$("#tableGrid tbody tr").css('cursor', 'pointer');
				$("#tableGrid tbody tr").click(function() {
					var pcSeq = $(this).children().eq(0).attr('data');
					modifyDiv(pcSeq);
				});
			}else {
				$('#tableGrid > tbody:last ').append("<tr> <td colspan='6'> 검색된 내용이 없습니다. </td></tr>");
			}
		}
		
	</script>
</html>