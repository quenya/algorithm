package theory_study.day2;

import java.io.*;
import java.util.*;

// 지게차 운전 - dp
public class Main_add_01 {
    static class Road implements Comparable<Road> {
        int y;
        int x;
        int v;
        List<int[]> path;
        public Road(int y, int x, int v, List<int[]> p) {
            this.y = y;
            this.x = x;
            this.v = v;
            this.path = p;
        }

        @Override
        public int compareTo(Road o) {
            int compare = Integer.compare(this.v, o.v);
            if (compare != 0) return compare;
            compare = Integer.compare(o.y, this.y);
            if (compare != 0) return compare;
            compare = Integer.compare(o.x, this.x);
            return compare;
        }
    }
    static int[] dy = {0, 1};
    static int[] dx = {1, 0};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] map;
    static int[][] table;
    static PriorityQueue<Road> pq;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        table = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(table[i], Integer.MAX_VALUE);
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ArrayList<int[]> list = new ArrayList<>();
        int[] start = {0, 0};
        list.add(start);
        pq = new PriorityQueue<>();
        pq.add(new Road(0, 0, map[0][0], list));
        List<int[]> ans = new ArrayList<>();
        while (!pq.isEmpty()) {
            Road road = pq.poll();
            if (table[road.y][road.x] > road.v) {
                table[road.y][road.x] = road.v;
                if (road.y == N-1 && road.x == N-1)
                    ans = road.path;
            }
            for (int i = 0; i < 2; i++) {
                int y = road.y + dy[i];
                int x = road.x + dx[i];
                if (y>=0&&y<N&&x>=0&&x<N && table[y][x] > road.v + map[y][x]) {
                    int[] next = {y, x};
                    ArrayList<int[]> ints = new ArrayList<>(road.path);
                    ints.add(next);
                    pq.add(new Road(y, x, road.v + map[y][x], ints));
                }
            }
        }
        bw.write(table[N-1][N-1] + "\n");
        for (int[] ints : ans) {
            bw.write(ints[0] + "," + ints[1] + "\n");
        }

        bw.close();
        br.close();
    }
}