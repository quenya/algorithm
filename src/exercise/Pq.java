package exercise;

public class Pq {
    static class MyPriorityQueue {
        static final int MAX = 100;
        int[] tree;
        int len;

        MyPriorityQueue() {
            tree = new int[MAX];
            len = 0;
        }
        void push(int n) {
            tree[len++] = n;
        }
        int top() {
            int popNode = tree[0];

            // sort
            tree[0] = tree[len];
            tree[len] = 0;
            len--;
            sort(0);

            return popNode;
        }
        void pop() {

        }

        void sort(int n) {
            if (tree[n] < tree[2*n]) {
                int temp = tree[2*n];
                tree[2*n] = tree[n];
                tree[n] = temp;
                sort(2*n);
            }
            else if (tree[n] < tree[2*n+1]) {
                int temp = tree[2*n+1];
                tree[2*n+1] = tree[n];
                tree[n] = temp;
                sort(2*n+1);
            }
        }
    }

    public static void main(String[] args) {
        MyPriorityQueue myPq = new MyPriorityQueue();
        myPq.push(3);
        myPq.push(1);
        myPq.push(2);
        myPq.pop();
        System.out.println(myPq.top());
    }
}
