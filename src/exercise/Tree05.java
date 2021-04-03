package exercise;

import java.util.Scanner;

public class Tree05 {
    static int N;
    static int X;
    static int Y;
    static int ancestor;
    static int[] parentArr;
    static boolean[] visit;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        X = sc.nextInt();
        Y = sc.nextInt();
        parentArr = new int[N];
        visit = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            parentArr[b] = a;
        }
        sc.close();

        ancestor = -1;

        // tree 구성
        makeTree(X);
        // 조상 비교
        compareTree(Y);

        System.out.println(ancestor);
    }

    static void makeTree(int n) {
        int parent = parentArr[n];
        if (visit[parent]) return;
        visit[parent] = true;
        makeTree(parent);
    }
    static void compareTree(int n) {
        int parent = parentArr[n];
        if (visit[parent]) {
            ancestor = parent;
            return;
        }
        visit[parent] = true;
        compareTree(parent);
    }
}
