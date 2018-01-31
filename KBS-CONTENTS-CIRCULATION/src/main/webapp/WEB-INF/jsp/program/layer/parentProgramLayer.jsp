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
		<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
		<script type="text/javascript" src="/resources/js/jquery-ui.js"></script>
		<!--[if lte IE 8]>
		<script type="text/javascript" src="/resources/js/html5.js"></script>
		<![endif]-->
	</head>
	<body>
		<div id="wrap">
			<!-- 상단 시작 -->
			<!-- 상단 끝 -->
		    <!-- 콘텐츠 시작  -->
			<div id="container">
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
							</ul>
							<div id="searchBtn"><a href="#" class="btn-submit"><span>검색</span></a></div>
						</div>
					</div>
					<div class="content-bx" id="listContents"> 
						<h3 class="depth10 type02">프로그램리스트</h3>
						<div class="tbl-bx2">
							<table summary="순번, 프로그램ID, 프로그램명, 대표프로그램명, 방송사, 채널명 정보를 제공합니다" id="tableGrid">
								<caption>원본 프로그램리스트</caption>
								<colgroup>
									<col width="86" />
									<col width="168" />
									<col width="" />
									<col width="210" />
									<col width="123" />
									<!--<col width="115" />-->
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">프로그램ID</th>
										<th scope="col">프로그램명</th>
										<th scope="col">대표프로그램명</th>
										<th scope="col">방송사</th>
										<!-- <th scope="col">채널명</th> -->
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
			
			var programList = new Array();
			var broadList = new Array();
			var channelList = new Array();
			var useYnList = new Array();
			var broadTypeList = new Array();
			
			var openrType = '${param.openrType}';
			var callback = '${param.callback}';
			
			$("#searchType").val('${param.searchType}')
			$("#searchValue").val('${param.searchValue}')
			
			makeList();
			search();
			
			$("#searchContents").show();
			$("#listContents").show();
			
			
			$("#searchBtn").click(function() {
				search();
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
					url  : 	"/program/getProgramListLayer",
					datatype : "json",
					async: false,
					data : {
						searchType 	: searchType,
						searchValue : searchValue,
						pSeqType	: '${param.pSeqType}',
						pagingYn : 'N'
						
					},
					success : function(result){
						programList = result.programList;
						
						$('#tableGrid > tbody').empty();
						if(programList.length > 0) {
							for(var k=0; k<programList.length; k++) {	
								
								if(broadList.length > 0) {
									var broadListSelectHtml  = "";
									for(var i=0; i < broadList.length; i++) {
										if(programList[k].broadId == broadList[i].code) {
											broadListSelectHtml  = broadList[i].codeNm;
										}
									}
									
									
								}
								
								if(channelList.length > 0) {
									var channelListSelectHtml  = "<select disabled='disabled'>";
									for(var i=0; i < channelList.length; i++) {
										if(programList[k].channelId != channelList[i].code) {
											channelListSelectHtml  += "<option value='"+channelList[i].code+"'>"+channelList[i].codeNm+"</option>"
										}else {
											channelListSelectHtml  += "<option value='"+channelList[i].code+"' selected >"+channelList[i].codeNm+"</option>"
										}
									}
									
									channelListSelectHtml += "</select>";
								}
								
								$('#tableGrid > tbody:last ').append("<tr> <td data='"+programList[k].pseq+"'>"+(k+1)+"</td>  <td >" 
									+programList[k].programId+"</td>  <td >"
									+programList[k].programNm+ " </td> <td>"
									+programList[k].parentNm+ " </td> <td>"
									+broadListSelectHtml+ " </td> <td>"
									//+channelListSelectHtml+ "</td>"
									+"</tr>" );
							}
						}else {
							$('#tableGrid > tbody:last ').append("<tr> <td colspan='6'> 검색된 내용이 없습니다. </td></tr>");
						}
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});	
				
				$("#tableGrid tbody tr").click(function() {
					var pSeq = $(this).children().eq(0).attr('data');
					var programNm = $(this).children().eq(2).text();
					
					if(openrType == "01"){
						
						opener.document.getElementById('parentPSeq').value = pSeq;
						opener.document.getElementById('parentNm').value = programNm;	
					}else if(openrType == "02") {
						
						if(callback == 'programBtn1') {
							opener.document.getElementById('pSeq1').value = pSeq;
							opener.document.getElementById('programNm1').value = programNm;
						}else if(callback == 'programBtn2') {
							opener.document.getElementById('pSeq2').value = pSeq;
							opener.document.getElementById('programNm2').value = programNm;
						}else if(callback == 'programBtn3') {
							opener.document.getElementById('pSeq3').value = pSeq;
							opener.document.getElementById('programNm3').value = programNm;
						}else if(callback == 'callback') {
							opener.parent.callback($(this).children());
						}
						
					}else {
						opener.document.getElementById('pSeq').value = pSeq;
						opener.document.getElementById('programNm').value = programNm;					
					}
					
					window.close();
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
			
		});
	</script>
</html>