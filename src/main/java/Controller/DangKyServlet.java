package Controller;

import Dao.DangKyDao;
import Dao.MonHocDAO;
import Dao.SinhVienDao;
import Model.DangKyView;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DangKyServlet",
        urlPatterns = {"/DangKyServlet", "/dangky"})
public class DangKyServlet extends HttpServlet {

   private DangKyDao dkDao = new DangKyDao();
    private SinhVienDao svDao = new SinhVienDao();
    private MonHocDAO mhDao = new MonHocDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "list":
                case "search":
                    hienThiDanhSachPhanTrang(request, response);
                    break;
                case "add":
                    request.setAttribute("isEdit", false);
                    request.setAttribute("dsSinhVien", svDao.findAll());
                    request.setAttribute("dsMonHoc", mhDao.findAll());
                    request.getRequestDispatcher("/view/form-dangky.jsp").forward(request, response);
                    break;
                case "edit":
                    String maSVEdit = request.getParameter("maSV");
                    String maMHEdit = request.getParameter("maMH");
                    DangKyView dkEdit = dkDao.findByCompositeKey(maSVEdit, maMHEdit);

                    request.setAttribute("dk", dkEdit);
                    request.setAttribute("isEdit", true);
                    request.setAttribute("dsSinhVien", svDao.findAll());
                    request.setAttribute("dsMonHoc", mhDao.findAll());
                    request.getRequestDispatcher("/view/form-dangky.jsp").forward(request, response);
                    break;
                case "delete":
                    String maSVDel = request.getParameter("maSV");
                    String maMHDel = request.getParameter("maMH");

                    boolean isDeleted = dkDao.delete(maSVDel, maMHDel);

                    if (isDeleted) {
                        response.sendRedirect("dangky?action=list&message=deleted");
                    } else {
                        response.sendRedirect("dangky?action=list&error=fail");
                    }
                    break;
                default:
                    response.sendRedirect("dangky?action=list");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Lỗi Servlet DangKy (GET): " + e.getMessage(), e);
        }
    }

    /**
     * Hàm dùng chung xử lý hiển thị danh sách có Phân trang & Tìm kiếm
     */
    private void hienThiDanhSachPhanTrang(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Lấy trang hiện tại (mặc định trang 1)
        int page = 1;
        String pageStr = request.getParameter("page");
        if (pageStr != null && !pageStr.trim().isEmpty()) {
            try {
                page = Integer.parseInt(pageStr);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        // 2. Cấu hình số dòng mỗi trang
        int pageSize = 10;

        // 3. Lấy từ khóa tìm kiếm
        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }

        try {
            // 4. Gọi DAO lấy danh sách phân trang và đếm số lượng tổng
            List<DangKyView> list = dkDao.findByPageAndKeyword(keyword, page, pageSize);
            int totalRecords = dkDao.countByKeyword(keyword);

            // 5. Tính tổng số trang
            int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            // 6. Đẩy toàn bộ thuộc tính sang JSP
            request.setAttribute("dsDangKy", list);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("totalRecords", totalRecords);
            request.setAttribute("pageSize", pageSize);
            request.setAttribute("keyword", keyword);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Lỗi kết nối CSDL hoặc Driver DB: " + e.getMessage(), e);
        }

        // 7. Forward sang JSP
        request.getRequestDispatcher("/view/dangky.jsp").forward(request, response);
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
            switch (action) {
                case "insert": {
                    String maSV = request.getParameter("maSV");
                    String maMH = request.getParameter("maMH");
                    if (dkDao.exists(maSV, maMH)) {
                        response.sendRedirect("dangky?action=add&error=duplicate");
                        break; 
                    }
                    String ngayStr = request.getParameter("ngayDangKy");
                    Date ngayDK = (ngayStr != null && !ngayStr.isEmpty()) ? new SimpleDateFormat("yyyy-MM-dd").parse(ngayStr) : new Date();

                    double diemQT = request.getParameter("diemQT") != null && !request.getParameter("diemQT").isEmpty() ? Double.parseDouble(request.getParameter("diemQT")) : 0.0;
                    double diemThi = request.getParameter("diemThi") != null && !request.getParameter("diemThi").isEmpty() ? Double.parseDouble(request.getParameter("diemThi")) : 0.0;
                    double diemTK = (diemQT * 0.4) + (diemThi * 0.6);
                    diemTK = Math.round(diemTK * 100.0) / 100.0;

                    DangKyView dk = new DangKyView(maSV, maMH, ngayDK, diemQT, diemThi, diemTK, "", "");
                    dkDao.insert(dk);
                    response.sendRedirect("dangky?action=list&message=added");
                    break;
                }
                case "update": {
                    String maSV = request.getParameter("maSV");
                    String maMH = request.getParameter("maMH");
                    String ngayStr = request.getParameter("ngayDangKy");
                    Date ngayDK = (ngayStr != null && !ngayStr.isEmpty()) ? new SimpleDateFormat("yyyy-MM-dd").parse(ngayStr) : new Date();

                    double diemQT = request.getParameter("diemQT") != null && !request.getParameter("diemQT").isEmpty() ? Double.parseDouble(request.getParameter("diemQT")) : 0.0;
                    double diemThi = request.getParameter("diemThi") != null && !request.getParameter("diemThi").isEmpty() ? Double.parseDouble(request.getParameter("diemThi")) : 0.0;
                    double diemTK = (diemQT * 0.4) + (diemThi * 0.6);
                    diemTK = Math.round(diemTK * 100.0) / 100.0;

                    DangKyView dk = new DangKyView(maSV, maMH, ngayDK, diemQT, diemThi, diemTK, "", "");
                    dkDao.update(dk);
                    response.sendRedirect("dangky?action=list&message=updated");
                    break;
                }
                default:
                    response.sendRedirect("dangky?action=list");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Lỗi Servlet DangKy (POST): " + e.getMessage(), e);
        }
    }
}
