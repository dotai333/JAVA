/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Dao.*;

/**
 *
 * @author MSI
 */
public class Khoa {
    private String MaKhoa;
        private String TenKhoa;

    public Khoa() {
    }

    public Khoa(String MaKhoa, String TenKhoa) {
        this.MaKhoa = MaKhoa;
        this.TenKhoa = TenKhoa;
    }

    public String getMaKhoa() {
        return MaKhoa;
    }

    public void setMaKhoa(String MaKhoa) {
        this.MaKhoa = MaKhoa;
    }

    public String getTenKhoa() {
        return TenKhoa;
    }

    public void setTenKhoa(String TenKhoa) {
        this.TenKhoa = TenKhoa;
    }

    @Override
    public String toString() {
        return this.MaKhoa.trim()+"-"+this.TenKhoa ;
    }
        
}
