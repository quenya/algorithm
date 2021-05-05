package deep_theory.day1_0331;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prop_1520 {
    static int M;
    static int N;
    static int[][] map;
    static int[][] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        M = Integer.parseInt(s[0]);
        N = Integer.parseInt(s[1]);
        map = new int[M][N];
        memo = new int[M][N];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                memo[i][j] = -1;
            }
        }
        br.close();
        System.out.println(DFS(0, 0));
    }

    static int DFS(int y, int x) {
        if (y == M - 1 && x == N - 1) return 1;
        if (memo[y][x] == -1) {
            memo[y][x] = 0;
            if (y > 0 && map[y][x] > map[y - 1][x])
                memo[y][x] += DFS(y - 1, x);
            if (y < M - 1 && map[y][x] > map[y + 1][x])
                memo[y][x] += DFS(y + 1, x);
            if (x > 0 && map[y][x] > map[y][x - 1])
                memo[y][x] += DFS(y, x - 1);
            if (x < N - 1 && map[y][x] > map[y][x + 1])
                memo[y][x] += DFS(y, x + 1);
        }
        return memo[y][x];
    }

}
