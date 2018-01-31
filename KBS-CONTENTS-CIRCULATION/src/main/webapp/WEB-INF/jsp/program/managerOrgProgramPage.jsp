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
				<div class="nav">Home &gt; 프로그램관리 &gt; <span>원본프로그램 관리</span></div>
				<div id="contents">
					<div class="content-bx" id ="searchContents">
						<h3 class="depth7">원본 프로그램관리</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="prname">검색조건</label>
									<ul>
										<li><select id="searchType" class="wx140">
											<option value="01">원본 프로그램ID</option>
											<option value="02">원본 프로그램명</option>
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
						<h3 class="depth7 type02">원본 프로그램리스트</h3>
						<div class="tbl-bx2">
							<table summary="순번, 프로그램ID, 원본프로그램명, 프로그램타입, 최종변경일 정보를 제공합니다" id="tableGrid">
								<caption>원본 프로그램 리스트</caption>
								<colgroup>
									<col width="86" />
									<col width="168" />
									<col width="" />
									<col width="210" />
									<col width="235" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">프로그램ID</th>
										<th scope="col">원본프로그램명</th>
										<th scope="col">프로그램타입</th>
										<th scope="col">채널명</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<div class="paging" id="paging">
							
						</div>
						<div class="btn-set">
							<a href="#" class="btn-user-add2" id=addBtn><span>원본 프로그램 추가</span></a>
						</div>
					</div>
					<div class="content-bx" id= "saveContents">
						<h3 class="depth7">원본 프로그램 추가</h3>
						<div class="tbl-write">
							<table summary="">
								<input type="hidden" id="opSeq" />
								<caption>글 작성</caption>
								<tbody>
									<tr>
										<th scope="row"><label for="oProgramId">원본 프로그램ID</label></th>
										<td><input type="text" id="oProgramId" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="oProgramNm">원본 프로그램명</label></th>
										<td><input type="text" id="oProgramNm" class="wx340" /></td>
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
							<a href="#" class="save" id="saveBtn"><span id="saveBtnText">저장</span></a>
							<a href="#" class="cancle" id="cancleBtn"><span>취소</span></a>
						</div>
					</div>
					<div class="content-bx" id="listContents2">
						<h3 class="depth7 type02">원본 프로그램명 비교 리스트</h3>
						<div class="tbl-bx2">
							<table summary="순번, 비교 프로그램명, 설정 정보를 제공합니다" id="tableGrid2">
								<caption>원본 프로그램명 비교 리스트</caption>
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
							<a href="#" class="btn-user-add2" id="addOrgExpbtn"><span>프로그램비교명 추가</span></a>
							<a href="#" class="btn-user-del wx90" id="deleteOrgExpBtn"><span>삭제</span></a>
						</div>
					</div>
				</div>
			</div>    
			<!-- 콘텐츠 끝 -->
		</div>
		<div id="dialog" > 
			<div class="content-bx" ">
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
	
		var orgProgramList = new Array();
		var broadTypeList = new Array();
		var channelList = new Array();
		var orgProgramExpList = new Array();
		var paging = new Array();
		var modifyDiv = new Array();
		
		$(document).ready(function() {
			
		
			
			$("#searchContents").show();
			$("#listContents").show();
			
			$("#saveContents").hide();
			$("#listContents2").hide();
			
			makeList();
			search();
			
			$("#searchBtn").click(function() {
				search();
			});
			
			$("#cancleBtn").click(function() {
				
				$("#searchContents").show();
				$("#listContents").show();
				$("#saveContents").hide();
				$("#listContents2").hide();
				iniSaveDiv();
			});
			
			$("#addBtn").click(function() {
				iniSaveDiv();
				showSaveDiv();
			});
			
			$("#saveBtn").click(function() {
				saveProc();
			});
			
			$("#deleteOrgExpBtn").click(function() {
				deleteOrgProgramExp();
			});
			
			$("#addOrgExpbtn").click(function() {
				$( "#dialog" ).dialog('open');
			});
		
			$("#saveExpProgramBtn").click(function() {
				saveProc2();
			});
			
			
			$('#dialog').dialog({
			    autoOpen: false,
			    resizable: false,
			    width : "500px" ,
			    closeText : '',
			    close : function (e){
			    	$( "#expProgramNm" ).val('');
			    	
			    }
			    
		  	});
			
			function search(){
				var searchType = $("#searchType").val();
				var searchValue = $("#searchValue").val();
				$.ajax({
					type :	"get",
					url  : 	"/program/getOrgProgramList",
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
						orgProgramList = result.orgProgramList;
						paging = result.param;
						makeTable();
						
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
				$("#tableGrid tbody tr").css('cursor', 'pointer');
				$("#tableGrid tbody tr").click(function() {
					var opSeq = $(this).children().eq(0).attr('data');
					modifyDiv(opSeq);
				});
			}
			
			function search2(){
				
				var opSeq = $("#opSeq").val();
				
				if(opSeq == ''){
					opSeq = 0;
				}
				
				if(opSeq == 0) {
					$('#tableGrid2 > tbody:last ').append("<tr> <td colspan='3'> 검색된 내용이 없습니다. </td></tr>");
					return;
				}else {
					$.ajax({
						type :	"get",
						url  : 	"/program/getOrgProgramExpList",
						datatype : "json",
						async: false,
						data : {
							opSeq : opSeq
						},
						success : function(result){
							orgProgramExpList = result.orgProgramExpList;
							
							$('#tableGrid2 > tbody').empty();
							
							if(orgProgramExpList.length > 0) {
								for(var k=0; k< orgProgramExpList.length; k++) {	
							
									$('#tableGrid2 > tbody:last ').append("<tr> <td data='"+orgProgramExpList[k].opeSeq+"'>"+(k+1)+"</td>  <td class='tdtype'>" 
										+orgProgramExpList[k].expProgramNm+"</td>  <td class='tdtype'>"
										+" <input type='checkbox' id='use"+k+"' name ='opeSeq' value='"+orgProgramExpList[k].opeSeq+"' > "
										+ " <label for='use"+k+"' class='ml0'><span></span></label>  </td>  </tr>" );
								}
							}else {
								$('#tableGrid2> tbody:last ').append("<tr> <td colspan='3'> 검색된 내용이 없습니다. </td></tr>");
							}
						},
						error:function(request,status,error){
				      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				     	}
					});	
				}
			}
			
			function saveProc() {
				var opSeq = $("#opSeq").val();
				var oProgramId = $("#oProgramId").val();
				var oProgramNm = $("#oProgramNm").val();
				var programType = $("#programType").val();
				var channelId = $("#channelId").val();
				
				var weekday = $("#weekday").val();
				
				if(oProgramId == ''){
					alert('원본 프로그램ID을 입력해주세요');
					return;
				}
				
				if(oProgramNm == ''){
					alert('원본 프로그램명을 선택해 주세요');
					return;
				}
				
				if(opSeq == ''){
					opSeq = 0;
				}
				
				$.ajax({
					type :	"post",
					url  : 	"/program/saveOrgProgram",
					datatype : "json",
					async: false,
					data : {
						opSeq   : opSeq	,						
						oProgramId 	: oProgramId,
						oProgramNm :  oProgramNm,
						programType 	: programType,
						channelId :  channelId,
						weekday : weekday
					},
					success : function(result){
						$(location).attr('href', '/program/managerOrgProgram');
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
			
			function saveProc2() {
				var opSeq = $("#opSeq").val();
				var expProgramNm = $("#expProgramNm").val();
				
				if(opSeq == ''){
					alert('원본 프로그램을 저장하고 진행해주세요.');
					return;
				}
				
				$.ajax({
					type :	"post",
					url  : 	"/program/saveOrgProgramExp",
					datatype : "json",
					async: false,
					data : {
						opSeq   : opSeq	,	
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
			
			
			function deleteOrgProgramExp() {

				var opSeq = $("#opSeq").val();
				
				var opeSeq = new Array();
				
				$("input[name=opeSeq]:checked").each(function() {
					opeSeq.push( $(this).val());  
				});
				
				if(opeSeq.length == 0 ) {
					alert('삭제 대상이 없습니다. 대상을 선택해 주세요');
					return;
				}
				
				$.ajaxSettings.traditional = true				
				$.ajax({
					type :	"post",
					url  : 	"/program/deleteOrgProgramExp",
					datatype : "json",
					async: false,
					data : {
						opeSeq  : opeSeq
					},
					success : function(result){
						alert("비교 프로그램이 삭제 되었습니다.");
						search2();
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
			
			function makeList() {
				<c:forEach items="${broadTypeList}" var="list">
					broadTypeList.push( {code : "${list.codeId}", codeNm : "${list.codeNm}"});
				</c:forEach>
				
				<c:forEach items="${channelList}" var="list">
					channelList.push( {code : "${list.codeId}", codeNm : "${list.codeNm}"});
				</c:forEach>
				
			} 
			
			function showSaveDiv(){
				
				$("#searchContents").hide();
				$("#listContents").hide();
				
				
				$("#saveContents").show();
				$("#listContents2").show();
			}
			
			function iniSaveDiv() {
				$("#saveBtnText").html("저장")
				$("#opSeq").val('');
				$("#oProgramId").val('');
				$("#oProgramNm").val('');
				$("#programType").val('');
				$("#channelId").val('');
				
				$("#weekday").val('');
				
				$('#tableGrid2 > tbody').empty();
			}
			
			modifyDiv = function(opSeq){
				iniSaveDiv();
				showSaveDiv();
				
				$("#saveBtnText").html("변경")
				for(var k = 0; k < orgProgramList.length; k++ ) {
					if(orgProgramList[k].opSeq == opSeq) {
	
						$("#opSeq").val(opSeq);
						$("#oProgramId").val(orgProgramList[k].oprogramId);
						$("#oProgramNm").val(orgProgramList[k].oprogramNm);
						$("#programType").val(orgProgramList[k].programType);
						$("#channelId").val(orgProgramList[k].channelId);
						$("#weekday").val(orgProgramList[k].weekday);
						
						break;
					}
				}
				search2();
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
				url  : 	"/program/getOrgProgramList",
				datatype : "json",
				async: false,
				data : {
					searchType 	: paging.searchType,
					searchValue : paging.searchValue,
					currentPageNo : currPage
				},
				success : function(result){
					orgProgramList = result.orgProgramList;
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
			
			if(orgProgramList.length > 0) {
				for(var k=0; k<orgProgramList.length; k++) {	
			
					if(broadTypeList.length > 0) {
						var broadTypeListSelectHtml  = "";
						for(var i=0; i < broadTypeList.length; i++) {
							if(orgProgramList[k].programType != broadTypeList[i].code) {
							}else {
								broadTypeListSelectHtml  += broadTypeList[i].codeNm;
							}
						}
					}
					
					var channelHtml = "";
					if(channelList.length > 0) {
						for(var i=0; i < channelList.length; i++) {
							if(orgProgramList[k].channelId == channelList[i].code) {
								channelHtml = channelList[i].codeNm;
							}
						}
						
					}
					
					$('#tableGrid > tbody:last ').append("<tr> <td data='"+orgProgramList[k].opSeq+"'>"+((k+1)+paging.offset)+"</td>  <td class='tdtype'>" 
						+orgProgramList[k].oprogramId+"</td>  <td class='tdtype'>"
						+orgProgramList[k].oprogramNm+"</td>  <td class='tdtype'>"
						+broadTypeListSelectHtml+ "</td> <td class='tdtype'>"
						+channelHtml +"</td>    </tr>" );
				}
				$("#tableGrid tbody tr").click(function() {
					var opSeq = $(this).children().eq(0).attr('data');
					modifyDiv(opSeq);
				});
			}else {
				$('#tableGrid > tbody:last ').append("<tr> <td colspan='5'> 검색된 내용이 없습니다. </td></tr>");
			}
		}
	</script>
</html>