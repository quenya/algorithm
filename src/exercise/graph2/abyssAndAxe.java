package exercise.graph2;

import java.util.*;

public class abyssAndAxe {
    static int N;
    static int M;
    static int[][] map;
    static Node[][] nodes;
    static int result;
    static List<Node> wallNodeList;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        nodes = new Node[N][M];
        result = Integer.MAX_VALUE;
        wallNodeList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nodes[i][j] = new Node(j, i);
                nodes[i][j].dist = N*M;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1) {
                    nodes[i][j].dist = Integer.MAX_VALUE;
                    wallNodeList.add(nodes[i][j]);
                }
            }
        }
        sc.close();

        wallNodeList.forEach(node -> {
            init();
            BFS(node.y, node.x);
            if (result > nodes[0][M-1].dist)
                result = nodes[0][M-1].dist;
        });

//        int i=0;
//        int j=0;
//        while (true) {
//            boolean found = false;
//            for (; i < N; i++) {
//                for (; j < M; j++) {
//                    if (map[i][j] == 1) {
//                        found = true;
//                        break;
//                    }
//                }
//                if (found) break;
//                j = 0;
//            }
//
//            BFS(i, j);
//
//            if (result > nodes[0][M-1].dist)
//                result = nodes[0][M-1].dist;
//
//            init();
//
//            if (i == N-1 || j == M-1)
//                break;
//        }

        System.out.println(result);
    }

    static void BFS(int a, int b) {
        map[a][b] = 0;
        nodes[N-1][0].dist = 0;
        Queue<Node> q = new LinkedList<>();
        q.add(nodes[N-1][0]);

        while (!q.isEmpty()) {
            Node current = q.poll();
            int dist = current.dist;
            int y = current.y;
            int x = current.x;
            current.visited = true;

            // y-1, x
            if (y > 0 && map[y-1][x] == 0) {
                Node child = nodes[y - 1][x];
                if (!child.visited) {
                    child.dist = dist + 1;
                    q.add(child);
                }
            }
            // y+1, x
            if (y < N-1 && map[y+1][x] == 0) {
                Node child = nodes[y + 1][x];
                if (!child.visited) {
                    child.dist = dist + 1;
                    q.add(child);
                }
            }
            // y, x-1
            if (x > 0 && map[y][x-1] == 0) {
                Node child = nodes[y][x - 1];
                if (!child.visited){
                    child.dist = dist + 1;
                    q.add(child);
                }
            }
            // y, x+1
            if (x < M-1 && map[y][x+1] == 0) {
                Node child = nodes[y][x + 1];
                if (!child.visited) {
                    child.dist = dist + 1;
                    q.add(child);
                }
            }
        }
        map[a][b] = 1;
    }

    static void init() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nodes[i][j] = new Node(j, i);
                if (map[i][j] == 1)
                    nodes[i][j].dist = Integer.MAX_VALUE;
                else
                    nodes[i][j].dist = N*M;
            }
        }

    }
    static class Node {
        int x;
        int y;
        int dist;
        boolean visited;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
