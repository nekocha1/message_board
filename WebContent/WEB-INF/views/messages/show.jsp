<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<c:import url="../layout/app.jsp">
		<c:param name="content">
			<c:choose>
				<c:when test="${message!=null}">
					<h2>id:${message.id}のメッセージ詳細ページ</h2>

					<table>
						<tbody>
					      <tr>
					        <td>タイトル:<c:out value="${message.title}" /></td>
					      </tr>
					      <tr>
					        <td>メッセージ<c:out value="${message.content}" /></td>
					      </tr>
					      <tr>
					        <td>作成日時:<fmt:formatDate value="${message.created_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					      </tr>
					      <tr>
					        <td>更新日時:<fmt:formatDate value="${message.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					      </tr>
					   </tbody>
					</table>
					<p><a href="${pageContext.request.contextPath}/index">一覧に戻る</a></p>
		        	<p><a href="${pageContext.request.contextPath}/edit?id=${message.id}">このメッセージを編集する</a></p>
				</c:when>
				<c:otherwise>
					<h2>お探しのデータは見つかりませんでした。</h2>
				</c:otherwise>
			</c:choose>
		</c:param>
	</c:import>
</body>
</html>