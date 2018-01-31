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
		<!--[if lte IE 8]>
		<script type="text/javascript" src="/resources/js/html5.js"></script>
		<![endif]-->
	</head>
	<body>
		<div id="wrap">
			<div id="container">
				<div id="contents">
					<div class="content-bx" id ="searchContents">
						<h3 class="depth7">프로그램관리</h3>
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
										<th scope="col">최종변경일</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
							</table>
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
			
			var orgProgramList = new Array();
			var broadTypeList = new Array();
			var channelList = new Array();
			var orgProgramExpList = new Array();
			
			$("#searchContents").show();
			$("#listContents").show();
			
			makeList();
			
			$("#searchType").val('${param.searchType}')
			$("#searchValue").val('${param.searchValue}')
			
			search();
			
			$("#searchBtn").click(function() {
				search();
			});
			
			
			function search(){
				var searchType = $("#searchType").val();
				var searchValue = $("#searchValue").val();
				
				if(searchValue == '' || searchValue.length < 2) {
					alert('검색어를 2자이상 입력해주세요');
					return
				}
				
				$.ajax({
					type :	"get",
					url  : 	"/program/getOrgProgramList",
					datatype : "json",
					async: false,
					data : {
						searchType 	: searchType,
						searchValue :searchValue ,
						pagingYn : 'N'
					},
					success : function(result){
						orgProgramList = result.orgProgramList;
						
						$('#tableGrid > tbody').empty();
						
						if(orgProgramList.length > 0) {
							for(var k=0; k<orgProgramList.length; k++) {	
						
								if(broadTypeList.length > 0) {
									var broadTypeListSelectHtml  = "<select disabled='disabled'>";
									for(var i=0; i < broadTypeList.length; i++) {
										if(orgProgramList[k].programType != broadTypeList[i].code) {
											broadTypeListSelectHtml  += "<option value='"+broadTypeList[i].code+"'>"+broadTypeList[i].codeNm+"</option>"
										}else {
											broadTypeListSelectHtml  += "<option value='"+broadTypeList[i].code+"' selected >"+broadTypeList[i].codeNm+"</option>"
										}
									}
									
									broadTypeListSelectHtml += "</select>";
								}
								
								
								$('#tableGrid > tbody:last ').append("<tr> <td data='"+orgProgramList[k].opSeq+"'>"+(k+1)+"</td>  <td class='tdtype'>" 
									+orgProgramList[k].oprogramId+"</td>  <td class='tdtype'>"
									+orgProgramList[k].oprogramNm+"</td>  <td class='tdtype'>"
									+broadTypeListSelectHtml+ "</td> <td class='tdtype'>"
									+orgProgramList[k].updateDt +"</td>    </tr>" );
							}
						}else {
							$('#tableGrid > tbody:last ').append("<tr> <td colspan='5'> 검색된 내용이 없습니다. </td></tr>");
						}
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
				$("#tableGrid tbody tr").click(function() {
					var opSeq = $(this).children().eq(0).attr('data');
					var oProgramNm = $(this).children().eq(2).text();
					
					opener.document.getElementById('opSeq').value = opSeq;
					opener.document.getElementById('oProgramNm').value = oProgramNm;
					
					window.close();
				});
			}
			
			function makeList() {
				<c:forEach items="${broadTypeList}" var="list">
					broadTypeList.push( {code : "${list.codeId}", codeNm : "${list.codeNm}"});
				</c:forEach>
			} 
			
			
		});
	</script>
</html>