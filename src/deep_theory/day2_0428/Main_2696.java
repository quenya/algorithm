package deep_theory.day2_0428;

import java.io.*;
import java.util.*;

public class Main_2696 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine().trim());
        for (int tc = 1; tc <= T; tc++) {
            int M = Integer.parseInt(br.readLine().trim());
            PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Integer> right = new PriorityQueue<>();
            StringTokenizer st = null;
            List<Integer> ans = new ArrayList<>();
            for (int i = 1; i <= M; i++) {
                if (i%10==1)
                    st = new StringTokenizer(br.readLine().trim());
                int a = Integer.parseInt(st.nextToken());
                if (left.size() < right.size())
                    left.add(a);
                else
                    right.add(a);
                if (!left.isEmpty() && left.peek() > right.peek()) {
                    right.add(left.poll());
                    left.add(right.poll());
                }
                if (i%2==1) {
                    ans.add(right.peek());
                }
            }
            bw.write(ans.size() + "\n");
            for (int i = 0; i < ans.size(); i++) {
                bw.write(ans.get(i) + "");
                bw.write(i != 0 && i % 9 == 0 ? "\n" : " ");
            }
            bw.write("\n");
        }

        bw.close();
        br.close();
    }

}
