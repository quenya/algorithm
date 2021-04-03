package exercise.graph;

import java.util.*;

public class DfsBfs {
    static int V;
    static int E;
    static List<Integer> map[];
    static boolean visited[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        map = new List[V+1];
        for (int i = 0; i < V; i++) {
            map[i] = new ArrayList<>();
        }
        visited = new boolean[V+1];
        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            map[a].add(b);
            map[b].add(a);
        }
        sc.close();

        DFS(0);
        visited = new boolean[V+1];
        System.out.println();
        BFS(0);
    }

    static void DFS(int x) {
        visited[x] = true;
        System.out.print(x + " ");

        for (int i = 0; i < map[x].size(); i++) {
            if (visited[map[x].get(i)] == false)
                DFS(map[x].get(i));
        }
    }

    static void BFS(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);

        while (queue.isEmpty() == false) {
            Integer pop = queue.poll();
            if (visited[pop])
                continue;

            visited[pop] = true;
            System.out.print(pop + " ");

            for (int i = 0; i < map[pop].size(); i++) {
                if (visited[map[pop].get(i)] == false)
                    queue.offer(map[pop].get(i));
            }
        }


    }
}
