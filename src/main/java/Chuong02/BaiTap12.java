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
public class BaiTap12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Nhap so luong phan tu n: ");
        int n = scanner.nextInt();
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("Phan tu [%d] = ", i);
            arr[i] = scanner.nextInt();
        }
        
        System.out.print("Nhap gia tri can tim: ");
        int x = scanner.nextInt();
        
        int viTri = -1; // Mặc định là -1 (nghĩa là không tìm thấy)
        
        for (int i = 0; i < n; i++) {
            if (arr[i] == x) {
                viTri = i; // Lưu lại vị trí đầu tiên tìm thấy
                break;     // Thoát vòng lặp ngay lập tức
            }
        }
        
        // In kết quả
        if (viTri != -1) {
            System.out.printf("Gia tri %d CÓ xuất hiện trong mảng. Vị trí đầu tiên: %d (chỉ số mảng bắt đầu từ 0)\n", x, viTri);
        } else {
            System.out.printf("Giá trị %d KHÔNG xuất hiện trong mảng.\n", x);
        }
    }
}
