package exercise;

import java.util.Arrays;
import java.util.Scanner;

public class MaxMinAvg {
    static int K;

    static class MaxPriorityQueue {
        int[] tree;
        int len = 1;

        MaxPriorityQueue(int k) {
            tree = new int[k + 1];
        }

        void push(int n) {
            int top = tree[1];
            if (len - 1 == K && top >= n)
                return;

            if (len <= K) {
                int index = len;
                tree[len++] = n;
                while (index > 1) {
                    int parent = index / 2;
                    if (tree[index] < tree[parent]) {
                        int temp = tree[index];
                        tree[index] = tree[parent];
                        tree[parent] = temp;
                        index = parent;
                    } else return;
                }
                return;
            }

            tree[1] = n;
            int index = 1;
            while (index < K) {
                int child;
                if (len <= index * 2) break;
                else if (1 <= index * 2 && index * 2 < len && len < index * 2 + 1)
                    child = index * 2;
                else {
                    if (tree[index * 2] < tree[index * 2 + 1])
                        child = index * 2;
                    else
                        child = index * 2 + 1;
                }

                if (tree[index] > tree[child]) {
                    int temp = tree[index];
                    tree[index] = tree[child];
                    tree[child] = temp;
                    index = child;
                } else break;
            }
        }

        double getAvg() {
            return Arrays.stream(tree).sum() / (len - 1);
        }
    }

    static class MinPriorityQueue {
        int[] tree;
        int len = 1;

        public MinPriorityQueue(int k) {
            tree = new int[k + 1];
        }

        void push(int n) {
            int top = tree[1];
            if (len - 1 == K && top <= n)
                return;

            if (len <= K) {
                int index = len;
                tree[len++] = n;
                while (index > 1) {
                    int parent = index / 2;
                    if (tree[index] > tree[parent]) {
                        int temp = tree[index];
                        tree[index] = tree[parent];
                        tree[parent] = temp;
                        index = parent;
                    } else return;
                }
                return;
            }

            tree[1] = n;
            int index = 1;
            while (index < K) {
                int child;
                if (len <= index * 2) break;
                else if (1 <= index * 2 && index * 2 < len && len < index * 2 + 1)
                    child = index * 2;
                else {
                    if (tree[index * 2] > tree[index * 2 + 1])
                        child = index * 2;
                    else
                        child = index * 2 + 1;
                }

                if (tree[index] < tree[child]) {
                    int temp = tree[index];
                    tree[index] = tree[child];
                    tree[child] = temp;
                    index = child;
                } else break;
            }
        }

        double getAvg() {
            return Arrays.stream(tree).sum() / (len - 1);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        K = sc.nextInt();
        MinPriorityQueue pqMin = new MinPriorityQueue(K);
        MaxPriorityQueue pqMax = new MaxPriorityQueue(K);
        System.out.println("");
        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();
            pqMin.push(n);
            pqMax.push(n);
            System.out.println(pqMax.getAvg() + " " + pqMin.getAvg());
        }
        sc.close();
    }
}