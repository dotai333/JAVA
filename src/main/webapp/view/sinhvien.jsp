<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:include page="/layout/head.jsp">
    <jsp:param name="title" value="Quản lý Sinh viên" />
</jsp:include>

<div class="d-flex min-vh-100">

    <jsp:include page="/layout/sidebar.jsp">
        <jsp:param name="active" value="sinhvien" />
    </jsp:include>

    <div class="main-content flex-grow-1 d-flex flex-column">

        <jsp:include page="/layout/navbar.jsp">
            <jsp:param name="pageTitle" value="Quản lý Sinh viên" />
        </jsp:include>

        <main class="p-4 flex-grow-1">
            <div id="tab-sinhvien" class="tab-content-section active bg-white p-4 rounded-3 shadow-sm">
                
                <!-- TIÊU ĐỀ VÀ THANH TÌM KIẾM -->
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5 class="fw-bold text-dark mb-0">Danh mục Sinh viên</h5>
                    <div class="d-flex gap-2">

                        <!-- Form tìm kiếm -->
                        <form action="${pageContext.request.contextPath}/sinhvien"
                              method="get"
                              class="input-group custom-search-group"
                              style="max-width: 300px;">

                            <input type="hidden" name="action" value="search">

                            <input type="text"
                                   name="keyword"
                                   class="form-control search-input"
                                   placeholder="Tìm mã hoặc tên sinh viên..."
                                   value="${keyword}">

                            <button class="btn btn-search" type="submit">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>
                        </form>

                        <!-- Nút làm mới / xem toàn bộ -->
                        <a href="${pageContext.request.contextPath}/sinhvien?action=list"
                           class="btn btn-reload"
                           title="Xem toàn bộ sinh viên">
                            <i class="fa-solid fa-rotate-left"></i>
                        </a>

                        <!-- Nút Thêm sinh viên -->
                        <c:if test="${sessionScope.currentUser.chucVu != 'GIAOVIEN'}">
                            <a href="${pageContext.request.contextPath}/sinhvien?action=add" class="btn btn-gradient btn-primary">
                                <i class="fa-solid fa-plus me-2"></i>Thêm Sinh viên
                            </a>
                        </c:if>

                    </div>
                </div>

                <!-- CÁC THÔNG BÁO TRẠNG THÁI -->
                <c:if test="${param.error == 'has_data'}">
                    <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                        <i class="fa-solid fa-triangle-exclamation me-2"></i>
                        <strong>Không thể xóa!</strong> Sinh viên này đã có điểm hoặc kết quả học tập liên quan.
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <c:if test="${param.message == 'deleted'}">
                    <div class="alert alert-success alert-dismissible fade show mb-3" role="alert">
                        <i class="fa-solid fa-circle-check me-2"></i> Xóa sinh viên thành công!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <c:if test="${param.message == 'added'}">
                    <div class="alert alert-success alert-dismissible fade show mb-3" role="alert">
                        <i class="fa-solid fa-circle-check me-2"></i> Thêm mới sinh viên thành công!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <c:if test="${param.message == 'updated'}">
                    <div class="alert alert-success alert-dismissible fade show mb-3" role="alert">
                        <i class="fa-solid fa-circle-check me-2"></i> Cập nhật thông tin sinh viên thành công!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <!-- BẢNG DANH SÁCH SINH VIÊN -->
                <div class="table-responsive table-custom">
                    <table class="table table-hover align-middle mb-0" id="tableSinhVien">
                        <thead>
                            <tr>
                                <th>Mã Sinh Viên</th>
                                <th>Họ Tên</th>
                                <th>Ngày Sinh</th>
                                <th>Giới Tính</th>
                                <th>Địa Chỉ</th>
                                <th>Mã Khoa</th>
                                <th class="text-center">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${dsSinhVien}" var="sv">
                                <tr>
                                    <td>
                                        <span class="badge badge-soft-primary px-3 py-2 rounded-pill fw-bold">
                                            ${sv.maSV}
                                        </span>
                                    </td>
                                    <td class="fw-semibold">${sv.hoTen}</td>
                                    <td><fmt:formatDate value="${sv.ngaySinh}" pattern="dd/MM/yyyy"/></td>
                                    <td>
                                        <span class="badge ${sv.gioiTinh ? 'badge-soft-success' : 'badge-soft-warning'} px-3 py-1 rounded-pill">
                                            ${sv.gioiTinh ? 'Nam' : 'Nữ'}
                                        </span>
                                    </td>
                                    <td>${sv.diaChi}</td>
                                    <td>
                                        <span class="badge badge-soft-info px-3 py-1 rounded-pill">
                                            ${sv.maKhoa}
                                        </span>
                                    </td>
                                    <td class="text-center">
                                        <!-- Nút Sửa -->
                                        <c:if test="${sessionScope.currentUser.chucVu != 'GIAOVIEN'}">
                                            <a href="${pageContext.request.contextPath}/sinhvien?action=edit&maSV=${sv.maSV}" 
                                               class="btn btn-sm btn-light text-warning me-1" title="Sửa">
                                                <i class="fa-solid fa-pen"></i>
                                            </a>
                                        </c:if>
                                        
                                        <!-- Nút Xóa -->
                                        <c:if test="${sessionScope.currentUser.chucVu == 'ADMIN'}">
                                            <a href="${pageContext.request.contextPath}/sinhvien?action=delete&maSV=${sv.maSV}" 
                                               class="btn btn-sm btn-light text-danger" 
                                               title="Xóa"
                                               onclick="return confirm('Bạn có chắc chắn muốn xóa sinh viên [${sv.hoTen}] không?');">
                                                <i class="fa-solid fa-trash"></i>
                                            </a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>

                            <c:if test="${empty dsSinhVien}">
                                <tr>
                                    <td colspan="7" class="text-center text-muted py-4">
                                        Chưa có dữ liệu sinh viên nào trong cơ sở dữ liệu!
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>

                <!-- THANH PHÂN TRANG (PAGINATION) -->
                <c:if test="${not empty totalPages && totalPages > 0}">
                    <div class="d-flex justify-content-between align-items-center mt-3 pt-3 border-top">
                        <div class="text-muted small">
                            Hiển thị trang <strong>${currentPage}</strong> / <strong>${totalPages}</strong> 
                            (Tổng <strong>${totalRecords}</strong> sinh viên)
                        </div>

                        <nav aria-label="Page navigation">
                            <ul class="pagination pagination-sm m-0">
                                <!-- Nút Trước -->
                                <li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/sinhvien?action=list&page=${currentPage - 1}&keyword=${keyword}">
                                        <i class="fa-solid fa-angle-left me-1"></i>Trước
                                    </a>
                                </li>

                                <!-- Danh sách các trang -->
                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                                        <a class="page-link" href="${pageContext.request.contextPath}/sinhvien?action=list&page=${i}&keyword=${keyword}">${i}</a>
                                    </li>
                                </c:forEach>

                                <!-- Nút Sau -->
                                <li class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/sinhvien?action=list&page=${currentPage + 1}&keyword=${keyword}">
                                        Sau<i class="fa-solid fa-angle-right ms-1"></i>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </c:if>

            </div>
        </main>

        <jsp:include page="/layout/footer.jsp" />
    </div>
</div>