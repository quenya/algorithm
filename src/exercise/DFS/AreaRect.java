package exercise.DFS;

import java.util.*;
import java.util.stream.Collectors;

public class AreaRect {
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};

    static int M;
    static int N;
    static int K;
    static int[][] map;
    static int[][] visited;
    static List<Integer> area;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        M = sc.nextInt();
        N = sc.nextInt();
        K = sc.nextInt();
        map = new int[M][N];
        visited = new int[M][N];
        for (int i = 0; i < K; i++) {
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            for (int j = M - y2; j < M - y1; j++) {
                for (int k = x1; k < x2; k++) {
                    map[j][k] = 1;
                }
            }
        }
        sc.close();

        // init
        area = new ArrayList<>();

        // minDistBFS
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0 && map[i][j] == 0)
                    DFS(i, j);
            }
        }

        System.out.println(area.size());
        System.out.println(area.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    static void DFS(int x, int y) {
        int size = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        visited[x][y] = 1;

        while (queue.isEmpty() == false) {
            int x1 = queue.peek().x;
            int y1 = queue.poll().y;
            size++;

            for (int i = 0; i <4; i++) {
                int x2 = x1 + dx[i];
                int y2 = y1 + dy[i];

                if (x2 >= 0 && y2 >= 0 && x2 <M && y2 < N && visited[x2][y2] == 0 && map[x2][y2] == 0) {
                    queue.add(new Node(x2, y2));
                    visited[x2][y2] = 1;
                }
            }
        }

        area.add(size);
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
