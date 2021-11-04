import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static class Airport implements Comparable<Airport> {
        int to;
        int cost;
        int time;

        public Airport(int to, int c, int h) {
            this.to = to;
            this.cost = c;
            this.time = h;
        }

        @Override
        public int compareTo(Airport o) {
            int compare = Integer.compare(this.time, o.time);
            if (compare != 0) return compare;
            return Integer.compare(this.cost, o.cost);
        }
    }

    static int N;
    static int M;
    static List<Airport>[] aList;

    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("./src/blog_study/Main_10217.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            aList = new ArrayList[N + 1];
            IntStream.rangeClosed(1, N).forEach(i -> aList[i] = new ArrayList<>());
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                aList[u].add(new Airport(v, c, d));
            }
            int min_hour = dijkstra();
            bw.write(min_hour == Integer.MAX_VALUE ? "Poor KCM\n" : min_hour + "\n");
        }
        br.close();
        bw.close();
    }

    static int dijkstra() {
        int MAX = 1023456789;
        int[][] hour = new int[N + 1][M + 1];
        IntStream.range(1, N + 1).forEach(i -> Arrays.fill(hour[i], MAX));
        IntStream.rangeClosed(0, M).forEach(i -> hour[1][i] = 0);
        PriorityQueue<Airport> pq = new PriorityQueue<>();
        pq.add(new Airport(1, 0, 0));
        while (!pq.isEmpty()) {
            Airport now = pq.poll();
            if (hour[now.to][now.cost] < now.time) continue;
            for (Airport next : aList[now.to]) {
                int next_cost = now.cost + next.cost;
                if (next_cost > M) continue;
                if (hour[next.to][next_cost] <= hour[now.to][now.cost] + next.time) continue;
                hour[next.to][next_cost] = hour[now.to][now.cost] + next.time;
                IntStream.rangeClosed(next_cost, M).filter(i -> hour[next.to][i] > hour[next.to][next_cost]).forEach(i -> hour[next.to][i] = hour[next.to][next_cost]);
                pq.add(new Airport(next.to, next_cost, hour[next.to][next_cost]));
            }
        }
        int min_hour = Integer.MAX_VALUE;
        for (int i = 0; i <= M; i++) {
            if (hour[N][i] == MAX) continue;
            min_hour = Math.min(min_hour, hour[N][i]);
        }
        return min_hour;
    }
}
