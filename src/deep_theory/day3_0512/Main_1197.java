package deep_theory.day3_0512;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1197 {
    static class Node implements Comparable<Node> {
        int from;
        int to;
        int cost;
        public Node(int f, int t, int c) {
            this.from = f;
            this.to = t;
            this.cost = c;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] parents;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        parents = new int[V+1];
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Node(s, e, c));
        }

        long sum = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int pa = find(now.from);
            int pb = find(now.to);
            if (pa == pb) continue;
            union(now.from, now.to);
            sum += now.cost;
        }

        bw.write(sum+"");

        bw.close();
        br.close();
    }

    static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px == py) return;
        parents[py] = x;
    }
    static int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }
}
