package exercise.DFS;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RGB {
    static int N;
    static int map[][];
    static int mapColored[][];
    static boolean visited[][];
    static int dx[] = {0, 0, 1, -1};
    static int dy[] = {1, -1, 0, 0};
    static int result_normal;
    static int result_colored;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        N = scanner.nextInt();
        map = new int[N][N];
        mapColored = new int[N][N];
        visited = new boolean[N][N];
        String str;
        for (int i = 0; i < N; i++) {
            str = scanner.next();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
                mapColored[i][j] = (str.charAt(j) == 'G')? 'R': str.charAt(j);
            }
        }
        scanner.close();

        // init
        result_normal = 0;
        result_colored = 0;

        // normal
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == false)
                    DFS(i, j);
            }
        }

        // colored
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == false)
                    DFS_color(i, j);
            }
        }

        System.out.println(result_normal + " " + result_colored);
    }

    static void DFS(int x, int y) {
        int color = map[x][y];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        visited[x][y] = true;

        while (queue.isEmpty() == false) {
            int x1 = queue.peek().x;
            int y1 = queue.poll().y;

            for (int i = 0; i < 4; i++) {
                int x2 = x1 + dx[i];
                int y2 = y1 + dy[i];

                if (x2 >= 0 && y2 >= 0 && x2 < N && y2 < N && map[x2][y2] == color && visited[x2][y2] == false) {
                    queue.add(new Node(x2, y2));
                    visited[x2][y2] = true;
                }
            }
        }

        result_normal++;
    }

    static void DFS_color(int x, int y) {
        int color = mapColored[x][y];
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        visited[x][y] = true;

        while (queue.isEmpty() == false) {
            int x1 = queue.peek().x;
            int y1 = queue.poll().y;

            for (int i = 0; i < 4; i++) {
                int x2 = x1 + dx[i];
                int y2 = y1 + dy[i];

                if (x2 >= 0 && y2 >= 0 && x2 < N && y2 < N && mapColored[x2][y2] == color && visited[x2][y2] == false) {
                    queue.add(new Node(x2, y2));
                    visited[x2][y2] = true;
                }
            }
        }

        result_colored++;
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
