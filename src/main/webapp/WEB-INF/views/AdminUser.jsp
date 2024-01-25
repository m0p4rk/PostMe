<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* 기존 스타일 */
        .user-management-container {
            flex-grow: 1;
            background-color: #f8f8f8;
            padding: 20px;
            border-radius: 5px;
        }

        .user-container {
            background-color: white;
            padding: 15px;
            margin-bottom: 10px;
            border-radius: 5px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .user-details {
            flex-grow: 1;
        }

        /* 페이지네이션 스타일 */
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
    <div class="d-flex">
        <jsp:include page="sidebar.jsp" />
        <div class="user-management-container">
            <h1>사용자 관리</h1>
            <c:choose>
                <c:when test="${not empty usersList}">
                    <c:forEach items="${usersList}" var="user">
                        <div class="user-container">
                            <div class="user-details">
                                <p>이름: ${user.name}</p>
                                <p>이메일: ${user.email}</p>
                            </div>
                            <div>
                                <a href="/admin/users/edit/${user.id}" class="btn btn-primary">수정</a>
                                <a href="/admin/users/delete/${user.id}" class="btn btn-danger">삭제</a>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>사용자가 없습니다.</p>
                </c:otherwise>
            </c:choose>
            
            <!-- 페이지네이션 -->
            <nav id="pagination">
                <c:forEach begin="1" end="${totalPages}" var="pageNum">
                    <c:choose>
                        <c:when test="${pageNum == currentPage}">
                            <span>${pageNum}</span> <!-- 현재 페이지 -->
                        </c:when>
                        <c:otherwise>
                            <a href="?page=${pageNum}">${pageNum}</a> <!-- 다른 페이지 링크 -->
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </nav>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
