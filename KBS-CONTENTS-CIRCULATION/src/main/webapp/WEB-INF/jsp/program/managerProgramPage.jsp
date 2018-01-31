<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<div class="nav">Home &gt; 프로그램관리 &gt; <span>프로그램관리</span></div>
				<div id="contents">
					<div class="content-bx" id="searchContents">
						<h3 class="depth10">프로그램관리</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="prname">검색조건</label>
									<ul>
										<li><select id="searchType" class="wx140">
											<option value="01">프로그램명</option>
											<option value="02">대표프로그램명</option>
											<option value="03">프로그램ID</option>
											</select>
										</li>
										<li>
											<label for="prname-bx" class="sound_only">프로그램명</label>
											<input type="text" id="searchValue" class="wx140" />
										</li>
									</ul>
								</li>
								<li><span>02</span>
									<ul>
										<li><span class="wx150">대표프로그램 설정여부</span>
											<input type="radio" id="pSeqType01" name = "pSeqType" value=""><label for="pSeqType01"><span></span>전체</label>
											<input type="radio" id="pSeqType02" name = "pSeqType" value="01"><label for="pSeqType02"><span></span>미설정</label>
											<input type="radio" id="pSeqType03" name = "pSeqType" value="02"><label for="pSeqType03"><span></span>설정</label>
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>검색</span></a>
						</div>
					</div>
					<div class="content-bx" id="listContents"> 
						<h3 class="depth10 type02">프로그램리스트</h3>
						<div class="tbl-bx2">
							<table summary="순번, 프로그램ID, 프로그램명, 대표프로그램명, 방송사, 채널명 정보를 제공합니다" id="tableGrid">
								<caption>프로그램리스트</caption>
								<colgroup>
									<col width="86" />
									<col width="168" />
									<col width="" />
									<col width="210" />
									<col width="123" />
									<col width="115" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">프로그램ID</th>
										<th scope="col">프로그램명</th>
										<th scope="col">대표프로그램명</th>
										<th scope="col">방송사</th>
										<th scope="col">채널명</th>
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
							<a href="#" class="btn-user-add" id="addBtn"><span>프로그램 추가</span></a>
						</div>
					</div>
					
					<div class="content-bx" id= "saveContents">
						<h3 class="depth7">프로그램 변경 설정</h3>
						<div class="tbl-write">
							<table summary="">
								<input type="hidden" id="pSeq" />
								<caption>글 작성</caption>
								<tbody>
									<tr>
										<th scope="row"><label for="programId">프로그램ID</label></th>
										<td><input type="text" id="programId" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="programNm">프로그램명</label></th>
										<td><input type="text" id="programNm" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="oProgramNm">원본 프로그램명</label></th>
										<td>
											<input type="hidden" id="opSeq"  />
											<input type="text" id="oProgramNm" class="wx340" />
											<a href="#" class="btn-sch" id="settingOriProgramBtn">
												<span>원본프로그램 찾기</span>
											</a>
										</td>
												
									</tr>
									<tr>
										<th scope="row"><label for="parentNm">대표 프로그램명</label></th>
										<td>
											<input type="hidden" id="parentPSeq"  />
											<input type="text" id="parentNm" class="wx340" />
											<a href="#" class="btn-sch" id="settingParentBtn">
												<span>대표 프로그램 찾기</span>
											</a>
										</td>
	
									</tr>
									<tr>
										<th scope="row"><label for="programType">프로그램타입</label></th>
										<td>
											<select id=programType class="wx340">
												<c:if test="${!empty broadTypeList}">
													<c:forEach items="${broadTypeList}" var="list" varStatus="status">
														<option value ="${list.codeId}"  >${list.codeNm}</option>
													</c:forEach>
												</c:if>
											</select>
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="braodId">방송사</label></th>
										<td>
											<select id=broadId class="wx340">
												<c:if test="${!empty broadList}">
													<c:forEach items="${broadList}" var="list" varStatus="status">
														<option value ="${list.codeId}"  >${list.codeNm}</option>
													</c:forEach>
												</c:if>
											</select>
										</td>
									</tr>
									<tr>
										<th scope="row"><label for="channelId">채널</label></th>
										<td>
											<select id=channelId class="wx340">
												<c:if test="${!empty channelList}">
													<c:forEach items="${channelList}" var="list" varStatus="status">
														<option value ="${list.codeId}"  >${list.codeNm}</option>
													</c:forEach>
												</c:if>
											</select>
										</td>
									</tr>
									
									<tr>
										<th scope="row"><label for="weekday">요일</label></th>
										<td><input type="text" id="weekday" class="wx340" /></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="board-btn-set" >
							<a href="#" class="save" id="saveBtn"><span>저장</span></a>
							<a href="#" class="cancle" id="cancleBtn"><span>취소</span></a>
						</div>
					</div>
					
					<div class="content-bx" id="listContents2">
						<h3 class="depth7 type02">프로그램명 비교 리스트</h3>
						<div class="tbl-bx2">
							<table summary="순번, 비교 프로그램명, 설정 정보를 제공합니다" id="tableGrid2">
								<caption>프로그램명 비교 리스트</caption>
								<colgroup>
									<col width="86">
									<col width="">
									<col width="120">
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col"> 비교 프로그램명</th>
										<th scope="col">설정</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
						<div class="btn-set">
							<a href="#" class="btn-user-add2" id="addExpbtn"><span>프로그램비교명 추가</span></a>
							<a href="#" class="btn-user-del wx90" id="deleteExpBtn"><span>삭제</span></a>
						</div>
					</div>
				</div>
			</div>
			<!-- 콘텐츠 끝 -->
		</div>
		<div id="dialog" > 
			<div class="content-bx">
				<h3 class="depth7 type02">원본 프로그램 비교추가</h3>
				<div class="tbl-bx2">
					<table >
						<caption>원본 프로그램추가</caption>
						<colgroup>
							<col width="86">
							<col width="">
						</colgroup>
						<tbody>
							<tr>
								<th scope="col"> 프로그램명</th>
								<td class='tdtype'> <input  id="expProgramNm" maxlength="70"/></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btn-set">
					<a href="#" class="btn-user-add2" id="saveExpProgramBtn"><span>추가</span></a>
				</div>
			</div>
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
		var broadList = new Array();
		var channelList = new Array();
		var useYnList = new Array();
		var broadTypeList = new Array();
		var programExpList = new Array();	
		var paging = new Array();
		var modifyDiv = new Array();
		$(document).ready(function() {
			
			makeList();
			search();
			
			$("#searchContents").show();
			$("#listContents").show();
			
			$("#saveContents").hide();
			$("#listContents2").hide();
			
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
				$("#listContents2").hide();
				iniSaveDiv();
			});
			
			$("#addExpbtn").click(function() {
				$( "#dialog" ).dialog('open');
			});
			
			$("#saveExpProgramBtn").click(function() {
				saveProc2();
			});
			
			$("#deleteExpBtn").click(function() {
				deleteProgramExp();
			});
			
			$('#dialog').dialog({
				closeText : '', 
			    autoOpen: false,
			    resizable: false,
			    width : "500px" ,
			    close : function (e){
			    	$( "#expProgramNm" ).val('');
			    	
			    }
			    
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
				var url = "/program/layer/parentProgramLayer?openrType=01&pSeqType=02&searchType=02&searchValue="+searchValue;
				window.open(url, 'program', 'width=1100, height=600, scrollbars=yes' );
			});
			
			$("#tableGrid tbody tr").click(function() {
				var pSeq = $(this).children().eq(0).attr('data');
				modifyDiv(pSeq);
			});
			
			function search(){
				var searchType = $("#searchType").val();
				var searchValue = $("#searchValue").val();
				var pSeqType = $(":input:radio[name=pSeqType]:checked").val(); 
				var offset = 1 ;
				if(pSeqType == 'undefined' || pSeqType == undefined){
					$("input[name=pSeqType]").eq(0).attr("checked", true);
				}
				
				$.ajax({
					type :	"get",
					url  : 	"/program/getProgramList",
					datatype : "json",
					async: false,
					data : {
						searchType 	: searchType,
						searchValue : searchValue,
						pSeqType	: pSeqType
						
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
					var pSeq = $(this).children().eq(0).attr('data');
					modifyDiv(pSeq);
				});
			}
			
			function search2(){
				
				var pSeq = $("#pSeq").val();
				if(pSeq == ''){
					pSeq = 0;
				}
				if(pSeq == 0) {
					alert('프로그램을 먼저 저장해주세요');
					$('#tableGrid2 > tbody:last ').append("<tr> <td colspan='3'> 검색된 내용이 없습니다. </td></tr>");
					return;
				}else {
					$.ajax({
						type :	"get",
						url  : 	"/program/getProgramExpList",
						datatype : "json",
						async: false,
						data : {
							pSeq : pSeq
						},
						success : function(result){
							programExpList = result.programExpList;
							
							$('#tableGrid2 > tbody').empty();
							
							if(programExpList.length > 0) {
								for(var k=0; k< programExpList.length; k++) {	
							
									$('#tableGrid2 > tbody:last ').append("<tr> <td data='"+programExpList[k].peSeq+"'>"+(k+1)+"</td>  <td class='tdtype'>" 
										+programExpList[k].expProgramNm+"</td>  <td class='tdtype'>"
										+" <input type='checkbox' id='use"+k+"' name ='opeSeq' value='"+programExpList[k].peSeq+"' > "
										+ " <label for='use"+k+"' class='ml0'><span></span></label>  </td>  </tr>" );
								}
							}else {
								$('#tableGrid2 > tbody:last ').append("<tr> <td colspan='3'> 검색된 내용이 없습니다. </td></tr>");
							}
						},
						error:function(request,status,error){
				      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				     	}
					});	
				}
			}
			
			
			function saveProc() {
				var pSeq = $("#pSeq").val();
				var programId =$("#programId").val();
				var programNm = $("#programNm").val();
				
				var opSeq = $("#opSeq").val();
				var oProgramId = $("#oProgramId").val();
				var oProgramNm = $("#oProgramNm").val();
				
				var parentPSeq = $("#parentPSeq").val();
				
				var programType = $("#programType").val();
				var broadId = $("#broadId").val();
				var channelId = $("#channelId").val();
				
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
				
				if(parentPSeq == '') {
					parentPSeq = 0;
				}
				
				if(programId == ''){
					alert('프로그램 ID를 입력해주세요');
					return;
				}
				
				if(programNm == ''){
					alert('프로그램명을 입력해주세요');
					return;
				}
				$.ajax({
					type :	"post",
					url  : 	"/program/saveProgram",
					datatype : "json",
					async: false,
					data : {
						pSeq   : pSeq	,						
						programId 	: programId,
						programNm :  programNm,
						opSeq 	: opSeq,
						parentPSeq : parentPSeq,
						programType : programType,
						broadId : broadId,
						cSeq : cSeq,
						cornerId : cornerId,
						channelId :  channelId,
						weekday : weekday
					},
					success : function(result){
						$(location).attr('href', '/program/managerProgram');
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
			
			function saveProc2() {
				var pSeq = $("#pSeq").val();
				var expProgramNm = $("#expProgramNm").val();
				
				if(pSeq == ''){
					alert('프로그램을 저장하고 진행해주세요.');
					return;
				}
				
				$.ajax({
					type :	"post",
					url  : 	"/program/saveProgramExp",
					datatype : "json",
					async: false,
					data : {
						pSeq   : pSeq	,	
						expProgramNm : expProgramNm
						
					},
					success : function(result){
						alert("추가되었습니다.");
						$( "#dialog" ).dialog('close');
						search2();
						
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
			
			function deleteProgramExp() {

				var pSeq = $("#pSeq").val();
				var peSeq = new Array();
				
				$("input[name=opeSeq]:checked").each(function() {
					peSeq.push( $(this).val());  
				});
				
				if(peSeq.length == 0 ) {
					alert('삭제 대상이 없습니다. 대상을 선택해 주세요');
					return;
				}
				
				$.ajaxSettings.traditional = true				
				$.ajax({
					type :	"post",
					url  : 	"/program/deleteProgramExp",
					datatype : "json",
					async: false,
					data : {
						peSeqList  : peSeq
					},
					success : function(result){
						alert("프로그램이 삭제 되었습니다.");
						search2();
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
			
			function makeList() {
				<c:forEach items="${broadList}" var="list">
					broadList.push( {code : "${list.codeId}", codeNm : "${list.codeNm}"});
				</c:forEach>
				
				<c:forEach items="${channelList}" var="list">
					channelList.push( {code : "${list.codeId}", codeNm : "${list.codeNm}"});
				</c:forEach>
				
				<c:forEach items="${broadTypeList}" var="list">
					broadTypeList.push( {code : "${list.codeId}", codeNm : "${list.codeNm}"});
				</c:forEach>
				
 				useYnList.push( { useYn : 'Y', useText: "사용" }  );
 				useYnList.push( { useYn : 'N', useText: "비사용" }  );
			} 
			
			modifyDiv =function (pSeq){
				iniSaveDiv();
				showSaveDiv();
				for(var k = 0; k < programList.length; k++ ) {
					if(programList[k].pseq == pSeq) {

						$("#saveBtnText").html("변경")
						
						$("#pSeq").val(programList[k].pseq);
						$("#programId").val(programList[k].programId);
						$("#programNm").val(programList[k].programNm);
						
						$("#opSeq").val(programList[k].opSeq);
						$("#oProgramId").val(programList[k].oprogramId);
						$("#oProgramNm").val(programList[k].oprogramNm);
						
						$("#parentPSeq").val(programList[k].parentPseq);
						$("#parentNm").val(programList[k].parentNm);
						
						$("#programType").val(programList[k].programType);
						$("#broadId").val(programList[k].broadId);
						$("#channelId").val(programList[k].channelId);
						
						$("#weekday").val(programList[k].weekday);
						
						break;
					}
				}
				search2();
			}
			
			function iniSaveDiv() {
				$("#saveBtnText").html("저장")
				
				$("#pSeq").val('');
				$("#programId").val('');
				$("#programNm").val('');
				
				$("#opSeq").val('');
				$("#oProgramId").val('');
				$("#oProgramNm").val('');
				
				$("#parentPSeq").val('');
				$("#parentNm").val('');
				
				$("#programType").val('');
				$("#broadId").val('');
				$("#channelId").val('');
				
				$("#weekday").val('');
				
			}
			
			function showSaveDiv(){
				
				$("#searchContents").hide();
				$("#listContents").hide();
				
				$("#saveContents").show();
				$("#listContents2").show();
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
				url  :  "/program/getProgramList",
				datatype : "json",
				async: false,
				data : {
					searchType 	: paging.searchType,
					searchValue : paging.searchValue,
					pSeqType	: paging.pseqType,
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
					
					if(broadList.length > 0) {
						var broadListSelectHtml  = "";
						for(var i=0; i < broadList.length; i++) {
							if(programList[k].broadId != broadList[i].code) {
							}else {
								broadListSelectHtml  += broadList[i].codeNm;
							}
						}
						
					}
					
					if(channelList.length > 0) {
						var channelListSelectHtml  = "";
						for(var i=0; i < channelList.length; i++) {
							if(programList[k].channelId != channelList[i].code) {
							}else {
								channelListSelectHtml  += channelList[i].codeNm;
							}
						}
					}
					
					$('#tableGrid > tbody:last ').append("<tr> <td data='"+programList[k].pseq+"'>"+((k+1)+paging.offset)+"</td>  <td >" 
						+programList[k].programId+"</td>  <td >"
						+programList[k].programNm+ " </td> <td>"
						+programList[k].parentNm+ " </td> <td>"
						+broadListSelectHtml+ " </td> <td>"
						+channelListSelectHtml+ "</td>		</tr>" );
				}
				$("#tableGrid tbody tr").click(function() {
					var pSeq = $(this).children().eq(0).attr('data');
					modifyDiv(pSeq);
				});
			}else {
				$('#tableGrid > tbody:last ').append("<tr> <td colspan='6'> 검색된 내용이 없습니다. </td></tr>");
			}
		}
	</script>
</html>