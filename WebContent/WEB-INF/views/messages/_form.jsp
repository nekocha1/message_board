<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${errors != null}">
	    <div id="flush_error">
	        入力内容にエラーがあります。<br />
	        <c:forEach var="error" items="${errors}">
	            ・<c:out value="${error}" /><br />
	        </c:forEach>

	    </div>
	</c:if>

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