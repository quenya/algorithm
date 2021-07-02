package dp;

import java.io.*;
import java.util.StringTokenizer;

public class Main_2098 {
    static int N;
    static int[][] map;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/dp/Main_2098.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dp = new int[N][1<<16];
        long ans = calMin(0, 1);
        bw.write(ans + "");
        bw.close();
        br.close();
    }

    static int calMin(int cur, int status) {
        if (dp[cur][status] != 0) return dp[cur][status];
        if (status == (1<<N)-1) {
            if (map[cur][0] == 0) return 1023456789;
            else return map[cur][0];
        }
        int min = 1023456789;
        for (int i = 1; i < N; i++) {
            if (map[cur][i] != 0 && (status & (1<<i)) == 0) {
                min = Math.min(min, calMin(i, status | (1<<i)) + map[cur][i]);
            }
        }
        return dp[cur][status] = min;
    }
}
