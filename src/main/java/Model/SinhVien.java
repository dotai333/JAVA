package Model;

import Dao.*;
import java.util.Date;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author MSI
 */
public class SinhVien {

    private String maSV;
    private String hoTen;
    private Date NgaySinh;
    private boolean gioiTinh;
    private String diaChi;
    private String maKhoa;

    public SinhVien(String maSV) {
        this.maSV = maSV;
    }

    public SinhVien(String maSV, String hoTen, Date NgaySinh, boolean gioiTinh, String diaChi, String maKhoa) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.NgaySinh = NgaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.maKhoa = maKhoa;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    @Override
    public String toString() {
       return this.maSV.trim()+"-"+this.hoTen ;
    }

}
