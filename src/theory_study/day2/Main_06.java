package theory_study.day2;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// Ugly Number
public class Main_06 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        PriorityQueue<Long> pq = new PriorityQueue<>();
        pq.add((long) 1);
        long[] num = new long[1501];
        num[1] = 1;
        int index = 1;
        long pre = 0;
        while (true) {
            if (index > 1500) break;
            long now = pq.poll();
            if (now == pre) continue;
            num[index++] = now;
            pre = now;
            pq.add(now * 2);
            pq.add(now * 3);
            pq.add(now * 5);
        }

        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            bw.write(num[n] + " ");
        }
        bw.close();
        br.close();
    }
}