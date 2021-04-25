package theory_study.day4;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

// binary search
public class Main_01 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] num;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        num = new int[N];
        for (int i = 0; i < N; i++)
            num[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(num);
        int K = Integer.parseInt(br.readLine().trim());
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < K; i++) {
            int n = Integer.parseInt(st.nextToken());
            // binary search
            int s = 0;
            int e = N - 1;
            int index = -1;
            while (s <= e) {
                int mid = (s + e) / 2;
                if (num[mid] == n)
                    index = mid;
                if (num[mid] > n)
                    e = mid - 1;
                else
                    s = mid + 1;
            }
            bw.write((index < 0 ? "X" : "O") + "");
        }
        bw.close();
        br.close();
    }
}
