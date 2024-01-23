<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 목록</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .post-preview {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 10px;
            cursor: pointer;
            background-color: #f8f8f8;
            height: 100px; /* 고정 높이 설정 */
        }
        .empty-post {
            text-align: center;
            color: #999;
        }
        #pagination a {
            margin-right: 5px;
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
                        <div class="post-preview">
                            <h3>${post.title}</h3>
                            <p>${post.content}</p>
                            <a href="/posts/${post.id}">자세히 보기</a>
                        </div>
                    </c:if>
                </c:forEach>
                <c:forEach begin="1" end="${6 - fn:length(posts)}">
                    <div class="post-preview empty-post">
                        게시글이 없습니다.
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach begin="1" end="6">
                    <div class="post-preview empty-post">
                        게시글이 없습니다.
                    </div>
                </c:forEach>
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
</body>
</html>
