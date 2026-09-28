package Controller;

import Dao.MonHocDAO;
import Model.MonHoc;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "MonHocList", urlPatterns = {"/monhoc", "/MonHocList"})
public class MonHocSerlet extends HttpServlet {

    private MonHocDAO mhDAO = new MonHocDAO();

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

                    var listMH = mhDAO.findAll();
                    System.out.println("Số lượng môn học tìm thấy: " + (listMH != null ? listMH.size() : "null"));

                    request.setAttribute("listMH", listMH);
                    request.getRequestDispatcher("/view/monhoc.jsp").forward(request, response);
                    break;
                case "search":
                    System.out.println("=== Đang gọi action SEARCH ===");
                    String keyword = request.getParameter("keyword");

                    if (keyword == null) {
                        keyword = "";
                    }

                    var searchResult = mhDAO.search(keyword);

                    request.setAttribute("listMH", searchResult);
                    request.setAttribute("keyword", keyword);

                    request.getRequestDispatcher("/view/monhoc.jsp").forward(request, response);
                    break;
                case "add":
                    System.out.println("add");
                    request.getRequestDispatcher("/view/form-monhoc.jsp").forward(request, response);
                    break;

                case "edit":
                    System.out.println("edit");
                    String mamh = request.getParameter("id"); // Lấy mã MH cần edit
                    var mh = mhDAO.findById(mamh);
                    request.setAttribute("mh", mh);

                    request.getRequestDispatcher("/view/form-monhoc.jsp").forward(request, response);
                    break;

                case "delete":
                    System.out.println("delete");
                    xuLyXoa(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException("Lỗi trong MonHocSerlet: " + ex.getMessage(), ex);
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
                    System.out.println("insert");
                    xuLyThemMH(request, response);
                    break;

                case "update":
                    System.out.println("update");
                    xuLySuaMH(request, response);
                    break;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ServletException("Lỗi trong MonHocSerlet (doPost): " + ex.getMessage(), ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "MonHoc Servlet";
    }

    private void xuLyXoa(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {
        try {
            String mamh = request.getParameter("id");

            boolean isDeleted = mhDAO.delete(mamh);

            if (isDeleted) {
                // SỬA TẠI ĐÂY: Thêm &message=deleted khi redirect về
                response.sendRedirect("monhoc?action=list&message=deleted");
            } else {
                // Nếu xóa thất bại (do dính khóa ngoại / đã có điểm)
                response.sendRedirect("monhoc?action=list&error=has_data");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                response.sendRedirect("monhoc?action=list&error=has_data");
            } catch (IOException ioEx) {
                ioEx.printStackTrace();
            }
        }
    }

    private void xuLyThemMH(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        String mamh = request.getParameter("maMH");
        String tenmh = request.getParameter("tenMH");
        String sotinchi = request.getParameter("soTinChi");
        String sotinchiStr = request.getParameter("soTinChi");
        sotinchiStr = (sotinchiStr != null) ? sotinchiStr.trim() : "";
        if (mamh.isEmpty() || tenmh.isEmpty() || sotinchiStr.isEmpty()) {
            response.sendRedirect("monhoc?action=add&error=empty");
            return;
        }

        if (mhDAO.exists(mamh)) {
            response.sendRedirect("monhoc?action=add&error=duplicate");
            return;
        }

        if (mhDAO.existsByName(tenmh)) {
            response.sendRedirect("monhoc?action=add&error=duplicate_name");
            return;
        }
        int soTinChi;
        try {
            soTinChi = Integer.parseInt(sotinchiStr);
            if (soTinChi <= 0) {
                response.sendRedirect("monhoc?action=add&error=invalid_tc");
                return;
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("monhoc?action=add&error=invalid_tc");
            return;
        }
        MonHoc mh = new MonHoc(mamh, tenmh, Integer.parseInt(sotinchi));
        mhDAO.insert(mh);
        response.sendRedirect("monhoc?action=list&message=added");
    }

    private void xuLySuaMH(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException {
        String mamh = request.getParameter("maMH");
        String tenmh = request.getParameter("tenMH");
        String sotinchi = request.getParameter("soTinChi");
        String sotinchiStr = request.getParameter("soTinChi");
        sotinchiStr = (sotinchiStr != null) ? sotinchiStr.trim() : "";

        if (tenmh.isEmpty() || sotinchiStr.isEmpty()) {
            response.sendRedirect("monhoc?action=edit&id=" + mamh + "&error=empty");
            return;
        }
        if (mhDAO.existsByNameAndNotId(tenmh, mamh)) {
            response.sendRedirect("monhoc?action=edit&id=" + mamh + "&error=duplicate_name");
            return;
        }
        int soTinChi;
        try {
            soTinChi = Integer.parseInt(sotinchiStr);
            if (soTinChi <= 0) {
                response.sendRedirect("monhoc?action=edit&id=" + mamh + "&error=invalid_tc");
                return;
            }
        } catch (NumberFormatException e) {
            response.sendRedirect("monhoc?action=edit&id=" + mamh + "&error=invalid_tc");
            return;
        }
        MonHoc mh = new MonHoc(mamh, tenmh, Integer.parseInt(sotinchi));
        mhDAO.update(mh);

        response.sendRedirect("monhoc?action=list&message=updated");
    }
}
