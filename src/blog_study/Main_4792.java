package blog_study;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Main_4792 {
    static class Node {
        int from;
        int to;
        public Node(int f, int t) {
            this.from = f;
            this.to = t;
        }
    }
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            parents = new int[n+1];
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            // terminate
            if (n==0 && m==0 && k==0) break;

            IntStream.rangeClosed(1, n).forEach(i -> parents[i] = i);
            List<Node> bList = new ArrayList<>();
            List<Node> rList = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                Node node = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                if (c == 'B')
                    bList.add(node);
                else
                    rList.add(node);
            }
            // exeption
            if (k > bList.size()) {
                bw.write("0\n");
                continue;
            }

            // MST
            // blue edge
            int count = 0;
            for (Node node : bList) {
                int px = find(node.from);
                int py = find(node.to);
                if (px == py) continue;
                if (union(node.from, node.to)) {
                    count++;
                    if (count >= n) break;
                }
            }
            String result = count == k && MST(rList) ? "1" : "0";
            bw.write(result + "\n");
        }

        bw.close();
        br.close();
    }

    static boolean MST(List<Node> rList) {
        if (find(n) == 1) return true;
        for (Node node : rList) {
            int px = find(node.from);
            int py = find(node.to);
            if (px == py) continue;
            if (union(node.from, node.to))
                if (find(n) == 1) return true;
        }
        return false;
    }
    static boolean union(int x, int y) {
        int px = parents[x];
        int py = parents[y];
        if (px == py) return false;
        parents[py] = px;
        return true;
    }
    static int find(int x) {
        return parents[x] == x ? x : (parents[x] = find(parents[x]));
    }
}
