package blog_study;

import java.io.*;
import java.util.StringTokenizer;

public class Main_1572 {
    static int[] tree;
    static int NN;
    private static int LIMIT = 65537;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/blog_study/Main_1572.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] input = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        NN = 1;
        while (NN < LIMIT) {
            NN *= 2;
        }
        tree = new int[NN * 2];
        for (int i = 1; i < K; i++) {
            updateTree(NN + input[i], 1);
        }
        long sum = 0;
        for (int i = K; i <= N; i++) {
            updateTree(NN + input[i], 1);
            sum += query(1, 1, NN, (K + 1) / 2);
            updateTree(NN + input[i - (K - 1)], -1);
        }

        bw.write(sum + "");
        bw.close();
        br.close();
    }

    private static int query(int node, int l, int r, int target) {
        if (l == r) return l - 1;
        int mid = (l + r) / 2;
        if (target <= tree[2 * node])
            return query(2 * node, l, mid, target);
        else
            return query(2 * node + 1, mid + 1, r, target - tree[2 * node]);
    }

    static void updateTree(int node, int value) {
        tree[node] += value;
        while (node > 1) {
            node /= 2;
            tree[node] = tree[2 * node] + tree[2 * node + 1];
        }
    }
}
