package exercise;

import java.util.stream.IntStream;

public class HeapTest {
    static class Heap {
        int MAX = 100;
        int[] tree;
        int len = 1;

        public Heap() {
            tree = new int[MAX];
            IntStream.range(0, MAX).forEach(i -> tree[i] = Integer.MAX_VALUE);
        }

        void push(int n) {
            int curr = len;
            tree[len++] = n;

            while (curr > 1) {
                int parent = curr / 2;
                if (tree[curr] > tree[parent])
                    return;
                int temp = tree[curr];
                tree[curr] = tree[parent];
                tree[parent] = temp;
                curr = parent;
            }
        }

        void pop() {
            tree[1] = Integer.MAX_VALUE;
            len--;

            int curr = 1;
            while (curr < len) {
                int child = curr * 2;
                if (tree[curr * 2] > tree[curr * 2 + 1])
                    child += 1;
                if (tree[curr] < tree[child])
                    return;
                int temp = tree[curr];
                tree[curr] = tree[child];
                tree[child] = temp;
                curr = child;
            }
        }

        int top() {
            return tree[1];
        }
    }

    public static void main(String[] args) {
        Heap myHeap = new Heap();
        myHeap.push(3);
        myHeap.push(1);
        myHeap.push(2);
        myHeap.push(4);
        System.out.println(myHeap.top());
        myHeap.pop();
        System.out.println(myHeap.top());
        myHeap.pop();
        System.out.println(myHeap.top());
        myHeap.pop();
        System.out.println(myHeap.top());
    }
}
