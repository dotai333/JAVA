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
   private String maSV;
    private String maMH;
    private Date ngayDK;
    private double diemQT;
    private double diemThi;
    private double diemTK;

    // Constructor không tham số
    public DangKy() {
    }

    // Constructor đầy đủ tham số
    public DangKy(String maSV, String maMH, Date ngayDK, double diemQT, double diemThi, double diemTK) {
        this.maSV = maSV;
        this.maMH = maMH;
        this.ngayDK = ngayDK;
        this.diemQT = diemQT;
        this.diemThi = diemThi;
        this.diemTK = diemTK;
    }

    // Getter & Setter cho maSV
    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    // Getter & Setter cho maMH
    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    // Getter & Setter cho ngayDK
    public Date getNgayDK() {
        return ngayDK;
    }

    public void setNgayDK(Date ngayDK) {
        this.ngayDK = ngayDK;
    }

    // Getter & Setter cho diemQT
    public double getDiemQT() {
        return diemQT;
    }

    public void setDiemQT(double diemQT) {
        this.diemQT = diemQT;
    }

    // Getter & Setter cho diemThi
    public double getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(double diemThi) {
        this.diemThi = diemThi;
    }

    // Getter & Setter cho diemTK
    public double getDiemTK() {
        return diemTK;
    }

    public void setDiemTK(double diemTK) {
        this.diemTK = diemTK;
    }

    // Phương thức toString phục vụ In / Debug
    @Override
    public String toString() {
        return "DangKy{" +
               "maSV='" + maSV + '\'' +
               ", maMH='" + maMH + '\'' +
               ", ngayDK=" + ngayDK +
               ", diemQT=" + diemQT +
               ", diemThi=" + diemThi +
               ", diemTK=" + diemTK +
               '}';
    }
}
