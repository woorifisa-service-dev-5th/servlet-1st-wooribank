<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Object typeObj = request.getAttribute("type");
    String type = typeObj != null ? typeObj.toString() : request.getParameter("type");
    if (type == null) type = "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계좌 생성 동의</title>
<script>
  function toggleSubmit() {
    var agree = document.getElementById('agree');
    document.getElementById('submitBtn').disabled = !agree.checked;
  }
</script>
</head>
<body>
  <h2>계좌 생성 동의</h2>
  <p>계좌 유형: <strong><%= type %></strong></p>

  <form action="<%= request.getContextPath() %>/account/create" method="post">
    <!-- 필수: 타입 유지 -->
    <input type="hidden" name="type" value="<%= type %>"/>

    <!-- 동의 항목들 (예시) -->
    <div>
      <label>
        <input type="checkbox" id="agree" name="agree" value="Y" onclick="toggleSubmit()"/>
        계좌 개설 및 약관에 동의합니다.
      </label>
    </div>

    <button type="submit" id="submitBtn" disabled>확인</button>
  </form>
</body>
</html>
