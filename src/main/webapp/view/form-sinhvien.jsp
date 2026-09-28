<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<jsp:include page="/layout/head.jsp">
    <jsp:param name="title" value="${isEdit ? 'Cập nhật Sinh viên' : 'Thêm mới Sinh viên'}" />
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
            <div class="bg-white p-4 rounded-3 shadow-sm" style="max-width: 700px; margin: 0 auto;">
                
                <h5 class="fw-bold text-dark mb-4">
                    <i class="fa-solid ${isEdit ? 'fa-pen-to-square' : 'fa-user-plus'} me-2 text-primary"></i>
                    ${isEdit ? 'Cập nhật thông tin Sinh viên' : 'Thêm mới Sinh viên'}
                </h5>

                <!-- THÔNG BÁO LỖI -->
                <c:if test="${param.error == 'empty'}">
                    <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                        <i class="fa-solid fa-triangle-exclamation me-2"></i> Vui lòng điền đầy đủ thông tin bắt buộc!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <c:if test="${param.error == 'duplicate'}">
                    <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                        <i class="fa-solid fa-triangle-exclamation me-2"></i> <strong>Thất bại!</strong> Mã sinh viên này đã tồn tại trong hệ thống!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <!-- FORM THÊM / SỬA -->
                <form action="${pageContext.request.contextPath}/sinhvien" method="POST">
                    
                    <!-- Phân biệt hành động insert hay update -->
                    <input type="hidden" name="action" value="${isEdit ? 'update' : 'insert'}">

                    <!-- Mã Sinh Viên -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Mã Sinh Viên <span class="text-danger">*</span></label>
                        <c:choose>
                            <c:when test="${isEdit}">
                                <input type="text" class="form-control bg-light" value="${sv.maSV}" readonly>
                                <input type="hidden" name="maSV" value="${sv.maSV}">
                            </c:when>
                            <c:otherwise>
                                <input type="text" class="form-control" name="maSV" required placeholder="Nhập mã sinh viên (VD: SV01)...">
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <!-- Họ Tên -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Họ Tên <span class="text-danger">*</span></label>
                        <input type="text" class="form-control" name="hoTen" value="${sv.hoTen}" required placeholder="Nhập họ và tên...">
                    </div>

                    <!-- Ngày Sinh -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Ngày Sinh:</label>
                        <fmt:formatDate value="${sv.ngaySinh}" pattern="yyyy-MM-dd" var="formattedDate"/>
                        <input type="date" class="form-control" name="ngaySinh" value="${formattedDate}">
                    </div>

                    <!-- Giới Tính -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Giới Tính:</label>
                        <select name="gioiTinh" class="form-select">
                            <option value="true" ${(!isEdit || sv.gioiTinh) ? 'selected' : ''}>Nam</option>
                            <option value="false" ${isEdit && !sv.gioiTinh ? 'selected' : ''}>Nữ</option>
                        </select>
                    </div>

                    <!-- Địa Chỉ -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Địa Chỉ:</label>
                        <input type="text" class="form-control" name="diaChi" value="${sv.diaChi}" placeholder="Nhập địa chỉ...">
                    </div>

                    <!-- Khoa -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Khoa <span class="text-danger">*</span></label>
                        <select name="maKhoa" class="form-select" required>
                            <option value="">-- Chọn Khoa --</option>
                            <c:forEach items="${dsKhoa}" var="k">
                                <option value="${k.maKhoa}" ${sv.maKhoa == k.maKhoa ? 'selected' : ''}>
                                    ${k.maKhoa} - ${k.tenKhoa}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- Nút thao tác -->
                    <div class="d-flex justify-content-between mt-4">
                        <a href="${pageContext.request.contextPath}/sinhvien?action=list" class="btn btn-secondary px-4">
                            <i class="fa-solid fa-arrow-left me-2"></i>Quay lại
                        </a>
                        <button type="submit" class="btn btn-primary px-4">
                            <i class="fa-solid fa-floppy-disk me-2"></i>Lưu lại
                        </button>
                    </div>

                </form>
            </div>
        </main>

        <jsp:include page="/layout/footer.jsp" />
    </div>
</div>