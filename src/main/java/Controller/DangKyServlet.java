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
                    List<DangKyView> list = dkDao.findAll();
                    request.setAttribute("dsDangKy", list);
                    request.getRequestDispatcher("/view/dangky.jsp").forward(request, response);
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
                case "search":
                    String keyword = request.getParameter("keyword");
                    if (keyword == null) {
                        keyword = "";
                    }
                    List<DangKyView> searchList = dkDao.search(keyword);
                    request.setAttribute("dsDangKy", searchList);
                    request.setAttribute("keyword", keyword);
                    request.getRequestDispatcher("/view/dangky.jsp").forward(request, response);
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
                    double diemTK = request.getParameter("diemTK") != null && !request.getParameter("diemTK").isEmpty() ? Double.parseDouble(request.getParameter("diemTK")) : 0.0;
                    diemTK = (diemQT * 0.4) + (diemThi * 0.6);

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
                    double diemTK = request.getParameter("diemTK") != null && !request.getParameter("diemTK").isEmpty() ? Double.parseDouble(request.getParameter("diemTK")) : 0.0;
                    diemTK = (diemQT * 0.4) + (diemThi * 0.6);
                    diemTK = Math.round(diemTK * 100.0) / 100.0;
                    DangKyView dk = new DangKyView(maSV, maMH, ngayDK, diemQT, diemThi, diemTK, "", "");
                    dkDao.update(dk);
                    response.sendRedirect("dangky?action=list&message=added");
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
