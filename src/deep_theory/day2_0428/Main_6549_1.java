package deep_theory.day2_0428;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main_6549_1 {
    static int[] arr;
    static int n;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        while (true) {
            String s = br.readLine();
            if (s.equals("0"))
                break;
            st = new StringTokenizer(s, " ");
            n = Integer.parseInt(st.nextToken());

            arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = Integer.parseInt(st.nextToken());

            tree = new int[(1 << (int) Math.ceil(Math.log10(n) / Math.log10(2)) + 1)];

            init(arr, 1, 0, n - 1);
            System.out.println(getMax(0, n - 1));
        }
    }

    private static void init(int[] arr, int node, int start, int end) {
        // 인덱스를 써넣는다.
        if (start == end)
            tree[node] = start;
        else {
            int mid = (start + end) / 2;
            init(arr, node * 2, start, mid);
            init(arr, node * 2 + 1, mid + 1, end);

            // 두 자식중 더 작은 값을 부모노드에 쓴다.
            tree[node] = arr[tree[node * 2]] <= arr[tree[node * 2 + 1]] ? tree[node * 2] : tree[node * 2 + 1];
        }
    }

    private static long getMax(int left, int right) {
        int n = arr.length;
        int m = query(1, 0, n - 1, left, right);    // 최솟값 index구하기

        // 넓이 계산
        long area = (long) (right - left + 1) * (long) arr[m];

        // 왼쪽에 막대가 있나??
        if (left <= m - 1)
            area = Math.max(area, getMax(left, m - 1));

        // 오른쪽에 막대가 있나?
        if (m + 1 <= right)
            area = Math.max(area, getMax(m + 1, right));

        // 최대 넓이 반환
        return area;
    }

    private static int query(int node, int start, int end, int left, int right) {
        if (left > end || right < start) return -1;
        if (left <= start && end <= right) return tree[node];    // 그 범위를 감싸면 값 반환

        // 반으로 나눠서 탐색
        int mid = (start + end) / 2;
        int m1 = query(node * 2, start, mid, left, right);
        int m2 = query(node * 2 + 1, mid + 1, end, left, right);

        // 둘 중에 더 작은 값은 갖는 index 반환
        if (m1 == -1) return m2;
        else
            if (m2 == -1) return m1;
        return arr[m1] <= arr[m2] ? m1 : m2;
    }
}