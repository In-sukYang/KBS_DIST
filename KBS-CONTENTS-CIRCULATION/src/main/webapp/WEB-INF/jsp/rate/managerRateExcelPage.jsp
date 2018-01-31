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
				<div class="nav">Home &gt; 시청률관리 &gt; <span>시청률 EXCEL 업로드</span></div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth10">시청률 EXCEL 리스트</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="cSeq">회사선택</label>
									<ul>
										<li>
											<select id="cSeq" class="wx180">
												<option value='133'>AGB</option>
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
							<a href="#" class="btn-submit" id="insertTemp"><span>저장</span></a>
							
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
	    			url  : 	"/rate/saveRateExcelUploadFile",
	    			data :  formData,
	    			contentType: false,
	    			processData : false,
	    			type: 'POST',
	    			success : function(result){
	    				
						alert('엑셀 파일이 서버에 업로드 되었습니다. \n 데이터가 비동기 방식으로 저장 중에 있습니다.');	    					
	    				
	    			},
	    			error:function(request,status,error){
		          		alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		         	}
	    		});
			}
		});
	</script>
	
</html>