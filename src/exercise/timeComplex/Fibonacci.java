package exercise.timeComplex;

import java.util.Scanner;

public class Fibonacci {
    static int N;
    static int memo[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        memo = new int[N+1];
        sc.close();

        memo[0] = 0;
        memo[1] = 1;
        System.out.println(fibo(N)%1_000_000);
    }

    static int fibo(int n) {
        if (n > 1 && memo[n] == 0)
            memo[n] = fibo(n-1) + fibo(n-2);
        return memo[n];
    }
}
