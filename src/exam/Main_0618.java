package exam;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main_0618 {
    static class NodeP implements Comparable<NodeP> {
        int to;
        int pop;

        public NodeP(int to, int pop) {
            this.to = to;
            this.pop = pop;
        }

        @Override
        public int compareTo(NodeP o) {
            return Integer.compare(this.pop, o.pop);
        }
    }

    static class NodeD implements Comparable<NodeD> {
        int to;
        int dist;

        public NodeD(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(NodeD o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static int N;
    static List<NodeP>[] pList;
    static List<NodeD>[] dList;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/exam/Main_0618.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            pList = new List[N + 1];
            IntStream.rangeClosed(1, N).forEach(i -> pList[i] = new ArrayList<>());
            dList = new List[N + 1];
            IntStream.rangeClosed(1, N).forEach(i -> dList[i] = new ArrayList<>());
            for (int i = 0; i < N - 1; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                pList[a].add(new NodeP(b, p));
                pList[b].add(new NodeP(a, p));
                dList[a].add(new NodeD(b, d));
                dList[b].add(new NodeD(a, d));
            }
            // find start
            int[] pop1 = dijkstra(1);
            int start = 0;
            int max = 0;
            for (int i = 1; i <= N; i++) {
                if (max < pop1[i]) {
                    start = i;
                    max = pop1[i];
                }
            }
            int[] pop2 = dijkstra(start);
            int end = 0;
            max = 0;
            for (int i = 1; i <= N; i++) {
                if (max < pop2[i]) {
                    end = i;
                    max = pop2[i];
                }
            }
            int[] pop3 = dijkstra(end);
            Vector<Integer> main = new Vector<>();
            for (int i = 1; i <= N; i++) {
                if (pop2[i] + pop3[i] == max) {
                    main.add(i);
                }
            }
            for (int i = 1; i <= N; i++) {
                for (NodeD next : dList[i]) {
                    if (main.contains(next.to)) {
                        next.dist = 0;
                    }
                }
            }
            // dijkstra - dist
            PriorityQueue<NodeD> pq = new PriorityQueue<>();
            pq.add(new NodeD(start, 0));
            int[] dist = new int[N + 1];
            Arrays.fill(dist, 1023456789);
            dist[start] = 0;
            while (!pq.isEmpty()) {
                NodeD now = pq.poll();
                for (NodeD next : dList[now.to]) {
                    if (dist[next.to] > dist[now.to] + next.dist) {
                        dist[next.to] = dist[now.to] + next.dist;
                        pq.add(new NodeD(next.to, dist[next.to]));
                    }
                }
            }
            int ans = IntStream.rangeClosed(1, N).map(i -> dist[i]).sum();
            bw.write("#" + tc + " " + ans + "\n");
        }
        br.close();
        bw.close();
    }

    private static int[] dijkstra(int s) {
        PriorityQueue<NodeP> pq = new PriorityQueue<>();
        pq.add(new NodeP(s, 0));
        int[] pops = new int[N + 1];
        Arrays.fill(pops, 1023456789);
        pops[s] = 0;
        while (!pq.isEmpty()) {
            NodeP now = pq.poll();
            for (NodeP next : pList[now.to]) {
                if (pops[next.to] > pops[now.to] + next.pop) {
                    pops[next.to] = pops[now.to] + next.pop;
                    pq.add(new NodeP(next.to, pops[next.to]));
                }
            }
        }
        return pops;
    }
}
