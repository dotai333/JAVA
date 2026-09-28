<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/layout/head.jsp">
    <jsp:param name="title" value="Thêm tài khoản mới" />
</jsp:include>

<div class="d-flex min-vh-100">

    <!-- Sidebar -->
    <jsp:include page="/layout/sidebar.jsp">
        <jsp:param name="active" value="taikhoan" />
    </jsp:include>

    <div class="main-content flex-grow-1 d-flex flex-column">

        <!-- Navbar -->
        <jsp:include page="/layout/navbar.jsp">
            <jsp:param name="pageTitle" value="Thêm tài khoản mới" />
        </jsp:include>

        <main class="p-4 flex-grow-1">
            <div class="bg-white p-4 rounded-3 shadow-sm" style="max-width: 650px; margin: 0 auto;">

                <!-- Tiêu đề Form -->
                <div class="d-flex justify-content-between align-items-center mb-4 border-bottom pb-3">
                    <h5 class="fw-bold text-dark mb-0">
                        <i class="fa-solid fa-user-plus me-2 text-primary"></i> THÊM TÀI KHOẢN MỚI
                    </h5>
                    <a href="${pageContext.request.contextPath}/taikhoan?action=list" class="btn btn-outline-secondary btn-sm">
                        <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                    </a>
                </div>

                <!-- Form nhập liệu -->
                <form action="${pageContext.request.contextPath}/taikhoan?action=insert" method="post">

                    <div class="mb-3">
                        <label for="tenDangNhap" class="form-label fw-semibold">Tên đăng nhập (Username) <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="tenDangNhap" name="tenDangNhap" placeholder="Nhập tên đăng nhập..." required>
                    </div>

                    <div class="mb-3">
                        <label for="matKhau" class="form-label fw-semibold">Mật khẩu <span class="text-danger">*</span></label>
                        <input type="password" class="form-control" id="matKhau" name="matKhau" placeholder="Nhập mật khẩu..." required>
                    </div>

                    <div class="mb-3">
                        <label for="hoTen" class="form-label fw-semibold">Họ và tên <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="hoTen" name="hoTen" placeholder="Nhập họ tên đầy đủ..." required>
                    </div>

                    <div class="mb-3">
                        <label for="chucVu" class="form-label fw-semibold">Chức vụ <span class="text-danger">*</span></label>
                        <select name="chucVu" id="chucVu" class="form-select" required>
                            <option value="" disabled selected>-- Chọn chức vụ --</option>
                            <option value="ADMIN">ADMIN (Quản trị viên tối cao)</option>
                            <option value="GIAOVU">GIAOVU (Cán bộ giáo vụ)</option>
                            <option value="GIAOVIEN">GIAOVIEN (Giáo viên)</option>
                        </select>
                    </div>

                    <div class="d-flex justify-content-end gap-2 mt-4 pt-3 border-top">
                        <a href="${pageContext.request.contextPath}/taikhoan?action=list" class="btn btn-light border">Hủy</a>
                        <button type="submit" class="btn btn-primary px-4">
                            <i class="fa-solid fa-floppy-disk me-1"></i> Lưu lại
                        </button>
                    </div>

                </form>

            </div>
        </main>

        <!-- Footer -->
        <jsp:include page="/layout/footer.jsp" />
    </div>
</div>