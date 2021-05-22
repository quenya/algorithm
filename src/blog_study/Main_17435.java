package blog_study;

import java.io.*;
import java.util.StringTokenizer;

public class Main_17435 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int K = 19;
        int[][] sparse = new int[500001][K];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sparse[i][0] = Integer.parseInt(st.nextToken());
        }
        // sparse table
        for (int j = 1; j < K; j++) {
            for (int i = 1; i <= M; i++) {
                sparse[i][j] = sparse[sparse[i][j - 1]][j - 1];
            }
        }
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            for (int j = K-1; j >= 0; j--) {
                if (n >= 1 << j) {
                    n -= 1<<j;
                    x = sparse[x][j];
                }
            }
            bw.write(x + "\n");
        }

        bw.close();
        br.close();
    }
}
