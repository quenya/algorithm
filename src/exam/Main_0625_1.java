package exam;

import java.io.*;
import java.util.*;

public class Main_0625_1 {
    static class Handle implements Comparable<Handle> {
        int left;
        int right;

        public Handle(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Handle o) {
            int compare = Integer.compare(this.left, o.left);
            if (compare != 0) return compare;
            return Integer.compare(o.right, this.right);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/exam/Main_0625.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            TreeSet<Integer> rightSet = new TreeSet<>();
            PriorityQueue<Handle> pq = new PriorityQueue<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                if (l > N || r > N) continue;
                if (l == 0 || r == 0) continue;
                pq.add(new Handle(l, r));
                rightSet.add(r);
            }
            int index = 0;
            int[] count = new int[300001];
            while (!pq.isEmpty()) {
                Handle now = pq.poll();
                int right = now.right;
                if (index == 0) {
                    count[++index] = right;
                    continue;
                }
                if (count[index] < right) {
                    count[++index] = right;
                    continue;
                }
                int search = Arrays.binarySearch(count, 0, index, right);
                if (search < 0) {
                    search *= -1;
                    search--;
                    count[search] = right;
                }
            }
            bw.write("#" + tc + " " + index + "\n");
            bw.flush();
        }
        br.close();
        bw.close();
    }
}
