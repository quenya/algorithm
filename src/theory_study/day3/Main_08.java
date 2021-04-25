package theory_study.day3;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 안나와엘사M
public class Main_08 {
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
        int N = Integer.parseInt(br.readLine().trim());
        char[][] map = new char[N][N];
        int[][] elsaMap = new int[N][N];
        int[][] annaMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            String trim = br.readLine().trim();
            for (int j = 0; j < N; j++) {
                map[i][j] = trim.charAt(j);
                annaMap[i][j] = elsaMap[i][j] = map[i][j] == '#' ? -1 : Integer.MAX_VALUE;
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int elsaY = Integer.parseInt(st.nextToken());
        int elsaX = Integer.parseInt(st.nextToken());
        int annaY = Integer.parseInt(st.nextToken());
        int annaX = Integer.parseInt(st.nextToken());

        // elsa map
        LinkedList<Pos> q = new LinkedList<>();
        q.add(new Pos(elsaY, elsaX));
        elsaMap[elsaY][elsaX] = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pos pos = q.poll();
                for (int j = 0; j < 4; j++) {
                    int ny = pos.y + dy[j];
                    int nx = pos.x + dx[j];
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N && elsaMap[ny][nx] >= 0) {
                        if (elsaMap[ny][nx] > elsaMap[pos.y][pos.x] + 1) {
                            elsaMap[ny][nx] = elsaMap[pos.y][pos.x] + 1;
                            q.add(new Pos(ny, nx));
                        }
                    }
                }
            }
        }
        // anna map
        q.add(new Pos(annaY, annaX));
        annaMap[annaY][annaX] = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pos pos = q.poll();
                for (int j = 0; j < 4; j++) {
                    int ny = pos.y + dy[j];
                    int nx = pos.x + dx[j];
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N && annaMap[ny][nx] >= 0) {
                        if (annaMap[ny][nx] > annaMap[pos.y][pos.x] + 1) {
                            annaMap[ny][nx] = annaMap[pos.y][pos.x] + 1;
                            q.add(new Pos(ny, nx));
                        }
                    }
                }
            }
        }
        // find answer
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int a = elsaMap[i][j];
                if (a == annaMap[i][j] && a > 0) {
                    ans = Math.min(ans, a);
                    continue;
                }
                for (int k = 0; k < 4; k++) {
                    int ny = i + dy[k];
                    int nx = j + dx[k];
                    if (ny >= 0 && ny < N && nx >= 0 && nx < N && annaMap[ny][nx] >= 0) {
                        if (a == annaMap[ny][nx])
                            ans = Math.min(ans, a + 1);
                    }
                }
            }
        }

        bw.write(ans + "");
        bw.close();
        br.close();
    }
}