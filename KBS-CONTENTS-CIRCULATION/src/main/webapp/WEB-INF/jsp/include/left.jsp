<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h1 id="hd_h1">KBS 유통정보시스템</h1>
<div id="skip_to_container">
	<a href="#contents">본문내용 바로가기</a>
</div>
<div id="logo">
	<a href="/dashboard/total/managerTotalDashBoard"><img src="/resources/img/common/logo2.png" alt="KBS 유통정보시스템 로고" /></a>
</div>
<div id="gnb" class="gnb">
	<h2 class="sound_only">메뉴</h2>
	<ul>
		<c:forEach items="${sessionScope.menuList}" var="list" varStatus="status">
			<c:if test="${list.classFg == 0 and list.subCnt != 0 and status.index == 0}">
				<li class="gnb${list.codeGrup1}"><a href="#"  <c:if test="${list.pageNm == sessionScope.parentMenu}"> class='active' </c:if>  ><span>${list.pageNm}</span></a>
					<ul <c:if test="${list.pageNm == sessionScope.parentMenu}"> class='active' </c:if> >
			</c:if>
			<c:if test="${list.classFg == 1}">
				<li><a href="${list.pageUrl }"  <c:if test="${list.pageUrl == sessionScope.acivePage}"> class='active' </c:if>  >${list.pageNm }</a></li>
			</c:if>
			<c:if test="${list.classFg == 0 and status.index != 0}">
				</ul>
				</li>
				<li class="gnb${list.codeGrup1}"><a href="#"  <c:if test="${list.pageNm == sessionScope.parentMenu}"> class='active' </c:if> > <span>${list.pageNm}</span></a>
					<ul <c:if test="${list.pageNm == sessionScope.parentMenu}"> class='active' </c:if> >
			</c:if>
			<c:if test="${status.last}">
				</ul>
				</li>
			</c:if>
		</c:forEach>
	</ul>
</div>