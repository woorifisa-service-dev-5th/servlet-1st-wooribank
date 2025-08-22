<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대출 신청</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/products/loan">&larr; 대출
		상품</a>
	<h2>대출 신청</h2>

	<p>
		계좌ID: <b><%=request.getAttribute("accountId")%></b>
	</p>

	<form method="post" action="<%=request.getContextPath()%>/loan/join">
		<input type="hidden" name="accountId"
			value="<%=request.getAttribute("accountId")%>" />

		<p>
			대출 금액(만원): <input type="number" name="loanAmount" min="0" max="10000" step="1"
				required>
		</p>
		<p>
			보증 유효: <label><input type="checkbox" name="guaranteeValid">
				예</label>
		</p>
		<p>
			CL 번호(선택, 13자리): <input type="text" name="clNumber" maxlength="13"
				pattern="\\d{0,13}">
		</p>

		<button type="submit">신청하기</button>
	</form>
</body>
</html>