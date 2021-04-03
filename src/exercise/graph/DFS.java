package exercise.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DFS {
    static int V;
    static int E;
    static List<Integer> map[];
    static boolean visited[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        V = sc.nextInt();
        E = sc.nextInt();
        map = new List[V + 1];
        visited = new boolean[V + 1];
        for (int i = 1; i <= V; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            map[x].add(y);
            map[y].add(x);
        }
        sc.close();

        for (int i = 1; i <= V; i++) {
            System.out.print(i + " ");
            map[i].stream().forEach(System.out::print);
            System.out.println();
        }

        DFS(1);
    }

    static void DFS(int x) {
        visited[x] = true;
        System.out.print(x + " ");

        for (int i = 0; i < map[x].size(); i++) {
            if (visited[map[x].get(i)] == false)
                DFS(map[x].get(i));
        }
    }
}
