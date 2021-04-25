package theory_study.day3;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// 폭탄 투하
public class Main_03 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        // init
        char[][] map = new char[4][5];
        for (int i = 0; i < 4; i++)
            Arrays.fill(map[i], '_');
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
        // first
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 8; i++) {
            int y2 = y+dy[i];
            int x2 = x+dx[i];
            if (y2>=0&&y2<4&&x2>=0&&x2<5)
                map[y2][x2] = '#';
        }
        // second
        st = new StringTokenizer(br.readLine().trim());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        for (int i = 0; i < 8; i++) {
            int y2 = y+dy[i];
            int x2 = x+dx[i];
            if (y2>=0&&y2<4&&x2>=0&&x2<5)
                map[y2][x2] = '#';
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                bw.write(map[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.close();
        br.close();
    }
}