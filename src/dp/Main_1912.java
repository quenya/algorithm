package dp;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;
        int last = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int j = 0; j < N; j++) {
            int num = Integer.parseInt(st.nextToken());
            if (j==0) {
                last = num;
                ans = num;
                continue;
            }
            last = Math.max(num, last + num);
            ans = Math.max(ans, last);
        }
        bw.write(ans + "");
        bw.close();
        br.close();
    }
}
