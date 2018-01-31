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
		<script type="text/javascript" src="/resources/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript" src="/resources/js/common.js"></script>
		<!--[if lte IE 8]>
		<script type="text/javascript" src="/resources/js/html5.js"></script>
		<![endif]-->
	</head>
	<body>
		<div id="wrap">
			<!-- 상단 시작 -->
			<div id="hd">
				<jsp:include page="../include/left.jsp" />
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
					<div class="content-bx" id="searchContents">
						<h3 class="depth9">코드관리</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="codename">검색조건</label>
									<ul>
										<li><select id="searchType" class="wx140">
												<option value="01">코드ID</option>
												<option value="02">코드명</option>
											</select>
										</li>
										<li>
											<label for="cpname-bx" class="sound_only">회사명</label>
											<input type="text" id="searchValue" class="wx140" />
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit"><span>검색</span></a>
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
							<a href="#" class="btn-user-add"><span>코드추가</span></a>
						</div>
						<div class="code-bx" id = "">
							<h4 class="h4-title">코드추가</h4>
							<div class="tbl-write2">
								<table summary="">
									<caption>코드추가 작성</caption>
									<tbody>
										<tr>
											<th scope="row"><label for="code-id">코드ID</label></th>
											<td><input type="text" id="code-id" class="wx200" /></td>
										</tr>
										<tr>
											<th scope="row"><label for="code-name">코드명</label></th>
											<td><input type="text" id="code-name" class="wx200" /></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="btn-set2">
								<a href="#" class="btn-add"><span>추가</span></a>
							</div>
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
		$(document).ready(function() {
			
			var codeList = new Array();
			var useYnList = new Array();
			var uAuthTypeList = new Array();
			var memberList = new Array();
			
			makeList();
			searchCode();
			
			
			$("#saveContents").hide();
			$("#searchContents").show();
			$("#listContents").show();
			
			
			$("#cancleBtn").click(function() {
				$("#saveContents").hide();
				$("#searchContents").show();
				$("#listContents").show();
				iniSaveDiv();
			});
			
			$("#saveBtn").click(function() {
				saveProc();
			});
			
			$("#searchBtn").click(function() {
				searchCode();
			});
			
			$("#usearAddBtn").click(function() {
				iniSaveDiv();
				showSaveDiv();
			});
			
			$("#tableGrid tbody tr").click(function() {
				var userId = $(this).children().eq(1).text();
				modifyDiv(userId);
			});
			
			
			function searchCode(){
				
				var searchType = $("#searchType").val();
				var searchValue = $("#searchValue").val();
				$.ajax({
					type :	"get",
					url  : 	"/code/getCodeList",
					datatype : "json",
					async: false,
					data : {
						searchType 	: searchType,
						searchValue :searchValue 
					},
					success : function(result){
						codeList = result.codeList;
						
						$('#tableGrid > tbody').empty();
						
						if(codeList.length > 0) {
							for(var k=0; k<codeList.length; k++) {	
								
								$('#tableGrid > tbody:last ').append("<tr> <td data="+codeList[k].codeSeq+">"+codeList[k].codeId+"</td>  <td >" 
									+codeList[k].codeNm+"</td>  <td >"
									+" <a href='#' class='btn-user-del'><span>코드삭제</span></a> </td>    </tr>" );
							}
						}else {
							$('#tableGrid > tbody:last ').append("<tr> <td colspan='3'> 검색된 내용이 없습니다. </td></tr>");
						}
					},
					error:function(request,status,error){
						alert("현재 시스템 사용량이 많습니다. 다시 조회해 주시기 바랍니다");
			      		//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
			
			function saveProc() {

				var uSeq = $("#uSeq").val();
				var userId = $("#userId").val();
				var userPw = $("#userPw").val();
				var userNm = $("#userName").val();
				var useYn =  $(":input:radio[name=useYn]:checked").val();
				var userDesc = $("#userInfo").val();
				var uAuthType = $(":input:radio[name=userType]:checked").val();
					
				if(userId == ''){
					alert('id를 입력해주세요');
					return;
				}
				
				if(userPw == ''){
					alert('pw를 입력해주세요');
					return;
				}
				
				if(userNm == ''){
					alert('이름을 입력해주세요');
					return;
				}
				
				if(uSeq == ''){
					uSeq = 0;
				}
				
				$.ajax({
					type :	"post",
					url  : 	"/member/saveMember",
					datatype : "json",
					async: false,
					data : {
						uSeq   : uSeq	,						
						userId 	: userId,
						userPw :  userPw,
						userNm 	: userNm,
						useYn :  useYn,
						userDesc 	: userDesc,
						uAuthType : uAuthType
					},
					success : function(result){
						$(location).attr('href', '/member/managerMember');
					},
					error:function(request,status,error){
			      		alert("저장 중 에러가 발생 했습니다. 관리자에게 문의 하세요");
						//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
			}
			
			function iniSaveDiv() {
				$("#userName").val('');
				$("#userId").val('');
				$("#userPw").val('');
				$("#userInfo").val('');
				
				$("input[name=useYn]").eq(0).attr("checked", true);
				$("input[name=userType]").eq(0).attr("checked", true);
			}
			
			function makeList() {
				<c:forEach items="${companyList}" var="item">
					companyList.push( {cSeq : "${item.CSeq}", cNm : "${item.CNm}"});
				</c:forEach>
				
				useYnList.push( { useYn : 'Y', useText: "사용" }  );
				useYnList.push( { useYn : 'N', useText: "비사용" }  );
				
				uAuthTypeList.push( { uAuthType : '1010', useText: "전체관리자" }  );
				uAuthTypeList.push( { uAuthType : '2020', useText: "매출사용자" }  );
				uAuthTypeList.push( { uAuthType : '3030', useText: "일반사용자" }  );
			}
			
			function modifyDiv(userId){
				iniSaveDiv();
				showSaveDiv();
				for(var k = 0; k < memberList.length; k++ ) {

					if(memberList[k].userId == userId) {
						$("#userName").val(memberList[k].userNm);
						$("#userId").val(memberList[k].userId);
						$("#userPw").val('');
						$("#userInfo").val(memberList[k].userDesc);
						$("#uSeq").val(memberList[k].useq);
						
						$('input:radio[name="useYn"]').filter("[value='"+memberList[k].useYn+"']").attr('checked', true);
						$('input:radio[name="userType"]').filter("[value='"+memberList[k].uauthType+"']").attr('checked', true);
						
						break;
					}
				}
			}
			
			function showSaveDiv(){
				$("#saveContents").show();
				$("#searchContents").hide();
				$("#listContents").hide();
			}
			
		});
		
	</script>
	
</html>