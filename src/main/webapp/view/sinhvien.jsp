<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:include page="/layout/head.jsp">
    <jsp:param name="title" value="Quản lý Sinh viên" />
</jsp:include>

<!-- CSS TÙY CHỈNH CHO PHÂN TRANG HIỆN ĐẠI -->
<style>
    .pagination-custom .page-link {
        border: none;
        margin: 0 3px;
        color: #495057;
        font-weight: 500;
        border-radius: 8px !important;
        padding: 0.4rem 0.85rem;
        transition: all 0.25s ease-in-out;
        box-shadow: 0 2px 4px rgba(0,0,0,0.02);
    }

    .pagination-custom .page-link:hover {
        background-color: #e9ecef;
        color: #0d6efd;
        transform: translateY(-2px);
        box-shadow: 0 4px 8px rgba(0,0,0,0.08);
    }

    .pagination-custom .page-item.active .page-link {
        background: linear-gradient(135deg, #0d6efd 0%, #0b5ed7 100%);
        color: #fff;
        font-weight: 600;
        box-shadow: 0 4px 10px rgba(13, 110, 253, 0.35);
    }

    .pagination-custom .page-item.disabled .page-link {
        background-color: #f8f9fa;
        color: #adb5bd;
        opacity: 0.7;
    }

    .pagination-custom .page-item:first-child .page-link,
    .pagination-custom .page-item:last-child .page-link {
        border-radius: 20px !important; /* Bo tròn mềm mại cho nút Trước / Sau */
        padding-left: 0.9rem;
        padding-right: 0.9rem;
    }

    .pagination-container-custom {
        display: flex;
        flex-direction: column;
        align-items: center; /* Căn giữa toàn bộ thanh phân trang */
        justify-content: center;
        gap: 12px;
    }

    .pagination-custom .page-link {
        border: none;
        margin: 0 3px;
        color: #4b5563;
        font-weight: 500;
        border-radius: 10px !important;
        padding: 0.45rem 0.85rem;
        transition: all 0.25s ease-in-out;
        background-color: #f8fafc;
    }

    .pagination-custom .page-link:hover {
        background-color: #e2e8f0;
        color: #6366f1;
        transform: translateY(-2px);
    }

    /* Gradient Tím - Xanh Dương khớp màu giao diện */
    .pagination-custom .page-item.active .page-link {
        background: linear-gradient(135deg, #6366f1 0%, #4f46e5 50%, #3b82f6 100%) !important;
        color: #ffffff !important;
        font-weight: 600;
        box-shadow: 0 4px 12px rgba(99, 102, 241, 0.35);
    }

    .pagination-custom .page-item.disabled .page-link {
        background-color: #f1f5f9;
        color: #cbd5e1;
    }
</style>

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
                                   value="<c:out value='${keyword}'/>">

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

                <!-- THANH PHÂN TRANG CĂN GIỮA & GRADIENT TÍM XANH -->
                <c:if test="${not empty totalPages && totalPages > 0}">
                    <div class="pagination-container-custom mt-4 pt-3 border-top">

                        <!-- Các nút bấm phân trang căn giữa -->
                        <nav aria-label="Page navigation">
                            <ul class="pagination pagination-sm pagination-custom m-0 align-items-center">
                                <!-- Nút Đầu -->
                                <li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/sinhvien?page=1&keyword=${keyword}" title="Trang đầu">
                                        <i class="fa-solid fa-angles-left"></i>
                                    </a>
                                </li>

                                <!-- Nút Trước -->
                                <li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/sinhvien?page=${currentPage - 1}&keyword=${keyword}">
                                        <i class="fa-solid fa-chevron-left me-1"></i>Trước
                                    </a>
                                </li>

                                <!-- Các số trang -->
                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <c:choose>
                                        <c:when test="${i == currentPage}">
                                            <li class="page-item active">
                                                <span class="page-link">${i}</span>
                                            </li>
                                        </c:when>
                                        <c:when test="${i >= currentPage - 2 && i <= currentPage + 2}">
                                            <li class="page-item">
                                                <a class="page-link" href="${pageContext.request.contextPath}/sinhvien?page=${i}&keyword=${keyword}">${i}</a>
                                            </li>
                                        </c:when>
                                        <c:when test="${i == 1 || i == totalPages}">
                                            <li class="page-item">
                                                <a class="page-link" href="${pageContext.request.contextPath}/sinhvien?page=${i}&keyword=${keyword}">${i}</a>
                                            </li>
                                        </c:when>
                                        <c:when test="${i == currentPage - 3 || i == currentPage + 3}">
                                            <li class="page-item disabled"><span class="page-link">...</span></li>
                                            </c:when>
                                        </c:choose>
                                    </c:forEach>

                                <!-- Nút Sau -->
                                <li class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/sinhvien?page=${currentPage + 1}&keyword=${keyword}">
                                        Sau<i class="fa-solid fa-chevron-right ms-1"></i>
                                    </a>
                                </li>

                                <!-- Nút Cuối -->
                                <li class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
                                    <a class="page-link" href="${pageContext.request.contextPath}/sinhvien?page=${totalPages}&keyword=${keyword}" title="Trang cuối">
                                        <i class="fa-solid fa-angles-right"></i>
                                    </a>
                                </li>
                            </ul>
                        </nav>

                        <!-- Dòng thông báo số trang căn giữa -->
                        <div class="text-secondary small fw-medium text-center">
                            <i class="fa-solid fa-list-check me-1" style="color: #6366f1;"></i> Hiển thị trang 
                            <span class="badge bg-light text-dark border px-2 py-1 mx-1">${currentPage}</span> / 
                            <span class="badge bg-light text-dark border px-2 py-1 mx-1">${totalPages}</span> 
                            (Tổng <strong style="color: #4f46e5;">${totalRecords}</strong> sinh viên)
                        </div>

                    </div>
                </c:if>

            </div>
        </main>

        <jsp:include page="/layout/footer.jsp" />
    </div>
</div>