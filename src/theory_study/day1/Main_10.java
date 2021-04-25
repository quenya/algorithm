package theory_study.day1;

import java.io.*;
import java.util.StringTokenizer;

public class Main_10 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine().trim());
            int[] map = new int[N];
            for (int i = 0; i < N; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }
            int ans = Integer.MIN_VALUE;
            int sum = 0;
            int s = 0;
            for (int i = 0; i < N; i++) {
                sum += map[i];
                if (i >= W-1) {
                    if (ans < sum) {
                        ans = sum;
                        s = i - (W-1);
                    }
                    sum -= map[i-(W-1)];
                }
            }
            bw.write("#" + tc + " " + s + " " + (s+W-1) + " " + ans + "\n");
        }
        br.close();
        bw.close();
    }

}
