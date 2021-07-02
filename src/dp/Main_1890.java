package dp;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1890 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/dp/Main_1890.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[][] a = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                a[i][j] = Integer.parseInt(st.nextToken());
        }
        long[][] dp = new long[N][N];
        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (a[i][j] == 0) continue;
                if (i+ a[i][j]<N) dp[i + a[i][j]][j] += dp[i][j];
                if (j+ a[i][j]<N) dp[i][j + a[i][j]] += dp[i][j];
            }
        }
        bw.write(dp[N-1][N-1] + "");
        bw.close();
        br.close();
    }
}
