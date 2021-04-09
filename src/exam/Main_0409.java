package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Main_0409 {
    static int N;
    static int dp[];
    static HashMap<Integer, Integer>[] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            String[] s = br.readLine().split(" ");
            N = Integer.parseInt(s[0]);
            int M = Integer.parseInt(s[1]);
            dp = new int[N + 1];
            map = new HashMap[N + 1];
            for (int i = 0; i <= N; i++) {
                map[i] = new HashMap<>();
            }
            for (int i = 0; i < M; i++) {
                s = br.readLine().split(" ");
                int a = Integer.parseInt(s[0]);
                int b = Integer.parseInt(s[1]);
                int c = Integer.parseInt(s[2]);
                map[a].put(b, c);
                map[b].put(a, c);
            }
            DP();
            boolean completed = true;
            int sum = 0;
            for (int i = 2; i <= N; i++) {
                if (dp[i] == 0) {
                    completed = false;
                    break;
                }
                sum += dp[i];
            }
            System.out.println("#" + tc + " " + (completed ? sum : -1));
        }
        br.close();
    }

    static void DP() {
        map[1].forEach((key, value) -> dp[key] = value);
        IntStream.rangeClosed(2, N).forEach(i -> {
            for (int next : map[i].keySet()) {
                if (next == 1) continue;
                int nextVal = map[i].get(next);
                if (dp[i] < nextVal && (dp[next] == 0 || nextVal < dp[next]))
                    dp[next] = nextVal;
            }
        });
    }
}
