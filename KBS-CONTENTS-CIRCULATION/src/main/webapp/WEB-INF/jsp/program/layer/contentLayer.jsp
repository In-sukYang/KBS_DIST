<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<!-- 상단 시작 -->
			<!-- 상단 끝 -->
		    <!-- 콘텐츠 시작  -->
			<div id="container">
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
											<option value="03">방영일자</option>
											</select>
										</li>
										<li>
											<label for="prname-bx" class="sound_only">프로그램명</label>
											<input type="text" id="searchValue" class="wx140" />
											<input type="text" id="searchDate" class="wx140" readonly="readonly"/>
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
										<th scope="col">프로그램ID</th>
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

			$("#searchType").val('${param.searchType}');
			$("#searchValue").val('${param.searchValue}');
			
			$( "#searchDate" ).datepicker({
				dateFormat: 'yy-mm-dd'
		    });
			
			$("#searchType").change(function() {
				chanteEvent();
			});
			chanteEvent();
			search();
			
			$("#searchContents").show();
			$("#listContents").show();
			
			
			$("#searchBtn").click(function() {
				search();
			});
			
			function search(){
				var searchType = $("#searchType").val();
				var searchValue = $("#searchValue").val();
				var searchDate = $("#searchDate").val();
				
				searchDate = searchDate.replace(/-/gi, '');
				
				if(searchType == '03') {
					searchValue = searchDate;
				} 
				
				if(searchValue == "" || searchValue.length < 2) {
					alert("검색어를 2자이상 입력해주세요");
					return;
				}
				
				$.ajax({
					type :	"get",
					url  : 	"/program/getProgramContentList",
					datatype : "json",
					async: false,
					data : {
						searchType 	: searchType,
						searchValue :searchValue,
						pagingYn : 'N'
					},
					success : function(result){
						programList = result.programList;
						
						$('#tableGrid > tbody').empty();
						
						if(programList.length > 0) {
							for(var k=0; k<programList.length; k++) {	
								
								$('#tableGrid > tbody:last ').append("<tr> <td data='"+programList[k].pcSeq+"'>"+(k+1)+"</td>  <td >" 
									+programList[k].contentsId+"</td>  <td >"
									+programList[k].contentsNm+"</td>  <td >"
									+programList[k].vodcnt+"</td>  <td >"
									+programList[k].broadDate+"</td>  <td >"
									+programList[k].programId+"</td>   </tr>" );
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
					var opSeq = $(this).children().eq(0).attr('data');
					var oProgramNm = $(this).children().eq(2).text();
					
					opener.document.getElementById('pcSeq').value = opSeq;
					opener.document.getElementById('contentsNm').value = oProgramNm;
					
					window.close();
				});
			}
			
			function chanteEvent() {
				
				var type = $("#searchType").val();
				
				if(type == '01' || type == '02')  {
					
					$("#searchValue").show();
					$("#searchDate").hide();
					
				}else {
					$("#searchValue").hide();
					$("#searchDate").show();
				}
			}
			
		});
	</script>
</html>