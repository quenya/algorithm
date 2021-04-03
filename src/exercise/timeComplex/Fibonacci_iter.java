package exercise.timeComplex;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fibonacci_iter {
    static BigInteger N;
    static List<BigInteger> memo;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextBigInteger();
        memo = new ArrayList<>();
        sc.close();

        memo.add(0, BigInteger.ZERO);
        memo.add(1, BigInteger.ONE);
        System.out.println(fibo(N) % 1_000_000);
    }

    static int fibo(BigInteger n) {
        for (BigInteger bigInteger : memo) {
            if (bigInteger.equals(BigInteger.ZERO) || bigInteger.equals(BigInteger.ONE)) continue;
            memo.add(bigInteger.intValue(), memo.get(bigInteger.min(BigInteger.ONE).intValue()).add(memo.get(bigInteger.min(BigInteger.valueOf(2)).intValue())));
        }

        return memo.get(n.intValue()).intValue();
    }
}
