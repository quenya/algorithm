package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Tree06 {
    static class Node {
        int num;
        int depth;
        List<Node> children;

        public Node() {
            children = new ArrayList<>();
        }
    }

    static int N;
    static int R;
    static int depth;
    static List<Integer>[] vertexList;
    static boolean[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        vertexList = new List[N];
        visit = new boolean[N];
        for (int i = 0; i < N; i++) {
            vertexList[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            vertexList[a].add(b);
            vertexList[b].add(a);
        }
        sc.close();

        depth = 0;

        // tree 구성
        Node node = new Node();
        node.num = R;
        node.depth = 0;
        visit[R] = true;
        makeTree(node);

        System.out.println(depth);
    }

    static void makeTree(Node node) {
        List<Integer> childList = vertexList[node.num];
        for (Integer n : childList) {
            if (visit[n]) continue;
            Node child = new Node();
            child.num = n;
            child.depth = node.depth + 1;
            if (depth < child.depth)
                depth = child.depth;
            visit[n] = true;
            makeTree(child);
        }
    }
}
