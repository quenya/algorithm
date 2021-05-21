package mid_problem;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16236 {
    static class Shark {
        int size;
        int move;
        int foods;
        int y;
        int x;

        public Shark(int s, int m, int f, int y, int x) {
            this.size = s;
            this.move = m;
            this.foods = f;
            this.y = y;
            this.x = x;
        }
    }

    static class Pos {
        int y;
        int x;

        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int N;
    static int[][] map;
    static Shark shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Shark(2, 0, 0, i, j);
                    map[i][j] = 0;
                }
            }
        }

        int move = BFS(shark.y, shark.x);
        while (move > 0) {
            shark.move += move;
            shark.foods++;
            if (shark.size == shark.foods) {
                shark.size++;
                shark.foods = 0;
            }
            map[shark.y][shark.x] = 0;
            move = BFS(shark.y, shark.x);
        }
        bw.write(shark.move + "");

        br.close();
        bw.close();
    }

    static int BFS(int y, int x) {
        Queue<Pos> q = new LinkedList<>();
        q.add(new Pos(y, x));
        Pos nextPos = null;
        int[][] moveMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(moveMap[i], Integer.MAX_VALUE);
        }
        moveMap[y][x] = 0;
        while (!q.isEmpty()) {
            Pos now = q.poll();
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];
                if (ny >= 0 && ny < N && nx >= 0 && nx < N && map[ny][nx] <= shark.size && moveMap[ny][nx] > moveMap[now.y][now.x] + 1) {
                    moveMap[ny][nx] = moveMap[now.y][now.x] + 1;
                    if (hasFish(ny, nx) && map[ny][nx] < shark.size) {
                        if (nextPos == null)
                            nextPos = new Pos(ny, nx);
                        else if (betterPos(moveMap, nextPos, ny, nx)) {
                            nextPos.y = ny;
                            nextPos.x = nx;
                        }
                    }
                    q.add(new Pos(ny, nx));
                }
            }
        }
        if (nextPos == null) return 0;
        shark.y = nextPos.y;
        shark.x = nextPos.x;
        return moveMap[nextPos.y][nextPos.x];
    }

    private static boolean betterPos(int[][] moveMap, Pos nextPos, int ny, int nx) {
        // shorter than before
        if (moveMap[nextPos.y][nextPos.x] > moveMap[ny][nx]) return true;
        // longer than before
        if (moveMap[nextPos.y][nextPos.x] < moveMap[ny][nx]) return false;
        // same distance
        // lower than before
        if (ny < nextPos.y) return true;
        // lefter than before?
        return ny == nextPos.y && nx < nextPos.x;
    }

    static boolean hasFish(int y, int x) {
        return map[y][x] >=1 && map[y][x] <=6;
    }
}
