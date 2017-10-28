package com.company;

public class Scanner {
    public static int ShowScanner() {
        java.util.Scanner scan = new java.util.Scanner(System.in);
        String number = scan.nextLine();
         int y = Integer.valueOf(number);
        return y;
    }
}
