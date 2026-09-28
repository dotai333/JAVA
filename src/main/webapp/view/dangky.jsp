<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:include page="/layout/head.jsp"><jsp:param name="title" value="Quản lý Đăng ký Học phần" /></jsp:include>

    <div class="d-flex min-vh-100">
    <jsp:include page="/layout/sidebar.jsp"><jsp:param name="active" value="dangky" /></jsp:include>
        <div class="main-content flex-grow-1 d-flex flex-column">
        <jsp:include page="/layout/navbar.jsp"><jsp:param name="pageTitle" value="Quản lý Đăng ký & Bảng điểm" /></jsp:include>

            <main class="p-4 flex-grow-1">
                <div class="bg-white p-4 rounded-3 shadow-sm">
                    <div class="d-flex justify-content-between align-items-center mb-4">
                        <h5 class="fw-bold text-dark mb-0">Danh sách Đăng ký Học phần</h5>
                        <div class="d-flex justify-content-between align-items-center mb-3">

                            <!-- CỤM BÊN PHẢI -->
                            <div class="d-flex align-items-center gap-2">

                                <!-- SEARCH -->
                                <form action="${pageContext.request.contextPath}/dangky"
                                      method="get"
                                      class="input-group custom-search-group">

                                    <input type="hidden"
                                           name="action"
                                           value="list">

                                    <input type="text"
                                           name="keyword"
                                           class="form-control search-input"
                                           placeholder="Tìm theo Mã SV hoặc Tên..."
                                           value="${keyword}">

                                    <button class="btn btn-search"
                                            type="submit"
                                            title="Tìm kiếm">
                                        <i class="fa-solid fa-magnifying-glass"></i>
                                    </button>

                                </form>

                                <!-- RELOAD -->
                                <a href="${pageContext.request.contextPath}/dangky?action=list"
                                   class="btn btn-reload"
                                   title="Xem toàn bộ danh sách">
                                    <i class="fa-solid fa-rotate-left"></i>
                                </a>

                                <!-- THÊM -->
                                <c:if test="${sessionScope.currentUser.chucVu != 'GIAOVIEN'}">
                                    <a href="${pageContext.request.contextPath}/dangky?action=add"
                                       class="btn btn-gradient btn-add-dangky">
                                        <i class="fa-solid fa-plus me-2"></i>
                                        Đăng ký mới
                                    </a>
                                </c:if>

                            </div>

                        </div>
                    </div>

                    <div class="table-responsive">

                        <c:if test="${param.message == 'added'}">
                            <div class="alert alert-success alert-dismissible fade show mb-3" role="alert">
                                <i class="fa-solid fa-circle-check me-2"></i>
                                Đăng ký học phần thành công!
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>

                        <c:if test="${param.message == 'updated'}">
                            <div class="alert alert-success alert-dismissible fade show mb-3" role="alert">
                                <i class="fa-solid fa-circle-check me-2"></i>
                                Cập nhật điểm thành công!
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>

                        <c:if test="${param.message == 'deleted'}">
                            <div class="alert alert-success alert-dismissible fade show mb-3" role="alert">
                                <i class="fa-solid fa-circle-check me-2"></i>
                                Hủy đăng ký học phần thành công!
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>

                        <c:if test="${param.error == 'fail'}">
                            <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                                <i class="fa-solid fa-triangle-exclamation me-2"></i>
                                Đã có lỗi xảy ra! Không thể thực hiện thao tác.
                                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                            </div>
                        </c:if>

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
                                            <a href="${pageContext.request.contextPath}/dangky?action=edit&maSV=${dk.maSV}&maMH=${dk.maMH}" class="btn btn-sm btn-light text-warning me-1" title="Nhập/Sửa điểm"><i class="fa-solid fa-pen-to-square"></i></a>
                                            <c:if test="${sessionScope.currentUser.chucVu == 'ADMIN'}">
                                                <a href="${pageContext.request.contextPath}/dangky?action=delete&maSV=${dk.maSV}&maMH=${dk.maMH}"
                                                   class="btn btn-sm btn-light text-danger" title="Xóa" onclick="return confirm('Bạn có chắc muốn hủy đăng ký của sinh viên [${dk.hoTenSV}] cho môn [${dk.tenMH}]?');"><i class="fa-solid fa-trash"></i></a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:if test="${empty dsDangKy}">
                                    <tr><td colspan="9" class="text-center text-muted py-4">Chưa có dữ liệu đăng ký học phần nào!</td></tr>
                                </c:if>
                            </tbody>
                        </table>

                        <!-- THANH PHÂN TRANG (HIỂN THỊ KHI CÓ DỮ LIỆU) -->
                        <c:if test="${not empty dsDangKy}">
                            <div class="d-flex justify-content-between align-items-center mt-3 pt-3 border-top">
                                <div class="text-muted small">
                                    Hiển thị từ <strong>${totalRecords == 0 ? 0 : (currentPage - 1) * pageSize + 1}</strong> đến 
                                    <strong>${currentPage * pageSize > totalRecords ? totalRecords : currentPage * pageSize}</strong> 
                                    trong tổng số <strong>${totalRecords}</strong> đăng ký
                                </div>

                                <c:if test="${totalPages > 1}">
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination pagination-sm m-0">
                                            <!-- Nút Trang trước -->
                                            <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                                                <a class="page-link" href="${pageContext.request.contextPath}/dangky?action=list&page=${currentPage - 1}&keyword=${keyword}">
                                                    <i class="fa-solid fa-chevron-left"></i>
                                                </a>
                                            </li>

                                            <!-- Các số trang -->
                                            <c:forEach begin="1" end="${totalPages}" var="i">
                                                <li class="page-item ${currentPage == i ? 'active' : ''}">
                                                    <a class="page-link" href="${pageContext.request.contextPath}/dangky?action=list&page=${i}&keyword=${keyword}">${i}</a>
                                                </li>
                                            </c:forEach>

                                            <!-- Nút Trang sau -->
                                            <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                                                <a class="page-link" href="${pageContext.request.contextPath}/dangky?action=list&page=${currentPage + 1}&keyword=${keyword}">
                                                    <i class="fa-solid fa-chevron-right"></i>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </c:if>
                            </div>
                        </c:if>

                    </div>
                </div>
            </main>
            <jsp:include page="/layout/footer.jsp" />
        </div>
    </div>