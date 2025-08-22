<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.woori_bank.be.model.Product"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>예/적금 상품</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/">&larr; 메인</a>
	<h2>예/적금 상품</h2>
	<ul>
		<li><b>WON플러스 예금</b><br />
			<p>기간도 금액도 내맘대로 예금</p>
			<p>- 상품종류: 목돈굴리기상품</p>
			<p>- 가입대상: 실명의 개인</p>
			<p>- 가입기간: 1~36개월</p>
			<p>- 가입금액: 1만원 이상</p> <a
			href="<%=request.getContextPath()%>/deposit/join">가입하기</a></li>
	</ul>
</body>
</html>