<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <h1>로그인</h1>
    ${err}
    <form action="/user/login" method="post">
        <div><input type="text" name="uid" placeholder="user id"></div>
        <div><input type="password" name="upw" placeholder="user password"></div>
        <div>
            <input type="submit" value="login">
            <a href="/user/join"><input type="button" value="join"></a>
        </div>
    </form>
</body>
</html>
