package exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class pre_vault_3 {
    static class Button implements Comparable<Button> {
        int n;
        int multi;

        public Button(int n, int m) {
            this.n = n;
            this.multi = m;
        }

        public int getN() {
            return this.n;
        }

        @Override
        public int compareTo(Button o) {
            return Integer.compare(this.n, o.n);
        }
    }

    static int N;
    static int M;
    static int K;
    static int[] buttonArr;
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
            buttonArr = new int[N];
            st = new StringTokenizer(br.readLine());
            map = new int[5][M];
            for (int i = 0; i < N; i++) {
                int b = Integer.parseInt(st.nextToken());
                buttonArr[i] = b;
            }
            Arrays.sort(buttonArr);
            IntStream.range(0, N).map(j -> calibrateButton(buttonArr[j])).forEach(button -> map[1][button]++);
            List<Button> buttonList = IntStream.range(0, M)
                    .filter(i -> map[1][i] > 0)
                    .mapToObj(i -> new Button(i, map[1][i]))
                    .sorted()
                    .collect(Collectors.toList());
            Set<Integer> visitList = buttonList.stream().map(Button::getN).collect(Collectors.toSet());
            Set<Integer> visitList2 = new HashSet<>();
            int i = 2;
            for (Button button : buttonList) {
                for (int j : visitList) {
                    if (map[i - 1][j] > 0) {
                        int cur = (j + button.n) % M;
                        map[i][cur] += map[i - 1][j] * button.multi;
                        visitList2.add(cur);
                    }
                }
            }
            i=3;
            visitList.clear();
            for (Button button : buttonList) {
                for (int j : visitList2) {
                    if (map[i - 1][j] > 0) {
                        int cur = (j + button.n) % M;
                        map[i][cur] += map[i - 1][j] * button.multi;
                        visitList.add(cur);
                    }
                }
            }
            i=4;
            for (Button button : buttonList) {
                for (int j : visitList) {
                    if (map[i - 1][j] > 0) {
                        int cur = (j + button.n) % M;
                        map[i][cur] += map[i - 1][j] * button.multi;
                    }
                }
            }
            System.out.println("#" + tc + " " + map[4][K]);
            for (int j = 1; j <= 4; j++) {
                for (int k = 0; k < M; k++) {
                    System.out.printf("%d ", map[j][k]);
                }
                System.out.println();
            }
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
        return button % M;
    }

}
