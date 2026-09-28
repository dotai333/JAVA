/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Dao.*;
import java.util.Date;

/**
 *
 * @author MSI
 */
public class DangKy {
    private String MaSVDK;
     private String MaMHDK;
      private Date NgayDK;
       private double DiemQT;
       private double DiemThi;
       private double DiemTK;

    public DangKy() {
    }

    public DangKy(String MaSVDK, String MaMHDK, Date NgayDK, double DiemQT, double DiemThi, double DiemTK) {
        this.MaSVDK = MaSVDK;
        this.MaMHDK = MaMHDK;
        this.NgayDK = NgayDK;
        this.DiemQT = DiemQT;
        this.DiemThi = DiemThi;
        this.DiemTK = DiemTK;
    }

    public String getMaSVDK() {
        return MaSVDK;
    }

    public void setMaSVDK(String MaSVDK) {
        this.MaSVDK = MaSVDK;
    }

    public String getMaMHDK() {
        return MaMHDK;
    }

    public void setMaMHDK(String MaMHDK) {
        this.MaMHDK = MaMHDK;
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

    @Override
    public String toString() {
        return "DangKy{" + "MaSVDK=" + MaSVDK + ", MaMHDK=" + MaMHDK + ", NgayDK=" + NgayDK + ", DiemQT=" + DiemQT + ", DiemThi=" + DiemThi + ", DiemTK=" + DiemTK + '}';
    }

   
}
