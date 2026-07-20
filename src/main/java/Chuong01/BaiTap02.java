/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chuong01;
import java.util.Scanner;
/**
 *
 * @author ADMIN
 */
public class BaiTap02 {
    public static void main(String[] args) {
    // khai báo 2 biến để nhận giá trị
    String hoten;
    int tuoi;
    //Tạo luồng đọc giá trị từ bàn phím
    Scanner sc = new Scanner (System.in);
    //Nhận giá trị từ bàn phím
    System.out.print("Cho biết họ tên bạn:");
    hoten = sc.nextLine();
    System.out.print("Cho biết tuổi:");
    tuoi = sc.nextInt ();
    //Xuất kết quả
    System.out.println("Chào bạn: " + hoten + ". Năm nay bạn: " + tuoi + " tuoi ");
}
}
