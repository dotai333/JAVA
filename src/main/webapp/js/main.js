function showTab(tabId, element) {
    const links = document.querySelectorAll('#sidebar ul li');
    links.forEach(li => li.classList.remove('active'));
    if (element) {
        element.parentElement.classList.add('active');
    }

    const tabs = document.querySelectorAll('.tab-content-section');
    tabs.forEach(tab => tab.classList.remove('active'));

    const activeTab = document.getElementById('tab-' + tabId);
    if (activeTab) {
        activeTab.classList.add('active');
    }

    const titles = {
        'dashboard': '<i class="fa-solid fa-chart-pie text-primary me-2"></i>Tổng quan hệ thống',
        'khoa': '<i class="fa-solid fa-sitemap text-primary me-2"></i>Quản lý Khoa',
        'sinhvien': '<i class="fa-solid fa-user-graduate text-primary me-2"></i>Quản lý Sinh viên',
        'monhoc': '<i class="fa-solid fa-book-open text-primary me-2"></i>Quản lý Môn học',
        'dangky': '<i class="fa-solid fa-pen-to-square text-primary me-2"></i>Quản lý Đăng ký & Điểm'
    };

    const titleElem = document.getElementById('page-title');
    if (titleElem && titles[tabId]) {
        titleElem.innerHTML = titles[tabId];
    }
}

// 2. Hàm tìm kiếm trong Bảng
function searchTable(inputId, tableId) {
    var input = document.getElementById(inputId);
    if (!input) return;
    
    var filter = input.value.toLowerCase().trim();
    var table = document.getElementById(tableId);
    if (!table) return;

    var tbody = table.getElementsByTagName("tbody")[0];
    if (!tbody) return;

    var rows = tbody.getElementsByTagName("tr");

    for (var i = 0; i < rows.length; i++) {
        var cells = rows[i].getElementsByTagName("td");
        if (cells.length >= 2) {
            var maMH = cells[0].textContent.toLowerCase();
            var tenMH = cells[1].textContent.toLowerCase();

            if (maMH.indexOf(filter) > -1 || tenMH.indexOf(filter) > -1) {
                rows[i].style.display = "";
            } else {
                rows[i].style.display = "none";
            }
        }
    }
}

// 3. TỰ ĐỘNG CHẠY KHI TẢI TRANG (Sửa lỗi mất màu active & lỗi lồng hàm)
document.addEventListener("DOMContentLoaded", function () {
    
    // --- LẮNG NGHE URL ĐỂ SÁNG ĐÚNG MENU SIDEBAR ---
    const currentUrl = window.location.href; // VD: .../sinhvien?action=list
    const sidebarLinks = document.querySelectorAll('#sidebar ul li a');

    sidebarLinks.forEach(link => {
        const href = link.getAttribute('href');
        
        // So sánh URL trình duyệt với đường dẫn href của từng nút menu
        if (href && href !== '#' && currentUrl.includes(href)) {
            // Xóa active hiện tại (thường bị dính ở Dashboard)
            document.querySelectorAll('#sidebar ul li').forEach(li => li.classList.remove('active'));
            
            // Thêm active vào thẻ li của nút vừa bấm
            link.parentElement.classList.add('active');
        }
    });

    // --- TÍNH ĐIỂM TỔNG KẾT ---
    const inputQT = document.getElementById('diemQT');
    const inputThi = document.getElementById('diemThi');
    const inputTK = document.getElementById('diemTK');

    function tinhDiemTongKet() {
        let qt = parseFloat(inputQT.value) || 0;
        let thi = parseFloat(inputThi.value) || 0;
        let tk = (qt * 0.4) + (thi * 0.6);
        inputTK.value = tk.toFixed(2);
    }

    if (inputQT && inputThi && inputTK) {
        inputQT.addEventListener('input', tinhDiemTongKet);
        inputThi.addEventListener('input', tinhDiemTongKet);

        if (inputQT.value !== '' || inputThi.value !== '') {
            tinhDiemTongKet();
        }
    }
});