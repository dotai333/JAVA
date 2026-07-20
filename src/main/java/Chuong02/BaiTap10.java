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
public class BaiTap10 {
    public static void main(String[] args) {
        int [] a;
        int n;
         Scanner sc = new Scanner(System.in);
        System.out.println("Cho biet so phan tu (n>0):");
        n= sc.nextInt();
        a = new int[n];
        nhapMang (a);
        int sum = tinhTong(a);
        int lon = max(a);
        int nho = min(a);
        int chan = demChan(a);
        double tb = tinhTB(a);
        System.out.println("==KET QUA==");
        System.out.println("Tong mang: " + sum);
        System.out.println("Gia tri lon nhat: " + lon);
        System.out.println("Gia tri nho nhat: " + nho);
         System.out.println("So chan: " + chan);
            System.out.println("Trinh binh phan tu: " + tb);
    }
    public static void nhapMang(int[] a) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i< a.length; i++)
        {
            System.out.println("Nhap a ["+ i +"]:");
            a[i]=sc.nextInt();
        }
    }
    public static int tinhTong(int[] a) {
      int s=0;
        for (int i = 0; i< a.length; i++)
        {
            s += a[i];
        }
        return s;
    }
     public static int max(int[] a) {
      int max = a[0];
        for (int i = 0; i< a.length; i++)
        {
            if(max <= a[i])
                max = a[i];
        }
        return max;
    }
     public static int min(int[] a) {
      int min = a[0];
        for (int i = 0; i< a.length; i++)
        {
            if(min >= a[i])
                min = a[i];
        }
        return min;
    }
      public static int demChan(int[] a) {
      int dem = 0;
        for (int i = 0; i< a.length; i++)
        {
            if(a[i] % 2 ==0)
                dem++;
        }
        return dem;
    }
       public static double tinhTB(int[] a) {
      int s = 0;
        for (int i = 0; i< a.length; i++)
        {
            s += a[i];
        }
        return (double)s/a.length;
    }
}

