<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Settings</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .settings-container {
            flex-grow: 1;
            background-color: #f8f8f8;
            padding: 20px;
            border-radius: 5px;
        }

        .reset-button {
            background-color: #dc3545;
            color: white;
        }

        .reset-button:hover {
            background-color: #c82333;
        }
    </style>
</head>
<body>
    <div class="d-flex">
        <jsp:include page="sidebar.jsp" />
        <div class="settings-container">
            <h1>설정</h1>
            <form action="/admin/reset" method="post">
                <div class="form-group">
                    <p>이 버튼을 누르면 전체 데이터가 초기화됩니다. 이 작업은 되돌릴 수 없습니다.</p>
                    <button type="submit" class="btn btn-primary reset-button">전체 초기화</button>
                </div>
            </form>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
