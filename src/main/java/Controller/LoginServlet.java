package Controller;

import Dao.TaiKhoanDAO;
import Model.TaiKhoan;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private TaiKhoanDAO tkDAO = new TaiKhoanDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String user = request.getParameter("username");
        String pass = request.getParameter("password");

      
        TaiKhoan tk = tkDAO.checkLogin(user, pass);
        
        if (tk != null) {
           
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", tk);
            
           
            response.sendRedirect("dashboard");
        } else {
           
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
}