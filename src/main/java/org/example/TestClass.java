package org.example;

public class TestClass {
    public static void main(String[] args) {
        int p, q;

        System.out.println("P\tQ\tAND\tOR\tXOR\tNOT");

        p = 1;
        q = 1;

        System.out.print(p + "\t" + q + "\t");
        System.out.print((p & q) + "\t" + (p | q) + "\t");
        System.out.println((p ^ q) + "\t" + (1 - p));
        p = 1;
        q = 0;
        System.out.print(p + "\t" + q + "\t");
        System.out.print((p & q) + "\t" + (p | q) + "\t");
        System.out.println((p ^ q) + "\t" + (1 - p));
        p = 0;
        q = 1;
        System.out.print(p + "\t" + q + "\t");
        System.out.print((p & q) + "\t" + (p | q) + "\t");
        System.out.println((p ^ q) + "\t" + (1 - p));
        p = 0;
        q = 0;
        System.out.print(p + "\t" + q + "\t");
        System.out.print((p & q) + "\t" + (p | q) + "\t");
        System.out.println((p ^ q) + " \t" + (1 - p));

        System.out.println("Один" + " Два" + " Три");

        int i = 0;
        int y = 10;
        if (i != 0 && (y / i) == 10) {
            System.out.println("Hy");
        }

        for (int c = 2; c <= 100; c++) {
            System.out.println(c);
        }
    }
}
