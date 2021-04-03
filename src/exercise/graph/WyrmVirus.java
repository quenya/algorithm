package exercise.graph;

import java.util.*;

public class WyrmVirus {
    static int V;
    static int E;
    static List<Integer> map[];
    static boolean visited[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        map = new List[V + 1];
        for (int i = 1; i < V + 1; i++) {
            map[i] = new ArrayList<>();
        }
        visited = new boolean[V + 1];
        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            map[a].add(b);
            map[b].add(a);
        }
        sc.close();

        BFS(1);

        int count = 0;
        for (int i = 0; i < visited.length; i++) {
            if (visited[i])
                count++;
        }
        System.out.println(count - 1);
    }

    static void BFS(int x) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(x);

        while (queue.isEmpty() == false) {
            Integer pop = queue.poll();
            if (visited[pop])
                continue;

            visited[pop] = true;

            for (int i = 0; i < map[pop].size(); i++) {
                if (visited[map[pop].get(i)] == false)
                    queue.offer(map[pop].get(i));
            }
        }
    }
}
