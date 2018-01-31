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
				<jsp:include page="../include/left.jsp" flush="true" />
			</div>
			<!-- 상단 끝 -->
			<!-- 콘텐츠 시작  -->
			<div id="container">
				<div class="tnb">
					<h2 class="sound_only">상단메뉴</h2>
					<a href="#" class="menual">사용자메뉴얼</a>
					<ul>
						<c:choose> 
							<c:when test="${sessionScope.loginId ne null && sessionScope.loginId ne ''}" > 
								<li><a href="/logout">로그아웃</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="/">로그인</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
				<div class="nav">
					Home &gt; <span>종합현황</span>
				</div>
				<div id="contents">
					<div class="content-bx">
						<h3 class="depth2">매체별 현황-TV VOD 조회</h3>
						<div class="search-bx">
							<ul>
								<li><span>01</span><label for="sch-date">기간선택</label>
									<ul>
										<li>
											<select id="sch-date" title="월별 선택" class="wx110">
												<option>월별</option>
											</select>
										</li>
										<li>
											<select id="sch-date02" title="년도 선택" class="wx110">
												<option>2017</option>
											</select> <span>년</span></li>
										<li>
											<select id="sch-date03" title="월 선택" class="wx110">
												<option>11</option>
										</select> <span>월</span></li>
									</ul>
								</li>
								<li><span>02</span><label for="sch-sort">정렬선택</label>
									<ul>
										<li>
											<select id="sch-sort" title="PPM" class="wx110">
												<option value=''>PPM</option>
												<option value=''>PPM</option>
											</select>
										</li>
										<li>
											<label for="sch-divis" class="mr10">구분</label> 
											<select id="sch-divis" title="구분" class="wx215">
												<option>프로그램별</option>
											</select>
										</li>
									</ul>
								</li>
								<li><span>03</span>
									<ul>
										<li>
											<span>사업자</span> 
											<c:if test="${!empty distributionList}">
												<c:forEach items="${distributionList}" var="list" varStatus="status">
													<input type="checkbox" id="buisn" value="${list.CSeq}"><label for="buisn01"><span></span>${list.CNm}</label>
													<c:if test="${status.index eq 4}">
														<br>
													</c:if>
												</c:forEach>
											
											</c:if>
										</li>
									</ul>
								</li>
								<li><span>04</span>
									<ul>
										<li>
											<span >채널</span> 
											<c:if test="${!empty channleList}">
												<c:forEach items="${channleList}" var="list" varStatus="status">
													<input type="checkbox" id="channel" value="${list.CSeq}"><label for="buisn01"><span></span>${list.CNm}</label>

												</c:forEach>
												
											</c:if>
										</li>
									</ul></li>
							</ul>
							<a href="#" class="btn-submit"><span>조회</span></a>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth2 type02">매체별 현황 차트 – TV VOD</h3>
						<div class="grap-bx gr-type02">
							<p class="title">TV VOD 순위</p>
							<div class="gr-area">
								<div class="graph">
									<img src="/resources/img/common/gr04.jpg" />
								</div>
							</div>
						</div>
						<div class="btn-area">
							<ul>
								<li><a href="#" class="btn-sort"><span>정렬조건 상위 10</span></a></li>
								<li><a href="#" class="btn-more"><span>더보기</span></a></li>
							</ul>
						</div>
					</div>
					<div class="content-bx">
						<h3 class="depth2 type02">매체별 현황 데이터 – TV VOD</h3>
						<div class="tbl-bx">
							<table summary="시청률, pooq 실시간, pooq VOD, TV VOD, SMR 클립 집계 정보를 제공합니다">
								<caption>종합현황 집계</caption>
								<thead>
									<tr>
										<th scope="col" width="55">No</th>
										<th scope="col" width="349">프로그램</th>
										<th scope="col" width="106">SKB</th>
										<th scope="col" width="106">KT Olleh</th>
										<th scope="col" width="106">LG U+</th>
										<th scope="col" width="106">CVOD</th>
										<th scope="col" width="136">합계</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td width="55">1</td>
										<td width="349" class="subject">1박2일</td>
										<td width="106" class="number">23,512</td>
										<td width="106" class="number">3,542</td>
										<td width="106" class="number">5,435</td>
										<td width="106" class="number">54,265</td>
										<td width="" class="number">215,568</td>
									</tr>
									<tr>
										<td width="55">1</td>
										<td width="349" class="subject">1박2일</td>
										<td width="106" class="number">23,512</td>
										<td width="106" class="number">3,542</td>
										<td width="106" class="number">5,435</td>
										<td width="106" class="number">54,265</td>
										<td width="" class="number">215,568</td>
									</tr>
									<tr>
										<td width="55">1</td>
										<td width="349" class="subject">1박2일</td>
										<td width="106" class="number">23,512</td>
										<td width="106" class="number">3,542</td>
										<td width="106" class="number">5,435</td>
										<td width="106" class="number">54,265</td>
										<td width="" class="number">215,568</td>
									</tr>
									<tr>
										<td width="55">1</td>
										<td width="349" class="subject">1박2일</td>
										<td width="106" class="number">23,512</td>
										<td width="106" class="number">3,542</td>
										<td width="106" class="number">5,435</td>
										<td width="106" class="number">54,265</td>
										<td width="" class="number">215,568</td>
									</tr>
									<tr>
										<td width="55">1</td>
										<td width="349" class="subject">1박2일</td>
										<td width="106" class="number">23,512</td>
										<td width="106" class="number">3,542</td>
										<td width="106" class="number">5,435</td>
										<td width="106" class="number">54,265</td>
										<td width="" class="number">215,568</td>
									</tr>
									<tr>
										<td width="55">1</td>
										<td width="349" class="subject">1박2일</td>
										<td width="106" class="number">23,512</td>
										<td width="106" class="number">3,542</td>
										<td width="106" class="number">5,435</td>
										<td width="106" class="number">54,265</td>
										<td width="" class="number">215,568</td>
									</tr>
									<tr>
										<td width="55">1</td>
										<td width="349" class="subject">1박2일</td>
										<td width="106" class="number">23,512</td>
										<td width="106" class="number">3,542</td>
										<td width="106" class="number">5,435</td>
										<td width="106" class="number">54,265</td>
										<td width="" class="number">215,568</td>
									</tr>
									<tr>
										<td width="55">1</td>
										<td width="349" class="subject">1박2일</td>
										<td width="106" class="number">23,512</td>
										<td width="106" class="number">3,542</td>
										<td width="106" class="number">5,435</td>
										<td width="106" class="number">54,265</td>
										<td width="" class="number">215,568</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="btn-area">
							<ul>
								<li><a href="#" class="btn-sort"><span>정렬조건 상위 10</span></a></li>
								<li><a href="#" class="btn-more"><span>더보기</span></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<!-- 콘텐츠 끝 -->
		</div>
		<!-- 하단 시작 -->
		<div id="ft">
			<div class="footer">COPYRIGHT(C) KBS. LTD. ALL RIGHTS RESERVED.</div>
		</div>
		<!-- 하단 끝 -->
	</body>
</html>