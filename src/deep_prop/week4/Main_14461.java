package deep_prop.week4;

import java.io.*;
import java.util.*;

public class Main_14461 {
    static class Grass implements Comparable<Grass> {
        int cost;
        int coord;

        public Grass(int c, int coord) {
            this.cost = c;
            this.coord = coord;
        }

        @Override
        public int compareTo(Grass o) {
            int compare = Integer.compare(o.cost, this.cost);
            if (compare != 0) return compare;
            return Integer.compare(o.coord, this.coord);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/deep_prop/week4/Main_14461"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 1023456789;
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], max);
        }
        dist[0][0] = 0;

        int dy[] = {0, 0, 1, -1, 3, 2, 1, 0, -1, -2, -3, -2, -1, 0, 1, 2};
        int dx[] = {1, -1, 0, 0, 0, 1, 2, 3, 2, 1, 0, -1, -2, -3, -2, -1};
        PriorityQueue<Grass> pq = new PriorityQueue<>();
        pq.add(new Grass(0, 0));
        int ans = max;
        while (!pq.isEmpty()) {
            Grass now = pq.poll();
            int cost = -now.cost;
            int y = now.coord / N;
            int x = now.coord % N;
            if (dist[y][x] != cost) continue;
            int toN = (N-y-1) + (N-x-1);
            if (toN <= 2) {
                ans = Math.min(ans, dist[y][x] + T*toN);
            }
            for (int i = 0; i < 16; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];
                if (ny >= 0 && ny < N && nx >= 0 && nx < N) {
                    int nCost = dist[y][x] + 3*T + map[ny][nx];
                    if (dist[ny][nx] > nCost) {
                        dist[ny][nx] = nCost;
                        pq.add(new Grass(-dist[ny][nx], ny*N+nx));
                    }
                }
            }
        }

        bw.write(ans + "");
        bw.close();
        br.close();
    }
}
