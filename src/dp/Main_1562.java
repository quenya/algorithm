package dp;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int mod = 1000000000;
        int[][][] dp = new int[N+1][10][1<<10];
        for (int i = 1; i <= 9; i++) {
            dp[1][i][1<<i] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k <= 1023; k++) {
                    if (j > 0)
                        dp[i+1][j][k | (1<<j)] += dp[i][j - 1][k];
                    if (j<9)
                        dp[i+1][j][k | (1<<j)] += dp[i][j + 1][k];
                    dp[i+1][j][k | (1<<j)] %= mod;
                }
            }
        }
        long ans = 0;
        for (int i = 0; i < 10; i++) {
            ans += dp[N][i][1023];
            ans %= mod;
        }
        bw.write(ans + "");
        bw.close();
        br.close();
    }
}
