package deep_theory.day1_0331;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_5569 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int MOD = 100000;
    static int dp[][][][];
    static int N, M;

    static int func(int x, int y, int t, int chk) {
        if (x <= 0 || y <= 0 || x > N || y > M)
            return 0;

        if (x == N && y == M)
            return dp[x][y][t][chk] = 1;

        if (dp[x][y][t][chk] != -1)
            return dp[x][y][t][chk];

        dp[x][y][t][chk] = 0;
        if (t == 1) {
            if (chk == 1) {
                dp[x][y][t][chk] = (dp[x][y][t][chk] + func(x, y + 1, t, chk) + func(x + 1, y, 1 - t, 1 - chk)) % MOD;
            } else {
                dp[x][y][t][chk] = (dp[x][y][t][chk] + func(x, y + 1, t, 1 - chk)) % MOD;
            }
        } else {
            if (chk == 1) {
                dp[x][y][t][chk] = (dp[x][y][t][chk] + func(x + 1, y, t, chk) + func(x, y + 1, 1 - t, 1 - chk)) % MOD;
            } else {
                dp[x][y][t][chk] = (dp[x][y][t][chk] + func(x + 1, y, t, 1 - chk)) % MOD;
            }
        }

        return dp[x][y][t][chk];
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][M + 1][2][2];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                Arrays.fill(dp[i][j][0], -1);
                Arrays.fill(dp[i][j][1], -1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        System.out.println((func(1, 1, 0, 0) + func(1, 1, 1, 0)) % MOD);
    }
}
