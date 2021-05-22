package blog_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main_11438 {
    static int N;
    static List<Integer> tree[];
    static int[] d;
    static int[][] p;
    static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        k = getK();
        tree = new ArrayList[N + 1];
        d = new int[N + 1];
        p = new int[N + 1][k];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N-1; i++) {
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            tree[a].add(b);
            tree[b].add(a);
        }

        d[1] = 1;
        DFS(1);
        fillParent();
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            int lca = getLca(a, b);
            // 출력
            System.out.println(lca);
        }
        br.close();
    }

    private static int getK() {
        k = 0;
        int temp = 1;
        while (temp <= N) {
            temp <<= 1;
            k++;
        }
        return k;
    }

    private static int getLca(int a, int b) {
        // 더 깊은 node를 a로
        if (d[a] < d[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        // 깊이 맞추기
        for (int i = k-1; i >=0; i--) {
            int diff = d[a] - d[b];
            if ((diff & 1<<i) != 0) {
                a = p[a][i];
            }
        }
        if (a==b) return a;
        // 공통 부모 찾기
        for (int i = k-1; i >= 0; i--) {
            if (p[a][i] != p[b][i]) {
                a = p[a][i];
                b = p[b][i];
            }
        }
        return p[a][0];
    }

    private static void fillParent() {
        for (int j = 0; j < k-1; j++)
            for (int i = 1; i <= N; i++)
                p[i][j + 1] = p[p[i][j]][j];
    }

    private static void DFS(int n) {
        for (int child : tree[n]) {
            if (d[child] == 0) {
                p[child][0] = n;
                d[child] = d[n]+1;
                DFS(child);
            }
        }
    }
}