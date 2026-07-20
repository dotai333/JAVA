/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Chuong02;

import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author ADMIN
 */
public class BaiTap11 {
    public static void main(String[] args) {
        int [] a;
        int n;
         Scanner sc = new Scanner(System.in);
        System.out.print("Cho biet so phan tu (n>0):");
        n= sc.nextInt();
        a = new int[n];
        for (int i = 0; i< a.length; i++)
        {
            System.out.print("Nhap a ["+ i +"]:");
            a[i]=sc.nextInt();
        }
            Arrays.sort(a);
      
        System.out.print("\nMang sau khi sap xep la");
        for (int x : a) {
            System.out.print(x + " ");
        }    
    }
}
