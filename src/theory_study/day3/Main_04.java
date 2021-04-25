package theory_study.day3;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

// Bloom
public class Main_04 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int[] dy = {-1, 1, 0, 0};
        int[] dx = {0, 0, -1, 1};

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int height = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int[][] map = new int[height][width];
        LinkedList<int[]> q = new LinkedList<>();
        // first
        st = new StringTokenizer(br.readLine().trim());
        int y1 = Integer.parseInt(st.nextToken());
        int x1 = Integer.parseInt(st.nextToken());
        int[] e1 = {y1, x1};
        q.add(e1);
        map[y1][x1] = 1;
        // second
        st = new StringTokenizer(br.readLine().trim());
        y1 = Integer.parseInt(st.nextToken());
        x1 = Integer.parseInt(st.nextToken());
        int[] e2 = {y1, x1};
        q.add(e2);
        map[y1][x1] = 1;

        int ans = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] poll = q.poll();  // bloom
                // seeding
                for (int j = 0; j < 4; j++) {
                    int ny = poll[0] + dy[j];
                    int nx = poll[1] + dx[j];
                    if (ny>=0&&ny<height&&nx>=0&&nx<width && map[ny][nx] == 0) {
                        int[] co = {ny, nx};
                        q.add(co);
                        map[ny][nx] = 1;
                    }
                }
            }
            ans++;
        }
        bw.write(ans + "");
        bw.close();
        br.close();
    }
}