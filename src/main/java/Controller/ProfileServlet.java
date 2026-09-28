package Controller;

import Dao.TaiKhoanDAO;
import Model.TaiKhoan;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        TaiKhoan user = (TaiKhoan) session.getAttribute("currentUser");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        request.getRequestDispatcher("/view/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        TaiKhoan user = (TaiKhoan) session.getAttribute("currentUser");
        
        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        
        if ("changePassword".equals(action)) {
            String oldPass = request.getParameter("oldPass");
            String newPass = request.getParameter("newPass");
            String confirmPass = request.getParameter("confirmPass");

          
            if (!user.getMatKhau().equals(oldPass)) {
                response.sendRedirect("profile?error=wrong_old_pass");
                return;
            }

            if (!newPass.equals(confirmPass)) {
                response.sendRedirect("profile?error=not_match");
                return;
            }

          
            TaiKhoanDAO dao = new TaiKhoanDAO();
            boolean success = dao.updatePassword(user.getTenDangNhap(), newPass);
            
            if (success) {
           
                user.setMatKhau(newPass);
                session.setAttribute("currentUser", user);
                response.sendRedirect("profile?success=changed");
            } else {
                response.sendRedirect("profile?error=database_error");
            }
        }
    }
}