package exam;

import java.io.*;
import java.util.*;

public class Main_0430 {
    static class Node {
        int num;
        int count;

        public Node(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    static int NN;
    static Node[] tree;
    static HashMap<Integer, Integer> map;
    static Queue<Integer> keyPool;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/exam/Main_0430.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            map = new HashMap<>();
            keyPool = new LinkedList<>();
            for (int i = 0; i < K; i++) {
                keyPool.add(i);
            }
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            // index tree
            NN = 1;
            while (NN < N) {
                NN *= 2;
            }
            tree = new Node[NN * 2];
            // init tree to K
            Queue<Integer> vector = new LinkedList<>();
            for (int i = 0; i < K-1; i++) {
                update(arr[i], 1);
                vector.add(arr[i]);
            }
            // sliding window
            int max = Integer.MIN_VALUE;
            int count = 0;
            for (int i = K-1; i < N; i++) {
                update(arr[i], 1);
                vector.add(arr[i]);
                if (max < tree[1].num) {
                    max = tree[1].num;
                    count = 1;
                }
                else if (max == tree[1].num) {
                    count++;
                }
                int firstElement = vector.poll();
                update(firstElement, -1);
            }
            bw.write("#" + " " + max + " " + count + "\n");
        }
        br.close();
        bw.close();
    }

    static void update(int num, int value) {
        int node = NN + getKey(num);
        if (tree[node] == null || tree[node].count == 0)
            tree[node] = new Node(num, 1);
        else {
            tree[node].count += value;
            if (tree[node].count == 0) {
                removeKey(num);
            }
        }
        while (node>1) {
            node /= 2;
            Node left = tree[node*2];
            Node right = tree[node*2+1];
            Node temp;
            if (right == null)
                temp = new Node(left.num, left.count);
            else if (left == null)
                temp = new Node(right.num, right.count);
            else {
                if (left.count == right.count) {
                    if (left.num < right.num) {
                        temp = new Node(right.num, right.count);
                    }
                    else {
                        temp = new Node(left.num, left.count);
                    }
                }
                else if (left.count < right.count) {
                    temp = new Node(right.num, right.count);
                }
                else {
                    temp = new Node(left.num, left.count);
                }
            }
            if (tree[node] == null) {
                tree[node] = temp;
                continue;
            }
            if (tree[node].num == temp.num && tree[node].count == temp.count)
                break;
            tree[node].num = temp.num;
            tree[node].count = temp.count;
        }
    }

    static void removeKey(int num) {
        keyPool.add(getKey(num));
        map.remove(num);
    }
    private static int getKey(int num) {
        if (map.containsKey(num)) return map.get(num);
        int result = keyPool.poll();
        map.put(num, result);
        return result;
    }
}
