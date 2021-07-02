package dp;

import java.io.*;
import java.util.StringTokenizer;

public class Main_9465 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/dp/Main_9465.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[2][n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                map[0][i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                map[1][i] = Integer.parseInt(st.nextToken());
            }
            int[][] dp = new int[3][n];
            for (int i = 0; i < n; i++) {
                if (i==0) {
                    // 0 - up
                    dp[0][i] = map[0][i];
                    // 1 - down
                    dp[1][i] = map[1][i];
                    // 2 - no touch
                    dp[2][i] = 0;
                }
                else {
                    dp[0][i] = map[0][i] + Math.max(dp[1][i-1], dp[2][i-1]);
                    dp[1][i] = map[1][i] + Math.max(dp[0][i-1], dp[2][i-1]);
                    dp[2][i] = Math.max(Math.max(dp[0][i-1], dp[1][i-1]), dp[2][i-1]);
                }
            }
            bw.write(Math.max(dp[0][n-1], dp[1][n-1])+ "\n");
        }
        bw.close();
        br.close();
    }
}
