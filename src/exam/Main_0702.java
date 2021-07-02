package exam;

import java.io.*;
import java.util.StringTokenizer;

public class Main_0702 {
    static int[] minTree;
    static int[] maxTree;
    static int NN = 1;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/exam/Main_0702.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            // init tree
            while (NN <= N)
                NN <<= 1;
            minTree = new int[NN*2];
            maxTree = new int[NN*2];
            // input
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                insert(NN + i, arr[i]);
            }
            // two pointer
            int ans = 0;
            int left = 1;
            int right = 1;
            while (right <= N) {
                int min = min(1, 1, NN, left, right);
                int max = max(1, 1, NN, left, right);
                int diff = max - min;
                if (diff <= K) {
                    ans = Math.max(ans, right-left);
                    right++;
                }
                else left++;
            }
            bw.write("#" + tc + " " + (ans+1) + "\n");
        }
        bw.close();
        br.close();
    }
    static int min(int pos, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return Integer.MAX_VALUE;
        if (ql <= l && r <= qr) return minTree[pos];
        int mid = (l+r)/2;
        return Math.min(min(pos*2, l, mid, ql, qr), min(pos*2+1, mid+1, r, ql, qr));
    }
    static int max(int pos, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return Integer.MIN_VALUE;
        if (ql <= l && r <= qr) return maxTree[pos];
        int mid = (l+r)/2;
        return Math.max(max(pos*2, l, mid, ql, qr), max(pos*2+1, mid+1, r, ql, qr));
    }
    static void insert(int pos, int value) {
        minTree[pos] = value;
        maxTree[pos] = value;
        int bPos = pos;
        while (pos > 1) {
            pos >>= 1;
            minTree[pos] = Math.min(minTree[pos*2], minTree[pos*2+1]);
        }
        pos = bPos;
        while (pos > 1) {
            pos >>= 1;
            maxTree[pos] = Math.max(maxTree[pos*2], maxTree[pos*2+1]);
        }
    }
}

/*
4
10 10
17 25 22 15 18 18 21 12 5 25
10 10
13 17 6 8 7 12 11 9 9 8
10 10
8 12 11 7 5 11 5 13 3 4
10 10
11 10 6 3 2 4 4 2 11 1

#1 7
#2 8
#3 10
#4 10
 */