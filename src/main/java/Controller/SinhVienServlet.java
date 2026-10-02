package Controller;

import Dao.KhoaDao;
import Dao.SinhVienDao;
import Model.Khoa;
import Model.SinhVien;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "SinhVienServlet", urlPatterns = {"/SinhVienServlet", "/sinhvien"})
public class SinhVienServlet extends HttpServlet {

    private final SinhVienDao sinhVienDao = new SinhVienDao();
    private final KhoaDao khoaDao = new KhoaDao();
    private static final int PAGE_SIZE = 5; // Số bản ghi hiển thị trên mỗi trang

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null || action.trim().isEmpty()) {
            action = "list";
        }

        try {
            switch (action) {
                case "add":
                    showAddForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteSinhVien(request, response);
                    break;
                case "search":
                case "list":
                default:
                    hienThiDanhSachPhanTrang(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/sinhvien?action=list");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        try {
            if ("insert".equals(action)) {
                insertSinhVien(request, response);
            } else if ("update".equals(action)) {
                updateSinhVien(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/sinhvien?action=list");
        }
    }

    private void hienThiDanhSachPhanTrang(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        String action = request.getParameter("action");
        if (action == null || action.trim().isEmpty()) {
            action = "list";
        }

        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }

        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.trim().isEmpty()) {
            try {
                page = Integer.parseInt(pageStr.trim());
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int totalRecords = sinhVienDao.countByKeyword(keyword);
        int totalPages = (int) Math.ceil((double) totalRecords / PAGE_SIZE);

        if (page > totalPages && totalPages > 0) {
            page = totalPages;
        }
        if (page < 1) {
            page = 1;
        }

        List<SinhVien> dsSinhVien = sinhVienDao.findByPageAndKeyword(keyword, page, PAGE_SIZE);

        request.setAttribute("dsSinhVien", dsSinhVien);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("totalRecords", totalRecords);
        request.setAttribute("keyword", keyword);
        request.setAttribute("action", action); // Bổ sung để JSP giữ nguyên action

        // LƯU Ý: Kiểm tra lại chính xác đường dẫn thư mục chứa file sinhvien.jsp trong project của bạn
        request.getRequestDispatcher("/view/sinhvien.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        List<Khoa> dsKhoa = khoaDao.findAll();
        request.setAttribute("dsKhoa", dsKhoa);
        request.setAttribute("isEdit", false);
        
        request.getRequestDispatcher("/view/form-sinhvien.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        String maSV = request.getParameter("maSV");
        SinhVien sv = sinhVienDao.findById(maSV);
        List<Khoa> dsKhoa = khoaDao.findAll();

        request.setAttribute("sv", sv);
        request.setAttribute("dsKhoa", dsKhoa);
        request.setAttribute("isEdit", true);
        
        request.getRequestDispatcher("/view/form-sinhvien.jsp").forward(request, response);
    }

    private void insertSinhVien(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        String maSV = request.getParameter("maSV");
        String hoTen = request.getParameter("hoTen");
        String ngaySinhStr = request.getParameter("ngaySinh");
        boolean gioiTinh = Boolean.parseBoolean(request.getParameter("gioiTinh"));
        String diaChi = request.getParameter("diaChi");
        String maKhoa = request.getParameter("maKhoa");

        if (maSV == null || maSV.trim().isEmpty() || hoTen == null || hoTen.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/sinhvien?action=add&error=empty");
            return;
        }

        if (sinhVienDao.exists(maSV)) {
            response.sendRedirect(request.getContextPath() + "/sinhvien?action=add&error=duplicate");
            return;
        }

        Date ngaySinh = null;
        if (ngaySinhStr != null && !ngaySinhStr.trim().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ngaySinh = sdf.parse(ngaySinhStr);
        }

        SinhVien sv = new SinhVien(maSV.trim(), hoTen.trim(), ngaySinh, gioiTinh, diaChi, maKhoa);
        sinhVienDao.insert(sv);

        response.sendRedirect(request.getContextPath() + "/sinhvien?action=list&message=added");
    }

    private void updateSinhVien(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        String maSV = request.getParameter("maSV");
        String hoTen = request.getParameter("hoTen");
        String ngaySinhStr = request.getParameter("ngaySinh");
        boolean gioiTinh = Boolean.parseBoolean(request.getParameter("gioiTinh"));
        String diaChi = request.getParameter("diaChi");
        String maKhoa = request.getParameter("maKhoa");

        Date ngaySinh = null;
        if (ngaySinhStr != null && !ngaySinhStr.trim().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ngaySinh = sdf.parse(ngaySinhStr);
        }

        SinhVien sv = new SinhVien(maSV, hoTen, ngaySinh, gioiTinh, diaChi, maKhoa);
        sinhVienDao.update(sv);

        response.sendRedirect(request.getContextPath() + "/sinhvien?action=list&message=updated");
    }

    private void deleteSinhVien(HttpServletRequest request, HttpServletResponse response) 
            throws Exception {
        String maSV = request.getParameter("maSV");
        try {
            boolean result = sinhVienDao.delete(maSV);
            if (result) {
                response.sendRedirect(request.getContextPath() + "/sinhvien?action=list&message=deleted");
            } else {
                response.sendRedirect(request.getContextPath() + "/sinhvien?action=list&error=has_data");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/sinhvien?action=list&error=has_data");
        }
    }
}