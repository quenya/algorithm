package deep_theory.day3_0512;

import java.io.*;
import java.util.*;

public class Main_1238 {
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
        int X = Integer.parseInt(st.nextToken());
        List<Node> aList[] = new ArrayList[N+1];
        List<Node> bList[] = new ArrayList[N+1];
        for (int i = 1; i <= N; i++) {
            aList[i] = new ArrayList<>();
            bList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            aList[s].add(new Node(e, c));
            bList[e].add(new Node(s, c));
        }

        // dijkstra - x -> all
        int[] fromX = new int[N+1];
        Arrays.fill(fromX, Integer.MAX_VALUE);
        fromX[X] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(X, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int now_node = now.to;
            for (Node next : aList[now_node]) {
                int next_node = next.to;
                if (fromX[next_node] > fromX[now_node] + next.cost) {
                    fromX[next_node] = fromX[now_node] + next.cost;
                    pq.add(next);
                }
            }
        }
        // dijkstra - all -> x
        int[] toX = new int[N+1];
        Arrays.fill(toX, Integer.MAX_VALUE);
        toX[X] = 0;
        pq.add(new Node(X, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int now_node = now.to;
            for (Node next : bList[now_node]) {
                int next_node = next.to;
                if (toX[next_node] > toX[now_node] + next.cost) {
                    toX[next_node] = toX[now_node] + next.cost;
                    pq.add(next);
                }
            }
        }
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, fromX[i] + toX[i]);
        }
        bw.write(max + "");
        bw.close();
        br.close();
    }
}
