package theory_study.day2;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_05 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            String s = st.nextToken();
            int n = Integer.parseInt(st.nextToken());
            if (s.equals("push")) {
                pq.add(n);
            } else if (s.equals("pop")) {
                for (int j = 0; j < n; j++) {
                    int poll = pq.poll();
                    bw.write(poll + " ");
                }
                bw.write("\n");
            } else if (s.equals("add")) {
                int poll = pq.poll();
                poll += n;
                pq.add(poll);
            }
        }

        bw.close();
        br.close();
    }
}