package theory_study.day5;

import java.io.*;
import java.util.*;

// 알뜰 기차여행
public class Main_01 {
    static class Node implements Comparable<Node> {
        int to;
        int dist;

        public Node(int n, int d) {
            this.to = n;
            this.dist = d;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.dist, o.dist);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        Queue<Node>[] q = new LinkedList[N];
        for (int i = 0; i < N; i++) {
            q[i] = new LinkedList<>();
        }
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            q[a].add(new Node(b, dist));
        }

        int[] map = new int[N];
        Arrays.fill(map, 1023456789);
        map[0] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int now_num = now.to;
            for (Node next : q[now_num]) {
                int next_num = next.to;
                if (map[next_num] > map[now_num] + next.dist) {
                    map[next_num] = map[now_num] + next.dist;
                    pq.add(next);
                }
            }
        }

        bw.write(map[N - 1]==1023456789? "impossible" : map[N-1]+"");
        bw.close();
        br.close();
    }
}
