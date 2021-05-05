package deep_theory.day3_0512;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11404 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        int M = Integer.parseInt(br.readLine().trim());
        int[][] ans = new int[N + 1][N + 1];
        int maxValue = 1023456789;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                ans[i][j] = maxValue;
            }
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            ans[s][e] = Math.min(ans[s][e], c);
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    ans[i][j] = Math.min(ans[i][j], ans[i][k] + ans[k][j]);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                bw.write(ans[i][j] >= maxValue ? "0 " : ans[i][j] + " ");
            }
            bw.write("\n");
        }

        bw.close();
        br.close();
    }
}
