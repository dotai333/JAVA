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
public class BaiTap06 {
    public static void main(String[] args) {
        double so;
        Scanner sc = new Scanner (System.in);
        System.out.print("Nhap so de kiem tra: ");
        so = sc.nextDouble();
        if (so%2==0)
        {
            System.out.print(so + " la so chan");
        }
        else
        {
            System.out.print(so + " la so le");
        }
    }
            
}
