package deep_theory.day2_0428;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2357 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] arr;
    static int[] minTree;
    static int[] maxTree;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[N+1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine().trim());
        }
        minTree = new int[N*4];
        initMinTree(1, N, 1);
        maxTree = new int[N*4];
        initMaxTree(1, N, 1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int min = findMin(1, N, 1, a, b);
            int max = findMax(1, N, 1, a, b);
            bw.write(min + " " + max + "\n");
        }

        bw.close();
        br.close();
    }

    static int initMinTree(int start, int end, int node) {
        if (start == end) {
            return minTree[node] = arr[start];
        }
        int mid = (start + end)/2;
        int left = initMinTree(start, mid, node * 2);
        int right = initMinTree(mid + 1, end, node * 2 + 1);
        return minTree[node] = Math.min(left, right);
    }
    static int initMaxTree(int start, int end, int node) {
        if (start == end) {
            return maxTree[node] = arr[start];
        }
        int mid = (start + end)/2;
        int left = initMaxTree(start, mid, node * 2);
        int right = initMaxTree(mid + 1, end, node * 2 + 1);
        return maxTree[node] = Math.max(left, right);
    }

    static int findMin(int start, int end, int node, int left, int right) {
        if (right < start || end < left)
            return Integer.MAX_VALUE;
        if (left <= start && end <= right)
            return minTree[node];
        int mid = (start + end)/2;
        int leftMin = findMin(start, mid, node * 2, left, right);
        int rightMin = findMin(mid+1, end, node * 2+1, left, right);
        return Math.min(leftMin, rightMin);
    }
    static int findMax(int start, int end, int node, int left, int right) {
        if (right < start || end < left)
            return Integer.MIN_VALUE;
        if (left <= start && end <= right)
            return maxTree[node];
        int mid = (start + end)/2;
        int leftMax = findMax(start, mid, node * 2, left, right);
        int rightMax = findMax(mid+1, end, node * 2+1, left, right);
        return Math.max(leftMax, rightMax);
    }
}
