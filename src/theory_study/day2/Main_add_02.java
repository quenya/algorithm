package theory_study.day2;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 개구리 점프 - dp
public class Main_add_02 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] map;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        map = new int[height][width];
        boolean[][] check = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(check[i], true);
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0)
                    check[i][j] = false;
            }
        }

        int dp[][] = new int[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = map[0][0];
        int[] dx = {-1, 0, 1};
        int ans = Integer.MIN_VALUE;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 1; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == 0)
                    continue;
                pq.clear();
                for (int k = 0; k < 3; k++) {
                    int x = j + dx[k];
                    if (x >= 0 && x<width && dp[i - 1][x] != Integer.MAX_VALUE && check[i-1][x] && check[i][j])
                        pq.add(map[i][j] + dp[i - 1][x]);
                }
                dp[i][j] = pq.isEmpty() ? Integer.MAX_VALUE : pq.poll();
                if (i == height - 1 && dp[i][j] != Integer.MAX_VALUE)
                    ans = Math.max(ans, dp[i][j]);
            }
        }
        bw.write(ans + "");
        bw.close();
        br.close();
    }
}