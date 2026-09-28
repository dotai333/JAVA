<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Đăng Nhập - Hệ Thống Quản Lý Giáo Vụ</title>
    <!-- Nhúng Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex align-items-center justify-content-center vh-100">

    <div class="card shadow border-0 rounded-4 p-4" style="width: 400px;">
        <h3 class="text-center fw-bold mb-4 text-primary">Đăng Nhập Hệ Thống</h3>
        
        <!-- Hiển thị cảnh báo nếu đăng nhập sai -->
        <c:if test="${param.error == 'invalid'}">
            <div class="alert alert-danger py-2 text-center" role="alert">
                Sai tên đăng nhập hoặc mật khẩu!
            </div>
        </c:if>

        <form action="login" method="POST">
            <div class="mb-3">
                <label class="form-label fw-semibold">Tên đăng nhập</label>
                <input type="text" name="username" class="form-control rounded-3" placeholder="Nhập tài khoản..." required>
            </div>
            <div class="mb-3">
                <label class="form-label fw-semibold">Mật khẩu</label>
                <input type="password" name="password" class="form-control rounded-3" placeholder="Nhập mật khẩu..." required>
            </div>
            <button type="submit" class="btn btn-primary w-100 py-2 rounded-3 fw-bold">Đăng Nhập</button>
        </form>
    </div>

</body>
</html>