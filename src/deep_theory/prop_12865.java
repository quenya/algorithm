package deep_theory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class prop_12865 {
    static class Mat {
        int w;
        int v;
        public Mat(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }
    static int N;
    static int K;
    static Mat[] map;
    static boolean[] visited;
    static int max_v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        map = new Mat[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            map[i] = new Mat(w, v);
        }
        br.close();

        visited = new boolean[N];
        max_v = 0;
        BT(visited, 0, 0);
        System.out.println(max_v);
    }

    static void BT(boolean[] visited, int w, int v) {
        if (max_v < v)
            max_v = v;
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                Mat mat = map[i];
                if (w+mat.w <= K) {
                    visited[i] = true;
                    BT(visited, w+mat.w, v+mat.v);
                    visited[i] = false;
                }
            }
        }
    }
}
