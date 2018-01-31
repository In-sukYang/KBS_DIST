<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<script type="text/javascript" src="/resources/js/jquery.blockUI.js"></script>
		<!--[if lte IE 8]>
		<script type="text/javascript" src="/resources/js/html5.js"></script>
		<![endif]-->
		<style>
			.bar {
			    height: 18px;
			    background: green;
			}
		</style>
		
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
				<div class="nav">Home &gt; 프로그램관리 &gt; <span>프로그램 이용현황 EXCEL 업로드</span></div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth10">프로그램 이용현황 EXCEL 리스트</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="cSeq">회사선택</label>
									<ul>
										<li>
											<select id="cSeq" class="wx180">
												<c:if test="${!empty companyList}">
													<c:forEach items="${companyList}" var="list" varStatus="status">
														<option value ="${list.codeId}"  >${list.codeNm}</option>
													</c:forEach>
												</c:if>
											</select>
										</li>
									</ul>
								</li>
								<li><span>02</span><label for="sch-opt">수급타입</label>
									<ul>
										<li>
											<select id="distType" class="wx180">
												<option value ="">등록된 수급타입이 없습니다</option>
											</select>
										</li>
									</ul>
								</li>
								<li id="excelZone"><span>03</span>
									<ul>
										<li><span class="txt-l">데이터</span>
											<div class="filebox bs3-primary">
												<input class="upload-name" value="파일선택" disabled="disabled">
												<label for="exFilename" class="btn-sch"><span>불러오기</span></label> 
												<input type="file" id="exFilename" class="upload-hidden"> 
											</div>
										</li>
									</ul>
								</li>
							</ul>
							<a href="#" class="btn-submit" id="insertTemp"><span>검토</span></a>
							<div class="board-btn-set">
							<a href="#" class="save" id="saveBtn"><span>저장</span></a>
						</div>
						</div>
					</div>
					<div class="content-bx">
						<div class="tbl-bx2">
							<table summary="" id="tableGrid">
								<caption>채널별 이용현황 리스트</caption>
								<colgroup>
									<col width="86" />
									<col width="86" />
									<col width="300" />
									<col width="" />
									<col width="99" />
								</colgroup>
								<thead>
									<tr>
										<th scope="col">순번</th>
										<th scope="col">엑셀행</th>
										<th scope="col">엑셀프로그램명</th>
										<th scope="col">변환프로그램명</th>
										<th scope="col">변환 회차 또는 변환 방영날짜</th>
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
			
			var check = false;
			var attachFile = "";
			var attachFileDir = "";
			var attachFileReal = "";
			
			$("#cSeq").change(function(){
				getDistType();
			});
			
			
			getDistType();
			
			$("#insertTemp").click(function() {
				uploadExcel();
			});
			
			$("#saveBtn").click(function() {
				saveProc();
			});
			
			function saveProc() {
				var cSeq = $("#cSeq").val();
				var distType = $("#distType").val();
				
				if(attachFile == '') {
					alert('정상적인 진행 상태가 아닙니다. 처음 부터 다시 시도해 주세요');
					return;
				}
				
				$.ajax({
					type :	"post",
					url  : 	"/program/saveGroup",
					datatype : "json",
					async: false,
					data : {
						cSeq	: cSeq,
						distType : distType,
						attachFile : attachFile,
						attachFileReal : attachFileReal,
						attachFileDir : attachFileDir
					},
					success : function(result){
						alert('비동기 방식으로 데이터를 저장하고 있습니다.');		
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});
				
			}
			
			function getDistType() {
				var cSeq = $("#cSeq").val();
				
				$.ajax({
					type :	"get",
					url  : 	"/company/getCompanyDistTypeList",
					datatype : "json",
					async: false,
					data : {
						cSeq	: cSeq
					},
					success : function(result){
						var companyDistTypeList = result.companyDistTypeList;
						$("#distType").find("option").remove();
						
						if(companyDistTypeList.length > 0) {
							$("#excelZone").show();
							for(var k=0; k<companyDistTypeList.length; k++) {
								$("#distType").append("<option value='"+companyDistTypeList[k].distType+"'>"+companyDistTypeList[k].distName+"</option>");
								
							}		
						}else {
							$("#distType").append("<option value=''> 등록된 수급타입이 없습니다</option>");
							$("#excelZone").hide();
							
						}
						
						
					},
					error:function(request,status,error){
			      		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			     	}
				});
			} 
			
			function uploadExcel() {
				$('#tableGrid > tbody').empty();
				
				attachFile = "";
				attachFileReal = "";
				attachFileDir = "";
				
				var cSeq = $("#cSeq").val();
				var distType = $("#distType").val();
				var fileName = $("#exFilename").val();
				
				if(cSeq == "") {
					alert("회사를 선택해주세요");
					return;
				}
				
				if(distType == "") {
					alert("수급타입을 선택해주세요");
					return;
				}
				
				if(fileName == "") {
					alert("파일을 선택해주세요");
					return;
				}
				
				if (!fileName.match(/(.csv|.xls|.xlsx)$/) ) {
					alert("선택하신 파일이 엑셀 파일이 아닙니다.");
					return;
				} 
				
				var formData = new FormData();
				formData.append("cSeq" , cSeq);
				formData.append("distType" , distType);
				formData.append("fileName" , $("#exFilename")[0].files[0]);
				
				$.ajax({
	    			type :	"post",
	    			url  : 	"/program/savetUsageStatProgramExcelUploadFile",
	    			data :  formData,
	    			contentType: false,
	    			processData : false,
	    			type: 'POST',
	    			success : function(result){
	    				check = result.typeCheck;
	    				var fileVo = result.fileVo
	    				attachFile = fileVo.saveFilenm;
	    				attachFileReal = fileVo.originFilenm;
	    				attachFileDir = fileVo.saveDir;
	    				
	    				if(check == true) {
	    					var errorList = result.errorList;
	    					if(cSeq == 11) {
    							$('#tableGrid > tbody:last ').append("<tr> <td colspan='5'> POOQ VOD는 에러 변환을 하지 않습니다. </td></tr>");
    							
	    					}else {
	    						if(errorList.length > 0) {
		    						for(var k=0; k<errorList.length; k++) {
	 		    						$('#tableGrid > tbody:last ').append("<tr> " 
											+"<td>"+(k+1)+"</td>  <td >" 
 		    								+errorList[k].row+"</td>  <td >"
 		    								+errorList[k].programNm+"</td>  <td >"
 		    								+errorList[k].title+"</td>  <td >"
 		    								+errorList[k].broadDate+"</td>  "
 		    								+"    </tr>" );
		    						}	
	    						}else {
	    							$('#tableGrid > tbody:last ').append("<tr> <td colspan='5'> 에러 내용이 없습니다. </td></tr>");	
	    						}
	    					}
							alert('엑셀 파일이 서버에 업로드 되었습니다.')	    					
	    				}else{
	    					alert('형식이 맞지 않는 파일입니다. 확인해주세요');
	    					return;
	    				}
	    				
	    				
	    			},
	    			error:function(request,status,error){
		          		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		         	}
	    		});
			}
		});
	</script>
	
</html>