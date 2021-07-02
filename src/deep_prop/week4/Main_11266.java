package deep_prop.week4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_11266 {
    static List<List<Integer>> aList;
    static boolean[] cutVertex;
    static int nodeIndex;
    static int[] discover;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/deep_prop/week4/Main_11266.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        aList = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            aList.add(i, new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            aList.get(A).add(B);
            aList.get(B).add(A);
        }
        cutVertex = new boolean[V + 1];
        discover = new int[V+1];
        nodeIndex = 1;
        for (int i = 1; i <= V; i++) {
            if (discover[i] == 0)
                DFS(i, true);
        }

        int count = 0;
        for (int i = 1; i <= V; i++) {
            if (cutVertex[i])
                count++;
        }
        bw.write(count + "\n");
        for (int i = 1; i <= V; i++) {
            if (cutVertex[i])
                bw.write(i + " ");
        }
        bw.close();
        br.close();
    }

    static int DFS(int node, boolean root) {
        discover[node] = nodeIndex++;
        int ret = discover[node];
        int child = 0;
        for (int next : aList.get(node)) {
            if (discover[next] != 0) {
                ret = Math.min(ret, discover[next]);
                continue;
            }
            child++;
            int low = DFS(next, false);
            if (!root && low >= discover[node]) {
                cutVertex[node] = true;
            }
            ret = Math.min(ret, low);
        }
        if (root && child >= 2)
            cutVertex[node] = true;
        return ret;
    }
}

