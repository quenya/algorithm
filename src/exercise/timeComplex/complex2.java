package exercise.timeComplex;

import java.math.BigInteger;
import java.util.Scanner;

public class complex2 {
    static int N;
    static BigInteger M;
    static int map[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextBigInteger();
        map = new int[M.add(BigInteger.ONE).intValue()];
        sc.close();

        map[1] = N;
        System.out.println((int)(pow(M.intValue())%10_007));
    }

    static int pow(int m) {
        if (map[m] > 0) return map[m];
        if (m == 1) return N;

        int mapHalf;
        int halfM = m/2;
        if (map[halfM] > 0)
            mapHalf = map[halfM];
        else
            mapHalf = pow(halfM);
        map[m] = mapHalf * mapHalf;
        if (m%2 != 0)
        {
            map[m] *= N;
        }
        return map[m];
    }}
