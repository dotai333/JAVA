package Dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.SinhVien;
import Utils.DBConnection;

public class SinhVienDao {

    public List<SinhVien> findAll() throws ClassNotFoundException {
        List<SinhVien> ds = new ArrayList<>();
        String sql = "SELECT * FROM SINHVIEN";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maSV = rs.getString("MaSV");
                String hoTen = rs.getString("HoTen");
                Date ngaySinh = rs.getDate("NgaySinh");
                boolean gioiTinh = rs.getBoolean("GioiTinh");
                String diaChi = rs.getString("DiaChi");
                String maKhoa = rs.getString("MaKhoa");

                ds.add(new SinhVien(maSV, hoTen, ngaySinh, gioiTinh, diaChi, maKhoa));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
        return ds;
    }

    public boolean delete(String maSV) throws ClassNotFoundException {
        String sql = "DELETE FROM SINHVIEN WHERE MaSV=?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maSV);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public SinhVien findById(String maSV) throws ClassNotFoundException {
        String sql = "SELECT * FROM SINHVIEN WHERE MaSV=?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maSV);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new SinhVien(
                            rs.getString("MaSV"),
                            rs.getString("HoTen"),
                            rs.getDate("NgaySinh"),
                            rs.getBoolean("GioiTinh"),
                            rs.getString("DiaChi"),
                            rs.getString("MaKhoa")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return null;
    }

    public boolean insert(SinhVien sv) {
        String sql = "INSERT INTO SINHVIEN VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sv.getMaSV());
            ps.setString(2, sv.getHoTen());
            ps.setDate(3, new java.sql.Date(sv.getNgaySinh().getTime()));
            ps.setBoolean(4, sv.isGioiTinh());
            ps.setString(5, sv.getDiaChi());
            ps.setString(6, sv.getMaKhoa());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public boolean update(SinhVien sv) {
        String sql = "UPDATE SINHVIEN SET HoTen = ?, NgaySinh = ?, GioiTinh = ?, DiaChi = ?, MaKhoa = ? WHERE MaSV = ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, sv.getHoTen());
            ps.setDate(2, new java.sql.Date(sv.getNgaySinh().getTime()));
            ps.setBoolean(3, sv.isGioiTinh());
            ps.setString(4, sv.getDiaChi());
            ps.setString(5, sv.getMaKhoa());
            ps.setString(6, sv.getMaSV());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public List<SinhVien> findByName(String name) throws ClassNotFoundException {
        List<SinhVien> ds = new ArrayList<>();
        String sql = "SELECT * FROM SINHVIEN WHERE HoTen LIKE ? OR MaSV LIKE ? OR DiaChi LIKE ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + name + "%");
            ps.setString(3, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maSV = rs.getString("MaSV");
                    String hoTen = rs.getString("HoTen");
                    Date ngaySinh = rs.getDate("NgaySinh");
                    boolean gioiTinh = rs.getBoolean("GioiTinh");
                    String diaChi = rs.getString("DiaChi");
                    String maKhoa = rs.getString("MaKhoa");

                    ds.add(new SinhVien(maSV, hoTen, ngaySinh, gioiTinh, diaChi, maKhoa));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo tên: " + e.getMessage());
        }
        return ds;
    }

    public boolean exists(String maSV) {
        String sql = "SELECT * FROM SinhVien WHERE MaSV = ?";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSV.trim());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public int countSinhVien() throws ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM SINHVIEN";
        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql); 
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // =========================================================================
    // TÍNH NĂNG MỚI: PHÂN TRANG VÀ TÌM KIẾM CHO SQL SERVER
    // =========================================================================

    public List<SinhVien> findByPageAndKeyword(String keyword, int page, int pageSize) throws ClassNotFoundException {
        List<SinhVien> ds = new ArrayList<>();
        int offset = (page - 1) * pageSize;

        String sql = "SELECT MaSV, HoTen, NgaySinh, GioiTinh, DiaChi, MaKhoa " +
                     "FROM SINHVIEN " +
                     "WHERE MaSV LIKE ? OR HoTen LIKE ? OR DiaChi LIKE ? OR MaKhoa LIKE ? " +
                     "ORDER BY MaSV ASC " +
                     "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String key = "%" + (keyword == null ? "" : keyword.trim()) + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ps.setString(4, key);
            ps.setInt(5, offset);
            ps.setInt(6, pageSize);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maSV = rs.getString("MaSV");
                    String hoTen = rs.getString("HoTen");
                    Date ngaySinh = rs.getDate("NgaySinh");
                    boolean gioiTinh = rs.getBoolean("GioiTinh");
                    String diaChi = rs.getString("DiaChi");
                    String maKhoa = rs.getString("MaKhoa");

                    ds.add(new SinhVien(maSV, hoTen, ngaySinh, gioiTinh, diaChi, maKhoa));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn phân trang SinhVien: " + e.getMessage());
            e.printStackTrace();
        }
        return ds;
    }

    public int countByKeyword(String keyword) throws ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM SINHVIEN WHERE MaSV LIKE ? OR HoTen LIKE ? OR DiaChi LIKE ? OR MaKhoa LIKE ?";

        try (Connection conn = DBConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String key = "%" + (keyword == null ? "" : keyword.trim()) + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ps.setString(3, key);
            ps.setString(4, key);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi đếm số lượng SinhVien: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }
}
