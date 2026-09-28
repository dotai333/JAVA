<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="/layout/head.jsp">
    <jsp:param name="title"
               value="${kh != null ? 'Cập nhật Khoa' : 'Thêm Khoa mới'}" />
</jsp:include>

<div class="d-flex min-vh-100">

    <jsp:include page="/layout/sidebar.jsp">
        <jsp:param name="active" value="khoa" />
    </jsp:include>

    <div class="main-content flex-grow-1 d-flex flex-column">

        <jsp:include page="/layout/navbar.jsp">
            <jsp:param name="pageTitle"
                       value="${kh != null ? 'Cập nhật Khoa' : 'Thêm Khoa mới'}" />
        </jsp:include>

        <main class="p-4 flex-grow-1">

            <div class="bg-white p-4 rounded-3 shadow-sm"
                 style="max-width: 650px; margin: 0 auto;">

                <div class="d-flex justify-content-between align-items-center mb-4 border-bottom pb-3">

                    <h5 class="fw-bold text-dark mb-0">

                        <i class="fa-solid ${kh != null ? 'fa-pen-to-square' : 'fa-plus-circle'} me-2 text-primary"></i>

                        ${kh != null ? "CẬP NHẬT KHOA" : "THÊM KHOA MỚI"}

                    </h5>

                    <a href="${pageContext.request.contextPath}/khoa?action=list"
                       class="btn btn-outline-secondary btn-sm">

                        <i class="fa-solid fa-arrow-left me-1"></i>
                        Quay lại

                    </a>

                </div>
                <!-- Thông báo lỗi để trống -->
                <c:if test="${param.error == 'empty'}">
                    <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                        <i class="fa-solid fa-triangle-exclamation me-2"></i>
                        Vui lòng không để trống các trường dữ liệu!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>

                <!-- Thông báo lỗi trùng mã khoa -->
                <c:if test="${param.error == 'duplicate'}">
                    <div class="alert alert-danger alert-dismissible fade show mb-3" role="alert">
                        <i class="fa-solid fa-triangle-exclamation me-2"></i>
                        <strong>Thất bại!</strong> Mã khoa này đã tồn tại trong hệ thống, vui lòng nhập mã khác!
                        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                    </div>
                </c:if>
                <c:if test="${param.error == 'duplicate_name'}">
                    <div class="alert alert-danger" role="alert">
                        <i class="fa-solid fa-triangle-exclamation"></i> Tên khoa này đã tồn tại trong hệ thống! Vui lòng nhập tên khác.
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/khoa"
                      method="post">

                    <input type="hidden"
                           name="action"
                           value="${kh != null ? 'update' : 'insert'}">

                    <div class="mb-3">

                        <label for="maKhoa"
                               class="form-label fw-semibold">

                            Mã Khoa
                            <span class="text-danger">*</span>

                        </label>

                        <input type="text"
                               class="form-control"
                               id="maKhoa"
                               name="maKhoa"
                               value="${kh.maKhoa}"
                               placeholder="Nhập mã khoa..."
                               ${kh != null ? 'readonly' : 'required'}>

                    </div>

                    <div class="mb-3">

                        <label for="tenKhoa"
                               class="form-label fw-semibold">

                            Tên Khoa
                            <span class="text-danger">*</span>

                        </label>

                        <input type="text"
                               class="form-control"
                               id="tenKhoa"
                               name="tenKhoa"
                               value="${kh.tenKhoa}"
                               placeholder="Nhập tên khoa..."
                               required>

                    </div>

                    <div class="d-flex justify-content-end gap-2 mt-4 pt-3 border-top">

                        <a href="${pageContext.request.contextPath}/khoa?action=list"
                           class="btn btn-light border">

                            Hủy

                        </a>

                        <button type="submit"
                                class="btn btn-primary px-4">

                            <i class="fa-solid fa-floppy-disk me-1"></i>

                            ${kh != null ? "Cập nhật" : "Lưu lại"}

                        </button>

                    </div>

                </form>

            </div>

        </main>

        <jsp:include page="/layout/footer.jsp" />

    </div>

</div>