<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				<div class="nav">Home &gt; 회사관리 &gt; <span>회사관리</span></div>
				<div id="contents">
					<div class="content-bx" id ="searchContents">
						<h3 class="depth7">회사관리</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="cpname">검색조건</label>
									<ul>
										<li><select id="searchType" class="wx140">
												<option value="01">회사명</option>
												<option value="02">회사타입</option>
											</select>
										</li>
										<li>
											<label for="cpname-bx" class="sound_only">회사명</label>
											<input type="text" id="searchValue" class="wx140" />
											<select id="compnayType">
												<c:forEach items="${companyTypeList}" var="item" varStatus="status">
													<option value="${item.codeId}">${item.codeNm}</option>
												</c:forEach>
											</select>
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>검색</span></a>
						</div>
					</div>
					<div class="content-bx" id="listContents">
						<h3 class="depth7 type02">회사리스트</h3>
						<div class="tbl-bx2">
							<table summary="순번, 수급타입, 회사타입 정보를 제공합니다" id="tableGrid">
								<caption>사용자리스트</caption>
								<colgroup>
									<col width="86" />
									<col width="" />
									<col width="200" />
									<col width="200" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">회사명</th>
										<th scope="col">사용여부</th>
										<th scope="col">회사타입</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<div class="paging">
							
						</div>
						<div class="btn-set" >
							<a href="#" class="btn-user-add" id="addBtn"><span>회사 추가</span></a>
						</div>
					</div>
					<div class="content-bx" id="saveContents">
					<input type="hidden" id="cSeq">
						<h3 class="depth7">회사추가</h3>
						<div class="tbl-write">
							<table summary="">
								<caption>글 작성</caption>
								<tbody>
									<tr>
										<th scope="row"><label for="cp-name">회사명</label></th>
										<td><input type="text" id="cNm" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row" valign="top"><label for="cp-info">회사 설명</label></th>
										<td><textarea id="cDesc"></textarea></td>
									</tr>
									<tr>
										<th scope="row">사용 여부</th>
										<td>
											<input type="radio" id="use01" name=useYn value='Y'/><label for="use01" class="ml0"><span></span>사용</label>
											<input type="radio" id="use02" name=useYn value='N'/><label for="use02"><span></span>미사용</label>
										</td>
									</tr>
									<tr>
										<th scope="row">회사 타입</th>
										<td>
										<c:forEach items="${companyTypeList}" var="item" varStatus="status">
											<input type="radio" id="cp-type${status.index}" class="ml0" name="cType" value="${item.codeId}"><label for="cp-type${status.index}"><span></span>${item.codeNm}</label>
										</c:forEach>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="board-btn-set" >
							<a href="#" class="save" id="saveBtn"><span>저장</span></a>
							<a href="#" class="cancle" id="cancleBtn"><span>취소</span></a>
						</div>
					</div>
					<div class="content-bx" id="saveContents2">
						<h3 class="depth7 type02">수급리스트</h3>
						
						<div class="tbl-bx2">
							<table summary="순번, 수급타입, 회사타입 정보를 제공합니다" id="tableGrid2">
								<caption>사용자리스트</caption>
								<colgroup>
									<col width="86" />
									<col width="" />
									<col width="280" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">수급타입</th>
										<th scope="col">선택</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
						<div class="btn-set">
							<a href="#" class="btn-user-add2" id="distAddBtn"><span>추가</span></a>
							<a href="#" class="btn-user-del wx90" id="deltypeList"><span>삭제</span></a>
						</div>
					</div>
				</div>
			</div>    
			<!-- 콘텐츠 끝 -->
		</div>
<!-- 하단 시작 -->
		<div id="dialog" > 
			<div class="content-bx" id="listContents2">
				<h3 class="depth7 type02">수급리스트추가</h3>
				<div class="tbl-bx2">
					<table >
						<caption>수급리스트추가</caption>
						<colgroup>
							<col width="86">
							<col width="">
						</colgroup>
						<tbody>
							<tr>
								<th scope="col"> 수급타입</th>
								<td class='tdtype'> 
									<select  id=distType >
										<c:forEach items="${typeList}" var="list">
											<option value ="${list.codeId}" >${list.codeNm}</option>
									</c:forEach>
									</select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btn-set">
					<a href="#" class="btn-user-add2" id="saveTypeList"><span>추가</span></a>
				</div>
			</div>
		</div>
		<div id="ft">
			<div class="footer">
				COPYRIGHT(C) KBS. LTD. ALL RIGHTS RESERVED.
			</div>
		</div>
