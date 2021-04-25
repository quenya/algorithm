package theory_study.day2;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_04 {
    static class Node implements Comparable<Node> {
        int start;
        int end;

        public Node(int s, int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.end, o.end);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        Node[] nodes = new Node[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(nodes);
        int end = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            Node node = nodes[i];
            if (end > node.start)
                continue;
            end = node.end;
            count++;
        }
        bw.write(count + "\n");

        bw.close();
        br.close();
    }
}