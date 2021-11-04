import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        long min;
        long max;
        public Node(long min, long max) {
            this.min = min;
            this.max = max;
        }
    }
    static Node[] tree;
    static int[] numberArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        numberArr = new int[N+1];
        int k = 1;
        while (k < N) {
            k <<=1;
        }
        k<<=1;
        tree = new Node[k];
        for (int i = 0; i < k; i++) {
            tree[i] = new Node(Long.MAX_VALUE, Long.MIN_VALUE);
        }
        for (int i = 1; i <= N; i++) {
            numberArr[i] = Integer.parseInt(br.readLine());
        }
        init(1, N, 1);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            Node query = query(1, N, 1, a, b);
            bw.write(query.min + " " + query.max + "\n");
        }
        br.close();
        bw.close();
    }
    static Node init(int start, int end, int node) {
        if (start == end) return tree[node] = new Node(numberArr[start], numberArr[start]);
        int mid = (start + end)/2;
        Node l = init(start, mid, node * 2);
        Node r = init(mid + 1, end, node * 2 + 1);
        tree[node].min = Math.min(l.min, r.min);
        tree[node].max = Math.max(l.max, r.max);
        return tree[node];
    }
    static Node query(int start, int end, int node, int left, int right) {
        if (right < start || end < left) return new Node(Long.MAX_VALUE, Long.MIN_VALUE);
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end)/2;
        Node l = query(start, mid, node * 2, left, right);
        Node r = query(mid + 1, end, node * 2 + 1, left, right);
        return new Node(Math.min(l.min, r.min), Math.max(l.max, r.max));
    }
}
