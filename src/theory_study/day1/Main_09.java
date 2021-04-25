package theory_study.day1;

import java.io.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main_09 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            int[] temp = new int[N+1];
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 1; i <= N; i++) {
                temp[i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine().trim());
            int[] rank = new int[N+1];
            for (int i = 1; i <= N; i++) {
                int card = Integer.parseInt(st.nextToken());
                rank[card] = temp[i];
            }
            boolean result = true;
            for (int i = 1; i < N; i++) {
                if (rank[i] < rank[i+1]) {
                    result = false;
                    break;
                }
            }
            bw.write((result? "YES" : "NO") + "\n");
        }
        br.close();
        bw.close();
    }

}
