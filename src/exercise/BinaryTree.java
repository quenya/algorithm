package exercise;

import java.util.Scanner;

public class BinaryTree {
    static class Tree {
        int left;
        int right;
    }

    static int N;
    static Tree[] trees;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        trees = new Tree[N+1];
        for (int n=0; n<N; n++) {
            int num = scanner.nextInt();
            trees[num] = new Tree();
            trees[num].left = scanner.nextInt();
            trees[num].right = scanner.nextInt();
        }
        scanner.close();

        // 전위순회
        pre(0);
        System.out.println("");
        // 중위순회
        mid(0);
        System.out.println("");
        // 후위순회
        post(0);
    }

    // 전위순회
    static void pre(int n) {
        if (n < 0) return;
        System.out.print(n + " ");
        pre(trees[n].left);
        pre(trees[n].right);
    }
    // 중위순회
    static void mid(int n) {
        if (n < 0) return;
        mid(trees[n].left);
        System.out.print(n + " ");
        mid(trees[n].right);
    }
    // 후위순회
    static void post(int n) {
        if (n < 0) return;
        post(trees[n].left);
        post(trees[n].right);
        System.out.print(n + " ");
    }
}
