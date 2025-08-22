<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>대출 상품</title>
</head>
<body>
	<a href="<%=request.getContextPath()%>/">&larr; 메인</a>

	<div class="box" style="margin-top: 12px;">
		<h2>우리 주거래 직장인대출(인터넷뱅킹)</h2>

		<ul>
			<li><b>대출종류</b>: 신용대출, 일반직장인대출, 인터넷신용</li>
			<li><b>대출대상</b>: 일반기업, 지정 우량기업 재직자 모두를 위한 우리은행 대표 신용대출</li>
			<li><b>대출기간</b>: 1년 ~ 5년</li>
			<li><b>대출한도</b>: 최대 2억원</li>
		</ul>

		<!-- 가입하기 -->
		<a href="<%=request.getContextPath()%>/loan/join">가입하기</a>

		<p style="color: #666; margin-top: 8px;">※ 가입은 로그인 후 가능합니다.</p>
	</div>
</body>
</html>