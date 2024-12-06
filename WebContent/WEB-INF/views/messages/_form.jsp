<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<label for="title">タイトル</label><br>
	<input type="text" name="title" id="title" value="${message.title}"/>
	<br><br>

	<label for="content-msg">メッセージ</label><br>
	<input type="text" name="content" id="content-msg" value="${message.title}"/>
	<br><br>

	<input type="hidden" name="_token" value="${_token}"/>
	<button type="submit">投稿</button>
</body>
</html>