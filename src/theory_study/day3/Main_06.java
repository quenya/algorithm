package theory_study.day3;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 친구 만나러 가는길
public class Main_06 {
    static class Pos {
        int y;
        int x;
        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        int[][] map = new int[3][5];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int y1 = Integer.parseInt(st.nextToken());
        int x1 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        int y2 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());

        int time = 0;
        // cheese
        LinkedList<Pos> q = new LinkedList<>();
        q.add(new Pos(0, 0));
        init(map);
        map[0][0] = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pos pos = q.poll();
                for (int j = 0; j < 4; j++) {
                    int ny = pos.y + dy[j];
                    int nx = pos.x + dx[j];
                    if (ny>=0&&ny<3&&nx>=0&&nx<5 && map[ny][nx] >= 0&& map[ny][nx] > map[pos.y][pos.x] + 1) {
                        q.add(new Pos(ny, nx));
                        map[ny][nx] = map[pos.y][pos.x] + 1;
                    }
                }
            }
        }
        time += map[y1][x1];
        // friend
        q.clear();
        q.add(new Pos(y1, x1));
        init(map);
        map[y1][x1] = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pos pos = q.poll();
                for (int j = 0; j < 4; j++) {
                    int ny = pos.y + dy[j];
                    int nx = pos.x + dx[j];
                    if (ny>=0&&ny<3&&nx>=0&&nx<5 && map[ny][nx] >= 0&& map[ny][nx] > map[pos.y][pos.x] + 1) {
                        q.add(new Pos(ny, nx));
                        map[ny][nx] = map[pos.y][pos.x] + 1;
                    }
                }
            }
        }
        time += map[y2][x2];

        bw.write(time + "\n");
        bw.close();
        br.close();
    }

    private static void init(int[][] map) {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
        }
        map[0][4] = -1;
        map[1][0] = -1;
        map[1][2] = -1;
        map[2][4] = -1;
    }
}