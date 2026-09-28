<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/layout/head.jsp">
    <jsp:param name="title" value="${mh != null ? 'Cập nhật Môn học' : 'Thêm Môn học mới'}" />
</jsp:include>

<div class="d-flex min-vh-100">

    <!-- Sidebar -->
    <jsp:include page="/layout/sidebar.jsp">
        <jsp:param name="active" value="monhoc" />
    </jsp:include>

    <div class="main-content flex-grow-1 d-flex flex-column">

        <!-- Navbar -->
        <jsp:include page="/layout/navbar.jsp">
            <jsp:param name="pageTitle" value="${mh != null ? 'Cập nhật Môn học' : 'Thêm Môn học mới'}" />
        </jsp:include>

        <main class="p-4 flex-grow-1">
            <div class="bg-white p-4 rounded-3 shadow-sm" style="max-width: 650px; margin: 0 auto;">

                <!-- Tiêu đề Form -->
                <div class="d-flex justify-content-between align-items-center mb-4 border-bottom pb-3">
                    <h5 class="fw-bold text-dark mb-0">
                        <i class="fa-solid ${mh != null ? 'fa-pen-to-square' : 'fa-plus-circle'} me-2 text-primary"></i>
                        ${mh != null ? "CẬP NHẬT MÔN HỌC" : "THÊM MÔN HỌC MỚI"}
                    </h5>
                    <a href="${pageContext.request.contextPath}/monhoc?action=list" class="btn btn-outline-secondary btn-sm">
                        <i class="fa-solid fa-arrow-left me-1"></i> Quay lại
                    </a>
                </div>
                <c:if test="${param.error == 'duplicate_name'}">
                    <div class="alert alert-danger" role="alert">
                        <i class="fa-solid fa-triangle-exclamation"></i> Tên môn học này đã tồn tại! Vui lòng nhập tên khác.
                    </div>
                </c:if>
                <c:if test="${param.error == 'empty'}">
                    <div class="alert alert-danger">Vui lòng điền đầy đủ thông tin các trường bắt buộc!</div>
                </c:if>
                <c:if test="${param.error == 'duplicate'}">
                    <div class="alert alert-danger">Mã môn học này đã tồn tại trong hệ thống!</div>
                </c:if>
                <c:if test="${param.error == 'invalid_tc'}">
                    <div class="alert alert-danger">Số tín chỉ phải là một số nguyên lớn hơn 0!</div>
                </c:if>
                <!-- Form nhập liệu -->
                <form action="${pageContext.request.contextPath}/monhoc" method="post">

                    <!-- Phân biệt Thêm (insert) hay Sửa (update) -->
                    <input type="hidden" name="action" value="${mh != null ? 'update' : 'insert'}">

                    <!-- Ô 1: Mã môn học -->
                    <div class="mb-3">
                        <label for="maMH" class="form-label fw-semibold">Mã Môn Học <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="maMH" name="maMH" 
                               value="${mh.maMH}" placeholder="Nhập mã môn (ví dụ: MH001)..."
                               ${mh != null ? 'readonly' : 'required'}>
                    </div>

                    <!-- Ô 2: Tên môn học -->
                    <div class="mb-3">
                        <label for="tenMH" class="form-label fw-semibold">Tên Môn Học <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" id="tenMH" name="tenMH" 
                               value="${mh.tenMH}" placeholder="Nhập tên môn học..." required>
                    </div>

                    <!-- Ô 3: Số tín chỉ -->
                    <div class="mb-3">
                        <label for="soTinChi" class="form-label fw-semibold">Số Tín Chỉ <span class="text-danger">*</span></label>
                        <input type="number" class="form-control" id="soTinChi" name="soTinChi" 
                               value="${mh.soTinChi}" placeholder="Nhập số tín chỉ..." required min="1">
                    </div>

                    <!-- Nút bấm Thao tác -->
                    <div class="d-flex justify-content-end gap-2 mt-4 pt-3 border-top">
                        <a href="${pageContext.request.contextPath}/monhoc?action=list" class="btn btn-light border">Hủy</a>
                        <button type="submit" class="btn btn-primary px-4">
                            <i class="fa-solid fa-floppy-disk me-1"></i>
                            ${mh != null ? "Cập nhật" : "Lưu lại"}
                        </button>
                    </div>

                </form>

            </div>
        </main>

        <!-- Footer -->
        <jsp:include page="/layout/footer.jsp" />
    </div>
</div>