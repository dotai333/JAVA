<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/layout/head.jsp">
    <jsp:param name="title" value="Hồ sơ cá nhân" />
</jsp:include>

<div class="d-flex min-vh-100">

    <!-- Sidebar -->
    <jsp:include page="/layout/sidebar.jsp" />

    <div class="main-content flex-grow-1 d-flex flex-column">

        <!-- Navbar -->
        <jsp:include page="/layout/navbar.jsp">
            <jsp:param name="pageTitle" value="Hồ sơ cá nhân" />
        </jsp:include>

        <main class="p-4 flex-grow-1">
            <div class="container" style="max-width: 800px;">

                <!-- Hiển thị thông báo thành công / lỗi -->
                <c:if test="${param.success == 'changed'}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        <i class="fa-solid fa-check-circle me-2"></i> Đổi mật khẩu thành công!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <c:if test="${param.error == 'wrong_old_pass'}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="fa-solid triangle-exclamation me-2"></i> Mật khẩu cũ không chính xác!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <c:if test="${param.error == 'not_match'}">
                    <div class="alert alert-danger alert-dismissible fade show" role="alert">
                        <i class="fa-solid triangle-exclamation me-2"></i> Mật khẩu mới và xác nhận không khớp nhau!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <div class="row g-4">
                    <!-- Cột trái: Thông tin cơ bản -->
                    <div class="col-md-4">
                        <div class="card shadow-sm border-0 text-center p-4">
                            <div class="bg-primary text-white rounded-circle d-flex align-items-center justify-content-center mx-auto mb-3 shadow" style="width: 80px; height: 80px; font-size: 2rem;">
                                <i class="fa-solid fa-user"></i>
                            "></div>
                            <h5 class="fw-bold mb-1">${sessionScope.currentUser.hoTen}</h5>
                            <p class="text-muted mb-2">@${sessionScope.currentUser.tenDangNhap}</p>
                            <div>
                                <c:choose>
                                    <c:when test="${sessionScope.currentUser.chucVu == 'ADMIN'}">
                                        <span class="badge bg-danger px-3 py-2">Quản trị viên (ADMIN)</span>
                                    </c:when>
                                    <c:when test="${sessionScope.currentUser.chucVu == 'GIAOVU'}">
                                        <span class="badge bg-primary px-3 py-2">Cán bộ Giáo vụ</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-success px-3 py-2">Giáo viên</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>

                    <!-- Cột phải: Form đổi mật khẩu -->
                    <div class="col-md-8">
                        <div class="card shadow-sm border-0 p-4">
                            <h5 class="fw-bold text-dark mb-3 border-bottom pb-2">
                                <i class="fa-solid fa-key me-2 text-primary"></i> Đổi mật khẩu
                            </h5>

                            <form action="${pageContext.request.contextPath}/profile?action=changePassword" method="post">
                                <div class="mb-3">
                                    <label class="form-label fw-semibold">Mật khẩu hiện tại:</label>
                                    <input type="password" name="oldPass" class="form-control" placeholder="Nhập mật khẩu cũ..." required>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label fw-semibold">Mật khẩu mới:</label>
                                    <input type="password" name="newPass" class="form-control" placeholder="Nhập mật khẩu mới..." required>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label fw-semibold">Xác nhận mật khẩu mới:</label>
                                    <input type="password" name="confirmPass" class="form-control" placeholder="Nhập lại mật khẩu mới..." required>
                                </div>

                                <div class="d-flex justify-content-end mt-4">
                                    <button type="submit" class="btn btn-primary px-4">
                                        <i class="fa-solid fa-floppy-disk me-1"></i> Lưu thay đổi
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </main>

        <!-- Footer -->
        <jsp:include page="/layout/footer.jsp" />
    </div>
</div>