package theory_study.day1;

import java.io.*;
import java.util.StringTokenizer;

// 미확인 신호
public class Main_12 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            int M = Integer.parseInt(st.nextToken());
            String s = br.readLine();
            boolean[] check = new boolean[256];
            boolean result = true;
            for (int i = 0; i < s.length(); i++) {
                if (check[s.charAt(i)]) {
                    result = false;
                    break;
                }
                check[s.charAt(i)] = true;
                if (i>=M-1) {
                    check[s.charAt(i-(M-1))] = false;
                }
            }
            bw.write("#" + tc + " " + (result?"PASS":"FAIL") + "\n");
        }
        br.close();
        bw.close();
    }

}
