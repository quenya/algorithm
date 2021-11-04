import java.io.*;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node> {
        int to;
        int dist;

        public Node(int to, int d) {
            this.to = to;
            this.dist = d;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("./src/blog_study/Main_2211.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Node>[] aList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            aList[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            aList[u].add(new Node(v, c));
            aList[v].add(new Node(u, c));
        }
        int[] dist = new int[N + 1];
        Arrays.fill(dist, 1023456789);
        dist[1] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        int[] prev = new int[N + 1];
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            for (Node node : aList[now.to]) {
                if (dist[node.to] > dist[now.to] + node.dist) {
                    prev[node.to] = now.to;
                    dist[node.to] = dist[now.to] + node.dist;
                    pq.add(new Node(node.to, dist[node.to]));
                }
            }
        }
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (prev[i] != 0) count++;
        }
        bw.write(count + "\n");
        for (int i = 2; i <= N; i++) {
            bw.write(prev[i] + " " + i +"\n");
        }
        br.close();
        bw.close();
    }
}
