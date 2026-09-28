    function showTab(tabId, element) {
        // Đổi active cho sidebar
        const links = document.querySelectorAll('#sidebar ul li');
        links.forEach(li => li.classList.remove('active'));
        element.parentElement.classList.add('active');

        // Chuyển tab hiển thị
        const tabs = document.querySelectorAll('.tab-content-section');
        tabs.forEach(tab => tab.classList.remove('active'));

        const activeTab = document.getElementById('tab-' + tabId);
        if (activeTab) {
            activeTab.classList.add('active');
        }

        // Cập nhật tiêu đề Navbar
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
 function searchTable(inputId, tableId) {
    var input = document.getElementById(inputId);
    var filter = input.value.toLowerCase().trim();

    var table = document.getElementById(tableId);

    if (!table) {
        return;
    }

    var tbody = table.getElementsByTagName("tbody")[0];

    if (!tbody) {
        return;
    }

    var rows = tbody.getElementsByTagName("tr");

    for (var i = 0; i < rows.length; i++) {

        var cells = rows[i].getElementsByTagName("td");

        if (cells.length >= 2) {

            var maMH = cells[0].textContent.toLowerCase();
            var tenMH = cells[1].textContent.toLowerCase();

            if (maMH.indexOf(filter) > -1 ||
                tenMH.indexOf(filter) > -1) {

                rows[i].style.display = "";

            } else {

                rows[i].style.display = "none";
            }
        }
    }
    const inputQT = document.getElementById('diemQT');
    const inputThi = document.getElementById('diemThi');
    const inputTK = document.getElementById('diemTK');

    function tinhDiemTongKet() {
        // Lấy giá trị, nếu trống hoặc lỗi thì mặc định là 0
        let qt = parseFloat(inputQT.value) || 0;
        let thi = parseFloat(inputThi.value) || 0;
        
        // Công thức: 40% quá trình + 60% thi
        let tk = (qt * 0.4) + (thi * 0.6);
        
        // Gán kết quả vào ô Điểm Tổng Kết và làm tròn 2 chữ số thập phân
        inputTK.value = tk.toFixed(2);
    }

    // Lắng nghe sự kiện khi người dùng gõ vào ô Điểm Quá Trình hoặc Điểm Thi
    if (inputQT && inputThi && inputTK) {
        inputQT.addEventListener('input', tinhDiemTongKet);
        inputThi.addEventListener('input', tinhDiemTongKet);
        
        // Tính sẵn giá trị nếu đây là form Sửa (Edit) đã có sẵn điểm cũ
        if(inputQT.value !== '' || inputThi.value !== '') {
            tinhDiemTongKet();
        }
    }
}