package exam.exam_0521;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Line implements Comparable<Line> {
        int x1;
        int y1;
        int x2;
        int y2;

        public Line(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public int compareTo(Line o) {
            return Integer.compare(this.y1, o.y1);
        }
    }

    static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/exam/exam_0521/Main_210521.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int LIMIT = 200_001;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Line> xList = new ArrayList<>();
            ArrayList<Line> yList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());
                Line line = new Line(x1, y1, x2, y2);
                if (x1 == x2) {
                    // vertical
                    yList.add(line);
                } else {
                    // horizontal
                    xList.add(line);
                }
            }
            // sort
            Collections.sort(xList);
            // init tree
            int NN = 1;
            while (NN < LIMIT) NN *= 2;
            tree = new ArrayList[NN * 2];
            // line to tree
            for (Line line : xList)
                updateTree(1, 1, NN, line.x1, line.x2, line.y1);

            long ans = 0;
            for (Line line : yList)
                ans += query(NN + line.x1 - 1, line.y1, line.y2);

            bw.write("#" + tc + " " + ans + "\n");
        }
        bw.close();
        br.close();
    }

    static void updateTree(int node, int l, int r, int ql, int qr, int value) {
        if (r < ql || qr < l) return;
        if (ql <= l && r <= qr) {
            // exact range
            if (tree[node] == null)
                tree[node] = new ArrayList<>();
            tree[node].add(value);
            return;
        }
        int mid = (l + r) / 2;
        updateTree(node * 2, l, mid, ql, qr, value);
        updateTree(node * 2 + 1, mid + 1, r, ql, qr, value);
    }

    static int query(int node, int y1, int y2) {
        int count = 0;
        while (node > 1) {
            if (tree[node] != null)
                count += upper(tree[node], y2) - lower(tree[node], y1);
            node /= 2;
        }
        return count;
    }

    private static int lower(List<Integer> list, int target) {
        int l = 0;
        int r = list.size();
        int mid = 0;

        while (l < r) {
            mid = (l + r) / 2;
            //l mid target r
            if (list.get(mid) < target)
                l = mid + 1;
            else
                r = mid;
        }

        return r;
    }

    private static int upper(List<Integer> list, int target) {
        int l = 0;
        int r = list.size();
        int mid = 0;

        while (l < r) {
            mid = (l + r) / 2;
            //l mid target r
            if (list.get(mid) <= target)
                l = mid + 1;
            else
                r = mid;
        }

        return r;
    }
}
