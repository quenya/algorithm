package theory_study.day5;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 무서운 시어머니
public class Main_02 {
    static class Node implements Comparable<Node> {
        int y;
        int x;
        int fatique;

        public Node(int y, int x, int f) {
            this.y = y;
            this.x = x;
            this.fatique = f;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.fatique, o.fatique);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] map;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static int max_value = 1023456789;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        int[][] dest = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dest[i][j] = map[i][j] == -1? -1 : max_value;
            }
        }
        dest[Y][X] = map[Y][X];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(Y, X, map[Y][X]));
        int ans = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            ans = Math.max(ans, now.fatique);
            for (int i = 0; i < 4; i++) {
                int ny = now.y+dy[i];
                int nx = now.x+dx[i];
                if (ny<0 || nx<0 || ny>=N || nx>=N)
                    continue;
                if (map[ny][nx] == -1)
                    continue;
                if (dest[ny][nx] > dest[now.y][now.x] + map[ny][nx]) {
                    dest[ny][nx] = dest[now.y][now.x] + map[ny][nx];
                    pq.add(new Node(ny, nx, dest[ny][nx]));
                }
            }
        }

        bw.write(ans + "");
        bw.close();
        br.close();
    }
}
