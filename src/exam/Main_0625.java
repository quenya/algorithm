package exam;

import java.io.*;
import java.util.*;

public class Main_0625 {
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
            return Integer.compare(this.right, o.right);
        }
    }
    static int NN;
    static int[] tree;

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
            Handle[] aList = new Handle[M+1];
            aList[0] = new Handle(0, 0);
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int l = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                aList[i] = new Handle(l, r);
                rightSet.add(r);
            }
            HashMap<Integer, Integer> rightIndexMap = new HashMap<>();
            int index = 1;
            for (int right : rightSet) {
                rightIndexMap.put(right, index++);
            }
            Arrays.sort(aList);
            NN = 1;
            while (NN < N) {
                NN *= 2;
            }
            tree = new int[NN*2+1];
            int prevLeft = 0;
            int count = 0;
            Queue<Integer> q = new LinkedList<>();
            for (Handle handle : aList) {
                if (handle.left == prevLeft) {
                    int pos = rightIndexMap.get(handle.right);
                    q.add(pos);
                    q.add(count);
                    continue;
                }
                while (!q.isEmpty()) {
                    int pos = q.poll();
                    int c = q.poll();
                    set(pos, c);
                }
                int pos = rightIndexMap.get(handle.right);
                int query = query(1, 1, M, 1, pos - 1);
                count = query+1;
                q.add(pos);
                q.add(count);
            }
            bw.write("#" + tc + " " + tree[1] + "\n");
        }
        br.close();
        bw.close();
    }
    static int query(int node, int l, int r, int ql, int qr) {
        if (ql == l && r == qr) return tree[node];
        int mid = (l+r)/2;
        if (mid <= ql)
            return query(node*2+1, mid+1, r, ql, qr);
        if (mid > qr)
            return query(node*2, l, mid, ql, qr);
        return 0;
    }
    static void set(int node, int count) {
        tree[node] = count;
        while (node > 1) {
            node /= 2;
            tree[node] = count;
        }
    }
}
