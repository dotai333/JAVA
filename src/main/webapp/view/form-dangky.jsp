<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/layout/head.jsp"><jsp:param name="title" value="${isEdit ? 'Cập nhật Điểm Đăng ký' : 'Đăng ký Môn học'}" /></jsp:include>

    <div class="d-flex min-vh-100">
    <jsp:include page="/layout/sidebar.jsp"><jsp:param name="active" value="dangky" /></jsp:include>
        <div class="main-content flex-grow-1 d-flex flex-column">
        <jsp:include page="/layout/navbar.jsp"><jsp:param name="pageTitle" value="${isEdit ? 'Cập nhật Điểm Đăng ký' : 'Đăng ký Môn học'}" /></jsp:include>

            <main class="p-4 flex-grow-1">
                <div class="bg-white p-4 rounded-3 shadow-sm" style="max-width: 650px; margin: 0 auto;">
                    <h5 class="fw-bold text-dark mb-4 border-bottom pb-2">
                    ${isEdit ? "Cập nhật điểm học phần" : "Thêm mới đăng ký học phần"}
                </h5>
                <c:if test="${param.error == 'duplicate'}">
                    <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                        <i class="fa-solid fa-triangle-exclamation me-2"></i>
                        <strong>Thất bại!</strong> Sinh viên này đã đăng ký môn học này từ trước rồi, không được đăng ký trùng!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/dangky" method="post">
                    <input type="hidden" name="action" value="${isEdit ? 'update' : 'insert'}">

                    <!-- Chọn Sinh Viên -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Sinh Viên <span class="text-danger">*</span></label>
                        <c:choose>
                            <c:when test="${isEdit}">
                                <input type="text" class="form-control bg-light" value="${dk.hoTenSV} (${dk.maSV})" readonly>
                                <input type="hidden" name="maSV" value="${dk.maSV}">
                            </c:when>
                            <c:otherwise>
                                <select class="form-select" name="maSV" required>
                                    <option value="">-- Chọn sinh viên --</option>
                                    <c:forEach items="${dsSinhVien}" var="sv">
                                        <option value="${sv.maSV}">${sv.maSV} - ${sv.hoTen}</option>
                                    </c:forEach>
                                </select>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <!-- Chọn Môn Học -->
                    <div class="mb-3">
                        <label class="form-label fw-semibold">Môn Học <span class="text-danger">*</span></label>
                        <c:choose>
                            <c:when test="${isEdit}">
                                <input type="text" class="form-control bg-light" value="${dk.tenMH} (${dk.maMH})" readonly>
                                <input type="hidden" name="maMH" value="${dk.maMH}">
                            </c:when>
                            <c:otherwise>
                                <select class="form-select" name="maMH" required>
                                    <option value="">-- Chọn môn học --</option>
                                    <c:forEach items="${dsMonHoc}" var="mh">
                                        <option value="${mh.maMH}">${mh.maMH} - ${mh.tenMH}</option>
                                    </c:forEach>
                                </select>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <!-- Nhập điểm (Chỉ hiện khi Sửa điểm) -->
                    <c:if test="${isEdit}">
                        <!-- ĐIỂM QUÁ TRÌNH (40%) -->
                        <div class="mb-3">
                            <label class="form-label">Điểm Quá Trình (40%):</label>
                            <input type="number" step="0.1" min="0" max="10" class="form-control" id="diemQT" name="diemQT" value="${dk.diemQT}">
                        </div>

                        <!-- ĐIỂM THI (60%) -->
                        <div class="mb-3">
                            <label class="form-label">Điểm Thi (60%):</label>
                            <input type="number" step="0.1" min="0" max="10" class="form-control" id="diemThi" name="diemThi" value="${dk.diemThi}">
                        </div>

                        <!-- ĐIỂM TỔNG KẾT (Tự động tính và chỉ đọc) -->
                        <div class="mb-3">
                            <label class="form-label fw-bold text-primary">Điểm Tổng Kết (Tự động):</label>
                            <input type="number" step="0.01" class="form-control fw-bold text-primary bg-light" id="diemTK" name="diemTK" value="${dk.diemTK}" readonly>
                        </div>
                    </c:if>

                    <div class="d-flex justify-content-end gap-2 mt-4">
                        <a href="${pageContext.request.contextPath}/dangky?action=list" class="btn btn-secondary">Hủy</a>
                        <button type="submit" class="btn btn-primary px-4">Lưu lại</button>
                    </div>
                </form>
            </div>
        </main>
        <jsp:include page="/layout/footer.jsp" />
    </div>
</div>