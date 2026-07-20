/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chuong02;

import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class BaiTap08 {
    public static void main(String[] args) {
        double toan, ly, hoa;
        double dtb;
        String kq = " ";
        Scanner sc = new Scanner (System.in);
        System.out.print("Nhap diem Toan: ");
        toan = sc.nextDouble();
        System.out.print("Nhap diem Ly: ");
        ly = sc.nextDouble();
        System.out.print("Nhap diem Hoa: ");
        hoa = sc.nextDouble();
        dtb = (toan+ly+hoa)/3;
        if (dtb >=9)
        {
            kq = "Xuat Sac";
        }else if (dtb >=8)
        {
            kq = "Gioi";
        }else if (dtb >=6.5)
        {
            kq = "Kha";
        }else if (dtb >=5)
        {
            kq = "Trung Binh";
        }else 
        {
            kq = "NGU";
        }     
        System.out.println("=================== \n");
        System.out.println("Diem Trung Binh: " + dtb );
        System.out.println("\n Xep loai: " + kq);
    }
}
