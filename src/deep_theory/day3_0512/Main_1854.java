package deep_theory.day3_0512;

import java.io.*;
import java.util.*;

public class Main_1854 {
    static class Node implements Comparable<Node> {
        int to;
        int cost;
        public Node(int t, int c) {
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

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Node> aList[] = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            aList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            aList[s].add(new Node(e, c));
        }

        // dijkstra
        PriorityQueue<Integer>[] dist = new PriorityQueue[N+1];
        for (int i = 1; i <= N; i++) {
            dist[i] = new PriorityQueue<>(K, (o1, o2) -> Integer.compare(o2, o1));
        }
        dist[1].add(0);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int now_node = now.to;
            int now_cost = now.cost;
            for (Node next : aList[now_node]) {
                int next_node = next.to;
                if (dist[next_node].size() < K) {
                    dist[next_node].add(now_cost + next.cost);
                    pq.add(new Node(next_node, now_cost + next.cost));
                }
                else if (dist[next_node].peek() > now_cost + next.cost) {
                    dist[next_node].poll();
                    dist[next_node].add(now_cost + next.cost);
                    pq.add(new Node(next_node, now_cost + next.cost));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (dist[i].size() == K) {
                bw.write(dist[i].peek() + "\n");
            }
            else {
                bw.write("-1\n");
            }
        }
        bw.close();
        br.close();
    }
}
