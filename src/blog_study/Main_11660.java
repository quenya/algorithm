package blog_study;

import java.io.*;
import java.util.StringTokenizer;

public class Main_11660 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N+1][N+1];
        int[][] sum = new int[N+1][N+1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                sum[i][j] = map[i][j] = Integer.parseInt(st.nextToken());
                if (i > 1)
                    sum[i][j] += sum[i - 1][j];
                if (j > 1)
                    sum[i][j] += sum[i][j - 1];
                if (i > 1 && j > 1)
                    sum[i][j] -= sum[i - 1][j - 1];
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int s = sum[y2][x2];
            if (x1 > 1)
                s -= sum[y2][x1-1];
            if (y1 > 1)
                s -= sum[y1-1][x2];
            if (x1 > 1 && y1 > 1)
                s += sum[y1-1][x1-1];
            bw.write(s + "\n");
        }


        bw.close();
        br.close();
    }
}
