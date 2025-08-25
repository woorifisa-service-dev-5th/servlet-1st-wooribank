<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.woori_bank.be.account.model.Account" %>

<html>
<head>
    <title>내 계좌 목록</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; }
        table { border-collapse: collapse; width: 80%; margin: auto; }
        th, td { border: 1px solid #ccc; padding: 10px; text-align: center; }
        th { background-color: #f2f2f2; }
        h2 { text-align: center; margin-bottom: 20px; }
    </style>
</head>
<body>
    <h2>내 계좌 목록</h2>

    <c:choose>
        <c:when test="${empty accountList}">
            <p style="text-align:center;">등록된 계좌가 없습니다.</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>ID</th>
                    <th>계좌번호</th>
                    <th>계좌종류</th>
                    <th>개설일</th>
                    <th>잔액</th>
                    <th>고객ID</th>
                </tr>
                <c:forEach var="account" items="${accountList}">
                    <tr>
                        <td>${account.id}</td>
                        <td>${account.number}</td>
                        <td>${account.type}</td>
                        <td>${account.createAt}</td>
                        <td>${account.balance}</td>
                        <td>${account.clientId}</td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</body>
</html>