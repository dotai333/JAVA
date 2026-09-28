package Dao;

import Model.MonHoc;
import Utils.DBConnection;
import Model.MonHoc;
import Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonHocDAO {

    // ================================
    // 1. LẤY TẤT CẢ MÔN HỌC
    // ================================
    public List<MonHoc> findAll() throws ClassNotFoundException {

        List<MonHoc> list = new ArrayList<>();

        String sql = "SELECT MaMH, TenMH, SoTinChi "
                + "FROM MONHOC "
                + "ORDER BY MaMH";

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                MonHoc mh = new MonHoc();

                mh.setMaMH(rs.getString("MaMH"));
                mh.setTenMH(rs.getString("TenMH"));
                mh.setSoTinChi(rs.getInt("SoTinChi"));

                list.add(mh);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================================
    // 2. THÊM MÔN HỌC
    // ================================
    public boolean insert(MonHoc mh) throws ClassNotFoundException {

        String sql
                = "INSERT INTO MONHOC(MaMH, TenMH, SoTinChi) "
                + "VALUES (?, ?, ?)";

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, mh.getMaMH());
            ps.setString(2, mh.getTenMH());
            ps.setInt(3, mh.getSoTinChi());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================================
    // 3. CẬP NHẬT MÔN HỌC
    // ================================
    public boolean update(MonHoc mh) throws ClassNotFoundException {

        String sql
                = "UPDATE MONHOC "
                + "SET TenMH = ?, SoTinChi = ? "
                + "WHERE MaMH = ?";

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, mh.getTenMH());
            ps.setInt(2, mh.getSoTinChi());
            ps.setString(3, mh.getMaMH());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================================
    // 4. XÓA MÔN HỌC
    // ================================
    public boolean delete(String maMH) throws ClassNotFoundException {

        String sql
                = "DELETE FROM MONHOC "
                + "WHERE MaMH = ?";

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maMH);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // ================================
    // 5. TÌM KIẾM MÔN HỌC
    // ================================
    public List<MonHoc> search(String keyword) throws ClassNotFoundException {

        List<MonHoc> list = new ArrayList<>();

        String sql = "SELECT MaMH, TenMH, SoTinChi "
                + "FROM MONHOC "
                + "WHERE MaMH LIKE ? "
                + " OR TenMH LIKE ? "
                + "ORDER BY MaMH";

        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            String value = "%" + keyword + "%";

            ps.setString(1, value);
            ps.setString(2, value);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    MonHoc mh = new MonHoc();
                    mh.setMaMH(rs.getString("MaMH"));
                    mh.setTenMH(rs.getString("TenMH"));
                    mh.setSoTinChi(rs.getInt("SoTinChi"));
                    list.add(mh);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // ================================
    // 6. TÌM THEO MÃ MÔN HỌC
    // ================================
    public MonHoc findById(String maMH) throws ClassNotFoundException {

        String sql
                = "SELECT MaMH, TenMH, SoTinChi "
                + "FROM MONHOC "
                + "WHERE MaMH = ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maMH);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                MonHoc mh = new MonHoc();
                mh.setMaMH(rs.getString("MaMH"));
                mh.setTenMH(rs.getString("TenMH"));
                mh.setSoTinChi(rs.getInt("SoTinChi"));
                return mh;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exists(String maMH) throws ClassNotFoundException {
        String sql = "SELECT 1 FROM MONHOC WHERE MaMH = ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maMH.trim());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Trả về true nếu đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        MonHocDAO mhDAO = new MonHocDAO();
        for (var mh : mhDAO.findAll()) {
            System.out.println(mh);
        }

    }
    // 1. Kiểm tra tên môn học đã tồn tại chưa (dùng khi Thêm mới)

    public boolean existsByName(String tenMH) throws ClassNotFoundException {
        String sql = "SELECT 1 FROM MONHOC WHERE TenMH = ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenMH.trim());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Trả về true nếu đã có tên này
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

// 2. Kiểm tra tên môn học đã tồn tại ở MỘT MÃ KHÁC CHƯA (dùng khi Cập nhật)
    public boolean existsByNameAndNotId(String tenMH, String maMH) throws ClassNotFoundException {
        String sql = "SELECT 1 FROM MONHOC WHERE TenMH = ? AND MaMH <> ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenMH.trim());
            ps.setString(2, maMH.trim());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Trả về true nếu tên này đã thuộc về môn học khác
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int countMonHoc() throws ClassNotFoundException {
        String sql = "SELECT COUNT(*) FROM MONHOC";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
