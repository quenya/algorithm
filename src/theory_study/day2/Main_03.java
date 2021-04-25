package theory_study.day2;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_03 {
    static class Node {
        int num;
        char ch;

        public Node(int n, char c) {
            this.num = n;
            this.ch = c;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        int temp[] = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            temp[i] = Integer.parseInt(st.nextToken());
        }
        Node[] nodes = new Node[N];
        st = new StringTokenizer(br.readLine().trim());
        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(temp[i], st.nextToken().charAt(0));
        }

        // type 1
        Arrays.sort(nodes, (o1, o2) -> Integer.compare(o1.num, o2.num));
        for (int i = 0; i < N; i++) {
            bw.write(nodes[i].num + " ");
        }
        bw.write("\n");
        // type 2
        Arrays.sort(nodes, (o1, o2) -> Integer.compare(o2.num, o1.num));
        for (int i = 0; i < N; i++) {
            bw.write(nodes[i].num + " ");
        }
        bw.write("\n");
        // type 3
        Arrays.sort(nodes, (o1, o2) -> {
            if (o1.num % 2 == 0 && o2.num % 2 != 0) return -1;
            if (o1.num % 2 != 0 && o2.num % 2 == 0) return 1;
            int compare = Integer.compare(o1.num, o2.num);
            if (compare != 0) return compare;
            return Character.compare(o1.ch, o2.ch);
        });
        for (int i = 0; i < N; i++) {
            bw.write(nodes[i].num + " ");
        }
        bw.write("\n");
        for (int i = 0; i < N; i++) {
            bw.write(nodes[i].ch + " ");
        }
        bw.close();
        br.close();
    }
}