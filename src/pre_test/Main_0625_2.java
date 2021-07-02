package pre_test;

import java.io.*;
import java.util.*;

public class Main_0625_2 {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("./src/pre_test/Main_0625_2.txt"));
//        System.setIn(new FileInputStream("./src/pre_test/Main_0625_2_2.txt"));
//        System.setIn(new FileInputStream("./src/pre_test/Main_0625_2_3.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            char[][] map = new char[N][M];
            int[] start = new int[2];
            int[] end = new int[2];
            List<int[]> ghostList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'S') {
                        map[i][j] = '.';
                        start = new int[]{i, j};
                    }
                    if (map[i][j] == 'C') {
                        map[i][j] = '.';
                        ghostList.add(new int[]{i, j});
                    }
                    if (map[i][j] == 'E') {
                        end = new int[]{i, j};
                    }
                }
            }
            Queue<int[]> q = new LinkedList<>();
            q.add(start);
            int[][] me = new int[N][M];
            boolean[][] visit = new boolean[N][M];
            visit[start[0]][start[1]] = true;
            while (!q.isEmpty()) {
                int[] now = q.poll();
                for (int i = 0; i < 4; i++) {
                    int ny = now[0] + dy[i];
                    int nx = now[1] + dx[i];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                    if (visit[ny][nx]) continue;
                    if (map[ny][nx] == '#') {
                        me[ny][nx] = -1;
                        continue;
                    }
                    visit[ny][nx] = true;
                    me[ny][nx] = me[now[0]][now[1]] + 1;
                    if (map[ny][nx] == 'E') continue;
                    q.add(new int[]{ny, nx});
                }
            }
            // shortest path
            List<int[]> path = new ArrayList<>();
            int y = end[0];
            int x = end[1];
            q.add(end);
            while (true) {
                int[] now = q.poll();
                int curr = me[now[0]][now[1]];
                path.add(now);
                if (curr == 0) break;
                for (int i = 0; i < 4; i++) {
                    int ny = now[0] + dy[i];
                    int nx = now[1] + dx[i];
                    if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                    if (me[ny][nx] == curr-1) {
                        q.add(new int[] {ny, nx});
                        break;
                    }
                }
            }
            boolean same = false;
            int[][] gMap;
            for (int[] ghost : ghostList) {
                gMap = new int[N][M];
                visit = new boolean[N][M];
                q.add(ghost);
                visit[ghost[0]][ghost[1]] = true;
                while (!q.isEmpty()) {
                    int[] now = q.poll();
                    for (int i = 0; i < 4; i++) {
                        int ny = now[0] + dy[i];
                        int nx = now[1] + dx[i];
                        if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
                        if (visit[ny][nx]) continue;
                        if (map[ny][nx] == '#') {
                            gMap[ny][nx] = -1;
                            continue;
                        }
                        visit[ny][nx] = true;
                        gMap[ny][nx] = gMap[now[0]][now[1]] + 1;
                        if (map[ny][nx] == 'E') continue;
                        q.add(new int[]{ny, nx});
                    }
                }
                for (int[] pos : path) {
                    if (me[pos[0]][pos[1]] == gMap[pos[0]][pos[1]]) {
                        same = true;
                        break;
                    }
                }
            }
            if (same || me[end[0]][end[1]] == 0)
                bw.write("#" + tc + " " + "-1\n");
            else
                bw.write("#" + tc + " " + me[end[0]][end[1]] + "\n");
        }
        br.close();
        bw.close();
    }
}

