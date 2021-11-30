<%@ page import="com.koreait.web.model.BoardVO" %>
<%@ page import="com.koreait.web.model.UserVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoardVO vo = (BoardVO) request.getAttribute("detailData");
    UserVO user = (UserVO) session.getAttribute("loginUser");
    String err = (String) request.getAttribute("err");
    int pre = (int) request.getAttribute("pre");
    int next = (int) request.getAttribute("next");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>
    <a href="/board/detail?id=<%=pre%>"><input type="button" value="이전 글"></a>
    <a href="/board/detail?id=${requestScope.next}"><input type="button" value="다음 글"></a>
    <a href="/user/login">${requestScope.err}</a>
    <a href="/board/list"><input type="button" value="리스트"></a>
    <div>번호: <%=vo.getId()%></div>
    <div>제목: <%=vo.getTitle()%></div>
    <div>카테고리 : <%=vo.getCategory()%></div>
    <div>작성일자: <%=vo.getRdt()%></div>
    <div>작성자: <%=vo.getWriterNm()%></div>
    <div>가격: <%=vo.getPrice()%> 원</div>
    <div>-- 내용 --</div>
    <div><%=vo.getContents()%></div>
    <% if(user != null && user.getIuser()==vo.getWriter()) { %>
    <a href="/board/del?id=<%=vo.getId()%>"><input type="button" value="삭제"></a>
    <a href="/board/mod?id=<%=vo.getId()%>"><input type="button" value="편집"></a>
    <% } %>
</body>
</html>
