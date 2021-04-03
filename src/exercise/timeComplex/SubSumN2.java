package exercise.timeComplex;

import java.util.Scanner;

public class SubSumN2 {
    static int N;
    static int M[];
    static int maxSum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = new int[N];
        maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            M[i] = sc.nextInt();
            if (maxSum < M[i])
                maxSum = M[i];
        }
        sc.close();

        // init
        int lineSum[] = new int[N];
        lineSum[0] = M[0];
        for (int i = 1; i < N; i++)
            lineSum[i] = lineSum[i-1] + M[i];

        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                int curSum = lineSum[j];
                if (i != 0)
                    curSum -= lineSum[i-1];
                if (maxSum < curSum)
                    maxSum = curSum;
            }
        }

        System.out.println(maxSum);
    }

}
