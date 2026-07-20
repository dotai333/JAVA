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
public class BaiTap09 {
    public static void main(String[] args) {
      int n;
        Scanner sc = new Scanner (System.in);
        System.out.print("Nhap cuu chuong n: ");
        n = sc.nextInt();
        System.out.println("\n BANG CUU CHUONG " + n);
        for (int i = 1; i <=10; i++ )
        {
            System.out.println(n + " x " + i + " = " + (i*n) );
        }
    }
}
