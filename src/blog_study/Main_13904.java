package blog_study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main_13904 {
    static class Homework implements Comparable<Homework> {
        int d;
        int w;

        public Homework(int d, int w) {
            this.d = d;
            this.w = w;
        }

        @Override
        public int compareTo(Homework o) {
            return Integer.compare(o.w, this.w);
        }
    }

    static PriorityQueue<Homework> pq;
    static int map[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        int max_d = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.add(new Homework(d, w));
            if (max_d < d)
                max_d = d;
        }
        br.close();

        map = new int[max_d + 1];
        while (!pq.isEmpty()) {
            Homework poll = pq.poll();
            for (int i = poll.d; i > 0; i--) {
                if (map[i] == 0) {
                    map[i] = poll.w;
                    break;
                }
            }
        }

        int sum = IntStream.rangeClosed(1, max_d).map(i -> map[i]).sum();
        System.out.println(sum);
    }
}
