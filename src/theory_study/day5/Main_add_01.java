package theory_study.day5;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 보드 게임 퀴즈 만들기
public class Main_add_01 {
    static class Node implements Comparable<Node> {
        int y;
        int x;
        int cost;

        public Node(int y, int x, int c) {
            this.y = y;
            this.x = x;
            this.cost = c;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
    static int max_value = 1023456789;
    static int H;
    static int W;
    static char[][] map;
    static int[][] dest;
    static int[][] destMemo;
    static int min_dist;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            int sy = 0, sx = 0, ey = 0, ex = 0;
            for (int i = 0; i < H; i++) {
                String trim = br.readLine().trim();
                for (int j = 0; j < W; j++) {
                    map[i][j] = trim.charAt(j);
                    if (map[i][j] == 'S') {
                        sy = i;
                        sx = j;
                    }
                    if (map[i][j] == 'E') {
                        ey = i;
                        ex = j;
                    }
                }
            }

            int ans = 0;
            min_dist = max_value;
            boolean[][] visited = new boolean[H][W];
            LinkedList<Node> q = new LinkedList<>();
            q.add(new Node(sy, sx, 0));
            visited[sy][sx] = true;
            destMemo = new int[H][W];
            for (int i = 0; i < H; i++) {
                Arrays.fill(destMemo[i], max_value);
            }
            int[][] dijkstra = dijkstra(sy, sx, ey, ex);
            min_dist = dijkstra[ey][ex];

            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (dijkstra[i][j] == min_dist)
                        ans++;
//                    if (dijkstra[i][j] == 0)
//                        ans+=2;
                }
            }
//            while (!q.isEmpty()) {
//                Node now = q.poll();
//                int now_y = now.y;
//                int now_x = now.x;
//                int cost = dijkstra(now.y, now.x, ey, ex);
//                if (min_dist > cost+now.cost) {
//                    min_dist = cost+now.cost;
//                    ans = 1;
//                } else if (min_dist == cost+now.cost) {
//                    ans++;
//                }
//                else
//                    continue;
//                // 1번씩 이동
//                for (int i = 0; i < 8; i++) {
//                    int next_y = now_y + dy[i];
//                    int next_x = now_x + dx[i];
//                    // 범위 check
//                    if (rangeCheck(now_y, now_x, next_y, next_x))
//                        continue;
//                    // 재방문 check
//                    if (visited[next_y][next_x])
//                        continue;
//                    // 목적지 check
//                    if (next_y == ey && next_x == ex)
//                        continue;
//                    int c = now.cost;
//                    if (map[next_y][next_x] != 'R')
//                        c++;
////                    bw.write(now_y + " " + now_x + " " + next_y + " " + next_x + " " + c + "\n");
//                    q.add(new Node(next_y, next_x, c));
//                    visited[next_y][next_x] = true;
//                }
//            }

            if (min_dist == max_value)
                bw.write("#" + tc + " -1\n");
            else
                bw.write("#" + tc + " " + min_dist + " " + (ans%1_000_000_007) + "\n");
            bw.flush();
        }

        bw.close();
        br.close();
    }

    static int[][] dijkstra(int sy, int sx, int ey, int ex) throws IOException {
//        if (destMemo[sy][sx] != max_value)
//            return destMemo[sy][sx];
        dest = new int[H][W];
        for (int i = 0; i < H; i++) {
            Arrays.fill(dest[i], max_value);
        }
        dest[sy][sx] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(sy, sx, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int now_y = now.y;
            int now_x = now.x;
            for (int i = 0; i < 8; i++) {
                int next_y = now_y + dy[i];
                int next_x = now_x + dx[i];
                if (rangeCheck(now_y, now_x, next_y, next_x))
                    continue;
                if (map[next_y][next_x] == 'R') {
                    if (dest[next_y][next_x] > dest[now_y][now_x]) {
                        dest[next_y][next_x] = dest[now_y][now_x];
                        pq.add(new Node(next_y, next_x, dest[next_y][next_x]));
                    }
                } else {
                    if (dest[next_y][next_x] > dest[now_y][now_x] + 1) {
                        dest[next_y][next_x] = dest[now_y][now_x] + 1;
                        pq.add(new Node(next_y, next_x, dest[next_y][next_x]));
                    }
                }
            }
        }

//        return destMemo[sy][sx] = dest[ey][ex];
        return dest;
    }

    private static boolean rangeCheck(int now_y, int now_x, int next_y, int next_x) {
        // 범위 check
        if (next_x < 0 || next_y < 0 || next_x >= W || next_y >= H)
            return true;
        // 기둥 check
        if (map[next_y][next_x] == '#')
            return true;
        // 찬스 연속 방문 check
        return map[now_y][now_x] == 'R' && map[next_y][next_x] == 'R';
    }
}
