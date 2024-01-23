<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Post Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <jsp:include page="navbar.jsp" />
    <div class="container">
        <div id="post-details">
            <!-- 게시글 상세 내용이 여기에 표시되어야 함 -->
        </div>
        <button id="edit-btn" class="btn btn-primary">수정</button>
        <button id="delete-btn" class="btn btn-danger">삭제</button>
        <div id="edit-form" style="display: none;">
            <input type="text" id="title-edit" class="form-control" />
            <textarea id="content-edit" class="form-control"></textarea>
            <button id="save-edit" class="btn btn-success">저장</button>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="resources/static/js/post-detail.js"></script>
</body>
</html>
