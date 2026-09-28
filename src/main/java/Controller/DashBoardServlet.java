package Controller;

import Dao.KhoaDao;
import Dao.SinhVienDao;
import Dao.MonHocDAO;
import Dao.DangKyDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DashBoardServlet", urlPatterns = {"/dashboard"})
public class DashBoardServlet extends HttpServlet {

    private KhoaDao khoaDao = new KhoaDao();
    private SinhVienDao svDao = new SinhVienDao();
    private MonHocDAO mhDao = new MonHocDAO();
    private DangKyDao dkDao = new DangKyDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
          
            int totalKhoa = khoaDao.countKhoa();
            int totalSV = svDao.countSinhVien();
            int totalMH = mhDao.countMonHoc();
            int totalDangKy = dkDao.countDangKy();

           
            request.setAttribute("totalKhoa", totalKhoa);
            request.setAttribute("totalSV", totalSV);
            request.setAttribute("totalMH", totalMH);
            request.setAttribute("totalDangKy", totalDangKy);

         
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Lỗi tải trang Dashboard: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}