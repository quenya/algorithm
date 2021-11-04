package boj.problems;

import java.io.*;
import java.util.StringTokenizer;

public class Main_2056 {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/boj/problems/Main_2056.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int ans = 0;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());

            dp[i] = time;
            for (int j = 0; j < count; j++) {
                int next = Integer.parseInt(st.nextToken());
                dp[i] = Math.max(dp[i], dp[next] + time);
            }
            ans = Math.max(ans, dp[i]);
        }

        bw.write(ans + "");
        br.close();
        bw.close();
    }
}
