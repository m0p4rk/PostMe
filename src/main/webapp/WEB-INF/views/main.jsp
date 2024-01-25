<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PostMe</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
.post-preview {
	border: 1px solid #ddd;
	padding: 15px;
	margin-bottom: 10px;
	cursor: pointer;
	background-color: #f8f8f8;
	height: 100px;
	overflow: hidden;
}

.post-preview h3, .post-preview p {
	white-space: nowrap;
	overflow: hidden;
	text-overflow: ellipsis;
}

.empty-post {
	text-align: center;
	color: #999;
}

#pagination {
	display: flex;
	justify-content: center; 
	padding-top: 20px;
}

#pagination a {
	color: #007bff; 
	text-decoration: none;
	padding: 5px 10px;
	border: 1px solid #ddd;
	margin: 0 2px;
}

#pagination a:hover {
	background-color: #f8f8f8;
}

#pagination span {
	padding: 5px 10px;
	border: 1px solid #ddd;
	margin: 0 2px;
	background-color: #007bff;
	color: white;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<c:choose>
			<c:when test="${not empty posts}">
				<c:forEach items="${posts}" var="post" varStatus="status">
					<c:if test="${status.count <= 6}">
						<div class="post-preview"
							onclick="location.href='/posts/${post.id}'">
							<h3>${post.title}</h3>
							<p>${post.content}</p>
						</div>
					</c:if>
				</c:forEach>
				<c:forEach begin="1" end="${6 - fn:length(posts)}">
					<div class="post-preview empty-post">게시글이 없습니다.</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach begin="1" end="6">
					<div class="post-preview empty-post">게시글이 없습니다.</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>

		<nav id="pagination">
			<c:forEach begin="1" end="${totalPages}" var="pageNum">
				<c:choose>
					<c:when test="${pageNum == currentPage}">
						<span>${pageNum}</span>
					</c:when>
					<c:otherwise>
						<a href="?page=${pageNum}">${pageNum}</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</nav>
	</div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script>
		function handlePostClick(postId) {
			window.location.href = '/posts/' + postId;
		}
	</script>
</body>
</html>
