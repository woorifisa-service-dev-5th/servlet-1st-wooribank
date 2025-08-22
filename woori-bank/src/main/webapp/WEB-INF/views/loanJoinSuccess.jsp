<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.woori_bank.be.model.Loan"%>
<%
Loan l = (Loan) request.getAttribute("loan");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대출 신청 완료</title>
</head>
<body>
	<h2>대출 신청 완료</h2>
	<p>
		대출 ID:
		<%=l.getLoanId()%></p>
	<p>
		계좌ID:
		<%=l.getAccountId()%></p>
	<p>
		대출 금액:
		<%=l.getLoanAmount()%>
		원
	</p>
	<p>
		상태:
		<%=l.getLoanStatus()%></p>
	<p>
		신청 시각:
		<%=l.getLoanDate()%></p>
	<p>
		보증 유효:
		<%=(l.getGuaranteeValid() != null && l.getGuaranteeValid()) ? "예" : "아니오/미입력"%></p>
	<p>
		CL 번호:
		<%=(l.getClNumber() == null ? "-" : l.getClNumber())%></p>

	<p>
		<a href="<%=request.getContextPath()%>/mypage">마이페이지</a> | <a
			href="<%=request.getContextPath()%>/products/loan">대출 상품</a>
	</p>
</body>
</html>