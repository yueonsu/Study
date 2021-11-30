<%@ page import="com.koreait.web.model.BoardVO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.koreait.web.model.UserVO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int maxPage = (int) request.getAttribute("maxPage");
    String option = (String)  request.getAttribute("option");
    String search = (String) request.getAttribute("search");
    List<BoardVO> list = (List<BoardVO>) request.getAttribute("listData");
    UserVO user = (UserVO) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>search</title>
</head>
<body>
    <h1>검색결과</h1>
    <div>
        <a href="/board/write"><input type="button" value="글쓰기"></a>
        <% if(user == null) { %>
        <a href="/user/login"><input type="button" value="로그인"></a>
        <a href="/board/list"><input type="button" value="리스트"></a>
        <% } else { %>
        <a href="/user/logout"><input type="button" value="로그아웃"></a>
        <% } %>
        <form action="/board/search" method="get">
            <input type="text" name="search" placeholder="검색어">
            <select name="option">
                <option value="title">제목</option>
                <option value="contents">내용</option>
                <option value="nm">작성자</option>
                <option value="double">제목+내용</option>
            </select>
            <input type="submit" value="검색">
        </form>
    </div>
    <% if(!"".equals(search) && !list.isEmpty()) { %>
    <table>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>가격</th>
            <th>작성일자</th>
        </tr>
        <% for(BoardVO vo : list) { %>
        <tr>
            <td><%=vo.getId()%></td>
            <td><a href="/board/detail?id=<%=vo.getId()%>"><%=vo.getTitle()%></a></td>
            <td><%=vo.getWriterNm()%></td>
            <td><%=vo.getPrice()%> 원</td>
            <td><%=vo.getRdt()%></td>
        </tr>
        <% } %>
    </table>
        <% for(int i=1; i<=maxPage; i++) { %>
            <a href="/board/search?option=<%=option%>&search=<%=search%>&page=<%=i%>"><span><%=i%></span></a>&nbsp;
        <% } %>
    <% } %>
    ${err}
</body>
</html>
