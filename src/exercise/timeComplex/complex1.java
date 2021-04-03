package exercise.timeComplex;

import java.math.BigInteger;
import java.util.Scanner;

public class complex1 {
    static int N;
    static BigInteger M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextBigInteger();
        sc.close();

        System.out.println((BigInteger.valueOf(N).pow(M.intValue()).mod(BigInteger.valueOf(10007))));
//        System.out.println((int)(Math.pow(N, M)%10_007));
    }
}
