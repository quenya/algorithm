package pre_test;

import java.io.*;
import java.util.StringTokenizer;

public class Main_0625 {
    static int[] tree;
    static int NN;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        int[] students = new int[N + 1];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            students[first] = second - 1;
        }
        NN = 1;
        while (NN <= N) {
            NN *= 2;
        }
        tree = new int[NN * 2];
        for (int i = 0; i < NN *2; i++) {
            tree[i] = Integer.MAX_VALUE;
        }
        int ans = N;
        for (int i = 1; i <= N; i++) {
            insert(students[i]);
            if (i == 1) continue;
            int min = query(1, 0, N, 0, students[i] - 1);
            if (min < students[i]) ans--;
        }
        bw.write(ans + "");
        br.close();
        bw.close();
    }

    static int query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return Integer.MAX_VALUE;
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r) / 2;
        int left = query(node * 2, l, mid, ql, qr);
        int right = query(node * 2 + 1, mid + 1, r, ql, qr);
        return Math.min(left, right);
    }

    static void insert(int node) {
        int n = node + NN;
        tree[n] = node;
        while (n > 1) {
            n /= 2;
            tree[n] = Math.min(tree[n * 2], tree[n * 2 + 1]);
        }
    }
}

