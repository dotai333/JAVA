package Controller;

import Dao.TaiKhoanDAO;
import Model.TaiKhoan;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "TaiKhoanServlet", urlPatterns = {"/taikhoan"})
public class TaiKhoanServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
       
        HttpSession session = request.getSession();
        TaiKhoan currentUser = (TaiKhoan) session.getAttribute("currentUser");
        if (currentUser == null || !"ADMIN".equals(currentUser.getChucVu())) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        TaiKhoanDAO dao = new TaiKhoanDAO();

        switch (action) {
            case "list":
                // Lấy danh sách tài khoản và truyền sang trang jsp
                List<TaiKhoan> list = dao.getAllAccounts();
                request.setAttribute("listTK", list);
                request.getRequestDispatcher("view/taikhoan.jsp").forward(request, response);
                break;
                
            case "add":
                // Chuyển hướng đến trang form thêm mới
                request.getRequestDispatcher("view/form-taikhoan.jsp").forward(request, response);
                break;
                
            case "insert":
                // Lấy dữ liệu từ form thêm tài khoản
                String tenDangNhap = request.getParameter("tenDangNhap");
                String matKhau = request.getParameter("matKhau");
                String hoTen = request.getParameter("hoTen");
                String chucVu = request.getParameter("chucVu");
                
                TaiKhoan newTk = new TaiKhoan(tenDangNhap, matKhau, hoTen, chucVu);
                dao.insertAccount(newTk);
                
                // Thêm xong quay về danh sách
                response.sendRedirect("taikhoan?action=list");
                break;
                
            case "delete":
                // Xóa tài khoản theo tên đăng nhập
                String tenDangNhapDel = request.getParameter("tenDangNhap");
                dao.deleteAccount(tenDangNhapDel);
                
                // Xóa xong quay về danh sách
                response.sendRedirect("taikhoan?action=list");
                break;
                
            default:
                response.sendRedirect("taikhoan?action=list");
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}