<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="d-flex flex-column flex-shrink-0 p-3 bg-light" style="width: 250px; height: 100vh;">
    <a href="/" class="d-flex align-items-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
        <span class="fs-4">PostMe - Admin</span>
    </a>
    <hr>
    <ul class="nav nav-pills flex-column mb-auto">
        <li class="nav-item">
            <a href="/admin/dashboard" class="nav-link link-dark" aria-current="page">
                대시보드
            </a>
        </li>
        <li>
            <a href="/admin/users" class="nav-link link-dark">
                사용자 관리
            </a>
        </li>
        <li>
            <a href="/admin/posts" class="nav-link link-dark">
                게시물 관리
            </a>
        </li>
        <li>
            <a href="/admin/settings" class="nav-link link-dark">
                설정
            </a>
        </li>
    </ul>
    <hr>
    <div class="dropdown">
        <a href="#" class="d-flex align-items-center link-dark text-decoration-none dropdown-toggle" id="dropdownUser" data-bs-toggle="dropdown" aria-expanded="false">
            <strong>더보기</strong>
        </a>
        <ul class="dropdown-menu text-small shadow" aria-labelledby="dropdownUser">
            <li><a class="dropdown-item" href="#">추가 기능 1</a></li>
            <li><a class="dropdown-item" href="#">추가 기능 2</a></li>
            <li><hr class="dropdown-divider"></li>
            <li><a class="dropdown-item" href="#">로그아웃</a></li>
        </ul>
    </div>
</div>
