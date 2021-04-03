package exercise.DFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class sheep {
    static final int dx[] = {0, 0, 1, -1};
    static final int dy[] = {1, -1, 0, 0};
    static int R;
    static int C;
    static int map[][];
    static boolean visited[][];
    static int result_wolves;
    static int result_sheeps;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map = new int[R][C];
        visited = new boolean[R][C];
        for (int i=0; i<R; i++) {
            String next = sc.next();
            for (int j=0; j<C; j++) {
                map[i][j] = next.charAt(j);
            }
        }
        sc.close();

        // init
        result_wolves = 0;
        result_sheeps = 0;

        // minDistBFS
        for (int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                if (visited[i][j] == false && map[i][j] != '#')
                    DFS(i, j);
            }
        }

        System.out.println(result_sheeps + " " + result_wolves);
    }

    static void DFS(int x, int y) {
        int wolves_count = 0;
        int sheeps_count = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        visited[x][y] = true;

        while (queue.isEmpty() == false) {
            int x1 = queue.peek().x;
            int y1 = queue.poll().y;

            if (map[x1][y1] == 'v') wolves_count++;
            if (map[x1][y1] == 'k') sheeps_count++;

            for (int i=0; i<4; i++) {
                int x2 = x1 + dx[i];
                int y2 = y1 + dy[i];

                if (x2 >= 0 && y2 >=0 && x2 < R && y2 < C) {
                    if (visited[x2][y2] == false && map[x2][y2] != '#') {
                        queue.add(new Node(x2, y2));
                        visited[x2][y2] = true;
                    }
                }
            }
        }

        if (wolves_count < sheeps_count)
            result_sheeps += sheeps_count;
        else
            result_wolves += wolves_count;
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}
