package theory_study.day1;

import java.io.*;
import java.util.StringTokenizer;

public class Main_m2_1 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static long[][] map;
    static int M;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            int N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] buttons = new int[N];
            st = new StringTokenizer(br.readLine().trim());
            for (int i = 0; i < N; i++) {
                int b = Integer.parseInt(st.nextToken());
                buttons[i] = calibrateButton(b);
            }
            map = new long[5][M];
            // 버튼 1회 초기화
            for (int i = 0; i < N; i++) {
                map[1][buttons[i]]++;
            }
            long permutation = getPermutation(4, K);
            bw.write("#" + tc + " " + permutation + "\n");
        }
        br.close();
        bw.close();
    }

    static long getPermutation(int count, int k) {
        if (map[count][k] > 0 || count == 1) return map[count][k];
        long sum = 0;
        for (int i = 0; i < M; i++) {
            int k1 = calibrateButton(k - i);
            sum += getPermutation(count/2, i) * getPermutation(count/2, k1);
        }
        map[count][k] = sum;
        return map[count][k];
    }
    private static int calibrateButton(int button) {
        if (button < 0) {
            if (M < Math.abs(button)) {
                int c = Math.abs(button) / M;
                button += M * c;
            }
            button += M;
        }
        return button % M;
    }

}
