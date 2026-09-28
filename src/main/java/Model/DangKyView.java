/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author PC_33
 */
public class DangKyView {
    
       private String MaSV;
       private String MaMH;
       private Date NgayDK;
       private double DiemQT;
       private double DiemThi;
       private double DiemTK;
       private String HoTenSV;
       private String TenMH;

    public DangKyView() {
    }

    public DangKyView(String MaSV, String MaMH, Date NgayDK, double DiemQT, double DiemThi, double DiemTK, String HoTenSV, String TenMH) {
        this.MaSV = MaSV;
        this.MaMH = MaMH;
        this.NgayDK = NgayDK;
        this.DiemQT = DiemQT;
        this.DiemThi = DiemThi;
        this.DiemTK = DiemTK;
        this.HoTenSV = HoTenSV;
        this.TenMH = TenMH;
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String MaMH) {
        this.MaMH = MaMH;
    }

    public Date getNgayDK() {
        return NgayDK;
    }

    public void setNgayDK(Date NgayDK) {
        this.NgayDK = NgayDK;
    }

    public double getDiemQT() {
        return DiemQT;
    }

    public void setDiemQT(double DiemQT) {
        this.DiemQT = DiemQT;
    }

    public double getDiemThi() {
        return DiemThi;
    }

    public void setDiemThi(double DiemThi) {
        this.DiemThi = DiemThi;
    }

    public double getDiemTK() {
        return DiemTK;
    }

    public void setDiemTK(double DiemTK) {
        this.DiemTK = DiemTK;
    }

    public String getHoTenSV() {
        return HoTenSV;
    }

    public void setHoTenSV(String HoTenSV) {
        this.HoTenSV = HoTenSV;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String TenMH) {
        this.TenMH = TenMH;
    }

    @Override
    public String toString() {
        return "DangKyView{" + "MaSV=" + MaSV + ", MaMH=" + MaMH + ", NgayDK=" + NgayDK + ", DiemQT=" + DiemQT + ", DiemThi=" + DiemThi + ", DiemTK=" + DiemTK + ", HoTenSV=" + HoTenSV + ", TenMH=" + TenMH + '}';
    }
       
       
}
