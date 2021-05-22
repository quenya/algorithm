package blog_study;

import java.io.*;
import java.util.StringTokenizer;

public class Main_2096 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] minArr = new int[3];
        int[] maxArr = new int[3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int min01 = Math.min(minArr[0], minArr[1]);
            int min12 = Math.min(minArr[1], minArr[2]);
            int min012 = Math.min(min01, min12);
            minArr[0] = a + min01;
            minArr[1] = b + min012;
            minArr[2] = c + min12;
            int max01 = Math.max(maxArr[0], maxArr[1]);
            int max12 = Math.max(maxArr[1], maxArr[2]);
            int max012 = Math.max(max01, max12);
            maxArr[0] = a + max01;
            maxArr[1] = b + max012;
            maxArr[2] = c + max12;
        }
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < 3; i++) {
            min = Math.min(min, minArr[i]);
            max = Math.max(max, maxArr[i]);
        }
        bw.write(max + " " + min);

        bw.close();
        br.close();
    }
}
