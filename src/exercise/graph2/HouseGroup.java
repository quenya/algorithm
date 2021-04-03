package exercise.graph2;

import java.util.*;

public class HouseGroup {
    static int N;
    static int[][] map;
    static Node[][] nodes;
    static List<Integer> group;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.nextLine();
        N = s1.charAt(0) - 48;
        map = new int[N][N];
        nodes = new Node[N][N];
        group = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String s = sc.nextLine();
            for (int j = 0; j < s.length(); j++) {
                nodes[i][j] = new Node(j, i);
                map[i][j] = s.charAt(j) - 48;
            }
        }
        sc.close();

        int i = 0;
        int j = 0;
        while (true) {
            // find
            boolean found = false;
            for (; i < N; i++) {
                for (; j < N; j++) {
                    if (map[i][j] == 1) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
                j = 0;
            }
            // condition check
            if (i == N-1 && j == N-1 && map[i][j] == 0)
                break;

            if (i >= N || j >= N) break;

            // bfs
            BFS(j, i);
        }

        System.out.println(group.size());
        group.stream().sorted().forEach(System.out::println);
    }

    static void BFS(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(nodes[y][x]);

        int count = 0;
        while (!q.isEmpty()) {
            Node current = q.poll();
            int x1 = current.x;
            int y1 = current.y;
            if (map[y1][x1] == 0) continue;

            count++;
            map[y1][x1] = 0;

            // y-1, x
            if (y1 > 0 && map[y1 - 1][x1] == 1) {
                q.add(nodes[y1 - 1][x1]);
            }
            // y+1, x
            if (y1 < N - 1 && map[y1 + 1][x1] == 1) {
                q.add(nodes[y1 + 1][x1]);
            }
            // y, x-1
            if (x1 > 0 && map[y1][x1 - 1] == 1) {
                q.add(nodes[y1][x1 - 1]);
            }
            // y, x+1
            if (x1 < N - 1 && map[y1][x1 + 1] == 1) {
                q.add(nodes[y1][x1 + 1]);
            }
        }
        group.add(count);
    }

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
