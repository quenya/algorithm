import java.io.*;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main {
    static int[] parents;
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("./src/blog_study/Main_1717.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parents = new int[N+1];
        IntStream.rangeClosed(0, N).forEach(i -> parents[i] = i);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (type == 0) {
                union(a, b);
            }
            else if (type == 1) {
                if (find(a) == find(b))
                    bw.write("YES\n");
                else
                    bw.write("NO\n");
            }
        }
        br.close();
        bw.close();
    }
    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        parents[py] = px;
    }
}
