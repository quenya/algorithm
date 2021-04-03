package exercise.DP;

import java.util.Scanner;

public class resourceMax {
    static int N;
    static int M;
    static int[][] map;
    static int[][] dp;
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        dp = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j] = map[i][j] = sc.nextInt();
            }
        }
        sc.close();

        dp[0][0] = map[0][0];
        int i=1;
        int j=0;
        while (true){
            int y=j;
            int x = i;
            while (x >= 0 && y <= M-1){
                if (x == 0) dp[x][y] += dp[x][y - 1];
                else if (y == 0) dp[x][y] += dp[x - 1][y];
                else
                    dp[x][y] += Math.max(dp[x][y - 1], dp[x - 1][y]);
                visited[x][y] = true;
                x--;
                y++;
            }

            if (visited[N-1][M-1]) break;

            i++;
            if (i>=N) {
                i=N-1;
            }
            for (j = 0; j < M; j++) {
                if (visited[i][j] == false)
                    break;
            }
        }

        System.out.println(dp[N-1][M-1]);
    }
}
