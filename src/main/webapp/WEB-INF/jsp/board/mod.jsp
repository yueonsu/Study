<%@ page import="com.koreait.web.model.BoardVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    BoardVO list = (BoardVO) request.getAttribute("modData");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<body>
    ${err}
<form action="/board/mod?id=${requestScope.modData.id}" method="post">
    <div><input type="text" name="title" placeholder="title" value="${requestScope.modData.title}"></div>
    <div>
        <textarea name="contents" placeholder="contents" rows="20">${requestScope.modData.contents}</textarea>
    </div>
    <div><input type="text" name="category" placeholder="category" value="${requestScope.modData.category}"></div>
    <div><input type="text" name="price" placeholder="price" value="${requestScope.modData.price}"></div>
    <div>
        <input type="submit" value="등록">
        <input type="reset" value="초기화">
        <a href="/board/list"><input type="button" value="리스트"></a>
    </div>
</form>
</body>
</html>
