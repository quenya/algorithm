package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class BinaryTree04 {
    static class Tree {
        List<Integer> children = new ArrayList<>();
    }

    static int N;
    static Tree[] trees;
    static int R;
    static int depth = -1;
    static boolean aliGali = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        trees = new Tree[10000];
        R = scanner.nextInt();
        IntStream.range(0, N - 1)
                .map(n -> scanner.nextInt())
                .forEach(num -> {
                    if (trees[num] == null) {
                        trees[num] = new Tree();
                    }
                    trees[num].children.add(scanner.nextInt());
                });
        scanner.close();

        // traverse end 찾기
        traverse(R, depth);

        System.out.println((aliGali)? depth : -1);
    }

    // traverse end 찾기
    static void traverse(int n, int d) {
        if (!aliGali) return;
        d++;
        if (trees[n] == null || trees[n].children.size() == 0) {
            if (depth < 0) {
                depth = d;
            } else if (depth != d) {
                aliGali = false;
            }
            d--;
            return;
        }
        for (Integer node : trees[n].children) {
            traverse(node, d);
            if (!aliGali) return;
        }
    }

}

