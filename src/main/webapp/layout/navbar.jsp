<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="top-navbar d-flex justify-content-between align-items-center">
    <h5 class="m-0 fw-bold text-dark" id="page-title">
        <i class="fa-solid fa-chart-pie text-primary me-2"></i>Tổng quan hệ thống
    </h5>
    <div class="d-flex align-items-center gap-3">
        <div class="dropdown">
            <button class="btn btn-light rounded-pill px-3 py-2 border d-flex align-items-center gap-2" type="button" data-bs-toggle="dropdown">
                <i class="fa-solid fa-circle-user fa-lg text-primary"></i>
                <div class="text-start d-none d-md-block">
                    <span class="fw-semibold d-block" style="font-size: 14px; line-height: 1.2;">${sessionScope.currentUser.hoTen}</span>
                 
                    <span class="badge bg-primary text-white" style="font-size: 10px;">${sessionScope.currentUser.chucVu}</span>
                </div>
               
                <span class="fw-semibold d-md-none">${sessionScope.currentUser.hoTen}</span>
            </button>
            <ul class="dropdown-menu dropdown-menu-end shadow-sm border-0 mt-2">
                <li>
                    <a class="dropdown-item py-2" href="${pageContext.request.contextPath}/profile">
                        <i class="fa-solid fa-user me-2 text-muted"></i>Hồ sơ
                    </a>
                </li>
                <li><hr class="dropdown-divider"></li>
                <li>
                    <a class="dropdown-item py-2 text-danger" href="${pageContext.request.contextPath}/logout">
                        <i class="fa-solid fa-right-from-bracket me-2"></i>Đăng xuất
                    </a>
                </li>
            </ul>
        </div>
    </div>
</div>