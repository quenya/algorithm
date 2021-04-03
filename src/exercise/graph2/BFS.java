package exercise.graph2;

import java.util.*;
import java.util.stream.Collectors;

public class BFS {
    static int N;
    static int V;
    static List<Integer>[] map;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        V = sc.nextInt();
        map = new List[N+1];
        visited = new boolean[N+1];
        for (int i = 1; i <= N ; i++)
            map[i] = new ArrayList<>();
        for (int i = 1; i <= V; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();
            map[s].add(e);
            map[e].add(s);
        }
        sc.close();
        for (int i = 1; i <= N ; i++)
            map[i] = map[i].stream().distinct().sorted().collect(Collectors.toList());

        BFS();
        System.out.println();
    }

    static void BFS() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        visited[1] = true;

        while (!q.isEmpty()) {
            int current = q.poll();
            System.out.printf(current + " ");
            for (int i = 0; i < map[current].size(); i++) {
                Integer child = map[current].get(i);
                if (visited[child] == false) {
                    q.add(child);
                    visited[child] = true;
                }
            }
        }
    }
}
