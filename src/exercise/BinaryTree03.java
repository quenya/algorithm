package exercise;

import java.util.Scanner;

public class BinaryTree03 {
    static class Tree {
        int left;
        int right;
    }

    static int N;
    static Tree[] trees;
    static int R;
    static int total;
    static int grandTotal;
    static boolean end;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        trees = new Tree[10000];
        R = scanner.nextInt();
        grandTotal = 0;
        for (int n=0; n<N; n++) {
            int num = scanner.nextInt();
            grandTotal += num;
            trees[num] = new Tree();
            trees[num].left = scanner.nextInt();
            trees[num].right = scanner.nextInt();
        }
        scanner.close();

        total = 0;
        end = false;
        // traverse end 찾기
        left(R);
        // right end 찾기

        end = false;
        right(R);
        total -= R;
        if (grandTotal - total == total)
            System.out.println(total);
        else
            System.out.println("-1");
    }

    // traverse end 찾기
    static void left(int n) {
        if (end || n < 0 || trees[n] == null) return;
        total += n;
        if (trees[n].left < 0 && trees[n].right < 0) {
            end = true;
            return;
        }
        left(trees[n].left);
        left(trees[n].right);
    }
    static void right(int n) {
        if (end || n < 0 || trees[n] == null) return;
        total += n;
        if (trees[n].left < 0 && trees[n].right < 0) {
            end = true;
            return;
        }
        right(trees[n].right);
        right(trees[n].left);
    }
}
