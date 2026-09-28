<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/layout/head.jsp">
    <jsp:param name="title" value="Danh mục Môn học" />
</jsp:include>

<div class="d-flex min-vh-100">

    <jsp:include page="/layout/sidebar.jsp">
        <jsp:param name="active" value="monhoc" />
    </jsp:include>

    <div class="main-content flex-grow-1 d-flex flex-column">

        <jsp:include page="/layout/navbar.jsp">
            <jsp:param name="pageTitle" value="Quản lý Môn học" />
        </jsp:include>

        <main class="p-4 flex-grow-1">
            <div id="tab-monhoc" class="tab-content-section active bg-white p-4 rounded-3 shadow-sm">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h5 class="fw-bold text-dark mb-0">Danh mục Môn học</h5>
                    <div class="d-flex gap-2">

                        <form action="${pageContext.request.contextPath}/monhoc"
                              method="get"
                              class="input-group custom-search-group"
                              style="max-width: 300px;">

                            <input type="hidden" name="action" value="search">

                            <input type="text"
                                   name="keyword"
                                   class="form-control search-input"
                                   placeholder="Tìm tên hoặc mã..."
                                   value="${keyword}">

                            <button class="btn btn-search" type="submit">
                                <i class="fa-solid fa-magnifying-glass"></i>
                            </button>

                        </form>

                        <a href="${pageContext.request.contextPath}/monhoc?action=list"
                           class="btn btn-reload"
                           title="Xem toàn bộ môn học">
                            <i class="fa-solid fa-rotate-left"></i>
                        </a>

                    </div>
                    <c:if test="${sessionScope.currentUser.chucVu != 'GIAOVIEN'}">
                        <a href="${pageContext.request.contextPath}/monhoc?action=add" class="btn btn-gradient btn-primary">
                            <i class="fa-solid fa-plus me-2"></i>Thêm Môn học
                        </a
                    </c:if>

                    >
                </div>

                <div class="table-responsive table-custom">
                    <c:if test="${param.error == 'has_data'}">
                        <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                            <i class="fa-solid fa-triangle-exclamation me-2"></i>
                            <strong>Không thể xóa!</strong> Môn học này đã có kết quả học tập (điểm) của sinh viên.
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>

                    <c:if test="${param.message == 'deleted'}">
                        <div class="alert alert-success alert-dismissible fade show mb-3" role="alert">
                            <i class="fa-solid fa-circle-check me-2"></i>
                            Xóa môn học thành công!
                            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                        </div>
                    </c:if>
                    <c:if test="${param.message == 'added'}">
                        <div class="alert alert-success">Thêm mới môn học thành công!</div>
                    </c:if>
                    <c:if test="${param.message == 'updated'}">
                        <div class="alert alert-success">Cập nhật môn học thành công!</div>
                    </c:if>
                    <c:if test="${param.message == 'deleted'}">
                        <div class="alert alert-success">Xóa môn học thành công!</div>
                    </c:if>
                    <!-- Thêm id="tableMonHoc" cho thẻ table để phục vụ tìm kiếm -->
                    <table class="table table-hover align-middle mb-0" id="tableMonHoc">
                        <thead>
                            <tr>
                                <th>Mã Môn Học</th>
                                <th>Tên Môn Học</th>
                                <th>Số Tín Chỉ</th>
                                <th class="text-center">Thao tác</th>
                            </tr>
                        </thead>
                        <tbody>

                            <c:forEach items="${listMH}" var="mh">
                                <tr>
                                    <td>
                                        <span class="badge badge-soft-primary px-3 py-2 rounded-pill fw-bold">
                                            ${mh.maMH} 
                                        </span>
                                    </td>
                                    <td class="fw-semibold">${mh.tenMH}</td>
                                    <td>
                                        <span class="badge badge-soft-info px-3 py-1 rounded-pill">
                                            ${mh.soTinChi} Tín chỉ
                                        </span>
                                    </td>
                                    <td class="text-center">
                                        <!-- Nút Sửa -->
                                        <c:if test="${sessionScope.currentUser.chucVu != 'GIAOVIEN'}">
                                            <a href="${pageContext.request.contextPath}/monhoc?action=edit&id=${mh.maMH}" 
                                               class="btn btn-sm btn-light text-warning me-1" title="Sửa">
                                                <i class="fa-solid fa-pen"></i>
                                            </a>

                                        </c:if>
                                        
                                        <!-- Nút Xóa -->
                                        <c:if test="${sessionScope.currentUser.chucVu == 'ADMIN'}">
                                            <a href="${pageContext.request.contextPath}/monhoc?action=delete&id=${mh.maMH}" 
                                               class="btn btn-sm btn-light text-danger" 
                                               title="Xóa"
                                               onclick="return confirm('Bạn có chắc chắn muốn xóa môn học [${mh.tenMH}] không?');">
                                                <i class="fa-solid fa-trash"></i>
                                            </a>
                                        </c:if>

                                    </td>
                                </tr>
                            </c:forEach>

                            <c:if test="${empty listMH}">
                                <tr>
                                    <td colspan="4" class="text-center text-muted py-4">
                                        Chưa có dữ liệu môn học nào trong CSDL!
                                    </td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>

        <jsp:include page="/layout/footer.jsp" />
    </div>
</div>