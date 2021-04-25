package theory_study.day5;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 신규 도로
public class Main_03 {
    static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int c) {
            this.to = to;
            this.cost = c;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static HashMap<Integer, HashMap<Integer, Node>> listMap;
    static int max_value = 1023456789;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            listMap = new HashMap<>();
            for (int i = 0; i <= N; i++) {
                listMap.put(i, new HashMap<>());
            }
            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine().trim());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                listMap.get(a).put(b, new Node(b, c));
                listMap.get(b).put(a, new Node(a, c));
            }

            // 원본 비용
            // 1 -> node 거리
            int[] front_dist = new int[N];
            Arrays.fill(front_dist, max_value);
            front_dist = dijkstra(1);
            // n -> node 거리
            int[] rear_dist = new int[N];
            Arrays.fill(rear_dist, max_value);
            rear_dist = dijkstra(N);
            rear_dist[0] = rear_dist[1] = rear_dist[N] = Integer.MAX_VALUE;
            Arrays.sort(rear_dist);

            int ans = 0;
            int one_to_n = front_dist[N];
            int a_to_b = 1;
            for (int i = 2; i < N; i++) {
                // 1->a + 1 + b->n
                int one_to_a = front_dist[i];
                int b_to_n = one_to_n - one_to_a - a_to_b;
                int index = bs(rear_dist, b_to_n);
                if (index > 0)
                    ans += index;
            }

            bw.write("#" + tc + " " + ans + "\n");
        }

        bw.close();
        br.close();
    }

    static int bs(int[] dist, int num) {
        int left = 0;
        int right = N - 2;
        int ret = -1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (dist[mid] < num) {
                ret = mid;
                left = mid + 1;
            } else
                right = mid - 1;
        }
        return ret + 1;
    }

    static int[] dijkstra(int s) {
        int[] dest = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dest[i] = max_value;
        }
        dest[s] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(s, 0));
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int now_num = now.to;
            for (Node next : listMap.get(now_num).values()) {
                int next_num = next.to;
                if (dest[next_num] > dest[now_num] + next.cost) {
                    dest[next_num] = dest[now_num] + next.cost;
                    pq.add(next);
                }
            }
        }
        return dest;
    }
}
