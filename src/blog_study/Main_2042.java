package blog_study;

import java.io.*;
import java.util.StringTokenizer;

public class Main_2042 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] tree;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int k = 1;
        while (k<N) {
            k<<=1;
        }
        tree = new int[k<<1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        initTree(0, N-1, 0, arr);
        for (int i = 0; i < M+K; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (a == 1) {

            }
            else if (a == 2) {
//                bw.write(s + "\n");
            }
        }


        bw.close();
        br.close();
    }

    static int initTree(int start, int end, int node, int[] arr) {
        if (start == end) return tree[node] = arr[start];
        int mid = (start + end)/2;
        int left = initTree(start, mid, mid*2, arr);
        int right = initTree(mid+1, end, mid*2+1, arr);
        return tree[node] = left + right;
    }

}
