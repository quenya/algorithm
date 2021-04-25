package theory_study.day3;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 좀비 바이러스
public class Main_05 {
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

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int[][] map = new int[height][width];
        for (int i = 0; i < height; i++) {
            String trim = br.readLine().trim();
            for (int j = 0; j < width; j++) {
                map[i][j] = trim.charAt(j) - '0';
            }
        }
        st = new StringTokenizer(br.readLine().trim());
        int x = Integer.parseInt(st.nextToken())-1;
        int y = Integer.parseInt(st.nextToken())-1;

        int max = 0;
        LinkedList<Pos> q = new LinkedList<>();
        q.add(new Pos(y, x));
        map[y][x] = 3;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pos pos = q.poll();
                max = Math.max(max, map[pos.y][pos.x]);
                for (int j = 0; j < 4; j++) {
                    int y2 = pos.y + dy[j];
                    int x2 = pos.x + dx[j];
                    if (y2 >= 0 && y2 < height && x2 >= 0 && x2 < width && map[y2][x2] == 1) {
                        q.add(new Pos(y2, x2));
                        map[y2][x2] = map[pos.y][pos.x] + 1;
                        max = Math.max(max, map[y2][x2]);
                    }
                }
            }
        }
        int zombies = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (map[i][j] == 1)
                    zombies++;
            }
        }
        bw.write(max + "\n");
        bw.write(zombies + "");
        bw.close();
        br.close();
    }
}