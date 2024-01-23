<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Post Details</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<style>
.modal-content, #comment-form {
	background-color: #f8f8f8; /* 희미한 회색 배경 */
}

.form-group {
	margin-bottom: 15px;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container">
		<div id="post-details">
			<h2>${post.title}</h2>
			<p>${post.content}</p>
		</div>

		<!-- 수정 버튼 -->
		<button type="button" class="btn btn-primary" data-toggle="modal"
			data-target="#editModal">수정</button>

		<!-- 삭제 버튼 -->
		<form action="/posts/delete/${post.id}" method="post"
			style="display: inline;">
			<input type="submit" value="삭제" class="btn btn-danger">
		</form>

		<!-- Modal -->
		<div class="modal fade" id="editModal" tabindex="-1" role="dialog"
			aria-labelledby="editModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editModalLabel">게시글 수정</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<form action="/posts/update/${post.id}" method="post">
						<div class="modal-body">
							<div class="form-group">
								<label for="title">제목</label> <input type="text"
									class="form-control" id="title" name="title"
									value="${post.title}">
							</div>
							<div class="form-group">
								<label for="content">내용</label>
								<textarea class="form-control" id="content" name="content">${post.content}</textarea>
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">닫기</button>
							<input type="submit" class="btn btn-primary" value="저장">
						</div>
					</form>
				</div>
			</div>
		</div>


		<!-- 댓글 입력란 -->
		<div id="comment-form" class="mt-4">
			<form action="/posts/comment/${post.id}" method="post">
				<div class="form-group">
					<label for="comment">댓글 작성</label>
					<textarea class="form-control" id="comment" name="comment"
						placeholder="댓글을 입력하세요"></textarea>
				</div>
				<button type="submit" class="btn btn-primary">댓글 등록</button>
			</form>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
