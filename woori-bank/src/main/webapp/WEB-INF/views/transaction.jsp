<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계좌 송금</title>
<style>
    body {
        font-family: Arial, sans-serif;
        margin: 20px;
    }
    .box {
        border: 1px solid #ddd;
        padding: 20px;
        max-width: 400px;
        border-radius: 8px;
    }
    label {
        display: block;
        margin-top: 10px;
    }
    input, select {
        width: 100%;
        padding: 6px;
        margin-top: 4px;
    }
    button {
        margin-top: 15px;
        padding: 10px;
        width: 100%;
        background: #0074D9;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
    button:hover {
        background: #005fa3;
    }
</style>
</head>
<body>
    <a href="<%=request.getContextPath()%>/">&larr; 메인</a>
    
    <%-- 성공 메시지 --%>
    <c:if test="${not empty successMsg}">
        <p style="color:green;">${successMsg}</p>
    </c:if>

    <%-- 에러 메시지 --%>
    <c:if test="${not empty errorMsg}">
        <p style="color:red;">${errorMsg}</p>
    </c:if>

    <div class="box" style="margin-top: 12px;">
        <h2>계좌 송금하기</h2>

        <form method="post" action="<%=request.getContextPath()%>/transaction">
            <!-- 송금자 계좌 -->
            <label for="fromAccount">송금자 계좌번호</label>
            <input type="text" id="from" name="from" required>

            <!-- 수취인 계좌 -->
            <label for="toAccount">수취인 계좌번호</label>
            <input type="text" id="to" name="to" required>

            <!-- 송금 금액 -->
            <label for="amount">송금 금액</label>
            <input type="number" id="amount" name="amount" min="1" required>

            <!-- 메모 -->
            <label for="description">메모 (선택)</label>
            <input type="text" id="description" name="description">

            <button type="submit">송금하기</button>
        </form>

        <p style="color: #666; margin-top: 8px;">※ 송금은 로그인 후 가능합니다.</p>
    </div>
</body>
</html>