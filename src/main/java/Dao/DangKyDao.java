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
import Model.DangKy;
import Model.DangKyView;
import Model.SinhVien;
import Utils.DBConnection;

/**
 *
 * @author MSI
 */
public class DangKyDao {

    public List<DangKyView> findAll() throws ClassNotFoundException {
        List<DangKyView> ds = new ArrayList<>();
        String sql = """
                       SELECT   DANGKY.MaSV, DANGKY.MaMH, NgayDangKy, DiemQuaTrinh, DiemThi, DiemTongKet, HoTen, TenMH
                         FROM   DANGKY INNER JOIN SINHVIEN ON DANGKY.MaSV = SINHVIEN.MaSV 
                         INNER JOIN  MONHOC ON DANGKY.MaMH = MONHOC.MaMH  
                     """;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String maSV = rs.getString("MaSV");
                String maMH = rs.getString("MaMH");
                Date ngayDK = rs.getDate("NgayDangKy");
                double diemQT = rs.getDouble("DiemQuaTrinh");
                double diemThi = rs.getDouble("DiemThi");
                double diemTK = rs.getDouble("DiemTongKet");
                String tenmh = rs.getString("TenMH");
                String hotensv = rs.getString("HoTen");

                ds.add(new DangKyView(maSV, maMH, ngayDK, diemQT, diemThi, diemTK, hotensv, tenmh));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
        return ds;
    }

    public boolean delete(String maSV, String maMH) throws ClassNotFoundException {
        String sql = "DELETE FROM DANGKY WHERE MaSV=? AND MaMH=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maSV);
            ps.setString(2, maMH);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public DangKy findById(String maSV) throws ClassNotFoundException {
        String sql = "SELECT * FROM DANGKY WHERE MaSV=?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, maSV);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new DangKy(
                            rs.getString("MaSV"),
                            rs.getString("MaMH"),
                            rs.getDate("NgayDangKy"),
                            rs.getDouble("DiemQuaTrinh"),
                            rs.getDouble("DiemThi"),
                            rs.getDouble("DiemTongKet")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm: " + e.getMessage());
        }
        return null;
    }

    public boolean insert(DangKyView dk) {

        String sql = "INSERT INTO DANGKY (MaSV, MaMH, NgayDangKy, DiemQuaTrinh, DiemThi, DiemTongKet) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, dk.getMaSV());
            ps.setString(2, dk.getMaMH());
            ps.setDate(3, new java.sql.Date(dk.getNgayDK().getTime()));
            ps.setDouble(4, dk.getDiemQT());
            ps.setDouble(5, dk.getDiemThi());
            ps.setDouble(6, dk.getDiemTK());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi thêm dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public boolean update(DangKyView dk) {

        String sql = "UPDATE DANGKY SET NgayDangKy = ?, DiemQuaTrinh = ?, DiemThi = ?, DiemTongKet = ? WHERE MaSV = ? AND MaMH = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(dk.getNgayDK().getTime()));
            ps.setDouble(2, dk.getDiemQT());
            ps.setDouble(3, dk.getDiemThi());
            ps.setDouble(4, dk.getDiemTK());

            ps.setString(5, dk.getMaSV());
            ps.setString(6, dk.getMaMH());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
        }
        return false;
    }

    public List<DangKyView> search(String keyword) throws ClassNotFoundException {
        List<DangKyView> ds = new ArrayList<>();
        String sql = """
                       SELECT   DANGKY.MaSV, DANGKY.MaMH, NgayDangKy, DiemQuaTrinh, DiemThi, DiemTongKet, HoTen, TenMH
                         FROM   DANGKY INNER JOIN SINHVIEN ON DANGKY.MaSV = SINHVIEN.MaSV 
                         INNER JOIN  MONHOC ON DANGKY.MaMH = MONHOC.MaMH WHERE dangky.MASV LIKE ? OR TENMH LIKE ?  
                     """;

        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maSV = rs.getString("MaSV");
                String maMH = rs.getString("MaMH");
                Date ngayDK = rs.getDate("NgayDangKy");
                double diemQT = rs.getDouble("DiemQuaTrinh");
                double diemThi = rs.getDouble("DiemThi");
                double diemTK = rs.getDouble("DiemTongKet");
                String tenmh = rs.getString("TenMH");
                String hotensv = rs.getString("HoTen");

                ds.add(new DangKyView(maSV, maMH, ngayDK, diemQT, diemThi, diemTK, hotensv, tenmh));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đọc dữ liệu: " + e.getMessage());
        }
        return ds;
    }

    public static void main(String[] args) throws ClassNotFoundException {

        DangKyDao dkDAO = new DangKyDao();
        for (DangKyView dk : dkDAO.findAll()) {
            System.out.println(dk);
        }
    }

    public DangKyView findByCompositeKey(String maSV, String maMH) throws ClassNotFoundException {
        String sql = """
                     SELECT DANGKY.MaSV, DANGKY.MaMH, NgayDangKy, DiemQuaTrinh, DiemThi, DiemTongKet, HoTen, TenMH
                       FROM DANGKY INNER JOIN SINHVIEN ON DANGKY.MaSV = SINHVIEN.MaSV 
                       INNER JOIN MONHOC ON DANGKY.MaMH = MONHOC.MaMH 
                       WHERE DANGKY.MaSV = ? AND DANGKY.MaMH = ?
                     """;
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSV);
            ps.setString(2, maMH);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new DangKyView(
                            rs.getString("MaSV"),
                            rs.getString("MaMH"),
                            rs.getDate("NgayDangKy"),
                            rs.getDouble("DiemQuaTrinh"),
                            rs.getDouble("DiemThi"),
                            rs.getDouble("DiemTongKet"),
                            rs.getString("HoTen"),
                            rs.getString("TenMH")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm kiếm chi tiết: " + e.getMessage());
        }
        return null;
    }

    public boolean exists(String maSV, String maMH) {
        String sql = "SELECT * FROM DangKy WHERE MaSV = ? AND MaMH = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maSV);
            ps.setString(2, maMH);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int countDangKy() throws ClassNotFoundException {
   
    String sql = "SELECT COUNT(*) FROM DANGKY"; 
    
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
