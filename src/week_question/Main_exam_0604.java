import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main_0604 {
    static class Node {
        int num;
        long cost;

        public Node(int n, long c) {
            this.num = n;
            this.cost = c;
        }
    }

    static long[] cost;
    static List<Integer>[] aList;
    static Vector<Node>[] treeList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/exam/Input_Pro_210604.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost = new long[N + 1];
            for (int i = 1; i <= N; i++) {
                cost[i] = Long.parseLong(st.nextToken());
            }
            int root = 0;
            aList = new List[N + 1];
            IntStream.rangeClosed(1, N).forEach(i -> aList[i] = new ArrayList<>());
            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                aList[u].add(v);
                aList[v].add(u);
                if (aList[u].size() == 3) root = u;
                if (aList[v].size() == 3) root = v;
            }
            // bfs
            treeList = new Vector[N];
            IntStream.rangeClosed(0, N - 1).forEach(i -> treeList[i] = new Vector<>());
            long[] basicSum = new long[3];
            for (int i = 0; i < 3; i++) {
                basicSum[i] = bfs(aList[root].get(i), i, root);
            }
            long[] treeSum = new long[3];
            for (int i = 0; i < 3; i++) {
                treeSum[i] = treeList[i].lastElement().cost;
            }
            // check
            long ans = basicSum[0] + basicSum[1] + basicSum[2];
            long[] dp = new long[N + 1];
            for (int i = 0; i < 3; i++) {
                Arrays.fill(dp, 0);
                dp[0] = basicSum[0] + basicSum[1] + basicSum[2];
                long anotherTreeSum = getAnotherTreeSum(i, treeSum);
                for (int j = 0; j < treeList[i].size(); j++) {
                    Node node = treeList[i].get(j);
                    long plus = anotherTreeSum + cost[root] + node.cost - cost[node.num];
                    long minus = treeSum[i] - node.cost + cost[node.num];
                    dp[j + 1] = dp[j] + plus - minus;
                    ans = Math.min(ans, dp[j+1]);
                }
            }
            bw.write("#" + tc + " " + ans + "\n");
            bw.flush();
        }
        bw.close();
        br.close();
    }

    static long getAnotherTreeSum(int idx, long[] treeSum) {
        if (idx == 0) return treeSum[1] + treeSum[2];
        else return treeSum[0] + (idx == 1 ? treeSum[2] : treeSum[1]);
    }

    static long bfs(int root, int num, int parent) {
        long sum = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(root, cost[root]));
        while (!q.isEmpty()) {
            Node now = q.poll();
            sum += cost[now.num] * (treeList[num].size()+1);
            for (int child : aList[now.num]) {
                if (child == parent) continue;
                if (!treeList[num].isEmpty() && child == treeList[num].lastElement().num) continue;
                q.add(new Node(child, now.cost + cost[child]));
            }
            treeList[num].add(new Node(now.num, now.cost));
        }
        return sum;
    }
}
