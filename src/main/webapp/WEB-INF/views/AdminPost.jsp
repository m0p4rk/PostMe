<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post Management</title>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	rel="stylesheet">
<style>
.sidebar {
	position: fixed;
	top: 0; /* 상단에 고정 */
	left: 0; /* 왼쪽에 고정 */
	width: 250px; /* 너비 설정 */
	height: 100%; /* 전체 높이 */
	/* 기타 스타일 */
}

.post-management-container {
	margin-left: 250px;
	flex-grow: 1;
	background-color: #f8f8f8;
	padding: 20px;
	border-radius: 5px;
}

.post-container {
	background-color: white;
	padding: 15px;
	margin-bottom: 10px;
	border-radius: 5px;
	box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.post-details {
	flex-grow: 1;
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
	<%
		response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0L);
	%>
	<div class="d-flex">
		<jsp:include page="sidebar.jsp" />
		<div class="post-management-container">
			<h1>게시물 관리</h1>
			<c:choose>
				<c:when test="${not empty postsList}">
					<c:forEach items="${postsList}" var="post">
						<div class="post-container">
							<div class="post-details">
								<p>제목: ${post.title}</p>
								<p>내용: ${post.content}</p>
								<p>작성자: ${post.userId}</p>
								<p>개시글 고유번호: ${post.id}</p>
							</div>
							<div>
								<a href="#" onclick="deletePost(${post.id})"
									class="btn btn-danger">삭제</a>
							</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<p>게시물이 없습니다.</p>
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
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script>
		function deletePost(postId) {
			var confirmDeletion = confirm('게시물을 삭제하시겠습니까?');

			if (confirmDeletion) {
				$.ajax({
					url : '/admin/posts/delete/' + postId,
					type : 'POST',
					success : function(response) {
						alert('게시물이 삭제되었습니다.');
						location.reload();
					},
					error : function(error) {
						alert('오류가 발생했습니다.');
					}
				});
			}
		}
	</script>
</body>
</html>
