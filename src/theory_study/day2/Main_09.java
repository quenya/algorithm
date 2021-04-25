package theory_study.day2;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 프린터 스케쥴링 시스템
public class Main_09 {
    static class Document implements Comparable<Document> {
        int num;
        int start;
        int duration;
        int printer;

        public Document(int n, int start, int duration) {
            this.num = n;
            this.start = start;
            this.duration = duration;
        }

        @Override
        public int compareTo(Document o) {
            int compare = Integer.compare(this.start, o.start);
            if (compare != 0) return compare;
            compare = Integer.compare(this.duration, o.duration);
            if (compare != 0) return compare;
            return Integer.compare(this.num, o.num);
        }
    }

    static class Printer implements Comparable<Printer> {
        int num;
        int end;

        public Printer(int n, int e) {
            this.num = n;
            this.end = e;
        }

        @Override
        public int compareTo(Printer o) {
            int compare = Integer.compare(this.end, o.end);
            if (compare != 0) return compare;
            return Integer.compare(this.num, o.num);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static PriorityQueue<Document> documentPq;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        documentPq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            documentPq.add(new Document(i, s, d));
        }

        PriorityQueue<Printer> printerPq = new PriorityQueue();
        for (int i = 1; i <= M; i++) {
            printerPq.add(new Printer(i, 0));
        }
        PriorityQueue<Document> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.num, o2.num));
        while (!documentPq.isEmpty()) {
            Document document = documentPq.poll();
            Printer printer = printerPq.poll();
            if (printer.end < document.start)
                printer.end = document.start;
            printer.end += document.duration;
            document.printer = printer.num;
            pq.add(document);
            printerPq.add(printer);
        }
        while (!pq.isEmpty()) {
            Document document = pq.poll();
            bw.write(document.printer + "\n");
        }

        bw.close();
        br.close();
    }
}