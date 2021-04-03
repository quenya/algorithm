package min_coding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class toilet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        String str = br.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(str);
        int length = str.length() / 2 - 1;
        for (int i = 0; i < length; i++) {
            int time = Integer.parseInt(stringTokenizer.nextToken());
            pq.add(time);
        }
        int t = 0;
        while (pq.size() > 1) {
            Integer poll = pq.poll();
            t += poll * pq.size();
        }
        System.out.println(t);

        br.close();
    }
}
