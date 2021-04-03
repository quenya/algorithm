package exercise;

import java.util.Scanner;

public class BinaryTree02 {
    static class Tree {
        int left;
        int right;
    }

    static int N;
    static Tree[] trees;
    static int K;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        trees = new Tree[N+1];
        K = scanner.nextInt();
        for (int n=0; n<N; n++) {
            int num = scanner.nextInt();
            trees[num] = new Tree();
            trees[num].left = scanner.nextInt();
            trees[num].right = scanner.nextInt();
        }
        scanner.close();

        // 중위순회
        mid(0);
        System.out.println("");
    }

    // 중위순회
    static void mid(int n) {
        if (n < 0 || K < 0) return;
        mid(trees[n].left);
        K--;
        if (K == 0) System.out.println(n);
        mid(trees[n].right);
    }
}
