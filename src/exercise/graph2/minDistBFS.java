package exercise.graph2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class minDistBFS {
    static int N;
    static int M;
    static int[][] map;
    static Node[][] nodes;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        nodes = new Node[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                nodes[i][j] = new Node(j, i);
                nodes[i][j].dist = N*M;
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 1)
                    nodes[i][j].dist = Integer.MAX_VALUE;
            }
        }
        sc.close();

        BFS();

        System.out.println(nodes[0][M-1].dist);
    }

    static void BFS() {
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
