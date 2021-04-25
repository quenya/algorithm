package theory_study.day2;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 프린터 스케쥴링 시스템
public class Main_09_1 {
    static class Document implements Comparable<Document> {
        long start;
        long cost;
        int index;

        public Document(long start, long cost, int index) {
            this.start = start;
            this.cost = cost;
            this.index = index;
        }

        @Override
        public int compareTo(Document o) {
            int compare = Long.compare(this.start, o.start);
            if (compare != 0) return compare;
            compare = Long.compare(this.cost, o.cost);
            if (compare != 0) return compare;
            return Integer.compare(this.index, o.index);
        }
    }

    static class Printer implements Comparable<Printer> {
        long end;
        int index;

        public Printer(long e, int i) {
            this.end = e;
            this.index = i;
        }

        @Override
        public int compareTo(Printer o) {
            int compare = Long.compare(this.end, o.end);
            if (compare != 0) return compare;
            return Integer.compare(this.index, o.index);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Document[] doc = new Document[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int s = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            doc[i] = new Document(s, c, i);
        }
        Arrays.sort(doc);   // nLogN
        PriorityQueue<Printer> pq = new PriorityQueue<>();
        for (int i = 1; i <= M; i++)
            pq.add(new Printer(0, i));  // mLogM
        int[] doc_print = new int[N];
        for (int i = 0; i < N; i++) {
            Printer printer = pq.poll();    // nLogM
            long end = printer.end;
            if (end < doc[i].start)
                end = doc[i].start + doc[i].cost;
            else
                end += doc[i].cost;
            pq.add(new Printer(end, printer.index));    // nLogM
            doc_print[doc[i].index] = printer.index;
        }
        for (int i = 0; i < N; i++)
            bw.write(doc_print[i] + "\n");

        bw.close();
        br.close();
    }
}