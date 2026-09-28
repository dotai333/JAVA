<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/layout/head.jsp">
    <jsp:param name="title" value="Quản lý tài khoản" />
</jsp:include>

<div class="d-flex min-vh-100">

    <!-- Sidebar -->
    <jsp:include page="/layout/sidebar.jsp">
        <jsp:param name="active" value="taikhoan" />
    </jsp:include>

    <div class="main-content flex-grow-1 d-flex flex-column">

        <!-- Navbar -->
        <jsp:include page="/layout/navbar.jsp">
            <jsp:param name="pageTitle" value="Quản lý tài khoản hệ thống" />
        </jsp:include>

        <main class="p-4 flex-grow-1">
            <div class="container-fluid px-0">
                
                <!-- Tiêu đề & Nút thêm -->
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h4 class="fw-bold text-dark mb-0">
                        <i class="fa-solid fa-user-shield me-2 text-primary"></i> DANH SÁCH TÀI KHOẢN HỆ THỐNG
                    </h4>
                    <a href="taikhoan?action=add" class="btn btn-primary">
                        <i class="fa-solid fa-user-plus me-1"></i> Thêm tài khoản mới
                    </a>
                </div>

                <!-- Bảng danh sách -->
                <div class="card shadow-sm border-0">
                    <div class="card-body">
                        <table class="table table-hover align-middle mb-0">
                            <thead class="table-light">
                                <tr>
                                    <th>Tên đăng nhập</th>
                                    <th>Họ và tên</th>
                                    <th>Chức vụ</th>
                                    <th class="text-center">Thao tác</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="tk" items="${listTK}">
                                    <tr>
                                        <td class="fw-semibold">${tk.tenDangNhap}</td>
                                        <td>${tk.hoTen}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${tk.chucVu == 'ADMIN'}">
                                                    <span class="badge bg-danger">ADMIN</span>
                                                </c:when>
                                                <c:when test="${tk.chucVu == 'GIAOVU'}">
                                                    <span class="badge bg-primary">GIÁO VỤ</span>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="badge bg-success">GIÁO VIÊN</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td class="text-center">
                                            <!-- Khung bảo mật: Chỉ ADMIN mới thấy nút Xóa -->
                                            <c:if test="${sessionScope.currentUser.chucVu == 'ADMIN'}">
                                                <a href="taikhoan?action=delete&tenDangNhap=${tk.tenDangNhap}" 
                                                   class="btn btn-sm btn-outline-danger" 
                                                   title="Xóa tài khoản"
                                                   onclick="return confirm('Bạn có chắc chắn muốn xóa tài khoản [${tk.tenDangNhap}] không?');">
                                                    <i class="fa-solid fa-trash"></i>
                                                </a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty listTK}">
                                    <tr>
                                        <td colspan="4" class="text-center text-muted py-4">Chưa có tài khoản nào trong hệ thống.</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </main>

        <!-- Footer -->
        <jsp:include page="/layout/footer.jsp" />
    </div>
</div>