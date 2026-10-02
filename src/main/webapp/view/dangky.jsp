<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:include page="/layout/head.jsp"><jsp:param name="title" value="Quản lý Đăng ký Học phần" /></jsp:include>

<!-- CSS BỔ SUNG TRỰC TIẾP CHO GIAO DIỆN PHÂN TRANG -->
<style>
.pagination-container-custom {
    display: flex;
    flex-direction: column;
    align-items: center;
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
    <jsp:include page="/layout/sidebar.jsp"><jsp:param name="active" value="dangky" /></jsp:include>
    
    <div class="main-content flex-grow-1 d-flex flex-column">
        <jsp:include page="/layout/navbar.jsp"><jsp:param name="pageTitle" value="Quản lý Đăng ký & Bảng điểm" /></jsp:include>

        <main class="p-4 flex-grow-1">
            <div class="bg-white p-4 rounded-3 shadow-sm">
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h5 class="fw-bold text-dark mb-0">Danh sách Đăng ký Học phần</h5>
                    
                    <div class="d-flex align-items-center gap-2">
                        <!-- SEARCH FORM -->
                        <form action="${pageContext.request.contextPath}/dangky" method="get" class="input-group custom-search-group">
                            <input type="hidden" name="action" value="list">
                            <input type="text" name="keyword" class="form-control search-input" placeholder="Tìm theo Mã SV hoặc Tên..." value="${keyword}">
                            <button class="btn btn-search" type="submit" title="Tìm kiếm">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>
                        </form>

                        <!-- RELOAD -->
                        <a href="${pageContext.request.contextPath}/dangky?action=list" class="btn btn-reload" title="Xem toàn bộ danh sách">
                            <i class="fa-solid fa-rotate-left"></i>
                        </a>

                        <!-- THÊM MỚI -->
                        <c:if test="${sessionScope.currentUser.chucVu != 'GIAOVIEN'}">
                            <a href="${pageContext.request.contextPath}/dangky?action=add" class="btn btn-gradient btn-add-dangky">
                                <i class="fa-solid fa-plus me-2"></i>Đăng ký mới
                            </a>
                        </c:if>
                    </div>
                </div>

                <div class="table-responsive">
                    <!-- THÔNG BÁO ALERT -->
                    <c:if test="${param.message == 'added'}">
                        <div class="alert alert-success alert-dismissible fade show mb-3" role="alert">
                            <i class="fa-solid fa-circle-check me-2"></i>Đăng ký học phần thành công!
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <c:if test="${param.message == 'updated'}">
                        <div class="alert alert-success alert-dismissible fade show mb-3" role="alert">
                            <i class="fa-solid fa-circle-check me-2"></i>Cập nhật điểm thành công!
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <c:if test="${param.message == 'deleted'}">
                        <div class="alert alert-success alert-dismissible fade show mb-3" role="alert">
                            <i class="fa-solid fa-circle-check me-2"></i>Hủy đăng ký học phần thành công!
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <c:if test="${param.error == 'fail'}">
                        <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                            <i class="fa-solid fa-triangle-exclamation me-2"></i>Đã có lỗi xảy ra! Không thể thực hiện thao tác.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>

                    <!-- BẢNG DỮ LIỆU -->
                    <table class="table table-hover align-middle mb-0">
                        <thead>
                            <tr class="table-light">
                                <th>Mã SV</th>
                                <th>Họ Tên Sinh Viên</th>
                                <th>Mã MH</th>
                                <th>Tên Môn Học</th>
                                <th>Ngày Đăng Ký</th>
                                <th class="text-center">Điểm QT</th>
                                <th class="text-center">Điểm Thi</th>
                                <th class="text-center">Điểm TK</th>
                                <th class="text-center">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${dsDangKy}" var="dk">
                                <tr>
                                    <td><span class="badge bg-danger text-light fw-bold">${dk.maSV}</span></td>
                                    <td class="fw-semibold">${dk.hoTenSV}</td>
                                    <td><span class="badge bg-primary">${dk.maMH}</span></td>
                                    <td>${dk.tenMH}</td>
                                    <td><fmt:formatDate value="${dk.ngayDK}" pattern="dd/MM/yyyy"/></td>
                                    <td class="text-center">${dk.diemQT}</td>
                                    <td class="text-center">${dk.diemThi}</td>
                                    <td class="text-center fw-bold text-primary">${dk.diemTK}</td>
                                    <td class="text-center">
                                        <a href="${pageContext.request.contextPath}/dangky?action=edit&maSV=${dk.maSV}&maMH=${dk.maMH}" class="btn btn-sm btn-light text-warning me-1" title="Nhập/Sửa điểm">
                                            <i class="fa-solid fa-pen-to-square"></i>
                                        </a>
                                        <c:if test="${sessionScope.currentUser.chucVu == 'ADMIN'}">
                                            <a href="${pageContext.request.contextPath}/dangky?action=delete&maSV=${dk.maSV}&maMH=${dk.maMH}" class="btn btn-sm btn-light text-danger" title="Xóa" onclick="return confirm('Bạn có chắc muốn hủy đăng ký của sinh viên [${dk.hoTenSV}] cho môn [${dk.tenMH}]?');">
                                                <i class="fa-solid fa-trash"></i>
                                            </a>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty dsDangKy}">
                                <tr>
                                    <td colspan="9" class="text-center text-muted py-4">Chưa có dữ liệu đăng ký học phần nào!</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>

                    <!-- PHẦN PHÂN TRANG CĂN GIỮA GIỐNG TRANG SINH VIÊN -->
                    <c:if test="${not empty dsDangKy}">
                        <div class="pagination-container-custom mt-4 pt-3 border-top">
                            <c:if test="${totalPages > 1}">
                                <nav aria-label="Page navigation">
                                    <ul class="pagination pagination-sm pagination-custom m-0 align-items-center">
                                        <!-- Trang Đầu -->
                                        <li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">
                                            <a class="page-link" href="${pageContext.request.contextPath}/dangky?action=list&page=1&keyword=${keyword}" title="Trang đầu">
                                                <i class="fa-solid fa-angles-left"></i>
                                            </a>
                                        </li>

                                        <!-- Trước -->
                                        <li class="page-item ${currentPage <= 1 ? 'disabled' : ''}">
                                            <a class="page-link" href="${pageContext.request.contextPath}/dangky?action=list&page=${currentPage - 1}&keyword=${keyword}">
                                                <i class="fa-solid fa-chevron-left me-1"></i>Trước
                                            </a>
                                        </li>

                                        <!-- Các Số Trang -->
                                        <c:forEach begin="1" end="${totalPages}" var="i">
                                            <c:choose>
                                                <c:when test="${i == currentPage}">
                                                    <li class="page-item active">
                                                        <span class="page-link">${i}</span>
                                                    </li>
                                                </c:when>
                                                <c:when test="${i >= currentPage - 2 && i <= currentPage + 2}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="${pageContext.request.contextPath}/dangky?action=list&page=${i}&keyword=${keyword}">${i}</a>
                                                    </li>
                                                </c:when>
                                                <c:when test="${i == 1 || i == totalPages}">
                                                    <li class="page-item">
                                                        <a class="page-link" href="${pageContext.request.contextPath}/dangky?action=list&page=${i}&keyword=${keyword}">${i}</a>
                                                    </li>
                                                </c:when>
                                                <c:when test="${i == currentPage - 3 || i == currentPage + 3}">
                                                    <li class="page-item disabled"><span class="page-link">...</span></li>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>

                                        <!-- Sau -->
                                        <li class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
                                            <a class="page-link" href="${pageContext.request.contextPath}/dangky?action=list&page=${currentPage + 1}&keyword=${keyword}">
                                                Sau<i class="fa-solid fa-chevron-right ms-1"></i>
                                            </a>
                                        </li>

                                        <!-- Trang Cuối -->
                                        <li class="page-item ${currentPage >= totalPages ? 'disabled' : ''}">
                                            <a class="page-link" href="${pageContext.request.contextPath}/dangky?action=list&page=${totalPages}&keyword=${keyword}" title="Trang cuối">
                                                <i class="fa-solid fa-angles-right"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </nav>
                            </c:if>

                            <!-- Thông tin hiển thị số bản ghi -->
                            <div class="text-secondary small fw-medium text-center">
                                <i class="fa-solid fa-list-check me-1" style="color: #6366f1;"></i> 
                                Hiển thị từ <span class="badge bg-light text-dark border px-2 py-1 mx-1">${totalRecords == 0 ? 0 : (currentPage - 1) * pageSize + 1}</span> 
                                đến <span class="badge bg-light text-dark border px-2 py-1 mx-1">${currentPage * pageSize > totalRecords ? totalRecords : currentPage * pageSize}</span> 
                                (Tổng <strong style="color: #4f46e5;">${totalRecords}</strong> đăng ký)
                            </div>
                        </div>
                    </c:if>

                </div>
            </div>
        </main>
        
        <jsp:include page="/layout/footer.jsp" />
    </div>
</div>