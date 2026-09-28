package Dao;

import Model.TaiKhoan;
import Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    // 1. Kiểm tra đăng nhập
    public TaiKhoan checkLogin(String username, String password) {
        String sql = "SELECT TenDangNhap, MatKhau, HoTen, ChucVu FROM TAIKHOAN WHERE TenDangNhap = ? AND MatKhau = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, username != null ? username.trim() : "");
            ps.setString(2, password != null ? password.trim() : "");

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TaiKhoan tk = new TaiKhoan();
                    tk.setTenDangNhap(rs.getString("TenDangNhap"));
                    tk.setMatKhau(rs.getString("MatKhau"));
                    tk.setHoTen(rs.getString("HoTen"));       
                    tk.setChucVu(rs.getString("ChucVu"));      
                    return tk;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return null; 
    }

  
    public List<TaiKhoan> getAllAccounts() {
        List<TaiKhoan> list = new ArrayList<>();
        String sql = "SELECT TenDangNhap, MatKhau, HoTen, ChucVu FROM TAIKHOAN";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setHoTen(rs.getString("HoTen"));
                tk.setChucVu(rs.getString("ChucVu"));
                list.add(tk);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public void deleteAccount(String tenDangNhap) {
        String sql = "DELETE FROM TAIKHOAN WHERE TenDangNhap = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, tenDangNhap);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   
    public void insertAccount(TaiKhoan tk) {
        String sql = "INSERT INTO TAIKHOAN (TenDangNhap, MatKhau, HoTen, ChucVu) VALUES (?, ?, ?, ?)";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, tk.getTenDangNhap());
            ps.setString(2, tk.getMatKhau());
            ps.setString(3, tk.getHoTen());
            ps.setString(4, tk.getChucVu());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean updatePassword(String tenDangNhap, String matKhauMoi) {
        String sql = "UPDATE TAIKHOAN SET MatKhau = ? WHERE TenDangNhap = ?";

        try (
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, matKhauMoi != null ? matKhauMoi.trim() : "");
            ps.setString(2, tenDangNhap);
            
            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}