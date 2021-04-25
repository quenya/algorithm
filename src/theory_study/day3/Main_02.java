package theory_study.day3;

import java.io.*;

// SPY와 조직도
public class Main_02 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] tree;
    static int max_tree = 15;

    public static void main(String[] args) throws IOException {
        tree = new int[]{0, 1004, 1680, 9941, 3367, 3261, 2976, 4889, 0, 0, 1234, 6461, 7329, 5518, 0, 0};
        int N = Integer.parseInt(br.readLine().trim());
        // 탐색
        int i = search(N, 1);
        // 출력
        if (i == 0)
            bw.write("no person\n");
        else {
            // 상사
            bw.write(i == 1 ? "no boss\n" : tree[i / 2] + "\n");

            // 동료
            int company = i % 2 == 0 ? i + 1 : i - 1;
            bw.write(company <= 1 ? "no company\n" : tree[company] + "\n");
            // 부하
            bw.write(i * 2 > max_tree || i * 2 + 1 > max_tree || tree[i*2] == 0 || tree[i*2+1] == 0? "no junior\n" : tree[i * 2] + " " + tree[i * 2 + 1] + "\n");
        }
        bw.close();
        br.close();
    }

    private static int search(int n, int i) {
        if (i > max_tree) return 0;
        if (tree[i] == n) return i;
        int left = search(n, i * 2);
        if (left != 0) return left;
        int right = search(n, i * 2 + 1);
        if (right != 0) return right;
        return 0;
    }
}