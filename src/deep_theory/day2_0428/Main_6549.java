package deep_theory.day2_0428;

import java.io.*;
import java.util.StringTokenizer;

// 히스토그램에서 가장 큰 직사각형 - index tree
public class Main_6549 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] arr;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            if (N == 0)
                break;
            arr = new int[N];
            int k = 1;
            while (k <= N) {
                k *= 2;
            }
            k *= 2;
            tree = new int[k];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            initTree(0, N-1, 1);
            bw.write(getMax(0, N-1) + "\n");
        }

        bw.close();
        br.close();
    }

    static void initTree(int start, int end, int node) {
        if (start == end) {
            tree[node] = start;
            return;
        }
        int mid = (start + end) / 2;
        initTree(start, mid, node * 2);
        initTree(mid + 1, end, node * 2 + 1);
        tree[node] = arr[tree[node * 2]] <= arr[tree[node * 2 + 1]] ? tree[node * 2] : tree[node * 2 + 1];
    }

    static long getMax(int left, int right) {
        int m = query(0, N-1, 1, left, right);
        long area = (long) (right - left + 1) * (long) arr[m];

        // left area
        if (left <= m-1) {
            area = Math.max(area, getMax(left, m - 1));
        }
        // right area
        if (m+1 <= right) {
            area = Math.max(area, getMax(m + 1, right));
        }

        return area;
    }

    static int query(int start, int end, int node, int left, int right) {
        if (left > end || right < start) return -1;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        int m1 = query(start, mid, node * 2, left, right);
        int m2 = query(mid + 1, end, node * 2 + 1, left, right);
        if (m1 == -1) return m2;
        else if (m2 == -1) return m1;
        return arr[m1] <= arr[m2] ? m1 : m2;
    }
}
