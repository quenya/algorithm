package theory_study.day5;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 보드 게임 퀴즈 만들기
public class Main_add_01 {
    static class Node implements Comparable<Node> {
        int y;
        int x;
        int dist;
        int value;

        public Node(int y, int x, int d, int v) {
            this.y = y;
            this.x = x;
            this.dist = d;
            this.value = v;
        }

        @Override
        public int compareTo(Node o) {
            int compare = Integer.compare(this.dist, o.dist);
            if (compare != 0) return compare;
            return Integer.compare(o.value, this.value);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int MAX_VALUE = 1023456789;
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
        int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int[][] map = new int[H][W];
            int[][] dist = new int[H][W];
            int[][] count = new int[H][W];
            int sy = 0, sx = 0, ey = 0, ex = 0;
            for (int i = 0; i < H; i++) {
                String trim = br.readLine().trim();
                for (int j = 0; j < W; j++) {
                    char c = trim.charAt(j);
                    switch (c) {
                        case 'S':
                            sy = i;
                            sx = j;
                            map[i][j] = -1;
                            break;
                        case 'E':
                            ey = i;
                            ex = j;
                            map[i][j] = 1;
                            break;
                        case 'R':
                            map[i][j] = 0;
                            break;
                        case '#':
                            map[i][j] = -1;
                            break;
                        default:
                            map[i][j] = 1;
                            break;
                    }
                }
            }

            // init map - dist, value
            for (int i = 0; i < H; i++)
                Arrays.fill(dist[i], MAX_VALUE);
            dist[sy][sx] = 0;
            count[sy][sx] = 1;

            PriorityQueue<Node> pq = new PriorityQueue<>();
            pq.add(new Node(sy, sx, 0, -1));
            while (!pq.isEmpty()) {
                Node now = pq.poll();
                int now_y = now.y;
                int now_x = now.x;
                if (now_y == ey && now_x == ex)
                    break;
                for (int i = 0; i < 8; i++) {
                    int next_y = now_y + dy[i];
                    int next_x = now_x + dx[i];
                    if (rangeCheck(now_y, now_x, next_y, next_x, W, H, map))
                        continue;
                    if (dist[next_y][next_x] > dist[now_y][now_x] + map[next_y][next_x]) {
                        dist[next_y][next_x] = dist[now_y][now_x] + map[next_y][next_x];
                        count[next_y][next_x] = count[now_y][now_x];
                        pq.add(new Node(next_y, next_x, dist[next_y][next_x], map[next_y][next_x]));
                    }
                    else if (dist[next_y][next_x] == dist[now_y][now_x] + map[next_y][next_x]) {
                        count[next_y][next_x] += count[now_y][now_x];
                    }
                    if (count[next_y][next_x] >= 1_000_000_007)
                        count[next_y][next_x] -= 1_000_000_007;
                }
            }

            if (dist[ey][ex] == MAX_VALUE)
                bw.write("#" + tc + " -1\n");
            else
                bw.write("#" + tc + " " + dist[ey][ex] + " " + count[ey][ex] + "\n");
            bw.flush();
        }

        bw.close();
        br.close();
    }

    static boolean rangeCheck(int now_y, int now_x, int next_y, int next_x, int W, int H, int[][] map) {
        // 범위 check
        if (next_x < 0 || next_y < 0 || next_x >= W || next_y >= H)
            return true;
        // 기둥 check
        if (map[next_y][next_x] == -1)
            return true;
        // 찬스 연속 방문 check
        return map[now_y][now_x] == 0 && map[next_y][next_x] == 0;
    }
}
