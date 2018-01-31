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
		<script type="text/javascript" src="/resources/js/common_paging.js"></script>
		<script type="text/javascript" src="/resources/js/jquery.blockUI.js"></script>
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
				<div class="nav">Home &gt; 사용자관리 &gt; <span>사용자관리 </span></div>
				<div id="contents">
					<div class="content-bx" id="searchContents">
						<h3 class="depth7">사용자 관리</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="username">검색조건</label>
									<ul>
										<li><select id="searchType" class="wx140">
												<option value="01">사용자ID</option>
												<option value="02">사용자명</option>
											</select>
										</li>
										<li>
											<label for="username-bx" class="sound_only">사용자명</label>
											<input type="text" id="searchValue" class="wx140" />
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="searchBtn"><span>검색</span></a>
						</div>
					</div>
					<div class="content-bx" id="listContents">
						<h3 class="depth7 type02">사용자리스트</h3>
						<div class="tbl-bx2">
							<table summary="순번, 사용자ID, 사용자, 사용여부, 회사명, 권한타입(권한명) 정보를 제공합니다" id="tableGrid">
								<caption>사용자리스트</caption>
								<colgroup>
									<col width="86" />
									<col width="168" />
									<col width="150" />
									<col width="111" />
									<col width="210" />
									<col width="" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">사용자ID</th>
										<th scope="col">사용자</th>
										<th scope="col">사용여부</th>
										<th scope="col">회사명</th>
										<th scope="col">권한타입(권한명)</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
						</div>
						<div class="paging" id="paging">
							<a href="#" class="first">처음</a>
							<a href="#" class="prev">이전</a>
							<strong>1</strong>
							<a href="#">2</a>
							<a href="#">3</a>
							<a href="#" class="next">다음</a>
							<a href="#" class="end">맨끝</a>
						</div>
						<div class="btn-set">
							<a href="#" class="btn-user-add" id="usearAddBtn"><span>사용자추가</span></a>
						</div>
					</div>
					<div class="content-bx" id ="saveContents">
						<h3 class="depth7">사용자추가</h3>
						<div class="tbl-write">
							<table summary="">
								<input type="hidden" id= "uSeq" />
								<caption>글 작성</caption>
								<tbody>
									<tr>
										<th scope="row"><label for="user-name">사용자명</label></th>
										<td><input type="text" id="userName" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="user-id">사용자 ID</label></th>
										<td><input type="text" ime-mode="disabled" id="userId" class="wx340" /></td>
									</tr>
									<tr>
										<th scope="row"><label for="user-pw">사용자 비밀번호</label></th>
										<td><input type="password" id="userPw" class="wx340" style="height:30px;line-height: 30px"/></td>
									</tr>
									<tr>
										<th scope="row" valign="top"><label for="user-info">사용자 설명</label></th>
										<td><textarea id="userInfo"></textarea></td>
									</tr>
									<tr>
										<th scope="row" valign="top"><label for="company-info">회사</label></th>
										<td>
											<select id="cSeq" >
												<c:if test="${!empty companyList}">
													<c:forEach items="${companyList}" var="list" varStatus="status">
														<option value ="${list.CSeq}"  >${list.CNm}</option>
													</c:forEach>
												</c:if>
											
											</select>
										</td>
										
										
									</tr>
									<tr>
										<th scope="row">사용자 타입</th>
										<td>
											<input type="radio" id="userType1" name="userType" value ="1010" /><label for="userType1" class="ml0"><span></span>전체관리자</label>
											<input type="radio" id="userType2" name="userType" value ="2020" /><label for="userType2"><span></span>매출 사용자</label>
											<input type="radio" id="userType3" name="userType" value ="3030"/><label for="userType3"><span></span>일반 사용자</label>
										</td>
									</tr>
									<tr>
										<th scope="row">사용 여부</th>
										<td>
											<input type="radio" id="useYn1" name="useYn" value ="Y" /><label for="useYn1" class="ml0"><span></span>사용</label>
											<input type="radio" id="useYn2" name="useYn" value ="N"/><label for="useYn2"><span></span>미사용</label>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="board-btn-set">
							<a href="#" class="save" id="saveBtn"><span>저장</span></a>
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
		$(document).ready(function() {
			
			var companyList = new Array();
			var useYnList = new Array();
			var uAuthTypeList = new Array();
			var memberList = new Array();
			
			makeList();
			searchMember();
			
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
				searchMember();
			});
			
			$("#usearAddBtn").click(function() {
				iniSaveDiv();
				showSaveDiv();
			});
			
			
			function searchMember(){
				
				var searchType = $("#searchType").val();
				var searchValue = $("#searchValue").val();
				$.ajax({
					type :	"get",
					url  : 	"/member/getMemberList",
					datatype : "json",
					async: false,
					data : {
						searchType 	: searchType,
						searchValue :searchValue ,
					},
					beforeSend : function() {
						$.blockUI({ message: '<img src="/resources/img/loader.gif" />' }); 
					},
					complete : function() {
						$.unblockUI();	    
					},
					success : function(result){
						memberList = result.memberList;
						var paging = result.param;
						
						makePaging(paging);
						
						$('#tableGrid > tbody').empty();
						
						if(memberList.length > 0) {
							for(var k=0; k<memberList.length; k++) {	
								var useName = '';
								if(memberList[k].useYn == 'Y') {
									useName == '사용';
								}else {
									useName == '미사용';
								}
								
								if(companyList.length > 0) {
									var compnaySelectHtml = "";
									for(var i=0; i < companyList.length; i++) {
										if(memberList[k].cseq != companyList[i].cSeq) {
										}else {
											compnaySelectHtml += companyList[i].cNm
										}
									}
									
								}
								
								var useSelectHtml = "";
								for(var i=0; i < useYnList.length; i++) {
									if(memberList[k].useYn != useYnList[i].useYn) {
									}else {
										useSelectHtml += useYnList[i].useText;
									}
								}
								
								var authSelectHtml = "";
								for(var i=0; i < uAuthTypeList.length; i++) {
									if(memberList[k].uauthType != uAuthTypeList[i].uAuthType) {
									}else {
										authSelectHtml += uAuthTypeList[i].useText;
									}
								}
								
								
								$('#tableGrid > tbody:last ').append("<tr> <td data="+memberList[k].useq+">"+(k+1)+"</td>  <td >" 
									+memberList[k].userId+"</td>  <td >"+memberList[k].userNm+"</td>   <td >"
									+useSelectHtml+"</td>   <td >"+compnaySelectHtml+"</td>   <td >"
									+ authSelectHtml +"</td>    </tr>" )
							}
						}else {
							$('#tableGrid > tbody:last ').append("<tr> <td colspan='6'> 검색된 내용이 없습니다. </td></tr>");
						}
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
				$("#tableGrid tbody tr").css('cursor', 'pointer');
				$("#tableGrid tbody tr").click(function() {
					var userId = $(this).children().eq(1).text();
					modifyDiv(userId);
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
				var cSeq = $("#cSeq").val();
				
					
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
						uAuthType : uAuthType,
						cSeq : cSeq
					},
					success : function(result){
						$(location).attr('href', '/member/managerMember');
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
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
			
			function makePaging(paging) {
				
				if(paging.finalPageNo == 1) {
					$('#paging').hide();
				}else {
					$('#paging').show();
						
				}
			}
			
		});
		
	</script>
	
</html>