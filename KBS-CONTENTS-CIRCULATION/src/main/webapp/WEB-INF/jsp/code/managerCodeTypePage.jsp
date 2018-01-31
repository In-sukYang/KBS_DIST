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
				<div class="nav">Home &gt; 코드관리 &gt; <span>코드관리</span></div>
				<div id="contents">
					<div class="content-bx" id="searchContents">
						<h3 class="depth9">코드관리</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="codename">검색조건</label>
									<ul>
										<li><select id="searchType" class="wx140">
												<option value="01">코드타입</option>
												<option value="02">코드타입명명</option>
											</select>
										</li>
										<li>
											<label for="cpname-bx" class="sound_only">회사명</label>
											<input type="text" id="searchValue" class="wx140" />
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>검색</span></a>
						</div>
					</div>
					<div class="content-bx" id="listContents">
						<h3 class="depth9 type02">코드 리스트</h3>
						<div class="tbl-bx2">
							<table summary="코드ID, 코드명, 설정 정보를 제공합니다" id="tableGrid">
								<caption>사용자리스트</caption>
								<colgroup>
									<col width="86" />
									<col width="" />
									<col width="120" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">코드ID</th>
										<th scope="col">코드명</th>
										<th scope="col">설정</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>Y</td>
										<td class="tdtype">사용</td>
										<td>
											<input type="checkbox" id="use-y" /><label for="use-y" class="ml0"><span></span></label>
										</td>
									</tr>
									<tr>
										<td>N</td>
										<td class="tdtype">미사용</td>
										<td>
											<input type="checkbox" id="use-n" /><label for="use-n" class="ml0"><span></span></label>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="btn-set">
							<div id="addBtn"><a href="#" class="btn-user-add" ><span>코드타입추가</span></a></div>
						</div>
					</div>
					<div class="code-bx" id ="saveContents">
						<h4 class="h4-title">코드추가</h4>
						<div class="tbl-write2">
							<table summary="">
								<caption>코드추가 작성</caption>
								<tbody>
									<tr>
										<th scope="row"><label for="code-id">코드타입</label></th>
										<td><input type="text" id="codeType" class="wx200" maxlength="2" readonly="readonly"/></td>
									</tr>
									<tr>
										<th scope="row"><label for="code-name">코드명</label></th>
										<td><input type="text" id="codeTypeNm" class="wx200" maxlength="20"/></td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="board-btn-set">
							<a href="#" class="save" id="saveBtn"><span id="saveBtnText">추가</span></a>
							<a href="#" class="cancle" id="cancleBtn"><span id="cancleBtnText">취소</span></a>
						</div>
					</div>
					
					<div class="content-bx" id="listContents2">
						<h3 class="depth7 type02">코드 리스트</h3>
						<div class="tbl-bx2">
							<table summary="순번, 비교 프로그램명, 설정 정보를 제공합니다" id="tableGrid2">
								<caption>원본 프로그램명 비교 리스트</caption>
								<colgroup>
									<col width="86">
									<col width="110">
									<col width="230">
									<col width="">
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">코드ID</th>
										<th scope="col">코드명</th>
										<th scope="col">설정</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
							</table>
						</div>
						<div class="btn-set">
							<a href="#" class="btn-user-add2" id="addOrgExpbtn"><span>코드 추가</span></a>
							<a href="#" class="btn-user-del wx90" id="deleteOrgExpBtn"><span>삭제</span></a>
						</div>
					</div>
					
				</div>
			</div>   
			<div id="dialog" > 
			<div class="content-bx" id="listContents2">
				<h3 class="depth7 type02">코드 추가</h3>
				<div class="tbl-bx2">
					<table >
						<caption>원본 프로그램추가</caption>
						<colgroup>
							<col width="86">
							<col width="">
						</colgroup>
						<tbody>
							<tr>
								<th scope="col"> 코드 ID</th>
								<td class='tdtype'> <input  id="codeId" maxlength="70"/></td>
								<th scope="col"> 코드명</th>
								<td class='tdtype'> <input  id="codeNm" maxlength="70"/></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btn-set">
					<a href="#" class="btn-user-add2" id="saveExpProgramBtn"><span>추가</span></a>
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
		$(document).ready(function() {
			
			var codeList = new Array();
			var subCodeList = new Array(); 
			searchCode();
			
			$("#saveContents").hide();
			$("#searchContents").show();
			$("#listContents").show();
			$("#listContents2").hide();
			
			$("#cancleBtn").click(function() {
				$("#saveContents").hide();
				$("#searchContents").show();
				$("#listContents").show();
				$("#listContents2").hide();
				iniSaveDiv();
			});
			
			$("#saveBtn").click(function() {
				saveProc();
			});
			
			$("#searchBtn").click(function() {
				searchCode();
			});
			
			$("#addBtn").click(function() {
				iniSaveDiv();
				showSaveDiv();
			});
			
			$("#saveExpProgramBtn").click(function() {
				saveProc2();
			});
			
			$("#deleteOrgExpBtn").click(function() {
				deleteCode();
			});
			
			$("#addOrgExpbtn").click(function() {
				$( "#dialog" ).dialog('open');
			});
		
			
			$('#dialog').dialog({
			    autoOpen: false,
			    resizable: false,
			    width : "700px" ,
			    closeText : '',
			    close : function (e){
			    	$( "#codeId" ).val('');
					$( "#codeNm" ).val('');
			    }
			    
		  	});
			$("#tableGrid tbody tr").css('cursor', 'pointer');
			$("#tableGrid tbody tr").click(function() {
				var codeType = $(this).children().eq(0).text();
				var codeTypeNm = $(this).children().eq(1).text();
				modifyDiv(codeType, codeTypeNm);
			});
			
			
			function searchCode(){
				
				var searchType = $("#searchType").val();
				var searchValue = $("#searchValue").val();
				$.ajax({
					type :	"get",
					url  : 	"/code/getCodeTypeList",
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
						codeList = result.codeList;
						
						$('#tableGrid > tbody').empty();
						
						if(codeList.length > 0) {
							for(var k=0; k<codeList.length; k++) {	
								
								$('#tableGrid > tbody:last ').append("<tr> <td data=>"+codeList[k].codeType+"</td>  <td >" 
									+codeList[k].codeTypeNm+"</td>  <td >"
									+" <a href=javascript:delCodeType('"+codeList[k].codeType +"') class='btn-user-del'><span>코드삭제</span></a> </td>    </tr>" );
							}
						}else {
							$('#tableGrid > tbody:last ').append("<tr> <td colspan='3'> 검색된 내용이 없습니다. </td></tr>");
						}
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
					
				});	
				$("#tableGrid tbody tr").click(function() {
					var codeType = $(this).children().eq(0).text();
					var codeTypeNm = $(this).children().eq(1).text();
					modifyDiv(codeType, codeTypeNm);
				});
			}
			
			function search2(){
				
				var codeType = $("#codeType").val();
				if(codeType == ''){
					$('#tableGrid2 > tbody:last ').append("<tr> <td colspan='2'> 검색된 내용이 없습니다. </td></tr>");
				}else {
					$.ajax({
						type :	"get",
						url  : 	"/code/getCodeList2",
						datatype : "json",
						async: false,
						data : {
							codeType : codeType
						},
						success : function(result){
							subCodeList = result.subCodeList;
							
							$('#tableGrid2 > tbody').empty();
							
							if(subCodeList.length > 0) {
								for(var k=0; k< subCodeList.length; k++) {	
							
									$('#tableGrid2 > tbody:last ').append("<tr> <td data=''>"+(k+1)+"</td>  <td >" 
										+subCodeList[k].codeId+"</td>  <td >"
										+subCodeList[k].codeNm+"</td>  <td >"
										+" <input type='checkbox' id='use"+k+"' name ='codeSeq' value='"+subCodeList[k].codeSeq+"' > "
										+ " <label for='use"+k+"' class='ml0'><span></span></label>  </td>  </tr>" );
								}
							}else {
								$('#tableGrid > tbody:last ').append("<tr> <td colspan='3'> 검색된 내용이 없습니다. </td></tr>");
							}
						},
						error:function(request,status,error){
				      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				     	}
					});	
				}
				
				
			}
			
			
			function saveProc() {

				var codeType = $("#codeType").val();
				var codeTypeNm = $("#codeTypeNm").val();

				if(codeType == ''){
					alert('코드타입을 입력해주세요');
					return;
				}
				
				if(codeTypeNm == ''){
					alert('코드타입명을 입력해주세요');
					return;
				}
				
				$.ajax({
					type :	"post",
					url  : 	"/code/saveCodeType",
					datatype : "json",
					async: false,
					data : {
						codeType   : codeType	,						
						codeTypeNm 	: codeTypeNm
					},
					success : function(result){
						alert('완료되었습니다.');
						$(location).attr('href', '/code/managerCodeType');
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
			
			
			function saveProc2() {
				var codeType = $("#codeType").val();
				var codeId = $("#codeId").val();
				var codeNm = $("#codeNm").val();
				var useYn = 'Y';
				
				if(codeType == ''){
					alert('코드타입부터 저장해주세요');
					return;
				}
				
				if(codeId == ''){
					alert('코드ID를 입력해 주세요');
					return;
				}
				
				if(codeNm == ''){
					alert('코드명을 입력해주세요');
					return;
				}
				
				$.ajax({
					type :	"post",
					url  : 	"/code/saveCode",
					datatype : "json",
					async: false,
					data : {
						codeType   : codeType	,	
						codeId : codeId,
						codeNm : codeNm,
						useYn : useYn 
						
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
			
			function deleteCode(){
				var codeSeq = new Array();
				
				$("input[name=codeSeq]:checked").each(function() {
					codeSeq.push( $(this).val());  
				});
				
				
				if(codeSeq.length == 0 ){
					return;
				}
				
				$.ajaxSettings.traditional = true
				$.ajax({
					type :	"post",
					url  : 	"/code/deleteCode",
					datatype : "json",
					async: false,
					data : {
						codeList   : codeSeq
						
					},
					success : function(result){
						alert("삭제되었습니다.");
						$( "#dialog" ).dialog('close');
						search2();
						
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
				
			}
			
			function iniSaveDiv() {
				$("#codeType").val('');
				$("#codeTypeNm").val('');
			}
			
			function modifyDiv(codeType, codeTypeNm){
				
				showSaveDiv();
				$("#codeType").val(codeType);
				$("#codeTypeNm").val(codeTypeNm);
				$("#saveBtnText").text('변경');
				
				search2();
			}
			
			function showSaveDiv(){
				$("#saveBtnText").text('추가');
				$("#searchContents").hide();
				$("#listContents").hide();
				$("#saveContents").show();
				$("#listContents2").show();
			}
			
		});
		var delCodeType = function delCodeType(codeType){
			$.ajax({
				type :	"post",
				url  : 	"/code/deleteCodeType",
				datatype : "json",
				async: false,
				data : {
					codeType   : codeType						
				},
				success : function(result){
					alert('삭제되었습니다.');
					$(location).attr('href', '/code/managerCodeType');
				},
				error:function(request,status,error){
		      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		     	}
			});	
		}
	</script>
	
</html>