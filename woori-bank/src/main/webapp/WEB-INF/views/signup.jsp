<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
    <h2 style="text-align:center;">회원가입</h2>
    
    <form action="<%=request.getContextPath()%>/user/signup" method="post">
        <div>
            <label for="id">아이디</label>
            <input type="text" name="id" id="id" placeholder="아이디 입력" required>
        </div>
        
        <div>
            <label for="password">비밀번호</label>
            <input type="password" name="password" id="password" placeholder="비밀번호 입력" required>
        </div>
        
        <div>
            <label for="ssn">주민등록번호</label>
            <input type="text" name="ssn" id="ssn" placeholder="주민번호 입력 (예: 000000-0000000)" required>
        </div>
        
        <div>
            <label for="name">이름</label>
            <input type="text" name="name" id="name" placeholder="이름 입력" required>
        </div>
        
        <div>
            <label for="phone">전화번호</label>
            <input type="text" name="phone" id="phone" placeholder="전화번호 입력 (예: 010-0000-0000)" required>
        </div>
        
        <div>
            <label for="birth">생년월일</label>
            <input type="date" name="birth" id="birth" required>
        </div>
        
        <div>
            <label for="address">주소</label>
            <input type="text" name="address" id="address" placeholder="주소 입력" required>
        </div>
        
        <div>
            <label for="email">이메일</label>
            <input type="email" name="email" id="email" placeholder="이메일 입력" required>
        </div>
        
        <div>
            <label for="job">직업</label>
            <input type="text" name="job" id="job" placeholder="직업 입력">
        </div>
        
        <div>
            <button type="submit">회원가입</button>
        </div>
    </form>
</body>
</html>
