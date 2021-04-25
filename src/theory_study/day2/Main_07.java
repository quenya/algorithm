package theory_study.day2;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 정중앙 대학교
public class Main_07 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static PriorityQueue<Integer> maxHeap;
    static PriorityQueue<Integer> minHeap;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        minHeap = new PriorityQueue<>();
        minHeap.add(500);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int n = Integer.parseInt(st.nextToken());
            maxHeap.add(n);
            n = Integer.parseInt(st.nextToken());
            minHeap.add(n);
            if (maxHeap.peek() > minHeap.peek()) {
                minHeap.add(maxHeap.poll());
                maxHeap.add(minHeap.poll());
            }
            bw.write(minHeap.peek() + "\n");
        }

        bw.close();
        br.close();
    }
}