/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import Model.Khoa;
import Utils.DBConnection;

/**
 *
 * @author MSI
 */
public class KhoaDao {

    public List<Khoa> findAll() throws ClassNotFoundException {
        List<Khoa> ds = new ArrayList<>();
        String sql = "SELECT * FROM KHOA";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maKH = rs.getString("MaKhoa");
                String tenKH = rs.getString("TenKhoa");

                ds.add(new Khoa(maKH, tenKH));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
        return ds;
    }

    public Khoa findById(String maKH) throws ClassNotFoundException {
        String sql = "SELECT * FROM KHOA WHERE MaKhoa=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maKH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Khoa(
                            rs.getString("MaKhoa"),
                            rs.getString("TenKhoa")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return null;
    }

    public boolean delete(String maKH) throws ClassNotFoundException {
        String sql = "DELETE FROM KHOA WHERE MaKhoa=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maKH);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public boolean insert(Khoa kh) {
        String sql = "INSERT INTO KHOA VALUES (?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kh.getMaKhoa());
            ps.setString(2, kh.getTenKhoa());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public boolean update(Khoa kh) {
        String sql = "UPDATE KHOA SET TenKhoa = ? WHERE MaKhoa = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kh.getTenKhoa());

            ps.setString(2, kh.getMaKhoa());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public List<Khoa> findByName(String name) throws ClassNotFoundException {
        List<Khoa> ds = new ArrayList<>();
        String sql = "SELECT * FROM KHOA WHERE TenKhoa LIKE ? OR MaKhoa LIKE ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");
            ps.setString(2, "%" + name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maKH = rs.getString("MaKhoa");
                    String tenKH = rs.getString("TenKhoa");

                    ds.add(new Khoa(maKH, tenKH));
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm theo tên: " + e.getMessage());
        }
        return ds;
    }

    public boolean exists(String maKhoa) {
        String sql = "SELECT * FROM Khoa WHERE MaKhoa = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKhoa.trim());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Trả về true nếu đã tồn tại mã khoa này
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    // 1. Kiểm tra tên khoa đã tồn tại chưa (dùng khi Thêm mới)

    public boolean existsByName(String tenKhoa) throws ClassNotFoundException {
        String sql = "SELECT 1 FROM KHOA WHERE TenKhoa = ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenKhoa.trim());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Trả về true nếu đã có tên khoa này
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

// 2. Kiểm tra tên khoa đã tồn tại ở MỘT MÃ KHOA KHÁC CHƯA (dùng khi Cập nhật)
    public boolean existsByNameAndNotId(String tenKhoa, String maKhoa) throws ClassNotFoundException {
        String sql = "SELECT 1 FROM KHOA WHERE TenKhoa = ? AND MaKhoa <> ?";
        try (
                Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenKhoa.trim());
            ps.setString(2, maKhoa.trim());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Trả về true nếu tên này đã thuộc về khoa khác
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public int countKhoa() throws ClassNotFoundException {
    String sql = "SELECT COUNT(*) FROM KHOA";
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
}
