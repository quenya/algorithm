package exam;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class pre_vault {
    static int N;
    static int M;
    static int K;
    static int[] buttonArr;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            buttonArr = new int[N];
            st = new StringTokenizer(br.readLine());
            map = new int[5][M];
            for (int i = 0; i < N; i++) {
                int b = Integer.parseInt(st.nextToken());
                buttonArr[i] = b;
            }
            Arrays.sort(buttonArr);
            for (int j = 0; j < N; j++) {
                int button = calibrateButton(buttonArr[j]);
                map[1][button % M]++;
            }
            for (int i = 2; i <= 4; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i - 1][j] > 0) {
                        for (int k = 0; k < N; k++) {
                            int button = buttonArr[k];
                            int cur = calibrateButton(j + button);
                            map[i][cur % M] += map[i - 1][j];
                        }
                    }
                }
            }
            bw.write("#" + tc + " " + map[4][K] + "\n");
        }
        br.close();
        bw.close();
    }

    private static int calibrateButton(int button) {
        if (button < 0) {
            if (M < Math.abs(button)) {
                int c = Math.abs(button) / M;
                button += M * c;
            }
            button += M;
        }
        return button;
    }

}
