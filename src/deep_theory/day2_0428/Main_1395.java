package deep_theory.day2_0428;

import java.io.*;
import java.util.StringTokenizer;

// 스위치
public class Main_1395 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] tree;
    static int[] lazy;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        int k = 2 << 17;
        tree = new int[k];
        lazy = new int[k];
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int o = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if (o == 0) {
                update(1, N, 1, s, t);
            } else if (o == 1) {
                int count = query(1, N, 1, s, t);
                bw.write(count + "\n");
            }
        }

        bw.close();
        br.close();
    }

    static int query(int s, int e, int node, int qs, int qe) {
        propagate(node, s, e);
        if (qe < s || e < qs) return 0;
        if (qs<=s && e<=qe) return tree[node];
        int mid = (s + e) / 2;
        int m1 = query(s, mid, node * 2, qs, qe);
        int m2 = query(mid + 1, e, node * 2 + 1, qs, qe);
        return m1 + m2;
    }

    static void update(int s, int e, int node, int us, int ue) {
        propagate(node, s, e);
        if (ue < s || e < us) return;
        if (us<=s&&e<=ue) {
            lazy[node] = (e-s+1) - lazy[node];
            propagate(node, s, e);
            return;
        }
        int m = (s + e)/2;
        update(s, m, node*2, us, ue);
        update(m+1, e, node*2+1, us, ue);
        tree[node] = tree[node*2]+tree[node*2+1];
    }

    static void propagate(int node, int rs, int re) {
        if (lazy[node] == 0) return;
        if (rs != re) {
            int m = (rs + re)/2;
            lazy[node*2] = (m-rs+1) - lazy[node*2];
            lazy[node*2+1] = (re-m) - lazy[node*2+1];
        }
        lazy[node] = 0;
        tree[node] = (re-rs+1) - tree[node];
    }
}
