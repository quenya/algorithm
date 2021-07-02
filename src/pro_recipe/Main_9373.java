package pro_recipe;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main_9373 {
    static class Node implements Comparable<Node> {
        int x;
        int y;
        int r;

        public Node(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.r, o.r);
        }
    }
    static class Edge implements Comparable<Edge> {
        int from;
        int to;

        public Edge(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        double dist;

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.dist, o.dist);
        }
    }
    static int[] parents;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/pro_recipe/Main_9373.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int W = Integer.parseInt(br.readLine());
            int N = Integer.parseInt(br.readLine());
            Node[] nodes = new Node[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                nodes[i] = new Node(x, y, r);
            }
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.add(new Edge(N, N+1, W));
            for (int i = 0; i < N; i++) {
                // to left
                double dist = nodes[i].x - nodes[i].r;
                pq.add(new Edge(N, i, dist));
                // to right
                dist = W - nodes[i].x - nodes[i].r;
                pq.add(new Edge(i, N+1, dist));

                for (int j = i+1; j < N; j++) {
                    dist = dist(nodes[i], nodes[j]);
                    pq.add(new Edge(i, j, dist));
                }
            }
            parents = new int[N+2];
            IntStream.range(0, N + 2).forEach(i -> parents[i] = i);
            double ans = 0;
            while (!pq.isEmpty()) {
                Edge now = pq.poll();
                int a = find(now.from);
                int b = find(now.to);
                if (a == b) continue;
                union(now.from, now.to);
                if (find(N) == find(N+1)) {
                    ans = now.dist;
                    break;
                }
            }
            bw.write(ans <= 0 ? "0\n" : String.format("%.6f", ans / 2) + "\n");
        }
        br.close();
        bw.close();
    }
    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        parents[py] = px;
    }
    static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }
    static double dist(Node a, Node b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2)) - b.r - a.r;
    }
}
