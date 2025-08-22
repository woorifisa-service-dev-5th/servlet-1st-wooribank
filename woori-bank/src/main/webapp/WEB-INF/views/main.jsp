<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
	<h2>wooribank main</h2>
	<ul>
		<li><a href="<%=request.getContextPath()%>/products/deposit">예/적금
				상품 보기</a></li>
		<li><a href="<%=request.getContextPath()%>/products/card">카드
				상품 보기</a></li>
		<li><a href="<%=request.getContextPath()%>/products/loan">대출
				상품 보기</a></li>
		<li><a href="<%=request.getContextPath()%>/transfer">이체(송금)
				하기</a></li>
		<li><a href="<%=request.getContextPath()%>/me">마이페이지</a></li>
	</ul>

	<%
	Boolean isLoggedIn = (Boolean) request.getAttribute("isLoggedIn");
	if (Boolean.TRUE.equals(isLoggedIn)) {
		Object currentUser = request.getAttribute("currentUser");
		// 이름을 보여주고 싶다면 (User) 캐스팅 + null 방어
		String displayName = (currentUser != null) ? currentUser.toString() : "";
	%>
	<p>
		<b><%=displayName%></b> 님 환영합니다! | <a
			href="<%=request.getContextPath()%>/mypage">마이페이지</a> | <a
			href="<%=request.getContextPath()%>/logout">로그아웃</a>
	</p>
	<%
	} else {
	%>
	<p>
		<a href="<%=request.getContextPath()%>/login">로그인</a> | <a
			href="<%=request.getContextPath()%>/signup">회원가입</a>
	</p>
	<p>
		※ 상품 <u>상세/가입</u>, <u>이체</u>, <u>마이페이지</u>는 로그인 필요
	</p>
	<%
	}
	%>
	
	<a href="<%=request.getContextPath()%>/account">계좌</a>
</body>
</html>