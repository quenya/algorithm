package exercise.timeComplex;

import java.util.Scanner;

public class SubSum {
    static int N;
    static int M[];
    static int map[][];
    static int maxSum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = new int[N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = -1;
            }
        }
        for (int i = 0; i < N; i++) {
            M[i] = sc.nextInt();
            map[i][i] = M[i];
        }
        sc.close();

        // init
        maxSum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                sum(i, j);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%2d ", map[i][j]);
            }
            System.out.println();
        }

        System.out.println(maxSum);
    }

    static int sum(int a, int b) {
        if (map[a][b] >= 0) {
            if (maxSum < map[a][b])
                maxSum = map[a][b];
            return map[a][b];
        }

        if (a == b) return map[a][b];

        map[a][b] = map[a][a] + sum(a+1, b);
        if (maxSum < map[a][b])
            maxSum = map[a][b];
        return map[a][b];
    }
}
