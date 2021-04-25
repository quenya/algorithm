package theory_study.day3;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 천지창조
public class Main_07 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
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
        char[][] map = new char[8][9];
        int sy = 0, sx = 0;
        for (int i = 0; i < 8; i++) {
            String trim = br.readLine().trim();
            for (int j = 0; j < 9; j++) {
                map[i][j] = trim.charAt(j);
                if (map[i][j] == '#') {
                    sy = i;
                    sx = j;
                }
            }
        }

        // human1 찾기
        boolean[][] check = new boolean[8][9];
        LinkedList<Pos> q = new LinkedList<>();
        LinkedList<Pos> h1 = new LinkedList<>();
        q.add(new Pos(sy, sx));
        h1.add(new Pos(sy, sx));
        while (!q.isEmpty()) {
            Pos pos = q.poll();
            map[pos.y][pos.x] = '_';
            for (int i = 0; i < 4; i++) {
                int ny = pos.y + dy[i];
                int nx = pos.x + dx[i];
                if (ny>=0&&ny<8&&nx>=0&&nx<9) {
                    if (map[ny][nx] == '#') {
                        q.add(new Pos(ny, nx));
                        map[ny][nx] = '_';
                        h1.add(new Pos(ny, nx));
                        check[ny][nx] = true;
                    }
                }
            }
        }
        // human2 찾기
        int dist = 0;
        int ans = Integer.MAX_VALUE;
        while (!h1.isEmpty()) {
            int size = h1.size();
            for (int i = 0; i < size; i++) {
                Pos pos = h1.poll();
                for (int j = 0; j < 4; j++) {
                    int ny = pos.y + dy[j];
                    int nx = pos.x + dx[j];
                    if (ny>=0&&ny<8&&nx>=0&&nx<9 && !check[ny][nx]) {
                        if (map[ny][nx] == '_') {
                            h1.add(new Pos(ny, nx));
                        }
                        else {
                            ans = Math.min(ans, dist);
                        }
                        check[ny][nx] = true;
                    }
                }
            }
            dist++;
        }

        bw.write(ans + "");
        bw.close();
        br.close();
    }
}