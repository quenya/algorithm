package theory_study.day2;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 연쇄폭탄
public class Main_08 {
    static class Bomb implements Comparable<Bomb> {
        int num;
        int y;
        int x;
        public Bomb(int n, int y, int x) {
            this.num = n;
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Bomb o) {
            return Integer.compare(this.num, o.num);
        }
    }
    static int[] xIndex = {-1, 1, 0, 0};
    static int[] yIndex = {0, 0, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static PriorityQueue<Bomb> pq;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        int[][] map = new int[N][N];
        pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                pq.add(new Bomb(map[i][j], i, j));
            }
        }

        int last = 0;
        while (!pq.isEmpty()) {
            Bomb poll = pq.poll();
            if (map[poll.y][poll.x] == 0)
                continue;
            last = poll.num;
            for (int i = 0; i < 4; i++) {
                int y = poll.y + yIndex[i];
                int x = poll.x + xIndex[i];
                if (y>=0&&y<N && x>=0&&x<N)
                    map[y][x] = 0;
            }
        }
        bw.write(last + "초");
        bw.close();
        br.close();
    }
}