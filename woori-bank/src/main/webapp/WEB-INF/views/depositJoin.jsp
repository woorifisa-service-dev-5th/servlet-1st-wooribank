<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post"
		action="<%=request.getContextPath()%>/deposit/join">
		<input type="hidden" name="accountId"
			value="<%=request.getAttribute("accountId")%>" />
		<p>
			금액: <input type="number" name="amount" min="0" step="1000" required />
		</p>
		<p>
			만기일: <input type="date" name="maturityDate" />
		</p>
		<button type="submit">가입하기</button>
	</form>
</body>
</html>