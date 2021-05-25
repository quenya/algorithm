package blog_study;

import java.io.*;
import java.util.StringTokenizer;

public class Main_2042 {
    static long[] tree;
    static int NN = 1;

    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("./src/blog_study/Main_2042.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        while (NN < N) {
            NN <<= 1;
        }
        tree = new long[NN << 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            updateTree(NN + i, arr[i]);
        }
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
                updateTree(NN + b - 1, c);
            } else if (a == 2) {
                int c = Integer.parseInt(st.nextToken());
                long query = query(1, 1, NN, b, c);
                bw.write(query + "\n");
                bw.flush();
            }
        }

        bw.close();
        br.close();
    }

    static void updateTree(int node, long value) {
        tree[node] = value;
        while (node > 1) {
            node /= 2;
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    static long query(int node, int l, int r, int ql, int qr) {
        if (r < ql || qr < l) return 0;
        if (ql <= l && r <= qr) return tree[node];
        int mid = (l + r) / 2;
        return query(node * 2, l, mid, ql, qr) + query(node * 2 + 1, mid + 1, r, ql, qr);
    }
}
