package blog_study;

import java.io.*;
import java.util.StringTokenizer;

public class Main_2003 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int s = 0;
        int e = 0;
        int sum = 0;
        int ans = 0;
        while (true) {
            if (sum >= M) sum -= arr[s++];
            else if (e == N) break;
            else sum += arr[e++];
            if (sum == M) ans++;
        }
        bw.write(ans + "");

        bw.close();
        br.close();
    }
}
