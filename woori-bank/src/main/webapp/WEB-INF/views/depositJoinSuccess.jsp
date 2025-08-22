<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.woori_bank.be.model.Deposit"%>
<%
Deposit d = (Deposit) request.getAttribute("deposit");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>가입 완료</h2>
	<p>
		예적금 ID:
		<%=d.getKey()%></p>
	<p>
		계좌ID:
		<%=d.getAccountId()%></p>
	<p>
		금액:
		<%=d.getAmount()%></p>
	<p>
		만기일:
		<%=d.getMaturityDate() == null ? "-" : d.getMaturityDate().toString()%></p>
	<p>
		상태:
		<%=d.isValidDate() ? "만기" : "진행중"%></p>

	<p>
		<a href="<%=request.getContextPath()%>/mypage">마이페이지</a> | <a
			href="<%=request.getContextPath()%>/products/deposit">예/적금 페이지</a>
	</p>
</body>
</html>