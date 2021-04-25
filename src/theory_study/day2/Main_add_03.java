package theory_study.day2;

import java.io.*;
import java.util.StringTokenizer;

// 빌딩은 나의 것 - Kadane 알고리즘
public class Main_add_03 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int[] build = new int[n];
        for (int i = 0; i < n; i++) {
            build[i] = Integer.parseInt(st.nextToken());
        }
        int ans = Integer.MIN_VALUE;
        int last_sum = 0;
        int s = 0;
        int e = 0;
        int last_s = 0;
        int last_e = 0;
        for (int i = 0; i < n; i++) {
            if (last_sum + build[i] > build[i]) {
                last_sum = last_sum + build[i];
                last_e = i;
            }
            else {
                last_sum = build[i];
                last_s = last_e = i;
            }
            if (ans < last_sum) {
                s = last_s;
                e = last_e;
                ans = last_sum;
            }
        }
        bw.write(ans + "\n");
        bw.write(s + " " + e + "\n");
        bw.close();
        br.close();
    }
}
