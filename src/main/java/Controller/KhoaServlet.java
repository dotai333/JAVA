package Controller;

import Dao.KhoaDao;
import Model.Khoa;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "KhoaServlet", urlPatterns = {"/khoa", "/KhoaServlet"})
public class KhoaServlet extends HttpServlet {

    private KhoaDao khDAO = new KhoaDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String action = "list";

            if (request.getParameter("action") != null) {
                action = request.getParameter("action");
            }

            switch (action) {

                case "list":

                    var listKH = khDAO.findAll();

                    System.out.println("Số lượng khoa tìm thấy: "
                            + (listKH != null ? listKH.size() : "null"));

                    request.setAttribute("listKH", listKH);

                    request.getRequestDispatcher("/view/khoa.jsp")
                            .forward(request, response);

                    break;

                case "search":

                    System.out.println("=== Đang gọi action SEARCH KHOA ===");

                    String keyword = request.getParameter("keyword");

                    if (keyword == null) {
                        keyword = "";
                    }

                    var searchResult = khDAO.findByName(keyword);

                    request.setAttribute("listKH", searchResult);
                    request.setAttribute("keyword", keyword);

                    request.getRequestDispatcher("/view/khoa.jsp")
                            .forward(request, response);

                    break;

                case "add":

                    System.out.println("add khoa");

                    request.getRequestDispatcher("/view/form-khoa.jsp")
                            .forward(request, response);

                    break;

                case "edit":

                    System.out.println("edit khoa");

                    String makh = request.getParameter("id");

                    var kh = khDAO.findById(makh);

                    request.setAttribute("kh", kh);

                    request.getRequestDispatcher("/view/form-khoa.jsp")
                            .forward(request, response);

                    break;

                case "delete":

                    System.out.println("delete khoa");

                    xuLyXoa(request, response);

                    break;
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            throw new ServletException(
                    "Lỗi trong KhoaServlet: " + ex.getMessage(), ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            request.setCharacterEncoding("UTF-8");

            String action = request.getParameter("action");

            switch (action) {

                case "insert":

                    System.out.println("insert khoa");

                    xuLyThemKH(request, response);

                    break;

                case "update":

                    System.out.println("update khoa");

                    xuLySuaKH(request, response);

                    break;
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            throw new ServletException(
                    "Lỗi trong KhoaServlet (doPost): "
                    + ex.getMessage(), ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Khoa Servlet";
    }

    private void xuLyXoa(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ClassNotFoundException {

        try {

            String makh = request.getParameter("id");

            boolean isDeleted = khDAO.delete(makh);

            if (isDeleted) {

                response.sendRedirect(
                        "khoa?action=list&message=deleted");

            } else {

                response.sendRedirect(
                        "khoa?action=list&error=has_data");
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            response.sendRedirect(
                    "khoa?action=list&error=has_data");
        }
    }

    private void xuLyThemKH(HttpServletRequest request,
            HttpServletResponse response)
            throws ClassNotFoundException, IOException {

        String makh = request.getParameter("maKhoa");
        String tenkh = request.getParameter("tenKhoa");
        if (makh.isEmpty() || tenkh.isEmpty()) {
            response.sendRedirect("khoa?action=add&error=empty");
            return;
        }

        if (khDAO.exists(makh)) {
            response.sendRedirect("khoa?action=add&error=duplicate");
            return;
        }
        if (khDAO.existsByName(tenkh)) {
            response.sendRedirect("khoa?action=add&error=duplicate_name");
            return;
        }
        Khoa kh = new Khoa(makh, tenkh);

        khDAO.insert(kh);

        response.sendRedirect("khoa?action=list&message=added");
    }

    private void xuLySuaKH(HttpServletRequest request,
            HttpServletResponse response)
            throws ClassNotFoundException, IOException {

        String makh = request.getParameter("maKhoa");
        String tenkh = request.getParameter("tenKhoa");
        if (tenkh.isEmpty()) {
            response.sendRedirect("khoa?action=edit&id=" + makh + "&error=empty");
            return;
        }
        if (khDAO.existsByNameAndNotId(tenkh, makh)) {
        response.sendRedirect("khoa?action=edit&id=" + makh+ "&error=duplicate_name");
        return;
    }
        Khoa kh = new Khoa(makh, tenkh);

        khDAO.update(kh);

        response.sendRedirect("khoa?action=list&message=updated");
    }
}
