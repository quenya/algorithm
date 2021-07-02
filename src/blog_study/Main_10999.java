package blog_study;

import java.io.*;
import java.util.StringTokenizer;

public class Main_10999 {
    static long[] tree;
    static long[] update;
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
        update = new long[NN << 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
//            update(NN + i, arr[i]);
        }
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a == 1) {
                long c = Long.parseLong(st.nextToken());
//                update(NN + b - 1, c);
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

    static long query(int pos, int l, int r, int from, int to) {
        if (update[pos] != 0) {
            int nodeCnt = r - l + 1;
            tree[pos] += update[pos] * nodeCnt;
            if (pos < NN) {
                update[pos*2] += update[pos];
                update[pos*2+1] = update[pos];
            }
            update[pos] = 0;
        }
        if (l == from && to == r) {
            return tree[pos];
        }
        int mid = (l+r)/2;
        if (to <= mid) {
            return query(pos*2, l, mid, from, to);
        }
        if (from > mid)
            return query(pos*2+1, l, mid+1, from, to);
        return query(pos*2, l, mid, from, mid) + query(pos*2+1, mid+1, r, mid+1, to);
    }
    static void update(int pos, int l, int r, int from , int to, long value) {
        if (l == from && to == r) {
            update[pos] += value;
            return;
        }

        tree[pos] += value * (to - from + 1);

        int mid = (l+r)/2;
        if (to <= mid) {
            update(pos*2, l, mid, from, to, value);
            return;
        }
        if (from > mid) {
            update(pos*2+1, mid+1, r, from, to, value);
            return;
        }

        update(pos*2, l, mid, from, mid, value);
        update(pos*2+1, mid, r, mid+1, to, value);
    }
}
