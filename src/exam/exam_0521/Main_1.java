package exam.exam_0521;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1 {

    private static int T;
    private static int N;
    private static List<Integer> tree[];
    private static int NN;
    private static int LIMIT = 200001;
    private static List<Node> xList;
    private static List<Node> yList;

    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("./src/exam/exam_0521/Main_210521.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++) {

            N = Integer.parseInt(br.readLine());

            NN = 1;
            while(LIMIT > NN) {
                NN *= 2;
            }
            tree = new List[NN*2];

            xList = new ArrayList<Node>();
            yList = new ArrayList<Node>();

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());
                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                if(y1 == y2) {
                    //x축과 평행한 선분 list
                    xList.add(new Node(x1, x2, y1, y2));

                }else {
                    //y축과 평행한 선분 list
                    yList.add(new Node(x1, x2, y1, y2));
                }
            }

            //x축과 평행한 선분을 높이 y(y1 == y2)의 오름차순으로 정렬 (이분탐색을 위한 전처리)
            Collections.sort(xList);

            for(Node node : xList) {
                updateTree(1, 1, NN, node.x1, node.x2, node.y1);
            }

            long result = 0;
            for(Node node : yList) {
                int x = node.x1;
                int y1 = node.y1;
                int y2 = node.y2;

                result += query(x+NN-1, y1, y2);
            }
            bw.append("#" + t + " " + result).append("\n");
        }

//		long endTime = System.currentTimeMillis();
//		System.out.println("execution time : " + (endTime - startTime));

        bw.close();

    }

    private static int query(int node, int y1, int y2) {
        int count = 0;
        while(node > 1) {
            //추가한 선분(Y축과 평행한 선분)의 x좌표 위치(leaf)에서 root까지 탐색하면서 해당 좌표에 y값이 있을 때마다 교차점 count
            if(tree[node] != null) {
                count += (upper(tree[node], y2) - lower(tree[node], y1));
            }
            node/=2;

        }

        return count;
    }

    private static void updateTree(int node, int l, int r, int ql, int qr, int value) {
        //x축과 평행한 선분을 트리에 추가하면서 해당하는 x1 ~ x2구간에 y값 추가
        if(qr < l || r < ql) {
            return;
        }

        if(ql <= l && r <= qr) {
            if(tree[node] == null) {
                tree[node] = new ArrayList<Integer>();
            }
            tree[node].add(value);
            return;
        }

        int mid = (l+r)/2;

        updateTree(2*node, l, mid, ql, qr, value);
        updateTree(2*node+1, mid+1, r, ql, qr, value);
    }

    private static int lower(List<Integer> list, int target) {
        int l = 0;
        int r = list.size();
        int mid = 0;

        while(l < r) {
            mid = (l+r)/2;

            //l mid target r
            if(list.get(mid) < target) {
                l = mid + 1;
            }else {
                r = mid;
            }
        }

        return r;
    }

    private static int upper(List<Integer> list, int target) {
        int l = 0;
        int r = list.size();
        int mid = 0;

        while(l < r) {
            mid = (l+r)/2;
            //l mid target r
            if(list.get(mid) <= target) {
                l = mid + 1;
            }else {
                r = mid;
            }
        }

        return r;
    }

    public static class Node implements Comparable<Node>{
        public int x1;
        public int x2;
        public int y1;
        public int y2;

        public Node(int x1, int x2, int y1, int y2) {
            this.x1 = x1;
            this.x2 = x2;
            this.y1 = y1;
            this.y2 = y2;
        }

        @Override
        public int compareTo(Node o) {
            //x축과 평행한 선분의 y1==y2이므로 y1, y2 아무거나 sorting해도 무관
            return this.y1 - o.y1;
        }
    }
}
