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

    private SinhVienDao svDao = new SinhVienDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String action = request.getParameter("action");
            if (action == null) {
                action = "list";
            }

            switch (action) {
                case "list": {
                    List<SinhVien> list = svDao.findAll();
                    request.setAttribute("dsSinhVien", list);
                    request.getRequestDispatcher("/view/sinhvien.jsp").forward(request, response);
                    break;
                }
                case "add": {
                    // Lấy danh sách khoa để đổ vào combobox ở form thêm sinh viên
                    KhoaDao khoaDao = new KhoaDao();
                    List<Khoa> dsKhoa = khoaDao.findAll();
                    request.setAttribute("dsKhoa", dsKhoa);

                    request.setAttribute("isEdit", false);
                    request.getRequestDispatcher("/view/form-sinhvien.jsp").forward(request, response);
                    break;
                }
                case "edit": {
                    String maSV = request.getParameter("maSV");
                    SinhVien sv = svDao.findById(maSV);
                    request.setAttribute("sv", sv);

                    // Lấy danh sách khoa để đổ vào combobox và tự động chọn (selected) khoa của sinh viên đó
                    KhoaDao khoaDao = new KhoaDao();
                    List<Khoa> dsKhoa = khoaDao.findAll();
                    request.setAttribute("dsKhoa", dsKhoa);

                    request.setAttribute("isEdit", true);
                    request.getRequestDispatcher("/view/form-sinhvien.jsp").forward(request, response);
                    break;
                }
                case "delete": {
                    String maSVDel = request.getParameter("maSV");

                    boolean isDeleted = svDao.delete(maSVDel);

                    if (isDeleted) {

                        response.sendRedirect("sinhvien?action=list&message=deleted");
                    } else {

                        response.sendRedirect("sinhvien?action=list&error=has_data");
                    }
                    break;
                }
                case "search": {
                    String keyword = request.getParameter("keyword");
                    if (keyword == null) {
                        keyword = "";
                    }
                    List<SinhVien> list = svDao.findByName(keyword); // Hoặc hàm tìm kiếm tương ứng trong SinhVienDao
                    request.setAttribute("dsSinhVien", list);
                    request.setAttribute("keyword", keyword);
                    request.getRequestDispatcher("/view/sinhvien.jsp").forward(request, response);
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException("Lỗi trong SinhVienServlet: " + ex.getMessage(), ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        try {
            String maSV = request.getParameter("MaSV");
            maSV = (maSV != null) ? maSV.trim() : "";

            String hoTen = request.getParameter("HoTen");
            hoTen = (hoTen != null) ? hoTen.trim() : "";

            String ngaySinhStr = request.getParameter("NgaySinh");
            String gioiTinhStr = request.getParameter("gioiTinh");
            boolean gioiTinh = (gioiTinhStr != null) ? Boolean.parseBoolean(gioiTinhStr) : false;

            String diaChi = request.getParameter("DiaChi");
            diaChi = (diaChi != null) ? diaChi.trim() : "";

            String maKhoa = request.getParameter("MaKhoa");
            maKhoa = (maKhoa != null) ? maKhoa.trim() : "";

            if ("insert".equals(action)) {
                // 1. Chống để trống khi thêm (Kiểm tra null kết hợp isEmpty)
                if (maSV.isEmpty() || hoTen.isEmpty() || ngaySinhStr == null || ngaySinhStr.isEmpty() || maKhoa.isEmpty()) {
                    response.sendRedirect("sinhvien?action=add&error=empty");
                    return;
                }

                // 2. Chống trùng mã sinh viên
                if (svDao.exists(maSV)) {
                    response.sendRedirect("sinhvien?action=add&error=duplicate");
                    return;
                }

                Date ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinhStr);
                SinhVien sv = new SinhVien(maSV, hoTen, ngaySinh, gioiTinh, diaChi, maKhoa);
                svDao.insert(sv);

                response.sendRedirect("sinhvien?action=list&message=added");

            } else if ("update".equals(action)) {
                // Chống để trống khi sửa
                if (hoTen.isEmpty() || ngaySinhStr == null || ngaySinhStr.isEmpty() || maKhoa.isEmpty()) {
                    response.sendRedirect("sinhvien?action=edit&maSV=" + maSV + "&error=empty");
                    return;
                }

                Date ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinhStr);
                SinhVien sv = new SinhVien(maSV, hoTen, ngaySinh, gioiTinh, diaChi, maKhoa);
                svDao.update(sv);

                response.sendRedirect("sinhvien?action=list&message=updated");
            } else {
                response.sendRedirect("sinhvien?action=list");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Lỗi Servlet SinhVien (POST): " + e.getMessage(), e);
        }
    }
}
