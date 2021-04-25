package theory_study.day3;

import java.io.*;
import java.util.StringTokenizer;

// 사랑의 스튜디오
public class Main_01 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        int[] sum = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for (int j = 0; j < N; j++) {
                sum[j] += Integer.parseInt(st.nextToken());
            }
        }
        int min = Integer.MAX_VALUE;
        int min_index = 0;
        int max = Integer.MIN_VALUE;
        int max_index = 0;
        for (int i = 0; i < N; i++) {
            if (min > sum[i]) {
                min = sum[i];
                min_index = i;
            }
            if (max < sum[i]) {
                max = sum[i];
                max_index = i;
            }
        }
        bw.write(max_index + " " + min_index);
        bw.close();
        br.close();
    }
}