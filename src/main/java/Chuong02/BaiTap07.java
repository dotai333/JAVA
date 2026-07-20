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
public class BaiTap07 {
    public static void main(String[] args) {
        //Giải phương trình bậc nhất aX + b = 0
        double a,b;
        Scanner sc = new Scanner (System.in);
        System.out.print("Nhap so a: ");
        a = sc.nextDouble();
        System.out.print("Nhap so b: ");
        b = sc.nextDouble();
        System.out.println("X " + "= " + -b/a);
    }
}
