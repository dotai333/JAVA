<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="tab-dashboard" class="tab-content-section active">
    <!-- HÀNG THỐNG KÊ 4 CARD -->
    <div class="row g-3 mb-4">
        <!-- 1. Tổng Khoa -->
        <div class="col-12 col-sm-6 col-xl-3">
            <div class="card border-0 shadow-sm rounded-4 p-3 style-card-primary">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <p class="text-muted mb-1 fw-semibold">Tổng Số Khoa</p>
                        <h2 class="fw-bold text-dark mb-0"><div class="number">${totalKhoa}</div></h2>
                    </div>
                    <div class="stat-icon bg-primary-subtle text-primary p-3 rounded-4">
                        <i class="fa-solid fa-graduation-cap fa-2x"></i>
                    </div>
                </div>
            </div>
        </div>

        <!-- 2. Tổng Sinh Viên -->
        <div class="col-12 col-sm-6 col-xl-3">
            <div class="card border-0 shadow-sm rounded-4 p-3 style-card-success">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <p class="text-muted mb-1 fw-semibold">Tổng Sinh Viên</p>
                        <h2 class="fw-bold text-dark mb-0"><div class="number">${totalSV}</div></h2>
                    </div>
                    <div class="stat-icon bg-success-subtle text-success p-3 rounded-4">
                        <i class="fa-solid fa-users fa-2x"></i>
                    </div>
                </div>
            </div>
        </div>

        <!-- 3. Tổng Môn Học (Trong hình của bạn) -->
        <div class="col-12 col-sm-6 col-xl-3">
            <div class="card border-0 shadow-sm rounded-4 p-3 style-card-warning">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <p class="text-muted mb-1 fw-semibold">Tổng Môn học</p>
                        <h2 class="fw-bold text-dark mb-0"><div class="number">${totalMH}</div></h2>
                    </div>
                    <div class="stat-icon bg-warning-subtle text-warning p-3 rounded-4">
                        <i class="fa-solid fa-book-open fa-2x"></i>
                    </div>
                </div>
            </div>
        </div>

        <!-- 4. Lượt Đăng Ký Môn (Trong hình của bạn) -->
        <div class="col-12 col-sm-6 col-xl-3">
            <div class="card border-0 shadow-sm rounded-4 p-3 style-card-info">
                <div class="d-flex justify-content-between align-items-center">
                    <div>
                        <p class="text-muted mb-1 fw-semibold">Lượt đăng ký môn</p>
                        <h2 class="fw-bold text-dark mb-0"><div class="number">${totalDangKy}</div></h2>
                    </div>
                    <div class="stat-icon bg-info-subtle text-info p-3 rounded-4">
                        <i class="fa-solid fa-pen-to-square fa-2x"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- NỘI DUNG XEM NHANH HOẶC BIỂU ĐỒ BÊN DƯỚI -->
    <div class="card border-0 shadow-sm rounded-4 p-4">
        <h5 class="fw-bold mb-3">Hoạt động gần đây</h5>
        <p class="text-muted mb-0">Chào mừng bạn đến với Hệ thống Quản lý Giáo vụ. Chọn các chức năng ở menu bên trái để bắt đầu thao tác.</p>
    </div>
</div>