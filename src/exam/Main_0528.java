package exam;

import java.io.*;
import java.util.*;

public class Main_0528 {
    static List<Integer>[] aList;
    static int[] depths;
    static int[] scores;
    static int[][] sparse;
    static long[] sumArr;
    static int MAX_SPARSE = 18;

    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        System.setIn(new FileInputStream("./src/exam/Main_0528.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int Q = Integer.parseInt(st.nextToken());
            scores = new int[N + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) scores[i] = Integer.parseInt(st.nextToken());
            // parent
            aList = new List[N + 1];
            for (int i = 1; i <= N; i++) aList[i] = new ArrayList<>();
            sparse = new int[N + 1][MAX_SPARSE];
            sparse[1][0] = 1;
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int parent = Integer.parseInt(st.nextToken());
                if (parent == 0) continue;
                sparse[i][0] = parent;
                aList[parent].add(i);
            }
            // sparse table
            for (int i = 1; i < MAX_SPARSE; i++)
                for (int j = 1; j <= N; j++)
                    sparse[j][i] = sparse[sparse[j][i - 1]][i - 1];

            // bfs
            depths = new int[N + 1];
            sumArr = new long[N + 1];
            Queue<Integer> q = new LinkedList<>();
            q.add(1);
            depths[1] = 0;
            sumArr[1] = scores[1];
            while (!q.isEmpty()) {
                int now = q.poll();
                int dn = depths[now];
                long sn = sumArr[now];
                for (int child : aList[now]) {
                    depths[child] = dn + 1;
                    sumArr[child] = sn + scores[child];
                    q.add(child);
                }
            }

            // query
            long sumA = 0;
            long sumB = 0;
            for (int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int lca = getLca(a, b);
                sumA += sumArr[a];
                sumB += sumArr[b];
                if (depths[a] >= depths[b]) sumA -= sumArr[lca];
                if (depths[a] <= depths[b]) sumB -= sumArr[lca];
            }
            bw.write("#" + tc + " " + sumA + " " + sumB + "\n");
            bw.flush();
        }
        bw.write("elapsed: " + (System.currentTimeMillis() - start) + "\n");
        bw.close();
        br.close();
    }

    private static int getLca(int a, int b) {
        // a > b
        if (depths[a] < depths[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        // equalize depth
        for (int i = MAX_SPARSE - 1; i >= 0; i--) {
            int diff = depths[a] - depths[b];
            if (diff == 0) break;
            if ((diff & (1 << i)) != 0)
                a = sparse[a][i];
        }
        if (a == b) return a;
        // up to parent
        for (int i = MAX_SPARSE - 1; i >= 0; i--) {
            if (sparse[a][i] != sparse[b][i]) {
                a = sparse[a][i];
                b = sparse[b][i];
            }
        }
        return sparse[a][0];
    }
}
