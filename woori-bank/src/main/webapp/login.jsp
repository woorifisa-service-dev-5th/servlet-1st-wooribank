<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
    <h2>로그인</h2>
    
    <%-- 회원가입 성공 메시지 --%>
    <c:if test="${not empty successMsg}">
        <p style="color:green;">${successMsg}</p>
    </c:if>

    <%-- 에러 메시지 --%>
    <c:if test="${not empty errorMsg}">
        <p style="color:red;">${errorMsg}</p>
    </c:if>
    
    <form action="user/login" method="post">
        <div>
            <label for="id">아이디</label>
            <input type="text" name="id" id="id" placeholder="아이디 입력" required>
        </div>
        
        <div>
            <label for="password">비밀번호</label>
            <input type="password" name="password" id="password" placeholder="비밀번호 입력" required>
        </div>
        
        <div>
            <button type="submit">로그인</button>
        </div>
    </form>
</body>
</html>
