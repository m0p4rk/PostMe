<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PostMe</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        .post-preview {
            border: 1px solid #ddd;
            padding: 15px;
            margin-bottom: 10px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <jsp:include page="navbar.jsp" />
    <!-- Main Body -->
    <div class="container">
        <div id="posts-container">
            <!-- AJAX loading -->
        </div>
        <nav id="pagination">
            <!-- pagination buttons -->
        </nav>
    </div>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="/static/js/main.js"></script>
</body>
</html>
