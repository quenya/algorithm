package exercise.timeComplex;

import java.util.Scanner;

public class SubSumDP {
    static int N;
    static int M[];
    static int map[][];
    static int maxSum;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = new int[N];
        map = new int[N][N];
        maxSum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = -9999;
            }
        }
        for (int i = 0; i < N; i++) {
            M[i] = sc.nextInt();
            map[i][i] = M[i];
            if (maxSum < M[i])
                maxSum = M[i];
        }
        sc.close();

        // init
        for (int y = 1; y < N; y++) {
            for (int x = 0; x < N - y; x++) {
                map[x][y+x] = map[x][y+x-1] + map[x+1][y+x];
                if (map[x+1][y+x-1] > -9999)
                    map[x][y+x] -= map[x+1][y+x-1];
                if (maxSum < map[x][y+x])
                    maxSum = map[x][y+x];
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%5d ", map[i][j]);
            }
            System.out.println();
        }

        System.out.println(maxSum);
    }

}
