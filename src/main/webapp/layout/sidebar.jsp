<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav id="sidebar">
    
    <div class="sidebar-header d-flex align-items-center">
        <div class="bg-primary bg-gradient p-2 rounded-3 me-3 text-white d-flex align-items-center justify-content-center" style="width: 40px; height: 40px;">
            <i class="fa-solid fa-graduation-cap fa-lg"></i>
        </div>
        <div>
            <h5 class="m-0 font-weight-bold text-white">QLGiaoVu</h5>
            <small class="text-white-50" style="font-size: 0.75rem;">Hệ thống Giáo vụ</small>
        </div>
    </div>

    <ul class="list-unstyled components">
        <li class="active">
            <a href="${pageContext.request.contextPath}/dashboard">
                <i class="fa-solid fa-chart-pie"></i>
                <span>Dashboard</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/khoa?action=list">
                <i class="fa-solid fa-sitemap"></i>
                <span>Quản lý Khoa</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/sinhvien?action=list">
                <i class="fa-solid fa-user-graduate"></i>
                <span>Quản lý Sinh viên</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/monhoc?action=list">
                <i class="fa-solid fa-book-open"></i>
                <span>Quản lý Môn học</span>
            </a>
        </li>
        <li>
            <a href="${pageContext.request.contextPath}/dangky?action=list">
                <i class="fa-solid fa-pen-to-square"></i>
                <span>Đăng ký & Điểm</span>
            </a>
        </li>

      
        <c:if test="${sessionScope.currentUser.chucVu == 'ADMIN'}">
            <hr class="text-white-50 my-2 mx-3">
            <li class="px-3 text-uppercase text-white-50" style="font-size: 0.7rem; letter-spacing: 1px;">Quản trị</li>
            <li>
                <a href="${pageContext.request.contextPath}/taikhoan">
                    <i class="fa-solid fa-user-shield text-warning"></i>
                    <span>Quản lý tài khoản</span>
                </a>
            </li>
        </c:if>
        <!-- ================================================================= -->
    </ul>
</nav>