package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class pre_vault_2 {
    static int N;
    static int M;
    static int K;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            List<Integer> buttonList = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            map = new int[5][M];
            int[] multi = new int[M];
            for (int i = 0; i < N; i++) {
                int b = Integer.parseInt(st.nextToken());
                b = b < 0 ? calibrateButton(b) : b % M;
                buttonList.add(b);
                multi[b]++;
            }
            buttonList = buttonList.stream().distinct().sorted().collect(Collectors.toList());
            int buttonSize = buttonList.size();
            for (int j = 0; j < buttonSize; j++) {
                int cur = buttonList.get(j);
                map[1][cur] += multi[cur];
            }
            for (int i = 2; i <= 4; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i - 1][j] > 0) {
                        for (int k = 0; k < buttonSize; k++) {
                            int button = buttonList.get(k);
                            int curr = (j + button)%M;
                            map[i][curr] += map[i - 1][j] * multi[button];
                        }
                    }
                }
            }
            System.out.println("#" + tc + " " + map[4][K]);
        }
        br.close();
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