<!-- 하단 끝 -->
	</body>
	
	<script>
	
		var companyList = new Array();
		var useYnList = new Array();
		var companyTypeList = new Array();
		var typeList = new Array();
		var companyDistTypeList = new Array();	
		var paging = new Array();
		
		$(document).ready(function() {
			
			$("#searchValue").show();
			$("#compnayType").hide();
			
			$("#searchType").change(function() {
				
				var type = $("#searchType").val();
				if(type == '02') {
					$("#searchValue").hide();
					$("#compnayType").show();
				}else {
					$("#searchValue").show();
					$("#compnayType").hide();
				}
			});
			
			makeList();
			searchCompany();
			
			$("#searchContents").show();
			$("#listContents").show();
			$("#saveContents").hide();
			$("#saveContents2").hide();
			
			$("#cancleBtn").click(function() {
				
				$("#searchContents").show();
				$("#listContents").show();
				$("#saveContents").hide();
				$("#saveContents2").hide();
				
				iniSaveDiv();
			});
			
			$("#saveBtn").click(function() {
				saveProc();
			});
			
			$("#searchBtn").click(function() {
				searchCompany();
			});
			
			$("#addBtn").click(function() {
				iniSaveDiv();
				showSaveDiv();
			});
			
			$("#distAddBtn").click(function() {
				$( "#dialog" ).dialog('open');
			});
			
			$("#saveTypeList").click(function() {
				saveProc2()
			});
			
			$("#deltypeList").click(function() {
				deleteCode()
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
			
		});
		
		var saveProc = function () {

			var cSeq = $("#cSeq").val();
			var cNm = $("#cNm").val();
			var cDesc = $("#cDesc").val();
			var useYn =  $(":input:radio[name=useYn]:checked").val();
			var cType = $(":input:radio[name=cType]:checked").val();
			
			if(cNm == ''){
				alert('회사명을 입력해주세요');
				return;
			}
			
			if(useYn == ''){
				alert('사용 여부를 선택해주세요');
				return;
			}
			
			if(cType == ''){
				alert('타입을 선택해 주세요');
				return;
			}
			
			if(cSeq == ''){
				cSeq = 0;
			}
			
			$.ajax({
				type :	"post",
				url  : 	"/company/saveCompany",
				datatype : "json",
				async: false,
				data : {
					cSeq   : cSeq	,						
					cNm 	: cNm,
					cDesc :  cDesc,
					useYn 	: useYn,
					cType :  cType
				},
				success : function(result){
					$(location).attr('href', '/company/managerCompany');
				},
				error:function(request,status,error){
		      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     	}
			});	
		}
		
		var saveProc2 = function () {

			var cSeq = $("#cSeq").val();
			var distType = $("#distType").val();				
			var useYn = 'Y';
			
			if(cSeq == ''){
				alert('회사를 저장하고 이용해주세요')
				return;
			}
			
			if(companyDistTypeList.length > 0) {
				for(var k=0; k < companyDistTypeList.length; k++) {
					if(companyDistTypeList[k].distType == distType){
						alert('해당수급타입이 이미 존재합니다.');
						return;
					}
				}
			}
			$.ajax({
				type :	"post",
				url  : 	"/company/saveComapnyDistType",
				datatype : "json",
				async: false,
				data : {
					cSeq   : cSeq	,						
					distType :  distType,
					useYn 	: useYn,
				},
				success : function(result){
					$( "#dialog" ).dialog('close');
					alert('타입이 추각되었습니다.');
					search2();
				},
				error:function(request,status,error){
		      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     	}
			});	
		}
		
		var iniSaveDiv = function () {
			$("#cSeq").val('');
			$("#cNm").val('');
			$("#cDesc").val('');
			$("#cType").val('');
			$("#useYn").val('');
			
			$("input[name=useYn]").eq(0).attr("checked", true);
			$("input[name=cType]").eq(0).attr("checked", true);
			
			$('#tableGrid2 > tbody').empty();
		}
		
		var makeList = function () {
			<c:forEach items="${companyTypeList}" var="item">
				companyTypeList.push( {codeId : "${item.codeId}", codeNm : "${item.codeNm}"});
			</c:forEach>
			
			useYnList.push( { useYn : 'Y', useText: "사용" }  );
			useYnList.push( { useYn : 'N', useText: "비사용" }  );
			
			<c:forEach items="${typeList}" var="item">
				typeList.push( {codeId : "${item.codeId}", codeNm : "${item.codeNm}"});
			</c:forEach>
		}
		
		var modifyDiv = function (cSeq){
			iniSaveDiv();
			showSaveDiv();
			for(var k = 0; k < companyList.length; k++ ) {
				if(companyList[k].cseq == cSeq) {
					
					$("#cSeq").val(cSeq);
					$("#cNm").val(companyList[k].cnm);
					$("#cDesc").val(companyList[k].cdesc);
					
					$('input:radio[name="useYn"]').filter("[value='"+companyList[k].useYn+"']").attr('checked', true);
					$('input:radio[name="cType"]').filter("[value='"+companyList[k].ctype+"']").attr('checked', true);
					
					break;
				}
			}
			search2();
			
		}
		
		var showSaveDiv =  function (){
			
			$("#searchContents").hide();
			$("#listContents").hide();
			
			$("#saveContents").show();
			$("#saveContents2").show();
		}
		
		var search2 = function search() {
			var cSeq = $("#cSeq").val();
			
			if(cSeq == '') {
				$('#tableGrid2 > tbody').empty();
				$('#tableGrid2 > tbody:last ').append("<tr> <td colspan='3'> 검색된 내용이 없습니다. </td></tr>");
				return;
			}
			
			$.ajax({
				type :	"get",
				url  : 	"/company/getCompanyDistTypeList",
				datatype : "json",
				async: false,
				data : {
					cSeq : cSeq
				},
				success : function(result){
					companyDistTypeList = result.companyDistTypeList;
					
					$('#tableGrid2 > tbody').empty();
					
					if(companyDistTypeList.length > 0) {
							for(var k=0; k<companyDistTypeList.length; k++) {	
							
							if(typeList.length > 0) {
								var compnayDistTypeSelectHtml  = "";
								for(var i=0; i < typeList.length; i++) {
									if(companyDistTypeList[k].distType != typeList[i].codeId) {
									}else {
										compnayDistTypeSelectHtml  += typeList[i].codeNm;
									}
								}
								
							}
					
								$('#tableGrid2 > tbody:last ').append("<tr> <td data="+companyDistTypeList[k].cdySeq+">"+(k+1)+"</td>  <td>"
									+compnayDistTypeSelectHtml+ " </td> <td > "
									+" <input type='checkbox' id='cdySeq"+k+"' name ='cdySeq' value='"+companyDistTypeList[k].cdySeq+"' > "
									+ " <label for='cdySeq"+k+"' class='ml0'><span></span></label>  </td>  </tr>" );
						}
					}else {
						
						$('#tableGrid2 > tbody:last ').append("<tr> <td colspan='3'> 검색된 내용이 없습니다. </td> "
							+ "< </tr>");
						
					}
				},
				error:function(request,status,error){
		      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     	}
			});	
		}
		
		var searchCompany = function (){
			
			var searchType = $("#searchType").val();
			var searchValue = $("#searchValue").val();
			var companyType = $("#compnayType").val();
			
			if(searchType == '02') {
				searchValue = companyType;
			}
			
			$.ajax({
				type :	"get",
				url  : 	"/company/getCompanyList",
				datatype : "json",
				async: false,
				data : {
					searchType : searchType,
					searchValue : searchValue
				},
				beforeSend : function() {
					$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
				},
				complete : function() {
					$.unblockUI();	    
				},
				success : function(result){
					companyList = result.companyList;
					paging = result.param;
					makeTable();
				},
				error:function(request,status,error){
		      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     	}
			});
			
		}
		
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
				url  : 	"/company/getCompanyList",
				datatype : "json",
				async: false,
				data : {
					searchType : paging.searchType,
					searchValue : paging.searchValue,
					currentPageNo : currPage
				},
				success : function(result){
					companyList = result.companyList;
					paging = result.param;
					makePaging(paging);
					makeTable();
					
				},
				error:function(request,status,error){
		      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     	}
			});
		}
		
		function deleteCode(){
			var cdySeq = new Array();
			
			$("input[name=cdySeq]:checked").each(function() {
				cdySeq.push( $(this).val());  
			});
			
			
			if(cdySeq.length == 0 ){
				return;
			}
			
			$.ajaxSettings.traditional = true
			$.ajax({
				type :	"post",
				url  : 	"/company/deleteCompanyDist",
				datatype : "json",
				async: false,
				data : {
					cdySeq   : cdySeq
				},
				success : function(result){
					alert("삭제되었습니다.");
					search2();
					
				},
				error:function(request,status,error){
		      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     	}
			});	
			
		}
		
		function makeTable() {
			makePaging(paging);
			$('#tableGrid > tbody').empty();
			
			if(companyList.length > 0) {
					for(var k=0; k<companyList.length; k++) {	
					
					if(companyTypeList.length > 0) {
						var compnayTypeSelectHtml  = "";
						for(var i=0; i < companyTypeList.length; i++) {
							if(companyList[k].ctype != companyTypeList[i].codeId) {
							}else {
								compnayTypeSelectHtml  += companyTypeList[i].codeNm;
							}
						}
						
					}
					
					var useSelectHtml = "";
					for(var i=0; i < useYnList.length; i++) {
						if(companyList[k].useYn != useYnList[i].useYn) {
						}else {
							useSelectHtml += useYnList[i].useText;
						}
					}
					
						
					$('#tableGrid > tbody:last ').append("<tr> <td data="+companyList[k].cseq+">"+((k+1)+paging.offset)+"</td>  <td >" 
						+companyList[k].cnm+"</td>  <td >"+useSelectHtml+"</td>   <td >"
						+compnayTypeSelectHtml+"</td>   </tr>" );
				}
				$("#tableGrid tbody tr").css('cursor', 'pointer');
				$("#tableGrid tbody tr").click(function() {
					var cSeq = $(this).children().eq(0).attr('data');
					modifyDiv(cSeq);
				});	
			}else {
				$('#tableGrid > tbody:last ').append("<tr> <td colspan='4'> 검색된 내용이 없습니다. </td></tr>");
			}
		}
		
	</script>
	
</html>